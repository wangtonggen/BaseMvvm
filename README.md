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
implementation 'com.gitee.wangdachui:BaseMvvm:1.1.6'
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
        - [BaseBindingSingleAdapter](com.wang.mvvmcore.adapter.singleAdapter.BaseSingleAdapter)
        示例：
        ```
        public class SingleAdapter extends BaseBindingSingleAdapter<NotificationBean,RecyclerItemNotificationBinding> {
        
            public SingleAdapter(int layoutResId) {
                super(layoutResId);
            }
        
            @Override
            public void bindData(@NotNull BaseDataBindingHolder<RecyclerItemNotificationBinding> vh, @NotNull RecyclerItemNotificationBinding viewDataBinding, NotificationBean notificationBean) {
                viewDataBinding.setNotificationItemVM(new NotificationItemVM(notificationBean));
            }
        
        }
        ```
    - 不使用MVVM
        - [BaseSingleAdapter](com.wang.mvvmcore.adapter.singleAdapter.BaseSingleAdapter)
        示例：
        ```
        public class SingleNoMvvmAdapter extends BaseSingleAdapter<NotificationBean, BaseViewHolder> {
            public SingleNoMvvmAdapter(int layoutResId) {
                super(layoutResId);
            }
        
            @Override
            protected void convert(@NotNull BaseViewHolder baseViewHolder, NotificationBean notificationBean) {
                baseViewHolder.setText(R.id.tv_name,notificationBean.name);
            }
        }
      ```
2. 多布局
    - 使用MMVM
        - [BaseBindingBinder](com.wang.mvvmcore.adapter.multi.binder.BaseBindingBinder)
        示例：
        ```
        public class UserItemBinder extends BaseBindingBinder<NotificationBean, RecyclerItemNotificationBinding> {
        
            @Override
            public void setData(RecyclerItemNotificationBinding viewDataBinding, NotificationBean notificationBean) {
                viewDataBinding.setNotificationItemVM(new NotificationItemVM(notificationBean));
            }
        
            @NotNull
            @Override
            public RecyclerItemNotificationBinding onCreateViewBinding(@NotNull LayoutInflater layoutInflater, @NotNull ViewGroup viewGroup, int i) {
                return RecyclerItemNotificationBinding.inflate(layoutInflater,viewGroup,false);
            }
        }
      ```
        - [BaseBindingProvider](com.wang.mvvmcore.adapter.multi.provider.BaseBindingProvider)
        示例：
        ```
        public class Home01Provider extends BaseBindingProvider<MultiItemBean, RecyclerItemHome01Binding> {
            @Override
            protected void bindData(@NotNull BaseViewHolder viewHolder, @NotNull RecyclerItemHome01Binding viewDataBinding, MultiItemBean multiItemBean) {
                viewDataBinding.setMultiItemBean(multiItemBean);
            }
        
            @Override
            public int getItemViewType() {
                return 0;
            }
        
            @Override
            public int getLayoutId() {
                return R.layout.recycler_item_home01;
            }
        }
      ```
    - 不使用MVVM
        - [BaseBinder](com.wang.mvvmcore.adapter.multi.binder.BaseBinder)
        示例：
        ```
        public class ImageNoMvvmBinder extends BaseBinder<UserBean> {
            @Override
            public int getLayoutId() {
                return R.layout.recycler_item_user_nomvvm;
            }
        
            @Override
            public void convert(@NotNull BaseViewHolder baseViewHolder, UserBean userBean) {
                Glide.with(getContext()).load(userBean.getUrl()).into((CircleImageView)baseViewHolder.getView(R.id.iv_image));
            }
        }
      ```
        - [BaseProvider](com.wang.mvvmcore.adapter.multi.provider.BaseProvider)
        示例：
        ```
        public class HomeBindingMultiAdapter extends BaseProviderAdapter<MultiItemBean> {
        
            @Override
            protected void addItemTypeProvider() {
                addItemProvider(new HomeProvider());
                addItemProvider(new Home01Provider());
            }
        }
      ```
## Application
---
```
//依赖项目需要继承 BaseCoreApplication 需要修改的地方重写相应的方法
public class BaseApplication extends BaseCoreApplication {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //初始化 Retrofit2
//        initRetrofit();
        //初始化下拉刷新，上拉加载 的头部和尾部
        initSmartRefreshHeaderAndFooter(R.color.colorPrimary,new CustomLoadMoreView());
        //初始化全局carsh
        initCrash();

        ButterKnife.setDebug(BuildConfig.DEBUG);
        //http://wthrcdn.etouch.cn/weather_mini?citykey=101180301
        ApiBaseUrl.URL_BASE = "http://192.168.10.139:8888/springboot/";
        initRetrofit();
        CoreLogUtils.setIsPrintLog(true);
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected void initCrash() {
        CrashHandlerUtils.getInstance().setUploadListener((crashFile, crashInfo) -> {
            //上传或者处理异常信息
        }).init();
    }
}
```
## utils使用
---
- [CoreLogUtils](com.wang.mvvmcore.utils.common.CoreLogUtils)打印日志
```
CoreLogUtils.setIsPrintLog(true);//true开启打印，false关闭打印
```
- [ActivityManagerUtils](com.wang.mvvmcore.utils.common.ActivityManagerUtils) Activity堆栈管理
## rxBus使用
---
```
//发送数据
RxBus.getInstance().post(new MsgEvent(1,"hello“))

//接受数据
RxBus.getInstance().toObservable(this,MsgEvent.class).subscribe(new RxBusObserver<MsgEvent>() {
           @Override
           public void onNext(MsgEvent msgEvent) {

           }
       });
```





