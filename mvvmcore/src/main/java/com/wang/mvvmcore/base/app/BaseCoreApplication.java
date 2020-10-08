package com.wang.mvvmcore.base.app;

import android.app.Application;
import android.content.Context;

import androidx.annotation.ColorRes;
import androidx.multidex.MultiDexApplication;

import com.chad.library.adapter.base.loadmore.BaseLoadMoreView;
import com.chad.library.adapter.base.module.LoadMoreModuleConfig;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.wang.mvvmcore.crash.CrashHandlerUtils;
import com.wang.mvvmcore.network.networkBase.RetrofitManager;

import static com.wang.mvvmcore.network.api.ApiBaseUrl.URL_BASE;

/**
 * author: wtg
 * date:2020/3/12 0012
 * desc:
 */
public class BaseCoreApplication extends MultiDexApplication {
    public static Application instance;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        instance = this;
    }

    /**
     * 初始化 Retrofit2
     */
    protected void initRetrofit() {
        RetrofitManager.getInstance().setBaseUrl(URL_BASE).init();
    }

    /**
     * 全局初始化下拉刷新上拉加载的头部和尾部
     * @param primaryColorsId 下拉的颜色
     * @param loadMoreView 加载更多的view
     */
    protected void initSmartRefreshHeaderAndFooter(@ColorRes int primaryColorsId, BaseLoadMoreView loadMoreView) {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            layout.setPrimaryColorsId(primaryColorsId, android.R.color.white);//全局设置主题颜色
            return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
        });
        /*//设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setDrawableSize(20);
        });*/

        LoadMoreModuleConfig.setDefLoadMoreView(loadMoreView);
    }

    /**
     * 初始化闪退拦截
     */
    protected void initCrash(){
        CrashHandlerUtils.getInstance().init();
    }
}
