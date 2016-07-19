package com.example.dllo.mygiftproject.ui.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.model.bean.CiFmSingleLvBean;
import com.example.dllo.mygiftproject.model.bean.RunnableDocumentBean;
import com.example.dllo.mygiftproject.model.net.VolleyInstance;
import com.example.dllo.mygiftproject.model.net.VolleyPort;
import com.example.dllo.mygiftproject.ui.adapter.CiSingleLeftLvAdapter;
import com.example.dllo.mygiftproject.ui.adapter.CiSingleRightLvAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/15.
 * 分类页单品fragment
 * 此页有两个listView联动
 */
public class CiSingleFragment extends AbsBaseFragment implements VolleyPort {
    private String singleUrl = RunnableDocumentBean.CI_FM_SINGLE_URL;
    private ListView leftListView, rightListView;
    private CiSingleLeftLvAdapter leftLvAdapter;
    private CiSingleRightLvAdapter rightLvAdapter;
    // 实验
    private View clickSource,touchSource;
    private int offset = 0;
    private int pos;
    private AbsListView.OnScrollListener scrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            pos = rightListView.getFirstVisiblePosition();
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        }
    };

    @Override
    protected int setLayout() {
        return R.layout.fragment_ci_single;
    }

    @Override
    protected void initView() {
        leftListView = byView(R.id.left_listView);
        rightListView = byView(R.id.right_listView);
    }

    @Override
    protected void initDatas() {
        VolleyInstance.getInstance(context).startStringRequest(singleUrl, this);

        rightListView.setOnScrollListener(scrollListener);

        /**
         * listView滑动事件  右侧listView滑动 左侧listView根据位置跳到相应位置
         */
        rightListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("CiSingleFragment", "position:" + pos);
                leftListView.setSelection(pos);
                leftListView.setBackgroundColor(Color.TRANSPARENT);
                leftLvAdapter.notifyDataSetInvalidated();

//                if (touchSource == null) {
//                    touchSource = v;
//                }
//                if (v == touchSource) {
//                    leftListView.dispatchTouchEvent(event);
//                    if (event.getAction() == MotionEvent.ACTION_UP) {
//                        clickSource = v;
//                        touchSource = null;
//                    }
//                }
                return false;
            }
        });

        /**
         * listView点击事件
         * 双listView联动 点击左侧listView 右侧跳到相应位置
         */
        leftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 右侧listView跳到当前左侧listView的位置
                rightListView.setSelection(position);
                // 通知适配器刷新位置
                rightLvAdapter.notifyDataSetInvalidated();
            }
        });
    }

    // 解析成功方法
    @Override
    public void stringSuccess(String result) {
        Gson gson = new Gson();
        List<CiFmSingleLvBean.DataBean.CategoriesBean> titleDatas = new ArrayList<>();
        CiFmSingleLvBean bean = gson.fromJson(result, CiFmSingleLvBean.class);
        for (int i = 0; i < bean.getData().getCategories().size(); i++) {
            titleDatas.add(bean.getData().getCategories().get(i));
        }
        leftLvAdapter = new CiSingleLeftLvAdapter(context);
        leftLvAdapter.setDatas(titleDatas);
        leftListView.setAdapter(leftLvAdapter);

        rightLvAdapter = new CiSingleRightLvAdapter(context);
        rightLvAdapter.setDatas(titleDatas);
        rightListView.setAdapter(rightLvAdapter);



    }

    @Override
    public void stringFailure() {

    }
}
