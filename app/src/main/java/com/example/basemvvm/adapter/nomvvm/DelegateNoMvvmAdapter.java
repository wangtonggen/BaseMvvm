package com.example.basemvvm.adapter.nomvvm;


import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.basemvvm.R;
import com.example.basemvvm.bean.UserBean;
import com.wang.mvvmcore.adapter.multiAdapter.baseMultiAdapter.BaseDelegateAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * author: wtg
 * date:2020/5/26 0026
 * desc:
 */
public class DelegateNoMvvmAdapter extends BaseDelegateAdapter<UserBean, BaseViewHolder> {
    @Override
    public BaseMultiTypeDelegate<UserBean> getBaseMultiTypeDelegate() {
        return new MyMultiTypeDelegate();
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, UserBean userBean) {
        switch (baseViewHolder.getItemViewType()){
            case 0:
                Glide.with(getContext()).load(userBean.getUrl()).into((CircleImageView)baseViewHolder.getView(R.id.iv_image));
                break;
            case 1:
                Glide.with(getContext()).load(userBean.getUrl()).into((AppCompatImageView)baseViewHolder.getView(R.id.iv_image));
                break;
        }
    }

    public static class MyMultiTypeDelegate extends BaseMultiTypeDelegate<UserBean> {
        public MyMultiTypeDelegate() {
            addItemType(0, R.layout.recycler_item_user_nomvvm);
            addItemType(1, R.layout.recycler_item_image_nomvvm);
        }

        @Override
        public int getItemType(@NotNull List<? extends UserBean> list, int i) {
            return list.get(i).getItemType();
        }
    }
}
