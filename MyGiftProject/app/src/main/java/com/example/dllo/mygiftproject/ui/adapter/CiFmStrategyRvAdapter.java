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

/**
 * Created by dllo on 16/7/15.
 * 分类页 攻略 > 栏目 recyclerView适配器 数据直接用网络拉去
 */
public class CiFmStrategyRvAdapter extends RecyclerView.Adapter<CiFmStrategyRvAdapter.StrategyRvHolder> {
    private CiFmStrategyRvBean datas;
    private Context context;
    private GdFmRvOnclick gdFmRvOnclick;

    public CiFmStrategyRvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(CiFmStrategyRvBean datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void setGdFmRvOnclick(GdFmRvOnclick gdFmRvOnclick) {
        this.gdFmRvOnclick = gdFmRvOnclick;
    }

    @Override
    public StrategyRvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        StrategyRvHolder strategyRvHolder = null;
        View view = LayoutInflater.from(context).inflate(R.layout.classify_strategy_rv_item, parent, false);
        strategyRvHolder = new StrategyRvHolder(view);
        return strategyRvHolder;
    }

    @Override
    public void onBindViewHolder(final StrategyRvHolder holder, int position) {
        holder.subTitleTv.setText(datas.getData().getColumns().get(position).getSubtitle());
        holder.authorTv.setText(datas.getData().getColumns().get(position).getAuthor());
        holder.titleTv.setText(datas.getData().getColumns().get(position).getTitle());
        SingleImageLoader.loaderImage(datas.getData().getColumns().get(position).getBanner_image_url(),holder.contentImgIv,context);
        // 添加rv监听事件
        if (gdFmRvOnclick != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition(); // 获取位置
                    gdFmRvOnclick.onClickListener(position); // 调取接口内方法参数为(位置)
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.getData().getColumns().size() : 0;
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
