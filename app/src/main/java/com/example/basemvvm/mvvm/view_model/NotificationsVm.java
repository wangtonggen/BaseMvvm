package com.example.basemvvm.mvvm.view_model;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.basemvvm.R;
import com.example.basemvvm.adapter.NotificationsRecyclerAdapter;
import com.example.basemvvm.base.fragment.BaseMVVMFragment;
import com.example.basemvvm.bean.NotificationBean;
import com.example.basemvvm.mvvm.view_model_base.BaseFragmentVM;
import com.example.basemvvm.utils.common_utils.ToastUtils;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * author: wtg
 * date:2020/3/18 0018
 * desc:
 */
public class NotificationsVm extends BaseFragmentVM {
    public NotificationsRecyclerAdapter notificationsRecyclerAdapter;
    private int page = 1;
    private int pageSize = 15;
    private List<NotificationBean> notificationBeans = new ArrayList<>();
    public LinearLayoutManager linearLayoutManager;
    public OnRefreshListener onRefreshListener = refreshLayout -> {
        page = 1;
        loadData(refreshLayout);
    };

    public OnLoadMoreListener onLoadMoreListener = refreshLayout -> {
        page++;
        loadData(refreshLayout);
    };

    public NotificationsVm(BaseMVVMFragment fragment) {
        super(fragment);
        init();
    }

    @Override
    protected void init() {
        linearLayoutManager = new LinearLayoutManager(mContext);
        notificationsRecyclerAdapter = new NotificationsRecyclerAdapter(R.layout.recycler_item_notification, notificationBeans);

        notificationsRecyclerAdapter.setOnItemClickListener((adapter, view, position) -> {
            notificationBeans.get(position).name = "hello word";
            notificationsRecyclerAdapter.notifyItemChanged(position);
//            ToastUtils.showLongToast("position_" + position);
        });

        notificationsRecyclerAdapter.setOnItemLongClickListener((adapter, view, position) -> {
            notificationBeans.remove(position);
            notificationsRecyclerAdapter.notifyItemRemoved(position);
            notificationsRecyclerAdapter.notifyItemRangeChanged(position, notificationBeans.size() - position);
            return true;
        });
    }

    private void loadData(RefreshLayout refreshLayout) {
        if (page == 1) {
            notificationBeans.clear();
            for (int i = 0; i < pageSize; i++) {
                notificationBeans.add(new NotificationBean("item_" + i));
                notificationsRecyclerAdapter.notifyDataSetChanged();
            }
        } else {
            int size = notificationBeans.size();
            for (int i = 0; i < pageSize; i++) {
                notificationBeans.add(new NotificationBean("item_" + (size + i)));
                notificationsRecyclerAdapter.notifyItemRangeInserted(notificationBeans.size(), 15);
            }
        }

        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
            refreshLayout.finishLoadMore();
        }
    }

}
