package com.example.dllo.mygiftproject.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by dllo on 16/7/20.
 * 带滚动监听的scrollView
 */
public class MyCustomScrollView extends ScrollView {

    // 创建接口对象
    private MyScrollViewListener myScrollViewListener = null;

    public void setMyScrollViewListener(MyScrollViewListener myScrollViewListener) {
        this.myScrollViewListener = myScrollViewListener;
    }

    public MyCustomScrollView(Context context) {
        super(context);
    }

    public MyCustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyCustomScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    // 复写onScrollChanged方法  获取参数
//    @Override
//    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
//        super.onScrollChanged(l, t, oldl, oldt);
//        if (myScrollViewListener == null) {
//            myScrollViewListener.onScrollChanged(this, l, t, oldl, oldt);
//        }
//    }
}
