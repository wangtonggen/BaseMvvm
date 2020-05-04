package com.example.basemvvm.base.baseViewModel;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.lxj.xpopup.core.BasePopupView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * author: wtg
 * date:2020/3/13 0013
 * desc: ViewModel 生命周期管理的基类
 */
public abstract class BaseLifecycleVM extends BaseVM implements LifecycleObserver {
    private CompositeDisposable mDisposables = new CompositeDisposable();//请求管理类
    protected BasePopupView basePopupView;
    /**
     * 显示加载框
     */
    public void showLoadingDialog(String content) {

    }

    /**
     * 关闭加载框
     */
    public void closeLoadingDialog() {
        if (basePopupView != null && basePopupView.isShow()){
            basePopupView.dismiss();
        }
    }

    /**
     * 显示提示性对话框
     */
    public void showTextDialog() {

    }

    /**
     * 关闭提示性对话框
     */
    public void closeTextDialog() {

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
//        LogUtils.logE(TAG, "onDestroy: ");
        cancelRequest();
    }
}
