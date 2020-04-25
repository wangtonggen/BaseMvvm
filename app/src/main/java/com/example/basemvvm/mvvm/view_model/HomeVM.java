package com.example.basemvvm.mvvm.view_model;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.basemvvm.adapter.HomeRecyclerAdapter;
import com.example.basemvvm.base.fragment.BaseMVVMFragment;
import com.example.basemvvm.bean.MultiItemBean;
import com.example.basemvvm.mvvm.view_model_base.BaseFragmentLifecycleVM;

import java.util.ArrayList;
import java.util.List;

/**
 * author: wtg
 * date:2020/3/18 0018
 * desc: 首页的viewModel
 */
public class HomeVM extends BaseFragmentLifecycleVM {
    public LinearLayoutManager linearLayoutManager;
    public HomeRecyclerAdapter homeRecyclerAdapter;
    private List<MultiItemBean> multiItemBeans = new ArrayList<>();

    public HomeVM(BaseMVVMFragment fragment) {
        super(fragment);
        init();
    }

    @Override
    protected void init() {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                multiItemBeans.add(new MultiItemBean(1, "item_" + i, "itemHome_" + i));
            } else {
                multiItemBeans.add(new MultiItemBean(0, "item_" + i, "itemHome_" + i));
            }
        }
        linearLayoutManager = new LinearLayoutManager(mContext);
        homeRecyclerAdapter = new HomeRecyclerAdapter(multiItemBeans);
    }
}
