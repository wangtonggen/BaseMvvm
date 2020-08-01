package com.example.basemvvm.mvvm.viewModel;

import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.example.basemvvm.R;
import com.example.basemvvm.bean.HttpResponse;
import com.example.basemvvm.constant.IntentFilterConstant;
import com.example.basemvvm.network.base.BaseObserver;
import com.example.basemvvm.network.model.UserModel;
import com.example.basemvvm.utils.common.MyUserSPUtils;
import com.wang.mvvmcore.base.activity.BaseMVVMActivity;
import com.wang.mvvmcore.base.baseViewModel.BaseActivityLifecycleVM;
import com.wang.mvvmcore.base.baseViewModel.BaseToolbarVM;
import com.wang.mvvmcore.utils.common.CountDownUtils;
import com.wang.mvvmcore.widget.SimpleTextWatcher;

import io.reactivex.observers.DefaultObserver;

/**
 * 登录的viewModel
 */
public class LoginVM extends BaseActivityLifecycleVM {
    public ObservableField<String> str_mobile = new ObservableField<>();
    public ObservableField<String> str_code = new ObservableField<>();
    public ObservableBoolean btnLoginEnabled = new ObservableBoolean(false);
    public ObservableBoolean btnCodeEnabled = new ObservableBoolean(true);
    public ObservableField<String> btnCodeText = new ObservableField<>("获取验证码");

    public BaseToolbarVM baseToolbarVM;
    public SimpleTextWatcher accountTextWatcher = new SimpleTextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            btnLoginEnabled.set(!TextUtils.isEmpty(s) && !TextUtils.isEmpty(str_code.get()));
        }
    };
    public SimpleTextWatcher codeTextWatcher = new SimpleTextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            btnLoginEnabled.set(!TextUtils.isEmpty(str_mobile.get()) && !TextUtils.isEmpty(str_code.get()));
        }
    };

    public LoginVM(BaseMVVMActivity mActivity) {
        super(mActivity);

        baseToolbarVM = new BaseToolbarVM(mActivity);
        baseToolbarVM.title.set("登录");
        baseToolbarVM.toolbarColor.set(R.color.white);
        baseToolbarVM.titleColor.set(R.color.color_title);
        baseToolbarVM.backNavigationResId.set(R.drawable.ic_arrow_back);
    }

    /**
     * 获取验证码
     *
     * @param view view
     */
    public void getCode(View view) {
        showLoadingDialog("获取中");
        new Handler().postDelayed(() -> {
            closeLoadingDialog();
            ToastUtils.showShortToast("发送成功,请注意查收");
            btnCodeEnabled.set(false);
            btnCodeText.set("60s");
            CountDownUtils.countDown(1L, 60L, new DefaultObserver<Long>() {
                @Override
                public void onNext(Long aLong) {
                    btnCodeText.set(aLong + "s");
                }

                @Override
                public void onError(Throwable e) {
                    btnCodeText.set("获取验证码");
                    btnCodeEnabled.set(true);
                }

                @Override
                public void onComplete() {
                    cancel();
                    btnCodeText.set("获取验证码");
                    btnCodeEnabled.set(true);
                }
            });
            //倒计时
        }, 1000);
    }

    /**
     * 登录
     *
     * @param view view
     */
    public void login(View view) {
        showLoadingDialog("登录中");
        new Handler().postDelayed(() -> {
            closeLoadingDialog();
            MyUserSPUtils.setIsLogin(true);
            MyUserSPUtils.setUserName("奔跑的一毛一");
            MyUserSPUtils.setUserMobile(str_mobile.get());
            String path = "http://e.hiphotos.baidu.com/image/pic/item/4e4a20a4462309f7e41f5cfe760e0cf3d6cad6ee.jpg";
            MyUserSPUtils.setHeadUrl(path);
            mActivity.sendBroadcast(new Intent(IntentFilterConstant.UPDATE_LOGIN_ACTION));
            mActivity.finish();
        }, 3000);
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
