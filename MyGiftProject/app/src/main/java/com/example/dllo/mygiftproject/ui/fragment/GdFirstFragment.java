package com.example.dllo.mygiftproject.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.model.bean.BannerBean;
import com.example.dllo.mygiftproject.model.bean.GuideFirstLvBean;
import com.example.dllo.mygiftproject.model.bean.GuideFirstRvBean;
import com.example.dllo.mygiftproject.model.bean.RunnableDocumentBean;
import com.example.dllo.mygiftproject.model.net.VolleyInstance;
import com.example.dllo.mygiftproject.model.net.VolleyPort;
import com.example.dllo.mygiftproject.ui.activity.JumpWebActivity;
import com.example.dllo.mygiftproject.ui.activity.SecondaryJumpActivity;
import com.example.dllo.mygiftproject.ui.adapter.GdFmRvOnclick;
import com.example.dllo.mygiftproject.ui.adapter.GuideFirstFmLvAdapter;
import com.example.dllo.mygiftproject.ui.adapter.GuideFirstFmRvAdapter;
import com.google.gson.Gson;
import com.youth.banner.Banner;

import java.util.List;

/**
 * Created by dllo on 16/7/12.
 * 指南页第一个Fragment
 */
public class GdFirstFragment extends AbsBaseFragment implements VolleyPort, Banner.OnBannerClickListener, GdFmRvOnclick, AdapterView.OnItemClickListener {
    // banner相关初始化
    private Banner rollImgBanner;// banner对象
    private String[] imageUrls;//图片的url
    private String[] bannerJupUrls;// banner点击跳转的url
    // rv相关初始化
    private String bannerUrl = RunnableDocumentBean.GD_BANNER_URL; // 初始化banner接口
    private String rvUrl = RunnableDocumentBean.GD_FIRST_RV_URL;// 初始化recyclerView接口
    private int type; // 设置一个状态 区分网络获取对象
    private List<String> rvUrlsArray; // 装url的集合
    private GuideFirstFmRvAdapter guideFirstFmRvAdapter;
    private RecyclerView guideFirstFmRv;
    // lv相关初始化
    private String lvUrl = RunnableDocumentBean.GD_FIRST_LV_URL; // 初始化listView接口
    private GuideFirstFmLvAdapter guideFirstFmLvAdapter; // 初始化适配器
    private ListView guideFirstFmLv; // 初始化listView
    // 初始化gson
    private Gson gson;
    private GuideFirstLvBean lvBean;


    @Override
    protected int setLayout() {
        return R.layout.fragment_gd_first;
    }

    @Override
    protected void initView() {
        rollImgBanner = byView(R.id.rollImg_banner);
        guideFirstFmRv = byView(R.id.guideFirstFm_rv);
        guideFirstFmLv = byView(R.id.guideFirstFm_lv);

    }

    @Override
    protected void initDatas() {
        // 网络获取 解析 字符串.
        type = 0;
        VolleyInstance.getInstance(context).startStringRequest(bannerUrl, this);
        // banner的监听事件
        rollImgBanner.setOnBannerClickListener(this);
        // 给listView设置点击事件
        guideFirstFmLv.setOnItemClickListener(this);


    }

    // banner显示方法
    public void showBanner() {
        // 设置小圆点
        rollImgBanner.setBannerStyle(Banner.CIRCLE_INDICATOR);
        // 设置位置 居中
        rollImgBanner.setIndicatorGravity(Banner.CENTER);
        // 设置轮播时间
        rollImgBanner.setDelayTime(3000);
        // 设置图片
        rollImgBanner.setImages(imageUrls);
    }


    @Override
    public void stringSuccess(String result) {
        gson = new Gson();
        switch (type) {
            case 0:
                // 解析
                BannerBean bean = gson.fromJson(result, BannerBean.class);
                // 获取数据
                List<BannerBean.DataBean.BannersBean> datas = bean.getData().getBanners();
                // 初始化数组 加入数据
                imageUrls = new String[datas.size()];
                bannerJupUrls = new String[datas.size()];
                for (int i = 0; i < datas.size(); i++) {
                    imageUrls[i] = datas.get(i).getImage_url();
                    bannerJupUrls[i] = "http://api.liwushuo.com/v2/collections/"
                            + String.valueOf(datas.get(i).getTarget_id())
                            + "/posts?gender=1&generation=2&limit=20&offset=0";
                }
                // 显示banner
                showBanner();
                // 解析recyclerView的图片url
                type = 1;
                VolleyInstance.getInstance(context).startStringRequest(rvUrl, this);
                break;
            case 1:
                GuideFirstRvBean rvBean = gson.fromJson(result, GuideFirstRvBean.class);
                // 初始化适配器 绑定适配器 传数据
                guideFirstFmRvAdapter = new GuideFirstFmRvAdapter(context);
                guideFirstFmRvAdapter.setRvBean(rvBean);
                guideFirstFmRv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                guideFirstFmRv.setAdapter(guideFirstFmRvAdapter);
                // 调用适配器内接口
                guideFirstFmRvAdapter.setGdFmRvOnclick(this);
                // 运行结束后 运行解析listView的网络获取
                type = 2;
                VolleyInstance.getInstance(context).startStringRequest(lvUrl, this);
                break;
            case 2:
                // 解析listView所需要的数据
                gson = new Gson();
                lvBean = gson.fromJson(result, GuideFirstLvBean.class);
                // 初始化适配器  set方法传数据  lv绑定适配器
                guideFirstFmLvAdapter = new GuideFirstFmLvAdapter(context);
                guideFirstFmLvAdapter.setDatas(lvBean);
                guideFirstFmLv.setAdapter(guideFirstFmLvAdapter);
                break;
        }
    }

    @Override
    public void stringFailure() {

    }

    /**
     * banner的点击事件方法
     *
     * @param view     banner控件
     * @param position 位置 当前第几张图片
     */
    @Override
    public void OnBannerClick(View view, int position) {
        Bundle bundle = new Bundle();
        String url = bannerJupUrls[position];
        bundle.putString("url",url);
        goTo(context, SecondaryJumpActivity.class,bundle);
    }

    // rv 回调监听事件
    @Override
    public void onClickListener(int position) {
        Toast.makeText(context, "你点击的是第" + position + "张图片", Toast.LENGTH_SHORT).show();
    }

    /**
     * listView 点击实现的接口回调的方法
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putString("url",lvBean.getData().getItems().get(position).getUrl());
        goTo(context, JumpWebActivity.class,bundle);
    }
}
