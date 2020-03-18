package com.example.basemvvm.ui.fragment;

import com.example.basemvvm.BR;
import com.example.basemvvm.R;
import com.example.basemvvm.base.BaseMvvmFragment;
import com.example.basemvvm.databinding.FragmentHomeBinding;
import com.example.basemvvm.mvvm.view_model.HomeVM;


public class HomeFragment extends BaseMvvmFragment<FragmentHomeBinding, HomeVM> {

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
}
