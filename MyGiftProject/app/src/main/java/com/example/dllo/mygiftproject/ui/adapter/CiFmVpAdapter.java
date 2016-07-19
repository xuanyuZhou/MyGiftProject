package com.example.dllo.mygiftproject.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/15.
 * 分类页viewPager适配器
 */
public class CiFmVpAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private final List<String> titles;

    public CiFmVpAdapter(FragmentManager fm) {
        super(fm);
        titles = new ArrayList<>();
        titles.add("攻略");
        titles.add("单品");
    }

    public void setFragments(List<Fragment> fragments) {
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
        return titles.get(position);
    }
}
