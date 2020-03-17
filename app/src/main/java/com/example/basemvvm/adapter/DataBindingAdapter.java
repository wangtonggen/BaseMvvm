package com.example.basemvvm.adapter;

import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.basemvvm.R;
import com.example.basemvvm.bean.LoginBean;
import com.example.basemvvm.databinding.ActivityMainBinding;

import org.jetbrains.annotations.NotNull;

/**
 * author: wtg
 * date:2020/3/16 0016
 * desc: dataBinding 列表的adapter
 */
public class DataBindingAdapter extends BaseQuickAdapter<LoginBean, BaseViewHolder> {
    public DataBindingAdapter() {
        super(R.layout.activity_main);
    }

    @Override
    protected void onItemViewHolderCreated(@NotNull BaseViewHolder viewHolder, int viewType) {
        DataBindingUtil.bind(viewHolder.itemView);//绑定数据
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, LoginBean loginBean) {
        ActivityMainBinding binding = baseViewHolder.getBinding();
//        binding.btnCode.setOnClickListener();
//        binding.setLoginVm();
        //把bean类导入到item的xml中，设置相应的bean 然后获取相应的数据 使用setNewData方法来设置数据
    }
}
