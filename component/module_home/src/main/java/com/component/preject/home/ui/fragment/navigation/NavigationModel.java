package com.component.preject.home.ui.fragment.navigation;

import com.component.preject.home.bean.NavigationListData;
import com.component.preject.home.bean.ResponseBean;
import com.component.preject.home.http.ApiEngine;

import java.util.List;

import io.reactivex.Observable;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.navigation
 * @ClassName: NavigationModel
 * @Author: xzg
 * @CreateDate: 2019-09-05 14:20
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-09-05 14:20
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class NavigationModel implements NavigationContract.Model {
    @Override
    public Observable<ResponseBean<List<NavigationListData>>> getNavigationData() {
        return ApiEngine.getInstance().getApiService().getNavigationListData();
    }
}
