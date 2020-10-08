package com.example.basemvvm.adapter;

import com.example.basemvvm.base.provider.Home01Provider;
import com.example.basemvvm.base.provider.HomeProvider;
import com.example.basemvvm.bean.MultiItemBean;
import com.wang.mvvmcore.adapter.multi.adapter.BaseProviderAdapter;

/**
 * author: wtg
 * date:2020/3/23 0023
 * desc: 多布局监听
 */
public class HomeBindingMultiAdapter extends BaseProviderAdapter<MultiItemBean> {

    @Override
    protected void addItemTypeProvider() {
        addItemProvider(new HomeProvider());
        addItemProvider(new Home01Provider());
    }
}
