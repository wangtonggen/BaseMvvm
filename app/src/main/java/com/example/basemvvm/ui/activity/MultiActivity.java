package com.example.basemvvm.ui.activity;

import com.example.basemvvm.BR;
import com.example.basemvvm.R;
import com.example.basemvvm.databinding.ActivityMultiBinding;
import com.example.basemvvm.mvvm.viewModel.MultiVM;
import com.wang.mvvmcore.base.activity.BaseSwipeBackLeftActivity;

/**
 * author: wtg
 * date:2020/5/20 0020
 * desc:
 */
public class MultiActivity extends BaseSwipeBackLeftActivity<ActivityMultiBinding, MultiVM> {
    @Override
    protected MultiVM getViewModel() {
        return new MultiVM(this);
    }

    @Override
    protected int getViewModelId() {
        return BR.multiVM;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_multi;
    }

    @Override
    protected void initView() {
        binding.smartRefreshLayout.autoRefresh();
    }
}
