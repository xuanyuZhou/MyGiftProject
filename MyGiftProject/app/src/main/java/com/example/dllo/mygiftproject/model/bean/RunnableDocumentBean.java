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

    // 首页复用fragmentListview的接扣  缺少 穿搭 礼物 手工
    // 海淘
    public static final String GD_REUSE_HT_URL = "http://api.liwushuo.com/v2/channels/129/items_v2?gender=1&limit=20&offset=0&generation=1";
    // 创意生活
    public static final String GD_REUSE_CYSH_URL = "http://api.liwushuo.com/v2/channels/10/items_v2?gender=1&limit=20&offset=0&generation=1";
    // 送女票
    public static final String GD_REUSE_SNP_URL = "http://api.liwushuo.com/v2/channels/10/items_v2?gender=1&limit=20&offset=0&generation=1";
    // 科技范
    public static final String GD_REUSE_KJF_URL = "http://api.liwushuo.com/v2/channels/28/items_v2?gender=1&limit=20&offset=0&generation=1";
    // 送爸妈
    public static final String GD_REUSE_SBM_URL = "http://api.liwushuo.com/v2/channels/6/items_v2?gender=1&limit=20&offset=0&generation=1";
    // 送机油
    public static final String GD_REUSE_SJY_URL = "http://api.liwushuo.com/v2/channels/26/items_v2?gender=1&limit=20&offset=0&generation=1";
    // 送闺蜜
    public static final String GD_REUSE_SGM_URL = "http://api.liwushuo.com/v2/channels/5/items_v2?gender=1&limit=20&offset=0&generation=1";
    // 送同事
    public static final String GD_REUSE_STS_URL = "http://api.liwushuo.com/v2/channels/17/items_v2?gender=1&limit=20&offset=0&generation=1";
    // 送宝贝
    public static final String GD_REUSE_SBB_URL = "http://api.liwushuo.com/v2/channels/24/items_v2?gender=1&limit=20&offset=0&generation=1";
    // 设计感
    public static final String GD_REUSE_SJG_URL = "http://api.liwushuo.com/v2/channels/127/items_v2?gender=1&limit=20&offset=0&generation=1";
    // 文艺风
    public static final String GD_REUSE_WYF_URL = "http://api.liwushuo.com/v2/channels/14/items_v2?gender=1&limit=20&offset=0&generation=1";
    // 奇葩搞怪
    public static final String GD_REUSE_QPGG_URL = "http://api.liwushuo.com/v2/channels/126/items_v2?gender=1&limit=20&offset=0&generation=1";
    // 萌萌哒
    public static final String GD_REUSE_MMD_URL = "http://api.liwushuo.com/v2/channels/129/items_v2?gender=1&limit=20&offset=0&generation=1";

    // 热门页GridView接口
    public static final String HOT_FM_GV_URL = "http://api.liwushuo.com/v2/items?gender=1&limit=20&offset=0&generation=4";
}
