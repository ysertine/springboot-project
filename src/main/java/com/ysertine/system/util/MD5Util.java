package com.ysertine.system.util;

import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title MD5Util.java
 * @Description MD5工具类
 * @author DengJinbo
 * @date 2019年1月4日
 */
public class MD5Util {

	/**
	 * @Title byteArrayToHexString
	 * @Description 二进制字节数组转换成十六进制字符串
	 * @author DengJinbo
	 * @date 2019年1月4日
	 * @version 1.0
	 * @param byteArr
	 * @return
	 */
	private static String byteArrayToHexString(byte bytes[]) {
		StringBuffer hexStr = new StringBuffer();
		int num;
		for (int i = 0; i < bytes.length; i++) {
			num = bytes[i];
			if (num < 0) {
				num += 256;
			}
			if (num < 16) {
				hexStr.append("0");
			}
			hexStr.append(Integer.toHexString(num));
		}
		return hexStr.toString().toUpperCase();
	}

	/**
	 * @Title unicodeToString 
	 * @Description Unicode中文编码转换成字符串
	 * @author DengJinbo
	 * @date 2019年1月4日
	 * @version 1.0
	 * @param str
	 * @return
	 */
	public static String unicodeToString(String str) {
		Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
		Matcher matcher = pattern.matcher(str);
		char ch;
		while (matcher.find()) {
			ch = (char) Integer.parseInt(matcher.group(2), 16);
			str = str.replace(matcher.group(1), ch + "");
		}
		return str;
	}
	
	/**
	 * @Title MD5Encode 
	 * @Description 将字符串进行MD5加密
	 * @author DengJinbo
	 * @date 2019年1月4日
	 * @version 1.0
	 * @param origin 需要加密的字符串
	 * @return
	 */
	public static String MD5Encode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes("UTF-8")));
		} catch (Exception exception) {
			
		}
		return resultString;
	}

	/**
	 * @Title MD5Encode 
	 * @Description 将字符串进行MD5加密
	 * @author DengJinbo
	 * @date 2019年1月4日
	 * @version 1.0
	 * @param origin 需要加密的字符串
	 * @param charset 字符串编码，默认UTF-8
	 * @return
	 */
	public static String MD5Encode(String origin, String charset) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charset == null || "".equals(charset)) {
				resultString = byteArrayToHexString(md.digest(resultString.getBytes("UTF-8")));
			} else {
				resultString = byteArrayToHexString(md.digest(resultString.getBytes(charset)));
			}
		} catch (Exception exception) {
			
		}
		return resultString;
	}
}