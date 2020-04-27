package com.example.basemvvm.mvvm.viewModel;

import android.Manifest;
import android.content.Intent;
import android.view.View;

import androidx.databinding.ObservableField;

import com.example.basemvvm.R;
import com.example.basemvvm.base.fragment.BaseMVVMFragment;
import com.example.basemvvm.base.baseViewModel.BaseFragmentLifecycleVM;
import com.example.basemvvm.service.UpdateAppService;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.disposables.Disposable;

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

    private void downLoadApk() {
        final RxPermissions rxPermissions = new RxPermissions(mFragment);
        Disposable permissions = rxPermissions.requestEachCombined(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(permission -> {
                    if (permission.granted) {//已经同意
                        mContext.startService(new Intent(mContext, UpdateAppService.class));
                    }
                });
    }
}
