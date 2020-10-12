package com.example.basemvvm.adapter;

import com.example.basemvvm.base.provider.Home01Provider;
import com.example.basemvvm.base.provider.HomeProvider;
import com.example.basemvvm.bean.MultiItemBean;
import com.wang.mvvmcore.adapter.multi.adapter.BaseProviderAdapter;

/**
 * author: wtg
 * date:2020/5/20 0020
 * desc:
 */
public class MultiAdapter extends BaseProviderAdapter<MultiItemBean> {

    @Override
    protected void addItemTypeProvider() {
        addItemProvider(new HomeProvider());
        addItemProvider(new Home01Provider());
    }
}
