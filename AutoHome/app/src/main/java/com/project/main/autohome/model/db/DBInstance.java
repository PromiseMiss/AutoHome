package com.project.main.autohome.model.db;

import android.database.sqlite.SQLiteDatabase;

import com.project.main.autohome.ui.activity.MyApp;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by youyo on 2016/7/26 0026.
 * 封装的数据库
 */
public class DBInstance implements IDBHelper<AutoHomeBean> {
    /// 数据库名
    private static final String DN_NAME = "autoHome.db";
    /// 单例
    private static DBInstance sInstance;
    // 数据库帮助类
    private DaoMaster.DevOpenHelper helper;
    private SQLiteDatabase database;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private AutoHomeBeanDao autoHomeBeanDao;
    private QueryBuilder<AutoHomeBean> queryBuilder;

    // 对外提供的方法
    public static DBInstance getsInstance() {
        if (sInstance == null) {
            synchronized (DBInstance.class) {
                if (sInstance == null) {
                    sInstance = new DBInstance();
                }
            }
        }
        return sInstance;
    }

    private DBInstance() {
        // 创建数据库操作对象
        autoHomeBeanDao = getAutoHomeBeanDao();
        queryBuilder = autoHomeBeanDao.queryBuilder();
    }

    /******
     * 对内方法(五个)
     ***/
    private DaoMaster.DevOpenHelper getHelper() {
        if (helper == null) {
            helper = new DaoMaster.DevOpenHelper(MyApp.getContext(), DN_NAME, null);
        }
        return helper;
    }

    private SQLiteDatabase getDatabase() {
        if (database == null) {
            database = getHelper().getWritableDatabase();
        }
        return database;
    }

    private DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            daoMaster = new DaoMaster(getDatabase());
        }
        return daoMaster;
    }

    private DaoSession getDaoSession() {
        if (daoSession == null) {
            daoSession = getDaoMaster().newSession();
        }
        return daoSession;
    }

    private AutoHomeBeanDao getAutoHomeBeanDao() {
        if (autoHomeBeanDao == null) {
            autoHomeBeanDao = getDaoSession().getAutoHomeBeanDao();
        }
        return autoHomeBeanDao;
    }

    private AutoHomeBeanDao _getAutoHomeBeanDao() {
        return _getAutoHomeBeanDao();
    }

    /*对外提供增删改查*/
    // 增
    @Override
    public void insert(AutoHomeBean autoHomeBean) {
//        getsInstance()._insert(autoHomeBean);
        autoHomeBeanDao.insertOrReplace(autoHomeBean);
    }

    private void _insert(AutoHomeBean autoHomeBean) {
        autoHomeBeanDao.insertOrReplace(autoHomeBean);
    }

    // 删
    @Override
    public void dele(AutoHomeBean autoHomeBean) {
        getsInstance()._dele(autoHomeBean);
    }

    private void _dele(AutoHomeBean autoHomeBean) {
        autoHomeBeanDao.deleteAll();
    }

    public void deleByTitle(String title) {
        autoHomeBeanDao.deleteInTx(query(title));
    }

    public void deleteBatch(List<AutoHomeBean> autoHomeBeen) {
        daoSession.getAutoHomeBeanDao().deleteInTx(autoHomeBeen);
    }

    public List<AutoHomeBean> delete(Long id) {
        daoSession.getAutoHomeBeanDao().deleteByKey(id);
        return queryBuilder.list();
    }


    // 改
    @Override
    public void change(AutoHomeBean autoHomeBean) {
        getsInstance()._change(autoHomeBean);
    }


    private void _change(AutoHomeBean autoHomeBean) {
        autoHomeBeanDao.update(autoHomeBean);
    }

    // 查
    @Override
    public List<AutoHomeBean> query() {
        return null;
    }

    @Override
    public List<AutoHomeBean> query(int count) {
        int dbSize = getAutoHomeBeanDao().loadAll().size();
        if (count > dbSize) {
            queryBuilder.limit(10);
        } else {
            queryBuilder.limit(count);
        }
        return queryBuilder.list();
    }

    @Override
    public List<AutoHomeBean> query(String title) {
        QueryBuilder qb = autoHomeBeanDao.queryBuilder();
        qb.where(AutoHomeBeanDao.Properties.Title.eq(title));
        return qb.list();
    }
}
