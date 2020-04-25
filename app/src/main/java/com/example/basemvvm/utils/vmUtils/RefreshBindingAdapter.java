package com.example.basemvvm.utils.vmUtils;

import androidx.databinding.BindingAdapter;

import com.example.basemvvm.utils.vmUtils.refresh.BindingCommand;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

/**
 * author: wtg
 * date:2020/3/16 0016
 * desc: 刷新和加载更多 adapter
 */
public class RefreshBindingAdapter {

    /**
     * 下拉刷新和上拉加载
     *
     * @param smartRefreshLayout 刷新控件
     * @param onRefreshCommand   刷新回调实例
     * @param onLoadMoreCommand  加载更多回调实例
     */
    @BindingAdapter(value = {"onRefreshCommand", "onLoadMoreCommand"}, requireAll = false)
    public static void refreshAndLoadMore(SmartRefreshLayout smartRefreshLayout, BindingCommand onRefreshCommand, BindingCommand onLoadMoreCommand) {
        if (onRefreshCommand != null) {
            smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
                onRefreshCommand.execute();
            });
        }

        if (onLoadMoreCommand != null) {
            smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
                onLoadMoreCommand.execute();
            });
        }
    }

    /**
     * 自动刷新
     *
     * @param smartRefreshLayout 刷新控件
     * @param autoRefresh        是否自动刷新 true 是
     */
    @BindingAdapter(value = "autoRefresh", requireAll = false)
    public static void autoRefresh(SmartRefreshLayout smartRefreshLayout, boolean autoRefresh) {
        if (autoRefresh) {
            smartRefreshLayout.autoRefresh();
        }
    }

    /**
     * 刷新控件是否再刷新
     *
     * @param smartRefreshLayout 刷新控件
     * @param isRefresh          true 正在刷新 false刷新完毕
     */
    @BindingAdapter("isRefreshing")
    public static void isRefresh(SmartRefreshLayout smartRefreshLayout, boolean isRefresh) {
        if (!isRefresh) {
            smartRefreshLayout.finishRefresh();
        }
    }

    /**
     * 刷新控件是否再加载更多
     *
     * @param smartRefreshLayout 刷新控件
     * @param isLoadingMore      true 正在刷新 false刷新完毕
     */
    @BindingAdapter("isLoadingMore")
    public static void isLoadingMore(SmartRefreshLayout smartRefreshLayout, boolean isLoadingMore) {
        if (!isLoadingMore) {
            smartRefreshLayout.finishRefresh();
        }
    }

}
