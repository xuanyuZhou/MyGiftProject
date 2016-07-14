package com.example.dllo.mygiftproject.ui.fragment;

import android.widget.GridView;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.model.bean.HotFmGvBean;
import com.example.dllo.mygiftproject.model.bean.LocalHotFmGvBean;
import com.example.dllo.mygiftproject.model.bean.RunnableDocumentBean;
import com.example.dllo.mygiftproject.model.net.VolleyInstance;
import com.example.dllo.mygiftproject.model.net.VolleyPort;
import com.example.dllo.mygiftproject.ui.adapter.HotFmGvAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/11.
 */
public class HotFragment extends AbsBaseFragment implements VolleyPort {
    private GridView hotFmGridView;
    private List<LocalHotFmGvBean> beanArray;
    private String gvUrl = RunnableDocumentBean.HOT_FM_GV_URL;
    private HotFmGvAdapter gvAdapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView() {
        hotFmGridView = byView(R.id.hotFragment_gridView);
    }

    @Override
    protected void initDatas() {
        VolleyInstance.getInstance(context).startStringRequest(gvUrl,this);
    }

    @Override
    public void stringSuccess(String result) {
        Gson gson = new Gson();
        HotFmGvBean hotFmGvBean = gson.fromJson(result,HotFmGvBean.class);
        List<HotFmGvBean.DataBean.ItemsBean> data = hotFmGvBean.getData().getItems();
        beanArray = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            LocalHotFmGvBean bean = new LocalHotFmGvBean();
            bean.setName(data.get(i).getData().getName())
                    .setLikesCount(String.valueOf(data.get(i).getData().getFavorites_count()))
                    .setImageUrl(data.get(i).getData().getCover_image_url())
                    .setPrice(data.get(i).getData().getPrice());
            beanArray.add(bean);
        }
        gvAdapter = new HotFmGvAdapter(context);
        gvAdapter.setDatas(beanArray);
        hotFmGridView.setAdapter(gvAdapter);

    }

    @Override
    public void stringFailure() {

    }
}
