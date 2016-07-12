package com.example.dllo.mygiftproject.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by dllo on 16/7/11.
 */
public abstract class AbsBaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 绑定布局
        setContentView(setLayout());
        // 绑定组件
        initView();
        // 设置监听
        initListeners();
        // 初始化数据
        initDatas();
    }
    // 绑定布局
    protected abstract int setLayout();
    // 绑定组件
    protected abstract void initView();
    // 设置监听
    protected abstract void initListeners();
    // 初始化数据
    protected abstract void initDatas();
    // 绑定id
    protected  <T extends View> T byView(int resId) {
        T t = (T) findViewById(resId);
        return t;
    }
    // 简化intent跳转
    protected void goTo(Context from,Class<? extends AbsBaseActivity> to) {
        Intent intent = new Intent(from,to);
        startActivity(intent);
    }
    // 简化带传值intent跳转
    protected void goTo(Context from,Class<? extends AbsBaseActivity> to,Bundle values) {
        Intent intent = new Intent(from,to);
        intent.putExtras(values);
        startActivity(intent);
    }
}
