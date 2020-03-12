package com.example.basemvvm.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * author: wtg
 * date:2020/3/12 0012
 * desc: activity 基类
 */
public abstract class BaseActivity extends SwipeBackActivity {
    protected SwipeBackLayout mSwipeBackLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(getEdgeTrackingEnabled());
        //添加activity 到activity管理器里面
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    /**
     * activity 划出关闭的方向 SwipeBackLayout.EDGE_LEFT 向右划出   SwipeBackLayout.EDGE_RIGHT 向左划出  SwipeBackLayout.EDGE_BOTTOM 向上划出
     * @return 方向
     */
    protected abstract int getEdgeTrackingEnabled();
}
