package com.example.dllo.mygiftproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.dllo.mygiftproject.R;

/**
 * Created by dllo on 16/7/19.
 * 宝贝详情activity
 */
public class JumpBabyDatailsActivity extends AbsBaseActivity implements View.OnClickListener {
    private WebView datailsWebView;
    private String webUrl;
    private ImageView babyBack;
    @Override
    protected int setLayout() {
        return R.layout.activity_jump_baby_datails;
    }

    @Override
    protected void initView() {
        datailsWebView = byView(R.id.babyDatails_webView);
        babyBack = byView(R.id.babyDatails_back);
    }

    @Override
    protected void initListeners() {
        babyBack.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        // 接收url
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        this.webUrl = bundle.getString("url");
        // 加载webView
        WebSettings webSettings = datailsWebView.getSettings();
        // 和javaScript交互
        webSettings.setJavaScriptEnabled(true);
        // 设置可以被显示的屏幕控制
        webSettings.setDisplayZoomControls(true);
        // 支持当前屏幕(适配)
        webSettings.setSupportZoom(true);
        //
        webSettings.setBuiltInZoomControls(true);
        // 默认字体大小
        webSettings.setDefaultFontSize(20);

        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        // 缓存 (加载缓存或者从网络加载)
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 默认启动手机浏览器关闭, 在webView里启动网页
        datailsWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        // 加载需要显示的网页

        datailsWebView.loadUrl(webUrl);

    }

    /**
     * 设置回退  覆盖activity类的 onKeyDown方法
     * @param keyCode 按得是哪一个妞
     * @param event  返回事件
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && datailsWebView.canGoBack()) {
            datailsWebView.goBack(); // GoBack表示返回webView的上一界面
            return true;
        }
        finish();
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 上方标题栏的返回按钮
            case R.id.babyDatails_back :
                // 点击结束当前activity
                finish();
                break;
        }
    }
}
