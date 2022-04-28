package com.efounder.JEnterprise.controller.interfaceUtil;

import com.alibaba.fastjson.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ISSPUtil {
	
	private ISSPUtil() {

	}
	
	/**
	 * 获取数据开始时间
	 * 
	 * @param version 版本号
	 * @return
	 */
	public static String getSTime(Long startTime) {
		return startTime == null ? null : longToDate(startTime);
	}
	
	public static String getETime(Long endTime){
		return endTime == null ? null : longToDate(endTime);
	}
	
	/**
	 * 上次检索数据结束时间作为这次开始时间
	 * @param version
	 * @return
	 */
	public static String getDataStart(String version) {
		if (version != null && version.indexOf("@") != -1)
			return longToDate(Long.valueOf(version.split("@")[0]));
		else
			return null;
	}
	
	/**
	 * 根据版本号获取上次检索id
	 * @param version
	 * @return
	 */
	public static String getId(String version) {
		if (version != null && version.indexOf("@") != -1)
			return version.split("@")[1];
		else
			return null;
	}
	
	/**
	 * 接口正常,无数据返回时
	 * 
	 * @param version 版本号
	 * @return
	 */
	public static JSONObject resultNoData(String version) {
		JSONObject obj = new JSONObject();
		obj.put("version", version);
		obj.put("status", "success");
		obj.put("data", null);
		return obj;
	}
	
	/**
	 * 接口异常时,返回值
	 * 
	 * @param version 版本号
	 * @return
	 */
	public static JSONObject resultException() {
		JSONObject obj = new JSONObject();
		obj.put("status", "fail");
		obj.put("version", null);
		obj.put("data", null);
		return obj;
	}
	
	/**
	 * 处理接口返回数据(date为String类型)
	 * @param date 最后一条数据的时间
	 * @param id 最后一条数据的id
	 * @param list 数据list
	 * @return
	 */
	public static JSONObject processData(String date, Object id, List<?> list) {
		JSONObject obj = new JSONObject();
		StringBuffer sb = new StringBuffer(String.valueOf(dateToLong(date)));
		sb.append("@");
		sb.append(id);
		obj.put("version", sb.toString());
		obj.put("status", "success");
		JSONObject baseJObject = new JSONObject();
		baseJObject.put("list", list);
		obj.put("data", baseJObject);
		return obj;
	}
	
	/**
	 * 处理接口返回数据(date为Date类型)
	 * @param date 最后一条数据的时间
	 * @param id 最后一条数据的id
	 * @param list 数据list
	 * @return
	 */
	public static JSONObject processData(Date date, Object id, List<?> list) {
		JSONObject obj = new JSONObject();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatdate = formatter.format(date);
		StringBuffer sb = new StringBuffer(String.valueOf(ISSPUtil.dateToLong(formatdate)));
		sb.append("@");
		sb.append(id);
		obj.put("version", sb.toString());
		obj.put("status", "success");
		JSONObject listObject = new JSONObject();
		listObject.put("list", list);
		obj.put("data", listObject);
		return obj;
	}
	
	/**
	 * 时间类型转换(long->date)
	 * @param lo
	 * @return
	 */
	public static String longToDate(long lo) {
		if (lo == 0L) {
			return null;
		}
		Date date = new Date(lo);
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sd.format(date);
	}
	
	/**
	 *  时间类型转换
	 * @param time(date->long)
	 * @return
	 */
	public static Long dateToLong(String time) {
		if (time == null) {
			return 0L;
		}
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = sdf.parse(time);
			return date.getTime();
		} catch (Exception e) {
			return 0L;
		}
	}
	
	/**
	 * 格式化String类型字符串
	 * 将 yyyy-MM-dd HH:mm:ss.0 格式化为 yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String formatDate(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return formatter.format(formatter.parse(date));
		} catch (ParseException e) {
		}
		return date;
	}

}
