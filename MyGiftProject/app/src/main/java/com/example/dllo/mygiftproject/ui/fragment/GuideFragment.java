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
import com.example.dllo.mygiftproject.ui.adapter.GuideFmVpAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/11.
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

        for (int i = 0; i < rollTitleArray.size(); i++) {
            if (i == 0) {
                fragments.add(new GdFirstFragment());
            } else {
                fragments.add(new GdReuseFragment());
            }
        }
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
