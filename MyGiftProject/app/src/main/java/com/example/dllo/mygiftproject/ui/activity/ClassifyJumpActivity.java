package com.example.dllo.mygiftproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.model.bean.ClassifyJumpBean;
import com.example.dllo.mygiftproject.model.net.VolleyInstance;
import com.example.dllo.mygiftproject.model.net.VolleyPort;
import com.example.dllo.mygiftproject.ui.adapter.ClassifyJumpLvAdapter;
import com.example.dllo.mygiftproject.view.MyCustomListView;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/7/20.
 * 分类页跳转详情activity
 */
public class ClassifyJumpActivity extends AbsBaseActivity implements VolleyPort, View.OnClickListener, AdapterView.OnItemClickListener {
    private MyCustomListView listView;
    private ImageView onBack, hotBtn;
    private TextView titleTv;
    private String url;
    private ClassifyJumpBean bean;

    @Override
    protected int setLayout() {
        return R.layout.activity_jump_classify_two;
    }

    @Override
    protected void initView() {
        listView = byView(R.id.classifyActivity_lv);
        onBack = byView(R.id.classifyActivity_back);
        hotBtn = byView(R.id.classifyActivity_hot);
        titleTv = byView(R.id.classifyActivity_title);
    }

    @Override
    protected void initListeners() {
        // 点击事件
        onBack.setOnClickListener(this);
        hotBtn.setOnClickListener(this);
        // listView 行监听
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        this.url = bundle.getString("url");
        // 设置标题
        titleTv.setText(bundle.getString("title"));
        // 解析
        VolleyInstance.getInstance(this).startStringRequest(url, this);
    }

    @Override
    public void stringSuccess(String result) {
        Gson gson = new Gson();
        bean = gson.fromJson(result, ClassifyJumpBean.class);
        ClassifyJumpLvAdapter lvAdapter = new ClassifyJumpLvAdapter(this);
        lvAdapter.setData(bean);
        listView.setAdapter(lvAdapter);
    }

    @Override
    public void stringFailure() {
        Toast.makeText(this, "解析失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 后退按钮
            case R.id.classifyActivity_back:
                // 点击结束当前activity
                finish();
                break;
            case R.id.classifyActivity_hot:
                break;
        }
    }

    /**
     * listView 监听回调方法
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.classifyActivity_lv:
                Bundle bundle = new Bundle();
                bundle.putString("url", bean.getData().getItems().get(position).getUrl());
                goTo(this, JumpWebActivity.class, bundle);
                break;
        }

    }
}
