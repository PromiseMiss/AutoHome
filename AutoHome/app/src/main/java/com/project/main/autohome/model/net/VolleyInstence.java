package com.project.main.autohome.model.net;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by youyo on 2016/7/13 0013.
 */
public class VolleyInstence {
    //    请求队列
    private RequestQueue queue;
    private static VolleyInstence instence;

    private VolleyInstence(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    // 对外提供对象的方法
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

/*    public void startRequest(String url, final VolleyInterfaceResult result) {
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
    }*/

}
