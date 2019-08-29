package com.component.preject.home.ui.fragment.official;

import com.component.preject.home.bean.KnowledgeHierarchyData;
import com.component.preject.home.rx.ApiException;
import com.component.preject.home.rx.HttpDisposableObserver;
import com.component.preject.home.rx.RxSchedulers;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.official
 * @ClassName: OfficialAccountsPagePresenter
 * @Author: xzg
 * @CreateDate: 2019-08-29 9:50
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 9:50
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class OfficialAccountsPagePresenter extends OfficialAccountsPageContract.Presenter {
    public OfficialAccountsPagePresenter() {
        mModel = new OfficialAccountsPageModel();
    }

    @Override
    void getOfficialAccountsListData() {
        DisposableObserver<List<KnowledgeHierarchyData>> disposableObserver = getOfficialAccountsPageObserver();
        mModel.getWxArticle()
                .compose(RxSchedulers.combine())
                .subscribe(disposableObserver);
        addSubscribe(disposableObserver);
    }

    /**
     * 观察者获取公众号
     * @return
     */
    private HttpDisposableObserver<List<KnowledgeHierarchyData>> getOfficialAccountsPageObserver() {
        return new HttpDisposableObserver<List<KnowledgeHierarchyData>>() {
            @Override
            public void onNext(List<KnowledgeHierarchyData> knowledgeHierarchyData) {
                if (mView != null) {
                    mView.showOfficialAccountsListSuccess(knowledgeHierarchyData);
                }
            }

            @Override
            public void onError(ApiException e) {
                if (mView != null) {
                    mView.showOfficialAccountsListFail(e.getMsg());
                }
            }
        };
    }
}
