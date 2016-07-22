package com.project.main.autohome.ui.activity;

import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.project.main.autohome.R;

/**
 * Created by youyo on 2016/7/21 0021.
 * 最新页
 */
public class UptoDataActivity extends AbsBaseActivity {
    private WebView webView;

    @Override
    protected int setlayout() {
        return R.layout.uptodata_activity;
    }

    @Override
    protected void initViews() {
        webView = byView(R.id.uptodata_wenView);

    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        String dataUrl = intent.getStringExtra("dataUrl");
        webView.loadUrl(dataUrl);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}
