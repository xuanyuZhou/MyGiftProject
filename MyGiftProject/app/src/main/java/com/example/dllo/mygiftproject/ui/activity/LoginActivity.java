package com.example.dllo.mygiftproject.ui.activity;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.mygiftproject.R;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by dllo on 16/7/13.
 * 登录页activity
 */
public class LoginActivity extends AbsBaseActivity implements View.OnClickListener {
    private ImageButton loginCloseBtn, qqBtn;
    private EditText userNameEd, passWordEd;
    private TextView rMessage, rPassword;
    private Button loginBtn, zhuCeBtn;
    private String userName, passWord; // 账号和密码
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private PlatformActionListener plListener = new PlatformActionListener() {
        @Override
        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            String imageUrl = platform.getDb().getUserIcon();
            userName = platform.getDb().getUserName();
            editor.putString("imageUrl" + userName,imageUrl);
            editor.putString("username", userName); // 记录现在登录的是谁
            editor.putBoolean("isLogin", true); // 记录是否登录
            editor.commit();
            goTo(LoginActivity.this, MainActivity.class);
        }

        @Override
        public void onError(Platform platform, int i, Throwable throwable) {
            Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(Platform platform, int i) {
            Toast.makeText(LoginActivity.this, "取消登录", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        ShareSDK.initSDK(this, "1542d974d25eb");
        loginCloseBtn = byView(R.id.btn_loginClose);
        userNameEd = byView(R.id.userNameEd);
        passWordEd = byView(R.id.passWordEd);
        rMessage = byView(R.id.registeredMessage);
        rPassword = byView(R.id.registeredPassword);
        loginBtn = byView(R.id.btn_login);
        zhuCeBtn = byView(R.id.btn_ZhuCe);
        qqBtn = byView(R.id.QqBtn);
    }

    @Override
    protected void initListeners() {
        loginCloseBtn.setOnClickListener(this);
        userNameEd.setOnClickListener(this);
        passWordEd.setOnClickListener(this);
        rMessage.setOnClickListener(this);
        rPassword.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        zhuCeBtn.setOnClickListener(this);
        qqBtn.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        if (sp == null) {
            sp = getSharedPreferences("user", MODE_PRIVATE);
            editor = sp.edit();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_loginClose:
                goTo(this, MainActivity.class);
                break;
            // 用户名 栏
            case R.id.userNameEd:
                break;
            // 密码 栏
            case R.id.passWordEd:
                break;
            // 短信 注册 字
            case R.id.registeredMessage:
                rMessage.setVisibility(View.GONE);
                rPassword.setVisibility(View.VISIBLE);
                loginBtn.setVisibility(View.GONE);
                zhuCeBtn.setVisibility(View.VISIBLE);
                break;
            // 密码 登录 字
            case R.id.registeredPassword:
                rMessage.setVisibility(View.VISIBLE);
                rPassword.setVisibility(View.GONE);
                loginBtn.setVisibility(View.VISIBLE);
                zhuCeBtn.setVisibility(View.GONE);
                break;
            // 登录按钮
            case R.id.btn_login:
                userName = userNameEd.getText().toString();
                passWord = passWordEd.getText().toString();
                if (userName.equals(sp.getString(userName, "")) && passWord.equals(sp.getString(userName + "password", ""))) {
                    editor.putString("username", userName); // 记录现在登录的是谁
                    editor.putBoolean("isLogin", true); // 记录是否登录
                    editor.commit();
                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                }
                goTo(LoginActivity.this,MainActivity.class);
                finish();
                break;
            // 注册按钮
            case R.id.btn_ZhuCe:
                userName = userNameEd.getText().toString();
                passWord = passWordEd.getText().toString();

                if (!userName.equals(sp.getString(userName, "")) && !sp.getString(userName, "").equals(null)) {
                    editor.putString(userName, userName);
                    editor.putString(userName + "password", passWord);
                    editor.commit();
                    Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "该账号已存在", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.QqBtn:
                // qq三方登录 (通过mob)
                Platform qq = ShareSDK.getPlatform(this, QQ.NAME);
                qq.setPlatformActionListener(plListener);

                qq.SSOSetting(true);
                qq.authorize();
                break;

        }
    }
}
