package com.example.basemvvm.ui.activity;

import com.example.basemvvm.BR;
import com.example.basemvvm.R;
import com.example.basemvvm.databinding.ActivitySingleBinding;
import com.example.basemvvm.mvvm.viewModel.SingleVM;
import com.wang.mvvmcore.base.activity.BaseSwipeBackLeftActivity;

/**
 * author: wtg
 * date:2020/5/20 0020
 * desc:
 */
public class SingleActivity extends BaseSwipeBackLeftActivity<ActivitySingleBinding, SingleVM> {
    @Override
    protected SingleVM getViewModel() {
        return new SingleVM(this);
    }

    @Override
    protected int getViewModelId() {
        return BR.singleVM;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_single;
    }

    @Override
    protected void initView() {
        binding.smartRefreshLayout.autoRefresh();
    }
}
