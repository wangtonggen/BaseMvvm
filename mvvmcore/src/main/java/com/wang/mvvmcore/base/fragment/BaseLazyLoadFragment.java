package com.wang.mvvmcore.base.fragment;

/**
 * author: wtg
 * date:2020/3/17 0017
 * desc: 懒加载的fragment基类
 */
abstract class BaseLazyLoadFragment extends BaseFragment {
    private boolean isFirstLoad = true;//是否是第一次加载 true是 false 否

    @Override
    public void onResume() {
        super.onResume();
        //懒加载 加载数据
        if (isFirstLoad) {
            isFirstLoad = false;
            onLazyLoadData();
        }
    }

    //加载数据
    public abstract void onLazyLoadData();
}
