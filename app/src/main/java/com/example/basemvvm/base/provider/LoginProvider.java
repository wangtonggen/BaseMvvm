package com.example.basemvvm.base.provider;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.basemvvm.base.entity.BaseMultiEntity;

import org.jetbrains.annotations.NotNull;

/**
 * author: wtg
 * date:2020/4/27 0027
 * desc:
 */
public class LoginProvider extends BaseProvider<BaseMultiEntity> {

    @Override
    void bindData(@NotNull BaseViewHolder viewHolder, BaseMultiEntity baseMultiEntity) {

    }

    @Override
    public int getItemViewType() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return 0;
    }
}
