package com.project.main.autohome.ui.fragment.fragmentpager;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.main.autohome.R;
import com.project.main.autohome.ui.activity.MyLogin;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;

/**
 * Created by youyo on 2016/7/18 0018.
 * 我 页
 */
public class MyChildFragment extends AbsBaseFragment implements View.OnClickListener {
    private TextView my_login;
    private ImageView my_user;

    @Override
    protected int setLayout() {
        return R.layout.my_child_fragment;
    }

    @Override
    protected void initView() {
        my_login = byView(R.id.my_login);
        my_user = byView(R.id.my_user);
    }

    @Override
    protected void initData() {
        my_login.setOnClickListener(this);
        my_user.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_login:
                Intent intent = new Intent(getContext(), MyLogin.class);
                startActivity(intent);
                break;
            case R.id.my_user:
                Intent intentLog = new Intent(getContext(), MyLogin.class);
                startActivity(intentLog);
                break;
            default:
                break;
        }
    }
}
