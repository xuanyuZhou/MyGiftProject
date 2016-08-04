package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class Main {
    public static void main(String[] args) {
        // 引包
        Schema schema = new Schema(1, "com.example.dllo.mygiftproject.db");
        // 生成实体类
        Entity entity = schema.addEntity("LikeBean");
        //添加id和列
        entity.addIdProperty().autoincrement().primaryKey();
        entity.addStringProperty("userName");
        entity.addStringProperty("imageUrl");
        entity.addStringProperty("title");
        entity.addStringProperty("nextUrl");
        entity.addStringProperty("likesCount");
        entity.addStringProperty("other");
        entity.addStringProperty("path");
        // 生成数据库
        try {
            new DaoGenerator().generateAll(schema,"./app/src/main/java/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
