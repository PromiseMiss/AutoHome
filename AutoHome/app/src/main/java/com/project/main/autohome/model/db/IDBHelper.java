package com.project.main.autohome.model.db;

import java.util.List;

/**
 * Created by youyo on 2016/7/26 0026.
 */
public interface IDBHelper<T> {
    // 增
    void insert(T t);

    // 删
    void dele(T t);

    // 改
    void change(T t);

    // 查

    List<T> query();

    List<T> query(int count);

    List<T> query(String title);


}
