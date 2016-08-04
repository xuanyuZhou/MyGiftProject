package com.example.dllo.mygiftproject.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dllo.mygiftproject.R;
import com.example.dllo.mygiftproject.db.DBTool;
import com.example.dllo.mygiftproject.db.LikeBean;

import org.greenrobot.eventbus.EventBus;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/7/29.
 * 点击头像的activity
 */
public class HeadPhotoActivity extends AbsBaseActivity implements View.OnClickListener {
    private LinearLayout changHeadPhoto;
    private CircleImageView circleImageView;
    private ImageView imageBack;
    private Bitmap bitmap;
    private String path;
    private EventBus eventBus;
    public boolean isToPhoto = false;

    @Override
    protected int setLayout() {
        return R.layout.activity_headphoto;
    }

    @Override
    protected void initView() {
        changHeadPhoto = byView(R.id.change_headphoto);
        circleImageView = byView(R.id.headphoto_imageview);
        imageBack = byView(R.id.head_back);
    }

    @Override
    protected void initListeners() {
        changHeadPhoto.setOnClickListener(this);
        imageBack.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        // 实例化
        eventBus = EventBus.getDefault();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_back:

                if (isToPhoto) {

                    SharedPreferences sp = getSharedPreferences("user", Context.MODE_PRIVATE);
                    String userName = sp.getString("username", "未登陆");
                    SharedPreferences.Editor editor = sp.edit();
                    if (!userName.equals("未登录")) {
                        DBTool.getInstance().insert(new LikeBean(path));

                        editor.putString("path" + userName, path);
                        //得到 让它上车
                        eventBus.post(path);
                        editor.commit();

                    }
                    finish();
                } else {
                    finish();
                }


                break;
            case R.id.change_headphoto:
                isToPhoto = true;
                // 跳转到图库
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 200);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            path = cursor.getString
                    (cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            bitmap = BitmapFactory.decodeFile(path);
            circleImageView.setImageBitmap(bitmap);
        }
    }
}
