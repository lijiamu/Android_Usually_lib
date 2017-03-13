package com.android.utils;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2016/5/30.
 * 关于数学的一些计算
 */
public class MathUtil {
    public static String setDouble_String(String st){
        DecimalFormat df = new DecimalFormat("0.00");
        Double d = new Double(st+"");
        return df.format(d);
    }
}
