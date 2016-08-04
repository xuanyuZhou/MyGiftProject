package com.example.dllo.mygiftproject.ui.activity;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by dllo on 16/7/11.
 * 整个app的应用 需要在清单文件中注册 (application中加name)
 * 通常在application中提供一些全局对象给所有人用
 */
public class MyApp extends Application {
    // Application 是当前应用,只存在一个
    private static Context context;
    // 缓存
    private static SharedPreferences sp;

    private static ArrayList<AbsBaseActivity> activities;

    @Override
    public void onCreate() {
        super.onCreate();
        // onCreate 中初始化
        context = getApplicationContext();
        // 缓存
        sp = context.getSharedPreferences("MyDemo", MODE_PRIVATE);

        activities = new ArrayList<>();

        File cacheDir = StorageUtils.
                getOwnCacheDirectory(
                        getApplicationContext(),
                        "BAT/Cache");


        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                // dp -> px dp
                // 单位转换
                .memoryCacheExtraOptions(320, 480) // max width, max height，即保存的每个缓存文件的最大长宽
                .threadPoolSize(3) //线程池内线程的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator()) //将保存的时候的URI名称用MD5 加密
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024) // 内存缓存的最大值
                .diskCacheSize(50 * 1024 * 1024)  // SD卡缓存的最大值
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                // 由原先的discCache -> diskCache
                .diskCache(new UnlimitedDiskCache(cacheDir))//自定义缓存路径
                .imageDownloader(new BaseImageDownloader(getApplicationContext(), 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs() // Remove for release app
                .build();


        // 将配置内容设置给ImageLoader
        ImageLoader.getInstance().init(config);
    }

    // 对外提供get方法
    public static Context getContext() {
        return context;
    }

    public static SharedPreferences getSp() {
        return sp;
    }

    public static void addActivity(AbsBaseActivity a) {
        activities.add(a);
    }


    public static void removeActivity(AbsBaseActivity a) {
        activities.remove(a);
    }
}
