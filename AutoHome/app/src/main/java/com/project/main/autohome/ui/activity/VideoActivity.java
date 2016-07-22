package com.project.main.autohome.ui.activity;

import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.project.main.autohome.R;

/**
 * Created by youyo on 2016/7/21 0021.
 * 视频页
 */
public class VideoActivity extends AbsBaseActivity {
    private WebView webView;

    @Override
    protected int setlayout() {
        return R.layout.video_activity;
    }

    @Override
    protected void initViews() {
        webView = byView(R.id.video_webView);
    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("videoUrl");
        webView.loadUrl(url);
        // 让界面不进行调用系统的浏览器
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }


}
