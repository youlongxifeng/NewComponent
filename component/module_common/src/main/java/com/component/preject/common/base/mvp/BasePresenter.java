package com.component.preject.common.base.mvp;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.common.base.mvp
 * @ClassName: BasePresenter
 * @Author: xzg
 * @CreateDate: 2019-08-28 16:12
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-28 16:12
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class BasePresenter<V extends BaseView, M extends BaseModel> {
    /***
     每当我们得到一个Disposable时就调用CompositeDisposable.add()将它添加到容器中,
     // 在退出的时候, 调用CompositeDisposable.clear() 即可切断所有的水管.*/
    private CompositeDisposable mCompositeSubscription;

    protected V mView;
    protected M mModel;


    public void attachView(BaseView view) {
        this.mView = (V) view;
    }

    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }


    protected void addSubscribe(Disposable subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeDisposable();
        }
        mCompositeSubscription.add(subscription);
    }

    /**
     * 在退出的时候, 调用CompositeDisposable.clear() 即可切断所有的水管.
     */
    public void unSubscribe() {
        if (mView != null) {
            mView = null;
        }
        if (mCompositeSubscription != null && mCompositeSubscription.isDisposed()) {
            mCompositeSubscription.dispose();
            mCompositeSubscription.clear();
        }
    }

}
