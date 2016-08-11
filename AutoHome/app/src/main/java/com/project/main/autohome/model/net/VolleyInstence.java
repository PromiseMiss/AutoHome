package com.project.main.autohome.model.net;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.project.main.autohome.R;
import com.project.main.autohome.tools.DoubleCache;

/**
 * Created by youyo on 2016/7/13 0013.
 * 网络请求，，图片缓存，单例
 */
public class VolleyInstence {
    //    请求队列
    private RequestQueue queue;
    private static VolleyInstence instence;
    private ImageLoader imageLoader;

    private VolleyInstence(Context context) {
        // 实例化请求队列
        queue = Volley.newRequestQueue(context);
        imageLoader = new ImageLoader(queue, new DoubleCache());
    }

    // 对外提供对象的方法(双重校验锁)
    public static VolleyInstence getInstence(Context context) {
        if (instence == null) {
            synchronized (VolleyInstence.class) {
                if (instence == null) {
                    instence = new VolleyInstence(context);
                }
            }
        }
        return instence;
    }

    public void startRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        //        开始请求
        StringRequest st = new StringRequest(url, listener, errorListener);
        //        加入队列
        queue.add(st);
    }


    public void startRequest(String url, final VolleyInterfaceResult result) {
        StringRequest sr = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                result.success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                result.failure();
            }
        });
        queue.add(sr);
    }

    /**
     * 图片缓存
     *
     * @param url
     * @param imageView
     */
    public void loadImage(String url, ImageView imageView) {
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView,
                R.mipmap.ahlib_logo_80_80, R.mipmap.ahlib_logo_80_80);
        imageLoader.get(url, listener);
    }

    public ImageLoader getImageLoader() {
        if (imageLoader == null) {
            imageLoader = new ImageLoader(queue, new DoubleCache());
        }
        return imageLoader;
    }
}
