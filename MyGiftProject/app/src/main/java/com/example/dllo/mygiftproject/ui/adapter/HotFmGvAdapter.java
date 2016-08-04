package com.example.dllo.mygiftproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.model.bean.HotFmGvBean;
import com.example.dllo.mygiftproject.model.net.VolleyInstance;

/**
 * Created by dllo on 16/7/14.
 * 热门页fragment的gridView适配器
 */
public class HotFmGvAdapter extends BaseAdapter {
    private HotFmGvBean datas;
    private Context context;

    public HotFmGvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(HotFmGvBean datas) {
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
        HotFmGvHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.hot_item_gv,parent,false);
            holder = new HotFmGvHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (HotFmGvHolder) convertView.getTag();
        }
        holder.nameTv.setText(datas.getData().getItems().get(position).getData().getName());
        holder.priceTv.setText(String.valueOf(datas.getData().getItems().get(position).getData().getPrice()));
        holder.likesCountTv.setText(String.valueOf(datas.getData().getItems().get(position).getData().getFavorites_count()));
        VolleyInstance.getInstance(context).loaderImage(datas.getData().getItems().get(position).getData().getCover_image_url(),holder.coverImgIv,context);
        return convertView;
    }

    class HotFmGvHolder {
        private TextView nameTv, likesCountTv, priceTv;
        private ImageView coverImgIv;

        public HotFmGvHolder(View view) {
            nameTv = (TextView) view.findViewById(R.id.hotFmGv_nameTv);
            likesCountTv = (TextView) view.findViewById(R.id.hotFmGv_likesCountTv);
            priceTv = (TextView) view.findViewById(R.id.hotFmGv_priceTv);
            coverImgIv = (ImageView) view.findViewById(R.id.hotFmGv_coverImgIv);
        }
    }
}
