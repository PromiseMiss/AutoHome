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
 * 视频页
 */
public class VideoActivity extends AbsBaseActivity implements View.OnClickListener {
    private WebView webView;
    private LinearLayout video_LL_back;
    private ImageView video_iv_collect, video_iv_share;

    @Override
    protected int setlayout() {
        ShareSDK.initSDK(this);
        return R.layout.video_activity;
    }

    @Override
    protected void initViews() {
        webView = byView(R.id.video_webView);
        video_LL_back = byView(R.id.video_LL_back); // 返回
        video_iv_collect = byView(R.id.video_iv_collect); // 收藏
        video_iv_share = byView(R.id.video_iv_share); // 分享

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

        video_LL_back.setOnClickListener(this);
        video_iv_collect.setOnClickListener(this);
        video_iv_share.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.video_LL_back:
                finish();
                break;
            case R.id.video_iv_collect:

                break;
            case R.id.video_iv_share:
                showShare();
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
