package com.example.basemvvm.ui.activity.noMvvm;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.example.basemvvm.R;
import com.example.basemvvm.adapter.nomvvm.MultiNoMvvmAdapter;
import com.example.basemvvm.bean.MultiItemBean;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.wang.mvvmcore.base.activity.BaseNoMVVMActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: wtg
 * date:2020/5/26 0026
 * desc:multi 不使用mvvm模式
 */
public class MultiNoMvvmActivity extends BaseNoMVVMActivity {
    private int page = 1;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private MultiNoMvvmAdapter multiNoMvvmAdapter;
    private BaseLoadMoreModule baseLoadMoreModule;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_multi_nomvvm;
    }

    @Override
    protected void initView() {
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
           page=1;
           loadData();
        });
        multiNoMvvmAdapter = new MultiNoMvvmAdapter();
        baseLoadMoreModule=multiNoMvvmAdapter.getLoadMoreModule();
        baseLoadMoreModule.setOnLoadMoreListener(()->{
            page++;
            loadData();
        });
        baseLoadMoreModule.setAutoLoadMore(true);
        baseLoadMoreModule.setEnableLoadMoreIfNotFullPage(false);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(multiNoMvvmAdapter);
        smartRefreshLayout.autoRefresh();
    }

    private void loadData(){
        if (page == 1){
            multiNoMvvmAdapter.setList(getData());
        }else {
            multiNoMvvmAdapter.addData(getData());
        }

        if (smartRefreshLayout != null){
            smartRefreshLayout.finishRefresh();
        }

        baseLoadMoreModule.setEnableLoadMore(true);
        if (multiNoMvvmAdapter.getData().size() >= 45){
            baseLoadMoreModule.loadMoreEnd();
        }else {
            baseLoadMoreModule.loadMoreComplete();
        }
    }

    private List<MultiItemBean> getData(){
        List<MultiItemBean> multiItemBeanList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            multiItemBeanList.add(new MultiItemBean(i % 2 == 0 ? 0 : 1, "item_" + i, "a you ok?"));
        }

        return multiItemBeanList;
    }
}
