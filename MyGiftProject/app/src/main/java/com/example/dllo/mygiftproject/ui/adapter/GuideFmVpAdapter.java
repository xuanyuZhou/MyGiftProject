package com.example.dllo.mygiftproject.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dllo.mygiftproject.model.bean.GuideRollTitleBean;

import java.util.List;

/**
 * Created by dllo on 16/7/11.
 * 指南页整体ViewPager适配器
 */
public class GuideFmVpAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<GuideRollTitleBean.DataBean.ChannelsBean> title;
    public GuideFmVpAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setTitle(List<GuideRollTitleBean.DataBean.ChannelsBean> title, List<Fragment> fragments) {
        this.title = title;
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments != null ? fragments.get(position) : null;
    }

    @Override
    public int getCount() {
        return fragments != null ? fragments.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position).getName();
    }


}
