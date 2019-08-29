package com.component.preject.home.ui.fragment.homefirst;

import com.component.preject.home.bean.HomeArticleListData;
import com.component.preject.home.bean.HomePageBannerModel;
import com.component.preject.home.rx.ApiException;
import com.component.preject.home.rx.HttpDisposableObserver;
import com.component.preject.home.rx.RxSchedulers;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.homefirst
 * @ClassName: HomeFirstTabPresenter
 * @Author: xzg
 * @CreateDate: 2019-08-29 15:10
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 15:10
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class HomeFirstTabPresenter extends HomePageFirstTabContract.Presenter {
    public HomeFirstTabPresenter() {
        mModel = new HomeFirstTabModel();
    }


    @Override
    void getHomeFirstPageData(boolean isRefreshData) {

    }

    @Override
    void getHomePageBannerData() {
        DisposableObserver<List<HomePageBannerModel>> disposableObserver = getHomePageBannerModelObserver();
        mModel.getHomePageBannerData()
                .compose(RxSchedulers.combine())
                .subscribe(disposableObserver);
        addSubscribe(disposableObserver);
    }

    private HttpDisposableObserver<List<HomePageBannerModel>> getHomePageBannerModelObserver() {
        return new HttpDisposableObserver<List<HomePageBannerModel>>() {
            @Override
            public void onNext(List<HomePageBannerModel> homePageBannerModels) {
                if (mView != null) {
                    mView.showHomePageBanner(homePageBannerModels);
                }
            }

            @Override
            public void onError(ApiException e) {
                if (mView != null) {
                    mView.showHomePageBannerFail(e.getMsg());
                }
            }
        };
    }

    @Override
    void homeTopArticleData() {

    }

    @Override
    void getHomeArticleListData(int pageNum) {
        DisposableObserver<HomeArticleListData> disposableObserver = getHomeArticleListDataObserver();
        mModel.getHomeArticleListData(pageNum)
                .compose(RxSchedulers.combine())
                .subscribe(disposableObserver);
        addSubscribe(disposableObserver);
    }

    private HttpDisposableObserver<HomeArticleListData> getHomeArticleListDataObserver() {
        return new HttpDisposableObserver<HomeArticleListData>() {

            @Override
            public void onNext(HomeArticleListData homeArticleListData) {
                if (mView != null) {
                    mView.showHomeArticleList(homeArticleListData);
                }
            }

            @Override
            public void onError(ApiException e) {
                if (mView != null) {
                    mView.showAutoLoginFail(e.getMsg());
                }
            }
        };
    }


}
