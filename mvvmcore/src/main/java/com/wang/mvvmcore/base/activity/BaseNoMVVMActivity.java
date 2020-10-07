package com.wang.mvvmcore.base.activity;

import android.os.Bundle;

import com.wang.mvvmcore.constant.SwipeConstant;

import butterknife.ButterKnife;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import me.imid.swipebacklayout.lib.SwipeBackLayout;

/**
 * author：wtg
 * time：2020/3/15
 * desc：不使用MVVM结构的 使用butterKnife 控件绑定
 */
public abstract class BaseNoMVVMActivity extends BaseActivity {
    protected CompositeDisposable mDisposables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
        initView();
    }

    /**
     * activity 划出关闭的方向 SwipeBackLayout.EDGE_LEFT 向右划出   SwipeBackLayout.EDGE_RIGHT 向左划出  SwipeBackLayout.EDGE_BOTTOM 向上划出 其他值无效果
     *
     * @return 方向
     */
    protected int getEdgeTrackingEnabled() {
        return SwipeConstant.SWIPE_LEFT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onUnBind();
    }

    /**
     * 加入订阅对象
     *
     * @param disposable 订阅对象
     */
    protected void addDisposable(Disposable disposable) {
        if (mDisposables == null || mDisposables.isDisposed()) {
            mDisposables = new CompositeDisposable();
        }
        mDisposables.add(disposable);
    }

    /**
     * 清除所有的请求
     */
    public void onUnBind() {
        if (mDisposables != null && mDisposables.size() > 0 && !mDisposables.isDisposed()) {
            mDisposables.dispose();
        }
    }
}
