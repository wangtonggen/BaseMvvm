package com.example.basemvvm.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.basemvvm.bean.UpdateBean;
import com.example.basemvvm.ui.activity.AppUpdateActivity;

/**
 * author: wtg
 * date:2020/3/19 0019
 * desc: 更新app的service
 */
public class UpdateAppService extends Service {

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
        //先请求接口判断是否需要更新 把更新的类型，地址传给下载的activity
        Intent intent1 = new Intent(this, AppUpdateActivity.class);
        UpdateBean updateBean = new UpdateBean();
        String url = "http://imtt.dd.qq.com/16891/E4E087B63E27B87175F4B9BC7CFC4997.apk?fsname=com.tencent.qlauncher_6.0.2_64170111.apk&csr=97c2";
        updateBean.setDownloadUrl(url);
        updateBean.setNewVersionCode(2);
        updateBean.setNewVersionName("1.0.1");
        updateBean.setUpdateType(1);
        updateBean.setDesc("1.更新内容1\n2.更新内容2\n3.更新内容3\n4.更新内容4\n5.更新内容5");
        intent1.putExtra("update",updateBean);
        startActivity(intent1);
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }
}
