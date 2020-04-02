package com.example.basemvvm.ui.activity;

import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.blankj.utilcode.util.ScreenUtils;
import com.example.basemvvm.BR;
import com.example.basemvvm.R;
import com.example.basemvvm.base.activity.BaseMVVMActivity;
import com.example.basemvvm.bean.UpdateBean;
import com.example.basemvvm.databinding.ActivityDialogDownApkBinding;
import com.example.basemvvm.mvvm.view_model.DownloadAPKVM;

/**
 * author: wtg
 * date:2020/4/2 0002
 * desc: 更新app的activity
 */
public class AppUpdateActivity extends BaseMVVMActivity<ActivityDialogDownApkBinding, DownloadAPKVM> {
    private UpdateBean updateBean;

    @Override
    protected void initView() {
        updateBean = (UpdateBean) getIntent().getSerializableExtra("update");
        if (updateBean == null) {
            finish();
            return;
        }
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.width = (int) (ScreenUtils.getScreenWidth() * 0.85);
        params.gravity = Gravity.CENTER;
        getWindow().setAttributes(params);
        viewModel.setUpdateBean(updateBean);
        this.setFinishOnTouchOutside(updateBean.getUpdateType() != 1);
    }

    @Override
    protected DownloadAPKVM getViewModel() {
        return new DownloadAPKVM(this);
    }

    @Override
    protected int getViewModelId() {
        return BR.downloadVm;
    }

    @Override
    protected int getEdgeTrackingEnabled() {
        return -1;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_dialog_down_apk;
    }

    @Override
    public void onBackPressed() {
        if (updateBean == null || updateBean.getUpdateType() != 1) {
            super.onBackPressed();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }
}
