package com.project.main.autohome.ui.activity;

import android.content.Intent;
import android.view.View;
import android.webkit.WebSettings;
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
 * 推荐页 - 用来复用的Activity，【二级界面】
 */
public class AllActivity extends AbsBaseActivity implements View.OnClickListener {
    private WebView webView;
    private LinearLayout all_activ_LL_back;
    private ImageView all_activ_iv_share, all_activ_iv_collect;
    private boolean flag;
    private String title;
    private String content;
    private String pric;

    @Override
    protected int setlayout() {
        ShareSDK.initSDK(this);
        return R.layout.all_activity;
    }

    @Override
    protected void initViews() {
        webView = byView(R.id.all_webView);
        all_activ_LL_back = byView(R.id.all_activ_LL_back);
        all_activ_iv_share = byView(R.id.all_activ_iv_share);
        all_activ_iv_collect = byView(R.id.all_activ_iv_collect);
    }

    @Override
    protected void initDatas() {
        all_activ_LL_back.setOnClickListener(this);
        all_activ_iv_share.setOnClickListener(this);
        all_activ_iv_collect.setOnClickListener(this);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");
        pric = intent.getStringExtra("pric");
        flag = intent.getBooleanExtra("flag", true);
        String url = intent.getStringExtra("allUrl");
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        // 设置缓存模式
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启DOMStorage API功能
        webView.getSettings().setDomStorageEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (flag == false) {
            all_activ_iv_collect.setImageResource(R.mipmap.collect_shi);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.all_activ_LL_back:// 后退
                finish();
                break;
            case R.id.all_activ_iv_share: // 分享
                showShare();
                break;
            case R.id.all_activ_iv_collect: // 收藏
                // 判断是否收藏
                if (flag) {
                    DBInstance.getsInstance().insert(new AutoHomeBean(title, pric, null, null, content, null, null));
                    all_activ_iv_collect.setImageResource(R.mipmap.collect_shi);
                    Toast.makeText(this, getText(R.string.collect_ok), Toast.LENGTH_SHORT).show();
                    flag = false;
                } else {
                    DBInstance.getsInstance().deleByTitle(title);
                    all_activ_iv_collect.setImageResource(R.mipmap.collect_kong);
                    Toast.makeText(this, getText(R.string.collect_cancel), Toast.LENGTH_SHORT).show();
                    flag = true;
                }
                break;
        }
    }

    /**
     * 第三方分享的用的
     */
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
