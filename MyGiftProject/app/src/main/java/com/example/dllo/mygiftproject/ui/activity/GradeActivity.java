package com.example.dllo.mygiftproject.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.dllo.mygiftproject.R;

/**
 * Created by dllo on 16/7/28.
 */
public class GradeActivity  extends AbsBaseActivity implements View.OnClickListener, RatingBar.OnRatingBarChangeListener {
    private RatingBar ratingBar;
    private Button grade_Btn;

    @Override
    protected int setLayout() {
        return R.layout.activity_grade;
    }

    @Override
    protected void initView() {
        ratingBar = byView(R.id.grade_rB);
        grade_Btn = byView(R.id.grade_Btn);
    }

    @Override
    protected void initListeners() {
        ratingBar.setOnRatingBarChangeListener(this);
        grade_Btn.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "感谢您的评分", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        Toast.makeText(this, "您的评分是:"+ String.valueOf(rating)+"分,请点击确定 谢谢~", Toast.LENGTH_SHORT).show();
    }
}
