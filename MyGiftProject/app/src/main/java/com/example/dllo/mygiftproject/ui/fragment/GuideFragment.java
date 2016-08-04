package com.example.dllo.mygiftproject.ui.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.model.bean.GuideRollTitleBean;
import com.example.dllo.mygiftproject.model.bean.RunnableDocumentBean;
import com.example.dllo.mygiftproject.model.net.VolleyInstance;
import com.example.dllo.mygiftproject.model.net.VolleyPort;
import com.example.dllo.mygiftproject.ui.activity.LoginActivity;
import com.example.dllo.mygiftproject.ui.activity.SearchActivity;
import com.example.dllo.mygiftproject.ui.adapter.GuideFmVpAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/11.
 * 指南页总fragment
 */
public class GuideFragment extends AbsBaseFragment implements VolleyPort, CompoundButton.OnCheckedChangeListener {

    private String rollUrl = RunnableDocumentBean.TL_TITLE_URL;
    private List<Fragment> fragments;
    private GuideFmVpAdapter guideFmVpAdapter;
    private ViewPager guideFmViewpager;
    private TabLayout guideFmTabLayout;
    // 标题栏第一个图标
    private ImageView guideTitleOneIv;
    private ImageView guideTitleSearch;
    // 点击三角出菜单
    private ToggleButton openBtn;
    private GridLayout gridLayout;
    private Button openBtn0, openBtn1, openBtn2, openBtn3, openBtn4, openBtn5, openBtn6, openBtn7, openBtn8, openBtn9, openBtn10,
            openBtn11, openBtn12, openBtn13, openBtn14, openBtn15, openBtn16;
    private Button[] buttons = {openBtn0, openBtn1, openBtn2, openBtn3, openBtn4, openBtn5, openBtn6, openBtn7, openBtn8, openBtn9, openBtn10,
            openBtn11, openBtn12, openBtn13, openBtn14, openBtn15, openBtn16};
    private Integer buttonIds[] = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8
            , R.id.btn9, R.id.btn10, R.id.btn11, R.id.btn12, R.id.btn13, R.id.btn14, R.id.btn15, R.id.btn16};
    private RelativeLayout openRl;

    @Override
    protected int setLayout() {
        return R.layout.fragment_guide;
    }

    @Override
    protected void initView() {
        guideFmViewpager = byView(R.id.guideFm_viewPager);
        guideFmTabLayout = byView(R.id.guideFm_tabLayout);
        guideTitleOneIv = byView(R.id.guide_title_one);
        guideTitleSearch = byView(R.id.guide_title_search);
        openBtn = byView(R.id.guide_tabOpenBtn);
        gridLayout = byView(R.id.guide_gridLayout);
        openRl = byView(R.id.openRl);
        // 绑定gridView内的button
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = byView(buttonIds[i]);
        }
    }

    @Override
    protected void initDatas() {
        // 网络获取Tablayout 的title
        VolleyInstance.getInstance(context).startStringRequest(rollUrl, this);
        // 初始化viewPager适配器
        fragments = new ArrayList<>();
        // 设置guideTitleOneIv设置监听 (指南标题栏第一个图标)
        guideTitleOneIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(getActivity(), LoginActivity.class);
            }
        });
        guideTitleSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(context, SearchActivity.class);
            }
        });

        openRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (openBtn.isChecked()) {
                    openBtn.setChecked(false);
                    gridLayout.setVisibility(View.GONE);
                } else {
                    openBtn.setChecked(true);
                    gridLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        // 处理 tabLayout右侧小三角跟tabLayout联动相关代码
        openBtn.setOnCheckedChangeListener(this);
        for (int i = 0; i < buttons.length; i++) {
            final int page = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    guideFmViewpager.setCurrentItem(page,true);
                    for (int i1 = 0; i1 < buttons.length; i1++) {
                        buttons[i1].setBackgroundResource(R.drawable.tablayout_drawable);
                        buttons[page].setBackgroundColor(Color.argb(200,255,182,193));
                        gridLayout.setVisibility(View.GONE);
                        openBtn.setChecked(false);
                    }
                }
            });
        }

        // Viewpager的选中监听
        guideFmViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i1 = 0; i1 < buttons.length; i1++) {
                    buttons[i1].setBackgroundResource(R.drawable.tablayout_drawable);
                    buttons[position].setBackgroundColor(Color.argb(200,255,182,193));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    // 请求成功方法
    @Override
    public void stringSuccess(String result) {
        Gson gson = new Gson();
        // 解析
        GuideRollTitleBean bean = gson.fromJson(result, GuideRollTitleBean.class);
        List<GuideRollTitleBean.DataBean.ChannelsBean> rollTitleArray = bean.getData().getChannels();

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
        guideFmVpAdapter.setTitle(rollTitleArray, fragments);
        guideFmViewpager.setAdapter(guideFmVpAdapter);
        guideFmTabLayout.setupWithViewPager(guideFmViewpager);
        guideFmTabLayout.setTabTextColors(Color.BLACK, Color.RED);
        guideFmTabLayout.setSelectedTabIndicatorColor(Color.RED);
        // viewPager设置懒加载模式 前16页不删除
        guideFmViewpager.setOffscreenPageLimit(16);

    }

    // 请求失败
    @Override
    public void stringFailure() {
        Log.d("GuideFragment", "网络请求失败");
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.guide_tabOpenBtn:
                if (isChecked) {
                    gridLayout.setVisibility(View.VISIBLE);
                } else {
                    gridLayout.setVisibility(View.GONE);
                }
                break;
        }
    }
}
