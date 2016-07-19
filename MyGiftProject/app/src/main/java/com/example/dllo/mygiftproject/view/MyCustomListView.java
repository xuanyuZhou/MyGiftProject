package com.example.dllo.mygiftproject.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by dllo on 16/7/13.
 * 我的自定义listView 添加了 全部展开 禁止滑动 方法
 */
public class MyCustomListView extends ListView {
    public MyCustomListView(Context context) {
        super(context);
    }

    public MyCustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 重写测量方法
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, height);
    }

    // 点击事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            // 禁止listView滑动
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }
}
