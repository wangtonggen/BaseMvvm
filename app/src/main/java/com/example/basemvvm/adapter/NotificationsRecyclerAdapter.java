package com.example.basemvvm.adapter;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.basemvvm.adapter.baseaDapter.BaseRecyclerSingleAdapter;
import com.example.basemvvm.bean.NotificationBean;
import com.example.basemvvm.databinding.RecyclerItemNotificationBinding;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author：wtg
 * time：2020/3/21
 * desc：信息提示的adapter
 */
public class NotificationsRecyclerAdapter extends BaseRecyclerSingleAdapter<NotificationBean, BaseViewHolder> {
    public NotificationsRecyclerAdapter(int layoutResId, @Nullable List<NotificationBean> data) {
        super(layoutResId, data);
    }

    @Override
    public void bindData(@NotNull BaseViewHolder baseViewHolder, NotificationBean notificationBean) {
        RecyclerItemNotificationBinding notificationBinding = baseViewHolder.getBinding();
        if (notificationBinding != null){
            notificationBinding.setNotificationBean(notificationBean);
            notificationBinding.executePendingBindings();
        }
    }
}
