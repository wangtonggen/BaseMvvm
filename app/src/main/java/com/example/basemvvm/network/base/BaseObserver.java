package com.example.basemvvm.network.base;

import com.blankj.utilcode.util.ToastUtils;
import com.example.basemvvm.bean.HttpResponse;
import com.google.gson.JsonParseException;
import com.wang.mvvmcore.network.exception.ResultException;
import com.wang.mvvmcore.utils.common.LogUtils;

import java.net.SocketTimeoutException;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * author: wtg  2019/4/24 0024
 * desc: 对数据处理
 */
public abstract class BaseObserver<T> implements Observer<HttpResponse<T>> {
    @Override
    public void onNext(HttpResponse<T> tHttpResponse) {
        if (tHttpResponse.getCode() != 1) {
            onError(new ResultException(tHttpResponse.getCode(), tHttpResponse.getMsg()));
        } else {
            onSuccess(tHttpResponse);
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {//连接超时
            ToastUtils.showShort("连接超时，请稍后再试!");
        } else if (e instanceof ResultException) {
            ResultException resultException = (ResultException) e;
            switch (resultException.getCode()) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
            ToastUtils.showShort(e.getMessage());
        } else if (e instanceof JsonParseException) {
            ToastUtils.showShort("数据错误");
        }
        onFail(e);
    }

    public void onFail(Throwable e) {
        LogUtils.logE("Throwable:" + e.getMessage());
    }

    public abstract void onSuccess(HttpResponse<T> data);

}
