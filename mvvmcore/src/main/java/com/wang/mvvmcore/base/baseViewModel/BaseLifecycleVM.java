package com.wang.mvvmcore.base.baseViewModel;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.LoadingPopupView;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * author: wtg
 * date:2020/3/13 0013
 * desc: ViewModel 生命周期管理的基类
 */
public abstract class BaseLifecycleVM extends BaseVM implements LifecycleObserver {
    protected LoadingPopupView loadingPopupView;
    protected CompositeDisposable mDisposables;

    /**
     * 显示加载框
     *
     * @param context 上下文
     * @param title   标题
     */
    public void showLoading(Context context, String title) {
        if (loadingPopupView == null) {
            loadingPopupView = new XPopup.Builder(context).asLoading();
        }
        loadingPopupView.setTitle(title);
        loadingPopupView.show();
    }

    /**
     * 显示加载框
     *
     * @param context 上下文
     */
    public void showLoading(Context context) {
        showLoading(context, "");
    }

    /**
     * 关闭加载框
     */
    public void closeLoading() {
        if (loadingPopupView != null && loadingPopupView.isShow()) {
            loadingPopupView.dismiss();
        }
    }

    /**
     * 按钮点击时间
     *
     * @param view view
     */
    public void onViewClick(View view) {

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
     * 清除所有的请求
     */
    public void onUnBind() {
        if (mDisposables != null && mDisposables.size() > 0 && !mDisposables.isDisposed()) {
            mDisposables.dispose();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
//        LogUtils.logE(TAG, "onCreate: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
//        LogUtils.logE(TAG, "onStart: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
//        LogUtils.logE(TAG, "onResume: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
//        LogUtils.logE(TAG, "onPause: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
//        LogUtils.logE(TAG, "onStop: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        onUnBind();
//        LogUtils.logE(TAG, "onDestroy: ");
    }
}
