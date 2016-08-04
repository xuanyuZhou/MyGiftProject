package com.example.dllo.mygiftproject.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.db.DBTool;
import com.example.dllo.mygiftproject.db.LikeBean;
import com.example.dllo.mygiftproject.model.bean.GuideFirstLvBean;
import com.example.dllo.mygiftproject.ui.activity.LoginActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/13.
 * 指南页第一个fragment的listView适配器
 */
public class GuideFirstFmLvAdapter extends BaseAdapter {
    private GuideFirstLvBean datas;
    private Context context;
    // 是否喜欢
    private List<Boolean> isLikes;
    private boolean heartLikes;
    // 当前登录的账号
    private String username;

    public GuideFirstFmLvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(GuideFirstLvBean datas) {
        this.datas = datas;
        notifyDataSetChanged();
        isLikes = new ArrayList<>();
        for (int i = 0; i < datas.getData().getItems().size(); i++) {
            isLikes.add(false);
        }
    }

    @Override
    public int getCount() {
        return datas != null ? datas.getData().getItems().size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return datas != null ? datas.getData().getItems().get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        GuideFirstFmLvHolder lvHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.guide_item_lv, parent, false);
            lvHolder = new GuideFirstFmLvHolder(convertView);
            convertView.setTag(lvHolder);
        } else {
            lvHolder = (GuideFirstFmLvHolder) convertView.getTag();
        }
        lvHolder.goneRl.setVisibility(View.GONE);
        // 点赞功能
        final GuideFirstFmLvHolder finalLvHolder = lvHolder;
        lvHolder.heart.setOnClickListener(new View.OnClickListener() {

            private LikeBean bean;

            @Override
            public void onClick(View v) {
                heartLikes = isLikes.get(position);
                SharedPreferences sp = context.getSharedPreferences("user",Context.MODE_PRIVATE);
                username = sp.getString("username","6");
                if (!username.equals("6")) {
                    if (!heartLikes) {
                        finalLvHolder.heart.setImageResource(R.mipmap.ic_action_compact_favourite_selected);
                        Toast.makeText(context, "喜欢成功", Toast.LENGTH_SHORT).show();
                        finalLvHolder.likesCountTv.setText(
                                String.valueOf((datas.getData().getItems().get(position).getLikes_count() + 1)));
                        isLikes.set(position,true);
                        // 添加到数据库
                        bean = new LikeBean();
                        bean.setTitle(datas.getData().getItems().get(position).getTitle());
                        bean.setLikesCount(String.valueOf(datas.getData().getItems().get(position).getLikes_count()));
                        bean.setImageUrl(datas.getData().getItems().get(position).getCover_image_url());
                        bean.setUserName(username);
                        bean.setNextUrl(datas.getData().getItems().get(position).getUrl());
                        DBTool.getInstance().insert(bean);
                    } else {
                        finalLvHolder.heart.setImageResource(R.mipmap.ic_feed_favourite_normal);
                        finalLvHolder.likesCountTv.setText(
                                String.valueOf(datas.getData().getItems().get(position).getLikes_count()));
                        Toast.makeText(context, "取消喜欢", Toast.LENGTH_SHORT).show();
                        isLikes.set(position,false);
                        // 从数据库删除
                        DBTool.getInstance().delete(bean);
                    }
                } else {
                    context.startActivity(new Intent(context, LoginActivity.class));
                }
            }
        });
        lvHolder.titleTv.setText(datas.getData().getItems().get(position).getTitle());
        lvHolder.likesCountTv.setText(String.valueOf(datas.getData().getItems().get(position).getLikes_count()));
        Picasso.with(context).load(datas.getData().getItems().get(position).getCover_image_url()).into(lvHolder.covertIv);
        return convertView;
    }

    class GuideFirstFmLvHolder {
        private TextView titleTv, likesCountTv;
        private ImageView covertIv;
        private ImageView heart;
        private RelativeLayout goneRl;

        public GuideFirstFmLvHolder(View view) {
            goneRl = (RelativeLayout) view.findViewById(R.id.gone_title);
            heart = (ImageView) view.findViewById(R.id.heart);
            titleTv = (TextView) view.findViewById(R.id.guideItemLv_title);
            likesCountTv = (TextView) view.findViewById(R.id.guideItemLv_likesCount);
            covertIv = (ImageView) view.findViewById(R.id.guideItemLv_contentImageView);
        }
    }
}
