package com.example.dllo.mygiftproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.model.bean.GuideFirstLvBean;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/7/13.
 * 指南页第一个fragment的listView适配器
 */
public class GuideFirstFmLvAdapter extends BaseAdapter {
    private GuideFirstLvBean datas;
    private Context context;

    public GuideFirstFmLvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(GuideFirstLvBean datas) {
        this.datas = datas;
        notifyDataSetChanged();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        GuideFirstFmLvHolder lvHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.guide_item_lv,parent,false);
            lvHolder = new GuideFirstFmLvHolder(convertView);
            convertView.setTag(lvHolder);
        } else {
            lvHolder = (GuideFirstFmLvHolder) convertView.getTag();
        }
        lvHolder.titleTv.setText(datas.getData().getItems().get(position).getTitle());
        lvHolder.likesCountTv.setText(String.valueOf(datas.getData().getItems().get(position).getLikes_count()));
        Picasso.with(context).load(datas.getData().getItems().get(position).getCover_image_url()).into(lvHolder.covertIv);
        return convertView;
    }
    class GuideFirstFmLvHolder {
        private TextView titleTv, likesCountTv;
        private ImageView covertIv;
        public GuideFirstFmLvHolder(View view) {
            titleTv = (TextView) view.findViewById(R.id.guideItemLv_title);
            likesCountTv = (TextView) view.findViewById(R.id.guideItemLv_likesCount);
            covertIv = (ImageView) view.findViewById(R.id.guideItemLv_contentImageView);
        }
    }
}
