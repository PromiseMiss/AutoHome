package com.project.main.autohome.tools;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by youyo on 2016/7/14 0014.
 * <p/>
 * 内存缓存工具类
 */
public class MemoryCache implements ImageLoader.ImageCache {

    private LruCache<String, Bitmap> caches;

    public MemoryCache() {
        //        设置最大内存
        int maxSize = (int) Runtime.getRuntime().maxMemory() / 2 / 1024;
        caches = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String url, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return caches.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        caches.put(url, bitmap);
    }
}
