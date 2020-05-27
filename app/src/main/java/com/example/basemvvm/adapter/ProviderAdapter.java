package com.example.basemvvm.adapter;

import com.example.basemvvm.base.provider.ImageBindingProvider;
import com.example.basemvvm.base.provider.UserBindingProvider;
import com.example.basemvvm.bean.UserBean;
import com.wang.mvvmcore.adapter.multiAdapter.baseMultiAdapter.BaseProviderAdapter;

/**
 * author: wtg
 * date:2020/5/20 0020
 * desc:
 */
public class ProviderAdapter extends BaseProviderAdapter<UserBean> {
    @Override
    protected void addItemType() {
        addItemProvider(new UserBindingProvider());
        addItemProvider(new ImageBindingProvider());
    }
}
