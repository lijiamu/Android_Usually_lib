package com.android.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 判断是否为null
 */
public class DateUtil {
	/**
	 *
	 * @param list
	 * @return  false 不为空  true  为空
	 */
	public static Boolean isEmpty(List<?> list){
		if(list!=null &&list.size()>0){
			return false;
		}
		return true;
		
	}
	/**
	 * 把时间戳转换为年月日
	 * @return
	 */
	public static String getDate(String time) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = formatter.format(Long.valueOf(time+"000"));
		return format;
	}
	/**
	 * 把时间戳转换为年月日
	 * @return
	 */
	public static String getDate_2(String time) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = formatter.format(Long.valueOf(time));
		return format;
	}
	/**
	 * 把年月日转换为时间戳
	 * @return
	 */
	public static Long getDateLong(String time) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date data;
		Long data_long = 0l;
		try {
			data = formatter.parse(time);
			data_long = data.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data_long;
	}
	/**
	 * 把年月日转换为时间戳
	 * @return
	 */
	public static Long getDateLongNYR(String time) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date data;
		Long data_long = 0l;
		try {
			data = formatter.parse(time);
			data_long = data.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data_long;
	}
}
