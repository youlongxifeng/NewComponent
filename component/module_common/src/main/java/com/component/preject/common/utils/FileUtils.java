package com.component.preject.common.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.component.preject.common.base.BaseApplication;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.common.utils
 * @ClassName: FileUtils
 * @Author: xzg
 * @CreateDate: 2019-08-28 9:39
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-28 9:39
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （文件工具类）
 */
public class FileUtils {
    public static final String APPS_ROOT_DIR = getExternalStorePath() + File.separator + BaseApplication.getApplication().getPackageName();
    public static final String IMAGE_PATH = APPS_ROOT_DIR + "/Image";
    public static final String TEMP_PATH = APPS_ROOT_DIR + "/Temp";
    public static final String APP_CRASH_PATH = APPS_ROOT_DIR + "/AppCrash";
    public static final String FILE_PATH = APPS_ROOT_DIR + "/File";


    /**
     * 外置存储卡的路径
     *
     * @return
     */
    @Nullable
    public static String getExternalStorePath() {
        if (isExistExternalStore()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return null;
    }

    /**
     * 是否有外存卡
     *
     * @return
     */
    public static boolean isExistExternalStore() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    private static File create(String path) {
        if (!isExistExternalStore()) {
            LogUtils.e("储存卡已拔出");
            return null;
        }
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdir();
        }
        return directory;
    }

    /**
     * 返回图片存放目录
     *
     * @return File
     */
    @Nullable
    public static File getImagePath() {
        return create(IMAGE_PATH);
    }

    /**
     * 返回临时存放目录
     *
     * @return File
     */
    @Nullable
    public static File getTempPath() {
        return create(TEMP_PATH);
    }

    /**
     * 存储日志文件目录
     *
     * @return File
     */
    public static File getAppCrashPath() {
        return create(APP_CRASH_PATH);
    }

    /**
     * 存储文件目录
     *
     * @return File
     */
    public static File getFilePath() {
        return create(FILE_PATH);
    }

    /**
     * 7.0以上拍照 安装应用等文件问题
     *
     * @param context context
     * @param file    file
     * @return Uri
     */
    public static Uri getUriForFile(Context context, File file) {
        Uri fileUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            fileUri = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", file);
        } else {
            fileUri = Uri.fromFile(file);
        }
        return fileUri;
    }


    /**
     * 获取目录下的文件列表
     *
     * @param strPath strPath
     */
    public static LinkedList<File> listLinkedFiles(String strPath) {
        File dir = new File(strPath);
        File file[] = dir.listFiles();
        LinkedList<File> list = null;
        if (dir.exists() && file != null && file.length > 0) {
            list = new LinkedList<>();
            Collections.addAll(list, file);
        }
        return list;
    }

    /**
     * 删除多个文件
     *
     * @param filesName filesName
     */
    public static void deleteListFiles(String filesName) {
        LinkedList<File> files = listLinkedFiles(filesName);
        if (files != null && files.size() > 0) {
            for (File file : files) {
                file.delete();
            }
        }
    }

    /**
     * 删除指定文件
     *
     * @param filesName filesName
     */
    public static void deleteFile(String filesName) {
        File file = new File(filesName);
        if (file.exists()) {
            file.delete();
        }
    }

}
