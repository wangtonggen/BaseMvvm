package com.wang.mvvmcore.base.activity;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.wang.mvvmcore.base.baseViewModel.BaseLifecycleVM;

/**
 * author: wtg
 * date:2020/3/12 0012
 * desc: activity 基类（使用数据绑定）
 */
public abstract class BaseMVVMActivity<B extends ViewDataBinding, VM extends BaseLifecycleVM> extends BaseActivity {
    protected B binding;
    protected int viewModelId;
    protected VM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutRes());
        //添加activity 到activity管理器里面
        viewModel = getViewModel();
        viewModelId = getViewModelId();
        refreshLayout();
        getLifecycle().addObserver(viewModel);//监听生命周期
        initView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (viewModel != null) {
            viewModel.onDestroy();
        }
        if (binding != null) {
            binding.unbind();
        }
    }


    /**
     * 刷新布局
     */
    protected void refreshLayout() {
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
