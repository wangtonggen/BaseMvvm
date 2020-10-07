package com.example.basemvvm.base.app;

import android.content.Context;

import com.example.basemvvm.BuildConfig;
import com.example.basemvvm.R;
import com.example.basemvvm.widget.common.CustomLoadMoreView;
import com.wang.mvvmcore.base.app.BaseCoreApplication;
import com.wang.mvvmcore.crash.CrashHandlerUtils;
import com.wang.mvvmcore.network.api.ApiBaseUrl;
import com.wang.mvvmcore.utils.common.CoreLogUtils;

import butterknife.ButterKnife;

/**
 * author: wtg
 * date:2020/3/12 0012
 * desc:
 */
public class BaseApplication extends BaseCoreApplication {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //初始化 Retrofit2
//        initRetrofit();
        //初始化下拉刷新，上拉加载 的头部和尾部
        initSmartRefreshHeaderAndFooter(R.color.colorPrimary,new CustomLoadMoreView());
        //初始化全局carsh
        initCrash();

        ButterKnife.setDebug(BuildConfig.DEBUG);
        //http://wthrcdn.etouch.cn/weather_mini?citykey=101180301
        ApiBaseUrl.URL_BASE = "http://192.168.10.139:8888/springboot/";
        initRetrofit();
        CoreLogUtils.setIsPrintLog(true);
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected void initCrash() {
        CrashHandlerUtils.getInstance().setUploadListener((crashFile, crashInfo) -> {
            //上传或者处理异常信息
        }).init();
    }
}
