package com.example.basemvvm.adapter.nomvvm;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.basemvvm.R;
import com.example.basemvvm.bean.NotificationBean;
import com.wang.mvvmcore.adapter.singleAdapter.BaseSingleAdapter;

import org.jetbrains.annotations.NotNull;

/**
 * author: wtg
 * date:2020/5/27 0027
 * desc:单布局不使用MVVM
 */
public class SingleNoMvvmAdapter extends BaseSingleAdapter<NotificationBean, BaseViewHolder> {
    public SingleNoMvvmAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, NotificationBean notificationBean) {
        baseViewHolder.setText(R.id.tv_name,notificationBean.name);
    }
}
