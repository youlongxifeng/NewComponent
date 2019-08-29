package com.component.preject.home.ui.fragment.homesecond;

import com.component.preject.home.bean.HomeArticleListData;
import com.component.preject.home.rx.ApiException;
import com.component.preject.home.rx.HttpDisposableObserver;
import com.component.preject.home.rx.RxSchedulers;

import io.reactivex.observers.DisposableObserver;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.homesecond
 * @ClassName: HomeSecondTabPresenter
 * @Author: xzg
 * @CreateDate: 2019-08-29 13:48
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 13:48
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class HomeSecondTabPresenter extends HomeSecondTabContracte.Presenter {

    public HomeSecondTabPresenter() {
        mModel = new HomeSecondTabModel();
    }

    @Override
    void getHomeArticleListProjectData(int pageNum) {
        DisposableObserver<HomeArticleListData> disposableObserver = getHomeArticleListDataObserver();
        mModel.getHomeArticleListProjectData(pageNum)
                .compose(RxSchedulers.combine())
                .subscribe(disposableObserver);
        addSubscribe(disposableObserver);

    }

    private HttpDisposableObserver<HomeArticleListData> getHomeArticleListDataObserver() {
        return new HttpDisposableObserver<HomeArticleListData>() {
            @Override
            public void onError(ApiException e) {
                if (mView != null) {
                    mView.onHomeArticleListFail(e.getMsg());
                }
            }

            @Override
            public void onNext(HomeArticleListData homeArticleListDataResponseBean) {
                if (mView != null) {
                    mView.onHomeArticleListSucceed(homeArticleListDataResponseBean);
                }
            }
        };
    }
}
