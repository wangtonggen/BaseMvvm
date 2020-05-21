package com.example.basemvvm.ui.fragment;

import com.example.basemvvm.BR;
import com.example.basemvvm.R;
import com.example.basemvvm.databinding.FragmentNotificationsBinding;
import com.example.basemvvm.mvvm.viewModel.NotificationsVM;
import com.wang.mvvmcore.base.fragment.LazyLoadFragment;


public class NotificationsFragment extends LazyLoadFragment<FragmentNotificationsBinding, NotificationsVM> {
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_notifications;
    }

    @Override
    protected NotificationsVM getViewModel() {
        return new NotificationsVM(this);
    }

    @Override
    protected int getViewModelId() {
        return BR.notificationsVm;
    }

    @Override
    public void onLazyLoadData() {
//        LogUtils.logE(TAG, "NotificationsFragment");
        binding.smartRefreshLayout.autoRefresh();
    }
}
