package com.example.basemvvm.mvvm.view_model;

import android.content.Intent;
import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.example.basemvvm.R;
import com.example.basemvvm.base.activity.BaseMVVMActivity;
import com.example.basemvvm.bean.HttpResponse;
import com.example.basemvvm.bean.LoginBean;
import com.example.basemvvm.mvvm.view_model_base.ToolBarActivityVM;
import com.example.basemvvm.network.model.UserModel;
import com.example.basemvvm.network.network_base.BaseObserver;
import com.example.basemvvm.ui.activity.TestActivity;
import com.example.basemvvm.utils.common_utils.LogUtils;
import com.example.basemvvm.utils.common_utils.ToastUtils;
import com.example.basemvvm.utils.vm_utils.refresh.BindingCommand;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import io.reactivex.disposables.Disposable;

/**
 * 登录的viewModel
 */
public class LoginVM extends ToolBarActivityVM {
    public final ObservableField<String> str_mobile = new ObservableField<>();
    public final ObservableField<String> str_code = new ObservableField<>();

    public final ObservableBoolean focus = new ObservableBoolean(true);

    private String[] mobiles = new String[]{"13735701398", "15727960191", "15727960192", "15727960193"};
    private String[] codes = new String[]{"25485", "12548", "87541", "98652"};

    public BindingCommand onRefreshCommand = new BindingCommand(() -> {
        //下拉刷新
        ToastUtils.showShortToast("下拉刷新");
        //加载数据
    });

    public BindingCommand onLoadMoreCommand = new BindingCommand(() -> {
        //上拉加载更多
        ToastUtils.showShortToast("上拉加载");
        //加载数据
    });

    public OnRefreshListener onRefreshListener = refreshLayout -> {
        ToastUtils.showShortToast("我是刷新");
        refreshLayout.finishRefresh(2000);
    };

    public OnLoadMoreListener onLoadMoreListener = refreshLayout -> {
        ToastUtils.showShortToast("我是加载");
        refreshLayout.finishLoadMore(2000);
    };

    public LoginVM(BaseMVVMActivity mActivity) {
        super(mActivity);
    }

    public void login() {
        UserModel.getInstance().login(str_mobile.get(), str_code.get(), new BaseObserver<LoginBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                super.onSubscribe(d);
                addDisposable(d);
            }

            @Override
            public void onSuccess(HttpResponse<LoginBean> data) {
                LogUtils.logE(data.getCode() + "---" + data.getMsg());
            }
        });
    }

    private void getCode() {
        UserModel.getInstance().sendCode(str_mobile.get(), new BaseObserver<String>() {
            @Override
            public void onSuccess(HttpResponse<String> data) {
                LogUtils.logE(data.getCode() + "---" + data.getMsg());
            }
        });
    }

    @Override
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_code:
                getCode();
                break;
            case R.id.tv:
                mActivity.startActivity(new Intent(mActivity, TestActivity.class));
                break;
        }
    }
}
