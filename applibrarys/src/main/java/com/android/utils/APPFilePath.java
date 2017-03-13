package com.android.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * 获取缓存路径Util
 */
public class APPFilePath {
    public static String setImageDownPath(Context context) {
        String ImageDownLoadPath = "";
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            // sd card 可用  
            ImageDownLoadPath = getSDPath() + "/." + context.getPackageName() + "/";// SD卡存储路径
            File file = new File(ImageDownLoadPath);
            if (!file.exists()) {
                file.mkdirs();
            }
        } else {
            //当前不可用
            ImageDownLoadPath = context.getCacheDir().getAbsolutePath().toString() + "/";// 内部存储
            File file = new File(ImageDownLoadPath);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        return ImageDownLoadPath;
    }

    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);   //判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }
        return sdDir.toString();

    }

    public static String getImageSavePath(Context context) {
        String ImageDownLoadPath = "";
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            // sd card 可用
            ImageDownLoadPath = getSDPath() + "/CamerImage/";// SD卡存储路径
            File file = new File(ImageDownLoadPath);
            if (!file.exists()) {
                file.mkdirs();
            }
        } else {
            //当前不可用
            ImageDownLoadPath = context.getCacheDir().getAbsolutePath().toString() + "/";// 内部存储
            File file = new File(ImageDownLoadPath);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        return ImageDownLoadPath;
    }
}
