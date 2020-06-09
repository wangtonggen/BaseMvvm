# 描述
该库使用的mvvm+rxjava2+retrofit2+okhttp3+glide架构模式 可以很方便的使用

# 使用
1.lib里面有使用到butterknife 首先在 project build.gradle添加  
dependencies {  
classpath 'com.android.tools.build:gradle:3.6.3'  
classpath'com.jakewharton:butterknife-gradle-plugin:10.2.1'  
}  
和  
allprojects {  
repositories {  
google()  
jcenter()  
maven {url 'https://jitpack.io' }  
}

2.在 app的build.gradle头部添加  
apply plugin: 'com.jakewharton.butterknife'

3.在APP 里面的 build.gradle添加依赖  
implementation 'com.github.wangtonggen:BaseMvvm:1.0.3'  
还需要在APP 里面的 build.gradle添加 apply plugin:
'com.jakewharton.butterknife'  
//butterknife  
implementation'com.jakewharton:butterknife:10.2.1'  
annotationProcessor'com.jakewharton:butterknife-compiler:10.2.1'

# 具体操作
## 1.activity的继承
```mermaid
   1.1 mvvm BaseActivity->BaseMvvmActivity->BaseSwipeBackLeftActivity(右滑关闭activity)/BaseSwipeBackRightActivity(左滑关闭activity)/BaseSwipeBackNoneActivity(无关闭效果activity)
    示例
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
   1.2 no mvvm  BaseActivity->BaseNoMvvmActivity 默认是右滑关闭activity 如果要实现其他的效果重写getEdgeTrackingEnabled() 方法（划出关闭的方向 SwipeBackLayout.EDGE_LEFT 向右划出   SwipeBackLayout.EDGE_RIGHT 向左划出  SwipeBackLayout.EDGE_BOTTOM 向上划出 其他值无效果）
    示例
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
## 2.fragment的继承
```mermaid
 1.1 mvvm BaseFragment->BaseLazyLoadFragment->BaseMVVMFragment
  示例
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
 1.2 mvvm BaseFragment->BaseLazyLoadFragment->BaseNoMVVMFragment
  示例
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
## 3.recyclerView的adapter的使用
1. 所有的adapter的基类都是基于[BaseRecyclerViewAdapterHelper](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)来二次封装的
   并且在里面加入了默认的空列表的显示信息
   adapter分为两部分
2. 单布局(下面包含使用DataBinding和不使用DataBinding)
3. 多布局(包含使用DataBinding和不使用DataBinding)
   如果要是继承[BaseMultiBinderAdapter](mvvmcore/src/main/java/com/wang/mvvmcore/adapter/multiAdapter/baseMultiAdapter/BaseMultiBinderAdapter.java)
   item需要继承[BaseBinder](mvvmcore/src/main/java/com/wang/mvvmcore/adapter/binder/BaseBinder.java)或者[BaseViewDataBinder](mvvmcore/src/main/java/com/wang/mvvmcore/adapter/binder/BaseViewDataBinder.java)
   如果是继承[BaseProviderAdapter](mvvmcore/src/main/java/com/wang/mvvmcore/adapter/multiAdapter/baseMultiAdapter/BaseProviderAdapter.java)
   item则需要继承[BaseProvider](mvvmcore/src/main/java/com/wang/mvvmcore/adapter/provider/BaseProvider.java)
   或者[BaseBindingProvider](mvvmcore/src/main/java/com/wang/mvvmcore/adapter/provider/BaseBindingProvider.java)
   具体请看demo
### 单布局 [singleAdapter](mvvmcore/src/main/java/com/wang/mvvmcore/adapter/singleAdapter)
1. [BaseSingleAdapter](mvvmcore/src/main/java/com/wang/mvvmcore/adapter/singleAdapter/BaseSingleAdapter.java)
2. [BaseBindingSingleAdapter](mvvmcore/src/main/java/com/wang/mvvmcore/adapter/singleAdapter/BaseBindingSingleAdapter.java)
### 多布局 [multiAdapter](mvvmcore/src/main/java/com/wang/mvvmcore/adapter/multiAdapter)
1.  [BaseDelegateAdapter](mvvmcore/src/main/java/com/wang/mvvmcore/adapter/multiAdapter/baseMultiAdapter/BaseDelegateAdapter.java)
2.  [BaseMultiAdapter](mvvmcore/src/main/java/com/wang/mvvmcore/adapter/multiAdapter/baseMultiAdapter/BaseMultiAdapter.java)
3.  [BaseMultiBinderAdapter](mvvmcore/src/main/java/com/wang/mvvmcore/adapter/multiAdapter/baseMultiAdapter/BaseMultiBinderAdapter.java)
4.  [BaseProviderAdapter](mvvmcore/src/main/java/com/wang/mvvmcore/adapter/multiAdapter/baseMultiAdapter/BaseProviderAdapter.java)
5.  [BaseBindingDelegateAdapter](mvvmcore/src/main/java/com/wang/mvvmcore/adapter/multiAdapter/baseMultiBindingAdapter/BaseBindingDelegateAdapter.java)
6.  [BaseBindingMultiAdapter](mvvmcore/src/main/java/com/wang/mvvmcore/adapter/multiAdapter/baseMultiBindingAdapter/BaseBindingMultiAdapter.java)

## 4.mvvm BindAdapter的使用
1. [command](mvvmcore/src/main/java/com/wang/mvvmcore/bindAdapter/command)
   用来监听按钮的点击事件
2. [CommonBindingAdapter](mvvmcore/src/main/java/com/wang/mvvmcore/bindAdapter/CommonBindingAdapter.java)
3. [EditTextBindingAdapter](mvvmcore/src/main/java/com/wang/mvvmcore/bindAdapter/EditTextBindingAdapter.java)
4. [ImageBindingAdapter](mvvmcore/src/main/java/com/wang/mvvmcore/bindAdapter/ImageBindingAdapter.java)
5. [RecyclerViewBindingAdapter](mvvmcore/src/main/java/com/wang/mvvmcore/bindAdapter/RecyclerViewBindingAdapter.java)
6. [RefreshBindingAdapter](mvvmcore/src/main/java/com/wang/mvvmcore/bindAdapter/RefreshBindingAdapter.java)
7. [SmartRefreshBindingAdapter](mvvmcore/src/main/java/com/wang/mvvmcore/bindAdapter/SmartRefreshBindingAdapter.java)
8. [TextViewBindingAdapter](mvvmcore/src/main/java/com/wang/mvvmcore/bindAdapter/TextViewBindingAdapter.java)
9. [ViewPagerBindingAdapter](mvvmcore/src/main/java/com/wang/mvvmcore/bindAdapter/ViewPagerBindingAdapter.java)




