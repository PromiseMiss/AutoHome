package com.project.main.autohome.ui.activity;

import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.project.main.autohome.R;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by youyo on 2016/7/21 0021.
 * 热帖详情页
 */
public class HotPostsActivity extends AbsBaseActivity implements View.OnClickListener {
    private WebView webView;
    private LinearLayout hotposts_LL_back;
    private ImageView hotposts_iv_share, hotposts_iv_collect;

    @Override
    protected int setlayout() {
        ShareSDK.initSDK(this);
        return R.layout.hotposts_activity;
    }

    @Override
    protected void initViews() {
        webView = byView(R.id.hotposts_webView);
        hotposts_LL_back = byView(R.id.hotposts_LL_back);
        hotposts_iv_share = byView(R.id.hotposts_iv_share);
        hotposts_iv_collect = byView(R.id.hotposts_iv_collect);
    }

    @Override
    protected void initDatas() {
        hotposts_LL_back.setOnClickListener(this);
        hotposts_iv_share.setOnClickListener(this);
        hotposts_iv_collect.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hotposts_LL_back:
                finish();
                break;
            case R.id.hotposts_iv_share: // 收藏
                showShare();
                break;
            case R.id.hotposts_iv_collect: // 分享

                break;
            default:
                break;
        }
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        // 关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("");
        oks.setTitleUrl("");
        oks.setText(""); // 分享文本，所有平台都需要
        oks.setUrl("");
        oks.setComment("");
        oks.setSiteUrl("");
        oks.setImageUrl("");
        oks.show(this);
    }
}
