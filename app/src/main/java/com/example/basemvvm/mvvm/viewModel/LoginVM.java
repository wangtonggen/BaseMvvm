package com.example.basemvvm.mvvm.viewModel;

import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;

import androidx.core.util.Pair;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.basemvvm.R;
import com.example.basemvvm.bean.HttpResponse;
import com.example.basemvvm.bean.LoginBean;
import com.example.basemvvm.constant.IntentFilterConstant;
import com.example.basemvvm.network.base.BaseObserver;
import com.example.basemvvm.network.model.UserModel;
import com.example.basemvvm.ui.activity.UserInfoActivity;
import com.example.basemvvm.utils.common.MyUserSPUtils;
import com.wang.mvvmcore.base.activity.BaseActivity;
import com.wang.mvvmcore.base.activity.BaseMVVMActivity;
import com.wang.mvvmcore.base.baseViewModel.BaseActivityLifecycleVM;
import com.wang.mvvmcore.base.baseViewModel.BaseToolbarVM;
import com.wang.mvvmcore.utils.anim.TransitionAnimationUtils;
import com.wang.mvvmcore.utils.common.CountDownUtils;
import com.wang.mvvmcore.utils.common.LogUtils;
import com.wang.mvvmcore.widget.SimpleTextWatcher;

import io.reactivex.observers.DefaultObserver;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * 登录的viewModel
 */
public class LoginVM extends BaseActivityLifecycleVM {
    public ObservableField<String> str_mobile = new ObservableField<>();
    public ObservableField<String> str_code = new ObservableField<>();
    public ObservableBoolean btnLoginEnabled = new ObservableBoolean(false);
    public ObservableBoolean btnCodeEnabled = new ObservableBoolean(true);
    public ObservableField<String> btnCodeText = new ObservableField<>("获取验证码");

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

    public LoginVM(BaseActivity mActivity) {
        super(mActivity);
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
            ToastUtils.showShort("发送成功,请注意查收");
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
        UserModel.getInstance().login(str_mobile.get(), str_code.get(), new BaseObserver<LoginBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(HttpResponse<LoginBean> data) {
                MyUserSPUtils.setIsLogin(true);
                str_mobile.set("15727960192");
                str_code.set("789456");
                MyUserSPUtils.setUserName("奔跑的一毛一");
                MyUserSPUtils.setHeadUrl("http://g.hiphotos.baidu.com/image/pic/item/6d81800a19d8bc3e770bd00d868ba61ea9d345f2.jpg");
                LogUtils.logE("login",data.getCode()+"---"+data.getMsg()+"---"+data.getData().getMsg()+"---"+data.getData().getUserName());
            }

            @Override
            public void onComplete() {
                super.onComplete();
                LogUtils.logE("onComplete");
                closeLoadingDialog();
            }

            @Override
            public void onFail(Throwable e) {
                super.onFail(e);
                LogUtils.logE("onFail");
                closeLoadingDialog();
            }
        });
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
