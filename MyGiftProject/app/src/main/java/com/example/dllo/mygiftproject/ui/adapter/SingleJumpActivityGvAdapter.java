package com.example.dllo.mygiftproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.model.bean.SingleJumpActivityBean;
import com.example.dllo.mygiftproject.model.net.SingleImageLoader;

/**
 * Created by dllo on 16/7/21.
 */
public class SingleJumpActivityGvAdapter extends BaseAdapter {
    private SingleJumpActivityBean data;
    private Context context;

    public SingleJumpActivityGvAdapter(Context context) {
        this.context = context;
    }

    public void setBean(SingleJumpActivityBean data) {
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
        SingleJumpActivityGvHolder gvHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.hot_item_gv,parent,false);
            gvHolder = new SingleJumpActivityGvHolder(convertView);
            convertView.setTag(gvHolder);
        } else {
            gvHolder = (SingleJumpActivityGvHolder) convertView.getTag();
        }
        gvHolder.likesCount.setText(String.valueOf(data.getData().getItems().get(position).getFavorites_count()));
        gvHolder.price.setText(data.getData().getItems().get(position).getPrice());
        gvHolder.title.setText(data.getData().getItems().get(position).getName());
        SingleImageLoader.loaderImage(data.getData().getItems().get(position).getCover_image_url(),gvHolder.image,context);
        return convertView;
    }

    class SingleJumpActivityGvHolder {
        private ImageView image;
        private TextView title, price, likesCount;

        public SingleJumpActivityGvHolder(View view) {
            image = (ImageView) view.findViewById(R.id.hotFmGv_coverImgIv);
            title = (TextView) view.findViewById(R.id.hotFmGv_nameTv);
            price = (TextView) view.findViewById(R.id.hotFmGv_priceTv);
            likesCount = (TextView) view.findViewById(R.id.hotFmGv_likesCountTv);
        }
    }
}
