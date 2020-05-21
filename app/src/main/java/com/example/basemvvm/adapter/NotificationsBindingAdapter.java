package com.example.basemvvm.adapter;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.basemvvm.bean.NotificationBean;
import com.example.basemvvm.databinding.RecyclerItemNotificationBinding;
import com.example.basemvvm.mvvm.viewModel.NotificationItemVM;
import com.wang.mvvmcore.adapter.singleAdapter.BaseBindingSingleAdapter;

import org.jetbrains.annotations.NotNull;

/**
 * author：wtg
 * time：2020/3/21
 * desc：信息提示的adapter
 */
public class NotificationsBindingAdapter extends BaseBindingSingleAdapter<RecyclerItemNotificationBinding, NotificationBean, BaseViewHolder> {

    public NotificationsBindingAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    public void bindData(@NotNull BaseViewHolder baseViewHolder, @NotNull RecyclerItemNotificationBinding viewDataBinding, NotificationBean notificationBean) {
        viewDataBinding.setNotificationItemVM(new NotificationItemVM(notificationBean));
    }
}
