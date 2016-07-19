package com.example.dllo.mygiftproject.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.model.bean.GuideReuseLvBean;
import com.example.dllo.mygiftproject.model.net.VolleyInstance;
import com.example.dllo.mygiftproject.model.net.VolleyPort;
import com.example.dllo.mygiftproject.ui.activity.JumpWebActivity;
import com.example.dllo.mygiftproject.ui.adapter.GuideReuseFmLvAdapter;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/7/12.
 * 指南页复用fragment
 */
public class GdReuseFragment extends AbsBaseFragment implements VolleyPort, AdapterView.OnItemClickListener {
    // 初始化URL
    private String reuseUrl;
    // 创建装本地实体类的集合
    private GuideReuseFmLvAdapter lvAdapter;
    private ListView gdReuseListView;
    private GuideReuseLvBean guideReuseLvBean;

    // 对外提供一个静态的 返回fragment对象的方法
    public static GdReuseFragment getReuseFragments(String url){
        // 初始化fragment
        GdReuseFragment gdReuseFragment = new GdReuseFragment();
        Bundle bundle = new Bundle(); // 初始化bundle
        bundle.putString("url",url); // 加入参数中的url
        gdReuseFragment.setArguments(bundle); // 通过setArgunments方法传bundle
        return gdReuseFragment; // 返回framgent
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_gd_reuse;
    }

    @Override
    protected void initView() {
        gdReuseListView = byView(R.id.gdReuseFM_lv);
    }

    @Override
    protected void initDatas() {
        Bundle bundle = getArguments();
        this.reuseUrl = bundle.getString("url");
        VolleyInstance.getInstance(context).startStringRequest(reuseUrl,this);
        // listview监听
        gdReuseListView.setOnItemClickListener(this);
    }

    @Override
    public void stringSuccess(String result) {
        Gson gson = new Gson();
        guideReuseLvBean = gson.fromJson(result,GuideReuseLvBean.class);
        // 初始化要传入适配器的集合
        lvAdapter = new GuideReuseFmLvAdapter(context);
        lvAdapter.setDatas(guideReuseLvBean);
        gdReuseListView.setAdapter(lvAdapter);
    }

    @Override
    public void stringFailure() {

    }

    /**
     * listView 监听接口回调方法
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // 获取接口加入bundle并跳转
        Bundle bundle = new Bundle();
        bundle.putString("url",guideReuseLvBean.getData().getItems().get(position).getUrl());
        goTo(context, JumpWebActivity.class,bundle);
    }
}
