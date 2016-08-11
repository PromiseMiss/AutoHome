package com.project.main.autohome.ui.activity;

import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.project.main.autohome.R;
import com.project.main.autohome.model.db.AutoHomeBean;
import com.project.main.autohome.model.db.DBInstance;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by youyo on 2016/7/21 0021.
 * 最新页
 */
public class UptoDataActivity extends AbsBaseActivity implements View.OnClickListener {
    private WebView webView;
    private ImageView uptadata_iv_collect, uptadata_iv_share;
    private LinearLayout uptodata_LL_back;
    private String title;
    private String price;
    private String imageUrl;
    private String url;
    private String content;
    private String info;
    private String classify;
    private boolean flag;
    private String dataUrl;

    @Override
    protected int setlayout() {
        ShareSDK.initSDK(this);
        return R.layout.uptodata_activity;
    }

    @Override
    protected void initViews() {
        webView = byView(R.id.uptodata_wenView);
        uptadata_iv_collect = byView(R.id.uptadata_iv_collect);
        uptodata_LL_back = byView(R.id.uptodata_LL_back);
        uptadata_iv_share = byView(R.id.uptadata_iv_share);
    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        dataUrl = intent.getStringExtra("dataUrl");
        title = intent.getStringExtra("title");
        price = intent.getStringExtra("price");
        imageUrl = intent.getStringExtra("imageUrl");
        url = intent.getStringExtra("Url");
        content = intent.getStringExtra("content");
        info = intent.getStringExtra("info");
        classify = intent.getStringExtra("classify");
        flag = intent.getBooleanExtra("isClick", true);
        // 将返回来的网址直接放到WebView
        webView.loadUrl(dataUrl);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        uptadata_iv_collect.setOnClickListener(this); // 收藏
        uptodata_LL_back.setOnClickListener(this);
        uptadata_iv_share.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (flag == false) {
            uptadata_iv_collect.setImageResource(R.mipmap.collect_shi);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.uptodata_LL_back: // 返回
                finish();
                break;
            case R.id.uptadata_iv_collect:
                // 判断是否收藏
                if (flag) {
                    DBInstance.getsInstance().insert(new AutoHomeBean(title, price, imageUrl, url, content, info, classify));
                    uptadata_iv_collect.setImageResource(R.mipmap.collect_shi);
                    Toast.makeText(this, getResources().getText(R.string.collect_ok), Toast.LENGTH_SHORT).show();
                    flag = false;
                } else {
                    DBInstance.getsInstance().deleByTitle(title);
                    uptadata_iv_collect.setImageResource(R.mipmap.collect_kong);
                    Toast.makeText(this, getResources().getText(R.string.collect_cancel), Toast.LENGTH_SHORT).show();
                    flag = true;
                }
                break;
            case R.id.uptadata_iv_share: // 分享
                showShare();
                break;
        }
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        // 关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(title);
        oks.setTitleUrl(dataUrl);
        oks.setText(content); // 分享文本，所有平台都需要
        oks.setUrl(dataUrl);
        oks.setComment(content);
        oks.setSiteUrl(price);
        oks.setImageUrl(imageUrl);
        oks.show(this);
    }
}
