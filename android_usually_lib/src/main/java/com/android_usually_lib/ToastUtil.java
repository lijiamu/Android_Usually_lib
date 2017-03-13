package com.android_usually_lib;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by ${Andy.li} on 2017/3/13.
 */

public class ToastUtil {
    public static  void Toast(Context context,String content){
        Toast.makeText(context,content,Toast.LENGTH_SHORT).show();
    }
}
