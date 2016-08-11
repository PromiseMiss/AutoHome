package com.project.main.autohome.ui.activity;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.main.autohome.R;
import com.project.main.autohome.model.net.NetUrl;
import com.project.main.autohome.util.ImageLoaderUtil;

/**
 * Created by youyo on 2016/7/27 0027.
 * 欢迎页
 */
public class WelcomeAvtivity extends AbsBaseActivity implements View.OnClickListener {
    private TextView wel_tv;
    private ImageView imageView;
    private CountDownTimer timer;

    @Override
    protected int setlayout() {
        return R.layout.welcome_activity;
    }

    @Override
    protected void initViews() {
        wel_tv = byView(R.id.welcome_tv_time);
        imageView = byView(R.id.welcome_iv);
    }

    @Override
    protected void initDatas() {
        wel_tv.setOnClickListener(this);
        ImageLoaderUtil.getInstance().load(NetUrl.WELCOME, imageView);
        // 延时显示
        timer = new CountDownTimer(2500, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                wel_tv.setText(getText(R.string.welcome_loading));
            }

            @Override
            public void onFinish() {
                goTo(WelcomeAvtivity.this, MainActivity.class);
                finish();
            }
        }.start();
    }

    @Override
    public void onClick(View v) {
        goTo(WelcomeAvtivity.this, MainActivity.class);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
    }
}
