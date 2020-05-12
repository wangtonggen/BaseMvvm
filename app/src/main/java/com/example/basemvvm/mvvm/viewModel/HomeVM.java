package com.example.basemvvm.mvvm.viewModel;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.basemvvm.R;
import com.example.basemvvm.adapter.HomeRecyclerAdapter;
import com.example.basemvvm.adapter.ImageBannerAdapter;
import com.example.basemvvm.base.fragment.BaseMVVMFragment;
import com.example.basemvvm.bean.BannerBean;
import com.example.basemvvm.bean.MultiItemBean;
import com.example.basemvvm.base.baseViewModel.BaseFragmentLifecycleVM;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.listener.OnPageChangeListener;
import com.youth.banner.transformer.MZScaleInTransformer;
import com.youth.banner.util.BannerUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * author: wtg
 * date:2020/3/18 0018
 * desc: 首页的viewModel
 */
public class HomeVM extends BaseFragmentLifecycleVM implements OnBannerListener, OnPageChangeListener {
    private int page = 1;
    private int pageSize = 15;

    public LinearLayoutManager linearLayoutManager;
    public HomeRecyclerAdapter homeRecyclerAdapter;
    private List<MultiItemBean> multiItemBeans = new ArrayList<>();

    private ImageBannerAdapter imageBannerAdapter;
    private Banner banner;
    private List<BannerBean> bannerBeans = new ArrayList<>();
    public OnRefreshListener onRefreshListener = refreshLayout -> {
        page = 1;
        loadBannerData();
        loadData(refreshLayout);
    };

    public OnLoadMoreListener onLoadMoreListener = refreshLayout -> {
        page++;
        loadData(refreshLayout);
    };

    public HomeVM(BaseMVVMFragment fragment) {
        super(fragment);
        init();
    }

    @SuppressLint("InflateParams")
    @Override
    protected void init() {
        linearLayoutManager = new LinearLayoutManager(mContext);
        homeRecyclerAdapter = new HomeRecyclerAdapter(multiItemBeans);

        View headerView = LayoutInflater.from(mContext).inflate(R.layout.view_home_header, null);
        homeRecyclerAdapter.addHeaderView(headerView);
        banner = headerView.findViewById(R.id.banner);
        imageBannerAdapter = new ImageBannerAdapter(bannerBeans);
        banner.setStartPosition(2);
        banner.setAdapter(imageBannerAdapter);
        //设置指示器
        banner.setIndicator(new CircleIndicator(mContext));
        banner.setOnBannerListener(this);
        banner.addOnPageChangeListener(this);
        banner.setBannerRound(BannerUtils.dp2px(10));
        //魅族效果
        banner.setBannerGalleryMZ(20);
    }

    @Override
    public void OnBannerClick(Object data, int position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onStart() {
        super.onStart();
        if (banner != null){
            banner.start();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (banner != null){
            banner.stop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (banner != null){
            banner.destroy();
        }
    }

    /**
     * 加载banner数据
     */
    private void loadBannerData(){
        bannerBeans.clear();
        BannerBean bannerBean = new BannerBean();
        bannerBean.setUrl("http://c.hiphotos.baidu.com/image/pic/item/9c16fdfaaf51f3de1e296fa390eef01f3b29795a.jpg");
        BannerBean bannerBean1 = new BannerBean();
        bannerBean1.setUrl("http://c.hiphotos.baidu.com/image/pic/item/9c16fdfaaf51f3de1e296fa390eef01f3b29795a.jpg");
        BannerBean bannerBean2 = new BannerBean();
        bannerBean2.setUrl("http://c.hiphotos.baidu.com/image/pic/item/9c16fdfaaf51f3de1e296fa390eef01f3b29795a.jpg");
        BannerBean bannerBean3 = new BannerBean();
        bannerBean3.setUrl("http://c.hiphotos.baidu.com/image/pic/item/9c16fdfaaf51f3de1e296fa390eef01f3b29795a.jpg");

        bannerBeans.add(bannerBean);
        bannerBeans.add(bannerBean1);
        bannerBeans.add(bannerBean2);
        bannerBeans.add(bannerBean3);
        imageBannerAdapter.notifyDataSetChanged();
    }

    private void loadData(RefreshLayout refreshLayout) {
        if (page == 1){
            multiItemBeans.clear();
            initData();
            homeRecyclerAdapter.notifyDataSetChanged();
        }else {
            initData();
//            homeRecyclerAdapter.notifyDataSetChanged();
            homeRecyclerAdapter.notifyItemRangeInserted(homeRecyclerAdapter.getItemCount(),multiItemBeans.size()-homeRecyclerAdapter.getItemCount());
        }

        finishRefreshAndLoadMore(refreshLayout);
    }

    /**
     * 关闭刷新或者加载更多
     * @param refreshLayout 刷新控件
     */
    private void finishRefreshAndLoadMore(RefreshLayout refreshLayout){
        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
            refreshLayout.finishLoadMore();
        }
    }

    private void initData(){
        int size = multiItemBeans.size();
        for (int i = 0; i < size + pageSize; i++) {
            multiItemBeans.add(new MultiItemBean(i%2==0?0:1,"item_"+i,"a you ok?"));
        }
    }
}
