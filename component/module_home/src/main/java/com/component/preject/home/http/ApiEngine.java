package com.component.preject.home.http;

import com.component.preject.common.base.BaseApplication;
import com.component.preject.common.http.AppConfigInfo;
import com.component.preject.home.http.api.ApiService;
import com.component.preject.home.http.cookies.CookiesManager;
import com.component.preject.home.http.interceptor.NetworkInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.common.http
 * @ClassName: ApiEngine
 * @Author: xzg
 * @CreateDate: 2019-08-28 15:46
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-28 15:46
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class ApiEngine {
    private volatile static ApiEngine apiEngine;
    private Retrofit retrofit;

    private ApiEngine() {
        //日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //缓存
        int size = 1024 * 1024 * 100;
        File cacheFile = new File(BaseApplication.getApplication().getCacheDir(), "OkHttpCache");
        Cache cache = new Cache(cacheFile, size);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(12, TimeUnit.SECONDS)
                .writeTimeout(12, TimeUnit.SECONDS)
                .writeTimeout(12, TimeUnit.SECONDS)
                ///在OkHttpClient创建时，传入这个CookieJar的实现，就能完成对Cookie的自动管理了
                .cookieJar(new CookiesManager())
                // 将有网络拦截器当做网络拦截器添加
                .addNetworkInterceptor(new NetworkInterceptor())
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(AppConfigInfo.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    public static ApiEngine getInstance() {
        if (apiEngine == null) {
            synchronized (ApiEngine.class) {
                if (apiEngine == null) {
                    apiEngine = new ApiEngine();
                }
            }
        }
        return apiEngine;
    }

    public ApiService getApiService() {
        return retrofit.create(ApiService.class);
    }
}
