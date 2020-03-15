package com.example.basemvvm.ui.activity;

import androidx.appcompat.widget.AppCompatEditText;

import com.example.basemvvm.R;
import com.example.basemvvm.base.BaseNoMvvmActivity;

import butterknife.BindView;

/**
 * author: wtg
 * date:2020/3/13 0013
 * desc:
 */
public class TestActivity extends BaseNoMvvmActivity {
    @BindView(R.id.et_test)
    AppCompatEditText et_test;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_test;
    }
}
