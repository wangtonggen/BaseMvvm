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

import com.example.basemvvm.mvvm.view_model_base.BaseViewModel;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import javax.annotation.Nonnull;

import io.reactivex.Observable;
import io.reactivex.ObservableOperator;
import io.reactivex.Observer;
import io.reactivex.subjects.BehaviorSubject;

/**
 * author: wtg
 * date:2020/3/12 0012
 * desc: fragment 基类
 */
public abstract class BaseMvvmFragment<B extends ViewDataBinding,VM extends BaseViewModel> extends Fragment {
    protected Context mContext;
    protected Activity mActivity;

    protected B binding;
    protected int viewModelId;
    protected VM viewModel;

    public final BehaviorSubject<FragmentEvent> lifecycleSubject = BehaviorSubject.create();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
        this.mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,getLayoutRes(),container,false);
        initView(savedInstanceState,binding.getRoot());
        return binding.getRoot();
    }


    @Override
    public void onStart() {
        super.onStart();
        lifecycleSubject.onNext(FragmentEvent.START);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(FragmentEvent.CREATE);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lifecycleSubject.onNext(FragmentEvent.CREATE_VIEW);
        //view 创建之后
        viewModelId = getViewModelId();
        viewModel = getViewModel();
        refreshLayout();
    }

    @Override
    public void onResume() {
        super.onResume();
        lifecycleSubject.onNext(FragmentEvent.RESUME);
    }

    @Override
    public void onPause() {
        super.onPause();
        lifecycleSubject.onNext(FragmentEvent.PAUSE);
    }

    @Override
    public void onStop() {
        super.onStop();
        lifecycleSubject.onNext(FragmentEvent.STOP);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        lifecycleSubject.onNext(FragmentEvent.DESTROY_VIEW);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lifecycleSubject.onNext(FragmentEvent.DESTROY);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        lifecycleSubject.onNext(FragmentEvent.DETACH);
    }

    @Nonnull
    public Observable<FragmentEvent> lifecycle() {
        return lifecycleSubject.lift(new ObservableOperator<FragmentEvent, FragmentEvent>() {
            @Override
            public Observer<? super FragmentEvent> apply(Observer<? super FragmentEvent> observer) throws Exception {
                return observer;
            }
        });
    }

    @Nonnull
    public <T> LifecycleTransformer<T> bindUntilEvent(@Nonnull FragmentEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Nonnull
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindFragment(lifecycleSubject);
    }

    protected void initView(Bundle savedInstanceState,View rootView){

    }

    protected void refreshLayout(){
        if (viewModel != null){
            binding.setVariable(viewModelId,viewModel);
        }
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
