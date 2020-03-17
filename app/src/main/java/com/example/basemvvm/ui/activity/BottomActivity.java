package com.example.basemvvm.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.basemvvm.R;
import com.example.basemvvm.adapter.MaxLifecyclePagerAdapter;
import com.example.basemvvm.ui.activity.ui.dashboard.DashboardFragment;
import com.example.basemvvm.ui.activity.ui.home.HomeFragment;
import com.example.basemvvm.ui.activity.ui.notifications.NotificationsFragment;
import com.example.basemvvm.utils.common_utils.BottomNavigationViewUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class BottomActivity extends AppCompatActivity {

    private int mIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);
        BottomNavigationView bottom = findViewById(R.id.bottom);
        bottom.setItemIconTintList(null);
        ViewPager viewPager = findViewById(R.id.viewPager);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new DashboardFragment());
        fragments.add(new NotificationsFragment());
        fragments.add(new HomeFragment());
        MaxLifecyclePagerAdapter pagerAdapter = new MaxLifecyclePagerAdapter(getSupportFragmentManager(),fragments,null);
        viewPager.setAdapter(pagerAdapter);
        BottomNavigationViewUtils.closeAnimation(bottom);

        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                if (mIndex == viewPager.getCurrentItem()){
//                    return false;
//                }
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
                bottom.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
