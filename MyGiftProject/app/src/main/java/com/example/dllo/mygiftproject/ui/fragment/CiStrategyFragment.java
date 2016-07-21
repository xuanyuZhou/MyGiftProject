package com.example.dllo.mygiftproject.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.model.bean.CiFmStrategyRvBean;
import com.example.dllo.mygiftproject.model.bean.CiStrategyGvBean;
import com.example.dllo.mygiftproject.model.bean.RunnableDocumentBean;
import com.example.dllo.mygiftproject.model.net.VolleyInstance;
import com.example.dllo.mygiftproject.model.net.VolleyPort;
import com.example.dllo.mygiftproject.ui.activity.ClassifyJumpActivity;
import com.example.dllo.mygiftproject.ui.activity.StrategyColumnActivity;
import com.example.dllo.mygiftproject.ui.adapter.CiFmStrategyGvAdapter;
import com.example.dllo.mygiftproject.ui.adapter.CiFmStrategyRvAdapter;
import com.example.dllo.mygiftproject.ui.adapter.GdFmRvOnclick;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/15.
 * 分类页攻略fragment
 */
public class CiStrategyFragment extends AbsBaseFragment implements VolleyPort, GdFmRvOnclick, AdapterView.OnItemClickListener {
    private int type; // 判断网络解析数据属于哪个对象的type;
    private RecyclerView sortRecyclerView;
    private CiFmStrategyRvAdapter rvAdapter;
    private String rvUrl = RunnableDocumentBean.CI_FM_ST_RV_URL; // 上方栏目的接口
    // 下方3个gv相关实例化
    private String gvUrl = RunnableDocumentBean.CI_FM_ST_GV_URL; // 下方三个模块的接口
    private GridView classGridView, styleGridView, peopleGridView; // 三个gv的对象
    private CiFmStrategyGvAdapter gvAdapter;
    private List<CiStrategyGvBean.DataBean.ChannelGroupsBean.ChannelsBean> classBeanArray;
    private List<CiStrategyGvBean.DataBean.ChannelGroupsBean.ChannelsBean> styleBeanArray;
    private List<CiStrategyGvBean.DataBean.ChannelGroupsBean.ChannelsBean> peopleBeanArray;
    private CiFmStrategyRvBean rvBean;


    @Override
    protected int setLayout() {
        return R.layout.fragment_ci_strategy;
    }

    @Override
    protected void initView() {
        sortRecyclerView = byView(R.id.ciFmStrategy_recyclerView);
        classGridView = byView(R.id.ciStrategyClassGv);
        styleGridView = byView(R.id.ciStrategyStyleGv);
        peopleGridView = byView(R.id.ciStrategyPeopleGv);
    }

    @Override
    protected void initDatas() {
        type = 0;
        VolleyInstance.getInstance(context).startStringRequest(rvUrl, this);
        // gridView监听
        classGridView.setOnItemClickListener(this);
        styleGridView.setOnItemClickListener(this);
        peopleGridView.setOnItemClickListener(this);

    }

    @Override
    public void stringSuccess(String result) {
        Gson gson = new Gson();
        switch (type) {
            case 0:
                rvBean = gson.fromJson(result, CiFmStrategyRvBean.class);
                // 初始化,绑定适配器 传数据
                rvAdapter = new CiFmStrategyRvAdapter(context);
                rvAdapter.setDatas(rvBean);
                sortRecyclerView.setAdapter(rvAdapter);
                // 初始化grid布局管理者  设置3行
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
                gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                sortRecyclerView.setLayoutManager(gridLayoutManager);
                // rv监听事件
                rvAdapter.setGdFmRvOnclick(this);
                // 上方运行成功 继续解析下一个
                type = 1;
                VolleyInstance.getInstance(context).startStringRequest(gvUrl, this);
                break;
            case 1:
                classBeanArray = new ArrayList<>();
                styleBeanArray = new ArrayList<>();
                peopleBeanArray = new ArrayList<>();
                CiStrategyGvBean gvBean = gson.fromJson(result, CiStrategyGvBean.class);
                for (int i = 0; i < gvBean.getData().getChannel_groups().size(); i++) {
                    CiStrategyGvBean.DataBean.ChannelGroupsBean data = gvBean.getData().getChannel_groups().get(i);
                    switch (i) {
                        case 0:
                            for (int i1 = 0; i1 < data.getChannels().size(); i1++) {
                                classBeanArray.add(data.getChannels().get(i1));
                            }
                            break;
                        case 1:
                            for (int i1 = 0; i1 < data.getChannels().size(); i1++) {
                                styleBeanArray.add(data.getChannels().get(i1));
                            }
                            break;
                        case 2:
                            for (int i1 = 0; i1 < data.getChannels().size(); i1++) {
                                peopleBeanArray.add(data.getChannels().get(i1));
                            }
                            break;
                    }
                    // 初始化适配器 set数据 绑定适配器  每次绑定都要初始化 否则数据只显示最后一个set的
                    gvAdapter = new CiFmStrategyGvAdapter(context);
                    gvAdapter.setDatas(classBeanArray);
                    classGridView.setAdapter(gvAdapter);

                    gvAdapter = new CiFmStrategyGvAdapter(context);
                    gvAdapter.setDatas(styleBeanArray);
                    styleGridView.setAdapter(gvAdapter);

                    gvAdapter = new CiFmStrategyGvAdapter(context);
                    gvAdapter.setDatas(peopleBeanArray);
                    peopleGridView.setAdapter(gvAdapter);
                }
                break;
        }
    }

    @Override
    public void stringFailure() {

    }

    // rv 自定义监听接口回调方法
    @Override
    public void onClickListener(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("url", "http://api.liwushuo.com/v2/columns/"
                + String.valueOf(rvBean.getData().getColumns().get(position).getId()) + "?limit=20&offset=0");
        bundle.putString("title", rvBean.getData().getColumns().get(position).getTitle());
        goTo(context, StrategyColumnActivity.class, bundle);
    }

    /**
     * gridView 监听接口回调的方法
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // 区分3个gridView的判断
        Bundle bundle = new Bundle();
        switch (parent.getId()) {
            case R.id.ciStrategyClassGv:
                bundle.putString("title", classBeanArray.get(position).getName());
                bundle.putString("url", "http://api.liwushuo.com/v2/channels/"
                        + String.valueOf(classBeanArray.get(position).getId()) + "/items?limit=20&offset=0");
                goTo(context, ClassifyJumpActivity.class, bundle);
                break;
            case R.id.ciStrategyStyleGv:
                bundle.putString("title", styleBeanArray.get(position).getName());
                bundle.putString("url", "http://api.liwushuo.com/v2/channels/"
                        + String.valueOf(styleBeanArray.get(position).getId()) + "/items?limit=20&offset=0");
                goTo(context, ClassifyJumpActivity.class, bundle);
                break;
            case R.id.ciStrategyPeopleGv:
                bundle.putString("title", peopleBeanArray.get(position).getName());
                bundle.putString("url", "http://api.liwushuo.com/v2/channels/"
                        + String.valueOf(peopleBeanArray.get(position).getId()) + "/items?limit=20&offset=0");
                goTo(context, ClassifyJumpActivity.class, bundle);
                break;
        }
    }
}
