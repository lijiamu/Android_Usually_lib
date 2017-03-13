package com.android.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;

/**
 * 获取关于手机的一些东西
 * @author Andy.li 下午2:12:36
 *
 */
public class PhoneUtil {
	public static final int REQUEST_CALL_PHONE=23;
	/**
	 * 获取手机唯一标识
	 * @return 
	 */
	public  static String getClientcode(Context context){
		TelephonyManager TelephonyMgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE); 
		String szImei = TelephonyMgr.getDeviceId();
		return szImei;
		
	}
	/**
	 * 拨打电话
	 * @param context
	 * @param phoneNumber
	 */
	public static void CallPhone(final Activity context, String phoneNumber){
		int checkSelfPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE);

		if(checkSelfPermission != PackageManager.PERMISSION_GRANTED){
			ActivityCompat.requestPermissions(context,new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);
			if(!ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.CALL_PHONE)){//是否设置显示dialog
				new AlertDialog.Builder(context)
						.setMessage("你需要启动拨打电话权限")
						.setPositiveButton("OK", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(PhotoPickActivity.this,"你需要启动权限WRITE_EXTERNAL_STORAGE",Toast.LENGTH_SHORT).show();
								ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CALL_PHONE);
							}
						})
						.setNegativeButton("Cancel",null)
						.create()
						.show();
			}
		}else{
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_CALL);
			//url:统一资源定位符
			//uri:统一资源标示符（更广）
			intent.setData(Uri.parse("tel:" + phoneNumber));
			//开启系统拨号器
			context.startActivity(intent);
		}
	}
}
