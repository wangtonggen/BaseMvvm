package com.example.basemvvm.mvvm.view_model_base;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * author: wtg
 * date:2020/3/13 0013
 * desc: ViewModel 基类
 */
public abstract class BaseViewModel implements LifecycleObserver {
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
     * 显示加载框
     */
    public void showLoadingDialog(){

    }

    /**
     * 关闭加载框
     */
    public void closeLoadingDialog(){

    }

    /**
     * 显示提示性对话框
     */
    public void showTextDialog(){

    }

    /**
     * 关闭提示性对话框
     */
    public void closeTextDialog(){

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

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        Log.e(TAG, "onCreate: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        Log.e(TAG, "onStart: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Log.e(TAG, "onResume: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        Log.e(TAG, "onPause: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        Log.e(TAG, "onStop: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        Log.e(TAG, "onDestroy: ");
        cancelRequest();
    }
}
