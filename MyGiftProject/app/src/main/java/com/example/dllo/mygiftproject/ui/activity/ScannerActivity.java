package com.example.dllo.mygiftproject.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dllo.mygiftproject.R;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

/**
 * Created by dllo on 16/8/3.
 */
public class ScannerActivity extends AbsBaseActivity implements View.OnClickListener {
    private Button btn_scanner, btn_build;
    private int REQUEST_CODE;
    private EditText et_input;
    private ImageView iv_output;
    private ImageButton imageButtonBack;

    @Override
    protected int setLayout() {
        return R.layout.activity_scanner;
    }

    @Override
    protected void initView() {
        btn_scanner = byView(R.id.btn_scanner);
        btn_build = byView(R.id.btn_build);
        et_input = byView(R.id.et_input);
        iv_output = byView(R.id.iv_output);
        imageButtonBack = byView(R.id.ib_bannerGo_left);
    }

    @Override
    protected void initListeners() {
        imageButtonBack.setOnClickListener(this);
    }


    @Override
    protected void initDatas() {
        btn_scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScannerActivity.this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        btn_build.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textContent = et_input.getText().toString();
                if (TextUtils.isEmpty(textContent)) {
                    Toast.makeText(ScannerActivity.this, "您的输入为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                et_input.setText("");
                Bitmap mBitmap = CodeUtils.createImage(textContent, 400, 400, null);
                iv_output.setImageBitmap(mBitmap);

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(ScannerActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_bannerGo_left:
                finish();
        }
    }
}