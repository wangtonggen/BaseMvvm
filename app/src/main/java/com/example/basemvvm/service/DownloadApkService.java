package com.example.basemvvm.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SDCardUtils;
import com.example.basemvvm.R;
import com.example.basemvvm.base.app.BaseApplication;
import com.example.basemvvm.bean.UpdateBean;
import com.example.basemvvm.network.downloadAndUpload.download.DownLoadUtils;
import com.example.basemvvm.network.downloadAndUpload.download.DownloadResponseBody;
import com.example.basemvvm.utils.common.MyFileUtils;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static com.example.basemvvm.constant.FileConstant.DIR_APP;
import static com.example.basemvvm.constant.NotificationConstant.DOWNLOAD_APK_CHANNEL_ID;
import static com.example.basemvvm.constant.NotificationConstant.NOTIFICATION_ID;

/**
 * author: wtg
 * date:2020/3/19 0019
 * desc: 下载APK的service
 */
public class DownloadApkService extends Service {
    private NotificationCompat.Builder mBuilder;
    private Notification mNotification;
    private NotificationManager mNotificationManager;
    private Call call;
    private UpdateBean updateBean;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            return super.onStartCommand(intent, flags, startId);
        }
        updateBean = (UpdateBean) intent.getSerializableExtra("update");
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        initNotify();
        notifyMsg(0);
        download();
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 下载
     */
    private void download() {
        if (SDCardUtils.isSDCardEnableByEnvironment()) {//sdk卡是否可用
            FileUtils.createOrExistsDir(DIR_APP);
            call = DownLoadUtils.download(updateBean.getDownloadUrl(), new DownloadResponseBody.DownloadListener() {
                @Override
                public void onStartDownload(long length) {
                    LogUtils.e("onStartDownload=" + length);
                }

                @Override
                public void onProgress(long progress, long total, boolean done) {
                    LogUtils.e("onProgress=" + progress + "---total=" + total);
                    notifyMsg((int) (progress * 100 / total));
                }

                @Override
                public void onFail(String errorInfo) {
                    LogUtils.e("onFail=" + errorInfo);
                }
            }, new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    LogUtils.e("onFailure=" + e.getMessage());
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) {
                    LogUtils.e("onResponse=" + response.message());
                    ResponseBody responseBody = response.body();
                    if (responseBody != null) {
                        File file = MyFileUtils.saveFile(responseBody.byteStream(), DIR_APP, "hello.apk");
                        AppUtils.installApp(file);
                        stopSelf();
                    }
                }
            });
        }
    }


    /**
     * 初始化通知栏
     */
    private void initNotify() {
        mBuilder = new NotificationCompat.Builder(BaseApplication.instance, createNotificationChannel());
        mBuilder.setContentTitle("正在更新...") //设置通知标题
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round)) //设置通知的大图标
                .setDefaults(Notification.FLAG_ONLY_ALERT_ONCE) //设置通知的提醒方式： 呼吸灯
                .setOnlyAlertOnce(true)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(false)//设置通知被点击一次是否自动取消
                .setSound(null)
                .setVibrate(new long[]{0})
                .setContentText("下载进度:" + "0%")
                .setProgress(100, 0, false);
        mNotification = mBuilder.build();
    }

    /**
     * 创建 channelId
     *
     * @return channelId
     */
    private String createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence channelName = "channelName";
            String channelDescription = "channelDescription";
            int channelImportance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(DOWNLOAD_APK_CHANNEL_ID, channelName, channelImportance);
            // 设置描述 最长30字符
            notificationChannel.setDescription(channelDescription);
            // 该渠道的通知是否使用震动
            notificationChannel.enableVibration(true);
            // 设置显示模式
            notificationChannel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PUBLIC);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        return DOWNLOAD_APK_CHANNEL_ID;
    }

    /**
     * 通知栏进行通知
     *
     * @param progress 下载的进度
     */
    private void notifyMsg(int progress) {
        mBuilder.setProgress(100, progress, false);
        mBuilder.setContentText("下载进度:" + progress + "%");
        mNotification = mBuilder.build();
        mNotificationManager.notify(NOTIFICATION_ID, mNotification);
    }

    /**
     * 取消通知
     */
    private void cancelNotify() {
        if (mNotificationManager != null) {
            mNotificationManager.cancel(NOTIFICATION_ID);
            mNotificationManager = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (call != null) {
            call.cancel();
        }
        cancelNotify();
    }
}
