package com.example.dllo.mygiftproject.tools;

import android.widget.Toast;

import com.example.dllo.mygiftproject.ui.activity.MyApp;


/**
 * Created by dllo on 16/7/11.
 * Toast 吐司的工具类
 */
public final class T {
    private static boolean isDebug = true;

    private T() {}

    /**
     * 吐司长时间的
     *
     * @param msg
     */
    public static void longMsg(String msg) {
        if (isDebug) {
            Toast.makeText(MyApp.getContext(), msg, Toast.LENGTH_LONG).show();
        }
    }

    public static void shortMsg(String msg) {
        Toast.makeText(MyApp.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
