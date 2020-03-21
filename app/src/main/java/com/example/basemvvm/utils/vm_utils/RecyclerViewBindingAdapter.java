package com.example.basemvvm.utils.vm_utils;

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
     * @param layoutManager          布局管理器
     * @param layout_item_cache_size 缓存的item数量
     * @param layout_has_fixed_size  item是否是固定宽高
     */
    @BindingAdapter(value = {"layout_manager", "layout_item_cache_size", "layout_has_fixed_size"}, requireAll = false)
    public static void layoutManager(RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager, int layout_item_cache_size, boolean layout_has_fixed_size) {
        if (layoutManager != null) {
            recyclerView.setLayoutManager(layoutManager);
        }
        if (layout_item_cache_size > 0) {
            recyclerView.setItemViewCacheSize(layout_item_cache_size);
        }
        recyclerView.setHasFixedSize(layout_has_fixed_size);
    }

    /**
     * 设置adapter
     *
     * @param recyclerView recycler
     * @param adapter      adapter
     */
    @BindingAdapter("recycler_adapter")
    public static void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

//    /**
//     * item的点击事件
//     * @param recyclerView recyclerView
//     * @param onItemClickListener 点击事件监听
//     */
//    @BindingAdapter("item_click_listener")
//    public static void onItemClickListener(RecyclerView recyclerView, OnItemClickListener onItemClickListener){
//        ((BaseQuickAdapter) Objects.requireNonNull(recyclerView.getAdapter())).setOnItemClickListener(onItemClickListener);
//    }
//
//    /**
//     * item的长按事件
//     * @param recyclerView recycler
//     * @param onItemLongClickListener 长按监听
//     */
//    @BindingAdapter(value = {"item_long_click_listener","adapter"})
//    private static void onItemLongClickListener(RecyclerView recyclerView,BaseQuickAdapter adapter, OnItemLongClickListener onItemLongClickListener){
//       adapter.setOnItemLongClickListener(onItemLongClickListener);
//    }
//
//    /**
//     * item的child的点击事件
//     * @param recyclerView recycler
//     * @param onItemChildClickListener 点击事件监听
//     */
//    @BindingAdapter("item_child_click_listener")
//    public static void onItemChildClickListener(RecyclerView recyclerView, OnItemChildClickListener onItemChildClickListener){
//        ((BaseQuickAdapter) Objects.requireNonNull(recyclerView.getAdapter())).setOnItemChildClickListener(onItemChildClickListener);
//    }

//    /**
//     * item的child长按事件
//     * @param recyclerView recycler
//     * @param onItemChildLongClickListener 长按监听
//     */
//    @BindingAdapter("item_child_long_click_listener")
//    private static void onItemChildLongClickListener(RecyclerView recyclerView, OnItemChildLongClickListener onItemChildLongClickListener){
//        ((BaseQuickAdapter) Objects.requireNonNull(recyclerView.getAdapter())).setOnItemChildLongClickListener(onItemChildLongClickListener);
//    }
}
