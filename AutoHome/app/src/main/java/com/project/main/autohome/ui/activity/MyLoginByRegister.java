package com.project.main.autohome.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.project.main.autohome.R;

/**
 * Created by youyo on 2016/7/28 0028.
 * 点完登录点进来的注册页
 */
public class MyLoginByRegister extends AbsBaseActivity implements View.OnClickListener {
    private LinearLayout register_back;
    private Button register_next;

    @Override
    protected int setlayout() {
        return R.layout.activity_my_login_register;
    }

    @Override
    protected void initViews() {
        register_back = byView(R.id.register_back);
        register_next = byView(R.id.register_next);
    }

    @Override
    protected void initDatas() {
        register_back.setOnClickListener(this);
        register_next.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_back:
                finish();
                break;
            case R.id.register_next:
                Toast.makeText(this, "下一步", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
