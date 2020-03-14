package com.example.basemvvm.mvvm.view_model_base;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;

import com.trello.rxlifecycle2.LifecycleTransformer;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * author: wtg
 * date:2020/3/13 0013
 * desc: ViewModel 基类
 */
public abstract class BaseViewModel {
    protected final String TAG = this.getClass().getSimpleName();
    private CompositeDisposable mDisposables = new CompositeDisposable();//请求管理类

    /**
     * View 的点击事件
     *
     * @param view view
     */
    public void onViewClick(View view) {

    }

    /**
     * 设置背景色
     *
     * @param view    view
     * @param colorId 颜色资源id
     */
    public void setBgColor(View view, @ColorRes int colorId) {
        view.setBackgroundResource(colorId);
    }

    /**
     * 设置字体颜色
     *
     * @param textView view
     * @param colorId  颜色资源id
     */
    public void setTextColor(TextView textView, @ColorRes int colorId) {
        textView.setTextColor(textView.getResources().getColor(colorId));
    }

    /**
     * 设置字体大小
     *
     * @param textView view
     * @param sizeId   子弟大小id
     */
    public void setTextSize(TextView textView, @DimenRes int sizeId) {
        textView.setTextSize(textView.getResources().getDimension(sizeId));
    }

    /**
     * 加入订阅对象
     *
     * @param disposable 订阅对象
     */
    protected void addDisposable(Disposable disposable) {
        if (mDisposables == null || mDisposables.isDisposed()) {
            mDisposables = new CompositeDisposable();
        }
        mDisposables.add(disposable);
    }

    /**
     * 取消所有请求
     */
    private void cancelRequest() {
        if (mDisposables != null && mDisposables.size() > 0 && !mDisposables.isDisposed())
            mDisposables.dispose();
    }

    /**
     * 及时解绑防止内存泄漏
     */
    public void onDestroy() {
        cancelRequest();//停止请求数据
    }

    /**
     * 绑定生命周期到指定的资源
     *
     * @param <T> T
     * @return 返回
     */
    public abstract <T> LifecycleTransformer<T> bindToLifecycle();

//    /**
//     * 摧毁数据 解绑 停止请求
//     */
//    public abstract void onDestroy();
}
