package com.example.basemvvm.ui.activity;

import android.animation.Animator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.example.basemvvm.R;
import com.example.basemvvm.adapter.ViewPager2Adapter;
import com.example.basemvvm.base.fragment.BaseFragment;
import com.example.basemvvm.base.activity.BaseNoMVVMActivity;
import com.example.basemvvm.constant.IntentFilterConstant;
import com.example.basemvvm.ui.fragment.DashboardFragment;
import com.example.basemvvm.ui.fragment.HomeFragment;
import com.example.basemvvm.ui.fragment.NotificationsFragment;
import com.example.basemvvm.ui.fragment.UserFragment;
import com.example.basemvvm.utils.anim.TransitionAnimationUtils;
import com.example.basemvvm.utils.common.BottomNavigationViewUtils;
import com.example.basemvvm.utils.common.MyUserSPUtils;
import com.example.basemvvm.utils.common.ToastUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.lxj.xpopup.XPopup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseNoMVVMActivity {
    private int mIndex = 0;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.viewPager)
    ViewPager2 viewPager;

    LinearLayout ll_head;
    CircleImageView iv_head;
    AppCompatTextView tv_name;
    AppCompatTextView tv_phone;

    private UpdateLoginReceiver updateLoginReceiver;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected int getEdgeTrackingEnabled() {
        return -1;
    }

    @Override
    protected void initView() {
        super.initView();
        initUpdateLoginReceiver();
        bottomNavigationView.setItemIconTintList(null);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.str_open, R.string.str_close);
        mDrawerToggle.syncState();//初始化状态
        drawer.addDrawerListener(mDrawerToggle);
        navigationView.setNavigationItemSelectedListener(item -> {
            if (!MyUserSPUtils.isLogin() && item.getItemId() != R.id.nav_settings) {
                ToastUtils.showShortToast("您还没有登录，请登录");
                TransitionAnimationUtils.startSceneTransitionAnimationActivity(this, LoginActivity.class, iv_head, StringUtils.getString(R.string.transition_user_head));
                return false;
            }
            switch (item.getItemId()) {
                case R.id.nav_my_info:
                    startActivity(new Intent(MainActivity.this, UserInfoActivity.class));
                    break;
                case R.id.nav_my_attention:
                    //我的关注
                    break;
                case R.id.nav_day:
                    //白天模式
                    break;
                case R.id.nav_night:
                    //夜晚模式
                    break;
                case R.id.nav_settings:
                    //设置
                    break;
                case R.id.nav_logout:
                    new XPopup.Builder(this).asConfirm("提醒", "确定要退出登录？", "取消", "确定", () -> {
                                MyUserSPUtils.loginOutClear();
                                initHeadLayout();
                            },
                            () -> {

                            }, false)
                            .show();
                    break;
            }
//            if (MyUserSPUtils.isLogin()) {
//                drawer.closeDrawer(GravityCompat.START, true);
//            }
            return false;
        });

        ll_head = navigationView.getHeaderView(0).findViewById(R.id.ll_head);
        iv_head = navigationView.getHeaderView(0).findViewById(R.id.iv_head);
        tv_name = navigationView.getHeaderView(0).findViewById(R.id.tv_name);
        tv_phone = navigationView.getHeaderView(0).findViewById(R.id.tv_phone);
        //初始化侧滑头部信息
        initHeadLayout();

        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new DashboardFragment());
        fragments.add(new NotificationsFragment());
        fragments.add(new UserFragment());
        ViewPager2Adapter pagerAdapter = new ViewPager2Adapter(this, fragments);
        viewPager.setAdapter(pagerAdapter);
        BottomNavigationViewUtils.closeAnimation(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            private int previousPosition = -1;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int index = 0;
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        index = 0;
                        toolbar.setTitle("首页");
                        break;
                    case R.id.navigation_find:
                        index = 1;
                        toolbar.setTitle("发现");
                        break;
                    case R.id.navigation_message:
                        index = 2;
                        toolbar.setTitle("消息");
                        break;
                    case R.id.navigation_dynamic:
                        index = 3;
                        toolbar.setTitle("动态");
                        break;
                    case R.id.navigation_empty:
                        return false;
                }
                if (mIndex == index) {
                    return false;
                }
                if (previousPosition != index) {
                    viewPager.setCurrentItem(index, false);
                    previousPosition = index;
                    mIndex = index;
                }
                return false;
            }
        });

        viewPager.setOffscreenPageLimit(1);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                mIndex = position;
                if (position >= 2) {
                    position++;
                }
                switch (mIndex) {
                    case 0:
                        toolbar.setTitle("首页");
                        break;
                    case 1:
                        toolbar.setTitle("发现");
                        break;
                    case 2:
                        toolbar.setTitle("消息");
                        break;
                    case 3:
                        toolbar.setTitle("动态");
                        break;
                }
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }
        });

        bottomNavigationView.post(() -> {
            BottomNavigationViewUtils.showBadgeView(this, bottomNavigationView, 0, 100);
            BottomNavigationViewUtils.showBadgeView(this, bottomNavigationView, 1, 50);
            BottomNavigationViewUtils.showBadgeView(this, bottomNavigationView, 2, 8);
            BottomNavigationViewUtils.showBadgeView(this, bottomNavigationView, 3, -10);
        });
    }

    /**
     * 初始化广播接收器
     */
    private void initUpdateLoginReceiver() {
        updateLoginReceiver = new UpdateLoginReceiver();
        registerReceiver(updateLoginReceiver, new IntentFilter(IntentFilterConstant.UPDATE_LOGIN_ACTION));
    }

    /**
     * 初始化侧滑头部信息
     */
    private void initHeadLayout() {
        if (MyUserSPUtils.isLogin()) {
            Glide.with(this).load(MyUserSPUtils.getHeadUrl()).placeholder(R.drawable.ic_account_circle).into(iv_head);
            tv_name.setText(MyUserSPUtils.getUserName());
            tv_phone.setVisibility(View.VISIBLE);
            tv_phone.setText(MyUserSPUtils.getUserMobile());
        } else {
            Glide.with(this).load(R.drawable.ic_account_circle).into(iv_head);
            tv_name.setText("未登录");
            tv_phone.setVisibility(View.GONE);
        }

        ll_head.setOnClickListener(v -> {
            if (MyUserSPUtils.isLogin()) {
                //进入详情
                TransitionAnimationUtils.startSceneTransitionAnimationActivity(this, UserInfoActivity.class, new Pair<>(iv_head, StringUtils.getString(R.string.transition_user_head)), new Pair<>(tv_name, StringUtils.getString(R.string.transition_user_name)));
            } else {
                //进入登录页 登录完成后则发送广播通知主页面更新
                TransitionAnimationUtils.startSceneTransitionAnimationActivity(this, LoginActivity.class, iv_head, StringUtils.getString(R.string.transition_user_head));
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (updateLoginReceiver != null) {
            unregisterReceiver(updateLoginReceiver);
        }
    }

    /**
     * 更新登录情况
     */
    private class UpdateLoginReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //收到消息就更新
            initHeadLayout();
        }
    }

    private void starAnimation(View view, int colorId) {
        Animator anim = ViewAnimationUtils.createCircularReveal(view, view.getWidth() / 2, view.getHeight(), 0, view.getWidth() / 2.0f);
        view.setBackgroundColor(ContextCompat.getColor(this, colorId));
        anim.setDuration(500);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        anim.start();
    }

    private void closeAnimation(View view, final int color) {
        Animator anim = ViewAnimationUtils.createCircularReveal(view, view.getWidth() / 2, view.getHeight(), view.getWidth() / 2.0f, 0);
        anim.setDuration(500);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                starAnimation(view, color);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        anim.start();
    }
}
