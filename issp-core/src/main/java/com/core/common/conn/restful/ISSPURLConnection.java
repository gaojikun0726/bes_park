package com.core.common.conn.restful;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

/**
 * HttpURLConnection 方式调用Restful接口
 * 
 * @author Alvin
 * @datetime 2018年5月28日
 *
 */
public class ISSPURLConnection {
	
	/**
	 * 处理URL请求
	 * 
	 * @param url
	 *            请求URL
	 * @param method
	 *            请求方式 GET POST DELETE PUT
	 * @param param
	 *            请求参数
	 * @return result
	 */
	public static String processRequest(String url, String method, JSONObject urlParam) {
		return processRequest(url, method, urlParam, new JSONObject());
	}

	/**
	 * 处理URL请求
	 * 
	 * @param url
	 *            请求URL
	 * @param method
	 *            请求方式 GET POST DELETE PUT
	 * @param urlParam
	 *            请求参数，拼接在url后
	 * @param param
	 *            请求参数            
	 * @return result
	 */
	public static String processRequest(String url, String method, JSONObject urlParam, JSONObject param) {
		String result = null;
		HttpURLConnection httpConnection = null;
		try {
			StringBuffer urlAppend = null;
			if (urlParam != null) {
				urlAppend = new StringBuffer("?");
				Set<String> keySet = urlParam.keySet();
				for (String key : keySet) {
					urlAppend.append(key);
					urlAppend.append("=");
					urlAppend.append(urlParam.get(key));
					urlAppend.append("&");
				}
			}
			if(urlAppend != null){				
				url += urlAppend.substring(0, urlAppend.length() - 1);
			}
			URL restServiceURL = new URL(url);
			httpConnection = (HttpURLConnection) restServiceURL.openConnection();
			// method 输入小写，转换成 GET POST DELETE PUT
			httpConnection.setRequestMethod(method.toUpperCase());
			if(param.getString("token") != null){				
				httpConnection.setRequestProperty("Cookie", "JSESSIONID=" + param.getString("token"));
			}
			String accept = param.getString("Accept") == null ? "application/json" : param.getString("Accept");
			String contentType = param.getString("Content-Type") == null ? "application/json" : param.getString("Content-Type");
			httpConnection.setRequestProperty("Accept", accept);
			httpConnection.setRequestProperty("Content-Type", contentType);
			// setConnectTimeout：设置连接主机超时（单位：毫秒）
			httpConnection.setConnectTimeout(3*1000);
			// setReadTimeout：设置从主机读取数据超时（单位：毫秒）
//			httpConnection.setReadTimeout(3*1000);
			//得到响应码  
			int responseCode = httpConnection.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("请求失败，错误码: "
						+ httpConnection.getResponseCode() + "错误信息："
						+ httpConnection.getResponseMessage());
			}
			StringBuffer sb = new StringBuffer();
			String readLine = new String();
			//得到响应流  
            InputStream inputStream = httpConnection.getInputStream();
            //获取响应  
			BufferedReader responseReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			while ((readLine = responseReader.readLine()) != null) {
				sb.append(readLine);
			}
			result = sb.toString();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpConnection.disconnect();
		}
		return result;
	}
	/**
	 * 处理URL请求
	 * 
	 * @param url
	 *            请求URL
	 * @param method
	 *            请求方式 GET POST DELETE PUT
	 * @param urlParam
	 *            请求参数，拼接在url后
	 * @param param
	 *            请求参数            
	 * @return result
	 */
	public static String processCameraRequest(String url, String method, JSONObject urlParam) {
		String result = null;
		HttpURLConnection httpConnection = null;
		try {
			StringBuffer urlAppend = null;
			if (urlParam != null) {
				urlAppend = new StringBuffer("?");
				Set<String> keySet = urlParam.keySet();
				for (String key : keySet) {
					urlAppend.append(key);
					urlAppend.append("=");
					urlAppend.append(urlParam.get(key));
					urlAppend.append("&");
				}
			}
			if(urlAppend != null){				
				url += urlAppend.substring(0, urlAppend.length() - 1);
			}
			URL restServiceURL = new URL(url);
			httpConnection = (HttpURLConnection) restServiceURL.openConnection();
			// method 输入小写，转换成 GET POST DELETE PUT
			httpConnection.setRequestMethod(method.toUpperCase());
			//httpConnection.setRequestProperty("Accept", "application/json");
			//httpConnection.setRequestProperty("Content-Type", "application/json");
			// setConnectTimeout：设置连接主机超时（单位：毫秒）
			httpConnection.setConnectTimeout(3*1000);
			// setReadTimeout：设置从主机读取数据超时（单位：毫秒）
//			httpConnection.setReadTimeout(3*1000);
			//得到响应码  
			int responseCode = httpConnection.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("请求失败，错误码: "
						+ httpConnection.getResponseCode() + "错误信息："
						+ httpConnection.getResponseMessage());
			}
			StringBuffer sb = new StringBuffer();
			String readLine = new String();
			//得到响应流  
			InputStream inputStream = httpConnection.getInputStream();
			//获取响应  
			BufferedReader responseReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			while ((readLine = responseReader.readLine()) != null) {
				sb.append(readLine);
			}
			result = sb.toString();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpConnection.disconnect();
		}
		return result;
	}
	
	/**
	 * 情报板内容修改请求使用
	 * @param url
	 * @param method
	 * @param urlParam
	 * @return
	 */
	public static String boardProcessRequest(String url, String method, JSONObject urlParam) {
		String result = null;
		HttpURLConnection httpConnection = null;
		try {
			StringBuffer urlAppend = null;
			if (urlParam != null) {
				urlAppend = new StringBuffer("/");
				Set<String> keySet = urlParam.keySet();
				for (String key : keySet) {
					urlAppend.append(urlParam.get(key));
					urlAppend.append("/");
				}
			}
			if(urlAppend != null){				
				url += urlAppend.substring(0, urlAppend.length() - 1);
			}
			URL restServiceURL = new URL(url);
			httpConnection = (HttpURLConnection) restServiceURL.openConnection();
			// method 输入小写，转换成 GET POST DELETE PUT
			httpConnection.setRequestMethod(method.toUpperCase());
			httpConnection.setRequestProperty("Accept", "application/json");
			httpConnection.setRequestProperty("Content-Type", "application/json");
			// setConnectTimeout：设置连接主机超时（单位：毫秒）
			httpConnection.setConnectTimeout(10*1000);
			// setReadTimeout：设置从主机读取数据超时（单位：毫秒）
//			httpConnection.setReadTimeout(3*1000);
			//得到响应码  
			int responseCode = httpConnection.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("请求失败，错误码: "
						+ httpConnection.getResponseCode() + "错误信息："
						+ httpConnection.getResponseMessage());
			}
			StringBuffer sb = new StringBuffer();
			String readLine = new String();
			//得到响应流  
            InputStream inputStream = httpConnection.getInputStream();
            //获取响应  
			BufferedReader responseReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			while ((readLine = responseReader.readLine()) != null) {
				sb.append(readLine);
			}
			result = sb.toString();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpConnection.disconnect();
		}
		return result;
	}

	public static void main(String[] args) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("uName", "wujifeng");
		jsonObject.put("uPwd", "123456");

		String rw1 = ISSPURLConnection.processRequest("http://172.16.13.202:8080/QLJTIOTWebapp/issp/v1.0/login", "post", jsonObject);
		JSONObject parseObject = JSONObject.parseObject(rw1);
		String ttt = (String) parseObject.get("token");
		JSONObject json = new JSONObject();
		json.put("token", ttt);
		
		String rw31 = ISSPURLConnection.processRequest("http://172.16.13.202:8080/QLJTIOTWebapp/issp/v1.0/getCycleTrafficInfo", "get", null, json);
		System.out.println(rw31);
	}

}