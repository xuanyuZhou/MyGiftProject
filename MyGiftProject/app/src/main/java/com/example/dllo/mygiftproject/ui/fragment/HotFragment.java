package com.example.dllo.mygiftproject.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.model.bean.HotFmGvBean;
import com.example.dllo.mygiftproject.model.bean.RunnableDocumentBean;
import com.example.dllo.mygiftproject.model.net.VolleyInstance;
import com.example.dllo.mygiftproject.model.net.VolleyPort;
import com.example.dllo.mygiftproject.ui.activity.JumpBabyDatailsActivity;
import com.example.dllo.mygiftproject.ui.adapter.HotFmGvAdapter;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/7/11.
 * 热门页总fragment
 */
public class HotFragment extends AbsBaseFragment implements VolleyPort, AdapterView.OnItemClickListener {
    private GridView hotFmGridView;
    private String gvUrl = RunnableDocumentBean.HOT_FM_GV_URL;
    private HotFmGvAdapter gvAdapter;
    private HotFmGvBean hotFmGvBean;

    @Override
    protected int setLayout() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView() {
        hotFmGridView = byView(R.id.hotFragment_gridView);
    }

    @Override
    protected void initDatas() {
        VolleyInstance.getInstance(context).startStringRequest(gvUrl,this);
        // gridView监听事件
        hotFmGridView.setOnItemClickListener(this);
    }

    @Override
    public void stringSuccess(String result) {
        Gson gson = new Gson();
        hotFmGvBean = gson.fromJson(result,HotFmGvBean.class);
        gvAdapter = new HotFmGvAdapter(context);
        gvAdapter.setDatas(hotFmGvBean);
        hotFmGridView.setAdapter(gvAdapter);

    }

    @Override
    public void stringFailure() {

    }

    /**
     * gridView 监听回调的方法
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putString("url",hotFmGvBean.getData().getItems().get(position).getData().getUrl());
        goTo(context, JumpBabyDatailsActivity.class,bundle);
    }
}
