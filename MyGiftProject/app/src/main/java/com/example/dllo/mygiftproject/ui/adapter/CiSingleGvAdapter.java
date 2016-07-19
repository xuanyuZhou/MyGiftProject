package com.example.dllo.mygiftproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.model.bean.CiFmSingleLvBean;
import com.example.dllo.mygiftproject.model.net.SingleImageLoader;

import java.util.List;

/**
 * Created by dllo on 16/7/16.
 * 分类页 右边listView 内嵌套的 gridView的适配器 显示不同的内容
 * 此处因为gridView是listView的行布局 所以gv的绑定id和绑定适配器 全部在此适配器里进行
 */
public class CiSingleGvAdapter extends BaseAdapter {
    private List<CiFmSingleLvBean.DataBean.CategoriesBean.SubcategoriesBean> datas;
    private Context context;

    public CiSingleGvAdapter(Context context) {
        this.context = context;

    }

    public void setDatas(List<CiFmSingleLvBean.DataBean.CategoriesBean.SubcategoriesBean> datas) {
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
        SingleGvHolder gvHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.classify_single_lv_gv_item,parent,false);
            gvHolder = new SingleGvHolder(convertView);
            convertView.setTag(gvHolder);
        } else {
            gvHolder = (SingleGvHolder) convertView.getTag();
        }
        gvHolder.titleTv.setText(datas.get(position).getName());
        SingleImageLoader.loaderImage(datas.get(position).getIcon_url(),gvHolder.imageIv,context);
        return convertView;
    }

    class SingleGvHolder {
        TextView titleTv;
        ImageView imageIv;
        public SingleGvHolder(View view) {
            titleTv = (TextView) view.findViewById(R.id.singleGv_titleTv);
            imageIv = (ImageView) view.findViewById(R.id.singleGv_imageIv);
        }
    }
}
