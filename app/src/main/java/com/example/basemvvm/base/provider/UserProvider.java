package com.example.basemvvm.base.provider;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.basemvvm.R;
import com.example.basemvvm.adapter.UserAdapter;
import com.example.basemvvm.bean.UserBean;
import com.wang.mvvmcore.adapter.multi.provider.BaseProvider;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * author: wtg
 * date:2020/5/27 0027
 * desc:
 */
public class UserProvider extends BaseProvider<UserBean> {
    @Override
    public int getItemViewType() {
        return UserAdapter.TYPE_USER;
    }

    @Override
    public int getLayoutId() {
        return R.layout.recycler_item_user_nomvvm;
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, UserBean userBean) {
        Glide.with(getContext()).load(userBean.getUrl()).into((CircleImageView)baseViewHolder.getView(R.id.iv_image));
    }
}
