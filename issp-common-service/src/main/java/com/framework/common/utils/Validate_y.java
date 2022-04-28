package com.framework.common.utils;

import java.text.DecimalFormat;

/**
 * 验证类
 * 
 * 
 */
public final class Validate_y {
	/**
	 * 处理数据中的空值
	 * */
	public static String NullValueToEmpty(Object obj){
		return obj == null ? "" : obj.toString();
	}
	/**
	 * 判断字符串是否为空
	 * 
	 */
	public static boolean isNullOrEmpty(String strs){
		return strs == null || "".equals(strs) ? true : false;
	}
	/**
	 * 自动保留两位小数点
	 * @param obj
	 * @return
	 */
	public static String addPoint(String obj){
		DecimalFormat df= new DecimalFormat("#######0.00");
		return isNullOrEmpty(obj) ? "" : df.format(Double.parseDouble(obj.toString()));
    }
	/**
	 * 验证空<br>
	 * 若是字符串则不是空，也不是空字符串
	 * @param object
	 *            要判断的值
	 * @return 若是空，返回true；反之，返回false
	 */
	public static boolean isNull(Object object) {
		return object == null || "null".equals(object.toString().trim())
				|| "".equals(object.toString().trim());
	}

	/**
	 * 验证空<br>
	 * 若是字符串则不是空，也不是空字符串
	 * @param object
	 *            要判断的值
	 * @return 不是空，返回true；反之，返回false
	 */
	public static boolean noNull(Object object) {
		return object != null && !"".equals(object.toString().trim())
				&& !"null".equals(object.toString().trim());
	}

	/**
	 * 验证空并返回默认值<br>
	 * 第一个参数若是空，则返回第二个默认值参数
	 * @param object
	 *            要判断的值
	 * @param defaultObject
	 *            默认值
	 * @return 若是空，返回defaultValue；反之，返回object
	 */
	public static Object isNullToDefault(Object object, Object defaultObject) {
		if (object != null && !"".equals(object.toString().trim())
				&& !"null".equals(object.toString().trim()))
			return object;
		else
			return defaultObject;
	}
	
	/**
	 * 验证空并返回默认值<br>
	 * 第一个参数若是空，则返回第二个默认值参数
	 * @param object
	 *            要判断的值
	 * @param defaultObject
	 *            默认值
	 * @return 若是空，返回defaultValue；反之，返回object
	 */
	public static String isNullToDefaultString(Object object, String defaultObject) {
		if (object != null && !"".equals(object.toString().trim())
				&& !"null".equals(object.toString().trim()))
			return object.toString().trim();
		else
			return defaultObject;
	}
	
}
