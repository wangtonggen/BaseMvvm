package com.example.basemvvm.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.example.basemvvm.mvvm.view_model_base.BaseVM;

/**
 * author: wtg
 * date:2020/3/12 0012
 * desc: fragment 基类
 */
public abstract class BaseMVVMFragment<B extends ViewDataBinding,VM extends BaseVM> extends Fragment {
    protected String TAG = this.getClass().getSimpleName();
    protected Context mContext;
    protected Activity mActivity;

    protected B binding;
    protected int viewModelId;
    protected VM viewModel;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
        this.mActivity = getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            getBundleArgument(getArguments());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,getLayoutRes(),container,false);
        initView(savedInstanceState,binding.getRoot());
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //view 创建之后
        viewModelId = getViewModelId();
        viewModel = getViewModel();
        refreshLayout();
        getLifecycle().addObserver(viewModel);
    }

    protected void initView(Bundle savedInstanceState,View rootView){

    }

    protected void refreshLayout(){
        if (viewModel != null){
            binding.setVariable(viewModelId,viewModel);
        }
    }

    /**
     * 用newInstance创建 fragment 获取参数
     * @param bundle bundle
     */
    public void getBundleArgument(Bundle bundle){

    }

    @LayoutRes
    protected abstract int getLayoutRes();

    /**
     * 获取viewModel
     * @return viewModel
     */
    protected abstract VM getViewModel();

    /**
     * 获取BR的id
     * @return id
     */
    protected abstract int getViewModelId();
}
