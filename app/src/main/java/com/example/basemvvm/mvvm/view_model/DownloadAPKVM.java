package com.example.basemvvm.mvvm.view_model;

import android.content.Intent;
import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.SDCardUtils;
import com.example.basemvvm.R;
import com.example.basemvvm.base.activity.BaseActivity;
import com.example.basemvvm.bean.UpdateBean;
import com.example.basemvvm.mvvm.view_model_base.BaseActivityLifecycleVM;
import com.example.basemvvm.network.downloadAndUpload.download.DownLoadUtils;
import com.example.basemvvm.network.downloadAndUpload.download.DownloadResponseBody;
import com.example.basemvvm.service.DownloadApkService;
import com.example.basemvvm.utils.commonUtils.LogUtils;
import com.example.basemvvm.utils.commonUtils.MyFileUtils;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static com.example.basemvvm.constant.FileConstant.DIR_APP;

/**
 * author: wtg
 * date:2020/4/2 0002
 * desc: 下载APK的vm
 */
public class DownloadAPKVM extends BaseActivityLifecycleVM {
    //防止引用不消失出现以前的引用 不要使用static
    public ObservableInt progressInt = new ObservableInt(0);
    public ObservableField<String> titleText = new ObservableField<>("");
    public ObservableField<String> cancelText = new ObservableField<>("");
    public ObservableField<String> sureText = new ObservableField<>("");
    public ObservableField<String> descText = new ObservableField<>("");
    public ObservableBoolean isDownLoading = new ObservableBoolean(false);
    public ObservableInt updateType = new ObservableInt(0);
    private UpdateBean updateBean;
    private Call call;

    public DownloadAPKVM(BaseActivity mActivity) {
        super(mActivity);
    }

    public void setUpdateBean(UpdateBean updateBean) {
        this.updateBean = updateBean;
        titleText.set("提醒");
        cancelText.set("取消");
        sureText.set("确定");
        updateType.set(updateBean.getUpdateType());
        descText.set(updateBean.getDesc());
    }

    @Override
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                mActivity.finish();
                break;
            case R.id.tv_sure:
                switch (updateBean.getUpdateType()) {
                    case 0:
                        Intent intent = new Intent(mActivity, DownloadApkService.class);
                        intent.putExtra("update", updateBean);
                        mActivity.startService(intent);
                        mActivity.finish();
                        break;
                    case 1:
                        download();
                        break;
                }
                break;
        }
    }

    private void download() {
        isDownLoading.set(true);
        titleText.set("下载");
        if (SDCardUtils.isSDCardEnableByEnvironment()) {//sdk卡是否可用
            FileUtils.createOrExistsDir(DIR_APP);
            call = DownLoadUtils.download(updateBean.getDownloadUrl(), new DownloadResponseBody.DownloadListener() {
                @Override
                public void onStartDownload(long length) {
                    LogUtils.logE("onStartDownload=" + length);
                }

                @Override
                public void onProgress(long progress, long total, boolean done) {
                    LogUtils.logE("onProgress=" + progress + "---total=" + total);
                    progressInt.set((int) (progress * 100 / total));
                }

                @Override
                public void onFail(String errorInfo) {
                    LogUtils.logE("onFail=" + errorInfo);
                    mActivity.finish();
                }
            }, new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    LogUtils.logE("onFailure=" + e.getMessage());
                    mActivity.finish();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) {
                    LogUtils.logE("onResponse=" + response.message());
                    ResponseBody responseBody = response.body();
                    if (responseBody != null) {
                        File file = MyFileUtils.saveFile(responseBody.byteStream(), DIR_APP, "hello.apk");
                        AppUtils.installApp(file);
                        mActivity.finish();
                    }
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (call != null) {
            call.cancel();
        }
    }
}
