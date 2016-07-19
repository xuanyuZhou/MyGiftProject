package com.example.dllo.mygiftproject.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * Created by dllo on 16/7/16.
 */
public class MyCustomGridView extends GridView{
    public MyCustomGridView(Context context) {
        super(context);
    }

    public MyCustomGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCustomGridView(Context context, AttributeSet attrs, int defStyleAttr) {
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
