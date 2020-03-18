package com.example.basemvvm.ui.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import com.example.basemvvm.R;
import com.example.basemvvm.adapter.MaxLifecyclePagerAdapter;
import com.example.basemvvm.base.BaseMVVMFragment;
import com.example.basemvvm.base.BaseNoMVVMActivity;
import com.example.basemvvm.ui.fragment.DashboardFragment;
import com.example.basemvvm.ui.fragment.HomeFragment;
import com.example.basemvvm.ui.fragment.NotificationsFragment;
import com.example.basemvvm.utils.common_utils.BottomNavigationViewUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class BottomActivity extends BaseNoMVVMActivity {

    private int mIndex = 0;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private Badge homeBadge;
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
        List<BaseMVVMFragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new DashboardFragment());
        fragments.add(new NotificationsFragment());
        fragments.add(new HomeFragment());
        MaxLifecyclePagerAdapter pagerAdapter = new MaxLifecyclePagerAdapter(getSupportFragmentManager(),fragments,null);
        viewPager.setAdapter(pagerAdapter);
        BottomNavigationViewUtils.closeAnimation(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int index = 0;
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        index = 0;
                        break;
                    case R.id.navigation_dashboard:
                        index = 1;
                        break;
                    case R.id.navigation_notifications:
                        index = 2;
                        break;
                    case R.id.navigation_home1:
                        index = 3;
                        break;
                }
                if (mIndex == index){
                    return false;
                }
                viewPager.setCurrentItem(index,false);
                mIndex = index;
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mIndex = position;
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottomNavigationView.post(()->{
            BottomNavigationViewUtils.showBadgeView(this,bottomNavigationView,0,100);
            BottomNavigationViewUtils.showBadgeView(this,bottomNavigationView,1,50);
            BottomNavigationViewUtils.showBadgeView(this,bottomNavigationView,2,8);
            BottomNavigationViewUtils.showBadgeView(this,bottomNavigationView,3,10);
        });
    }
}
