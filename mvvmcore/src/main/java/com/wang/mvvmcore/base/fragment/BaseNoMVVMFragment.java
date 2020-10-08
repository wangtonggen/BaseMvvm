package com.wang.mvvmcore.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.ButterKnife;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * author: wtg
 * date:2020/3/21 0021
 * desc: 不适用binding的fragment的基类
 */
public abstract class BaseNoMVVMFragment extends BaseLazyLoadFragment {
    protected View rootView;
    protected CompositeDisposable mDisposables;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = LayoutInflater.from(mContext).inflate(getLayoutRes(), container, false);
        }
        ButterKnife.bind(rootView);
        initView(savedInstanceState, rootView);
        return rootView;
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        onUnBind();
    }

    /**
     * 清除所有的请求
     */
    public void onUnBind() {
        if (mDisposables != null && mDisposables.size() > 0 && !mDisposables.isDisposed()) {
            mDisposables.dispose();
        }
    }
}
