package com.example.dllo.mygiftproject.model.net;

import android.content.Context;
import android.widget.ImageView;

import com.example.dllo.mygiftproject.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by dllo on 16/7/15.
 * 我自己写的单例 牛逼完了
 */
public class SingleImageLoader {
    /**
     * 创建一个静态对象
     */
    private static SingleImageLoader imageLoader;

    /**
     * 私有化构造方法
     */
    private SingleImageLoader() {
    }

    /**
     * 对外提供一个获取我的对象的方法
     * @return 返回一个此类的对象
     */
    public static SingleImageLoader getImageLoader() {
        if (imageLoader == null) {
            synchronized (SingleImageLoader.class) {
                if (imageLoader == null) {
                    imageLoader = new SingleImageLoader();
                }
            }
        }
        return imageLoader;
    }

    // UniversalImageLoader 解析图片方法
    private static DisplayImageOptions options =
            new DisplayImageOptions.Builder()
                    .cacheInMemory(true) // 设置内存缓存
                    .cacheOnDisk(true)  // 设置硬盘缓存
                    .showImageForEmptyUri(R.mipmap.icon_progress_bar_refresh) // 设置URL为空时用的图片
                    .showImageOnFail(R.mipmap.icon_progress_bar_refresh)  // 设置图片加载错误时用的图片
                    .build();

    public static void loaderImage(String url, ImageView imageView, Context context) {
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(context);
        ImageLoader.getInstance().init(configuration);
        ImageLoader.getInstance().displayImage(url, imageView, options);
    }

}
