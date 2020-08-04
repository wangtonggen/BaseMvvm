package com.wang.mvvmcore.presenter;

import com.wang.mvvmcore.base.baseViewModel.BaseLifecycleVM;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * author: 王统根
 * time: 2020/8/4
 * desc: 数据处理类 可以在里面对ViewModel进行赋值操作 使用ViewModel和xml绑定需要在fragment 和 activity里面操作
 */
public class BasePresenter<VM extends BaseLifecycleVM> {
    protected CompositeDisposable mDisposables;
    protected VM viewModel;

    /**
     * 反省构造方法
     *
     * @param viewModel viewModel
     */
    public BasePresenter(VM viewModel) {
        mDisposables = new CompositeDisposable();
        this.viewModel = viewModel;
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
    public void onDestroy() {
        if (mDisposables != null && mDisposables.size() > 0 && !mDisposables.isDisposed()) {
            mDisposables.dispose();
        }
    }
}
