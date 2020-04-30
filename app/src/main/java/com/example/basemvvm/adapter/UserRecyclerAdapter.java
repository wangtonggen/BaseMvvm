package com.example.basemvvm.adapter;

import com.example.basemvvm.adapter.baseAdapter.BaseRecyclerProviderMultiAdapter;
import com.example.basemvvm.bean.UserBean;
import com.example.basemvvm.provider.ImageProvider;
import com.example.basemvvm.provider.UserProvider;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author: wtg
 * date:2020/4/30 0030
 * desc:
 */
public class UserRecyclerAdapter extends BaseRecyclerProviderMultiAdapter<UserBean> {
    public static int TYPE_USER = 0;
    public static int TYPE_IMAGE = 1;
    public UserRecyclerAdapter(@Nullable List<UserBean> data) {
        super(data);
    }

    @Override
    protected void addItemType() {
        addItemProvider(new UserProvider());
        addItemProvider(new ImageProvider());
    }

    @Override
    protected int getItemType(@NotNull List<? extends UserBean> list, int i) {
        int type = 0;
        switch (list.get(i).getItemType()){
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
