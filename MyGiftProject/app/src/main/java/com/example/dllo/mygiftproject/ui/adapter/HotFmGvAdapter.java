package com.example.dllo.mygiftproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.model.bean.LocalHotFmGvBean;
import com.example.dllo.mygiftproject.model.net.VolleyInstance;

import java.util.List;

/**
 * Created by dllo on 16/7/14.
 */
public class HotFmGvAdapter extends BaseAdapter {
    private List<LocalHotFmGvBean> datas;
    private Context context;

    public HotFmGvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<LocalHotFmGvBean> datas) {
        this.datas = datas;
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
        HotFmGvHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.hot_item_gv,parent,false);
            holder = new HotFmGvHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (HotFmGvHolder) convertView.getTag();
        }
        LocalHotFmGvBean bean = datas.get(position);
        holder.nameTv.setText(bean.getName());
        holder.priceTv.setText(bean.getPrice());
        holder.likesCountTv.setText(bean.getLikesCount());
        VolleyInstance.getInstance(context).loaderImage(bean.getImageUrl(),holder.coverImgIv,context);
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
