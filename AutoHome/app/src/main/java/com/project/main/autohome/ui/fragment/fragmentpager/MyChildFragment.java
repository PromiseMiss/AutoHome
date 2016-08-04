package com.project.main.autohome.ui.fragment.fragmentpager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.UpCarouselBean;
import com.project.main.autohome.model.net.VolleyInstence;
import com.project.main.autohome.ui.activity.MyChildBySetUpActivity;
import com.project.main.autohome.ui.activity.MyChildCollection;
import com.project.main.autohome.ui.activity.MyLogin;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;
import com.project.main.autohome.util.ImageLoaderUtil;

import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by youyo on 2016/7/18 0018.
 * 我 页
 */
public class MyChildFragment extends AbsBaseFragment implements View.OnClickListener {
    private TextView my_login;
    private ImageView my_user, my_qq, my_xinlang;
    private LinearLayout my_shoucang, my_shezhi, my_duihuan;
    private List<UpCarouselBean.ResultBean.NewslistBean> beanList;

    private ImageView my_child_login_iv;
    private TextView my_child_login_name;
    private LinearLayout my_child_user_jiemian, my_child_fragment_outer;
    private MyChildLogBroad childLogBroad;
    private MyChildBraod myChildBraod;

    private SharedPreferences mychildSp;

    private PlatformActionListener paListener = new PlatformActionListener() {
        // 回调成功
        @Override
        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            String image = platform.getDb().getUserIcon();
            String name = platform.getDb().getUserName();

            Intent intent = new Intent(getContext().getPackageName() + ".LOG");
            intent.putExtra("image", image);
            intent.putExtra("userName", name);

            intent.putExtra("isClick", true);
            getContext().sendBroadcast(intent);
        }

        @Override
        public void onError(Platform platform, int i, Throwable throwable) {

        }

        @Override
        public void onCancel(Platform platform, int i) {

        }
    };

    @Override
    protected int setLayout() {
        ShareSDK.initSDK(getContext());
        return R.layout.my_child_fragment;
    }

    @Override
    protected void initView() {
        my_login = byView(R.id.my_login);
        my_user = byView(R.id.my_user);
        my_shoucang = byView(R.id.my_shoucang);
        my_qq = byView(R.id.my_qq);
        my_shezhi = byView(R.id.my_shezhi);
        my_duihuan = byView(R.id.my_duihuan);
        my_xinlang = byView(R.id.my_xinlang);

        my_child_login_iv = byView(R.id.my_child_login_iv);
        my_child_login_name = byView(R.id.my_child_login_name);
        my_child_user_jiemian = byView(R.id.my_child_user_jiemian);
        my_child_fragment_outer = byView(R.id.my_child_fragment_outer);

    }

    @Override
    protected void initData() {
        my_login.setOnClickListener(this);
        my_user.setOnClickListener(this);
        my_shoucang.setOnClickListener(this);
        my_qq.setOnClickListener(this);
        my_shezhi.setOnClickListener(this);
        my_duihuan.setOnClickListener(this);
        my_xinlang.setOnClickListener(this);

        childLogBroad = new MyChildLogBroad();
        IntentFilter intentFilter = new IntentFilter(getContext().getPackageName() + ".LOG");
        getContext().registerReceiver(childLogBroad, intentFilter);

        myChildBraod = new MyChildBraod();
        IntentFilter intentFilter1 = new IntentFilter("com.project.main.autohome.ui.activity");
        getContext().registerReceiver(myChildBraod, intentFilter1);


        mychildSp = getContext().getSharedPreferences("isLog", Context.MODE_PRIVATE);

        boolean usflag = mychildSp.getBoolean("isClicks", false);
        if (usflag) {
            String usImage = mychildSp.getString("images", "");
            String usName = mychildSp.getString("userNames", "");

            VolleyInstence.getInstence(getContext()).loadImage(usImage, my_child_login_iv);
            my_child_login_name.setText(usName);
            my_child_user_jiemian.setVisibility(View.VISIBLE);
            my_child_fragment_outer.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_login:// 登录
                goTo(getContext(), MyLogin.class);


                break;
            case R.id.my_user: // 账号登录
                goTo(getContext(), MyLogin.class);
                getActivity().overridePendingTransition(R.anim.input, R.anim.out);
                break;
            case R.id.my_shoucang: /// 我的收藏
                goTo(getContext(), MyChildCollection.class);
                break;
            case R.id.my_qq:  //  QQ登陆
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.setPlatformActionListener(paListener);
                qq.authorize();
                break;
            case R.id.my_shezhi: // 设置页
                goTo(getContext(), MyChildBySetUpActivity.class);
                break;
            case R.id.my_duihuan:

                break;
            case R.id.my_xinlang:  // 新浪登陆
                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
                weibo.setPlatformActionListener(paListener);
                weibo.authorize();
                break;
            default:
                break;
        }
    }

    class MyChildLogBroad extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String userIcon = intent.getStringExtra("image");
            String userName = intent.getStringExtra("userName");


            boolean flag = intent.getBooleanExtra("isClick", false);
            if (userIcon != null) {
                ImageLoaderUtil.getInstance().load(userIcon, my_child_login_iv);
            } else {
                my_child_login_iv.setImageResource(R.mipmap.ahlib_logo_80_80);
            }
            my_child_login_name.setText(userName);
            if (flag) {
                my_child_user_jiemian.setVisibility(View.VISIBLE);
                my_child_fragment_outer.setVisibility(View.GONE);
            }
            SharedPreferences.Editor editor = context.getSharedPreferences("isLog", Context.MODE_PRIVATE).edit();
            editor.putString("images", userIcon);
            editor.putString("userNames", userName);
            editor.putBoolean("isClicks", true);
            editor.commit();
        }
    }

    class MyChildBraod extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            my_child_user_jiemian.setVisibility(View.GONE);
            my_child_fragment_outer.setVisibility(View.VISIBLE);
        }
    }


}
