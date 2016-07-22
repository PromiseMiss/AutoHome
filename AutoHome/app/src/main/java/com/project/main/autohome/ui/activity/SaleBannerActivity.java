package com.project.main.autohome.ui.activity;

import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.project.main.autohome.R;

/**
 * Created by youyo on 2016/7/21 0021.
 * 发现页轮播图详情
 */
public class SaleBannerActivity extends AbsBaseActivity {
    private WebView webView;

    @Override
    protected int setlayout() {
        return R.layout.activity_sale_banner;
    }

    @Override
    protected void initViews() {
        webView = byView(R.id.sale_banner_details);

    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        String weburl = intent.getStringExtra("url");
        webView.loadUrl(weburl);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

    }
}
