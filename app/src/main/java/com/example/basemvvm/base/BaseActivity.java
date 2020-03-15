package com.example.basemvvm.base;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.LayoutRes;

import com.example.basemvvm.R;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;

import javax.annotation.Nonnull;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * author：wtg
 * time：2020/3/15
 * desc：
 */
public abstract class BaseActivity extends SwipeBackActivity {
    protected SwipeBackLayout mSwipeBackLayout;
    protected BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(ActivityEvent.CREATE);
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(getEdgeTrackingEnabled());
    }

    @Nonnull
    public Observable<ActivityEvent> lifecycle() {
        return lifecycleSubject.lift(observer -> observer);
    }

    @Override
    public void onStart() {
        super.onStart();
        lifecycleSubject.onNext(ActivityEvent.START);
    }

    @Override
    public void onResume() {
        super.onResume();
        lifecycleSubject.onNext(ActivityEvent.RESUME);
    }

    @Override
    public void onPause() {
        super.onPause();
        lifecycleSubject.onNext(ActivityEvent.PAUSE);
    }

    @Override
    public void onStop() {
        super.onStop();
        lifecycleSubject.onNext(ActivityEvent.STOP);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lifecycleSubject.onNext(ActivityEvent.DESTROY);
    }

    @Nonnull
    public <T> LifecycleTransformer<T> bindUntilEvent(@Nonnull ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Nonnull
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycle.bind(lifecycleSubject);
    }

    protected void startAnimationActivity(Intent intent){
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
        startActivity(intent);
    }

    protected void finishAnimationActivity(){
        finish();
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
    }

    /**
     * activity 划出关闭的方向 SwipeBackLayout.EDGE_LEFT 向右划出   SwipeBackLayout.EDGE_RIGHT 向左划出  SwipeBackLayout.EDGE_BOTTOM 向上划出
     *
     * @return 方向
     */
    protected abstract int getEdgeTrackingEnabled();

    /**
     * 获取布局id
     * @return layoutId
     */
    @LayoutRes
    protected abstract int getLayoutRes();
}
