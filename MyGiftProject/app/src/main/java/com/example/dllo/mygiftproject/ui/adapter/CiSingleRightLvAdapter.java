package com.example.dllo.mygiftproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.model.bean.CiFmSingleLvBean;
import com.example.dllo.mygiftproject.view.MyCustomGridView;

import java.util.List;

/**
 * Created by dllo on 16/7/16.
 * 分类页 单品 右边的listView 适配器
 * 内部有一个TextView  下方一个gridView
 */
public class CiSingleRightLvAdapter extends BaseAdapter {
    private List<CiFmSingleLvBean.DataBean.CategoriesBean> datas;
    private Context context;

    public CiSingleRightLvAdapter(Context context) {
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
        RightLvHolder lvHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.classify_single_right_lv_item, parent, false);
            lvHolder = new RightLvHolder(convertView);
            convertView.setTag(lvHolder);
        } else {
            lvHolder = (RightLvHolder) convertView.getTag();
        }
        lvHolder.rightLvTitleTv.setText("------  " + datas.get(position).getName() + "  ------");
        CiSingleGvAdapter gvAdapter = new CiSingleGvAdapter(context);
        gvAdapter.setDatas(datas.get(position).getSubcategories());
        lvHolder.myCustomGridView.setAdapter(gvAdapter);
        return convertView;
    }


    class RightLvHolder {
        private TextView rightLvTitleTv;
        MyCustomGridView myCustomGridView;
        public RightLvHolder(View view) {
            rightLvTitleTv = (TextView) view.findViewById(R.id.right_lvTitleTv);

            myCustomGridView = (MyCustomGridView) view.findViewById(R.id.right_listView_gv);
        }
    }
}
