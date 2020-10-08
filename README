# 描述
该库使用的mvvm+rxjava2+retrofit2+okhttp3+glide架构模式 可以很方便的使用

# 使用
## 项目的gradle
---
```
allprojects {  
    repositories {  
    google()  
    jcenter()  
    maven {url 'https://jitpack.io' }  
}
```
## App里面的gradle添加依赖
---
```
implementation 'com.gitee.wangdachui:BaseMvvm:1.1.4'
//添加butterknife
implementation'com.jakewharton:butterknife:10.2.3'  
annotationProcessor'com.jakewharton:butterknife-compiler:10.2.3'
```
## activity的继承
---
```
// 使用mvvm创建activity BaseSwipeBackLeftActivity 是右划关闭activity的基类，不使用侧滑删除需要重写
// getEdgeTrackingEnabled方法并将返回值设置为 SwipeConstant.SWIPE_NONE
public class ProviderActivity extends BaseSwipeBackLeftActivity<ActivityProviderBinding, ProviderVM> {
    @Override
    protected ProviderVM getViewModel() {
        return new ProviderVM(this);
    }

    @Override
    protected int getViewModelId() {
        return BR.providerVM;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_provider;
    }
}
// 不使用mvvm创建activity BaseNoMVVMActivity 是右划关闭activity的基类，不使用侧滑删除需要重写
// getEdgeTrackingEnabled方法并将返回值设置为 SwipeConstant.SWIPE_NONE 并且默认添加Butterknife的注解绑定
 public class TestActivity extends BaseNoMVVMActivity {
      @Override
      protected int getLayoutRes() {
          return R.layout.activity_test;
      }
  
      @Override
      protected void initView() {
  
      }
  
      @OnClick({R.id.tv_main,R.id.tv_delegate_multi,R.id.tv_multi,R.id.tv_provider,R.id.tv_single,R.id.tv_binder})
      public void onViewClicked(View view) {
          switch (view.getId()){
              case R.id.tv_main:
                  startActivity(new Intent(this,MainActivity.class));
                  break;
              case R.id.tv_delegate_multi://多布局条目 delegate方式
                  startActivity(new Intent(this,DelegateActivity.class));
                  break;
              case R.id.tv_multi://多布局条目 普通方式
                  startActivity(new Intent(this, MultiActivity.class));
                  break;
              case R.id.tv_provider://多布局条目 provider方式
                  startActivity(new Intent(this,ProviderActivity.class));
                  break;
              case R.id.tv_binder://多布局 binder方式
                  startActivity(new Intent(this,BinderActivity.class));
                  break;
              case R.id.tv_single:// 单布局
                  startActivity(new Intent(this,SingleActivity.class));
                  break;
          }
      }
 }
```
## fragment的继承
---

```
// 使用MVVM的fragment的基类 默认采用懒加载方式加载数据
public class DashboardFragment extends BaseMVVMFragment<FragmentDashboardBinding, DashboardVM> {
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_dashboard;
    }

    @Override
    protected DashboardVM getViewModel() {
        return new DashboardVM(this);
    }

    @Override
    protected int getViewModelId() {
        return BR.dashboardVM;
    }

    @Override
    public void onLazyLoadData() {
//        LogUtils.logE(TAG, "DashboardFragment");
    }
}

// 不使用mvvm的fragment的基类如果需要使用懒加载，则继承BaseLazyLoadFragment 不使用懒加载继承BaseFragment
public abstract class BaseNoMVVMFragment extends BaseLazyLoadFragment {
    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = LayoutInflater.from(mContext).inflate(getLayoutRes(), container, false);
        ButterKnife.bind(rootView);
        initView(savedInstanceState, rootView);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
```
## recyclerView的adapter的使用
---

所有的adapter的基类都是基于[BaseRecyclerViewAdapterHelper](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)来二次封装的
并且在里面加入了默认的空列表的显示信息
adapter分为两部分
1. 单布局
    - 使用MVVM
    - 不使用MVVM
2. 多布局
    - 使用MMVM
    - 不使用MVVM




