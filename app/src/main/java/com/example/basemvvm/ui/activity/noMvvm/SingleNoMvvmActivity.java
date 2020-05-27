package com.example.basemvvm.ui.activity.noMvvm;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.example.basemvvm.R;
import com.example.basemvvm.adapter.nomvvm.SingleNoMvvmAdapter;
import com.example.basemvvm.bean.NotificationBean;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.wang.mvvmcore.base.activity.BaseNoMVVMActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: wtg
 * date:2020/5/26 0026
 * desc:
 */
public class SingleNoMvvmActivity extends BaseNoMVVMActivity {
    private int page = 1;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private SingleNoMvvmAdapter singleNoMvvmAdapter;
    private BaseLoadMoreModule baseLoadMoreModule;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_single_nomvvm;
    }

    @Override
    protected void initView() {
        singleNoMvvmAdapter = new SingleNoMvvmAdapter(R.layout.recycler_item_notification_nomvvm);
        baseLoadMoreModule = singleNoMvvmAdapter.getLoadMoreModule();
        baseLoadMoreModule.setOnLoadMoreListener(() -> {
            page++;
            loadData();
        });

        baseLoadMoreModule.setEnableLoadMore(true);
        baseLoadMoreModule.setEnableLoadMoreIfNotFullPage(false);
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            page = 1;
            loadData();
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(singleNoMvvmAdapter);
        smartRefreshLayout.autoRefresh();
    }

    private void loadData() {
        if (page == 1) {
            singleNoMvvmAdapter.setList(getData());
        } else {
            singleNoMvvmAdapter.addData(getData());
        }
        baseLoadMoreModule.setEnableLoadMore(true);
        if (singleNoMvvmAdapter.getData().size() >= 75) {
            baseLoadMoreModule.loadMoreEnd();
        } else {
            baseLoadMoreModule.loadMoreComplete();
        }

        if (smartRefreshLayout != null) {
            smartRefreshLayout.finishRefresh();
            smartRefreshLayout.finishLoadMore();
        }
    }

    private List<NotificationBean> getData() {
        List<NotificationBean> notificationBeans = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            notificationBeans.add(new NotificationBean("item_" + i));
        }
        return notificationBeans;
    }
}
