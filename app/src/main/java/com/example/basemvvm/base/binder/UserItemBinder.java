package com.example.basemvvm.base.binder;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.basemvvm.bean.NotificationBean;
import com.example.basemvvm.databinding.RecyclerItemNotificationBinding;
import com.example.basemvvm.mvvm.viewModel.NotificationItemVM;
import com.wang.mvvmcore.adapter.binder.BaseViewDataBinder;

import org.jetbrains.annotations.NotNull;

/**
 * author: wtg
 * date:2020/5/20 0020
 * desc:
 */
public class UserItemBinder extends BaseViewDataBinder<NotificationBean, RecyclerItemNotificationBinding> {

    @Override
    public void setData(RecyclerItemNotificationBinding viewDataBinding, NotificationBean notificationBean) {
        viewDataBinding.setNotificationItemVM(new NotificationItemVM(notificationBean));
    }

    @NotNull
    @Override
    public RecyclerItemNotificationBinding onCreateViewBinding(@NotNull LayoutInflater layoutInflater, @NotNull ViewGroup viewGroup, int i) {
        return RecyclerItemNotificationBinding.inflate(layoutInflater,viewGroup,false);
    }
}
