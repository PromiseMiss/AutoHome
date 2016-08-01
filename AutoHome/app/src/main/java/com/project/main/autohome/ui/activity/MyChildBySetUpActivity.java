package com.project.main.autohome.ui.activity;

import android.view.View;
import android.widget.LinearLayout;

import com.project.main.autohome.R;

/**
 * Created by youyo on 2016/7/28 0028.
 * 设置页
 */
public class MyChildBySetUpActivity extends AbsBaseActivity implements View.OnClickListener {
    private LinearLayout my_setup_back;

    @Override
    protected int setlayout() {
        return R.layout.activity_mychild_set_up;
    }

    @Override
    protected void initViews() {
        my_setup_back = byView(R.id.my_setup_back);
    }

    @Override
    protected void initDatas() {
        my_setup_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_setup_back:
                finish();
                break;

            default:
                break;
        }
    }
}
