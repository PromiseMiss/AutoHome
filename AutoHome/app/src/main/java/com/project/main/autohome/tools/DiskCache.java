package com.project.main.autohome.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.android.volley.toolbox.ImageLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by youyo on 2016/7/14 0014.
 */
public class DiskCache implements ImageLoader.ImageCache {
    //    定义存储在硬盘中的位置
    private final String cachePath = "sdcard/DownLoad";

    @Override
    public Bitmap getBitmap(String url) {
        return BitmapFactory.decodeFile(cachePath + url + ".png");
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        FileOutputStream fos = null;// 定义
        //        判断文件是否存在
        File file = new File(cachePath);
        if (!file.exists()) {
            //            如果不存在， 创建
            file.mkdir();
        }
        File imgFile = new File(cachePath, url + ".png");
        //        判断是否存在，如果不存在创建该文件，
        if (!imgFile.exists()) {
            try {
                imgFile.createNewFile();
                // 存储到sd卡中
                fos = new FileOutputStream(imgFile);
                // 将图片放入输出流
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // 关流
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
