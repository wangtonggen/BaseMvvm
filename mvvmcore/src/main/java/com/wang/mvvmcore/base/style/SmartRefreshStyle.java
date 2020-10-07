package com.wang.mvvmcore.base.style;

import androidx.databinding.ObservableBoolean;

/**
 * author: wtg
 * time: 2020/10/7
 * desc:刷新控件的状态值
 */
public class SmartRefreshStyle {
    //是否自动刷新 默认true 自动刷新
    public ObservableBoolean autoRefresh = new ObservableBoolean(true);
    //是否自动加载数据 默认false 禁止自动加载更多
    public ObservableBoolean autoLoadMore = new ObservableBoolean(false);
    //禁用下拉刷新 默认false 不禁用
    public ObservableBoolean enableRefresh = new ObservableBoolean(false);
    //禁用上拉加载 默认true 禁用
    public ObservableBoolean enableLoadMore = new ObservableBoolean(true);
    //完成下拉刷新 默认false
    public ObservableBoolean finishRefresh = new ObservableBoolean(false);
    //完成加载更多 默认false
    public ObservableBoolean finishLoadMore = new ObservableBoolean(false);
}
