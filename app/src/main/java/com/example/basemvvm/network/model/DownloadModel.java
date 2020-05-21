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

    /**
     * 不需要下载进度的 下载器
     *
     * @param url                  路径
     * @param destDir              文件目录
     * @param fileName             文件名称
     * @param fileDownLoadObserver 文件监听
     */
    public void downloadFile(String url, String destDir, String fileName, FileDownLoadObserver<File> fileDownLoadObserver) {
        downloadAndUploadService.download(url)
                .subscribeOn(Schedulers.io())//subscribeOn和ObserOn必须在io线程，如果在主线程会出错
                .observeOn(Schedulers.io())
                .observeOn(Schedulers.computation())//需要
                .map(responseBody -> fileDownLoadObserver.saveFile(responseBody, destDir, fileName))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(fileDownLoadObserver);
    }
}
