package com.example.dllo.mygiftproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.model.bean.ClassifyJumpBean;
import com.example.dllo.mygiftproject.model.net.SingleImageLoader;

/**
 * Created by dllo on 16/7/20.
 */
public class ClassifyJumpLvAdapter extends BaseAdapter {
    private ClassifyJumpBean data;
    private Context context;

    public ClassifyJumpLvAdapter(Context context) {
        this.context = context;
    }

    public void setData(ClassifyJumpBean data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data != null ? data.getData().getItems().size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return data != null ? data.getData().getItems().get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ClassifyJumpHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_classify_jump_activity_lv,parent,false);
            holder = new ClassifyJumpHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ClassifyJumpHolder) convertView.getTag();
        }
        holder.title.setText(data.getData().getItems().get(position).getTitle());
        holder.likesCount.setText(String.valueOf(data.getData().getItems().get(position).getLikes_count()));
        SingleImageLoader.loaderImage(data.getData().getItems().get(position).getCover_image_url(),holder.image,context);
        return convertView;
    }

    class ClassifyJumpHolder {
        private ImageView image;
        private TextView title, likesCount;

        public ClassifyJumpHolder(View view) {
            image = (ImageView) view.findViewById(R.id.classifyActivity_lvItem_image);
            title = (TextView) view.findViewById(R.id.classifyActivity_lvItem_title);
            likesCount = (TextView) view.findViewById(R.id.classifyActivity_lvItem_likesCount);
        }
    }
}
