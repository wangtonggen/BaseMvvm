package com.wang.mvvmcore.network.networkBase;

import io.reactivex.observers.DefaultObserver;

/**
 * author: wtg
 * date:2020/3/18 0018
 * desc:
 */
public abstract class FileDownLoadObserver<T> extends DefaultObserver<T> {
    @Override
    public void onNext(T t) {
        onDownLoadSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onDownLoadFail(e);
    }

    @Override
    public void onComplete() {

    }

    //下载成功的回调
    public abstract void onDownLoadSuccess(T t);

    //下载失败回调
    public abstract void onDownLoadFail(Throwable throwable);

    //下载进度监听
    public abstract void onProgress(int progress, long total);
}
