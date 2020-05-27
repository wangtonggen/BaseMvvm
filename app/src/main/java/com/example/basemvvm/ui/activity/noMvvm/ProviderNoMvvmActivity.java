package com.example.basemvvm.ui.activity.noMvvm;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.example.basemvvm.R;
import com.example.basemvvm.adapter.nomvvm.ProviderNoMvvmAdapter;
import com.example.basemvvm.bean.UserBean;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.wang.mvvmcore.base.activity.BaseNoMVVMActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

/**
 * author: wtg
 * date:2020/5/26 0026
 * desc:
 */
public class ProviderNoMvvmActivity extends BaseNoMVVMActivity {
    private int page = 1;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private ProviderNoMvvmAdapter providerNoMvvmAdapter;
    private BaseLoadMoreModule baseLoadMoreModule;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_provider_nomvvm;
    }

    @Override
    protected void initView() {
        providerNoMvvmAdapter = new ProviderNoMvvmAdapter();
        baseLoadMoreModule = providerNoMvvmAdapter.getLoadMoreModule();
        baseLoadMoreModule.setOnLoadMoreListener(()->{
            page++;
            loadData();
        });
        baseLoadMoreModule.setEnableLoadMore(true);
        baseLoadMoreModule.setEnableLoadMoreIfNotFullPage(false);

        smartRefreshLayout.setOnRefreshListener(refreshLayout->{
            page=1;
            loadData();
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(providerNoMvvmAdapter);

        smartRefreshLayout.autoRefresh();
    }

    private void loadData() {
        if (page == 1) {
            providerNoMvvmAdapter.setList(getData());
        } else {
            providerNoMvvmAdapter.addData(getData());
        }

        if (smartRefreshLayout != null) {
            smartRefreshLayout.finishRefresh();
            smartRefreshLayout.finishLoadMore();
        }

        baseLoadMoreModule.setEnableLoadMore(true);
        if (providerNoMvvmAdapter.getData().size() >= 75) {
            baseLoadMoreModule.loadMoreEnd();
        } else {
            baseLoadMoreModule.loadMoreComplete();
        }
    }

    private List<UserBean> getData() {
        Random random = new Random();
        List<UserBean> userBeans = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            userBeans.add(new UserBean(random.nextInt(2), "http://g.hiphotos.baidu.com/image/pic/item/6d81800a19d8bc3e770bd00d868ba61ea9d345f2.jpg"));
        }
        return userBeans;
    }
}
