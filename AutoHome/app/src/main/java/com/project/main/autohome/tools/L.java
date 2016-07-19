package com.project.main.autohome.tools;

import android.util.Log;

/**
 * Created by youyo on 2016/7/11 0011.
 * 自定义打Log的实体类
 */
public class L {
    //    被final修饰 不能被继承
    //    私有构造方法，外部不能创建对象
    //    私有构造方法
    private L() {

    }

    //    调试模式 目前定义为true
    private static boolean isDebug = true;
    //    log日志的标签，定义的是应用名
    private static String TAG = "MyDemo";


    public static void e(String msg) {
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }


    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }

}
