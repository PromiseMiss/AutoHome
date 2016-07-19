package com.project.main.autohome.ui.activity;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by youyo on 2016/7/11 0011.
 *
 */
public class MyApp extends Application {
//    Application 是当前应用，只存在一个
    private static Context context;
    private static SharedPreferences sp;
//    public static void addActivity

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
    public static Context getContext(){
        return context;
    }

}
