package com.example.basemvvm.adapter;

import com.chad.library.adapter.base.module.LoadMoreModule;
import com.example.basemvvm.bean.UserBean;
import com.example.basemvvm.base.provider.ImageProvider;
import com.example.basemvvm.base.provider.UserProvider;
import com.wang.mvvmcore.adapter.multiAdapter.baseMultiBindingAdapter.BaseBindingProviderAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * author: wtg
 * date:2020/4/30 0030
 * desc:
 */
public class UserBindingAdapter extends BaseBindingProviderAdapter<UserBean> implements LoadMoreModule {
    public static int TYPE_USER = 0;
    public static int TYPE_IMAGE = 1;

    @Override
    protected void addItemType() {
        addItemProvider(new UserProvider());
        addItemProvider(new ImageProvider());
    }

    @Override
    protected int getItemType(@NotNull List<? extends UserBean> list, int i) {
        int type = 0;
        switch (list.get(i).getItemType()) {
            case 0:
                type = TYPE_USER;
                break;
            case 1:
                type = TYPE_IMAGE;
                break;
        }
        return type;
    }
}
