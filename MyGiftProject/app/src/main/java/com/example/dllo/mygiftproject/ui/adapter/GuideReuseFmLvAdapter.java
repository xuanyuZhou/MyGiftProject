package com.example.dllo.mygiftproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.model.bean.LocalGuideReuseLvBean;
import com.example.dllo.mygiftproject.model.net.VolleyInstance;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/7/14.
 * 指南页复用fragment的listView适配器
 */
public class GuideReuseFmLvAdapter extends BaseAdapter {
    private List<LocalGuideReuseLvBean> datas;
    private Context context;

    public GuideReuseFmLvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<LocalGuideReuseLvBean> datas) {
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
        GuideReuseFmLvHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.guide_item_lv,parent,false);
            holder = new GuideReuseFmLvHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GuideReuseFmLvHolder) convertView.getTag();
        }
        LocalGuideReuseLvBean bean = datas.get(position);
        holder.nikeNameTv.setText(bean.getNickName());
        holder.categoryTv.setText(bean.getCategory());
        holder.shortTitleTv.setText(bean.getShortTitle());
        holder.titleTv.setText(bean.getTitle());
        holder.likesCountTv.setText(bean.getLikesCount());
        VolleyInstance.loaderImage(bean.getAvatarUrl(),holder.avatarIv,context);
        VolleyInstance.loaderImage(bean.getImageUrl(),holder.coverImageIv,context);
        return convertView;
    }

    class GuideReuseFmLvHolder {
        private TextView titleTv,shortTitleTv,likesCountTv,nikeNameTv,categoryTv;
        private ImageView coverImageIv;
        private CircleImageView avatarIv;
        public GuideReuseFmLvHolder(View view) {
            titleTv = (TextView) view.findViewById(R.id.guideItemLv_title);
            shortTitleTv = (TextView) view.findViewById(R.id.guideItemLv_shortTitleTv);
            likesCountTv = (TextView) view.findViewById(R.id.guideItemLv_likesCount);
            nikeNameTv = (TextView) view.findViewById(R.id.guideItemLv_nickNameTv);
            coverImageIv = (ImageView) view.findViewById(R.id.guideItemLv_contentImageView);
            categoryTv = (TextView) view.findViewById(R.id.guideItemLv_categoryTv);
            avatarIv = (CircleImageView) view.findViewById(R.id.guideItemLv_avatarIv);
        }
    }
}
