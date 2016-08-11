package com.project.main.autohome.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.project.main.autohome.R;

/**
 * Created by youyo on 2016/7/31 0031.
 * <p>
 * 判断网络是否连接并打印 Toast 的单例
 */
public class NetWorkConnectedToast {
    private static NetWorkConnectedToast connectedToast;

    private NetWorkConnectedToast() {
    }

    public static synchronized NetWorkConnectedToast getConnectedToast() {
        if (connectedToast == null) {
            connectedToast = new NetWorkConnectedToast();
        }
        return connectedToast;
    }

    /**
     * 自定义吐司
     * @param context
     * @param str
     * @param showTime
     */
    private void minToast(Context context, String str, int showTime) {
        Toast toast = Toast.makeText(context, str, showTime);
        View v = LayoutInflater.from(context).inflate(R.layout.toast_item, null);
        TextView text = (TextView) v.findViewById(R.id.tv_toast);
        toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.BOTTOM, 0, 160);
        text.setText(str);
        toast.setView(v);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * 对外提供的方法
     * @param context
     */
    public void isNet(Context context) {
        isNetWordConnected(context);
    }

    /**
     * 对内的网络判断是否连接方法
     * @param context
     * @return
     */
    private boolean isNetWordConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            } else {
                minToast(context, "当前网络不可用，请检查网络设置", 0);
            }
        }
        return false;
    }

}
