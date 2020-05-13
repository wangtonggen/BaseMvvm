package com.example.basemvvm.widget.common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.loadmore.BaseLoadMoreView;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.basemvvm.R;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * author: wtg
 * date:2020/5/13 0013
 * desc: 加载更多的view
 */
public class CustomLoadMoreView extends BaseLoadMoreView {
    @NotNull
    @Override
    public View getLoadComplete(@NotNull BaseViewHolder baseViewHolder) {
        return Objects.requireNonNull(baseViewHolder.findView(R.id.tv_load_complete));
    }

    @NotNull
    @Override
    public View getLoadEndView(@NotNull BaseViewHolder baseViewHolder) {
        return Objects.requireNonNull(baseViewHolder.findView(R.id.tv_load_end));
    }

    @NotNull
    @Override
    public View getLoadFailView(@NotNull BaseViewHolder baseViewHolder) {
        return Objects.requireNonNull(baseViewHolder.findView(R.id.tv_load_fail));
    }

    @NotNull
    @Override
    public View getLoadingView(@NotNull BaseViewHolder baseViewHolder) {
        return Objects.requireNonNull(baseViewHolder.findView(R.id.cl_loading));
    }

    @NotNull
    @Override
    public View getRootView(@NotNull ViewGroup viewGroup) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_custom_load_more, viewGroup, false);
    }
}
