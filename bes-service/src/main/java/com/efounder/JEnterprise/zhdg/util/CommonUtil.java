package com.efounder.JEnterprise.zhdg.util;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CommonUtil {

	/**
	 * 获取中国格式的年月日
	 * 
	 * @return
	 */
	public static String getDate() {
		Date current = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		return sdf.format(current);
	}

	public static String get_Date() {
		Date current = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(current);
	}

	/**
	 * 格式化时间
	 *
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	public static String get_SimpleDate() {
		Date current = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(current);
	}

	public static String getCurrentYearAndMonth() {
		Date current = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		return sdf.format(current);
	}

	/**
	 * 计算小数点后n位的方法。摄取法
	 *
	 * @param number
	 * @param n
	 * @return
	 */
	public static String keepPointDigit(String number, int n) {
		if (CommonUtil.isNull(number))
			return number;
		String[] temp = number.split("\\.");
		if (temp.length == 2) {
			if (n == 0)
				return temp[0];
			if (n > temp[1].length())
				n = temp[1].length();
			return temp[0] + "." + temp[1].substring(0, n);
		}
		return number;
	}

	public static String dayOfWeek(String dateStr) {
		if (CommonUtil.isNull(dateStr))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = sdf.parse(dateStr);
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
			return getWeekName(dayOfWeek);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getWeekName(int i) {
		Map<Integer, String> values = new HashMap<Integer, String>();
		values.put(1, "周日");
		values.put(2, "周一");
		values.put(3, "周二");
		values.put(4, "周三");
		values.put(5, "周四");
		values.put(6, "周五");
		values.put(7, "周六");
		return values.get(i);
	}

	/**
	 * 获取二进制字符串。根据输入的number建立二进制字符串的位置为1
	 * 
	 * @param seq
	 * @return
	 */
	public static String getBinay(int... seq) {
		double result = 0.0;
		// 1.预防输入重复的数值。
		Set<Integer> sequences = new HashSet<Integer>();
		for (int num : seq)
			sequences.add(num);
		// 2.将所有的非重复值相加
		for (int _num : sequences) {
			result += Math.pow(2, _num - 1);
		}
		// 3.转换成二进制
		return Long.toBinaryString(Math.round(result));
	}

	/**
	 * 根据二进制数获取值为1的位置
	 * 
	 * @param binayString
	 * @return
	 */
	public static Set<Integer> getSequences(String binayString) {
		/*
		 * 1.参数校验
		 */
		if (CommonUtil.isNull(binayString))
			return null;
		// 2.预防输入重复的数值。
		StringBuffer sb = new StringBuffer(binayString);
		String binayReverse = sb.reverse().toString();
		Set<Integer> result = new HashSet<Integer>();
		int position = 0;
		for (char seq : binayReverse.toCharArray()) {
			int _position = position++;
			if (seq == '1')
				result.add(_position + 1);
		}
		// 3.转换成二进制
		return result;
	}

	/**
	 * 获得本月的第一天
	 * 
	 * @return
	 */
	public static String getThisMonthFirstDay() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	/**
	 * 获得本月的最后一天
	 * 
	 * @return
	 */
	public static String getThisMonthLastDay() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.roll(Calendar.DAY_OF_MONTH, -1);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	/**
	 * 获取以空格隔开的字符串的值
	 * 
	 * @param intevalStr
	 * @param sequences
	 * @return
	 */
	public static String getIntevalData(String intevalStr, int... sequences) {
		if (CommonUtil.isNull(intevalStr))
			return null;
		String[] _p = intevalStr.split(" ");
		int length = _p.length;
		StringBuffer sb = new StringBuffer();

		for (int seq : sequences) {
			if (seq >= length)
				throw new IllegalArgumentException("下标[" + seq + "],超过字符数组长度["
						+ intevalStr + "]");
			sb.append(_p[seq]).append(" ");
		}
		return sb.toString().trim();
	}

	/**
	 * 获取以空格隔开的字符串的值
	 * 
	 * @param intevalStr
	 * @param startSequent
	 * @param endSequent
	 * @return
	 */
	public static String getIntevalData(String intevalStr, int startSequent,
			int endSequent) {
		if (CommonUtil.isNull(intevalStr))
			return null;
		String[] _p = intevalStr.split(" ");

		if (endSequent > _p.length)
			throw new IllegalArgumentException("下标[" + endSequent
					+ "],超过字符数组长度[" + intevalStr + "]");

		StringBuffer sb = new StringBuffer();

		for (int i = startSequent; i <= endSequent; i++) {
			sb.append(_p[i]).append(" ");
		}
		return sb.toString().trim();
	}

	/**
	 * 获取以空格隔开的字符串的值
	 * 
	 * @param intevalStr
	 * @param sequences
	 * @return
	 */
	public static String getIntevalData(String intevalStr, String sequences) {
		if (sequences == null || sequences.trim().length() <= 0)
			return null;
		String[] _sequents = sequences.split("-");

		if (_sequents != null && _sequents.length == 1)
			return getIntevalData(intevalStr, Integer.parseInt(sequences));

		if (_sequents != null && _sequents.length == 2) {
			int startSequent = Integer.parseInt(_sequents[0]);
			int endSequent = Integer.parseInt(_sequents[1]);
			return getIntevalData(intevalStr, startSequent, endSequent);
		}
		return null;
	}

	/**
	 * 根据协议规则，切分协议。如：规则为："0-1:header;2-4:address;4-7:data;8-9:crc;10:end"
	 * 
	 * @param protocol
	 * @param rules
	 * @return
	 */
	public static Map<String, String> getProtocolDatas(String protocol, String rules) {
		/*
		 * 1.参数校验
		 */
		if (CommonUtil.isNull(protocol, rules)) {
			return new HashMap<String, String>();
		}
		String[] _rules = rules.split(";");
		Map<String, String> result = new HashMap<String, String>();
		result.put("_protocol_" + System.currentTimeMillis(), protocol);
		for (String rule : _rules) {
			String[] _ruleConent = rule.split(":");
			if (_ruleConent == null || _ruleConent.length != 2)
				continue;
			String key = _ruleConent[1];
			String sequent = _ruleConent[0];
			result.put(key, getIntevalData(protocol, sequent));
		}
		return result;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param strs
	 * @return
	 */
	public static boolean isNull(String... strs) {
		for (String str : strs) {
			if (str == null || str.trim().length() <= 0)
				return true;
		}
		return false;
	}

	/**
	 * 
	 * 将指定格式的字符串，分解成数组。格式如:FAULT:02;SUCCESS:Y;
	 * 
	 * @param str
	 *            格式为 key:value;key:value;key:value
	 * @return
	 */
	public static Map<String, String> splitStringToMap(String str) {
		Map<String, String> result = new HashMap<String, String>();
		if (isNull(str))
			return result;
		String[] strs = str.split(";");
		for (String _str : strs) {
			if (isNull(strs))
				continue;
			String[] keyValue = _str.split(":");
			if (keyValue == null || keyValue.length != 2)
				continue;
			result.put(keyValue[0], keyValue[1]);
		}
		return result;
	}

	/**
	 * 
	 * 将java对象转换成json对象，并打印到页面上。
	 * 
	 * @param obj
	 * @param response
	 */
	public static void writeJsonObject(Object obj, HttpServletResponse response) {
		JSONObject jsonObject = JSONObject.parseObject((String) obj);
		writeObjectToView(jsonObject, response);
	}

	/**
	 * 
	 * 将json串打印到页面上。
	 * 
	 * @param jsonString
	 * @param response
	 */
	public static void writeStringObject(Object jsonString, HttpServletResponse response) {
		writeObjectToView(jsonString, response);
	}

	/**
	 * 
	 * 将对象写到页面上。
	 * 
	 * @param object
	 * @param response
	 */
	public static void writeObjectToView(Object object, HttpServletResponse response) {
		/*
		 * 1.写入页面
		 */
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.print(object);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从bean的getter方法名字中，解析出bean的名称。
	 * 
	 * @param getMethodName
	 *            getter的方法名称
	 * @return bean的属性名称。
	 */
	public static String getBeanNameFromGetMethodName(String methodname) {
		if (isNull(methodname) || methodname.length() < 4) {
			return null;
		}
		if (methodname.length() == 4) {
			return methodname.substring(3).toLowerCase();
		}
		return methodname.substring(3, 4).toLowerCase()
				+ methodname.substring(4);
	}

	/**
	 * 
	 * 字符串按照分隔符切分，返回指定索引的段。查不到则返回null
	 * 
	 * @param str
	 *            待搜索的字符串
	 * @param index
	 *            索引
	 * @param split
	 *            分隔符。
	 * @return
	 */
	public static String find(String str, int index, String split) {
		if (isNull(str))
			return null;
		if (index < 0)
			return null;
		String[] strs = str.split(split);
		if (strs.length > index)
			return strs[index];
		return null;
	}

	/**
	 * 获得当前的时间，格式为：2010-9-9
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		return getCurrentTime(null);
	}

	/**
	 * 获得当前的时间，格式为：2010-9-9
	 * 
	 * @param sperator
	 * @return
	 */
	public static String getCurrentTime(String sperator) {
		if (isNull(sperator))
			sperator = "-";
		Calendar c = Calendar.getInstance();
		return c.get(1) + sperator + (c.get(2) + 1) + sperator + c.get(5);
	}
}
