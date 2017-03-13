package com.android.utils;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 处理JSON异常的Util
 * @author Andy.li 上午10:33:21
 *
 */
public class JSONUtilBase {
	/**
	 * 判断JS0N 是否正常
	 * @param context
	 * @param json
	 * @return
	 */
	public static String isNormal(Context context,String json){
		try {
			JSONObject jsonObject = new JSONObject(json);
			String status = jsonObject.getString("status");
			String msg =jsonObject.getString("msg");
		 if(status.equals("0")){
				Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
				return "";
			}else if(status.equals("1")){
				return jsonObject.getString("data");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Toast.makeText(context, "数据异常", Toast.LENGTH_SHORT).show();
			return "";
		}
		
		return "";
	}
	public static String isNormal(Context context,String json,Boolean isShowMsg){
		try {
			JSONObject jsonObject = new JSONObject(json);
			String status = jsonObject.getString("status");
			String msg =jsonObject.getString("msg");
			if(status.equals("0")){
				if(isShowMsg==true){
					Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
				}
				return "";
			}else if(status.equals("1")){
				return jsonObject.getString("data");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Toast.makeText(context, "数据异常", Toast.LENGTH_SHORT).show();
			return "";
		}

		return "";
	}
	/**
	 * 状态类JSON  只需要判断成功与否
	 * @param context
	 * @param json
	 * @return
	 */
	public static boolean isSuccess(Context context,String json){
		try {
			JSONObject jsonObject = new JSONObject(json);
			String status = jsonObject.getString("status");
			String msg =jsonObject.getString("msg");
		if(status.equals("0")){
				Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
				return false;
			} else if(status.equals("1")){
					Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
				return true;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Toast.makeText(context, "数据异常", Toast.LENGTH_SHORT).show();
			return false;
		}

		return false;
	}
}
