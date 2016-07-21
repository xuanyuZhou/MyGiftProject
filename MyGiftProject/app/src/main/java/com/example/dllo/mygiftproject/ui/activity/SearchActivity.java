package com.example.dllo.mygiftproject.ui.activity;

import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.ui.fragment.SearchFragment;

import java.io.UnsupportedEncodingException;

/**
 * Created by dllo on 16/7/21.
 * 点击放大镜 弹出的搜索界面 activity
 */
public class SearchActivity extends AbsBaseActivity implements View.OnClickListener {
    private SearchView searchView;
    private TextView textViewOne,textViewTwo,textViewThree,textViewFour,textViewFive,textViewSix,textViewSeven,textViewEight,textViewNight,textViewTen,textViewEleven,textViewTwelve;
    private ImageView onBack;
    private TextView goSearch;

    @Override
    protected int setLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        searchView = byView(R.id.searchActivity_searchView);
        onBack = byView(R.id.searchActivity_back);
        goSearch = byView(R.id.searchActivity_goSearch);
        // 小方块
        textViewOne = byView(R.id.searchActivity_textOne);
        textViewTwo = byView(R.id.searchActivity_textTwo);
        textViewThree = byView(R.id.searchActivity_textThree);
        textViewFour = byView(R.id.searchActivity_textFour);
        textViewFive = byView(R.id.searchActivity_textFive);
        textViewSix = byView(R.id.searchActivity_textSix);
        textViewSeven = byView(R.id.searchActivity_textSeven);
        textViewEight = byView(R.id.searchActivity_textEight);
        textViewNight = byView(R.id.searchActivity_textNight);
        textViewTen = byView(R.id.searchActivity_textTen);
        textViewEleven = byView(R.id.searchActivity_textEleven);
        textViewTwelve = byView(R.id.searchActivity_textTwelve);

    }

    @Override
    protected void initListeners() {
        onBack.setOnClickListener(this);
        goSearch.setOnClickListener(this);
        // 小方块监听
        textViewOne.setOnClickListener(this);
        textViewTwo.setOnClickListener(this);
        textViewThree.setOnClickListener(this);
        textViewFour.setOnClickListener(this);
        textViewFive.setOnClickListener(this);
        textViewSix.setOnClickListener(this);
        textViewSeven.setOnClickListener(this);
        textViewEight.setOnClickListener(this);
        textViewNight.setOnClickListener(this);
        textViewTen.setOnClickListener(this);
        textViewEleven.setOnClickListener(this);
        textViewTwelve.setOnClickListener(this);

        // 为搜索框设置监听事件
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            // 输入完成的字符串
            public boolean onQueryTextSubmit(String query) {
                parse(query);
                return false;
            }

            @Override
            // 正输入的的字符串
            public boolean onQueryTextChange(String newText) {
                parse(newText);
                return false;
            }
        });
    }

    private void parse(String newText){
        String s = null;
        try {
            s = new String(newText.getBytes(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "http://api.liwushuo.com/v2/search/item?keyword="+s+"&limit=20&offset=0&sort=";
        getSupportFragmentManager().
                beginTransaction().replace
                (R.id.searchActivity_frameLayout, SearchFragment.searchFragment(url)).commit();
    }

    @Override
    protected void initDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchActivity_back:
                finish();
                break;
            case R.id.searchActivity_goSearch:

                break;
            case R.id.searchActivity_textOne:
                break;
            case R.id.searchActivity_textTwo:
                break;
            case R.id.searchActivity_textThree:
                break;
            case R.id.searchActivity_textFour:
                break;
            case R.id.searchActivity_textFive:
                break;
            case R.id.searchActivity_textSix:
                break;
            case R.id.searchActivity_textSeven:
                break;
            case R.id.searchActivity_textEight:
                break;
            case R.id.searchActivity_textNight:
                break;
            case R.id.searchActivity_textTen:
                break;
            case R.id.searchActivity_textEleven:
                break;
            case R.id.searchActivity_textTwelve:
                break;
        }
    }
}
