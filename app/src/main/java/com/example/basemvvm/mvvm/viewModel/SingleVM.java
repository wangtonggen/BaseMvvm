package com.example.basemvvm.mvvm.viewModel;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.example.basemvvm.R;
import com.example.basemvvm.adapter.SingleAdapter;
import com.example.basemvvm.bean.NotificationBean;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.wang.mvvmcore.base.activity.BaseActivity;
import com.wang.mvvmcore.base.baseViewModel.BaseActivityLifecycleVM;

import java.util.ArrayList;
import java.util.List;

/**
 * author: wtg
 * date:2020/5/20 0020
 * desc:
 */
public class SingleVM extends BaseActivityLifecycleVM {
    private int page = 1;
    private int pageSize = 15;
    private BaseLoadMoreModule baseLoadMoreModule;
    public SingleAdapter singleAdapter;
    public LinearLayoutManager linearLayoutManager;
    public OnRefreshListener onRefreshListener = refreshLayout -> {
        page = 1;
        loadData(refreshLayout);
    };
    public SingleVM(BaseActivity mActivity) {
        super(mActivity);
        init();
    }

    @Override
    protected void init() {
        linearLayoutManager = new LinearLayoutManager(mActivity);
        singleAdapter = new SingleAdapter(R.layout.recycler_item_notification);
        baseLoadMoreModule = singleAdapter.getLoadMoreModule();
        baseLoadMoreModule.setOnLoadMoreListener(()->{
            page++;
            loadData(null);
        });
        baseLoadMoreModule.setEnableLoadMore(true);
        baseLoadMoreModule.setEnableLoadMoreIfNotFullPage(true);
        singleAdapter.setOnItemClickListener((adapter, view, position) -> {
            singleAdapter.getData().get(position).name = "hello word";
            singleAdapter.notifyItemChanged(position);
        });

        singleAdapter.setOnItemLongClickListener((adapter, view, position) -> {
            singleAdapter.remove(position);
            singleAdapter.notifyItemRemoved(position);
            singleAdapter.notifyItemRangeChanged(position, singleAdapter.getData().size() - position);
            return true;
        });
    }

    private void loadData(RefreshLayout refreshLayout) {
        if (page == 1) {
            singleAdapter.setList(getData());
        } else {
            singleAdapter.addData(getData());
        }
        baseLoadMoreModule.setEnableLoadMore(true);
        if (singleAdapter.getData().size() >= 75) {
            baseLoadMoreModule.loadMoreEnd();
        } else {
            baseLoadMoreModule.loadMoreComplete();
        }

        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
            refreshLayout.finishLoadMore();
        }
    }

    private List<NotificationBean> getData(){
        List<NotificationBean> notificationBeans = new ArrayList<>();
        for (int i = 0; i < pageSize; i++) {
            notificationBeans.add(new NotificationBean("item_" + i));
        }
        return notificationBeans;
    }
}
