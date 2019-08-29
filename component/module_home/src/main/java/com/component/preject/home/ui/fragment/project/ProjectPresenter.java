package com.component.preject.home.ui.fragment.project;

import com.component.preject.home.bean.ProjectClassifyData;
import com.component.preject.home.rx.ApiException;
import com.component.preject.home.rx.HttpDisposableObserver;
import com.component.preject.home.rx.RxSchedulers;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.ui.fragment.project
 * @ClassName: ProjectPresenter
 * @Author: xzg
 * @CreateDate: 2019-08-29 11:51
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 11:51
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class ProjectPresenter extends ProjectContract.Presenter {
    public ProjectPresenter() {
        mModel = new ProjectModel();
    }

    @Override
    void getProjectClassifyData() {
        DisposableObserver<List<ProjectClassifyData>> disposableObserver = getProjectClassifyDataObserver();
        mModel.getProjectClassifyData()
                .compose(RxSchedulers.combine())
                .subscribe(disposableObserver);
        addSubscribe(disposableObserver);
    }

    private DisposableObserver<List<ProjectClassifyData>> getProjectClassifyDataObserver() {
        return new HttpDisposableObserver<List<ProjectClassifyData>>() {
            @Override
            public void onNext(List<ProjectClassifyData> projectClassifyData) {
                if (mView != null) {
                    mView.onProjectClassifyDataSucceed(projectClassifyData);
                }

            }

            @Override
            public void onError(ApiException e) {
                if (mView != null) {
                    mView.onProjectClassifyDataFail(e.getMsg());
                }
            }
        };
    }
}
