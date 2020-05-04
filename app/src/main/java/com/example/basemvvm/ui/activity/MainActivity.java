package com.example.basemvvm.ui.activity;

import android.view.Gravity;
import android.view.MenuItem;

import com.example.basemvvm.R;
import com.example.basemvvm.adapter.ViewPager2Adapter;
import com.example.basemvvm.base.fragment.BaseFragment;
import com.example.basemvvm.base.activity.BaseNoMVVMActivity;
import com.example.basemvvm.ui.fragment.DashboardFragment;
import com.example.basemvvm.ui.fragment.HomeFragment;
import com.example.basemvvm.ui.fragment.NotificationsFragment;
import com.example.basemvvm.ui.fragment.UserFragment;
import com.example.basemvvm.utils.common.BottomNavigationViewUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.gyf.immersionbar.ImmersionBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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
//        ImmersionBar.with(this).statusBarDarkFont(false).init();
        bottomNavigationView.setItemIconTintList(null);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.str_open, R.string.str_close);
        mDrawerToggle.syncState();//初始化状态
        drawer.addDrawerListener(mDrawerToggle);
        navigationView.setNavigationItemSelectedListener(item -> {
            drawer.closeDrawer(Gravity.LEFT,true);
            return false;
        });
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
                case R.id.navigation_dashboard:
                    index = 1;
                    break;
                case R.id.navigation_notifications:
                    index = 2;
                    break;
                case R.id.user:
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
}
