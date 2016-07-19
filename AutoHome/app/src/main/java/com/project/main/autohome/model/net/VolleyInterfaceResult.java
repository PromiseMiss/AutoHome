package com.project.main.autohome.model.net;

/**
 * Created by youyo on 2016/7/13 0013.
 * 网络请求接口类
 */
public interface VolleyInterfaceResult {
    // 成功
    void success(String str);

    // 失败
    void failure();
}
