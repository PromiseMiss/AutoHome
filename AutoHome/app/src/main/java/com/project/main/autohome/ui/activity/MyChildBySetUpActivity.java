package com.project.main.autohome.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.project.main.autohome.R;

/**
 * Created by youyo on 2016/7/28 0028.
 * 设置页
 */
public class MyChildBySetUpActivity extends AbsBaseActivity implements View.OnClickListener {
    private LinearLayout my_setup_back, mychild_back_login_layout;
    private Button back_Login_btn;
    private SharedPreferences sp;

    private MasterBroad masterBroad;
    private BackBroad backBroad;


    @Override
    protected int setlayout() {
        return R.layout.activity_mychild_set_up;
    }

    @Override
    protected void initViews() {
        my_setup_back = byView(R.id.my_setup_back);
        mychild_back_login_layout = byView(R.id.mychild_back_login_layout);
        back_Login_btn = byView(R.id.back_Login_btn);
    }

    @Override
    protected void initDatas() {
        my_setup_back.setOnClickListener(this);
        mychild_back_login_layout.setOnClickListener(this);
        back_Login_btn.setOnClickListener(this);

        //注册广播
        masterBroad = new MasterBroad();
        IntentFilter masterFilter = new IntentFilter("com.project.main.autohome.ui.fragment.fragmentpager"+".LOG");
        registerReceiver(masterBroad, masterFilter);

        backBroad = new BackBroad();
        IntentFilter backFilter = new IntentFilter("com.project.main.autohome.ui.activity");
        registerReceiver(backBroad, backFilter);

        sp = getSharedPreferences("isLog", MODE_PRIVATE);
        if (sp.getBoolean("isClicks", false)) {
            mychild_back_login_layout.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(masterBroad);
        unregisterReceiver(backBroad);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_setup_back:
                finish();
                break;
            case R.id.mychild_back_login_layout:

                break;
            case R.id.back_Login_btn: // 退出账号
                SharedPreferences.Editor editor = getSharedPreferences("isLog", Context.MODE_PRIVATE).edit();
                editor.putBoolean("isClicks", false);
                editor.commit();
                Intent intent = new Intent("com.project.main.autohome.ui.activity");
                sendBroadcast(intent);
                Intent intent1 = new Intent("com.project.main.autohome.ui.activity");
                sendBroadcast(intent);
                break;
        }
    }

    class MasterBroad extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            mychild_back_login_layout.setVisibility(View.VISIBLE);
        }
    }

    class BackBroad extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            mychild_back_login_layout.setVisibility(View.GONE);
        }
    }
}


