package com.example.basemvvm.mvvm.viewModel;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.example.basemvvm.R;
import com.example.basemvvm.adapter.UserBindingAdapter;
import com.example.basemvvm.bean.UserBean;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.wang.mvvmcore.base.baseViewModel.BaseFragmentLifecycleVM;
import com.wang.mvvmcore.base.fragment.BaseMVVMFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * author: wtg
 * date:2020/3/14 0014
 * desc: 我的viewModel
 */
public class UserVM extends BaseFragmentLifecycleVM {
    private int page = 1;
    private int pageSize = 15;
    public UserBindingAdapter userRecyclerAdapter;
    public LinearLayoutManager linearLayoutManager;
    private BaseLoadMoreModule baseLoadMoreModule;
    public OnRefreshListener onRefreshListener = refreshLayout -> {
        page = 1;
        loadData(refreshLayout);
    };

    public UserVM(BaseMVVMFragment fragment) {
        super(fragment);
        init();
    }

    @Override
    protected void init() {
        linearLayoutManager = new LinearLayoutManager(mContext);
        userRecyclerAdapter = new UserBindingAdapter();

        baseLoadMoreModule = userRecyclerAdapter.getLoadMoreModule();
        baseLoadMoreModule.setOnLoadMoreListener(() -> {
            page++;
            loadData(null);
        });
        baseLoadMoreModule.setAutoLoadMore(true);
        baseLoadMoreModule.setEnableLoadMoreIfNotFullPage(false);
        //url http://e.hiphotos.baidu.com/image/pic/item/4e4a20a4462309f7e41f5cfe760e0cf3d6cad6ee.jpg
        userRecyclerAdapter.addChildClickViewIds(R.id.btn_delete);
        userRecyclerAdapter.setOnItemClickListener((adapter, view, position) -> {
            userRecyclerAdapter.getData().remove(position);
            userRecyclerAdapter.notifyItemRemoved(position);
            userRecyclerAdapter.notifyItemRangeChanged(position, userRecyclerAdapter.getData().size() - position);
        });
        userRecyclerAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.btn_delete:
                    userRecyclerAdapter.getData().get(position).setUrl("http://e.hiphotos.baidu.com/image/pic/item/4e4a20a4462309f7e41f5cfe760e0cf3d6cad6ee.jpg");
                    userRecyclerAdapter.notifyItemChanged(position);
                    break;
            }
        });
    }

    private void loadData(RefreshLayout refreshLayout) {
        if (page == 1) {
            userRecyclerAdapter.setList(getData());
        } else {
            userRecyclerAdapter.addData(getData());
        }

        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
            refreshLayout.finishLoadMore();
        }

        baseLoadMoreModule.setEnableLoadMore(true);
        if (userRecyclerAdapter.getData().size() >= 75) {
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
