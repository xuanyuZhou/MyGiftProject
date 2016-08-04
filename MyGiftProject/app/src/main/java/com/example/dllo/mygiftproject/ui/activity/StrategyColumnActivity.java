package com.example.dllo.mygiftproject.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.model.bean.ColumnActivityBean;
import com.example.dllo.mygiftproject.model.net.SingleImageLoader;
import com.example.dllo.mygiftproject.model.net.VolleyInstance;
import com.example.dllo.mygiftproject.model.net.VolleyPort;
import com.example.dllo.mygiftproject.ui.adapter.StrategyColumnLvAdapter;
import com.example.dllo.mygiftproject.view.MyCustomScrollView;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/7/20.
 * 分类 栏目的recyclerView跳转的activity
 */
public class StrategyColumnActivity extends AbsBaseActivity implements VolleyPort, View.OnClickListener, ViewTreeObserver.OnGlobalLayoutListener {
    private ListView columnListView;
    private String url;
    private TextView titleTv;
    private ImageView titleImage;
    private TextView titleContent;
    private ImageView onBack;
    // 透明标题栏效果 定义相关
    private MyCustomScrollView scrollView;
    // 相对布局对象
    private RelativeLayout alphaRelativeLayout;
    // 图片高度
    private int imageHeight;

    @Override
    protected int setLayout() {
        return R.layout.activity_startegy_column;
    }

    @Override
    protected void initView() {
        columnListView = byView(R.id.startegy_column_lv);
        titleTv = byView(R.id.startegy_column_title);
        titleImage = byView(R.id.startegy_column_titleImage);
        titleContent = byView(R.id.startegy_column_titleText);
        onBack = byView(R.id.startegy_column_back);
        // >>>>>>>
        alphaRelativeLayout = byView(R.id.startegy_column_alphaRelativeLayout);
        scrollView = byView(R.id.startegy_column_myCustomScrollView);
    }

    @Override
    protected void initListeners() {
        titleTv.setOnClickListener(this);
        onBack.setOnClickListener(this);
        // 获取图片高度
        ViewTreeObserver vto = alphaRelativeLayout.getViewTreeObserver();
        // 设置监听
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
//                // 获取图片高度
//                titleImage.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                // 设置给我的对象
                imageHeight = titleImage.getHeight();

                scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                    @Override
                    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                        if (scrollY == 0) {
                            alphaRelativeLayout.setBackgroundColor(Color.argb(0,255,68,68));
                        }
                        if (scrollY > 0 && scrollY < imageHeight) {
                            float fy = (float) scrollY;
                            float imageF = (float) imageHeight;
                            float num = (fy / imageF );
                            int alpha = (int)(num * 255);
                            alphaRelativeLayout.setBackgroundColor(Color.argb(alpha,255,68,68));
                        }
                        if (scrollY > imageHeight) {
                            alphaRelativeLayout.setBackgroundColor(Color.argb(255,255,68,68));
                        }
                    }
                });
            }
        });


    }

    @Override
    protected void initDatas() {
        // 接收url title
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        this.url = bundle.getString("url");
        titleTv.setText(bundle.getString("title"));
        // 解析
        VolleyInstance.getInstance(this).startStringRequest(url, this);
        //
        scrollView.smoothScrollTo(0,20);
    }

    @Override
    public void stringSuccess(String result) {
        Gson gson = new Gson();
        ColumnActivityBean bean = gson.fromJson(result, ColumnActivityBean.class);
        // 向控件传入数据
        SingleImageLoader.loaderImage(bean.getData().getCover_image_url(), titleImage, this);
        titleContent.setText(bean.getData().getDescription());
        // 初始化适配器 传入数据 启动适配器
        StrategyColumnLvAdapter lvAdapter = new StrategyColumnLvAdapter(this);
        lvAdapter.setData(bean);
        columnListView.setAdapter(lvAdapter);
    }

    @Override
    public void stringFailure() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startegy_column_back:
                // 点击上方后退图标 结束当前activity
                finish();
                break;
            case R.id.startegy_column_title:

                break;
        }
    }

    @Override
    public void onGlobalLayout() {

    }
}
