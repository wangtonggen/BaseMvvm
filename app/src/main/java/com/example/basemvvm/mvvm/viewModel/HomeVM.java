package com.example.basemvvm.mvvm.viewModel;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.basemvvm.R;
import com.example.basemvvm.adapter.HomeRecyclerAdapter;
import com.example.basemvvm.base.fragment.BaseMVVMFragment;
import com.example.basemvvm.bean.BannerBean;
import com.example.basemvvm.bean.MultiItemBean;
import com.example.basemvvm.base.baseViewModel.BaseFragmentLifecycleVM;
import com.example.basemvvm.utils.common.ToastUtils;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.stx.xhb.androidx.XBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * author: wtg
 * date:2020/3/18 0018
 * desc: 首页的viewModel
 */
public class HomeVM extends BaseFragmentLifecycleVM{
    private int page = 1;
    private int pageSize = 15;

    public LinearLayoutManager linearLayoutManager;
    public HomeRecyclerAdapter homeRecyclerAdapter;
    private List<MultiItemBean> multiItemBeans = new ArrayList<>();

    private XBanner banner;
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
        banner = headerView.findViewById(R.id.xbanner);
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

        banner.setBannerData(R.layout.view_home_banner,bannerBeans);
        banner.loadImage((banner1, model, view, position) -> {
            AppCompatImageView imageView = view.findViewById(R.id.iv_image);
            Glide.with(mContext).load(bannerBeans.get(position).getUrl()).apply(RequestOptions.bitmapTransform(new RoundedCorners(10))).into(imageView);
        });
        banner.setOnItemClickListener((banner1, model, view, position) -> {
            ToastUtils.showShortToast("position_"+position);
        });
        banner.startAutoPlay();
    }

    private void loadData(RefreshLayout refreshLayout) {
        if (page == 1){
            multiItemBeans.clear();
            initData();
            homeRecyclerAdapter.notifyDataSetChanged();
        }else {
            initData();
            homeRecyclerAdapter.notifyItemRangeInserted(homeRecyclerAdapter.getItemCount(),pageSize);
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
        for (int i = size; i < size + pageSize; i++) {
            multiItemBeans.add(new MultiItemBean(i%2==0?0:1,"item_"+i,"a you ok?"));
        }
    }
}
