package com.example.dllo.mygiftproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.model.bean.ColumnActivityBean;
import com.example.dllo.mygiftproject.model.net.SingleImageLoader;

/**
 * Created by dllo on 16/7/20.
 * 分类 攻略上方recyclerView跳转到得activity中lv的适配器
 */
public class StrategyColumnLvAdapter extends BaseAdapter {
    private ColumnActivityBean data;
    private Context context;

    public StrategyColumnLvAdapter(Context context) {
        this.context = context;
    }

    public void setData(ColumnActivityBean data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data != null ? data.getData().getPosts().size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return data != null ? data.getData().getPosts().get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StrategyColumnHolder lvHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_startegy_column_lv,parent,false);
            lvHolder = new StrategyColumnHolder(convertView);
            convertView.setTag(lvHolder);
        } else {
            lvHolder = (StrategyColumnHolder) convertView.getTag();
        }
        lvHolder.nikeName.setText(data.getData().getPosts().get(position).getAuthor().getNickname());
        lvHolder.likesCount.setText(String.valueOf(data.getData().getPosts().get(position).getLikes_count()));
        lvHolder.title.setText(data.getData().getPosts().get(position).getTitle());
        SingleImageLoader.loaderImage(data.getData().getPosts().get(position).getCover_image_url(),lvHolder.image,context);
        return convertView;
    }

    class StrategyColumnHolder {
        private TextView nikeName,likesCount,title;
        private ImageView image;
        public StrategyColumnHolder(View view) {
            nikeName = (TextView) view.findViewById(R.id.startegyColumnItem_nikeName);
            likesCount = (TextView) view.findViewById(R.id.startegyColumnItem_likesCount);
            image = (ImageView) view.findViewById(R.id.startegyColumnItem_image);
            title = (TextView) view.findViewById(R.id.startegyColumnItem_title);
        }
    }
}
