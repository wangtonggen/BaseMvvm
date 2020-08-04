package com.wang.mvvmcore.base.baseViewModel;

import android.view.View;

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
        if (basePopupView != null && basePopupView.isShow()) {
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
     * 按钮点击时间
     * @param view view
     */
    public void onViewClick(View view){

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
    }
}
