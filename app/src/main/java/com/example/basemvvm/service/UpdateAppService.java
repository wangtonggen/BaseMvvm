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
import com.blankj.utilcode.util.SDCardUtils;
import com.example.basemvvm.R;
import com.example.basemvvm.base.BaseApplication;
import com.example.basemvvm.network.model.DownloadModel;
import com.example.basemvvm.network.network_base.FileDownLoadObserver;
import com.example.basemvvm.utils.common_utils.LogUtils;

import java.io.File;

/**
 * author: wtg
 * date:2020/3/19 0019
 * desc: 更新app的service
 */
public class UpdateAppService extends Service {
    private static final int NOTIFICATION_ID = 0;
    private NotificationCompat.Builder mBuilder;
    private Notification mNotification;
    private NotificationManager mNotificationManager;

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
            String dir = SDCardUtils.getSDCardPathByEnvironment() + File.separator + "luyuan";
//            LogUtils.logE("dir", dir);
            FileUtils.createOrExistsDir(dir);
            String url = "http://imtt.dd.qq.com/16891/E4E087B63E27B87175F4B9BC7CFC4997.apk?fsname=com.tencent.qlauncher_6.0.2_64170111.apk&csr=97c2";
            DownloadModel.getInstance().downloadFile(url, dir, "qq.apk", new FileDownLoadObserver<File>() {
                @Override
                public void onDownLoadSuccess(File file) {
                    AppUtils.installApp(file);
                    stopSelf();
//                    LogUtils.logE("onDownLoadSuccess", "成功");
                }

                @Override
                public void onDownLoadFail(Throwable throwable) {
//                    LogUtils.logE("onDownLoadFail", "失败");
                }

                @Override
                public void onProgress(int progress, long total) {
                    notifyMsg(progress);
//                    LogUtils.logE("progress", progress + "---" + total);
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
        String channelId = "channelId";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence channelName = "channelName";
            String channelDescription = "channelDescription";
            int channelImportance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, channelImportance);
            // 设置描述 最长30字符
            notificationChannel.setDescription(channelDescription);
            // 该渠道的通知是否使用震动
            notificationChannel.enableVibration(true);
            // 设置显示模式
            notificationChannel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PUBLIC);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        return channelId;
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
        cancelNotify();
    }
}
