package com.android.utils;

import android.content.Context;

public class DensityUtil {

	

	// dip转像素
	public static int DipToPixels(Context context, int dip) {
		final float SCALE = context.getResources().getDisplayMetrics().density;
		float valueDips = dip;
		int valuePixels = (int) (valueDips * SCALE + 0.5f);
		return valuePixels;

	}

	// 像素转dip
	public static float PixelsToDip(Context context, int Pixels) {
		final float SCALE = context.getResources().getDisplayMetrics().density;
		float dips = Pixels / SCALE;
		return dips;

	}
	 /**
	  * 将px值转换为sp值，保证文字大小不变
	  *
	  * @param pxValue
	  * @param fontScale（DisplayMetrics类中属�?�scaledDensity�??
	  * @return
	  */
	 public static int px2sp(float pxValue, float fontScale) {
	  return (int) (pxValue / fontScale + 0.5f);
	 }
	 /**
      * 将sp值转换为px值，保证文字大小不变
      * 
      * @param spValue
      * @param fontScale
      *            （DisplayMetrics类中属�?�scaledDensity�?
      * @return
      */ 
     public static int sp2px(Context context, float spValue) { 
         final float fontScale = context.getResources().getDisplayMetrics().scaledDensity; 
         return (int) (spValue * fontScale + 0.5f); 
     } 
}
