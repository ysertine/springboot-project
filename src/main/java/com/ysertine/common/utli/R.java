package com.ysertine.common.utli;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title R.java
 * @Description 返回消息封装类
 * @author DengJinbo
 * @date 2019年1月14日
 */
public class R extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	/**
	 * @Title R.java 默认返回消息
	 * @Description 状态码200，消息“success”
	 * @author DengJinbo
	 * @date 2019年1月14日
	 */
	public R() {
		put("code", 200);
		put("msg", "success");
	}
	
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
	
	/**
	 * @Title ok
	 * @Description 请求成功，返回状态码200，消息内容：“success”
	 * @author DengJinbo
	 * @date 2019年1月14日
	 * @version 1.0
	 * @return HashMap
	 */
	public static R ok() {
		return new R();
	}
	
	/**
	 * @Title ok
	 * @Description 请求成功，返回状态码200
	 * @author DengJinbo
	 * @date 2019年1月14日
	 * @version 1.0
	 * @param msg 自定义消息内容
	 * @return HashMap
	 */
	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}

	/**
	 * @Title ok
	 * @Description 请求成功，返回状态码200
	 * @author DengJinbo
	 * @date 2019年1月14日
	 * @version 1.0
	 * @param map 自定义消息内容
	 * @return HashMap
	 */
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}

	/**
	 * @Title error
	 * @Description 请求失败，返回状态码400，消息内容：“error”
	 * @author DengJinbo
	 * @date 2019年1月14日
	 * @version 1.0
	 * @return HashMap
	 */
	public static R error() {
		return error(400, "error");
	}

	/**
	 * @Title error
	 * @Description 请求失败，返回状态码400和自定义的消息内容
	 * @author DengJinbo
	 * @date 2019年1月14日
	 * @version 1.0
	 * @param msg 自定义消息内容
	 * @return HashMap
	 */
	public static R error(String msg) {
		return error(400, msg);
	}

	/**
	 * @Title error
	 * @Description 请求失败，返回自定义状态码和消息内容
	 * @author DengJinbo
	 * @date 2019年1月14日
	 * @version 1.0
	 * @param code 自定义状态码
	 * @param msg 自定义消息内容
	 * @return HashMap
	 */
	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}
}