package com.example.dllo.mygiftproject.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.model.bean.CiFmStrategyRvBean;
import com.example.dllo.mygiftproject.model.net.SingleImageLoader;

import java.util.List;

/**
 * Created by dllo on 16/7/15.
 * 分类页 攻略 > 栏目 recyclerView适配器 数据直接用网络拉去
 */
public class CiFmStrategyRvAdapter extends RecyclerView.Adapter<CiFmStrategyRvAdapter.StrategyRvHolder> {
    private List<CiFmStrategyRvBean.DataBean.ColumnsBean> datas;
    private Context context;

    public CiFmStrategyRvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<CiFmStrategyRvBean.DataBean.ColumnsBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public StrategyRvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        StrategyRvHolder strategyRvHolder = null;
        View view = LayoutInflater.from(context).inflate(R.layout.classify_strategy_rv_item, parent, false);
        strategyRvHolder = new StrategyRvHolder(view);
        return strategyRvHolder;
    }

    @Override
    public void onBindViewHolder(StrategyRvHolder holder, int position) {
        CiFmStrategyRvBean.DataBean.ColumnsBean bean = datas.get(position);
        holder.subTitleTv.setText(bean.getSubtitle());
        holder.authorTv.setText(bean.getAuthor());
        holder.titleTv.setText(bean.getTitle());
        SingleImageLoader.loaderImage(bean.getBanner_image_url(),holder.contentImgIv,context);
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class StrategyRvHolder extends RecyclerView.ViewHolder {
        TextView titleTv, subTitleTv, authorTv;
        ImageView contentImgIv;

        public StrategyRvHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.sort_strategy_item_title);
            subTitleTv = (TextView) itemView.findViewById(R.id.sort_strategy_item_subTitle);
            authorTv = (TextView) itemView.findViewById(R.id.sort_strategy_item_author);
            contentImgIv = (ImageView) itemView.findViewById(R.id.sort_strategy_item_contentImage);
        }
    }
}
