package com.example.basemvvm.network.model;

import com.example.basemvvm.network.service.DownloadAndUploadService;
import com.wang.mvvmcore.network.networkBase.FileDownLoadObserver;
import com.wang.mvvmcore.network.networkBase.RetrofitManager;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author: wtg
 * date:2020/3/18 0018
 * desc: 下载的model(不需要实时监听下载进度)
 */
public class DownloadModel {
    private static DownloadModel downloadModel;
    private static DownloadAndUploadService downloadAndUploadService;

    public static DownloadModel getInstance() {
        if (downloadModel == null) {
            synchronized (UserModel.class) {
                if (downloadModel == null) {
                    downloadModel = new DownloadModel();
                }
            }
        }
        if (downloadAndUploadService == null) {
            downloadAndUploadService = RetrofitManager.getInstance().createService(DownloadAndUploadService.class);
        }
        return downloadModel;
    }
}
