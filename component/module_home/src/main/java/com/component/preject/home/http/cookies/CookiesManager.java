package com.component.preject.home.http.cookies;

import com.component.preject.common.base.BaseApplication;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.common.http.cookies
 * @ClassName: CookiesManager
 * @Author: xzg
 * @CreateDate: 2019-08-28 15:48
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-28 15:48
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */

public class CookiesManager implements CookieJar {
    private final PersistentCookieStore cookieStore = new PersistentCookieStore(BaseApplication.getApplication());

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null && cookies.size() > 0) {
            for (Cookie item : cookies) {
                cookieStore.add(url, item);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url);
        return cookies != null ? cookies : new ArrayList<Cookie>();
    }
}