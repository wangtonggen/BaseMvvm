package com.example.basemvvm.base;


/**
 * author: wtg
 * date:2020/3/12 0012
 * desc: 向左划出
 */
public abstract class BaseSwipeNoneRightActivity extends BaseActivity {
    @Override
    protected int getEdgeTrackingEnabled() {
        return -1;
    }
}
