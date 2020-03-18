package com.example.basemvvm.ui.fragment;

import com.example.basemvvm.BR;
import com.example.basemvvm.R;
import com.example.basemvvm.base.LazyLoadFragment;
import com.example.basemvvm.databinding.FragmentHomeBinding;
import com.example.basemvvm.mvvm.view_model.HomeVM;
import com.example.basemvvm.utils.common_utils.LogUtils;


public class HomeFragment extends LazyLoadFragment<FragmentHomeBinding, HomeVM> {

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
        LogUtils.logE(TAG,"HomeFragment");
    }
}
