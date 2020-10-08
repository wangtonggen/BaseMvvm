package com.example.basemvvm.adapter;

import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.example.basemvvm.bean.NotificationBean;
import com.example.basemvvm.databinding.RecyclerItemNotificationBinding;
import com.example.basemvvm.mvvm.viewModel.NotificationItemVM;
import com.wang.mvvmcore.adapter.singleAdapter.BaseBindingSingleAdapter;

import org.jetbrains.annotations.NotNull;

/**
 * author: wtg
 * date:2020/5/20 0020
 * desc:
 */
public class SingleAdapter extends BaseBindingSingleAdapter<NotificationBean,RecyclerItemNotificationBinding> {

    public SingleAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    public void bindData(@NotNull BaseDataBindingHolder<RecyclerItemNotificationBinding> vh, @NotNull RecyclerItemNotificationBinding viewDataBinding, NotificationBean notificationBean) {
        viewDataBinding.setNotificationItemVM(new NotificationItemVM(notificationBean));
    }

}
