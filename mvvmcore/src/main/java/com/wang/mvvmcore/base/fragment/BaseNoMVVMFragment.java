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
 * desc: 不适用binding的fragment的基类
 */
public abstract class BaseNoMVVMFragment extends BaseLazyLoadFragment {
    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null){
            rootView = LayoutInflater.from(mContext).inflate(getLayoutRes(), container, false);
        }
        ButterKnife.bind(rootView);
        initView(savedInstanceState, rootView);
        return rootView;
    }
}
