package com.project.main.autohome.tools;

import android.widget.Toast;

import com.project.main.autohome.ui.activity.MyApp;


/**
 * Created by youyo on 2016/7/11 0011.
 */
public class T {

    private static boolean isDebug = true;

    public static void longMsg(String msg) {
        if (isDebug) {
            Toast.makeText(MyApp.getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    public static void shortMsg(String msg) {
        if (isDebug) {
            Toast.makeText(MyApp.getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }
}
