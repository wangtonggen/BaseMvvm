package com.example.basemvvm.mvvm.view_model_base;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.example.basemvvm.R;
import com.example.basemvvm.base.BaseMvvmActivity;

/**
 * author: wtg
 * date:2020/3/13 0013
 * desc:
 */
public class ToolBarActivityVM extends BaseActivityVM {
    public final ObservableInt toolbarColor = new ObservableInt(R.color.colorAccent);//toolbar 的颜色值
    public final ObservableField<String> title = new ObservableField<>("hello word");//标题
    public final ObservableInt titleColor = new ObservableInt(R.color.color_title);//标题的颜色值
    public final ObservableInt titleTextSize = new ObservableInt(R.dimen.size_text_title);//标题字体大小
    public final ObservableBoolean titleShow = new ObservableBoolean(true);//标题是否显示 true 显示 false不显示
    public final ObservableField<String> rightText = new ObservableField<>();//右边文本
    public final ObservableInt rightTextSize = new ObservableInt(R.dimen.size_text_title_right);//右边文字大小
    public final ObservableInt rightTextColor = new ObservableInt(R.color.color_title_right);//右边文字颜色
    public final ObservableBoolean rightTextShow = new ObservableBoolean(false);//右边文本显示
    public final ObservableInt rightImageResId = new ObservableInt();//右边图标资源文件
    public final ObservableBoolean rightImageShow = new ObservableBoolean(false);//右侧图标是否显示
    public final ObservableBoolean backNavigationShow = new ObservableBoolean(true);//导航按钮是否显示
    public final ObservableInt backNavigationResId = new ObservableInt(R.drawable.ic_navigate_before_black_24dp);//导航按钮资源文件
    public ToolBarActivityVM toolBarActivityViewModel;
    public ToolBarActivityVM(BaseMvvmActivity mActivity) {
        super(mActivity);

        toolBarActivityViewModel = this;
    }
}
