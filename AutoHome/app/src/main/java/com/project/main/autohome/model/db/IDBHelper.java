package com.project.main.autohome.model.db;

import java.util.List;

/**
 * Created by youyo on 2016/7/26 0026.
 * <p/>
 * 数据库操作类
 */
public interface IDBHelper<T> {
    // 增
    void insert(T t);

    // 删
    void dele(T t);

    // 查

    List<T> query();

    List<T> query(int count);

    List<T> query(String title);


}
