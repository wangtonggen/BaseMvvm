package com.example.basemvvm.base;

import androidx.databinding.ViewDataBinding;

import com.example.basemvvm.mvvm.view_model_base.BaseVM;

/**
 * author: wtg
 * date:2020/3/17 0017
 * desc: 懒加载
 */
public abstract class LazyLoadFragment<B extends ViewDataBinding,VM extends BaseVM> extends BaseMvvmFragment<B,VM> {
    @Override
    public void onResume() {
        super.onResume();
        //懒加载 加载数据
        onLazyLoadData();
    }
    //加载数据
    public abstract void onLazyLoadData();
}
