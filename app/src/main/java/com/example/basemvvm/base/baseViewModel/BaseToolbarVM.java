package com.example.basemvvm.base.baseViewModel;

import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableFloat;
import androidx.databinding.ObservableInt;

import com.example.basemvvm.R;
import com.example.basemvvm.base.activity.BaseActivity;
import com.example.basemvvm.utils.common.LogUtils;

/**
 * author: wtg
 * date:2020/4/3 0003
 * desc: toolbar的基类
 */
public class BaseToolbarVM extends BaseVM {
    public ObservableInt toolbarColor = new ObservableInt(R.color.colorPrimary);//toolbar 的颜色值
    public ObservableFloat toolbarAlpha = new ObservableFloat(1.0f);//toolbar 透明度
    public ObservableField<String> title = new ObservableField<>("hello word");//标题
    public ObservableFloat titleBgAlpha = new ObservableFloat(1.0f);//标题
    public ObservableInt titleColor = new ObservableInt(R.color.white);//标题的颜色值
    public ObservableInt titleTextSize = new ObservableInt(R.dimen.size_toolbar_text_title);//标题字体大小
    public ObservableBoolean titleShow = new ObservableBoolean(true);//标题是否显示 true 显示 false不显示
    public ObservableField<String> rightText = new ObservableField<>();//右边文本
    public ObservableInt rightTextSize = new ObservableInt(R.dimen.size_text_title_right);//右边文字大小
    public ObservableInt rightTextColor = new ObservableInt(R.color.color_title_right);//右边文字颜色
    public ObservableBoolean rightTextShow = new ObservableBoolean(false);//右边文本显示
    public ObservableInt rightImageResId = new ObservableInt();//右边图标资源文件
    public ObservableBoolean rightImageShow = new ObservableBoolean(false);//右侧图标是否显示
    public ObservableBoolean backNavigationShow = new ObservableBoolean(true);//导航按钮是否显示
    public ObservableInt backNavigationColor = new ObservableInt(R.color.white);//导航的颜色
    public ObservableInt backNavigationResId = new ObservableInt(R.drawable.ic_arrow_back_white);//导航按钮资源文件

    protected BaseActivity mActivity;

    public BaseToolbarVM(BaseActivity mActivity) {
        this.mActivity = mActivity;
        LogUtils.logE("tag", toolbarColor.get() + "---");
    }

    public void onBack(View view) {
        mActivity.onBackPressed();
    }
}
