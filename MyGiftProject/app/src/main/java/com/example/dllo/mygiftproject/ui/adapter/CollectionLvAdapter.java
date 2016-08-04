package com.example.dllo.mygiftproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.db.LikeBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/7/26.
 */
public class CollectionLvAdapter extends BaseAdapter {
    private List<LikeBean> datas;
    private Context context;

    public CollectionLvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<LikeBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void deleteItem(int position){
        datas.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas != null ? datas.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return datas != null ? datas.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CollectionLvHolder lvHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_startegy_column_lv,parent,false);
            lvHolder = new CollectionLvHolder(convertView);
            convertView.setTag(lvHolder);
        } else {
            lvHolder = (CollectionLvHolder) convertView.getTag();
        }
        lvHolder.likesCount.setText(datas.get(position).getLikesCount());
        lvHolder.title.setText(datas.get(position).getTitle());
        Picasso.with(context).load(datas.get(position).getImageUrl()).into(lvHolder.image);

        return convertView;
    }
    class CollectionLvHolder {
        private TextView nikeName,likesCount,title;
        private ImageView image;
        public CollectionLvHolder(View view) {
            nikeName = (TextView) view.findViewById(R.id.startegyColumnItem_nikeName);
            likesCount = (TextView) view.findViewById(R.id.startegyColumnItem_likesCount);
            image = (ImageView) view.findViewById(R.id.startegyColumnItem_image);
            title = (TextView) view.findViewById(R.id.startegyColumnItem_title);
        }
    }
}
