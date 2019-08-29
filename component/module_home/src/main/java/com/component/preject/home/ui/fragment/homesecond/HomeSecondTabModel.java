package com.component.preject.home.ui.fragment.homesecond;

import com.component.preject.home.bean.HomeArticleListData;
import com.component.preject.home.bean.ResponseBean;
import com.component.preject.home.http.ApiEngine;

import io.reactivex.Observable;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.homesecond
 * @ClassName: HomeSecondTabModel
 * @Author: xzg
 * @CreateDate: 2019-08-29 14:08
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 14:08
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class HomeSecondTabModel implements HomeSecondTabContracte.Model {
    @Override
    public Observable<ResponseBean<HomeArticleListData>> getHomeArticleListProjectData(int imei) {
        return ApiEngine.getInstance().getApiService().getHomeArticleListProjectData(imei);
    }
}
