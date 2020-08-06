package com.example.basemvvm.mvvm.viewModel;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.example.basemvvm.R;
import com.example.basemvvm.base.binder.ImageItemBinder;
import com.example.basemvvm.base.binder.UserItemBinder;
import com.example.basemvvm.bean.HttpResponse;
import com.example.basemvvm.bean.LoginBean;
import com.example.basemvvm.bean.NotificationBean;
import com.example.basemvvm.bean.UserBean;
import com.example.basemvvm.network.base.BaseObserver;
import com.example.basemvvm.network.model.UserModel;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.wang.mvvmcore.adapter.multiAdapter.baseMultiAdapter.BaseMultiBinderAdapter;
import com.wang.mvvmcore.base.activity.BaseActivity;
import com.wang.mvvmcore.base.baseViewModel.BaseActivityLifecycleVM;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * author: wtg
 * date:2020/5/20 0020
 * desc:
 */
public class BinderVM extends BaseActivityLifecycleVM {
    private int page = 1;
    private int pageSize = 15;
    private BaseLoadMoreModule baseLoadMoreModule;
    public BaseMultiBinderAdapter baseMultiBinderAdapter;
    public LinearLayoutManager linearLayoutManager;
    public OnRefreshListener onRefreshListener = refreshLayout -> {
        page = 1;
        loadData(refreshLayout);
    };
    public BinderVM(BaseActivity mActivity) {
        super(mActivity);
        init();
    }

    @Override
    protected void init() {
        linearLayoutManager = new LinearLayoutManager(mActivity);
        baseMultiBinderAdapter = new BaseMultiBinderAdapter();
        baseMultiBinderAdapter.addItemBinder(UserBean.class,new ImageItemBinder()).addItemBinder(NotificationBean.class,new UserItemBinder());
        baseLoadMoreModule = baseMultiBinderAdapter.getLoadMoreModule();
        baseLoadMoreModule.setOnLoadMoreListener(() -> {
            page++;
            loadData(null);
        });
        baseLoadMoreModule.setAutoLoadMore(true);
        baseLoadMoreModule.setEnableLoadMoreIfNotFullPage(false);
        //url http://e.hiphotos.baidu.com/image/pic/item/4e4a20a4462309f7e41f5cfe760e0cf3d6cad6ee.jpg
        baseMultiBinderAdapter.addChildClickViewIds(R.id.btn_delete);
        baseMultiBinderAdapter.setOnItemClickListener((adapter, view, position) -> {
            baseMultiBinderAdapter.getData().remove(position);
            baseMultiBinderAdapter.notifyItemRemoved(position);
            baseMultiBinderAdapter.notifyItemRangeChanged(position, baseMultiBinderAdapter.getData().size() - position);
        });
        baseMultiBinderAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.btn_delete:
//                    baseRecyclerBinderAdapter.getData().get(position).setUrl("http://e.hiphotos.baidu.com/image/pic/item/4e4a20a4462309f7e41f5cfe760e0cf3d6cad6ee.jpg");
//                    baseRecyclerBinderAdapter.notifyItemChanged(position);
                    break;
            }
        });
    }

    private void loadData(RefreshLayout refreshLayout) {
        if (page == 1) {
            baseMultiBinderAdapter.setList(getData());
        } else {
            baseMultiBinderAdapter.addData(getData());
        }
        baseLoadMoreModule.setEnableLoadMore(true);
        if (baseMultiBinderAdapter.getData().size() >= 75) {
            baseLoadMoreModule.loadMoreEnd();
        } else {
            baseLoadMoreModule.loadMoreComplete();
        }

        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
            refreshLayout.finishLoadMore();
        }
    }

    private List<Object> getData(){
        List<Object> objects = new ArrayList<>();
        for (int i = 0; i < pageSize; i++) {
            objects.add(i%2==0?new UserBean(0,"http://e.hiphotos.baidu.com/image/pic/item/4e4a20a4462309f7e41f5cfe760e0cf3d6cad6ee.jpg"):new NotificationBean("position_"+i));
        }
        return objects;
    }
}
