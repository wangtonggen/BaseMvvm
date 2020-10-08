package com.example.basemvvm.adapter.nomvvm;


import com.example.basemvvm.base.provider.ImageProvider;
import com.example.basemvvm.base.provider.UserProvider;
import com.example.basemvvm.bean.UserBean;
import com.wang.mvvmcore.adapter.multi.adapter.BaseProviderAdapter;

/**
 * author: wtg
 * date:2020/5/27 0027
 * desc:
 */
public class ProviderNoMvvmAdapter extends BaseProviderAdapter<UserBean> {
    @Override
    protected void addItemTypeProvider() {
        addItemProvider(new UserProvider());
        addItemProvider(new ImageProvider());
    }
}
