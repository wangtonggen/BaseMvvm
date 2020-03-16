package com.example.basemvvm.base;

import androidx.databinding.ViewDataBinding;

import me.imid.swipebacklayout.lib.SwipeBackLayout;

/**
 * author: wtg
 * date:2020/3/12 0012
 * desc: 向左划出
 */
public abstract class BaseSwipeBackRightActivity<B extends ViewDataBinding> extends BaseMvvmActivity {
    @Override
    protected int getEdgeTrackingEnabled() {
        return SwipeBackLayout.EDGE_RIGHT;
    }
}
