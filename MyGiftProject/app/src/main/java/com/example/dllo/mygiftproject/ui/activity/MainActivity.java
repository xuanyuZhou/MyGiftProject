package com.example.dllo.mygiftproject.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.ui.fragment.ClassifyFragment;
import com.example.dllo.mygiftproject.ui.fragment.GuideFragment;
import com.example.dllo.mygiftproject.ui.fragment.HotFragment;
import com.example.dllo.mygiftproject.ui.fragment.MyFragment;

public class MainActivity extends AbsBaseActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup mainRadioGroup;
    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mainRadioGroup = byView(R.id.main_radioGroup);
    }

    @Override
    protected void initListeners() {
        mainRadioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    protected void initDatas() {
        // 默认进第一个radioButton
        mainRadioGroup.check(R.id.guide_radioButton);
    }

    // radioGroup点击事件
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        // 初始化fragment管理者  业务员;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // 点击事件
        switch (checkedId) {
            case R.id.guide_radioButton:
                // 参数1 被替换布局的ID 占位布局
                // 参数2 被替换的fragment 的对象
                fragmentTransaction.replace(R.id.main_frameLayout,new GuideFragment());
                // ******  公司基本上用 hinde(隐藏)和show(显示)来控制fragment的切换
                break;
            case R.id.hot_radioButton:
                fragmentTransaction.replace(R.id.main_frameLayout,new HotFragment());
                break;
            case R.id.classify_radioButton:
                fragmentTransaction.replace(R.id.main_frameLayout,new ClassifyFragment());
                break;
            case R.id.my_radioButton:
                fragmentTransaction.replace(R.id.main_frameLayout,new MyFragment());
                break;
            default:
                break;
        }
        // 提交业务
        fragmentTransaction.commit();
    }
}
