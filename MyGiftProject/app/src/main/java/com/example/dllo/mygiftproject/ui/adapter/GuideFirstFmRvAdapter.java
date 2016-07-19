package com.example.dllo.mygiftproject.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dllo.mygiftproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/7/13.
 * 指南页第一个fragment的recyclerView适配器
 */
public class GuideFirstFmRvAdapter extends RecyclerView.Adapter<GuideFirstFmRvAdapter.GdFmRvHolder> {
    private List<String> imageUrls;
    private Context context;
    private GdFmRvOnclick gdFmRvOnclick;

    public GuideFirstFmRvAdapter(Context context) {
        this.context = context;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
        notifyDataSetChanged();
    }

    // 接口set方法
    public void setGdFmRvOnclick(GdFmRvOnclick gdFmRvOnclick) {
        this.gdFmRvOnclick = gdFmRvOnclick;
    }

    @Override
    public GdFmRvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GdFmRvHolder holder = null;
        View view = LayoutInflater.from(context).inflate(R.layout.guide_item_rv,parent,false);
        holder = new GdFmRvHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final GdFmRvHolder holder, int position) {
        Picasso.with(context).load(imageUrls.get(position)).into(holder.imageView);
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
        return imageUrls.size() != 0 ? imageUrls.size() : 0;
    }



    class GdFmRvHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public GdFmRvHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.guideRv_item_imageView);
        }
    }
}
