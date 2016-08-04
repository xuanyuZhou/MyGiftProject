package com.example.dllo.mygiftproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.model.bean.SearchFragmentGvBean;
import com.example.dllo.mygiftproject.model.net.SingleImageLoader;

/**
 * Created by dllo on 16/7/21.
 */
public class SearchFmGvAdapter extends BaseAdapter {
    private SearchFragmentGvBean datas;
    private Context context;

    public SearchFmGvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(SearchFragmentGvBean datas) {
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
        SearchLvHolder lvHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.hot_item_gv, null);
            lvHolder = new SearchLvHolder(convertView);
            convertView.setTag(lvHolder);
        } else {
            lvHolder = (SearchLvHolder) convertView.getTag();

        }
        lvHolder.searchGvNameTv.setText(datas.getData().getItems().get(position).getName());
        lvHolder.searchGvpriceTv.setText(datas.getData().getItems().get(position).getPrice());
        lvHolder.searchGvlikesCountTv.setText(String.valueOf(datas.getData().getItems().get(position).getLikes_count()));
        SingleImageLoader.loaderImage(datas.getData().getItems().get(position).getCover_image_url(), lvHolder.searchGvcoverImgIv, context);
        return convertView;
    }

    class SearchLvHolder {
        private TextView searchGvpriceTv, searchGvNameTv, searchGvlikesCountTv;
        private ImageView searchGvcoverImgIv;

        public SearchLvHolder(View view) {
            searchGvNameTv = (TextView) view.findViewById(R.id.hotFmGv_nameTv);
            searchGvlikesCountTv = (TextView) view.findViewById(R.id.hotFmGv_likesCountTv);
            searchGvpriceTv = (TextView) view.findViewById(R.id.hotFmGv_priceTv);
            searchGvcoverImgIv = (ImageView) view.findViewById(R.id.hotFmGv_coverImgIv);
        }
    }
}