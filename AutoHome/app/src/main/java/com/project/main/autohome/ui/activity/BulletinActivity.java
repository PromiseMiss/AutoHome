package com.project.main.autohome.ui.activity;

import android.content.Intent;

import com.project.main.autohome.R;

/**
 * Created by youyo on 2016/7/21 0021.
 * 快报
 */
public class BulletinActivity extends AbsBaseActivity {

    @Override
    protected int setlayout() {
        return R.layout.activity_bulletin;
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("bullUrl");

    }
}
