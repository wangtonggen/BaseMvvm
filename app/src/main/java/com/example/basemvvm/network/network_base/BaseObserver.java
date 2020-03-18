package com.example.basemvvm.network.network_base;


import com.example.basemvvm.bean.HttpResponse;
import com.example.basemvvm.network.exception.ResultException;
import com.example.basemvvm.utils.common_utils.LogUtils;
import com.example.basemvvm.utils.common_utils.ToastUtils;
import com.google.gson.JsonParseException;

import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * author: wtg  2019/4/24 0024
 * desc: 对数据处理
 */
public abstract class BaseObserver<T> implements Observer<HttpResponse<T>> {
    @Override
    public void onNext(HttpResponse<T> tHttpResponse) {
        onSuccess(tHttpResponse);
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
            ToastUtils.showShortToast("连接超时，请稍后再试!");
        }else if (e instanceof ResultException){
            ResultException resultException = (ResultException) e;
            switch (resultException.getCode()){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
//            ToastUtils.showShortToast(e.getMessage());
        }else if (e instanceof JsonParseException){
            ToastUtils.showShortToast("数据错误");
        }
        onFail(e);
    }

    public void onFail(Throwable e){
        LogUtils.logE(e.getMessage());
    }



    public abstract void onSuccess(HttpResponse<T> data);


}
