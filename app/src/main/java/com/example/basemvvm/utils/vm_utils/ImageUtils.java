package com.example.basemvvm.utils.vm_utils;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

/**
 * author: wtg
 * date:2020/3/13 0013
 * desc: image加载图片
 */
public class ImageUtils {

    /**
     * 加载不需要 占位图片
     * @param imageView view
     * @param url url
     */
    @BindingAdapter("imageUrl")
    public static void loadImage(AppCompatImageView imageView, String url){
        Glide.with(imageView).load(url).into(imageView);
    }

    /**
     * 加载需要占位图片
     * @param imageView view
     * @param url url
     */
    @BindingAdapter("imageUrlWithPlaceHolder")
    public static void loadImageWithPlaceHolder(ImageView imageView,String url){
//        Glide.with(imageView).load(url).placeholder(placeHolder).into(imageView);
    }
}
