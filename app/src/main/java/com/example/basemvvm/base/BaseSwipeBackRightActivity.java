package com.example.basemvvm.base;

import me.imid.swipebacklayout.lib.SwipeBackLayout;

/**
 * author: wtg
 * date:2020/3/12 0012
 * desc: 向左划出
 */
public abstract class BaseSwipeBackRightActivity extends BaseActivity {
    @Override
    protected int getEdgeTrackingEnabled() {
        return SwipeBackLayout.EDGE_RIGHT;
    }
}
