package com.example.dllo.mygiftproject.ui.activity;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

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
