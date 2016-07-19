package com.project.main.autohome.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by youyo on 2016/7/11 0011.
 * 基类 Activity
 */
public abstract class AbsBaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setlayout());
        initViews();
        initDatas();
    }

    protected abstract int setlayout();

    protected abstract void initViews();

    protected abstract void initDatas();

    /**
     * 简化fandviewById
     *
     * @param resId
     * @param <T>
     * @return
     */
    protected <T extends View> T byView(int resId) {
        T t = (T) findViewById(resId);
        return t;
    }

    /*简单跳转*/
    protected void goTo(Context from, Class<? extends AbsBaseActivity> to) {
        Intent intent = new Intent(from, to);
        startActivity(intent);
    }

    /*隐式intent*/
    protected void goTo(String action, String uri) {
        Intent intent = new Intent(action);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }

    /*带值跳转*/
    protected void goTo(Context from, Class<? extends AbsBaseActivity> to, Bundle values) {
        Intent intent = new Intent(from, to);
        intent.putExtras(values);
        startActivity(intent);
    }
}
