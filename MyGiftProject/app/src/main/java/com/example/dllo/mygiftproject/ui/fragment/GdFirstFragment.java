package com.example.dllo.mygiftproject.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.model.bean.BannerBean;
import com.example.dllo.mygiftproject.model.bean.GuideFirstLvBean;
import com.example.dllo.mygiftproject.model.bean.GuideFirstRvBean;
import com.example.dllo.mygiftproject.model.bean.LocalGuideFirstLvBean;
import com.example.dllo.mygiftproject.model.bean.RunnableDocumentBean;
import com.example.dllo.mygiftproject.model.net.VolleyInstance;
import com.example.dllo.mygiftproject.model.net.VolleyPort;
import com.example.dllo.mygiftproject.ui.activity.SecondaryJumpActivity;
import com.example.dllo.mygiftproject.ui.adapter.GdFmRvOnclick;
import com.example.dllo.mygiftproject.ui.adapter.GuideFirstFmLvAdapter;
import com.example.dllo.mygiftproject.ui.adapter.GuideFirstFmRvAdapter;
import com.google.gson.Gson;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/12.
 * 指南页第一个Fragment
 */
public class GdFirstFragment extends AbsBaseFragment implements VolleyPort, Banner.OnBannerClickListener, GdFmRvOnclick {
    // banner相关初始化
    private Banner rollImgBanner;// banner对象
    private String[] imageUrls;//图片的url
    private String[] bannerJupUrls;// banner点击跳转的url
    private List<String> titles;// banner点击条换的activity上方标题栏的标题
    // rv相关初始化
    private String bannerUrl = RunnableDocumentBean.GD_BANNER_URL; // 初始化banner接口
    private String rvUrl = RunnableDocumentBean.GD_FIRST_RV_URL;// 初始化recyclerView接口
    private int type; // 设置一个状态 区分网络获取对象
    private List<String> rvUrlsArray; // 装url的集合
    private GuideFirstFmRvAdapter guideFirstFmRvAdapter;
    private RecyclerView guideFirstFmRv;
    // lv相关初始化
    private String lvUrl = RunnableDocumentBean.GD_FIRST_LV_URL; // 初始化listView接口
    private List<LocalGuideFirstLvBean> lvBeanArray; // 装类的集合
    private GuideFirstFmLvAdapter guideFirstFmLvAdapter; // 初始化适配器
    private ListView guideFirstFmLv; // 初始化listView
    // 初始化gson
    private Gson gson;
    //


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
                titles = new ArrayList<>();
                for (int i = 0; i < datas.size(); i++) {
                    imageUrls[i] = datas.get(i).getImage_url();
                    bannerJupUrls[i] = "http://api.liwushuo.com/v2/collections/"
                            + String.valueOf(datas.get(i).getTarget_id())
                            + "/posts?gender=1&generation=2&limit=20&offset=0";
                    // titles.add(bean.getData().getBanners().get(i).getTarget().getTitle());
                }
                // 显示banner
                showBanner();
                // 解析recyclerView的图片url
                type = 1;
                VolleyInstance.getInstance(context).startStringRequest(rvUrl, this);
                break;
            case 1:
                GuideFirstRvBean rvBean = gson.fromJson(result, GuideFirstRvBean.class);
                List<GuideFirstRvBean.DataBean.SecondaryBannersBean> rvData = rvBean.getData().getSecondary_banners();
                rvUrlsArray = new ArrayList<>();
                for (int i = 0; i < rvData.size(); i++) {
                    String url = rvData.get(i).getImage_url();
                    rvUrlsArray.add(url);
                }
                // 初始化适配器 绑定适配器 传数据
                guideFirstFmRvAdapter = new GuideFirstFmRvAdapter(context);
                guideFirstFmRvAdapter.setImageUrls(rvUrlsArray);
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
                GuideFirstLvBean lvBean = gson.fromJson(result, GuideFirstLvBean.class);
                List<GuideFirstLvBean.DataBean.ItemsBean> lvData = lvBean.getData().getItems();
                lvBeanArray = new ArrayList<>();
                for (int i = 0; i < lvData.size(); i++) {
                    LocalGuideFirstLvBean localGuideFirstLvBean = new LocalGuideFirstLvBean();
                    localGuideFirstLvBean.setImageUrl(lvData.get(i).getCover_image_url())
                            .setTitle(lvData.get(i).getTitle()) // 此处将今天类型的likeCount强转成String类型放入实体类
                            .setLikesCount(String.valueOf(lvData.get(i).getLikes_count()));
                    lvBeanArray.add(localGuideFirstLvBean);
                }
                // 初始化适配器  set方法传数据  lv绑定适配器
                guideFirstFmLvAdapter = new GuideFirstFmLvAdapter(context);
                guideFirstFmLvAdapter.setDatas(lvBeanArray);
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
        Log.d("GdFirstFragment", url);
        bundle.putString("url",url);
        goTo(context, SecondaryJumpActivity.class,bundle);
        Toast.makeText(context, "点击的是第" + position + "张图片", Toast.LENGTH_SHORT).show();
    }

    // rv 回调监听事件
    @Override
    public void onClickListener(int position) {
        Toast.makeText(context, "你点击的是第" + position + "张图片", Toast.LENGTH_SHORT).show();
    }
}
