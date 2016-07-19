package com.project.main.autohome.tools;


import android.graphics.Bitmap;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by youyo on 2016/7/14 0014.
 * @date
 * 二级缓存类
 * 判断是否从内存或硬盘获取
 */
public class DoubleCache implements ImageLoader.ImageCache {
    private ImageLoader.ImageCache memoryCache;
    private ImageLoader.ImageCache diskCache;

    public DoubleCache() {
        memoryCache = new MemoryCache();
        diskCache = new DiskCache();
    }

    @Override
    public Bitmap getBitmap(String url) {
        // 先从内存获取
        Bitmap bitmap = memoryCache.getBitmap(url);
        // 如果内存没有
        if (bitmap == null) {
            // 从硬盘获取
            bitmap = diskCache.getBitmap(url);
        }
        return bitmap;
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        memoryCache.putBitmap(url, bitmap);
        diskCache.putBitmap(url, bitmap);
    }
}
