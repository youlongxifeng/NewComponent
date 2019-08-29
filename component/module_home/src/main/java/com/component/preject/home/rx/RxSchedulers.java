package com.component.preject.home.rx;

import com.component.preject.home.bean.ResponseBean;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @ProjectName: NewComponent
 * @Package: com.component.preject.home.rx
 * @ClassName: RxSchedulers
 * @Author: xzg
 * @CreateDate: 2019-08-29 9:58
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-08-29 9:58
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @description: （java类作用描述）
 */
public class RxSchedulers {
    public static <T> FlowableTransformer<T, T> switchFlowableThread() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 统一线程处理 ompose简化线程
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> switchObservableThread() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        //指定的就是发射事件的线程
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .onErrorResumeNext(new ErrorObservableSource<T>())
                        //指定的就是订阅者接收事件的线程。
                        .observeOn(AndroidSchedulers.mainThread());

            }
        };
    }

    public static <T> ObservableTransformer<ResponseBean<T>, T> switchObservableThread2() {
        return new ObservableTransformer<ResponseBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<ResponseBean<T>> upstream) {
                return (ObservableSource<T>) upstream
                        //指定的就是发射事件的线程
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .map(new MapFunction<T>())
                        .onErrorResumeNext(new ErrorObservableSource<T>())
                        //指定的就是订阅者接收事件的线程。
                        .observeOn(AndroidSchedulers.mainThread());

            }
        };
    }


    public static <T> ObservableTransformer<ResponseBean<T>, T> combine() {
        return new ObservableTransformer<ResponseBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<ResponseBean<T>> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .map(new MapFunction<T>())
                        .onErrorResumeNext(new ErrorObservableSource<T>())
                        .observeOn(AndroidSchedulers.mainThread());

            }
        };
    }
}
