package com.example.basemvvm.ui.activity.noMvvm;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.example.basemvvm.R;
import com.example.basemvvm.base.binder.ImageNoMvvmBinder;
import com.example.basemvvm.base.binder.MultiItemBeanNoMvvmBinder;
import com.example.basemvvm.bean.MultiItemBean;
import com.example.basemvvm.bean.UserBean;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.wang.mvvmcore.adapter.multiAdapter.baseMultiAdapter.BaseMultiBinderAdapter;
import com.wang.mvvmcore.base.activity.BaseNoMVVMActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: wtg
 * date:2020/5/26 0026
 * desc:
 */
public class BinderNoMvvmActivity extends BaseNoMVVMActivity {
    private int page = 1;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private BaseMultiBinderAdapter baseBinderAdapter;
    private BaseLoadMoreModule baseLoadMoreModule;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_binder_momvvm;
    }

    @Override
    protected void initView() {
        baseBinderAdapter = new BaseMultiBinderAdapter();
        baseBinderAdapter.addItemBinder(UserBean.class,new ImageNoMvvmBinder()).addItemBinder(MultiItemBean.class,new MultiItemBeanNoMvvmBinder());
        baseLoadMoreModule = baseBinderAdapter.getLoadMoreModule();
        baseLoadMoreModule.setOnLoadMoreListener(()->{
            page++;
            loadData();
        });

        baseLoadMoreModule.setEnableLoadMore(true);
        baseLoadMoreModule.setEnableLoadMoreIfNotFullPage(false);

        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            page=1;
            loadData();
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(baseBinderAdapter);

        smartRefreshLayout.autoRefresh();
    }

    private void loadData() {
        if (page == 1) {
            baseBinderAdapter.setList(getData());
        } else {
            baseBinderAdapter.addData(getData());
        }
        baseLoadMoreModule.setEnableLoadMore(true);
        if (baseBinderAdapter.getData().size() >= 75) {
            baseLoadMoreModule.loadMoreEnd();
        } else {
            baseLoadMoreModule.loadMoreComplete();
        }

        if (smartRefreshLayout != null) {
            smartRefreshLayout.finishRefresh();
            smartRefreshLayout.finishLoadMore();
        }
    }

    private List<Object> getData(){
        List<Object> objects = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            objects.add(i%2==0?new UserBean(0,"http://e.hiphotos.baidu.com/image/pic/item/4e4a20a4462309f7e41f5cfe760e0cf3d6cad6ee.jpg"):new MultiItemBean(0,"position_"+i,"a you ok?"));
        }
        return objects;
    }
}
