package com.example.handy.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.handy.GlideApp;
import com.example.handy.app.HandyAPP;
import com.shehuan.niv.NiceImageView;


public class ImageLoader {

    /**
     * 使用Glide加载圆形ImageView(如头像)时，不要使用占位图
     *
     * @param context context
     * @param url image url
     * @param iv imageView
     */
    public static void load(Context context, String url, ImageView iv) {
        if (!HandyAPP.getAppComponent().getDataManager().getNoImageState()) {
            GlideApp.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.DATA).into(iv);
        }
    }

    public static void loadToNIV(Context context, String url, NiceImageView iv) {
        if (!HandyAPP.getAppComponent().getDataManager().getNoImageState()) {
            GlideApp.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.DATA).into(iv);
        }
    }
}
