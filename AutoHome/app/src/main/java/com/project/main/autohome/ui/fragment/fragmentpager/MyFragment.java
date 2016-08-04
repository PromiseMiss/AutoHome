package com.project.main.autohome.ui.fragment.fragmentpager;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.project.main.autohome.R;
import com.project.main.autohome.ui.adapter.MyIntoAdapter;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by youyo on 2016/7/11 0011.
 * 我 页 （主要是Tablayout 就一页，个人中心页）
 */
public class MyFragment extends AbsBaseFragment implements View.OnClickListener, View.OnLongClickListener {
    private TabLayout mytab;
    private ViewPager myPager;
    private MyIntoAdapter myAdapter;
    private List<String> myTitle;
    private List<Fragment> myFragment;
    private ImageView my_QR_Code;
    /*扫描跳转Activity*/
    public static final int REQUEST_CODE = 111;
    /*选择系统图片*/
    public static final int REQUEST_IMAGE = 112;

    @Override
    protected int setLayout() {

        return R.layout.my_fargment;
    }

    @Override
    protected void initView() {
        mytab = byView(R.id.my_tabLayout);
        myPager = byView(R.id.my_vp);
        my_QR_Code = byView(R.id.my_QR_Code);
    }

    @Override
    protected void initData() {
        myTitle = new ArrayList<>();
        myFragment = new ArrayList<>();
        myFragment.add(new MyChildFragment()); /// TabLayout布局视图
        myTitle.add("我");
        myAdapter = new MyIntoAdapter(getChildFragmentManager(), myFragment, myTitle);
        myPager.setAdapter(myAdapter);
        mytab.setTabMode(TabLayout.GRAVITY_FILL);
        mytab.setupWithViewPager(myPager);
        //图片监听与长按监听
        my_QR_Code.setOnClickListener(this);
        my_QR_Code.setOnLongClickListener(this);

    }

    /**
     * 设置监听，打开摄像头进行二维码扫描
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * 对返回结果进行解析和显示
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getContext(), "解析结果:" + result, Toast.LENGTH_SHORT).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getContext(), "解析失败", Toast.LENGTH_SHORT).show();
                }
            }
        }
/*获取图库图片*/
        else if (requestCode == REQUEST_IMAGE) {
            if (data != null) {
                Uri uri = data.getData();
                ContentResolver resolver = getContext().getContentResolver();
                try {
                    Bitmap mBitmap = MediaStore.Images.Media.getBitmap(resolver, uri);

                    CodeUtils.analyzeBitmap(mBitmap, new CodeUtils.AnalyzeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            Toast.makeText(getContext(), "解析结果：" + result, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onAnalyzeFailed() {
                            Toast.makeText(getContext(), "失败！", Toast.LENGTH_SHORT).show();
                        }
                    });
                    if (mBitmap != null) {
                        mBitmap.recycle();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 长按图片调出图库选择二维码解析
     *
     * @param v
     * @return
     */
    @Override
    public boolean onLongClick(View v) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_IMAGE);
        return true;
    }
}
