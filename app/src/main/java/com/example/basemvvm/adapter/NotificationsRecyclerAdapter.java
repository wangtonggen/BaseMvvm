package com.example.basemvvm.adapter;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

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
public class NotificationsRecyclerAdapter extends BaseRecyclerSingleAdapter<RecyclerItemNotificationBinding, NotificationBean, BaseViewHolder> {
    public NotificationsRecyclerAdapter(int layoutResId, @Nullable List<NotificationBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected RecyclerItemNotificationBinding getViewDataBinding(@NotNull BaseViewHolder baseViewHolder) {
        return baseViewHolder.getBinding();
    }

    @Override
    public void bindData(@NotNull BaseViewHolder baseViewHolder, @NotNull RecyclerItemNotificationBinding viewDataBinding, NotificationBean notificationBean) {
        viewDataBinding.setNotificationBean(notificationBean);
    }
}
