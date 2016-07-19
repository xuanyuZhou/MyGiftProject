package com.example.dllo.mygiftproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.model.bean.SecondaryJumBean;
import com.example.dllo.mygiftproject.model.net.VolleyInstance;
import com.example.dllo.mygiftproject.model.net.VolleyPort;
import com.example.dllo.mygiftproject.ui.adapter.SecondaryJumpLvAdapter;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/7/18.
 * banner等轮播图 的二级界面 是一个activity
 * url采用拼接的方法 直接在此activity内解析
 * 此activity是一个复用的activity被多个点击事件做为二级跳转使用
 */
public class SecondaryJumpActivity extends AbsBaseActivity implements VolleyPort {
    private String url;
    private ListView jumpLv;

    @Override
    protected int setLayout() {
        return R.layout.activity_secondary_jump;
    }

    @Override
    protected void initView() {
        jumpLv = byView(R.id.secondaryJump_lv);
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        this.url = bundle.getString("url");
        Log.e("SecondaryJumpActivity", url);
        VolleyInstance.getInstance(this).startStringRequest(url,this);
    }

    @Override
    public void stringSuccess(String result) {
        Gson gson = new Gson();
        SecondaryJumBean bean = gson.fromJson(result,SecondaryJumBean.class);
        SecondaryJumpLvAdapter lvAdapter = new SecondaryJumpLvAdapter(this);
        lvAdapter.setBean(bean);
        jumpLv.setAdapter(lvAdapter);

    }

    @Override
    public void stringFailure() {
        Toast.makeText(this, "解析失败", Toast.LENGTH_SHORT).show();
    }
}
