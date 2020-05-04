package com.example.basemvvm.mvvm.viewModel;

import android.os.Handler;
import android.view.View;

import androidx.databinding.ObservableField;

import com.example.basemvvm.R;
import com.example.basemvvm.base.activity.BaseMVVMActivity;
import com.example.basemvvm.bean.HttpResponse;
import com.example.basemvvm.bean.LoginBean;
import com.example.basemvvm.base.baseViewModel.BaseToolbarActivityVM;
import com.example.basemvvm.network.model.UserModel;
import com.example.basemvvm.network.networkBase.BaseObserver;

import io.reactivex.disposables.Disposable;

/**
 * 登录的viewModel
 */
public class LoginVM extends BaseToolbarActivityVM {
    public final ObservableField<String> str_mobile = new ObservableField<>();
    public final ObservableField<String> str_code = new ObservableField<>();

    public LoginVM(BaseMVVMActivity mActivity) {
        super(mActivity);

        toolbarColor.set(R.color.white);
//        titleColor.set(R.color.white);
        backNavigationShow.set(false);
        title.set("登录");
    }

    public void login(View view) {
        showLoadingDialog("");
        new Handler().postDelayed(this::closeLoadingDialog,3000);
//        UserModel.getInstance().login(str_mobile.get(), str_code.get(), new BaseObserver<LoginBean>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                super.onSubscribe(d);
//                addDisposable(d);
//            }
//
//            @Override
//            public void onSuccess(HttpResponse<LoginBean> data) {
//            }
//        });
    }

    private void getCode() {
        UserModel.getInstance().sendCode(str_mobile.get(), new BaseObserver<String>() {
            @Override
            public void onSuccess(HttpResponse<String> data) {
//                LogUtils.logE(data.getCode() + "---" + data.getMsg());
            }
        });
    }

    @Override
    public void onViewClick(View view) {

    }
}
