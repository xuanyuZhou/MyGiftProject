package com.example.dllo.mygiftproject.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 16/7/11.
 */
public class GuideFmVpAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<String> title;
    public GuideFmVpAdapter(FragmentManager fm) {
        super(fm);

    }

    public void setTitle(List<String> title,List<Fragment> fragments) {
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
        return fragments.size() > 0 ? fragments.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }


}
