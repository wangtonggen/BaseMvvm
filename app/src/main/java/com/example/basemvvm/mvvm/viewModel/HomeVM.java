package com.example.basemvvm.mvvm.viewModel;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.example.basemvvm.R;
import com.example.basemvvm.adapter.HomeRecyclerAdapter;
import com.example.basemvvm.base.fragment.BaseMVVMFragment;
import com.example.basemvvm.bean.BannerBean;
import com.example.basemvvm.bean.MultiItemBean;
import com.example.basemvvm.base.baseViewModel.BaseFragmentLifecycleVM;
import com.example.basemvvm.utils.common.ToastUtils;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.stx.xhb.androidx.XBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * author: wtg
 * date:2020/3/18 0018
 * desc: 首页的viewModel
 */
public class HomeVM extends BaseFragmentLifecycleVM {
    private int page = 1;
    private int pageSize = 15;

    private BaseLoadMoreModule baseLoadMoreModule;
    public LinearLayoutManager linearLayoutManager;
    public HomeRecyclerAdapter homeRecyclerAdapter;

    private XBanner banner;
    private List<BannerBean> bannerBeans = new ArrayList<>();
    public OnRefreshListener onRefreshListener = refreshLayout -> {
        page = 1;
        loadBannerData();
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
        homeRecyclerAdapter = new HomeRecyclerAdapter();

        View headerView = LayoutInflater.from(mContext).inflate(R.layout.view_home_header, null);
        homeRecyclerAdapter.addHeaderView(headerView);
        banner = headerView.findViewById(R.id.xbanner);

        homeRecyclerAdapter.setOnItemClickListener((adapter, view, position) -> {
            homeRecyclerAdapter.notifyItemChanged(position + homeRecyclerAdapter.getHeaderLayoutCount());
            ToastUtils.showShortToast("position_" + position);
        });
        baseLoadMoreModule = homeRecyclerAdapter.getLoadMoreModule();
        baseLoadMoreModule.setOnLoadMoreListener(() -> {
            page++;
            loadData(null);
        });
        baseLoadMoreModule.setAutoLoadMore(true);
        baseLoadMoreModule.setEnableLoadMoreIfNotFullPage(false);
    }

    /**
     * 加载banner数据
     */
    private void loadBannerData() {
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

        banner.setBannerData(R.layout.view_home_banner, bannerBeans);
        banner.loadImage((banner1, model, view, position) -> {
            AppCompatImageView imageView = view.findViewById(R.id.iv_image);
            Glide.with(mContext).load(bannerBeans.get(position).getUrl()).apply(RequestOptions.bitmapTransform(new RoundedCorners(10))).into(imageView);
        });
        banner.setOnItemClickListener((banner1, model, view, position) -> ToastUtils.showShortToast("position_" + position));
        banner.startAutoPlay();
    }

    private void loadData(RefreshLayout refreshLayout) {
        new Handler().postDelayed(() -> {
            if (page == 1) {
                homeRecyclerAdapter.setList(initData());
                homeRecyclerAdapter.notifyDataSetChanged();
                finishRefreshAndLoadMore(refreshLayout);
            } else {
                homeRecyclerAdapter.addData(initData());
                finishRefreshAndLoadMore(refreshLayout);
            }
        }, 2000);
    }

    /**
     * 关闭刷新或者加载更多
     *
     * @param refreshLayout 刷新控件
     */
    private void finishRefreshAndLoadMore(RefreshLayout refreshLayout) {
        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
            refreshLayout.finishLoadMore();
        }

        baseLoadMoreModule.setEnableLoadMore(true);
        if (homeRecyclerAdapter.getData().size() >= 45) {
            baseLoadMoreModule.loadMoreEnd();
        } else {
            baseLoadMoreModule.loadMoreComplete();
        }
    }

    private List<MultiItemBean> initData() {
        List<MultiItemBean> multiItemBeanList = new ArrayList<>();
        for (int i = 0; i < pageSize; i++) {
            multiItemBeanList.add(new MultiItemBean(i % 2 == 0 ? 0 : 1, "item_" + i, "a you ok?"));
        }

        return multiItemBeanList;
    }
}
