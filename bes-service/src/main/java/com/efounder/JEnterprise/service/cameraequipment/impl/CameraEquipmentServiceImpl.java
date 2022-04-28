package com.efounder.JEnterprise.service.cameraequipment.impl;

import com.alibaba.fastjson.JSONObject;
import com.core.common.tree.ISSPTreeBuilder;
import com.core.common.tree.ISSPTreeNode;
import com.core.common.util.WebservicePluginUtil;
import com.efounder.JEnterprise.adapterProcess.camera.VideoProcess;
import com.efounder.JEnterprise.controller.interfaceUtil.ISSPUtil;
import com.efounder.JEnterprise.mapper.cameraequipment.CameraEquipmentMapper;
import com.efounder.JEnterprise.model.BESCameraBase;
import com.efounder.JEnterprise.service.cameraequipment.CameraEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 摄像机设备服务接口实现类
 *
 * @author huangxianbo
 * @datetime 2018年11月30日
 */

@Service("cameraEquipmentService")
public class CameraEquipmentServiceImpl implements CameraEquipmentService {

    @Autowired
    private CameraEquipmentMapper cameraEquipmentMapper;

    @Override
    public JSONObject getVideoInfoByDevId(String devId) {
        JSONObject dataJObject = new JSONObject();
        net.sf.json.JSONObject loginInfo = null;
        try {
            loginInfo = VideoProcess.userLogin();
        } catch (RemoteException e) {
            //网络不通或者用户名密码错误导致登录异常
            dataJObject.put("status", "fail");
            dataJObject.put("msg", "接口内部网络异常，请联系管理员！");
            dataJObject.put("xmlParam", null);
            return dataJObject;
        }
        String sessionId = loginInfo.getString("sessionId");
        net.sf.json.JSONObject dataInfo = (net.sf.json.JSONObject) loginInfo.get("dataInfo");
        int netZoneId = dataInfo.getInt("netZoneId"); // 网域ID
        // 获取监控点预览所需参数
        Object xmlInfo = VideoProcess.getPreviewParamXML(sessionId, devId, netZoneId);
        net.sf.json.JSONObject obj = WebservicePluginUtil.parseXmlToJSONObj(xmlInfo.toString());
        net.sf.json.JSONObject resultInfo = (net.sf.json.JSONObject) obj.get("resultInfo");
        if (resultInfo != null && resultInfo.getBoolean("result") == false) {
            dataJObject.put("status", "success");
            dataJObject.put("xmlParam", null);
            dataJObject.put("msg", resultInfo.getString("errorReason"));
            return dataJObject;
        }
        dataJObject.put("status", "success");
        dataJObject.put("msg", "获取成功！");
        dataJObject.put("xmlParam", xmlInfo);
        //dataJObject.put("sessionId", sessionId);
        return dataJObject;
    }

    @Override
    public JSONObject getCameraEquipmentInfo(String devId, String ver) {
        JSONObject dataJObject = new JSONObject();
        try {
            List<Map<String, Object>> cameraEquipmentInfo = cameraEquipmentMapper.getCameraEquipmentInfo(devId, ver);
            if (cameraEquipmentInfo.size() == 0) { // 数据检索为空
                return ISSPUtil.resultNoData(ver);
            }
            Map<String, Object> verObj = cameraEquipmentInfo.get(cameraEquipmentInfo.size() - 1);
            dataJObject.put("version", verObj.get("F_ID"));
            dataJObject.put("status", "success");
            JSONObject baseJObject = new JSONObject();
            baseJObject.put("list", cameraEquipmentInfo);
            dataJObject.put("data", baseJObject);
        } catch (Exception e) {
            return ISSPUtil.resultException();
        }
        return dataJObject;
    }

    /**
     * @author liuhoujun
     * 描述：根据f_sbb查询摄像机的f_status并返回查询结果
     */

    @Override
    public JSONObject getCameraStatusInfo(String deviceId) {
        JSONObject dataJObject = new JSONObject();
        try {
            Map<String, Object> cameraStatusInfos = cameraEquipmentMapper.getCameraStatusInfo(deviceId);
            if (cameraStatusInfos == null) {
                dataJObject.put("status", "success");
                dataJObject.put("msg", "0");//该设备信息不存在
                return dataJObject;
            }
            dataJObject.put("status", "success");
            dataJObject.put("onlineStauts", cameraStatusInfos.get("F_STATUS") == null ? "2" : cameraStatusInfos.get("F_STATUS"));
            dataJObject.put("msg", "1");//正常返回
        } catch (Exception e) {
            dataJObject.put("status", "fail");
            dataJObject.put("msg", "2");//系统异常
            return dataJObject;
        }
        return dataJObject;
    }


    @Override
    public JSONObject getHlsVideoStreamInfo(String devId) {
        JSONObject dataJObject = new JSONObject();
        net.sf.json.JSONObject loginInfo = null;
        try {
            loginInfo = VideoProcess.userLogin();
        } catch (RemoteException e) {
            dataJObject.put("status", "fail");
            dataJObject.put("hlsUrl", null);
            dataJObject.put("msg", "网络连接异常");
            return dataJObject;
        }
        String sessionId = loginInfo.getString("sessionId");
        net.sf.json.JSONObject dataInfo = (net.sf.json.JSONObject) loginInfo.get("dataInfo");
        int netZoneId = dataInfo.getInt("netZoneId"); // 网域ID
        Object xmlInfo = VideoProcess.getPreviewParamXML(sessionId, devId, netZoneId);
        net.sf.json.JSONObject obj = WebservicePluginUtil.parseXmlToJSONObj(xmlInfo.toString());
        net.sf.json.JSONObject resultInfo = (net.sf.json.JSONObject) obj.get("resultInfo");
        if (resultInfo != null && resultInfo.getBoolean("result") == false) {
            dataJObject.put("status", "success");
            dataJObject.put("hlsUrl", null);
            dataJObject.put("msg", resultInfo.getString("errorReason"));
            return dataJObject;
        }
        net.sf.json.JSONObject paramInfo = WebservicePluginUtil.parseXmlToJSONObj(xmlInfo.toString());
        net.sf.json.JSONObject paramentInfo = (net.sf.json.JSONObject) paramInfo.get("Parament");
//        String vtduip = paramentInfo.getString("StreamServerIP");// 流媒体的IP
        String vtduip = "60.208.23.59";// 流媒体的IP
        if (vtduip == null) {// 非级联监控点预览
            dataJObject.put("status", "success");
            dataJObject.put("hlsUrl", null);
            dataJObject.put("msg", "该节点为级联节点，无法返回视频流");
            return dataJObject;
        }
//		String vtduPort = paramentInfo.getString("StreamServerPort");// hls流媒体的端口
//		vtduPort = vtduPort == null ? "83" : vtduPort;// (端口号默认83)
        String vtduPort = "83";// hls流媒体的端口  (海康方面说使用端口号默认83)
        String vagIp = paramentInfo.getString("VagIp");// vag IP
        String vagPort = paramentInfo.getString("VagPort");// vag 端口
        String cameraIndexCode = paramentInfo.getString("CameraIndexCode");// 监控点编号
        String streamType = paramentInfo.getString("StreamType");// 码流类型
        if ("0".equals(streamType)) {// 主码流
            streamType = "MAIN";
        } else if ("1".equals(streamType)) {// 子码流
            streamType = "SUB";
        } else {// 第三码流
            streamType = "SUB2";
        }
        String protocolType = "TCP";// 协议类型  (海康方面说默认使用TCP)
//		String protocolType = paramentInfo.getString("ProtocolType");// 协议类型
//		if ("1".equals(protocolType)) {
//			protocolType = "UDP";
//		} else {
//			protocolType = "TCP";
//		}
        String url = "http://" + vtduip + ":" + vtduPort + "/pag/" + vagIp + "/" + vagPort + "/" + cameraIndexCode + "/0/" + streamType + "/" + protocolType + "/live.m3u8";
        dataJObject.put("status", "success");
        dataJObject.put("hlsUrl", url);
        return dataJObject;
    }

    @Override
    public Map<String, Object> getVideoMethodByDevId(String devId) {
        return cameraEquipmentMapper.getVideoMethodByDevId(devId);
    }

    @Override
    public JSONObject getRtspVideoStreamInfo(String devId) {
        JSONObject dataJObject = new JSONObject();
        net.sf.json.JSONObject loginInfo = null;
        try {
            loginInfo = VideoProcess.userLogin();
        } catch (RemoteException e) {
            dataJObject.put("status", "fail");
            dataJObject.put("rtspUrl", null);
            dataJObject.put("msg", "网络连接异常");
            return dataJObject;
        }
        String sessionId = loginInfo.getString("sessionId");
        net.sf.json.JSONObject dataInfo = (net.sf.json.JSONObject) loginInfo.get("dataInfo");
        int netZoneId = dataInfo.getInt("netZoneId"); // 网域ID
        Object xmlInfo = VideoProcess.getPreviewParamXML(sessionId, devId, netZoneId);
        net.sf.json.JSONObject obj = WebservicePluginUtil.parseXmlToJSONObj(xmlInfo.toString());
        net.sf.json.JSONObject resultInfo = (net.sf.json.JSONObject) obj.get("resultInfo");
        if (resultInfo != null && resultInfo.getBoolean("result") == false) {
            dataJObject.put("status", "success");
            dataJObject.put("rtspUrl", null);
            dataJObject.put("msg", resultInfo.getString("errorReason"));
            return dataJObject;
        }
        net.sf.json.JSONObject paramInfo = WebservicePluginUtil.parseXmlToJSONObj(xmlInfo.toString());
        net.sf.json.JSONObject paramentInfo = (net.sf.json.JSONObject) paramInfo.get("Parament");
//        String vtduip = paramentInfo.getString("StreamServerIP");// 流媒体的IP
        String vtduip = "60.208.23.59";// 流媒体的IP
        if (vtduip == null) {// 非级联监控点预览
            dataJObject.put("status", "success");
            dataJObject.put("rtspUrl", null);
            dataJObject.put("msg", "该节点为级联节点，无法返回视频流");
            return dataJObject;
        }
//		String vtduPort = paramentInfo.getString("StreamServerPort");// hls流媒体的端口
//		vtduPort = vtduPort == null ? "554" : vtduPort;// (端口号默认554)
        String vtduPort = "554";// hls流媒体的端口  (海康方面说使用端口号默认554)
        String vagIp = paramentInfo.getString("VagIp");// vag IP
        if ("60.208.23.59".equals(vagIp)) {
            vagIp = "10.50.13.248";// vag IP
        }
        String vagPort = "7302";
//		String vagPort = paramentInfo.getString("VagPort");// vag 端口
//		vagPort = vagPort == null ? "7302" : vtduPort;// (vag 端口号默认7302)
        String cameraIndexCode = paramentInfo.getString("CameraIndexCode");// 监控点编号
        String substream = paramentInfo.getString("StreamType");// 码流类型
        if ("0".equals(substream)) {// 主码流
            substream = "MAIN";
        } else if ("1".equals(substream)) {// 子码流
            substream = "SUB";
        } else {// 第三码流
            substream = "SUB2";
        }
        String transform = "TCP";// 协议类型  (海康方面说默认使用TCP)
//		String transform = paramentInfo.getString("ProtocolType");// 协议类型
//		if ("1".equals(transform)) {
//			transform = "UDP";
//		} else {
//			transform = "TCP";
//		}
        String url = "rtsp://" + vtduip + ":" + vtduPort + "/pag://" + vagIp + ":" + vagPort + ":" + cameraIndexCode + ":0:" + substream + ":" + transform + "?streamform=rtp";
        dataJObject.put("status", "success");
        dataJObject.put("rtspUrl", url);
        return dataJObject;
    }

    @Override
    public JSONObject getRtspVideoStreamInfo2(String devId) {

        Map<String, Object> videoMethodByDevId = getVideoMethodByDevId(devId);

        JSONObject dataJObject = new JSONObject();



        String vagIp ="10.50.13.231";//大华平台默认IP
        String vagPort ="9090"; // (大华平台 端口号默认9090)
        String alarmCode = videoMethodByDevId.get("F_ALARM_CODE").toString();
        Integer f_video_channel = Integer.valueOf(videoMethodByDevId.get("F_VIDEO_CHANNEL").toString());
        //大华平台默认从0开始
        int channel = f_video_channel - 1;
        String videoChannel = String.valueOf(channel);

        //大华平台对应rtsp 流
        //rtsp://10.50.13.231:9090/dss/monitor/param?cameraid=设备id%24通道号(从0开始)&substream=1（主码流）
        String url = "rtsp://" + vagIp + ":" + vagPort + "/dss/monitor/param?cameraid=" + alarmCode + "%24" + videoChannel + "&substream=1";
        dataJObject.put("status", "success");
        dataJObject.put("rtspUrl", url);
        dataJObject.put("alarmCode", alarmCode);
        dataJObject.put("videoChannel", videoChannel);
        return dataJObject;
    }


    @Override
    public JSONObject getRtmpVideoStreamInfo(String devId) {
        JSONObject dataJObject = new JSONObject();
        net.sf.json.JSONObject loginInfo = null;
        try {
            loginInfo = VideoProcess.userLogin();
        } catch (RemoteException e) {
            dataJObject.put("status", "fail");
            dataJObject.put("rtmpUrl", null);
            dataJObject.put("msg", "网络连接异常");
            return dataJObject;
        }
        String sessionId = loginInfo.getString("sessionId");
        net.sf.json.JSONObject dataInfo = (net.sf.json.JSONObject) loginInfo.get("dataInfo");
        int netZoneId = dataInfo.getInt("netZoneId"); // 网域ID
        try {
            Object xmlInfo = VideoProcess.getPreviewParamXML(sessionId, devId, netZoneId);
            net.sf.json.JSONObject obj = WebservicePluginUtil.parseXmlToJSONObj(xmlInfo.toString());
            net.sf.json.JSONObject resultInfo = (net.sf.json.JSONObject) obj.get("resultInfo");
            if (resultInfo != null && resultInfo.getBoolean("result") == false) {
                dataJObject.put("status", "success");
                dataJObject.put("rtmpUrl", null);
                dataJObject.put("msg", resultInfo.getString("errorReason"));
                return dataJObject;
            }
            net.sf.json.JSONObject paramInfo = WebservicePluginUtil.parseXmlToJSONObj(xmlInfo.toString());
            net.sf.json.JSONObject paramentInfo = (net.sf.json.JSONObject) paramInfo.get("Parament");
//            String vtduip = paramentInfo.getString("StreamServerIP");// 流媒体的IP
            String vtduip = "60.208.23.59";// 流媒体的IP
            if (vtduip == null) {// 非级联监控点预览
                dataJObject.put("status", "success");
                dataJObject.put("rtspUrl", null);
                dataJObject.put("msg", "该节点为级联节点，无法返回视频流");
                return dataJObject;
            }
            String vtduPort = "1935";// rtmp流媒体的端口 (海康方面说使用端口号默认1935)
            String vagIp = paramentInfo.getString("VagIp");// vag IP
            if ("60.208.23.59".equals(vagIp)) {
                vagIp = "10.50.13.248";// vag IP
            }
            String vagPort = "7302";
            String cameraIndexCode = paramentInfo.getString("CameraIndexCode");// 监控点编号
            String substream = paramentInfo.getString("StreamType");// 码流类型
            if ("0".equals(substream)) {// 主码流
                substream = "MAIN";
            } else if ("1".equals(substream)) {// 子码流
                substream = "SUB";
            } else {// 第三码流
                substream = "SUB2";
            }
            String transform = "TCP";// 协议类型 (海康方面说默认使用TCP)
            String url = "rtmp://" + vtduip + ":" + vtduPort + "/live/pag/" + vagIp + "/" + vagPort + "/"
                    + cameraIndexCode + "/0/" + substream + "/" + transform;
            dataJObject.put("status", "success");
            dataJObject.put("rtmpUrl", url);
        } catch (Exception e) {
            e.printStackTrace();
            dataJObject.put("status", "fail");
            dataJObject.put("rtmpUrl", null);
            dataJObject.put("msg", "接口解析异常");
            return dataJObject;
        }

        // rtmp://<vtduip>:<vtduport>/live/pag/<vagip>/<vagport>/<indexcod/0/<substream>/<transform>
        return dataJObject;
    }

    @Override
    public JSONObject getCameraBaseInfo(String localInfo) {
        JSONObject dataJObject = new JSONObject();
        try {
            List<Map<String, Object>> cameraEquipmentInfo = cameraEquipmentMapper.getCameraBaseInfo(localInfo);
            if (cameraEquipmentInfo.size() == 0) { // 数据检索为空
                dataJObject.put("status", "success");
                dataJObject.put("data", null);
                return dataJObject;
            }
            Map<String, Object> verObj = cameraEquipmentInfo.get(cameraEquipmentInfo.size() - 1);
            dataJObject.put("status", "success");
            JSONObject baseJObject = new JSONObject();
            baseJObject.put("list", cameraEquipmentInfo);
            dataJObject.put("data", baseJObject);
        } catch (Exception e) {
            return ISSPUtil.resultException();
        }
        return dataJObject;
    }

    @Override
    public JSONObject getCameraBaseTree() {
        // TODO Auto-generated method stub
        JSONObject dataJObject = new JSONObject();
        try {
            // 组织结构业务逻辑编写-- 线路-->路段-->分公司-->摄像机
            // 1.0 查询所有线路
            List<BESCameraBase> bESRoute = new ArrayList<>();
                /*道路监控(云台摄像机,固定摄像机,高清一体化摄像机,摄像机,监控室摄像机)
                (00000000014,00000000016,00000000204,00000000206,00000000212,00000000100,00000000178)
	            广场(广场摄像机)(00000000061)
	            收费亭(收费亭内摄像机)(00000000060)
	            车道(ETC车道摄像机)(00000000185)*/
            // 获得一棵树模型的数据
            List<ISSPTreeNode> tree = getTreeJson();
            dataJObject.put("status", "success");
            dataJObject.put("data", tree);
        } catch (Exception e) {
            return ISSPUtil.resultException();
        }
        return dataJObject;
    }

    private List<ISSPTreeNode> getTreeJson() {
        // 写死树级头
        List<Map<String, String>> list = getList();
        try {
            // 道路监控
            getdljkList(list);
            // 广场
            getGcList(list);
            // 收费亭
            getSftList(list);
            // 车道
            getCdList(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 把所有资源转换成树模型的节点集合，此容器用于保存所有节点
        List<ISSPTreeNode> nodes = new ArrayList<ISSPTreeNode>();
        for (Map<String, String> map : list) {
            ISSPTreeNode node = new ISSPTreeNode();
            node.setId(map.get("CId"));
            node.setPid(map.get("PId"));
            node.setText(map.get("CMc"));
            node.setNodeTreeId(map.get("CJs"));
            nodes.add(node);
        }
        List<ISSPTreeNode> buildTree = ISSPTreeBuilder.buildTree(nodes);
        return buildTree;
    }

    // 写死树头
    public List<Map<String, String>> getList() {
        List<Map<String, String>> list = new ArrayList<>();
        // 道路监控传输数组
        List<String> dljk = new ArrayList<>();
        dljk.add("00000000014");
        dljk.add("00000000016");
        dljk.add("00000000204");
        dljk.add("00000000206");
        dljk.add("00000000212");
        dljk.add("00000000100");
        dljk.add("00000000178");
        // 收费站数组
        List<String> sfz = new ArrayList<>();
        sfz.add("00000000061");
        sfz.add("00000000060");
        sfz.add("00000000185");
        // 广场传输数组
        List<String> gc = new ArrayList<>();
        gc.add("00000000061");
        // 收费亭传输数组
        List<String> sft = new ArrayList<>();
        sft.add("00000000060");
        // 车道传输数组
        List<String> cd = new ArrayList<>();
        cd.add("00000000185");
        Map<String, String> map = new HashMap<>();
        map.put("PId", "");
        map.put("CId", "00");
        map.put("CMc", "齐鲁交通");
        map.put("CJs", "1");
        list.add(map);
        Map<String, String> map1 = new HashMap<>();
        map1.put("PId", "00");
        map1.put("CId", "0010");
        map1.put("CMc", "道路监控" + getSxjSl(dljk));
        map1.put("CJs", "2");
        list.add(map1);
        Map<String, String> map2 = new HashMap<>();
        map2.put("PId", "00");
        map2.put("CId", "0011");
        map2.put("CMc", "收费站");
        map2.put("CJs", "2");
        list.add(map2);
        Map<String, String> map3 = new HashMap<>();
        map3.put("PId", "0011");
        map3.put("CId", "001111");
        map3.put("CMc", "广场");
        map3.put("CJs", "3");
        list.add(map3);
        Map<String, String> map4 = new HashMap<>();
        map4.put("PId", "0011");
        map4.put("CId", "001112");
        map4.put("CMc", "收费亭");
        map4.put("CJs", "3");
        list.add(map4);
        Map<String, String> map5 = new HashMap<>();
        map5.put("PId", "0011");
        map5.put("CId", "001113");
        map5.put("CMc", "车道");
        map5.put("CJs", "3");
        list.add(map5);
        return list;
    }


    // 道路监控
    public List<Map<String, String>> getdljkList(List<Map<String, String>> list) {
        // 道路监控传输数组
        List<String> dljk = new ArrayList<>();
        dljk.add("00000000014");
        dljk.add("00000000016");
        dljk.add("00000000204");
        dljk.add("00000000206");
        dljk.add("00000000212");
        dljk.add("00000000100");
        dljk.add("00000000178");
        // 1.0 查询道路监控下所有的摄像头设备
        List<String> sxtsbList = cameraEquipmentMapper.getsxtsb_list(dljk);
        // 1.1 根据摄像头查询所有的路段表cameraEquipmentMapper中的线路id
        List<String> xlid_list = cameraEquipmentMapper.GetxlidList(sxtsbList);
        // 1.2 根据所有线路id查询所有道路监控的线路
        List<Map<String, String>> xlList = cameraEquipmentMapper.GetAllxlList(xlid_list, "3", "0010");
        // 分解List 重新组合
        xlList = comList(xlList);
        // 1.3 线路下边挂组织机构
        List<Map<String, String>> xlZzjgList = cameraEquipmentMapper.GetxlZzjgList(sxtsbList, "4", "0010", "0010");
        // 1.4 组织机构下边放具体摄像头
        List<Map<String, String>> zzjgsxt_list = cameraEquipmentMapper.GetzzjgsxtList(dljk, "5", "0010");
        //将道路监控数据保存到list中
        list.addAll(xlList);
        list.addAll(xlZzjgList);
        list.addAll(zzjgsxt_list);
        return list;
    }

    // 广场
    public List<Map<String, String>> getGcList(List<Map<String, String>> list) {
        // 广场传输数组
        List<String> gc = new ArrayList<>();
        gc.add("00000000061");
        // 1.0 查询道路监控下所有的摄像头设备
        List<String> sxtsbList = cameraEquipmentMapper.getsxtsb_list(gc);
        // 1.1 根据摄像头查询所有的路段表中的线路id
        List<String> xlid_list = cameraEquipmentMapper.GetxlidList(sxtsbList);
        // 1.2 根据所有线路id查询所有道路监控的线路
        List<Map<String, String>> xlList = cameraEquipmentMapper.GetAllxlList(xlid_list, "4", "001111");
        // 1.3 线路下边挂组织机构
        List<Map<String, String>> xlZzjgList = cameraEquipmentMapper.GetxlZzjgList(sxtsbList, "5", "001111", "001111");
        // 1.4 组织机构下边放具体摄像头
        List<Map<String, String>> zzjgsxt_list = cameraEquipmentMapper.GetzzjgsxtList(gc, "6", "001111");
        //将道路监控数据保存到list中
        list.addAll(xlList);
        list.addAll(xlZzjgList);
        list.addAll(zzjgsxt_list);
        return list;
    }

    // 收费亭
    public List<Map<String, String>> getSftList(List<Map<String, String>> list) throws Exception {
        // 收费亭传输数组
        List<String> gc = new ArrayList<>();
        gc.add("00000000060");
        // 1.0 查询道路监控下所有的摄像头设备
        List<String> sxtsbList = cameraEquipmentMapper.getsxtsb_list(gc);
        // 1.1 根据摄像头查询所有的路段表中的线路id
        List<String> xlid_list = cameraEquipmentMapper.GetxlidList(sxtsbList);
        // 1.2 根据所有线路id查询所有道路监控的线路
        List<Map<String, String>> xlList = cameraEquipmentMapper.GetAllxlList(xlid_list, "4", "001112");
        // 1.3 线路下边挂组织机构
        List<Map<String, String>> xlZzjgList = cameraEquipmentMapper.GetxlZzjgList(sxtsbList, "5", "001112", "001112");
        // 1.4 组织机构下边放具体摄像头
        List<Map<String, String>> zzjgsxt_list = cameraEquipmentMapper.GetzzjgsxtList(gc, "6", "001112");
        //将道路监控数据保存到list中
        list.addAll(xlList);
        list.addAll(xlZzjgList);
        list.addAll(zzjgsxt_list);
        return list;
    }

    // 车道
    public List<Map<String, String>> getCdList(List<Map<String, String>> list) throws Exception {
        // 车道传输数组
        List<String> gc = new ArrayList<>();
        gc.add("00000000185");
        // 1.0 查询道路监控下所有的摄像头设备
        List<String> sxtsbList = cameraEquipmentMapper.getsxtsb_list(gc);
        // 1.1 根据摄像头查询所有的路段表中的线路id
        List<String> xlid_list = cameraEquipmentMapper.GetxlidList(sxtsbList);
        // 1.2 根据所有线路id查询所有道路监控的线路
        List<Map<String, String>> xlList = cameraEquipmentMapper.GetAllxlList(xlid_list, "4", "001113");
        // 1.3 线路下边挂组织机构
        List<Map<String, String>> xlZzjgList = cameraEquipmentMapper.GetxlZzjgList(sxtsbList, "5", "001113", "001113");
        // 1.4 组织机构下边放具体摄像头
        List<Map<String, String>> zzjgsxt_list = cameraEquipmentMapper.GetzzjgsxtList(gc, "6", "001113");
        //将道路监控数据保存到list中
        list.addAll(xlList);
        list.addAll(xlZzjgList);
        list.addAll(zzjgsxt_list);
        return list;
    }

    // 摄像机数量
    public String getSxjSl(List<String> list) {
        String zc = cameraEquipmentMapper.GetSxtzcSum(list);
        String bj = cameraEquipmentMapper.GetSxtbjSum(list);
        String yc = cameraEquipmentMapper.GetSxtycSum(list);
        return "(" + zc + "/" + bj + "/" + yc + ")";
    }

    // 线路id查询摄像机数量
    public String getSxjXlSl(List<String> list) {
        String zc = cameraEquipmentMapper.GetSxtXlzcSum(list, "0");
        String bj = cameraEquipmentMapper.GetSxtXlzcSum(list, "1");
        String yc = cameraEquipmentMapper.GetSxtXlzcSum(list, "2");
        return "(" + zc + "/" + bj + "/" + yc + ")";
    }

    // 分解List添加()数量
    public List<Map<String, String>> comList(List<Map<String, String>> list) {
        List<Map<String, String>> retutnList = new ArrayList<>();
        for (Map<String, String> map : list) {
            // 查询
            List<String> list1 = new ArrayList<>();
            list1.add(map.get("F_XLBH"));
            Map<String, String> map1 = new HashMap<>();
            map1.put("PId", map.get("PId"));
            map1.put("CId", map.get("CId"));
            map1.put("CMc", map.get("CMc") + getSxjXlSl(list1));
            map1.put("CJs", map.get("CJs"));
            retutnList.add(map1);
        }
        return retutnList;
    }
}
