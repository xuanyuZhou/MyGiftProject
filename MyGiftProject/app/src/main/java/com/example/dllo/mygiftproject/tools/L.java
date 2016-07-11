package com.example.dllo.mygiftproject.tools;

import android.util.Log;

/**
 * Created by dllo on 16/7/11.
 * Log的工具类
 */
public final class L {
    // 被final修饰,不能被继承
    // 私有构造方法,外部不能创建对象

    //私有构造方法
    private L(){}

    // 调试模式 目前定义为true (项目上线是改为false 就可以不用一个一个删除)
    private static boolean isDebug = true;
    // log日志的标签,定义的是应用名
    private static String TAG = "MyDemo";

    /**
     * 使用默认TAG标签的
     * @param msg
     */
    public static void e(String msg) {
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }

    /**
     * 使用自定义TAG标签的
     * @param tag
     * @param msg
     */
    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg);
        }
    }

    /**
     * 使用默认TAG标签的
     * @param msg
     */
    public static void d(String msg) {
        if (isDebug) {
            Log.d(TAG, msg);
        }
    }

    /**
     * 使用自定义TAG标签的
     * @param tag
     * @param msg
     */
    public static void d(String tag, String msg) {
        if (isDebug) {
            Log.d(tag, msg);
        }
    }




}
