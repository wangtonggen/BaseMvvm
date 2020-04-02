package com.example.basemvvm.utils.vmUtils;

import androidx.databinding.BindingAdapter;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

/**
 * author: wtg
 * date:2020/3/21 0021
 * desc: 下拉刷新，上拉加载的adapter
 */
public class SmartRefreshBindingAdapter {

    /**
     * 设置 下拉刷新，上拉加载的监听
     *
     * @param smartRefreshLayout 控件
     * @param onRefreshListener  刷新监听
     * @param onLoadMoreListener 加载更多监听
     */
    @BindingAdapter(value = {"onRefreshListener", "onLoadMoreListener"}, requireAll = false)
    public static void setRefreshAndLoadMoreListener(SmartRefreshLayout smartRefreshLayout, OnRefreshListener onRefreshListener, OnLoadMoreListener onLoadMoreListener) {
        smartRefreshLayout.setOnRefreshListener(onRefreshListener);
        smartRefreshLayout.setOnLoadMoreListener(onLoadMoreListener);
    }
}
