package com.project.main.autohome.ui.activity;

import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.project.main.autohome.R;

/**
 * Created by youyo on 2016/7/21 0021.
 * 热帖详情页
 */
public class HotPostsActivity extends AbsBaseActivity {
    private WebView webView;

    @Override
    protected int setlayout() {
        return R.layout.hotposts_activity;
    }

    @Override
    protected void initViews() {
        webView = byView(R.id.hotposts_webView);
    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}
