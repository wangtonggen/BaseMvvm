package com.wang.mvvmcore.rxBus;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * author: 王统根
 * time: 2020/7/28
 * desc:
 */
public abstract class RxBusObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
