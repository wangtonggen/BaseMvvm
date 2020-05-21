package com.example.basemvvm.mvvm.viewModel;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.example.basemvvm.R;
import com.example.basemvvm.adapter.ProviderAdapter;
import com.example.basemvvm.bean.UserBean;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.wang.mvvmcore.base.activity.BaseActivity;
import com.wang.mvvmcore.base.baseViewModel.BaseActivityLifecycleVM;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * author: wtg
 * date:2020/5/20 0020
 * desc:
 */
public class ProviderVM extends BaseActivityLifecycleVM {
    private int page = 1;
    private int pageSize = 15;
    private BaseLoadMoreModule baseLoadMoreModule;
    public LinearLayoutManager linearLayoutManager;
    public ProviderAdapter providerAdapter;
    public OnRefreshListener onRefreshListener = refreshLayout -> {
        page = 1;
        loadData(refreshLayout);
    };
    public ProviderVM(BaseActivity mActivity) {
        super(mActivity);
        init();
    }

    @Override
    protected void init() {
        linearLayoutManager = new LinearLayoutManager(mActivity);
        providerAdapter = new ProviderAdapter();

        baseLoadMoreModule = providerAdapter.getLoadMoreModule();
        baseLoadMoreModule.setOnLoadMoreListener(() -> {
            page++;
            loadData(null);
        });
        baseLoadMoreModule.setAutoLoadMore(true);
        baseLoadMoreModule.setEnableLoadMoreIfNotFullPage(false);
        //url http://e.hiphotos.baidu.com/image/pic/item/4e4a20a4462309f7e41f5cfe760e0cf3d6cad6ee.jpg
        providerAdapter.addChildClickViewIds(R.id.btn_delete);
        providerAdapter.setOnItemClickListener((adapter, view, position) -> {
            providerAdapter.getData().remove(position);
            providerAdapter.notifyItemRemoved(position);
            providerAdapter.notifyItemRangeChanged(position, providerAdapter.getData().size() - position);
        });
        providerAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.btn_delete:
                    providerAdapter.getData().get(position).setUrl("http://e.hiphotos.baidu.com/image/pic/item/4e4a20a4462309f7e41f5cfe760e0cf3d6cad6ee.jpg");
                    providerAdapter.notifyItemChanged(position);
                    break;
            }
        });
    }

    private void loadData(RefreshLayout refreshLayout) {
        if (page == 1) {
            providerAdapter.setList(getData());
        } else {
            providerAdapter.addData(getData());
        }

        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
            refreshLayout.finishLoadMore();
        }

        baseLoadMoreModule.setEnableLoadMore(true);
        if (providerAdapter.getData().size() >= 75) {
            baseLoadMoreModule.loadMoreEnd();
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
