package com.android.Application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

public class BaseApplication extends Application{
	public static SharedPreferences user_sp;
	//需要在启动页 在SharedPreferences （name为phone）   存入宽高
	public static int Screen_width=0,Screen_height=0,StateHeight=0;//屏幕分辨率
	public static List<Activity> All_activitys=new ArrayList<Activity>();
	public static BaseApplication instance;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
			instance = this;
	}
	public static int getScreen_width(Context context) {
		if(Screen_width==0){
			SharedPreferences sharedPreferences = context.getSharedPreferences("phone", Context.MODE_PRIVATE);
			Screen_width=sharedPreferences.getInt("Screen_width", 0);
		}
		return Screen_width;
	}
	public static int getScreen_height(Context context) {
		if(Screen_height==0){
			SharedPreferences sharedPreferences = context.getSharedPreferences("phone", Context.MODE_PRIVATE);
			Screen_height=sharedPreferences.getInt("Screen_height", 0);
		}
		return Screen_height;
	}
	public static int getStateHeight(Context context) {
		if(StateHeight==0){
			SharedPreferences sharedPreferences = context.getSharedPreferences("phone", Context.MODE_PRIVATE);
			StateHeight=sharedPreferences.getInt("StateHeight", 0);
		}
		return StateHeight;
	}
	public  static  SharedPreferences getUser_sp(Context context) {
		if(user_sp==null){
			user_sp= context.getSharedPreferences("user", Context.MODE_PRIVATE);
		}
		return user_sp;
	}
	/**
	 　　* 获取版本号
	 　　* @return 当前应用的版本号
	 　　*/
	public static  String getVersion(Context context) {
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
			String version = info.versionName;
			return version;
		} catch (PackageManager.NameNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return "";
	}
}
