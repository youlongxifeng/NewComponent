package com.component.preject.home.ui.fragment.navigation;

import com.component.preject.home.bean.NavigationListData;
import com.component.preject.home.rx.ApiException;
import com.component.preject.home.rx.HttpDisposableObserver;
import com.component.preject.home.rx.RxSchedulers;

import java.util.List;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.navigation
 * @ClassName: NavigationPresenter
 * @Author: xzg
 * @CreateDate: 2019-09-05 14:20
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-09-05 14:20
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class NavigationPresenter extends NavigationContract.Presenter {
    public NavigationPresenter(){
        mModel=new NavigationModel();
    }
    @Override
    void getNavigationData() {

        HttpDisposableObserver<List<NavigationListData>> disposableObserver = getNavigationDataObserver();
        mModel.getNavigationData()
                .compose(RxSchedulers.combine())
                .subscribe(disposableObserver);
        addSubscribe(disposableObserver);
    }

    private HttpDisposableObserver<List<NavigationListData>> getNavigationDataObserver() {
        return new HttpDisposableObserver<List<NavigationListData>>() {
            @Override
            public void onError(ApiException e) {
                if (mView != null) {
                    mView.getNavigationDataFail(e.getMsg());
                }
            }

            @Override
            public void onNext(List<NavigationListData> knowledgeBeans) {
                if (mView != null) {
                    mView.getNavigationDataSuccess(knowledgeBeans);
                }
            }
        };
    }
}
