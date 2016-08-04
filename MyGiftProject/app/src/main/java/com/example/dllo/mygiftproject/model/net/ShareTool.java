package com.example.dllo.mygiftproject.model.net;

import android.content.Context;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by dllo on 16/7/22.
 * 自己封装的分享单例类
 */
public class ShareTool {
    // 定义一个当前类的静态对象
    private static ShareTool shareTool;

    // 私有化构造方法
    private ShareTool(Context context) {
    }

    // 对外提供一个活的对象的方法 双重校验锁
    // 静态的 类方法,用类就可以调用 ->获取单例对象ShareTool的对象
    public static ShareTool getShareTool(Context context) {
        if (shareTool == null) {
            synchronized (ShareTool.class) {
                if (shareTool == null) {
                    shareTool = new ShareTool(context);
                }
            }
        }
        return shareTool;
    }

    private void _showShare(Context context, String title, String content, String imageUrl) {
        ShareSDK.initSDK(context);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(title);
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://www.liwushuo.com");
        // text是分享文本，所有平台都需要这个字段
        oks.setText(content);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImageUrl(imageUrl);
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("来自周轩宇的礼物说app");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://www.liwushuo.com");

        // 启动分享GUI
        oks.show(context);

    }

    //***************************************
    // 对象方法,静态的类方法获得了对象
    public  void showShare(Context context, String title, String content,String imageUrl) {
        getShareTool(context)._showShare(context, title, content, imageUrl);
    }


}
