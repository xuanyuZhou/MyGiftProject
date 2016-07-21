package com.example.dllo.mygiftproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.model.bean.SingleJumpActivityBean;
import com.example.dllo.mygiftproject.model.net.VolleyInstance;
import com.example.dllo.mygiftproject.model.net.VolleyPort;
import com.example.dllo.mygiftproject.ui.adapter.SingleJumpActivityGvAdapter;
import com.example.dllo.mygiftproject.view.MyCustomGridView;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/7/21.
 * 分类页 单品点击后跳转的activity
 */
public class SingleJumpActivity extends AbsBaseActivity implements VolleyPort, View.OnClickListener {
    private MyCustomGridView singleJumpGv;
    private String url;
    private TextView titleTv;
    private ImageView onBack;
    private ImageView hot;

    @Override
    protected int setLayout() {
        return R.layout.activity_single_jump;
    }

    @Override
    protected void initView() {
        titleTv = byView(R.id.singleActivity_title);
        singleJumpGv = byView(R.id.singleActivity_gv);
        onBack = byView(R.id.singleActivity_back);
        hot = byView(R.id.singleActivity_hot);
    }

    @Override
    protected void initListeners() {
        onBack.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        titleTv.setText(bundle.getString("title"));
        this.url = bundle.getString("url");
        VolleyInstance.getInstance(this).startStringRequest(url, this);
    }

    @Override
    public void stringSuccess(String result) {
        Gson gson = new Gson();
        SingleJumpActivityBean bean = gson.fromJson(result, SingleJumpActivityBean.class);
        SingleJumpActivityGvAdapter gvAdapter = new SingleJumpActivityGvAdapter(this);
        gvAdapter.setBean(bean);
        singleJumpGv.setAdapter(gvAdapter);
    }

    @Override
    public void stringFailure() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.singleActivity_back:
                finish();
                break;
        }
    }
}
