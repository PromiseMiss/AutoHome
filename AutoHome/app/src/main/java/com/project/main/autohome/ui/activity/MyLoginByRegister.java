package com.project.main.autohome.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.project.main.autohome.R;

/**
 * Created by youyo on 2016/7/28 0028.
 * 点完登录点进来的注册页
 */
public class MyLoginByRegister extends AbsBaseActivity implements View.OnClickListener {
    private LinearLayout register_back;
    private Button register_next;
    private EditText num_edit;// 手机号输入框

    @Override
    protected int setlayout() {
        return R.layout.activity_my_login_register;
    }

    @Override
    protected void initViews() {
        register_back = byView(R.id.register_back);
        register_next = byView(R.id.register_next);
        // 手机号输入框
        num_edit = byView(R.id.my_register_num);
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
                String user = num_edit.getText().toString();
                Intent intent = new Intent(this, MyLogin.class);
                intent.putExtra("user", user);
                startActivity(intent);
                break;
        }
    }
}
