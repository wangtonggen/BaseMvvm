package com.example.basemvvm.mvvm.view_model;

import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;

import androidx.databinding.ObservableField;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.SDCardUtils;
import com.example.basemvvm.R;
import com.example.basemvvm.base.BaseMVVMFragment;
import com.example.basemvvm.mvvm.view_model_base.BaseFragmentVM;
import com.example.basemvvm.network.model.DownloadModel;
import com.example.basemvvm.network.network_base.FileDownLoadObserver;
import com.example.basemvvm.utils.common_utils.LogUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;

import io.reactivex.disposables.Disposable;

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
        switch (view.getId()){
            case R.id.tv1:
                downLoadApk();
                break;
        }
    }

    private void downLoadApk(){
        final RxPermissions rxPermissions = new RxPermissions(mFragment);
        Disposable permissions = rxPermissions.requestEachCombined(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(permission -> {
                    if (permission.granted) {//已经同意
                        download();
                    }
                });
    }

    private void download(){
        if (SDCardUtils.isSDCardEnableByEnvironment()){//sdk卡是否可用
            String dir =  SDCardUtils.getSDCardPathByEnvironment() + File.separator+"app";
            LogUtils.logE("dir",dir);
            FileUtils.createOrExistsDir(dir);
            String url = "http://imtt.dd.qq.com/16891/E4E087B63E27B87175F4B9BC7CFC4997.apk?fsname=com.tencent.qlauncher_6.0.2_64170111.apk&csr=97c2";
            DownloadModel.getInstance().downloadFile(url, dir, "qq.apk", new FileDownLoadObserver<File>() {
                @Override
                public void onDownLoadSuccess(File file) {
                    AppUtils.installApp(file);
                    LogUtils.logE("onDownLoadSuccess","成功");
                }

                @Override
                public void onDownLoadFail(Throwable throwable) {
                    LogUtils.logE("onDownLoadFail","失败");
                }

                @Override
                public void onProgress(int progress, long total) {
                    LogUtils.logE("progress",progress+"---"+total);
                }
            });
        }
    }
}
