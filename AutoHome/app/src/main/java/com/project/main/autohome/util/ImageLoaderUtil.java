package com.project.main.autohome.util;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.project.main.autohome.ui.activity.MyApp;

/**
 * 网络请求工具类
 */
public class ImageLoaderUtil {

    private static ImageLoaderUtil instance;

    private ImageLoaderUtil() {
        init();
    }


    public static ImageLoaderUtil getInstance() {
        if (instance == null) {
            instance = new ImageLoaderUtil();
        }
        return instance;
    }

    private DisplayImageOptions options;
    private ImageLoader mUiImageLoader;

    // configBuilder.imageDownloader();
    private void init() {

        mUiImageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2).build();
        ImageLoaderConfiguration.Builder configBuilder = new ImageLoaderConfiguration.Builder(MyApp.getContext());
        configBuilder.defaultDisplayImageOptions(options);
        ImageLoaderConfiguration config = configBuilder.build();
        mUiImageLoader.init(config);

    }

    public void load(String uri, ImageView imageAware) {
        if (TextUtils.isEmpty(uri)) {
            return;
        }
        if (imageAware == null) {
            return;
        }
        if (mUiImageLoader != null && options != null) {
            try {
                mUiImageLoader.displayImage(uri, imageAware, options);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void loadRes(int uri, ImageView imageAware) {
        load("drawable://" + uri, imageAware);
    }

    public void loadDisk(String uri, ImageView imageAware) {
        load("file:///mnt/sdcard/" + uri, imageAware);
    }

    public Bitmap loadBitmapByHttp(String uri) {
        return mUiImageLoader.loadImageSync(uri);
    }

}