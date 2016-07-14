package com.example.dllo.mygiftproject.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.model.bean.GuideRollTitleBean;
import com.example.dllo.mygiftproject.model.bean.RunnableDocumentBean;
import com.example.dllo.mygiftproject.model.net.VolleyInstance;
import com.example.dllo.mygiftproject.model.net.VolleyPort;
import com.example.dllo.mygiftproject.ui.activity.LoginActivity;
import com.example.dllo.mygiftproject.ui.adapter.GuideFmVpAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/11.
 * 复用的fragment
 */
public class GuideFragment extends AbsBaseFragment implements VolleyPort {

    private String rollUrl = RunnableDocumentBean.TL_TITLE_URL;
    private List<String> rollTitleArray;
    private List<Fragment> fragments;
    private GuideFmVpAdapter guideFmVpAdapter;
    private ViewPager guideFmViewpager;
    private TabLayout guideFmTabLayout;
    // 标题栏第一个图标
    private ImageView guideTitleOneIv;

    @Override
    protected int setLayout() {
        return R.layout.fragment_guide;
    }

    @Override
    protected void initView() {
        guideFmViewpager = byView(R.id.guideFm_viewPager);
        guideFmTabLayout = byView(R.id.guideFm_tabLayout);
        guideTitleOneIv = byView(R.id.guide_title_one);
    }

    @Override
    protected void initDatas() {
        // 网络获取Tablayout 的title
        VolleyInstance.getInstance(context).startStringRequest(rollUrl,this);
        // 初始化viewPager适配器
        fragments = new ArrayList<>();
        rollTitleArray = new ArrayList<>();
        // 设置guideTitleOneIv设置监听 (指南标题栏第一个图标)
        guideTitleOneIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(getActivity(), LoginActivity.class);
                Toast.makeText(context, "hahaha", Toast.LENGTH_SHORT).show();
            }
        });

    }

    // 请求成功方法
    @Override
    public void stringSuccess(String result) {
        Gson gson = new Gson();
        // 解析
        GuideRollTitleBean bean = gson.fromJson(result,GuideRollTitleBean.class);
        List<GuideRollTitleBean.DataBean.ChannelsBean> datas = bean.getData().getChannels();
        for (int i = 0; i < datas.size(); i++) {
            String title = datas.get(i).getName();
            rollTitleArray.add(title);
        }
                // 添加Fragment
                fragments.add(new GdFirstFragment());
                fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_CYSH_URL)); // 穿搭 没有
                fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_HT_URL)); // 海淘
                fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_SNP_URL)); // 送男票
                fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_SJY_URL)); // 礼物 没有
                fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_SGM_URL)); // 送闺蜜
                fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_SBM_URL)); // 送爸妈
                fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_STS_URL)); // 送同事
                fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_SJY_URL)); // 送机油
                fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_SBB_URL)); // 送宝贝
                fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_SGM_URL)); // 手工  没有
                fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_SJG_URL)); // 设计感
                fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_CYSH_URL)); // 创意生活
                fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_WYF_URL)); // 文艺风
                fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_KJF_URL)); // 科技范
                fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_MMD_URL)); // 萌萌哒
                fragments.add(GdReuseFragment.getReuseFragments(RunnableDocumentBean.GD_REUSE_QPGG_URL)); // 奇葩搞怪

        // tabLayout滑动属性
        guideFmTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        guideFmVpAdapter = new GuideFmVpAdapter(getChildFragmentManager());
        guideFmVpAdapter.setTitle(rollTitleArray,fragments);
        guideFmViewpager.setAdapter(guideFmVpAdapter);
        guideFmTabLayout.setupWithViewPager(guideFmViewpager);

    }
    // 请求失败
    @Override
    public void stringFailure() {
        Log.d("GuideFragment", "网络请求失败");
    }
}
