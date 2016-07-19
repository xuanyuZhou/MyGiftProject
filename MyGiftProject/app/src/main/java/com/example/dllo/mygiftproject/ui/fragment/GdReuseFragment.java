package com.example.dllo.mygiftproject.ui.fragment;

import android.os.Bundle;
import android.widget.ListView;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.model.bean.GuideReuseLvBean;
import com.example.dllo.mygiftproject.model.bean.LocalGuideReuseLvBean;
import com.example.dllo.mygiftproject.model.net.VolleyInstance;
import com.example.dllo.mygiftproject.model.net.VolleyPort;
import com.example.dllo.mygiftproject.ui.adapter.GuideReuseFmLvAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/12.
 * 指南页复用fragment
 */
public class GdReuseFragment extends AbsBaseFragment implements VolleyPort {
    // 初始化URL
    private String reuseUrl;
    // 创建装本地实体类的集合
    List<LocalGuideReuseLvBean> localGuideReuseLvBeanArray;
    private GuideReuseFmLvAdapter lvAdapter;
    private ListView gdReuseListView;

    // 对外提供一个静态的 返回fragment对象的方法
    public static GdReuseFragment getReuseFragments(String url){
        // 初始化fragment
        GdReuseFragment gdReuseFragment = new GdReuseFragment();
        Bundle bundle = new Bundle(); // 初始化bundle
        bundle.putString("url",url); // 加入参数中的url
        gdReuseFragment.setArguments(bundle); // 通过setArgunments方法传bundle
        return gdReuseFragment; // 返回framgent
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_gd_reuse;
    }

    @Override
    protected void initView() {
        gdReuseListView = byView(R.id.gdReuseFM_lv);
    }

    @Override
    protected void initDatas() {
        Bundle bundle = getArguments();
        this.reuseUrl = bundle.getString("url");
        VolleyInstance.getInstance(context).startStringRequest(reuseUrl,this);
    }

    @Override
    public void stringSuccess(String result) {
        Gson gson = new Gson();
        GuideReuseLvBean guideReuseLvBean = gson.fromJson(result,GuideReuseLvBean.class);
        List<GuideReuseLvBean.DataBean.ItemsBean> lvData = guideReuseLvBean.getData().getItems();
        // 初始化要传入适配器的集合
        localGuideReuseLvBeanArray = new ArrayList<>();
        for (int i = 0; i < lvData.size(); i++) {
            LocalGuideReuseLvBean bean = new LocalGuideReuseLvBean();
            // 链式编程导入数据进入实体类
            bean.setLikesCount(String.valueOf(lvData.get(i).getLikes_count())).
                    setTitle(lvData.get(i).getTitle()).
                    setImageUrl(lvData.get(i).getCover_image_url()).
                    setNickName(lvData.get(i).getAuthor().getNickname()).
                    setAvatarUrl(lvData.get(i).getAuthor().getAvatar_url()).
                    setShortTitle(lvData.get(i).getColumn().getTitle()).
                    setCategory(lvData.get(i).getColumn().getCategory());
            localGuideReuseLvBeanArray.add(bean);
        }
        lvAdapter = new GuideReuseFmLvAdapter(context);
        lvAdapter.setDatas(localGuideReuseLvBeanArray);
        gdReuseListView.setAdapter(lvAdapter);
    }

    @Override
    public void stringFailure() {

    }
}
