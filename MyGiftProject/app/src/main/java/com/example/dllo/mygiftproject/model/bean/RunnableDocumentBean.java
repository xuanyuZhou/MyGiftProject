package com.example.dllo.mygiftproject.model.bean;

/**
 * Created by dllo on 16/7/12.
 */
public class RunnableDocumentBean {
    // tabLayout的title 的接口
    public static final String TL_TITLE_URL = "http://api.liwushuo.com/v2/channels/preset?gender=2&generation=1";
    // 首页第一个Fragment的banner接口
    public static final String GD_BANNER_URL = "http://api.liwushuo.com/v2/banners";
    // 首页第一个Fragment的recyclerView接口
    public static final String GD_FIRST_RV_URL = "http://api.liwushuo.com/v2/secondary_banners?gender=2&generation=1";
    // 首页第一个Fragment的listView接口
    public static final String GD_FIRST_LV_URL = "http://api.liwushuo.com/v2/channels/103/items?limit=20&ad=2&gender=2&offset=0&generation=1";

}
