package com.ysertine.system.util;

/**
 * @Title StringUtils.java
 * @Description String工具类
 * @author DengJinbo
 * @date 2019年1月8日
 */
public class StringUtils {

	/**
	 * @Title isBlank 
	 * @Description 判断字符串是否为空
	 * @author DengJinbo
	 * @date 2019年1月8日
	 * @version 1.0
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return (str == null || str.trim().length() == 0);
	}
}
