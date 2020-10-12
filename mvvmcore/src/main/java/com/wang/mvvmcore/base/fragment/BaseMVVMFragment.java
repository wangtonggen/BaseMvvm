package com.wang.mvvmcore.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.wang.mvvmcore.base.baseViewModel.BaseLifecycleVM;
import com.wang.mvvmcore.utils.common.CoreLogUtils;

/**
 * author: wtg
 * date:2020/3/12 0012
 * desc: fragment 基类使用数据绑定
 */
public abstract class BaseMVVMFragment<B extends ViewDataBinding, VM extends BaseLifecycleVM> extends BaseLazyLoadFragment {
    protected B binding;
    protected int viewModelId;
    protected VM viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CoreLogUtils.logE("create", "onCreateView");
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
        }
        initView(savedInstanceState, binding.getRoot());
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //view 创建之后
        viewModelId = getViewModelId();
        viewModel = getViewModel();
        refreshLayout();
        getLifecycle().addObserver(viewModel);
    }

    /**
     * 设置viewModel
     */
    private void refreshLayout() {
        if (viewModel != null) {
            binding.setVariable(viewModelId, viewModel);
        }
        bindOtherViewModel();
    }

    /**
     * 绑定其他的viewModel
     */
    protected void bindOtherViewModel() {

    }

    /**
     * 获取viewModel
     *
     * @return viewModel
     */
    protected abstract VM getViewModel();

    /**
     * 获取BR的id
     *
     * @return id
     */
    protected abstract int getViewModelId();
}
