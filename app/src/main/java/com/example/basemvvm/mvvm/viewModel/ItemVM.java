package com.example.basemvvm.mvvm.viewModel;

import androidx.databinding.ObservableInt;

import com.example.basemvvm.R;

/**
 * author: wtg
 * date:2020/3/23 0023
 * desc:
 */
public class ItemVM {
    public final ObservableInt textColor = new ObservableInt(R.color.colorPrimary);
    public final ObservableInt imageRes = new ObservableInt(R.mipmap.ic_bk_instruct_power_s);

    public ItemVM() {
        textColor.set(R.color.colorAccent);
        imageRes.set(R.mipmap.ic_bk_instruct_power);
    }
}
