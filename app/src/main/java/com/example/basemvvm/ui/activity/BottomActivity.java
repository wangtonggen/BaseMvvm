package com.example.basemvvm.ui.activity;

import android.view.MenuItem;

import com.example.basemvvm.R;
import com.example.basemvvm.adapter.MaxLifecyclePagerAdapter;
import com.example.basemvvm.adapter.ViewPager2Adapter;
import com.example.basemvvm.base.fragment.BaseFragment;
import com.example.basemvvm.base.fragment.BaseMVVMFragment;
import com.example.basemvvm.base.activity.BaseNoMVVMActivity;
import com.example.basemvvm.ui.fragment.DashboardFragment;
import com.example.basemvvm.ui.fragment.HomeFragment;
import com.example.basemvvm.ui.fragment.NotificationsFragment;
import com.example.basemvvm.ui.fragment.UserFragment;
import com.example.basemvvm.utils.common.BottomNavigationViewUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BottomActivity extends BaseNoMVVMActivity {
    private int mIndex = 0;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.viewPager)
    ViewPager2 viewPager;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_bottom;
    }

    @Override
    protected int getEdgeTrackingEnabled() {
        return -1;
    }

    @Override
    protected void initView() {
        super.initView();
        bottomNavigationView.setItemIconTintList(null);
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
                    break;
                case R.id.navigation_dashboard:
                    index = 1;
                    break;
                case R.id.navigation_notifications:
                    index = 2;
                    break;
                case R.id.user:
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
