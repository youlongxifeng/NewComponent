package com.component.preject.home.rx;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.rx
 * @ClassName: ErrorObservableSource
 * @Author: xzg
 * @CreateDate: 2019-08-29 10:01
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 10:01
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class ErrorObservableSource<T> implements Function<Throwable, ObservableSource<? extends T>> {
    @Override
    public ObservableSource<? extends T>  apply(Throwable throwable) throws Exception {
        return Observable.error(ApiException.handleException(throwable));
    }
}


