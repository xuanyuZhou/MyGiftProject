package com.example.dllo.mygiftproject.ui.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.ui.adapter.CiFmVpAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/11.
 * 分类页总fragment
 */
public class ClassifyFragment extends AbsBaseFragment {
    private TabLayout ciFmTabLayout;
    private ViewPager ciFmViewPager;
    private CiFmVpAdapter ciFmVpAdapter;
    private List<Fragment> fragments;
    private TextView ciFmTitleTv;

    @Override
    protected int setLayout() {
        return R.layout.fragment_classify;
    }

    @Override
    protected void initView() {
        ciFmTabLayout = byView(R.id.cIFm_tabLayout);
        ciFmViewPager = byView(R.id.cIFm_viewPager);
        ciFmTitleTv = byView(R.id.cIFm_XlTv);
    }


    @Override
    protected void initDatas() {
        ciFmVpAdapter = new CiFmVpAdapter(getChildFragmentManager());
        fragments = new ArrayList<>();
        fragments.add(new CiStrategyFragment());
        fragments.add(new CiSingleFragment());
        ciFmVpAdapter.setFragments(fragments);
        ciFmViewPager.setAdapter(ciFmVpAdapter);
        ciFmTabLayout.setupWithViewPager(ciFmViewPager);
        ciFmTabLayout.setTabTextColors(Color.WHITE,Color.WHITE);

        if (ciFmTabLayout.getSelectedTabPosition() == 0) {
            ciFmTitleTv.setVisibility(View.GONE);
        } else {
            ciFmTitleTv.setVisibility(View.VISIBLE);
        }

        ciFmViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0){
                    ciFmTitleTv.setVisibility(View.GONE);
                }else {
                    ciFmTitleTv.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

//        ciFmTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                if (ciFmTabLayout.getSelectedTabPosition() == 0) {
//                    ciFmTitleTv.setVisibility(View.GONE);
//                } else {
//                    ciFmTitleTv.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });


    }
}
