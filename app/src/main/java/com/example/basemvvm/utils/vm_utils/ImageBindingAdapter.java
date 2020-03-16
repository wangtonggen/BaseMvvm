package com.example.basemvvm.utils.vm_utils;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

/**
 * author: wtg
 * date:2020/3/16 0016
 * desc: imageView的bindingAdapter
 */
public class ImageBindingAdapter {

    /**
     * 加载图片
     * @param imageView imageView
     * @param url path
     * @param image_placeholder 占位图片
     * @param image_error_load 加载错误的图片
     */
    @BindingAdapter(value = {"image_url","image_placeholder","image_error_load"},requireAll = false)
    public static void loadImage(ImageView imageView, Object url,int image_placeholder,int image_error_load) {
        Glide.with(imageView).load(url).placeholder(image_placeholder).error(image_error_load).into(imageView);
    }
}
