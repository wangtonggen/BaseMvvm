package com.example.basemvvm.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;

import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.SwipeBackLayout;

/**
 * author：wtg
 * time：2020/3/15
 * desc：不使用MVVM结构的 使用butterKnife 控件绑定
 */
public abstract class BaseNoMvvmActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 初始化控件
     */
    protected void initView() {

    }

    /**
     * activity 划出关闭的方向 SwipeBackLayout.EDGE_LEFT 向右划出   SwipeBackLayout.EDGE_RIGHT 向左划出  SwipeBackLayout.EDGE_BOTTOM 向上划出
     *
     * @return 方向
     */
    protected int getEdgeTrackingEnabled(){
        return SwipeBackLayout.EDGE_LEFT;
    }

    /**
     * 获取布局id
     * @return layoutId
     */
    @LayoutRes
    protected abstract int getLayoutRes();
}
