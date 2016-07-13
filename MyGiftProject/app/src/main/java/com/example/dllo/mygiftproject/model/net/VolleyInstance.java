package com.example.dllo.mygiftproject.model.net;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by dllo on 16/7/12.
 */
public class VolleyInstance {
    // 定义一个请求队列
    private RequestQueue queue;

    // 定义一个当前类的静态对象
    private static VolleyInstance instance;

    // 私有化构造方法
    private VolleyInstance(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    // 对外提供一个获得对象的方法
    public static VolleyInstance getInstance(Context context) {
        if (instance == null) {
            synchronized (VolleyInstance.class) {
                if (instance == null) {
                    instance = new VolleyInstance(context);
                }
            }
        }
        return instance;
    }
    //  上方是单例对象 单例请求队列

    /********************************************************************/
    // 下方 是提供Volley的使用方法
    // 1. 简单方式
    public void startStringRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        // 开始请求
        StringRequest sr = new StringRequest(url, listener, errorListener);
        // 加入队列
        queue.add(sr);
    }

    // 2. 利用接口,将成功和失败封装起来
    // 将请求接口利用接口回调响应到调用端

    /**
     * @param url        数据网址
     * @param volleyPort 请求结果(是个接口)
     */
    public void startStringRequest(String url, final VolleyPort volleyPort) {
        StringRequest sr = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // 成功方法
                volleyPort.stringSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // 失败方法
                volleyPort.stringFailure();
            }
        });
        queue.add(sr);
    }

    // Picasso.with(context).load(url).into(imageview);


}
