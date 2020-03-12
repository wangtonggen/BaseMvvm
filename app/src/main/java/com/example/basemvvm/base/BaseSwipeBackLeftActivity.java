package com.example.basemvvm.base;

import me.imid.swipebacklayout.lib.SwipeBackLayout;

/**
 * author: wtg
 * date:2020/3/12 0012
 * desc: 向右划出
 */
public abstract class BaseSwipeBackLeftActivity extends BaseActivity {
    @Override
    protected int getEdgeTrackingEnabled() {
        return SwipeBackLayout.EDGE_LEFT;
    }
}
