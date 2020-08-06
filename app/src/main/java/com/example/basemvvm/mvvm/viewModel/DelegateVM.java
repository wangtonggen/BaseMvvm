package com.example.basemvvm.mvvm.viewModel;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.example.basemvvm.adapter.DelegateAdapter;
import com.example.basemvvm.bean.UserBean;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.wang.mvvmcore.base.activity.BaseActivity;
import com.wang.mvvmcore.base.baseViewModel.BaseActivityLifecycleVM;
import com.wang.mvvmcore.rxBus.MsgEvent;
import com.wang.mvvmcore.rxBus.RxBus;
import com.wang.mvvmcore.utils.common.LogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * author: wtg
 * date:2020/5/20 0020
 * desc:
 */
public class DelegateVM extends BaseActivityLifecycleVM {
    private int page = 1;
    private int pageSize = 15;
    private BaseLoadMoreModule baseLoadMoreModule;
    public LinearLayoutManager linearLayoutManager;
    public DelegateAdapter delegateAdapter;
    public OnRefreshListener onRefreshListener = refreshLayout -> {
        page = 1;
        loadData(refreshLayout);
    };

    public DelegateVM(BaseActivity mActivity) {
        super(mActivity);
        init();
    }

    @Override
    protected void init() {
        linearLayoutManager = new LinearLayoutManager(mActivity);
        delegateAdapter = new DelegateAdapter();
        baseLoadMoreModule = delegateAdapter.getLoadMoreModule();
        baseLoadMoreModule.setOnLoadMoreListener(() -> {
            page++;
            loadData(null);
        });
        baseLoadMoreModule.setAutoLoadMore(true);
        baseLoadMoreModule.setEnableLoadMoreIfNotFullPage(false);

        delegateAdapter.setOnItemClickListener((adapter, view, position) -> {
            LogUtils.logE("delegateAdapter");
            RxBus.getInstance().post(new MsgEvent(1,"hello"));
            ToastUtils.showShort("position_" + position);
        });
    }

    private void loadData(RefreshLayout refreshLayout) {
        if (page == 1) {
            delegateAdapter.setList(getData());
        } else {
            delegateAdapter.addData(getData());
        }

        if (refreshLayout != null && refreshLayout.isRefreshing()) {
            refreshLayout.finishRefresh();
        }

        baseLoadMoreModule.setEnableLoadMore(true);
        if (delegateAdapter.getData().size() >= 75) {
            baseLoadMoreModule.setEnableLoadMore(false);
//            baseLoadMoreModule.loadMoreEnd();
        } else {
            baseLoadMoreModule.loadMoreComplete();
        }
    }

    private List<UserBean> getData() {
        Random random = new Random();
        List<UserBean> userBeans = new ArrayList<>();
        for (int i = 0; i < pageSize; i++) {
            userBeans.add(new UserBean(random.nextInt(2), "http://g.hiphotos.baidu.com/image/pic/item/6d81800a19d8bc3e770bd00d868ba61ea9d345f2.jpg"));
        }
        return userBeans;
    }
}
