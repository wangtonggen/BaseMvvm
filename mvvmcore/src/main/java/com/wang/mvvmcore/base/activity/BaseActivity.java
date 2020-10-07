package com.wang.mvvmcore.base.activity;

import android.os.Bundle;

import androidx.annotation.LayoutRes;

import com.wang.mvvmcore.Interface.SwipeInterface;
import com.wang.mvvmcore.utils.common.ActivityManagerUtils;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * author：wtg
 * time：2020/3/15
 * desc：
 */
public abstract class BaseActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwipeBackLayout mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(getEdgeTrackingEnabled());
        ActivityManagerUtils.getAppManager().addActivity(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ActivityManagerUtils.getAppManager().finishActivity(this);
    }

    /**
     * 初始化控件
     */
    protected void initView() {

    }

    /**
     * activity 划出关闭的方向 SwipeConstant.EDGE_LEFT 向右划出   SwipeConstant.EDGE_RIGHT 向左划出  SwipeConstant.EDGE_BOTTOM 向上划出
     *
     * @return 方向
     */
    @SwipeInterface
    protected abstract int getEdgeTrackingEnabled();

    /**
     * 获取布局id
     *
     * @return layoutId
     */
    @LayoutRes
    protected abstract int getLayoutRes();
}
