package com.example.basemvvm.ui.activity;

import com.example.basemvvm.BR;
import com.example.basemvvm.R;
import com.example.basemvvm.databinding.ActivityBinderBinding;
import com.example.basemvvm.mvvm.viewModel.BinderVM;
import com.wang.mvvmcore.base.activity.BaseSwipeBackLeftActivity;
import com.wang.mvvmcore.base.baseViewModel.BaseToolbarVM;

/**
 * author: wtg
 * date:2020/5/20 0020
 * desc:
 */
public class BinderActivity extends BaseSwipeBackLeftActivity<ActivityBinderBinding, BinderVM> {
    @Override
    protected BinderVM getViewModel() {
        return new BinderVM(this);
    }

    @Override
    protected int getViewModelId() {
        return BR.binderVM;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_binder;
    }

    @Override
    protected void bindOtherViewModel() {
        binding.toolbar.setToolbarVM(new BaseToolbarVM(this));
    }

    @Override
    protected void initView() {
        binding.smartRefreshLayout.autoRefresh();
    }
}
