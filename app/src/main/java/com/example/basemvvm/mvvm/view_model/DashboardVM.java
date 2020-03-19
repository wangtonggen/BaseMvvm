package com.example.basemvvm.mvvm.view_model;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;

import androidx.core.app.NotificationCompat;
import androidx.databinding.ObservableField;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.NotificationUtils;
import com.blankj.utilcode.util.SDCardUtils;
import com.example.basemvvm.R;
import com.example.basemvvm.base.BaseApplication;
import com.example.basemvvm.base.BaseMVVMFragment;
import com.example.basemvvm.mvvm.view_model_base.BaseFragmentVM;
import com.example.basemvvm.network.model.DownloadModel;
import com.example.basemvvm.network.network_base.FileDownLoadObserver;
import com.example.basemvvm.service.UpdateAppService;
import com.example.basemvvm.utils.common_utils.LogUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.Objects;

import io.reactivex.disposables.Disposable;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * author: wtg
 * date:2020/3/18 0018
 * desc:
 */
public class DashboardVM extends BaseFragmentVM {
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
