package com.example.basemvvm.ui.activity;

import androidx.appcompat.widget.AppCompatEditText;

import com.example.basemvvm.R;
import com.example.basemvvm.base.activity.BaseNoMVVMActivity;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: wtg
 * date:2020/3/13 0013
 * desc:
 */
public class TestActivity extends BaseNoMVVMActivity {
    @BindView(R.id.et_test)
    AppCompatEditText et_test;
    @BindView(R.id.bnve)
    BottomNavigationViewEx bnve;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {
        bnve.enableAnimation(false);
        bnve.enableShiftingMode(false);
        bnve.enableItemShiftingMode(false);
    }

    @OnClick(R.id.et_test)
    public void onViewClicked() {
    }
}
