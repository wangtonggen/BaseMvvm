package com.example.basemvvm.ui.fragment;

import com.example.basemvvm.BR;
import com.example.basemvvm.R;
import com.example.basemvvm.base.baseViewModel.BaseLifecycleVM;
import com.example.basemvvm.base.fragment.LazyLoadFragment;
import com.example.basemvvm.databinding.FragmentUserBinding;
import com.example.basemvvm.mvvm.viewModel.UserVM;

/**
 * author: wtg
 * date:2020/4/30 0030
 * desc:
 */
public class UserFragment extends LazyLoadFragment<FragmentUserBinding, UserVM> {

    @Override
    public void onLazyLoadData() {
        binding.smartRefreshLayout.autoRefresh();
    }

    @Override
    protected UserVM getViewModel() {
        return new UserVM(this);
    }

    @Override
    protected int getViewModelId() {
        return BR.userVM;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_user;
    }
}
