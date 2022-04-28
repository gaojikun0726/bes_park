package com.core.common.util;

import com.core.common.data.ISPRowSet;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 
 * @author Alvin
 *
 */
public final class MapUtil {
	/**
	 * 
	 * @param map
	 *            Map
	 * @param keyName
	 *            String
	 * @param value
	 *            Number
	 */
	public static void putNumber(Map map, String keyName, Number value) {
		putObject(map, keyName, value);
	}

	/**
	 *
	 * @param map
	 *            Map
	 * @param keyName
	 *            String
	 * @param defValue
	 *            Number
	 * @return Number
	 */
	public static Number getNumber(Map map, String keyName, Number defValue) {
		return getNumber(map, keyName, defValue, null);
	}

	/**
	 * 
	 * @param map
	 *            Map
	 * @param keyName
	 *            String
	 * @param defValue
	 *            Number
	 * @param depRowSet
	 *            ESPRowSet
	 * @return Number
	 */
	public static Number getNumber(Map map, String keyName, Number defValue,
			ISPRowSet depRowSet) {
		Object value = getObject(map, keyName, defValue, depRowSet);
		if (value instanceof Number && value != null) {
			return (Number) value;
		} else if (value != null && isNumber(value)) {
			BigDecimal number = new BigDecimal((String) value);
			return number;
		} else {
			return defValue;
		}
	}

	// 正则表达式数字验证,是否数字
	public static boolean isNumber(Object value) {
		if (value == null)
			return false;

		if (value instanceof String) {
			if (value.toString().trim().length() == 0)
				return false;
			String numberPress = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";
			java.util.regex.Pattern pattern = java.util.regex.Pattern
					.compile(numberPress);// "[0-9]*"
			java.util.regex.Matcher match = pattern.matcher(value.toString());
			if (match.matches() == false) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	/**
	 *
	 * @param map
	 * @param keyName
	 * @param value
	 */
	public static void putString(Map map, String keyName, String value) {
		putObject(map, keyName, value);
	}

	/**
	 *
	 * @param map
	 *            Map
	 * @param keyName
	 *            String
	 * @param defValue
	 *            String
	 * @return String
	 */
	public static String getString(Map map, String keyName, String defValue) {
		return getString(map, keyName, defValue, null);
	}

	/**
	 * 
	 * @param map
	 * @param keyName
	 * @param defValue
	 * @param depRowSet
	 * @return String
	 */
	public static String getString(Map map, String keyName, String defValue,
			ISPRowSet depRowSet) {
		Object value = getObject(map, keyName, defValue, depRowSet);
		if (value instanceof String && value != null) {
			return (String) value;
		} else {
			return defValue;
		}
	}

	public static void putBoolean(Map map, String keyName, Boolean value) {
		putObject(map, keyName, value);
	}

	/**
	 *
	 * @param map
	 *            Map
	 * @param keyName
	 *            String
	 * @param defValue
	 *            Boolean
	 * @return Boolean
	 */
	public static Boolean getBoolean(Map map, String keyName, Boolean defValue) {
		return getBoolean(map, keyName, defValue, null);
	}

	/**
	 *
	 * @param map
	 *            Map
	 * @param keyName
	 *            String
	 * @param defValue
	 *            Boolean
	 * @param depRowSet
	 *            ESPRowSet
	 * @return Boolean
	 */
	public static Boolean getBoolean(Map map, String keyName, Boolean defValue,
			ISPRowSet depRowSet) {
		Object value = getObject(map, keyName, defValue, depRowSet);
		if (value instanceof Boolean && value != null) {
			return (Boolean) value;
		} else {
			return defValue;
		}
	}

	/**
	 *
	 * @param map
	 * @param keyName
	 * @param value
	 */
	public static void putDate(Map map, String keyName, Date value) {
		putObject(map, keyName, value);
	}

	/**
	 *
	 * @param map
	 *            Map
	 * @param keyName
	 *            String
	 * @param defValue
	 *            Date
	 * @return Date
	 */
	public static Date getDate(Map map, String keyName, Date defValue) {
		return getDate(map, keyName, defValue, null);
	}

	/**
	 * 
	 * @param map
	 * @param keyName
	 * @param defValue
	 * @param depRowSet
	 * @return Date
	 */
	public static Date getDate(Map map, String keyName, Date defValue,
			ISPRowSet depRowSet) {
		Object value = getObject(map, keyName, defValue, depRowSet);
		if (value instanceof Date && value != null) {
			return (Date) value;
		} else {
			return defValue;
		}
	}

	/**
	 *
	 * @param map
	 * @param keyName
	 * @param value
	 */
	public static void putObject(Map map, String keyName, Object value) {
		if (map == null) {
			return;
		}
		if (value == null)
			map.remove(keyName);
		else
			map.put(keyName, value);
	}

	public static Object getObject(Map map, String keyName, Object defValue) {
		return getObject(map, keyName, defValue, null);
	}

	/**
	 * 
	 * @param map
	 *            Map
	 * @param keyName
	 *            String
	 * @param defValue
	 *            Object
	 * @param depRowSet
	 *            ESPRowSet
	 * @return Object
	 */
	public static Object getObject(Map map, String keyName, Object defValue,
			ISPRowSet depRowSet) {
		if (map == null) {
			return defValue;
		}
		Object value = map.get(keyName);
		if (value instanceof Object && value != null) {
			return (Object) value;
		} else {
			if (depRowSet != null) {
				return depRowSet.getObject(keyName, defValue);
			}
			return defValue;
		}
	}
}