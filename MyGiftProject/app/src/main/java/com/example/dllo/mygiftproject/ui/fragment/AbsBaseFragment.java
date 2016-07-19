package com.example.dllo.mygiftproject.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dllo.mygiftproject.ui.activity.AbsBaseActivity;

/**
 * Created by dllo on 16/7/11.
 * fragment基类
 */
public abstract class AbsBaseFragment extends Fragment {
    protected Context context;

    // 将context让全包可用
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setLayout(), container, false);
    }

    // 绑定布局
    protected abstract int setLayout();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 调用
        initView();
    }

    // 绑定组件
    protected abstract void initView();

    // 简化findViewById
    protected <T extends View> T byView(int resId) {
        T t = (T) getView().findViewById(resId);
        return t;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 初始化数据
        initDatas();
    }

    // 初始化数据
    protected abstract void initDatas();

    // fragment跳转activity
    protected void goTo(Context from, Class<? extends AbsBaseActivity> a) {
        Intent intent = new Intent(from, a);
        from.startActivity(intent);
    }
    // 带传值
    protected  void goTo(Context from, Class<? extends AbsBaseActivity> to, Bundle values){
        Intent intent = new Intent(from,to);
        intent.putExtras(values);
        startActivity(intent);
    }

}
