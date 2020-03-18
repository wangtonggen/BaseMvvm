package com.example.basemvvm.ui.fragment;

import com.example.basemvvm.BR;
import com.example.basemvvm.R;
import com.example.basemvvm.base.BaseMvvmFragment;
import com.example.basemvvm.databinding.FragmentNotificationsBinding;
import com.example.basemvvm.mvvm.view_model.NotificationsVm;


public class NotificationsFragment extends BaseMvvmFragment<FragmentNotificationsBinding, NotificationsVm> {
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_notifications;
    }

    @Override
    protected NotificationsVm getViewModel() {
        return new NotificationsVm(this);
    }

    @Override
    protected int getViewModelId() {
        return BR.notificationsVm;
    }
}
