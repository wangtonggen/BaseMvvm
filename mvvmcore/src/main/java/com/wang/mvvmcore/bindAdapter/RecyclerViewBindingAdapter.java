package com.wang.mvvmcore.bindAdapter;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

/**
 * author: wtg
 * date:2020/3/16 0016
 * desc: 列表的数据绑定adapter
 */
public class RecyclerViewBindingAdapter {

    /**
     * 设置列表的 布局管理器和缓存大小和item宽高是否是固定值
     *
     * @param recyclerView           recyclerView
     * @param layout_item_cache_size 缓存的item数量
     * @param layout_has_fixed_size  item是否是固定宽高
     * @param adapter                适配器
     */
    @BindingAdapter(value = {"layout_item_cache_size", "layout_has_fixed_size", "recycler_adapter"}, requireAll = false)
    public static void layoutManager(RecyclerView recyclerView, int layout_item_cache_size, boolean layout_has_fixed_size, RecyclerView.Adapter adapter) {
        if (adapter != null) {
            recyclerView.setAdapter(adapter);
        }
        if (layout_item_cache_size > 0) {
            recyclerView.setItemViewCacheSize(layout_item_cache_size);
        }
    }
}
