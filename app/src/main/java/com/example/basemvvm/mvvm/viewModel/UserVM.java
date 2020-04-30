package com.example.basemvvm.mvvm.viewModel;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.basemvvm.R;
import com.example.basemvvm.adapter.UserRecyclerAdapter;
import com.example.basemvvm.base.baseViewModel.BaseFragmentLifecycleVM;
import com.example.basemvvm.base.fragment.BaseMVVMFragment;
import com.example.basemvvm.bean.NotificationBean;
import com.example.basemvvm.bean.UserBean;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

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
    public UserRecyclerAdapter userRecyclerAdapter;
    private List<UserBean> userBeans = new ArrayList<>();
    public LinearLayoutManager linearLayoutManager;
    public OnRefreshListener onRefreshListener = refreshLayout -> {
        page = 1;
        loadData(refreshLayout);
    };

    public OnLoadMoreListener onLoadMoreListener = refreshLayout -> {
        page++;
        loadData(refreshLayout);
    };
    public UserVM(BaseMVVMFragment fragment) {
        super(fragment);
        init();
    }

    @Override
    protected void init() {
        linearLayoutManager = new LinearLayoutManager(mContext);
        userRecyclerAdapter = new UserRecyclerAdapter(userBeans);

        //url http://e.hiphotos.baidu.com/image/pic/item/4e4a20a4462309f7e41f5cfe760e0cf3d6cad6ee.jpg
        userRecyclerAdapter.addChildClickViewIds(R.id.btn_delete);
        userRecyclerAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()){
                case R.id.btn_delete:
                    userBeans.get(position).setUrl("http://e.hiphotos.baidu.com/image/pic/item/4e4a20a4462309f7e41f5cfe760e0cf3d6cad6ee.jpg");
                    userRecyclerAdapter.notifyItemChanged(position);
                    break;
            }
        });
    }

    private void loadData(RefreshLayout refreshLayout) {
        Random random = new Random();
        if (page == 1) {
            userBeans.clear();
            for (int i = 0; i < pageSize; i++) {
                userBeans.add(new UserBean(random.nextInt(2),"http://g.hiphotos.baidu.com/image/pic/item/6d81800a19d8bc3e770bd00d868ba61ea9d345f2.jpg"));
                userRecyclerAdapter.notifyDataSetChanged();
            }
        } else {
            for (int i = 0; i < pageSize; i++) {
                userBeans.add(new UserBean(random.nextInt(2),"http://g.hiphotos.baidu.com/image/pic/item/6d81800a19d8bc3e770bd00d868ba61ea9d345f2.jpg"));
                userRecyclerAdapter.notifyItemRangeInserted(userBeans.size(), 15);
            }
        }

        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
            refreshLayout.finishLoadMore();
        }
    }
}
