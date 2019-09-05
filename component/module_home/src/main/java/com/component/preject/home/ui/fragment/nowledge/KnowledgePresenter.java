package com.component.preject.home.ui.fragment.nowledge;

import com.component.preject.home.bean.KnowledgeHierarchyData;
import com.component.preject.home.rx.ApiException;
import com.component.preject.home.rx.HttpDisposableObserver;
import com.component.preject.home.rx.RxSchedulers;

import java.util.List;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.nowledge
 * @ClassName: KnowledgePresenter
 * @Author: xzg
 * @CreateDate: 2019-09-05 10:49
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-09-05 10:49
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class KnowledgePresenter extends KnowledgeContract.Presenter {
    public KnowledgePresenter() {
        mModel = new KnowledgeModel();
    }

    @Override
    void getKnowledgeListCache() {

    }

    @Override
    void getKnowledgeList() {
        HttpDisposableObserver<List<KnowledgeHierarchyData>> disposableObserver = getKnowledgeBeanObserver();
        mModel.getKnowledgeList()
                .compose(RxSchedulers.combine())
                .subscribe(disposableObserver);
        addSubscribe(disposableObserver);
    }

    private HttpDisposableObserver<List<KnowledgeHierarchyData>> getKnowledgeBeanObserver() {
        return new HttpDisposableObserver<List<KnowledgeHierarchyData>>() {
            @Override
            public void onError(ApiException e) {
                if (mView != null) {
                    mView.getKnowledgeListFail(e.getMsg());
                }
            }

            @Override
            public void onNext(List<KnowledgeHierarchyData> knowledgeBeans) {
                if (mView != null) {
                    mView.getKnowledgeListSuccess(knowledgeBeans);
                }
            }
        };
    }
}
