package com.wang.mvvmcore.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.ButterKnife;

/**
 * author: wtg
 * date:2020/3/21 0021
 * desc: 不适用mvvm的fragment的基类
 */
public abstract class BaseNoMVVMFragment extends BaseFragment {
    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = LayoutInflater.from(mContext).inflate(getLayoutRes(), container, false);
        ButterKnife.bind(rootView);
        initView(savedInstanceState, rootView);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
