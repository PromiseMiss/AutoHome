package com.project.main.autohome.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.main.autohome.R;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by youyo on 2016/7/23 0023.
 * 我页  登录
 */
public class MyLogin extends AbsBaseActivity implements View.OnClickListener {
    private SharedPreferences sharedPreferences;
    private PlatformActionListener paListener;


    private TextView my_log_back, my_login_register;
    private ImageView my_log_sina, my_log_qq;
    private EditText my_log_user_edit, my_log_pass;
    private Button my_log_btnlog;
    private String userName;
    private String password;
    private SharedPreferences sp;


    @Override
    protected int setlayout() {
        ShareSDK.initSDK(this);
        return R.layout.my_login;
    }

    @Override
    protected void initViews() {
        my_log_back = byView(R.id.my_log_back);
        my_login_register = byView(R.id.my_login_register);
        my_log_user_edit = byView(R.id.my_log_user);
        my_log_pass = byView(R.id.my_log_pass);
        my_log_btnlog = byView(R.id.my_log_btnlog);

        my_log_qq = byView(R.id.my_log_qq);
        my_log_sina = byView(R.id.my_log_sina);
    }

    @Override
    protected void initDatas() {
        my_log_back.setOnClickListener(this);
        my_login_register.setOnClickListener(this);
        my_log_btnlog.setOnClickListener(this);
        my_log_sina.setOnClickListener(this);
        my_log_qq.setOnClickListener(this);

        userName = my_log_user_edit.getText().toString();
        password = my_log_pass.getText().toString();


        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        isLogInfo();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_log_back:// 登录页返回
                finish();
                overridePendingTransition(R.anim.input, R.anim.out);
                break;
            case R.id.my_login_register: // 登录页注册
                Intent intent = new Intent(this, MyLoginByRegister.class);
                startActivity(intent);
                break;
            case R.id.my_log_btnlog:// 登录按钮




                break;
            case R.id.my_log_qq: // QQ登陆
                Platform qq = ShareSDK.getPlatform(this, QQ.NAME);
                qq.setPlatformActionListener(paListener);
                qq.authorize();
                break;
            case R.id.my_log_sina: // 新浪登陆
                Platform sina = ShareSDK.getPlatform(this, SinaWeibo.NAME);
                sina.setPlatformActionListener(paListener);
                sina.authorize();
                break;
            default:
                break;
        }
    }

    public void isLogInfo() {
        Intent intent = getIntent();
        String userNum = intent.getStringExtra("user");
        my_log_user_edit.setText(userNum);
    }
}