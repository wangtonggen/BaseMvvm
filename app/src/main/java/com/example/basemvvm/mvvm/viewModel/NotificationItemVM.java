package com.example.basemvvm.mvvm.viewModel;

import androidx.databinding.ObservableField;

import com.example.basemvvm.bean.NotificationBean;
import com.wang.mvvmcore.base.baseViewModel.BaseVM;

/**
 * author：wtg
 * time：2020/5/1
 * desc：
 */
public class NotificationItemVM extends BaseVM {
    public ObservableField<String> name = new ObservableField<>();

    public NotificationItemVM(NotificationBean notificationBean) {
        name.set(notificationBean.name);
    }
}
