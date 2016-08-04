package com.example.dllo.mygiftproject.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.model.bean.SearchFragmentGvBean;
import com.example.dllo.mygiftproject.model.net.VolleyInstance;
import com.example.dllo.mygiftproject.model.net.VolleyPort;
import com.example.dllo.mygiftproject.ui.adapter.SearchFmGvAdapter;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/7/21.
 * 搜索后替换站位布局的fragment
 */
public class SearchFragment extends AbsBaseFragment implements VolleyPort {
    private String searchUrl;
    private GridView searchFmGv;
    private SearchFragmentGvBean bean;

    // 对外提供一个静态的 返回fragment对象的方法
    public static SearchFragment searchFragment(String url) {
        // 初始化fragment
        SearchFragment searchFragment = new SearchFragment();
        Bundle bundle = new Bundle(); // 初始化bundle
        bundle.putString("url", url); // 加入参数中的url
        searchFragment.setArguments(bundle); // 通过setArgunments方法传bundle
        return searchFragment; // 返回framgent
    }


    @Override
    protected int setLayout() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initView() {
        searchFmGv = byView(R.id.searchFragment_Gv);
    }

    @Override
    protected void initDatas() {
        Bundle bundle = getArguments();
        this.searchUrl = bundle.getString("url");
        String noUrl = "http://api.liwushuo.com/v2/search/item?keyword=&limit=20&offset=0&sort=";
        if (searchUrl.equals(noUrl)) {
            Log.d("SearchFragment", "我被gone了");
            searchFmGv.setVisibility(View.GONE);
        }
        VolleyInstance.getInstance(context).startStringRequest(searchUrl,this);
    }

    @Override
    public void stringSuccess(String result) {
        Gson gson = new Gson();
        bean = gson.fromJson(result,SearchFragmentGvBean.class);
        SearchFmGvAdapter gvAdapter = new SearchFmGvAdapter(context);
        gvAdapter.setDatas(bean);
        searchFmGv.setAdapter(gvAdapter);
    }

    @Override
    public void stringFailure() {

    }
}
