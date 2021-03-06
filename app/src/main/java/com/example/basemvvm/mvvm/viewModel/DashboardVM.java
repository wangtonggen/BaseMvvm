package com.example.basemvvm.mvvm.viewModel;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;

import androidx.databinding.ObservableField;

import com.example.basemvvm.R;
import com.example.basemvvm.service.UpdateAppService;
import com.tbruyelle.rxpermissions3.Permission;
import com.tbruyelle.rxpermissions3.RxPermissions;
import com.wang.mvvmcore.base.baseViewModel.BaseFragmentLifecycleVM;
import com.wang.mvvmcore.base.fragment.BaseMVVMFragment;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * author: wtg
 * date:2020/3/18 0018
 * desc:
 */
public class DashboardVM extends BaseFragmentLifecycleVM {
    public final ObservableField<String> text = new ObservableField<>();
    public final ObservableField<String> text1 = new ObservableField<>();
    public final ObservableField<String> text2 = new ObservableField<>();

    public DashboardVM(BaseMVVMFragment fragment) {
        super(fragment);
        text.set("wang");
        text1.set("tong");
        text2.set("gen");
    }

    @Override
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv1:
                downLoadApk();
                break;
        }
    }

    @SuppressLint("CheckResult")
    private void downLoadApk() {
        final RxPermissions rxPermissions = new RxPermissions(mFragment);
        @NonNull Disposable subscribe = rxPermissions.requestEachCombined(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe((permission -> {
                    if (permission.granted) {//已经同意
                        mContext.startService(new Intent(mContext, UpdateAppService.class));
                    }
                }));
    }
}
