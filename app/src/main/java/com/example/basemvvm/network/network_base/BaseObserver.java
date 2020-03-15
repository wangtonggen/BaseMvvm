package com.example.basemvvm.network.network_base;


import com.example.basemvvm.bean.HttpResponse;
import com.example.basemvvm.utils.common_utils.LogUtils;
import com.example.basemvvm.utils.common_utils.ToastUtils;

import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * author: wtg  2019/4/24 0024
 * desc: 对数据处理
 */
public abstract class BaseObserver<T> implements Observer<HttpResponse<T>> {
//    private Disposable disposable;
    @Override
    public void onNext(HttpResponse<T> tHttpResponse) {
        onSuccess(tHttpResponse);
        //里面过滤 抛出异常
//        switch (tHttpResponse.getCode()) {
//        }
    }

    @Override
    public void onSubscribe(Disposable d) {
//        this.disposable = d;
    }

    @Override
    public void onComplete() {
        disposeIt();
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {//连接超时
            ToastUtils.showShortToast("连接超时，请稍后再试!");
        }
        disposeIt();
        onFail(e);
    }

    public void onFail(Throwable e){
        LogUtils.logE(e.getMessage());
    }

    /**
     * 销毁disposable
     */
    private void disposeIt() {
//        if (disposable != null && !disposable.isDisposed()) {
//            disposable.dispose();
//            disposable = null;
//        }
    }


    public abstract void onSuccess(HttpResponse<T> data);


}
