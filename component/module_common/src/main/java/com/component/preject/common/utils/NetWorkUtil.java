package com.component.preject.common.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.component.preject.common.base.BaseApplication;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.common.utils
 * @ClassName: NetWorkUtil
 * @Author: xzg
 * @CreateDate: 2019-08-28 15:51
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-28 15:51
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class NetWorkUtil {
    /**
     * 判断网络是否连接
     * @return 网络是否连接
     */
    public static boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) BaseApplication.getApplication().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}
