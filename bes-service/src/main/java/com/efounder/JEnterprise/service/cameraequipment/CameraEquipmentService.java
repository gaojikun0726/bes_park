package com.efounder.JEnterprise.service.cameraequipment;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface CameraEquipmentService {

	/**
	 * 视频播放参数
	 * @param devId 设备ID
	 * @return
	 */
	JSONObject getVideoInfoByDevId(String devId);
	
	/**
	 * 摄像机设备信息
	 * @param devId 设备ID
	 * @return
	 */
	JSONObject getCameraEquipmentInfo(String devId, String version);
	
	/**
	 * 摄像机Hls视频流信息
	 * @param devId 设备ID
	 * @return
	 */
	JSONObject getHlsVideoStreamInfo(String devId);


	/**
	 * 视频播放方式
	 * @param devId 设备ID
	 * @return
	 */
	Map<String, Object> getVideoMethodByDevId(String devId);


	/**
	 * 摄像机Rtsp视频流信息
	 * @param devId 设备ID
	 * @return
	 */
	JSONObject getRtspVideoStreamInfo(String devId);
	/**
	 * 摄像机Rtsp视频流信息
	 * @param devId 设备ID
	 * @return
	 */
	JSONObject getRtspVideoStreamInfo2(String devId);

	/**
	 * 摄像机Rtmp视频流信息
	 * @param devId 设备ID
	 * @return
	 */
	JSONObject getRtmpVideoStreamInfo(String devId);

	/**
	 * 摄像机设备状态信息
	 * @param deviceId
	 * @author liuhoujun
	 * @return
	 */
	JSONObject getCameraStatusInfo(String deviceId);
	/**
	 * 获取所有摄像机基础信息
	 * @return
	 */
	JSONObject getCameraBaseInfo(String localInfo);
	
	/**
	 * 获取摄像机道路树
	 * @return
	 */
	JSONObject getCameraBaseTree();
	
}
