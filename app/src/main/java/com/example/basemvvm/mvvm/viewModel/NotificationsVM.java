package com.example.basemvvm.mvvm.viewModel;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.example.basemvvm.R;
import com.example.basemvvm.adapter.NotificationsBindingAdapter;
import com.example.basemvvm.bean.NotificationBean;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.wang.mvvmcore.base.baseViewModel.BaseFragmentLifecycleVM;
import com.wang.mvvmcore.base.fragment.BaseMVVMFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * author: wtg
 * date:2020/3/18 0018
 * desc:
 */
public class NotificationsVM extends BaseFragmentLifecycleVM {
    public NotificationsBindingAdapter notificationsRecyclerAdapter;
    private int page = 1;
    private int pageSize = 15;
    private BaseLoadMoreModule baseLoadMoreModule;
    public LinearLayoutManager linearLayoutManager;
    public OnRefreshListener onRefreshListener = refreshLayout -> {
        page = 1;
        loadData(refreshLayout);
    };

    public OnLoadMoreListener onLoadMoreListener = refreshLayout -> {
        page++;
        loadData(refreshLayout);
    };

    public NotificationsVM(BaseMVVMFragment fragment) {
        super(fragment);
        init();
    }

    @Override
    protected void init() {
        linearLayoutManager = new LinearLayoutManager(mContext);
        notificationsRecyclerAdapter = new NotificationsBindingAdapter(R.layout.recycler_item_notification);
        baseLoadMoreModule = notificationsRecyclerAdapter.getLoadMoreModule();
        baseLoadMoreModule.setOnLoadMoreListener(()->{
            page++;
            loadData(null);
        });
        baseLoadMoreModule.setAutoLoadMore(true);
        baseLoadMoreModule.setEnableLoadMoreIfNotFullPage(true);
        notificationsRecyclerAdapter.setOnItemClickListener((adapter, view, position) -> {
            notificationsRecyclerAdapter.getData().get(position).name = "hello word";
            notificationsRecyclerAdapter.notifyItemChanged(position);
        });

        notificationsRecyclerAdapter.setOnItemLongClickListener((adapter, view, position) -> {
            notificationsRecyclerAdapter.getData().remove(position);
            notificationsRecyclerAdapter.notifyItemRemoved(position);
            notificationsRecyclerAdapter.notifyItemRangeChanged(position, notificationsRecyclerAdapter.getData().size() - position);
            return true;
        });
    }

    private void loadData(RefreshLayout refreshLayout) {
        if (page == 1) {
            notificationsRecyclerAdapter.setList(getData());
        } else {
            notificationsRecyclerAdapter.addData(getData());
        }
        baseLoadMoreModule.setEnableLoadMore(true);
        if (notificationsRecyclerAdapter.getData().size() >= 75) {
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
