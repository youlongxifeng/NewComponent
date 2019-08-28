package com.component.preject.common.manage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import androidx.annotation.Nullable;

import com.component.preject.common.base.BaseApplication;
import com.component.preject.common.utils.DateUtils;
import com.component.preject.common.utils.FileUtils;
import com.component.preject.common.utils.ZipUtils;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.common.manage
 * @ClassName: CrashHandlerManage
 * @Author: xzg
 * @CreateDate: 2019-08-28 9:35
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-28 9:35
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （UncaughtException处理类,当程序发生Uncaught异常的时候,由该类来接管程序,并记录发送错误报告.）
 */
@SuppressLint("StaticFieldLeak")
public class CrashHandlerManage  implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "CrashHandlerManage";
    /**
     *  系统默认的UncaughtException处理类
     */
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private static CrashHandlerManage INSTANCE;
    /**
     * // 程序的Context对象
     */
    private Context mContext;
    /**
     * // 用来存储设备信息和异常信息
     */
    private Map<String, String> info = new HashMap<>();

    /**
     * 保证只有一个CrashHandler实例
     */
    private CrashHandlerManage() {
    }

    public synchronized static CrashHandlerManage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CrashHandlerManage();
        }
        return INSTANCE;
    }


    /**
     * 初始化
     */
    public void init(Context context) {
        mContext = context;
        // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该重写的方法来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            // 如果自定义的没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                // 如果处理了，让程序继续运行3秒再退出，保证文件保存并上传到服务器
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ((BaseApplication) mContext.getApplicationContext()).exitApp();
            // 退出程序
        }
    }


    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex 异常信息
     * @return true 如果处理了该异常信息;否则返回false.
     */
    public boolean handleException(Throwable ex) {
        if (ex == null)
        {
            return false;
        }
        // 收集设备参数信息
        collectDeviceInfo(mContext);
        // 保存日志文件
        saveCrashInfo2File(ex);
        return true;
    }

    /**
     * 收集设备参数信息
     */
    public void collectDeviceInfo(Context context) {
        try {
            // 获得包管理器
            PackageManager pm = context.getPackageManager();
            // 得到该应用的信息，即主Activity
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null"
                        : pi.versionName;
                String versionCode = pi.versionCode + "";
                info.put("versionName", versionName);
                info.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Logger.e("获取设置信息失败");
        }

        // 反射机制
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                info.put(field.getName(), field.get("").toString());
                Logger.e(field.getName() + ":" + field.get(""));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Nullable
    private String saveCrashInfo2File(Throwable ex) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : info.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key).append("=").append(value).append("\r\n");
        }
        Writer writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        ex.printStackTrace(pw);
        Throwable cause = ex.getCause();
        // 循环着把所有的异常信息写入writer中
        while (cause != null) {
            cause.printStackTrace(pw);
            cause = cause.getCause();
        }
        pw.close();// 记得关闭
        String result = writer.toString();
        sb.append(result);
        // 保存文件
        String fileName = "crash-" + DateUtils.getCurrentDateStr() + "-" + DateUtils.getCurrentTimeStamp() + ".log";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                File file = new File(FileUtils.getAppCrashPath(), fileName);
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(sb.toString().getBytes());
                fos.close();
                return fileName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 上传日志文件
     */
    public static void uploadCrashFiles() {
        final File outFile = FileUtils.getAppCrashPath();
        LinkedList<File> files = FileUtils.listLinkedFiles(FileUtils.getAppCrashPath().getPath());
        if (files == null || files.size() == 0) {
            return;
        }
        try {
            ZipUtils.zipFiles(files, outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!outFile.exists()) {//如果这个zip文件不存在的话，则不执行如下的操作
            return;
        }
        //TODO  做上传操作
    }
}
