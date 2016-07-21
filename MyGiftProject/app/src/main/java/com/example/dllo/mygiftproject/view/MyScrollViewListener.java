package com.example.dllo.mygiftproject.view;

/**
 * Created by dllo on 16/7/20.
 */
public interface MyScrollViewListener {
    /**
     *
     * @param scrollView
     * @param x  scrollview的x轴
     * @param y  scrollview的y轴
     * @param oldx
     * @param oldy
     */
    void onScrollChanged(MyCustomScrollView scrollView, int x, int y, int oldx, int oldy);
}
