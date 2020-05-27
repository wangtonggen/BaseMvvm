package com.example.basemvvm.ui.activity.noMvvm;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.example.basemvvm.R;
import com.example.basemvvm.adapter.nomvvm.DelegateNoMvvmAdapter;
import com.example.basemvvm.bean.UserBean;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
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
public class DelegateNoMvvmActivity extends BaseNoMVVMActivity {
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private int page = 1;
    private BaseLoadMoreModule loadMoreModule;
    private DelegateNoMvvmAdapter delegateNoMvvmAdapter;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_delegate_nomvvm;
    }

    @Override
    protected void initView() {
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            page = 1;
            loadData();
        });

        delegateNoMvvmAdapter = new DelegateNoMvvmAdapter();
        loadMoreModule = delegateNoMvvmAdapter.getLoadMoreModule();
        loadMoreModule.setOnLoadMoreListener(()->{
            page++;
            loadData();
        });
        loadMoreModule.setAutoLoadMore(true);
        loadMoreModule.setEnableLoadMoreIfNotFullPage(false);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(delegateNoMvvmAdapter);
        smartRefreshLayout.autoRefresh();
    }

    private void loadData(){
        if (page == 1){
            delegateNoMvvmAdapter.setList(getData());
        }else {
            delegateNoMvvmAdapter.addData(getData());
        }

        if (smartRefreshLayout != null) {
            smartRefreshLayout.finishRefresh();
            smartRefreshLayout.finishLoadMore();
        }

        loadMoreModule.setEnableLoadMore(true);
        if (delegateNoMvvmAdapter.getData().size() >= 75) {
            loadMoreModule.loadMoreEnd();
        } else {
            loadMoreModule.loadMoreComplete();
        }
    }

    private List<UserBean> getData(){
        Random random = new Random();
        List<UserBean> userBeans = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            userBeans.add(new UserBean(random.nextInt(2), "http://g.hiphotos.baidu.com/image/pic/item/6d81800a19d8bc3e770bd00d868ba61ea9d345f2.jpg"));
        }
        return userBeans;
    }
}
