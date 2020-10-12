package com.wang.mvvmcore.bindAdapter;

import androidx.databinding.BindingAdapter;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.wang.mvvmcore.bindAdapter.command.BindingCommand;

/**
 * author: wtg
 * date:2020/3/16 0016
 * desc: 刷新和加载更多 adapter
 */
public class RefreshBindingAdapter {

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
     * 自动刷新
     *
     * @param smartRefreshLayout 刷新控件
     * @param autoLoadMore       是否自动刷新 true 是
     */
    @BindingAdapter(value = "autoLoadMore", requireAll = false)
    public static void autoLoadMore(SmartRefreshLayout smartRefreshLayout, boolean autoLoadMore) {
        smartRefreshLayout.setEnableAutoLoadMore(autoLoadMore);
    }

    /**
     * 刷新控件是否再刷新
     *
     * @param smartRefreshLayout 刷新控件
     * @param isRefreshing       true 正在刷新 false刷新完毕
     */
    @BindingAdapter("finishRefresh")
    public static void isRefresh(SmartRefreshLayout smartRefreshLayout, boolean isRefreshing) {
        if (isRefreshing) {
            smartRefreshLayout.finishRefresh();
        }
    }

    /**
     * 刷新控件是否再加载更多
     *
     * @param smartRefreshLayout 刷新控件
     * @param isLoadingMore      true 正在刷新 false刷新完毕
     */
    @BindingAdapter("finishLoadMore")
    public static void isLoadingMore(SmartRefreshLayout smartRefreshLayout, boolean isLoadingMore) {
        if (!isLoadingMore) {
            smartRefreshLayout.finishLoadMore();
        }
    }

    /**
     * 是否刷新
     *
     * @param smartRefreshLayout 刷新控件
     * @param enableRefresh      true 可刷新 false未刷新
     */
    @BindingAdapter(value = {"enableRefresh"}, requireAll = false)
    public static void enableRefresh(SmartRefreshLayout smartRefreshLayout, boolean enableRefresh) {
        if (smartRefreshLayout != null) {
            smartRefreshLayout.setEnableRefresh(enableRefresh);
        }
    }

    /**
     * 是否刷新
     *
     * @param smartRefreshLayout 刷新控件
     * @param enableLoadMore     true 可加载更多 false不可加载更多
     */
    @BindingAdapter(value = {"enableLoadMore"}, requireAll = false)
    public static void enableLoadMore(SmartRefreshLayout smartRefreshLayout, boolean enableLoadMore) {
        if (smartRefreshLayout != null) {
            smartRefreshLayout.setEnableRefresh(enableLoadMore);
        }
    }
}
