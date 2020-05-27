package com.example.basemvvm.mvvm.viewModel;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.example.basemvvm.adapter.MultiAdapter;
import com.example.basemvvm.bean.MultiItemBean;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.wang.mvvmcore.base.activity.BaseActivity;
import com.wang.mvvmcore.base.baseViewModel.BaseActivityLifecycleVM;
import com.wang.mvvmcore.utils.common.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * author: wtg
 * date:2020/5/20 0020
 * desc:
 */
public class MultiVM extends BaseActivityLifecycleVM {
    private int page = 1;
    private int pageSize = 15;
    private BaseLoadMoreModule baseLoadMoreModule;
    public LinearLayoutManager linearLayoutManager;
    public MultiAdapter multiAdapter;
    public OnRefreshListener onRefreshListener = refreshLayout -> {
        page = 1;
        loadData(refreshLayout);
    };
    public MultiVM(BaseActivity mActivity) {
        super(mActivity);
        init();
    }

    @Override
    protected void init() {
        linearLayoutManager = new LinearLayoutManager(mActivity);
        multiAdapter = new MultiAdapter();

        multiAdapter.setOnItemClickListener((adapter, view, position) -> {
            multiAdapter.getData().get(position).name = "hello";
            multiAdapter.notifyItemChanged(position + multiAdapter.getHeaderLayoutCount());
            ToastUtils.showShortToast("position_" + position);
        });
        baseLoadMoreModule = multiAdapter.getLoadMoreModule();
        baseLoadMoreModule.setOnLoadMoreListener(() -> {
            page++;
            loadData(null);
        });
        baseLoadMoreModule.setAutoLoadMore(true);
        baseLoadMoreModule.setEnableLoadMoreIfNotFullPage(false);
    }

    private void loadData(RefreshLayout refreshLayout) {
        if (page == 1) {
            multiAdapter.setList(getData());
        } else {
            multiAdapter.addData(getData());
        }

        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
            refreshLayout.finishLoadMore();
        }

        baseLoadMoreModule.setEnableLoadMore(true);
        if (multiAdapter.getData().size() >= 75) {
            baseLoadMoreModule.loadMoreEnd();
        } else {
            baseLoadMoreModule.loadMoreComplete();
        }
    }

    private List<MultiItemBean> getData() {
        List<MultiItemBean> multiItemBeanList = new ArrayList<>();
        for (int i = 0; i < pageSize; i++) {
            multiItemBeanList.add(new MultiItemBean(i % 2 == 0 ? 0 : 1, "item_" + i, "a you ok?"));
        }

        return multiItemBeanList;
    }
}
