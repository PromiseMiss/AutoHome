package com.project.main.autohome.ui.activity;

import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.project.main.autohome.R;

/**
 * Created by youyo on 2016/7/27 0027.
 * 我的收藏
 */
public class MyChildcollectActivity extends AbsBaseActivity {
    private WebView my_child_details_webView;

    @Override
    protected int setlayout() {
        return R.layout.my_collect_details_item;
    }

    @Override
    protected void initViews() {
        my_child_details_webView = byView(R.id.my_child_details_webView);
    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        my_child_details_webView.loadUrl(url);
        my_child_details_webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });


    }
}
