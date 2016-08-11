package com.project.main.autohome.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.main.autohome.R;

/**
 * Created by youyo on 2016/7/28 0028.
 * 点完登录点进来的注册页
 */
public class MyLoginByRegister extends AbsBaseActivity implements View.OnClickListener {
    private LinearLayout register_back;
    private Button register_next;
    private EditText num_edit;// 手机号输入框
    private TextView register_protocol_tv;
    private SpannableString span;

    @Override
    protected int setlayout() {
        return R.layout.activity_my_login_register;
    }

    @Override
    protected void initViews() {
        register_back = byView(R.id.register_back);
        register_next = byView(R.id.register_next);// 下一步
        // 手机号输入框
        num_edit = byView(R.id.my_register_num);
        register_protocol_tv = byView(R.id.register_protocol_tv);
    }

    @Override
    protected void initDatas() {
        register_back.setOnClickListener(this);
        register_next.setOnClickListener(this);
        // 把某一段字体变色
        span = new SpannableString(getString(R.string.register_protocol_tv));
        span.setSpan(new ForegroundColorSpan(Color.BLUE), 7, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        register_protocol_tv.setText(span);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_back:
                goTo(this,MyLogin.class);
                finish();
                break;
            case R.id.register_next:
                String user = num_edit.getText().toString();
                // 判断手机号是否为空
                if (user.equals("")) {
                    Toast.makeText(this, getText(R.string.pass_edit_judge)
                            , Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(this, MyLogin.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }
}
