package com.example.basemvvm.ui.fragment;

import com.example.basemvvm.BR;
import com.example.basemvvm.R;
import com.example.basemvvm.databinding.FragmentDashboardBinding;
import com.example.basemvvm.mvvm.viewModel.DashboardVM;
import com.wang.mvvmcore.base.fragment.BaseMVVMFragment;


public class DashboardFragment extends BaseMVVMFragment<FragmentDashboardBinding, DashboardVM> {
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_dashboard;
    }

    @Override
    protected DashboardVM getViewModel() {
        return new DashboardVM(this);
    }

    @Override
    protected int getViewModelId() {
        return BR.dashboardVM;
    }

    @Override
    public void onLazyLoadData() {
//        LogUtils.logE(TAG, "DashboardFragment");
    }
}
