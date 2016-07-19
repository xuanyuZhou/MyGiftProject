package com.example.dllo.mygiftproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.model.bean.SecondaryJumBean;
import com.example.dllo.mygiftproject.model.net.VolleyInstance;
import com.example.dllo.mygiftproject.model.net.VolleyPort;
import com.example.dllo.mygiftproject.ui.adapter.SecondaryJumpLvAdapter;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/7/18.
 * banner等轮播图 的二级界面 是一个activity
 * url采用拼接的方法 直接在此activity内解析
 * 此activity是一个复用的activity被多个点击事件做为二级跳转使用
 */
public class SecondaryJumpActivity extends AbsBaseActivity implements VolleyPort, View.OnClickListener {
    private String url; // 传过来的url,解析后显示在listView的数据
    private ListView jumpLv; // listView
    private ImageView jumpBack, jumpShare; // 上方返回按钮  分享按钮
    private TextView titleTv; // 上方的标题栏
    private PopupWindow popupWindow = new PopupWindow();

    @Override
    protected int setLayout() {
        return R.layout.activity_secondary_jump;
    }

    @Override
    protected void initView() {
        jumpLv = byView(R.id.secondaryJump_lv);
        jumpBack = byView(R.id.secondaryJump_back);
        jumpShare = byView(R.id.secondaryJump_share);
        titleTv = byView(R.id.secondaryJump_titleTv);
    }

    @Override
    protected void initListeners() {
        jumpBack.setOnClickListener(this);
        jumpShare.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        // 接收上一层fragment传来的url
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        this.url = bundle.getString("url");
        VolleyInstance.getInstance(this).startStringRequest(url, this);
    }

    @Override
    public void stringSuccess(String result) {
        Gson gson = new Gson();
        SecondaryJumBean bean = gson.fromJson(result, SecondaryJumBean.class);
        SecondaryJumpLvAdapter lvAdapter = new SecondaryJumpLvAdapter(this);
        titleTv.setText(bean.getData().getTitle());
        lvAdapter.setBean(bean);
        jumpLv.setAdapter(lvAdapter);

    }

    @Override
    public void stringFailure() {
        Toast.makeText(this, "解析失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 后退按钮的点击事件
            case R.id.secondaryJump_back:
                // 结束当前activity
                finish();
                break;
            case R.id.secondaryJump_share:
                showPopupWindow();
                popupWindow.showAsDropDown(jumpShare,0,1100);
                break;
        }
    }

    public void showPopupWindow(){
        popupWindow = new PopupWindow();
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(600);
        View view = getLayoutInflater().inflate(R.layout.popupwindow_activity_secondary_jump_share,null);
        popupWindow.setBackgroundDrawable(this.getResources().getDrawable(R.mipmap.ic_launcher));
        popupWindow.setContentView(view);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);

        // 这句话的效果是让我的popWindows变暗
        // 出现的时候我的背景颜色是半透明
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.alpha = 0.5f;
        getWindow().setAttributes(layoutParams);

        // 消失后我的背景颜色变回
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
    }
}
