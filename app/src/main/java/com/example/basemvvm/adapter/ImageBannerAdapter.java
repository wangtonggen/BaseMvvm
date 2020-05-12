package com.example.basemvvm.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.basemvvm.R;
import com.example.basemvvm.bean.BannerBean;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

/**
 * author: wtg
 * date:2020/5/12 0012
 * desc:
 */
public class ImageBannerAdapter extends BannerAdapter<BannerBean, ImageBannerAdapter.BannerViewHolder> {

    public ImageBannerAdapter(List<BannerBean> datas) {
        super(datas);
    }

    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.view_home_banner,null);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return new BannerViewHolder(view);
    }

    @Override
    public void onBindView(BannerViewHolder holder, BannerBean data, int position, int size) {
        Glide.with(holder.imageView).load(data.getUrl()).into(holder.imageView);
    }

    static class BannerViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView imageView;

        BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.iv_image);
        }
    }
}
