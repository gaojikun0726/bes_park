package com.efounder.JEnterprise.adapterProcess.camera;

import com.alibaba.fastjson.JSONObject;
import com.core.common.conn.restful.WebservicePlugin;
import com.core.common.util.WebservicePluginUtil;

import java.rmi.RemoteException;

/**
 * 海康视频接入服务类
 * 
 * @author huangxianbo
 * @datetime 2018年10月19日
 */
public class VideoProcess {
	
	static String endpoint = "http://10.50.13.248:8014/services/ThirdInfoService?wsdl";// 请求URL
	//static String endpoint = "http://60.208.23.59:8014/services/ThirdInfoService?wsdl";// 请求URL
	static String nameSpace = "http://webservice.cms.hikvision.com/"; // 命名空间
	static String reqParamTitle = "<?xml version=\"1.0\" ?><requestInfo>"; //请求参数头
	
	/**
	 * 登录
	 * @return
	 * @throws RemoteException 
	 */
	public static net.sf.json.JSONObject userLogin() throws RemoteException {
		StringBuffer reqParam = new StringBuffer(reqParamTitle);
		reqParam.append("<userName>admin</userName>"); //登陆用户名(String)
		reqParam.append("<pwd>Ql0812!@#</pwd>"); //登陆密码(String)
		reqParam.append("<clientIp>172.16.14.54</clientIp>"); //客户端ip(String)
		reqParam.append("<clientPort>8014</clientPort>"); //客户端端口(Int)
//		reqParam.append("<cmsIP>10.50.13.248</cmsIP>"); //gv cms服务器IP(String)
		reqParam.append("<cmsIP>60.208.23.59</cmsIP>"); //gv cms服务器IP(String)
		reqParam.append("</requestInfo>");
		JSONObject jsonObj = getReqObj("userLogin", reqParam.toString());
		Object result = WebservicePlugin.executeProcessService(jsonObj);
		return WebservicePluginUtil.parseXmlToJSONObj(result.toString());
	}
	
	/**
	 * 登出
	 * @param sessionId 用户登陆sessionId
	 * @return
	 */
	public static net.sf.json.JSONObject userLogout(String sessionId) {
		net.sf.json.JSONObject obj = null;
		try {
			StringBuffer reqParam = new StringBuffer(reqParamTitle);
			reqParam.append("<sessionId>").append(sessionId).append("</sessionId>"); //<!--用户sessionid(String)-->
			reqParam.append("</requestInfo>");
			JSONObject jsonObj = getReqObj("userLogout", reqParam.toString());
			Object result = WebservicePlugin.executeProcessService(jsonObj);
			obj = WebservicePluginUtil.parseXmlToJSONObj(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * 用户在线心跳测试
	 * @param sessionId 用户登陆sessionId
	 * @return
	 */
	public static net.sf.json.JSONObject userOnlineHeartbeat(String sessionId) {
		net.sf.json.JSONObject obj = null;
		try {
			StringBuffer reqParam = new StringBuffer(reqParamTitle);
			reqParam.append("<sessionId>").append(sessionId).append("</sessionId>"); //用户sessionId(String)
			reqParam.append("</requestInfo>");
			JSONObject jsonObj = getReqObj("userOnlineHeartbeat", reqParam.toString());
			Object result = WebservicePlugin.executeProcessService(jsonObj);
			obj = WebservicePluginUtil.parseXmlToJSONObj(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * 分页获取组织机构列表
	 * 
	 * @param sessionId 用户登陆sessionId
	 * @param currentPage 当前页
	 * @param pageSize 每页记录数
	 * @param parentUnitId 组织ID(根组织节点为登录用户组织id)
	 * @param isFirstSubUnit 是否只获取第一级子组织(1:第一级 , 2:所有下级)
	 * @return
	 */
	public static net.sf.json.JSONObject getAllControlCenterForList(String sessionId, int currentPage, int pageSize, int parentUnitId, int isFirstSubUnit) {
		net.sf.json.JSONObject obj = null;
		try {
			StringBuffer reqParam = new StringBuffer(reqParamTitle);
			reqParam.append("<sessionId>").append(sessionId).append("</sessionId>");
			reqParam.append("<commonPageReq>"); 
			reqParam.append("<currentPage>").append(currentPage).append("</currentPage>");
			reqParam.append("<pageSize>").append(pageSize).append("</pageSize>");
			reqParam.append("</commonPageReq>"); 
			reqParam.append("<dataReq>");
			reqParam.append("<parentUnitId>").append(parentUnitId).append("</parentUnitId>");
			reqParam.append("<isFirstSubUnit>").append(isFirstSubUnit).append("</isFirstSubUnit>");
			reqParam.append("</dataReq>");
			reqParam.append("</requestInfo>");
			JSONObject jsonObj = getReqObj("getAllControlCenterForList", reqParam.toString());
			Object result = WebservicePlugin.executeProcessService(jsonObj);
			obj = WebservicePluginUtil.parseXmlToJSONObj(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * 分页获取设备列表
	 * 
	 * @param sessionId 用户登陆sessionId
	 * @param currentPage 当前页
	 * @param pageSize 每页记录数
	 * @param controlUnitId 组织ID(0表示所有组织)
	 * @return
	 */
	public static net.sf.json.JSONObject getAllDevice(String sessionId, int currentPage, int pageSize, int controlUnitId) {
		net.sf.json.JSONObject obj = null;
		try {
			StringBuffer reqParam = new StringBuffer(reqParamTitle);
			reqParam.append("<sessionId>").append(sessionId).append("</sessionId>");
			reqParam.append("<commonPageReq>"); 
			reqParam.append("<currentPage>").append(currentPage).append("</currentPage>");
			reqParam.append("<pageSize>").append(pageSize).append("</pageSize>");
			reqParam.append("</commonPageReq>"); 
			reqParam.append("<dataReq>");
			reqParam.append("<controlUnitId>").append(controlUnitId).append("</controlUnitId>");
			reqParam.append("</dataReq>");
			reqParam.append("</requestInfo>");
			JSONObject jsonObj = getReqObj("getAllDevice", reqParam.toString());
			Object result = WebservicePlugin.executeProcessService(jsonObj);
			obj = WebservicePluginUtil.parseXmlToJSONObj(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * 分页获取监控点
	 * 
	 * @param sessionId 用户登陆sessionId
	 * @param currentPage 当前页
	 * @param pageSize 每页记录数
	 * @param controlUnitId 组织ID(0表示所有组织)
	 * @param regionId 区域ID(0表示所有区域)
	 * @return
	 */
	public static net.sf.json.JSONObject getAllCamera(String sessionId, int currentPage, int pageSize, int controlUnitId, int regionId) {
		net.sf.json.JSONObject obj = null;
		try {
			StringBuffer reqParam = new StringBuffer(reqParamTitle);
			reqParam.append("<sessionId>").append(sessionId).append("</sessionId>");
			reqParam.append("<commonPageReq>"); 
			reqParam.append("<currentPage>").append(currentPage).append("</currentPage>");
			reqParam.append("<pageSize>").append(pageSize).append("</pageSize>");
			reqParam.append("</commonPageReq>"); 
			reqParam.append("<dataReq>");
			reqParam.append("<controlUnitId>").append(controlUnitId).append("</controlUnitId>");
			reqParam.append("<regionId>").append(regionId).append("</regionId>");
			reqParam.append("</dataReq>");
			reqParam.append("</requestInfo>");
			JSONObject jsonObj = getReqObj("getAllCamera", reqParam.toString());
			Object result = WebservicePlugin.executeProcessService(jsonObj);
			obj = WebservicePluginUtil.parseXmlToJSONObj(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * 获取预览所需的参数
	 * 
	 * @param sessionId 用户登陆sessionId
	 * @param cameraId 监控点ID
	 * @param netZoneId 网域ID
	 * @return
	 */
	public static net.sf.json.JSONObject getPreviewParam(String sessionId, String cameraId, int netZoneId){
		net.sf.json.JSONObject obj = null;
		try {
			Object result = getPreviewParamXML(sessionId, cameraId, netZoneId);
			obj = WebservicePluginUtil.parseXmlToJSONObj(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * 获取预览所需的参数(xml形式)
	 * 
	 * @param sessionId 用户登陆sessionId
	 * @param cameraId 监控点ID
	 * @param netZoneId 网域ID
	 * @return
	 */
	public static Object getPreviewParamXML(String sessionId, String cameraId, int netZoneId){
		Object result = null;
		try {
			StringBuffer reqParam = new StringBuffer(reqParamTitle);
			reqParam.append("<sessionId>").append(sessionId).append("</sessionId>");
			reqParam.append("<previewParam>"); 
			reqParam.append("<cameraId>").append(cameraId).append("</cameraId>");
			reqParam.append("<netZoneID>").append(netZoneId).append("</netZoneID> ");
			reqParam.append("</previewParam>"); 
			reqParam.append("</requestInfo>");
			JSONObject jsonObj = getReqObj("getPreviewParam", reqParam.toString());
			result = WebservicePlugin.executeProcessService(jsonObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取回放所需的参数
	 * 
	 * @param sessionId 用户登陆sessionId
	 * @param cameraId 监控点ID
	 * @param recordLocation 存储类型
	 * @param playWndIndex 播放窗口
	 * @param netZoneId 网域ID
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param recordType 录像类型
	 * @return
	 */
	public static net.sf.json.JSONObject getPlaybackParam(String sessionId, int cameraId, String recordLocation, String playWndIndex, int netZoneId, String startTime, String endTime, String recordType){
		net.sf.json.JSONObject obj = null;
		try {
			StringBuffer reqParam = new StringBuffer(reqParamTitle);
			reqParam.append("<sessionId>").append(sessionId).append("</sessionId>");
			reqParam.append("<playbackParam>"); 
			reqParam.append("<cameraId>").append(cameraId).append("</cameraId>");
			reqParam.append("<recordLocation>").append(recordLocation).append("</recordLocation>");
			reqParam.append("<playWndIndex>").append(playWndIndex).append("</playWndIndex>");
			reqParam.append("<netZoneID>").append(netZoneId).append("</netZoneID>");
			reqParam.append("<startTime>").append(startTime).append("</startTime>");
			reqParam.append("<endTime>").append(endTime).append("</endTime>");
			reqParam.append("<recordType>").append(recordType).append("</recordType>");
			reqParam.append("</playbackParam>"); 
			reqParam.append("</requestInfo>");
			JSONObject jsonObj = getReqObj("getPlaybackParam", reqParam.toString());
			Object result = WebservicePlugin.executeProcessService(jsonObj);
			obj = WebservicePluginUtil.parseXmlToJSONObj(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * 分页获取设备在线状态
	 * 
	 * @param sessionId 用户登陆sessionId
	 * @param currentPage 当前页
	 * @param pageSize 每页记录数
	 * @param controlUnitId 组织ID(0表示所有组织)
	 * @return
	 */
	public static net.sf.json.JSONObject getDeviceStatus(String sessionId, int currentPage, int pageSize, int controlUnitId) {
		net.sf.json.JSONObject obj = null;
		try {
			StringBuffer reqParam = new StringBuffer(reqParamTitle);
			reqParam.append("<sessionId>").append(sessionId).append("</sessionId>");
			reqParam.append("<commonPageReq>"); 
			reqParam.append("<currentPage>").append(currentPage).append("</currentPage>");
			reqParam.append("<pageSize>").append(pageSize).append("</pageSize>");
			reqParam.append("</commonPageReq>"); 
			reqParam.append("<dataReq>");
			reqParam.append("<controlUnitId>").append(controlUnitId).append("</controlUnitId>");
			reqParam.append("</dataReq>");
			reqParam.append("</requestInfo>");
			JSONObject jsonObj = getReqObj("getDeviceStatus", reqParam.toString());
			Object result = WebservicePlugin.executeProcessService(jsonObj);
//			System.out.println("result is " + result);
			obj = WebservicePluginUtil.parseXmlToJSONObj(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * 分页获取监控点在线状态
	 * 
	 * @param sessionId 用户登陆sessionId
	 * @param currentPage 当前页
	 * @param pageSize 每页记录数
	 * @param controlUnitId 组织ID(0表示所有组织)
	 * @param regionId 区域ID(0表示所有区域)
	 * @return
	 */
	public static net.sf.json.JSONObject getCameraStatus(String sessionId, int currentPage, int pageSize, int controlUnitId, int regionId) {
		net.sf.json.JSONObject obj = null;
		try {
			StringBuffer reqParam = new StringBuffer(reqParamTitle);
			reqParam.append("<sessionId>").append(sessionId).append("</sessionId>");
			reqParam.append("<commonPageReq>"); 
			reqParam.append("<currentPage>").append(currentPage).append("</currentPage>");
			reqParam.append("<pageSize>").append(pageSize).append("</pageSize>");
			reqParam.append("</commonPageReq>"); 
			reqParam.append("<dataReq>");
			reqParam.append("<controlUnitId>").append(controlUnitId).append("</controlUnitId>");
			reqParam.append("<regionId>").append(regionId).append("</regionId>");
			reqParam.append("</dataReq>");
			reqParam.append("</requestInfo>");
			JSONObject jsonObj = getReqObj("getCameraStatus", reqParam.toString());
			Object result = WebservicePlugin.executeProcessService(jsonObj);
			obj = WebservicePluginUtil.parseXmlToJSONObj(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * 获取请求参数对象
	 * @param reqMethodName 请求方法名
	 * @param paramVal 请求参数值
	 * @return
	 */
	private static JSONObject getReqObj(String reqMethodName, String paramVal){
		JSONObject obj = new JSONObject();
		obj.put("url", endpoint);
		obj.put("nameSpace", nameSpace);
		obj.put("methodName", reqMethodName);
		obj.put("paramNames", "arg0");
		obj.put("params", paramVal);
		return obj;
	}
}
