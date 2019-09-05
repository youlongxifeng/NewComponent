package com.component.preject.home.http.interceptor;

import com.component.preject.common.utils.LogUtils;
import com.component.preject.common.utils.NetWorkUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.common.http.interceptor
 * @ClassName: NetworkInterceptor
 * @Author: xzg
 * @CreateDate: 2019-08-28 15:49
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-28 15:49
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class NetworkInterceptor implements Interceptor {
    private final static String TAG = NetworkInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request requestBuilder = chain.request();
        Request.Builder builder = requestBuilder.newBuilder();

      /*  //设备类型;
        builder.addHeader(Constant.FX_DEVICE_TYPE, Constant.FX_MULTIPLE)
                //设备ID FX_TOKEN
                .addHeader(Constant.FX_DEVICE_ID, PhoneUtils.getDeviceId())
                //时间戳
                .addHeader(Constant.FX_TIMESTAMP, String.valueOf(System.currentTimeMillis()));
        if (StringUtils.noEmpty(SharedPreferencesUtils.getString(Constant.FX_TOKEN))) {
            builder.addHeader(Constant.FX_TOKEN, SharedPreferencesUtils.getString(Constant.FX_TOKEN));
        }*/
        boolean isConnected = NetWorkUtil.isConnected();
        //无网络时强制使用缓存
        if (!isConnected) {
            builder.cacheControl(CacheControl.FORCE_CACHE)
                    .build();

        }
        String method = requestBuilder.method();
        if ("POST".equalsIgnoreCase(method)) {
            LogUtils.i(TAG, "POST 请求方式  url=" + requestBuilder.url());
        } else if ("GET".equalsIgnoreCase(method)) {
            LogUtils.i(TAG, "GET 请求方式  url=" + requestBuilder.url());
        }
        Request request = builder.build();
        Response response = chain.proceed(request);
        return response;
    }
}


