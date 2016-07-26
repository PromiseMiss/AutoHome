package com.project.main.autohome.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.project.main.autohome.R;

/**
 * Created by youyo on 2016/7/23 0023.
 */
public class MyLogin extends AbsBaseActivity implements View.OnClickListener {
    private TextView my_log_back;
    @Override
    protected int setlayout() {
        return R.layout.my_login;
    }

    @Override
    protected void initViews() {
        my_log_back = byView(R.id.my_log_back);

    }

    @Override
    protected void initDatas() {
        my_log_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
