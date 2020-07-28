package com.wang.mvvmcore.base.activity;

import android.content.Intent;
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

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
//        setStartAnimation(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
//        setFinishAnimation(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
    }

    @Override
    public void finish() {
        super.finish();
//        setFinishAnimation(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
    }


    /**
     * 设置打开activity动画
     *
     * @param enterAnim 进入动画
     * @param exitAnim  退出动画
     */
    public void setStartAnimation(int enterAnim, int exitAnim) {
        overridePendingTransition(enterAnim, exitAnim);
    }

    /**
     * 设置关闭activity动画
     *
     * @param enterAnim 进入动画
     * @param exitAnim  退出动画
     */
    public void setFinishAnimation(int enterAnim, int exitAnim) {
        overridePendingTransition(enterAnim, exitAnim);
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
