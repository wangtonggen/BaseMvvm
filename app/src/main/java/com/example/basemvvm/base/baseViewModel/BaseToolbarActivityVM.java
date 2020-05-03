package com.example.basemvvm.base.baseViewModel;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.example.basemvvm.R;
import com.example.basemvvm.base.activity.BaseActivity;

/**
 * author: wtg
 * date:2020/4/3 0003
 * desc: toolbar的基类
 */
public class BaseToolbarActivityVM extends BaseActivityLifecycleVM {
    public ObservableInt toolbarColor = new ObservableInt(R.color.colorAccent);//toolbar 的颜色值
    public ObservableField<String> title = new ObservableField<>("hello word");//标题
    public ObservableInt titleColor = new ObservableInt(R.color.color_title);//标题的颜色值
    public ObservableInt titleTextSize = new ObservableInt(R.dimen.size_toolbar_text_title);//标题字体大小
    public ObservableBoolean titleShow = new ObservableBoolean(true);//标题是否显示 true 显示 false不显示
    public ObservableField<String> rightText = new ObservableField<>();//右边文本
    public ObservableInt rightTextSize = new ObservableInt(R.dimen.size_text_title_right);//右边文字大小
    public ObservableInt rightTextColor = new ObservableInt(R.color.color_title_right);//右边文字颜色
    public ObservableBoolean rightTextShow = new ObservableBoolean(false);//右边文本显示
    public ObservableInt rightImageResId = new ObservableInt();//右边图标资源文件
    public ObservableBoolean rightImageShow = new ObservableBoolean(false);//右侧图标是否显示
    public ObservableBoolean backNavigationShow = new ObservableBoolean(true);//导航按钮是否显示
    public ObservableInt backNavigationResId = new ObservableInt(R.drawable.ic_navigate_before_black_24dp);//导航按钮资源文件

    public BaseToolbarActivityVM(BaseActivity mActivity) {
        super(mActivity);
    }
}
