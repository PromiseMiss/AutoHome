package com.project.main.autohome.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.project.main.autohome.R;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;

/**
 * Created by youyo on 2016/7/21 0021.
 * 论坛  ---- 精选推荐页
 */
public class FeatureActivity extends AbsBaseActivity implements View.OnClickListener {
    private WebView webView;
    private ImageView feature_iv_collect, feature_iv_share;
    private LinearLayout feature_LL_back;
    private String url;

    @Override
    protected int setlayout() {
        return R.layout.feature_activity;
    }

    @Override
    protected void initViews() {
        webView = byView(R.id.feature_webview);
        feature_iv_collect = byView(R.id.feature_iv_collect); /// 收藏
        feature_iv_share = byView(R.id.feature_iv_share); // 分享
        feature_LL_back = byView(R.id.feature_LL_back); // 返回

    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        feature_iv_collect.setOnClickListener(this);
        feature_iv_share.setOnClickListener(this);
        feature_LL_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.feature_LL_back:
                finish();
                break;
            case R.id.feature_iv_collect:

                break;
            case R.id.feature_iv_share:
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
        oks.setTitle("标题");
        oks.setTitleUrl("http://sharesdk.cn");
        oks.setText("我是分享文本"); // 分享文本，所有平台都需要
        oks.setUrl(url);
        oks.setComment("我是测试评论文本");
        oks.setSiteUrl(url);
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            @Override
            public void onShare(Platform platform, Platform.ShareParams paramsToShare) {
                if ("QZone".equals(platform.getName())) {
                    paramsToShare.setTitle(null);
                    paramsToShare.setTitleUrl(null);
                }
                if ("SinaWeibo".equals(platform.getName())) {
                    paramsToShare.setUrl(null);
                    paramsToShare.setText("分享文本 http://www.baidu.com");
                }
                if ("Wechat".equals(platform.getName())) {
                    Bitmap imageData = BitmapFactory.decodeResource(getResources(), R.drawable.ssdk_logo);
                    paramsToShare.setImageData(imageData);
                }
                if ("WechatMoments".equals(platform.getName())) {
                    Bitmap imageData = BitmapFactory.decodeResource(getResources(), R.drawable.ssdk_logo);
                    paramsToShare.setImageData(imageData);
                }
            }
        });
        oks.show(this);
    }
}
