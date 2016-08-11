package com.project.main.autohome.ui.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.main.autohome.R;

import java.io.IOException;

/**
 * Created by youyo on 2016/8/5 0005.
 * 个人中心页-- 登录后点击用户跳转进来的界面
 */
public class MyPersonalActivity extends AbsBaseActivity implements View.OnClickListener {
    private RelativeLayout my_personal_user_iv;
    private LinearLayout my_personal_back;
    private TextView my_by_personal_userName;
    private ImageView my_personal_usericon;
    public static final int REQUEST_IMAGE = 112;

    @Override
    protected int setlayout() {
        return R.layout.my_by_personal;
    }

    @Override
    protected void initViews() {
        my_personal_back = byView(R.id.my_personal_back);
        my_by_personal_userName = byView(R.id.my_by_personal_userName);
        // 头像
        my_personal_user_iv = byView(R.id.my_personal_user_iv);
        // 设置头像ImageView
        my_personal_usericon = byView(R.id.my_personal_usericon);
    }

    @Override
    protected void initDatas() {
        // 对布局监听
        my_personal_back.setOnClickListener(this);
        my_personal_user_iv.setOnClickListener(this);// 头像
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 返回跳转
            case R.id.my_personal_back:
                finish();
                break;
            case R.id.my_personal_user_iv:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                // 过滤图片，只选择图片
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_IMAGE);
                break;
        }
    }

    /**
     * 通过这个方法调用另外一个avtivity并接受返回值的。
     * 这个方法会在 startActivityForResult 的Intent对应的activity
     * 返回的时候就回到了你原来的 activity 中调用 onActivityResult()了。
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE) {
            if (data != null) {
                Uri uri = data.getData();
                ContentResolver resolver = getContentResolver();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(resolver, uri);
                    bitmap = Bitmap.createScaledBitmap(bitmap, 180, 180, true);
                    my_personal_usericon.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}


