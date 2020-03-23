package com.example.basemvvm.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

/**
 * author: wtg
 * date:2020/3/23 0023
 * desc: 获取基本配置的service
 */
public class ConfigService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //开始请求 基本配置数据
        getConfigData();
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 获取基本配置的data
     */
    private void getConfigData(){
        //完成后 停止服务
        stopSelf();
    }
}
