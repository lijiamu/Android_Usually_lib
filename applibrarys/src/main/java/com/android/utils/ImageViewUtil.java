package com.android.utils;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * @author Administrator
 *  根据图片的宽高 动态的计算View的宽高
 */
public class ImageViewUtil {
	//根据控件的宽  动态设置高
	public static void resetImageSize(ImageView image, int width, int originalWidth,
									  int originalHeight) {

		int height = (int) ((float) originalHeight * width / originalWidth);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,height);
		image.setLayoutParams(params);
	}
	//根据控件的宽  动态设置高
	public static void resetImageSize_FrameLayout(View image, int width, int originalWidth,
												  int originalHeight) {

		int height = (int) ((float) originalHeight * width / originalWidth);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width,height);
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
