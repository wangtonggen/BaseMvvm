package com.example.basemvvm.ui.activity;

import android.content.Intent;
import android.view.View;

import com.example.basemvvm.R;
import com.example.basemvvm.ui.activity.noMvvm.BinderNoMvvmActivity;
import com.example.basemvvm.ui.activity.noMvvm.DelegateNoMvvmActivity;
import com.example.basemvvm.ui.activity.noMvvm.MultiNoMvvmActivity;
import com.example.basemvvm.ui.activity.noMvvm.ProviderNoMvvmActivity;
import com.example.basemvvm.ui.activity.noMvvm.SingleNoMvvmActivity;
import com.wang.mvvmcore.base.activity.BaseNoMVVMActivity;

import butterknife.OnClick;

/**
 * author: wtg
 * date:2020/3/13 0013
 * desc:
 */
public class TestActivity extends BaseNoMVVMActivity {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.tv_main, R.id.tv_databinding_delegate_multi, R.id.tv_databinding_multi, R.id.tv_databinding_provider, R.id.tv_databinding_binder, R.id.tv_databinding_single,
            R.id.tv_delegate_multi, R.id.tv_multi, R.id.tv_provider, R.id.tv_binder, R.id.tv_single})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_main:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.tv_databinding_delegate_multi://多布局条目 delegate方式
                startActivity(new Intent(this, DelegateActivity.class));
                break;
            case R.id.tv_databinding_multi://多布局条目 普通方式
                startActivity(new Intent(this, MultiActivity.class));
                break;
            case R.id.tv_databinding_provider://多布局条目 provider方式
                startActivity(new Intent(this, ProviderActivity.class));
                break;
            case R.id.tv_databinding_binder://多布局 binder方式
                startActivity(new Intent(this, BinderActivity.class));
                break;
            case R.id.tv_databinding_single:// 单布局
                startActivity(new Intent(this, SingleActivity.class));
                break;
            case R.id.tv_delegate_multi://多布局不使用dataBinding
                startActivity(new Intent(this, DelegateNoMvvmActivity.class));
                break;
            case R.id.tv_multi:
                startActivity(new Intent(this, MultiNoMvvmActivity.class));
                break;
            case R.id.tv_provider:
                startActivity(new Intent(this, ProviderNoMvvmActivity.class));
                break;
            case R.id.tv_binder:
                startActivity(new Intent(this, BinderNoMvvmActivity.class));
                break;
            case R.id.tv_single://单布局不适用dataBinding
                startActivity(new Intent(this, SingleNoMvvmActivity.class));
                break;
        }
    }

}
