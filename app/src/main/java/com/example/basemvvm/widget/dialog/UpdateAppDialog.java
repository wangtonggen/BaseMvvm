package com.example.basemvvm.widget.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;

import com.example.basemvvm.R;

/**
 * author: wtg
 * date:2020/3/19 0019
 * desc: 更新的dialog
 */
public class UpdateAppDialog extends AlertDialog {
    public UpdateAppDialog(Context context) {
        super(context);
        initView();
    }

    public UpdateAppDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }

    public UpdateAppDialog(Context context, int themeResId) {
        super(context, themeResId);
        initView();
    }

    /**
     * 初始化
     */
    @SuppressLint("ResourceType")
    private void initView() {
        setContentView(R.layout.dialog_update_app);
    }
}
