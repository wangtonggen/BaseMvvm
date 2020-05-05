package com.example.basemvvm.ui.activity;

import android.annotation.SuppressLint;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.example.basemvvm.R;
import com.example.basemvvm.adapter.ViewPager2Adapter;
import com.example.basemvvm.base.fragment.BaseFragment;
import com.example.basemvvm.base.activity.BaseNoMVVMActivity;
import com.example.basemvvm.ui.fragment.DashboardFragment;
import com.example.basemvvm.ui.fragment.HomeFragment;
import com.example.basemvvm.ui.fragment.NotificationsFragment;
import com.example.basemvvm.ui.fragment.UserFragment;
import com.example.basemvvm.utils.common.BottomNavigationViewUtils;
import com.example.basemvvm.utils.common.MyUserSPUtils;
import com.example.basemvvm.utils.common.ToastUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.gyf.immersionbar.ImmersionBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
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

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected int getEdgeTrackingEnabled() {
        return -1;
    }

    @SuppressLint("RtlHardcoded")
    @Override
    protected void initView() {
        super.initView();
//        ImmersionBar.with(this).statusBarDarkFont(false).init();
        bottomNavigationView.setItemIconTintList(null);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.str_open, R.string.str_close);
        mDrawerToggle.syncState();//初始化状态
        drawer.addDrawerListener(mDrawerToggle);
        navigationView.setNavigationItemSelectedListener(item -> {
            drawer.closeDrawer(Gravity.LEFT, true);
            return false;
        });

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

        bottomNavigationView.setOnNavigationItemSelectedListener((@NonNull MenuItem item) -> {
            int index = 0;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    index = 0;
                    toolbar.setTitle("首页");
                    ImmersionBar.with(this).statusBarDarkFont(false).init();
                    break;
                case R.id.navigation_find:
                    index = 1;
                    break;
                case R.id.navigation_message:
                    index = 2;
                    break;
                case R.id.navigation_dynamic:
//                    ImmersionBar.with(this).statusBarDarkFont(true).init();
                    index = 3;
                    break;
            }
            if (mIndex == index) {
                return false;
            }
            viewPager.setCurrentItem(index, false);
            mIndex = index;
            return false;
        });

        viewPager.setOffscreenPageLimit(1);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                mIndex = position;
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
     * 初始化侧滑头部信息
     */
    private void initHeadLayout() {
        ll_head = navigationView.getHeaderView(0).findViewById(R.id.ll_head);
        iv_head = navigationView.getHeaderView(0).findViewById(R.id.iv_head);
        tv_name = navigationView.getHeaderView(0).findViewById(R.id.tv_name);
        tv_phone = navigationView.getHeaderView(0).findViewById(R.id.tv_phone);

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
                ToastUtils.showShortToast("已登录");
            } else {
                //进入登录页 登录完成后则发送广播通知主页面更新
                ToastUtils.showShortToast("未登录");
            }
        });
    }
}
