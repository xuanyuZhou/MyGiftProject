package com.example.dllo.mygiftproject.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.tools.DataCleanManager;

/**
 * Created by dllo on 16/7/28.
 * 设置页activity
 */
public class SetupActivity extends AbsBaseActivity implements View.OnClickListener {
    private LinearLayout cleanLinearLayout;
    private TextView showData;
    private TextView callPhoneTv;
    private LinearLayout gradeLayout;
    @Override
    protected int setLayout() {
        return R.layout.activity_setup;
    }

    @Override
    protected void initView() {
        cleanLinearLayout = byView(R.id.cleanDataLl);
        showData = byView(R.id.showDataTv);
        callPhoneTv = byView(R.id.callPhoneTv);
        gradeLayout = byView(R.id.grade_layout);

    }

    @Override
    protected void initListeners() {
        cleanLinearLayout.setOnClickListener(this);
        callPhoneTv.setOnClickListener(this);
        gradeLayout.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        try {
            showData.setText(DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cleanDataLl:
                DataCleanManager.clearAllCache(this);
                Toast.makeText(this, "缓存已清除", Toast.LENGTH_SHORT).show();
                showData.setText("0K");
                break;
            case R.id.callPhoneTv:
                showDialog();
                break;
            case R.id.grade_layout:
                goTo(this,GradeActivity.class);
                break;
        }
    }

    private void showDialog() {
        AlertDialog.Builder  builder = new AlertDialog.Builder(this);
        // 内容
        builder.setMessage("拨打礼物说客服电话");


        // 确定
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // 隐式intent实现拨号功能
                Intent intent = new Intent();
                intent.setAction(intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:4009992053"));
                startActivity(intent);

            }
        });
        // 取消
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
    }
}
