package com.example.dllo.mygiftproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.model.bean.GuideReuseLvBean;
import com.example.dllo.mygiftproject.model.net.VolleyInstance;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/7/14.
 * 指南页复用fragment的listView适配器
 */
public class GuideReuseFmLvAdapter extends BaseAdapter {
    private GuideReuseLvBean datas;
    private Context context;

    public GuideReuseFmLvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(GuideReuseLvBean datas) {
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
        GuideReuseFmLvHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.guide_item_lv,parent,false);
            holder = new GuideReuseFmLvHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GuideReuseFmLvHolder) convertView.getTag();
        }
        holder.nikeNameTv.setText(datas.getData().getItems().get(position).getAuthor().getNickname());
        holder.categoryTv.setText(datas.getData().getItems().get(position).getColumn().getCategory());
        holder.shortTitleTv.setText(datas.getData().getItems().get(position).getColumn().getTitle());
        holder.titleTv.setText(datas.getData().getItems().get(position).getTitle());
        holder.likesCountTv.setText(String.valueOf(datas.getData().getItems().get(position).getLikes_count()));
        VolleyInstance.loaderImage(datas.getData().getItems().get(position).getAuthor().getAvatar_url(),holder.avatarIv,context);
        VolleyInstance.loaderImage(datas.getData().getItems().get(position).getCover_image_url(),holder.coverImageIv,context);
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
