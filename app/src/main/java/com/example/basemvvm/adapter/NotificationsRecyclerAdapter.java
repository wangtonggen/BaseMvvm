package com.example.basemvvm.adapter;

import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.basemvvm.bean.NotificationBean;
import com.example.basemvvm.databinding.RecyclerItemNotificationBinding;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author：wtg
 * time：2020/3/21
 * desc：
 */
public class NotificationsRecyclerAdapter extends BaseQuickAdapter<NotificationBean, BaseViewHolder> {
    public NotificationsRecyclerAdapter(int layoutResId, @Nullable List<NotificationBean> data) {
        super(layoutResId, data);
    }

    @NotNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
//        LogUtils.logE("onCreateViewHolder",parent+"---");
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NotNull BaseViewHolder holder, int position, @NotNull List<Object> payloads) {
//        LogUtils.logE("onBindViewHolder",holder.itemView+"---");
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    protected void onItemViewHolderCreated(@NotNull BaseViewHolder viewHolder, int viewType) {
        DataBindingUtil.bind(viewHolder.itemView);//绑定数据
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, NotificationBean notificationBean) {
        if (notificationBean == null){
            return;
        }
        RecyclerItemNotificationBinding notificationBinding = baseViewHolder.getBinding();
        if (notificationBinding != null){
            notificationBinding.setNotificationBean(notificationBean);
        }
    }
}
