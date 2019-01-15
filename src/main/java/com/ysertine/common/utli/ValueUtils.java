package com.ysertine.common.utli;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Title ValueUtils.java
 * @Description 参数值类型转换工具类
 * @author DengJinbo
 * @date 2019年1月15日
 */
public class ValueUtils {

	/**
	 * @Title stringValue 
	 * @Description 获取字符串，如果字符串内容为空，则返回默认值
	 * @author DengJinbo
	 * @date 2019年1月15日
	 * @version 1.0
	 * @param origin 转换源
	 * @param defaultValue 默认返回值
	 * @return
	 */
	public static String stringValue(String origin, String defaultValue) {
		if (origin == null || origin.length() == 0) {
			return defaultValue;
		}
		return origin;
	}
	
	/**
	 * @Title intValue 
	 * @Description 字符串转换成Int类型
	 * @author DengJinbo
	 * @date 2019年1月15日
	 * @version 1.0
	 * @param origin 转换源
	 * @param defaultValue 默认返回值
	 * @return
	 */
	public static int intValue(String origin, int defaultValue) {
		if (origin == null || origin.length() == 0) {
			return defaultValue;
		}
		try {
			return Integer.parseInt(origin.trim());
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	/**
	 * @Title longValue 
	 * @Description 字符串转换成long类型
	 * @author DengJinbo
	 * @date 2019年1月15日
	 * @version 1.0
	 * @param origin 转换源
	 * @param defaultValue 默认返回值
	 * @return
	 */
	public static long longValue(String origin, long defaultValue) {
		if (origin == null || origin.length() == 0) {
			return defaultValue;
		}
		try {
			return Long.parseLong(origin.trim());
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	/**
	 * @Title dateValue 
	 * @Description 字符串转换成Date
	 * @author DengJinbo
	 * @date 2019年1月15日
	 * @version 1.0
	 * @param origin 转换源
	 * @param format 转换格式
	 * @param defaultValue 默认返回值
	 * @return
	 */
	public static Date dateValue(String origin, String format, Date defaultValue) {
		if (origin == null || origin.length() == 0) {
			return defaultValue;
		}
		try {
			return new SimpleDateFormat(format).parse(origin.trim());
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	/**
	 * @Title doubleValue 
	 * @Description 符串转换成Double类型
	 * @author DengJinbo
	 * @date 2019年1月15日
	 * @version 1.0
	 * @param origin 转换源
	 * @param defaultValue 默认返回值
	 * @return
	 */
	public static double doubleValue(String origin, double defaultValue) {
		if (origin == null || origin.length() == 0) {
			return defaultValue;
		}
		try {
			return Double.parseDouble(origin.trim());
		} catch (Exception e) {
			return defaultValue;
		}
	}
}