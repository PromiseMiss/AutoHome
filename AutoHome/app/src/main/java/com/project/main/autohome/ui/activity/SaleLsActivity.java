package com.project.main.autohome.ui.activity;

import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.project.main.autohome.R;

/**
 * Created by youyo on 2016/7/23 0023.
 */
public class SaleLsActivity extends AbsBaseActivity {
    private WebView sale_ls_web;

    @Override
    protected int setlayout() {
        return R.layout.sale_ls_web;
    }

    @Override
    protected void initViews() {
        sale_ls_web = byView(R.id.sale_ls_web);
    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("saleUrl");
        sale_ls_web.loadUrl(url);
        sale_ls_web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}
