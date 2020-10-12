package com.wang.mvvmcore.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * author: wtg
 * date:2020/3/21 0021
 * desc: fragment基类
 */
public abstract class BaseFragment extends Fragment {
    protected final String TAG = this.getClass().getSimpleName();
    protected Context mContext;
    protected Activity mActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
        this.mActivity = getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            getBundleArgument(getArguments());
        }
    }

    /**
     * 用newInstance创建 fragment 获取参数
     *
     * @param bundle bundle
     */
    public void getBundleArgument(Bundle bundle) {

    }

    protected void initView(Bundle savedInstanceState, View rootView) {

    }


    @LayoutRes
    protected abstract int getLayoutRes();
}
