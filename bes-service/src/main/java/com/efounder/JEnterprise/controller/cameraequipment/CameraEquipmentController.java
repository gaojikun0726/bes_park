package com.efounder.JEnterprise.controller.cameraequipment;

import com.alibaba.fastjson.JSONObject;
import com.core.common.conn.restful.ISSPURLConnection;
import com.efounder.JEnterprise.service.cameraequipment.CameraEquipmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 摄像机设备数据服务接口
 *
 * @author huangxianbo
 */
@RestController
@Api(value = "CameraEquipmentController", tags = {"摄像机"})
@ApiSort(value = 2)
public class CameraEquipmentController {
    private static final Logger logger = LoggerFactory.getLogger(CameraEquipmentController.class);

    @Autowired
    private CameraEquipmentService cameraEquipmentService;

//	/**
//	 * 查询视频播放所需参数信息
//	 *
//	 * @param devId
//	 * @param endTime
//	 * @param version
//	 */
//	@ApiOperation(value="视频播放参数", notes="通过设备ID获取摄像机设备播放视频所需参数")
//	@RequestMapping(value = "/issp/v1.0/getVideoInfoByDevId", method = RequestMethod.GET)
//	public JSONObject getVideoInfoByDevId(@ApiParam(value = "设备标识ID", required = true) @RequestParam String deviceId) {
//		logger.info("获取视频播放参数数据...");
//		// 获取视频播放所需参数信息
//		return cameraEquipmentService.getVideoInfoByDevId(deviceId);
//	}

    /**
     * 查询摄像机设备详情信息
     *
     * @param version
     */
    @ApiOperation(value = "摄像机设备信息", notes = "获取摄像机设备详细数据")
    @RequestMapping(value = "/issp/v1.0/getCameraEquipmentInfo", method = RequestMethod.GET)
    public JSONObject getCameraEquipmentInfo(
            @ApiParam(value = "设备标识ID", required = false) @RequestParam String deviceId,
            @ApiParam(value = "同步标识", required = false) @RequestParam String version) {
        logger.info("获取摄像机设备详细数据...");
        // 获取摄像机设备详情信息
        return cameraEquipmentService.getCameraEquipmentInfo(deviceId, version);
    }

    /**
     * 查询摄像机设备状态信息
     *
     * @param deviceId
     * @return
     * @author liuhoujun
     */

    @ApiOperation(value = "摄像机状态信息", notes = "根据设备标识查询设备的状态信息")
    @RequestMapping(value = "/issp/v1.0/getCamereStatusInfo", method = RequestMethod.GET)
    public JSONObject getCameraStatusInfo(
            @ApiParam(value = "设备标识ID", required = true) @RequestParam String deviceId) {
        logger.info("获取摄像机状态信息...");
        // 获取摄像机设备详情信息
        return cameraEquipmentService.getCameraStatusInfo(deviceId);
    }

    /**
     * 获取摄像机hls视频流信息
     */
    @ApiOperation(value = "摄像机hls视频流信息", notes = "获取摄像机hls视频流数据")
    @RequestMapping(value = "/issp/v1.0/getHlsVideoStreamInfo", method = RequestMethod.GET)
    public JSONObject getHlsVideoStreamInfo(
            @ApiParam(value = "设备标识ID", required = true) @RequestParam String deviceId) {
        logger.info("获取摄像机hls视频流数据...");
        // 获取摄像机视频流信息
        return cameraEquipmentService.getHlsVideoStreamInfo(deviceId);
    }

    /**
     * 获取摄像机rtsp视频流信息
     */
    @ApiOperation(value = "摄像机rtsp视频流信息1", notes = "获取摄像机rtsp视频流数据格式1")
    @RequestMapping(value = "/issp/v1.0/getRtspVideoStreamInfo1", method = RequestMethod.GET)
    public JSONObject getRtspVideoStreamInfo1(
            @ApiParam(value = "设备标识ID", required = true) @RequestParam String deviceId) {
        logger.info("获取摄像机rtsp视频流数据...");
        // 获取摄像机视频流信息
        return cameraEquipmentService.getRtspVideoStreamInfo(deviceId);
    }

    /**
     * 获取摄像机rtsp视频流信息
     */
    @ApiOperation(value = "摄像机rtsp视频流信息2", notes = "获取摄像机rtsp视频流数据格式2")
    @RequestMapping(value = "/issp/v1.0/getRtspVideoStreamInfo2", method = RequestMethod.GET)
    public JSONObject getRtspVideoStreamInfo2(
            @ApiParam(value = "设备标识ID", required = true) @RequestParam String deviceId) {
        logger.info("获取摄像机rtsp视频流数据...");
        JSONObject videoStreamInfo = null;
        Map<String, Object> videoMethodByDevId = cameraEquipmentService.getVideoMethodByDevId(deviceId);
        if ("1".equals(videoMethodByDevId.get("F_METHOD").toString())) {
            videoStreamInfo = cameraEquipmentService.getRtspVideoStreamInfo2(deviceId);
        } else {
        }
        return videoStreamInfo;
    }

    /**
     * 获取摄像机rtmp视频流信息
     */
    @ApiOperation(value = "摄像机rtmp视频流信息", notes = "获取摄像机rtmp视频流数据")
    @RequestMapping(value = "/issp/v1.0/getRtmpVideoStreamInfo", method = RequestMethod.GET)
    public JSONObject getRtmpVideoStreamInfo(
            @ApiParam(value = "设备标识ID", required = true) @RequestParam String deviceId) {
        logger.info("获取摄像机rtmp视频流数据...");
        // 获取摄像机视频流信息
        JSONObject rtmpVideoStreamInfo = cameraEquipmentService.getRtmpVideoStreamInfo(deviceId);
        return rtmpVideoStreamInfo;
    }

    /**
     * 获取摄像机rtmp视频流信息
     */
    @ApiOperation(value = "摄像机视频流信息", notes = "获取摄像机视频流数据")
    @RequestMapping(value = "/issp/v1.0/getVideoStreamInfo", method = RequestMethod.GET)
    public JSONObject getVideoStreamInfo(
            @ApiParam(value = "设备标识ID", required = true) @RequestParam String deviceId) {
        logger.info("获取摄像机视频流数据...");
        JSONObject videoStreamInfo;
        // 获取摄像机视频流信息
        Map<String, Object> videoMethodByDevId = cameraEquipmentService.getVideoMethodByDevId(deviceId);
        if ("0".equals(videoMethodByDevId.get("F_METHOD").toString())) {
            videoStreamInfo = cameraEquipmentService.getRtmpVideoStreamInfo(deviceId);
        } else {
            videoStreamInfo = getRTMPStreamInfo(deviceId);
        }
        return videoStreamInfo;
    }


    //	@ApiOperation(value = "摄像机hls视频流信息", notes = "摄像机hls视频流信息")
//	@RequestMapping(value = "/issp/v1.0/getHlsVideoStreamInfo", method = RequestMethod.POST)
//	public JSONObject getHLSStreamInfo(
//			@ApiParam(value = "设备标识ID", required = true) @RequestParam String deviceId) {
//		JSONObject hlsObject = new JSONObject();
//		String streamPath = CacheMap.get(deviceId);
//		if(streamPath != null){
//			hlsObject.put("status", "success");
//			hlsObject.put("hlsUrl", "http://172.16.13.209:9090/hls/" + streamPath + "/index.m3u8");
//			return hlsObject;
//		}
//		JSONObject streamInfo = cameraEquipmentService.getRtspVideoStreamInfo(deviceId);
//		String rtspUrl = (String) streamInfo.get("rtspUrl");
//		long path = System.currentTimeMillis();
//		CacheMap.put(deviceId, path + "");
//		new Thread(new Runnable() {
//            @SuppressWarnings("rawtypes")
//			@Override
//            public void run() {
//				Map map = ISSPFFMpegUtil.convetorStream(rtspUrl, "rtmp://172.16.13.209:1935/hls/" + path);
//				int waitFor = (int) map.get("WaitFor");
//				Process videoProcess = (Process) map.get("Process");
//				if(waitFor == 1){
//					CacheMap.remove(deviceId);
//					videoProcess.destroy();
//				}
//            }
//        }).start();
//		hlsObject.put("status", "success");
//		hlsObject.put("hlsUrl", "http://172.16.13.209:9090/hls/" + path + "/index.m3u8");
//		return hlsObject;
//	}
//
//	private static Map<String, String> RTSPCacheMap = new HashMap<String, String>();
//    @ApiOperation(value = "摄像机转推rtmp视频流信息", notes = "摄像机rtmp视频流信息")
//    @RequestMapping(value = "/issp/v1.0/getFfmpegRtmpVideoStreamInfo", method = RequestMethod.POST)
    public JSONObject getRTMPStreamInfo(String deviceId) {
        JSONObject rtspObject = new JSONObject();


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("deviceId", deviceId);
        JSONObject streamInfo = cameraEquipmentService.getRtspVideoStreamInfo2(deviceId);
        String alarmCode = (String) streamInfo.get("alarmCode");
        String videoChannel = (String) streamInfo.get("videoChannel");
        jsonObject.put("alarmCode", alarmCode);
        jsonObject.put("videoChannel", videoChannel);
        String rw1 = ISSPURLConnection.processRequest("http://10.200.10.63:8080/transcode/plugFlow", "post", jsonObject);
        if (rw1 != null) {
            rtspObject.put("status", "success");
            rtspObject.put("rtmpUrl", "rtmp://124.128.225.18:1935/hls/t" + rw1);
        }
        return rtspObject;
    }

    /**
     * 获取摄像机基础信息
     */
    @ApiOperation(value = "摄像机基础信息", notes = "获取所有摄像机设备基础信息")
    @RequestMapping(value = "/issp/v1.0/getCameraBaseInfo", method = RequestMethod.GET)
    public JSONObject getCameraBaseInfo(
            @ApiParam(value = "设备位置信息:0 道路设备;1收费站设备", required = true) @RequestParam String positionInfo
    ) {
        logger.info("获取摄像基础信息...");
        return cameraEquipmentService.getCameraBaseInfo(positionInfo);
    }

    /**
     * 获取摄像机基础信息树
     */
    @ApiOperation(value = "摄像机基础信息树", notes = "获取所有摄像机设备道路树")
    @RequestMapping(value = "/issp/v1.0/getCameraBaseTree", method = RequestMethod.POST)
    public JSONObject getCameraBaseTree() {
        logger.info("获取摄像基础信息...");
        return cameraEquipmentService.getCameraBaseTree();
    }


}
