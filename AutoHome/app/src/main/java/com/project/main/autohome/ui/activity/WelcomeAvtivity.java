package com.project.main.autohome.ui.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.project.main.autohome.R;

/**
 * Created by youyo on 2016/7/27 0027.
 */
public class WelcomeAvtivity extends AbsBaseActivity implements View.OnClickListener {
    private TextView wel_tv;
    private static final int GOTO_MAIN_ACTIVITY = 0;
    private CountDownTimer timer;

    @Override
    protected int setlayout() {
        mHandler.sendEmptyMessageDelayed(GOTO_MAIN_ACTIVITY, 1000);

        return R.layout.welcome_activity;
    }

    @Override
    protected void initViews() {
        wel_tv = byView(R.id.welcome_tv_time);
    }

    @Override
    protected void initDatas() {
        wel_tv.setOnClickListener(this);
        timer = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                wel_tv.setText("跳过");
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GOTO_MAIN_ACTIVITY:
                    Intent intent = new Intent(WelcomeAvtivity.this, MainActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(WelcomeAvtivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
    }
}
