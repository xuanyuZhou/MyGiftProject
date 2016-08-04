package com.example.dllo.mygiftproject.db;

import java.util.List;

/**
 * Created by dllo on 16/7/26.
 */
public interface IDBTool<T> {
    // 增加单条
    void insert(T t);

    // 增加一个集合
    void insert(List<T> ts);

    // 条件查找
    List<T> queryBy(String userName);

    List<T> queryBy(List<T> ts);

}
