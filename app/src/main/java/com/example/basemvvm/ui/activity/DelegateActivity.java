package com.example.basemvvm.ui.activity;

import com.example.basemvvm.BR;
import com.example.basemvvm.R;
import com.example.basemvvm.databinding.ActivityDelegateBinding;
import com.example.basemvvm.mvvm.viewModel.DelegateVM;
import com.wang.mvvmcore.base.activity.BaseSwipeBackLeftActivity;

/**
 * author: wtg
 * date:2020/5/20 0020
 * desc:
 */
public class DelegateActivity extends BaseSwipeBackLeftActivity<ActivityDelegateBinding, DelegateVM> {
    @Override
    protected DelegateVM getViewModel() {
        return new DelegateVM(this);
    }

    @Override
    protected int getViewModelId() {
        return BR.delegateVM;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_delegate;
    }

    @Override
    protected void initView() {
        binding.smartRefreshLayout.autoRefresh();
    }
}
