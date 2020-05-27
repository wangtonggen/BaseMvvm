package com.example.basemvvm.ui.activity;

import com.example.basemvvm.BR;
import com.example.basemvvm.R;
import com.example.basemvvm.databinding.ActivityProviderBinding;
import com.example.basemvvm.mvvm.viewModel.ProviderVM;
import com.wang.mvvmcore.base.activity.BaseSwipeBackLeftActivity;

/**
 * author: wtg
 * date:2020/5/20 0020
 * desc:
 */
public class ProviderActivity extends BaseSwipeBackLeftActivity<ActivityProviderBinding, ProviderVM> {
    @Override
    protected ProviderVM getViewModel() {
        return new ProviderVM(this);
    }

    @Override
    protected int getViewModelId() {
        return BR.providerVM;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_provider;
    }

    @Override
    protected void initView() {
        binding.smartRefreshLayout.autoRefresh();
    }
}
