package com.android.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;

/**
 * @title AppUtils.java<br>
 * @Copyright Copyright ©2014<br>
 * @description App验证和提示工具类<br>
 * 
 * @author kougin
 * @version v1.0
 * @date 2014-6-15 上午11:10:01
 */
public class InstallApkUtil {
	/**
	 * 安装apk
	 * 
	 * @param context
	 * @param file
	 */
	public static void installApk(Context context, File file) {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.setType("application/vnd.android.package-archive");
		intent.setData(Uri.fromFile(file));
		intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

}
