package com.component.preject.home.rx;

import io.reactivex.observers.DisposableObserver;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.rx
 * @ClassName: HttpDisposableObserver
 * @Author: xzg
 * @CreateDate: 2019-08-29 9:53
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 9:53
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public abstract class HttpDisposableObserver<T>  extends DisposableObserver<T> {
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ApiException) {
            onError((ApiException) e);
        } else {
            onError(new ApiException(e));
        }
    }

    @Override
    public void onComplete() {
    }

    public abstract void onError(ApiException e);
}
