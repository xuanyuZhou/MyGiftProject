package com.example.dllo.mygiftproject.model.net;

/**
 * Created by dllo on 16/7/12.
 * 单例类内数据请求成功失败的接口回调类
 */
public interface VolleyPort {
    // 文字成功
    void stringSuccess (String result);
    // 文字失败
    void stringFailure();

}
