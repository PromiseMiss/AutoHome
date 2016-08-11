package cn.sharesdk;

import cn.sharesdk.framework.authorize.AuthorizeAdapter;

/**
 * Created by youyo on 2016/8/6 0006.
 * <p/>
 * 隐藏powered by shareSdk 的类
 */
public class MyAdapter extends AuthorizeAdapter {
    public void onCreate() {
        // 隐藏标题栏右部的ShareSDK Logo
        hideShareSDKLogo();
    }
}
