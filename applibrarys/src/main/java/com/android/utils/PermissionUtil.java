package com.android.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by Administrator on 2016/10/17.
 * 权限管理
 */
public class PermissionUtil {
    public static final int REQUEST_PERMISSION = 0;
    public static final int REQUEST_PERMISSION_NO = 1;

    /**
     * @param context
     * @return true 有权限  false 没有权限
     */
    public static Boolean getPermission(final Activity context, final String permission, String dialog_title) {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkSelfPermission = ContextCompat.checkSelfPermission(context, permission);
            if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(context, permission)) {//是否设置显示dialog
                    //ActivityCompat.requestPermissions(context, new String[]{permission}, REQUEST_PERMISSION);
                    new AlertDialog.Builder(context)
                            .setMessage( dialog_title)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //                                Toast.makeText(PhotoPickActivity.this,"你需要启动权限WRITE_EXTERNAL_STORAGE",Toast.LENGTH_SHORT).show();
                                    ActivityCompat.requestPermissions(context, new String[]{permission}, REQUEST_PERMISSION);
                                }
                            })
                            .setNegativeButton("Cancel",  new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //                                Toast.makeText(PhotoPickActivity.this,"你需要启动权限WRITE_EXTERNAL_STORAGE",Toast.LENGTH_SHORT).show();
                                    ActivityCompat.requestPermissions(context, new String[]{permission}, REQUEST_PERMISSION_NO);
                                }
                            })
                            .create()
                            .show();
                }
                return false;
            } else {
                //ActivityCompat.requestPermissions(context, new String[]{permission}, REQUEST_PERMISSION);
                return true;
            }
        } else {//6.0以下
            // ActivityCompat.requestPermissions(context, new String[]{permission}, REQUEST_PERMISSION);
            return true;
        }
    }
}
