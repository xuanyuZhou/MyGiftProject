package com.example.dllo.mygiftproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.model.bean.CiFmSingleLvBean;

import java.util.List;

/**
 * Created by dllo on 16/7/16.
 * 分类页 单品 左边listView 适配器
 */
public class CiSingleLeftLvAdapter extends BaseAdapter {
    private List<CiFmSingleLvBean.DataBean.CategoriesBean> datas;
    private Context context;

    public CiSingleLeftLvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<CiFmSingleLvBean.DataBean.CategoriesBean> datas) {
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
        CiSingleLvHolder lvHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.classify_single_left_lv_item,parent,false);
            lvHolder = new CiSingleLvHolder(convertView);
            convertView.setTag(lvHolder);
        } else {
            lvHolder = (CiSingleLvHolder) convertView.getTag();
        }
        lvHolder.titleTv.setText(datas.get(position).getName());
        return convertView;
    }
    class CiSingleLvHolder {
        TextView titleTv;
        public CiSingleLvHolder(View view) {
            titleTv = (TextView) view.findViewById(R.id.classify_single_leftLv_titleTv);
        }
    }
}
