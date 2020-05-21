package com.example.basemvvm.adapter;

import com.chad.library.adapter.base.module.LoadMoreModule;
import com.example.basemvvm.base.provider.ImageProvider;
import com.example.basemvvm.base.provider.UserProvider;
import com.example.basemvvm.bean.UserBean;
import com.wang.mvvmcore.adapter.multiAdapter.baseMultiBindingAdapter.BaseBindingProviderAdapter;

/**
 * author: wtg
 * date:2020/5/20 0020
 * desc:
 */
public class ProviderAdapter extends BaseBindingProviderAdapter<UserBean> implements LoadMoreModule {
    @Override
    protected void addItemType() {
        addItemProvider(new UserProvider());
        addItemProvider(new ImageProvider());
    }
}
