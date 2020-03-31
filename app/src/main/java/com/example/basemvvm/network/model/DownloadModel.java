package com.example.basemvvm.network.model;

import androidx.annotation.NonNull;

import com.example.basemvvm.network.network_base.FileDownLoadObserver;
import com.example.basemvvm.network.network_base.RetrofitManager;
import com.example.basemvvm.network.service.DownloadService;
import com.example.basemvvm.utils.common_utils.LogUtils;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * author: wtg
 * date:2020/3/18 0018
 * desc: 下载的model
 */
public class DownloadModel {
    private static DownloadModel downloadModel;
    private static DownloadService downloadService;

    public static DownloadModel getInstance() {
        if (downloadModel == null) {
            synchronized (UserModel.class) {
                if (downloadModel == null) {
                    downloadModel = new DownloadModel();
                }
            }
        }
        if (downloadService == null) {
            downloadService = RetrofitManager.getInstance().createService(DownloadService.class);
        }
        return downloadModel;
    }

    /**
     * 带下载进度的
     *
     * @param url                  路径
     * @param destDir              文件目录
     * @param fileName             文件名称
     * @param fileDownLoadObserver 文件监听
     */
    public void downloadFile(String url, String destDir, String fileName, FileDownLoadObserver<File> fileDownLoadObserver) {
        downloadService.download(url).subscribeOn(Schedulers.io())//subscribeOn和ObserOn必须在io线程，如果在主线程会出错
                .observeOn(Schedulers.io())
                .observeOn(Schedulers.computation())//需要
                .map(new Function<ResponseBody, File>() {
                    @Override
                    public File apply(@NonNull ResponseBody responseBody) throws Exception {
                        return fileDownLoadObserver.saveFile(responseBody, destDir, fileName);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(fileDownLoadObserver);
    }
}
