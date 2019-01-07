package com.ysertine.system.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Title BaseEntity.java
 * @Description 基础实体类
 * @author DengJinbo
 * @date 2019年1月7日
 */
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 8655851615465363473L;
	
	/**
	 * 通用的toString函数
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		try {
			Field[] fields = this.getClass().getDeclaredFields();
			sb.append(this.getClass().getSimpleName()).append(" : {");
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				field.setAccessible(true);
				sb.append(field.getName());
				sb.append(" : ");
				if (field.getType() == String.class) {
					String text = (String) field.get(this);
					sb.append('"');
					if (text != null && text != "") {
						if (text.length() > 100) {
							sb.append(text.substring(0, 100)).append("...");
						} else {
							sb.append(text);
						}
					}
					sb.append('"');
				} else if (field.getType() == Date.class) {
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Object object = field.get(this);
					sb.append('"');
					if (object != null && object != "") {
						sb.append(simpleDateFormat.format(object));
					}
					sb.append('"');
				} else {
					sb.append(field.get(this));
				}
				if (i < fields.length - 1) {
					sb.append(", ");
				}
			}
			sb.append("}");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("generate entity toString failed.");
		}
		return sb.toString();
	}
}