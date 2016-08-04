package com.example.dllo.mygiftproject.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.db.DBTool;
import com.example.dllo.mygiftproject.db.LikeBean;
import com.example.dllo.mygiftproject.ui.activity.HeadPhotoActivity;
import com.example.dllo.mygiftproject.ui.activity.JumpWebActivity;
import com.example.dllo.mygiftproject.ui.activity.ScannerActivity;
import com.example.dllo.mygiftproject.ui.activity.SetupActivity;
import com.example.dllo.mygiftproject.ui.adapter.CollectionLvAdapter;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/7/11.
 * 我的页总Fragment
 */
public class MyFragment extends AbsBaseFragment implements View.OnClickListener, AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {

    private List<LikeBean> datas;
    private CollectionLvAdapter lvAdapter;
    private ListView listView;
    private TextView userTv;
    private ImageView setupIv;
    private String userName;
    private SharedPreferences sp;
    private CircleImageView authorIv;
    private ImageButton goScanner;

    @Override
    protected int setLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {
        listView = byView(R.id.collection_lv);
        userTv = byView(R.id.myFm_userTv);
        setupIv = byView(R.id.imageButton_setting);
        authorIv = byView(R.id.authorIv);
        goScanner = byView(R.id.goScanner);

    }

    @Override
    protected void initDatas() {

        sp = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        userName = sp.getString("username", "6");
        if (sp.getBoolean("isLogin", false)) {
            userTv.setText(userName);
        }
        if (!userName.equals("6")) {
            datas = DBTool.getInstance().queryBy(userName);
        }
        lvAdapter = new CollectionLvAdapter(context);
        lvAdapter.setDatas(datas);
        listView.setAdapter(lvAdapter);
//        listView.setClickable(true);
        listView.setOnItemLongClickListener(this);
        listView.setOnItemClickListener(this);

        setupIv.setOnClickListener(this);
        authorIv.setOnClickListener(this);
        goScanner.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButton_setting:
                goTo(context, SetupActivity.class);
                break;
            case R.id.authorIv:
                goTo(context, HeadPhotoActivity.class);
                break;
            case R.id.goScanner:
                goTo(context, ScannerActivity.class);
                break;

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        String paths = sp.getString("path" + userName, "noLogin");
        if (!paths.equals("noLogin")) {
            authorIv.setImageBitmap(BitmapFactory.decodeFile(paths));
        }
        String imageUrl = sp.getString("imageUrl" + userName, "666");
        if (!imageUrl.equals("666")) {
            Picasso.with(context).load(imageUrl).into(authorIv);
        }

    }

    @Subscribe
    public void onReceive(String path) {
        DBTool.getInstance().queryBy(path);
        String paths = sp.getString("path" + userName, "noLogin");
        if (!paths.equals("noLogin")) {
            authorIv.setImageBitmap(BitmapFactory.decodeFile(paths));
        } else {
            authorIv.setImageBitmap(BitmapFactory.decodeFile(path));

        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        LikeBean bean = (LikeBean) parent.getItemAtPosition(position);
        DBTool.getInstance().delete(bean);
        lvAdapter.deleteItem(position);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LikeBean bean = (LikeBean) parent.getItemAtPosition(position);
        Bundle bundle = new Bundle();
        bundle.putString("url", bean.getNextUrl());
        goTo(context, JumpWebActivity.class, bundle);


    }
}
