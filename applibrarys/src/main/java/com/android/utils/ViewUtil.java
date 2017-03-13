package com.android.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * 对控件的一些点击效果设置
 * @author Andy.li 上午10:19:53
 *
 */
public class ViewUtil {
	/**
	 * 设置圆角
	 * @param Bgcolor_1 背景色
	 * @return
	 */
	public static  Drawable setRaduis(int Bgcolor_1){
		GradientDrawable gd = new GradientDrawable();//创建drawable
	    gd.setColor(Bgcolor_1);
	    gd.setCornerRadius(10);
	    gd.setStroke(1, Bgcolor_1);
		return gd;
	}
	/**
	 * 设置圆角
	 * @param bgColor 背景色
	 * @param strokeColor 边框色
	 * @param strokeWidth 边框宽度
	 * @param Radius 圆角幅度  
	 * @return
	 */
	public static  Drawable setViewRaduis(int bgColor,int strokeColor,int strokeWidth,int Radius){
		GradientDrawable gd = new GradientDrawable();//创建drawable
	    gd.setColor(bgColor);
	    gd.setCornerRadius(Radius);
	    gd.setStroke(strokeWidth, strokeColor);
		return gd;
	}
	/**
	 * 设置圆角按钮和手指触碰效果(需要边框)
	 * @param bgColor 背景色
	 * @param touchColor 触碰色
	 * @param strokeColor 边框色
	 * @param strokeColor_touch 触碰时边框色
	 * @param strokeWidth	边框宽
	 * @param Radius	边框幅度
	 * @return
	 */
	public static  Drawable setViewRaduisAndTouch(int bgColor,int touchColor,int strokeColor,int strokeColor_touch,int strokeWidth,int Radius){
		GradientDrawable gd_default = new GradientDrawable();//创建drawable
		gd_default.setColor(bgColor);
		gd_default.setCornerRadius(Radius);
		gd_default.setStroke(strokeWidth, strokeColor);
		
		GradientDrawable gd_touch = new GradientDrawable();//创建drawable
		gd_touch.setColor(touchColor);
		gd_touch.setCornerRadius(Radius);
		gd_touch.setStroke(strokeWidth, strokeColor_touch);
	    
		StateListDrawable drawable = new StateListDrawable();
		drawable.addState(new int[]{android.R.attr.state_pressed}, gd_touch);
		drawable.addState(new int[]{}, gd_default);
		return drawable;
	}
	/**
	 * 设置圆角按钮和手指触碰效果(不需要边框)
	 * @param bgColor 背景色
	 * @param touchColor 触碰色
	 * @param strokeWidth	边框宽
	 * @param Radius	边框幅度
	 * @return
	 */
	public static  Drawable setViewRaduisAndTouch(int bgColor,int touchColor,int Radius){
		GradientDrawable gd_default = new GradientDrawable();//创建drawable
		gd_default.setColor(bgColor);
		gd_default.setCornerRadius(Radius);
		gd_default.setStroke(1, bgColor);
		
		GradientDrawable gd_touch = new GradientDrawable();//创建drawable
		gd_touch.setColor(touchColor);
		gd_touch.setCornerRadius(Radius);
		gd_touch.setStroke(1, touchColor);
	    
		StateListDrawable drawable = new StateListDrawable();
		drawable.addState(new int[]{android.R.attr.state_pressed}, gd_touch);
		drawable.addState(new int[]{}, gd_default);
		return drawable;
	}
	/**
	 * 设置圆角按钮  一半圆角一半不要之类 根据Float数组决定
	 * 例如： // float[] radii = new float[] { 2f, 5f, 10f, 20f, 50f, 60f, 70f, 80 };
	 */
	public static  Drawable setViewRaduis_other(int bgColor,int strokeColor,int strokeWidth,float [] radii){
		GradientDrawable gd = new GradientDrawable();//创建drawable
	    gd.setColor(bgColor);
	    gd.setCornerRadii(radii);
	    gd.setStroke(strokeWidth, strokeColor);
		return gd;
	}

	//根据控件的宽  动态设置高
	public static void resetImageSize(ImageView image, int width, int originalWidth,
									  int originalHeight) {

		int height = (int) ((float) originalHeight * width / originalWidth);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,height);

		image.setLayoutParams(params);
	}
	//根据控件的高  动态设置宽
	public static void resetImageSize_height(ImageView image, int height, int originalWidth,
											 int originalHeight) {
		int width = (int) ((float) originalWidth * height / originalHeight);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,height);
		image.setLayoutParams(params);
	}
}
