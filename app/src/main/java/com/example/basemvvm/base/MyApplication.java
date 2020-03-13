package com.example.basemvvm.base;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;


/**
 * author: wtg
 * date:2020/3/12 0012
 * desc:
 */
public class MyApplication extends MultiDexApplication {
    public static Application instance;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
        instance = this;
    }
}
