package com.example.basemvvm.ui.fragment;

import com.example.basemvvm.BR;
import com.example.basemvvm.R;
import com.example.basemvvm.databinding.FragmentHomeBinding;
import com.example.basemvvm.mvvm.viewModel.HomeVM;
import com.wang.mvvmcore.base.fragment.BaseMVVMFragment;


public class HomeFragment extends BaseMVVMFragment<FragmentHomeBinding, HomeVM> {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomeVM getViewModel() {
        return new HomeVM(this);
    }

    @Override
    protected int getViewModelId() {
        return BR.homeVM;
    }

    @Override
    public void onLazyLoadData() {
        binding.smartRefreshLayout.autoRefresh();
//        LogUtils.logE(TAG, "HomeFragment");
    }
}
