package com.example.basemvvm.ui.fragment;

import com.example.basemvvm.BR;
import com.example.basemvvm.R;
import com.example.basemvvm.base.fragment.LazyLoadFragment;
import com.example.basemvvm.databinding.FragmentDashboardBinding;
import com.example.basemvvm.mvvm.view_model.DashboardVM;
import com.example.basemvvm.utils.common_utils.LogUtils;


public class DashboardFragment extends LazyLoadFragment<FragmentDashboardBinding, DashboardVM> {
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
        LogUtils.logE(TAG, "DashboardFragment");
    }
}
