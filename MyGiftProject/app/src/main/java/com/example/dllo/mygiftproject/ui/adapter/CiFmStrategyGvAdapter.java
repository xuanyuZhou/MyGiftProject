package com.example.dllo.mygiftproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.model.bean.CiStrategyGvBean;
import com.example.dllo.mygiftproject.model.net.SingleImageLoader;

import java.util.List;

/**
 * Created by dllo on 16/7/16.
 * 分类页 攻略界面 下方3个一样的gridView 的适配器 1个适配器绑定3个gridView
 */
public class CiFmStrategyGvAdapter extends BaseAdapter {
    private List<CiStrategyGvBean.DataBean.ChannelGroupsBean.ChannelsBean> datas;
    private Context context;

    public CiFmStrategyGvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<CiStrategyGvBean.DataBean.ChannelGroupsBean.ChannelsBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return 6;
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
        ClassHolder classHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.classify_startegy_gv_item,parent,false);
            classHolder = new ClassHolder(convertView);
            convertView.setTag(classHolder);
        } else {
            classHolder = (ClassHolder) convertView.getTag();
        }
        SingleImageLoader.loaderImage(datas.get(position).getCover_image_url(),classHolder.classIv,context);
        return convertView;
    }
    class ClassHolder {
        private ImageView classIv;
        public ClassHolder(View view) {
            classIv = (ImageView) view.findViewById(R.id.ci_strategy_gvItem_contentIv);
        }
    }
}
