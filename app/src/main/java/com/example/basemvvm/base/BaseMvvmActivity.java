package com.example.basemvvm.base;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.basemvvm.mvvm.view_model_base.BaseViewModel;

/**
 * author: wtg
 * date:2020/3/12 0012
 * desc: activity 基类
 */
public abstract class BaseMvvmActivity<B extends ViewDataBinding,VM extends BaseViewModel> extends BaseActivity {
    protected B binding;
    protected int viewModelId;
    protected VM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,getLayoutRes());
        //添加activity 到activity管理器里面
        viewModel = getViewModel();
        viewModelId = getViewModelId();
        refreshLayout();

        initView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (binding != null){
            binding.unbind();
        }

        if (viewModel != null){
            viewModel.onDestroy();
        }
    }

    /**
     * 初始化控件
     */
    protected void initView() {

    }

    /**
     * 刷新布局
     */
    protected void refreshLayout(){
        if (viewModel != null) {
            binding.setVariable(viewModelId, viewModel);
        }
    }

    /**
     * 获取viewModel
     * @return viewModel
     */
    protected abstract VM getViewModel();

    /**
     * 获取BR的id
     * @return id
     */
    protected abstract int getViewModelId();
}
