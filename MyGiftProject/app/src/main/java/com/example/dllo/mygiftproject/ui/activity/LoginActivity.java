package com.example.dllo.mygiftproject.ui.activity;

import android.view.View;
import android.widget.ImageButton;
import com.example.dllo.mygiftproject.R;

/**
 * Created by dllo on 16/7/13.
 * 登录页activity
 */
public class LoginActivity extends AbsBaseActivity implements View.OnClickListener {
    private ImageButton loginCloseBtn;
    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        loginCloseBtn = byView(R.id.btn_loginClose);
    }

    @Override
    protected void initListeners() {
        loginCloseBtn.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_loginClose:
                goTo(this,MainActivity.class);
                break;
        }
    }
}
