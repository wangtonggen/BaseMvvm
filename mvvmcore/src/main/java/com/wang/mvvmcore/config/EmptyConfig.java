package com.wang.mvvmcore.config;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;

import com.wang.mvvmcore.R;
import com.wang.mvvmcore.base.app.BaseCoreApplication;

/**
 * author: wtg
 * date:2020/5/25 0025
 * desc: 列表为空时的的view 设置
 */
public class EmptyConfig {
    @SuppressLint("StaticFieldLeak")
    public static View emptyView  = LayoutInflater.from(BaseCoreApplication.instance).inflate(R.layout.view_default_recycler_empty, null);

    /**
     * 设置全局的空布局
     * @param view view
     */
    public void setEmptyView(View view){
        emptyView = view;
    }

    /**
     * 获取空布局
     * @return view
     */
    @SuppressLint("InflateParams")
    public View getEmptyView(){
        if (emptyView == null){//模式添加空布局
            emptyView = LayoutInflater.from(BaseCoreApplication.instance).inflate(R.layout.view_default_recycler_empty, null);
        }
        return emptyView;
    }
}
