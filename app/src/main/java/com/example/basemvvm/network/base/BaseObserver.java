package com.example.basemvvm.network.base;

import com.blankj.utilcode.util.ToastUtils;
import com.example.basemvvm.bean.HttpResponse;
import com.google.gson.JsonParseException;
import com.wang.mvvmcore.network.exception.ResultException;
import com.wang.mvvmcore.utils.common.CoreLogUtils;

import java.net.SocketTimeoutException;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * author: wtg  2019/4/24 0024
 * desc: 对数据处理封装，可以根据自己的需具体业务和数据标准来定制
 *       只需把{@link HttpResponse}替换成自己定义的数据格式基类就可以了
 *       把相应的{@link com.example.basemvvm.network.service.UserService}数据类型替换
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
        CoreLogUtils.logE("Throwable:" + e.getMessage());
    }

    public abstract void onSuccess(HttpResponse<T> data);

}
