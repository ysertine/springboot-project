package com.ysertine.common.constant;

/**
 * @Title StatusEnum.java
 * @Description 状态枚举类
 * @author DengJinbo
 * @date 2019年1月21日
 */
public enum StatusEnum {
	
	STATUS_DELETE(-1, "删除"), STATUS_FORBIDDEN(0, "禁用"), 
	STATUS_NORMAL(1, "正常"), STATUS_NONEED(-99, "无效");

	/**
	 * 索引
	 */
	private int index;
	
	/**
	 * 描述
	 */
	private String desc;

	/**
	 * 构造函数
	 * @param index
	 * @param desc
	 */
	StatusEnum(int index, String desc) {
		this.index = index;
		this.desc = desc;
	}

	/**
	 * getIndex
	 * @return
	 */
	/**
	 * @Title getIndex 
	 * @Description 获取索引
	 * @author DengJinbo
	 * @date 2019年1月21日
	 * @version 1.0
	 * @return
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @Title getDesc 
	 * @Description 获取描述
	 * @author DengJinbo
	 * @date 2019年1月21日
	 * @version 1.0
	 * @return
	 */
	public String getDesc() {
		return desc;
	}
        
	/**
	 * @Title getIndex 
	 * @Description 根据索引获取常量
	 * @author DengJinbo
	 * @date 2019年1月21日
	 * @version 1.0
	 * @param index
	 * @return
	 */
	public static StatusEnum getIndex(int index) {
		for (StatusEnum e : StatusEnum.values()) {
			if (e.getIndex() == index) {
				return e;
			}
		}
		return null;
	}
	
	/**
	 * @Title getDesc 
	 * @Description 根据索引获取描述
	 * @author DengJinbo
	 * @date 2019年1月21日
	 * @version 1.0
	 * @param index
	 * @return
	 */
	public static String getDesc(int index) {
		StatusEnum e = StatusEnum.getIndex(index);
        return (null != e) ? e.getDesc() : "";
	}
}