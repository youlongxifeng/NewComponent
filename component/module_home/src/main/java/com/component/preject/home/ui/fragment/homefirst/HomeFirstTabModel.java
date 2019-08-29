package com.component.preject.home.ui.fragment.homefirst;

import com.component.preject.home.bean.HomeArticleData;
import com.component.preject.home.bean.HomeArticleListData;
import com.component.preject.home.bean.HomePageBannerModel;
import com.component.preject.home.bean.ResponseBean;
import com.component.preject.home.http.ApiEngine;

import java.util.List;

import io.reactivex.Observable;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.homefirst
 * @ClassName: HomeFirstTabModel
 * @Author: xzg
 * @CreateDate: 2019-08-29 15:10
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 15:10
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class HomeFirstTabModel implements HomePageFirstTabContract.Model {


    @Override
    public Observable<ResponseBean<List<HomePageBannerModel>>> getHomePageBannerData() {
        return ApiEngine.getInstance().getApiService().getHomePageBannerData() ;
    }

    @Override
    public Observable<ResponseBean<List<HomeArticleData>>> homeTopArticleData() {
        return ApiEngine.getInstance().getApiService().homeTopArticleData() ;
    }

    @Override
    public Observable<ResponseBean<HomeArticleListData>> getHomeArticleListData(int pageNum) {
        return ApiEngine.getInstance().getApiService().getHomeArticleListData(pageNum) ;
    }


}
