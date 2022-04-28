package com.efounder.JEnterprise.zhdg.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.efounder.JEnterprise.platform.util.InterfaceAccessUtil;
import com.efounder.JEnterprise.platform.util.SwitchControlObject;
import com.efounder.JEnterprise.platform.websocket.config.PduStatusData;
import com.efounder.JEnterprise.zhdg.config.LightPoleConfig;
import com.efounder.JEnterprise.zhdg.service.PointService;
import com.efounder.JEnterprise.zhdg.util.ThreadPool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * describe: PDU接口
 *
 * @author zs
 * @date 2021/11/19
 */
@Controller
@Api(value = "PduApiController",tags = "智慧灯杆接口")
@RequestMapping("/api/public/pole/pdu")
public class PduApiController {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(PduApiController.class);

    @Resource
    private LightPoleConfig lightPoleConfig;

    @Resource
    private PointService pointService;



    /**
     * PDU开关控制--同时控制所有PDU的同一对象，比如灯的开关
     * @param controlObject 控制对象
     * @param switchStatus 开关状态
     * @return
     */
    @ApiOperation(value="所有开关控制PDU", notes="所有开关控制PDU")
    @RequestMapping(value = "/allSwitchControl", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject allSwitchControl(
            @ApiParam(value = "控制对象", required = true) @RequestParam String controlObject,
            @ApiParam(value = "开关状态", required = true) @RequestParam String switchStatus){

        JSONObject jsonObject = new JSONObject();

        //pdu控制
        String path = lightPoleConfig.switchControlUrl;
        //获取全部的PDU_IP
        List<String> list = pointService.queryAllPduIp();
        list.forEach(ip ->{
            StringBuffer params = new StringBuffer();
            params.append("ip=").append(ip).append("&");
            params.append("controlObject=").append(controlObject).append("&");
            params.append("switchStatus=").append(switchStatus);

            String result = InterfaceAccessUtil.doGetWithParam(path,params.toString(),"","");
            logger.info("所有开关控制接口调用,PDU_IP="+ip+",返回信息：result="+result);
            jsonObject.put("result",result);
        });

        return jsonObject;

    }

    /**
     * 开关控制
     * @param ip IP地址
     * @param controlObject 控制对象，显示屏、音柱、摄像头等
     * @param switchStatus
     * @return
     */
    @ApiOperation(value="开关控制PDU", notes="开关控制PDU")
    @RequestMapping(value = "/switchControl", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject switchControl(
            @ApiParam(value = "IP地址", required = true) @RequestParam String ip,
//            @ApiParam(value = "端口号", required = true) @RequestParam String port,
            @ApiParam(value = "控制对象，显示屏、音柱、摄像头等", required = true) @RequestParam SwitchControlObject controlObject,
            @ApiParam(value="开关状态，开 1 关 0",required = true) @RequestParam String switchStatus) {

        //pdu控制
        String path = lightPoleConfig.switchControlUrl;
        StringBuffer params = new StringBuffer();
        params.append("ip=").append(ip).append("&");
        params.append("controlObject=").append(controlObject.getCode()).append("&");
        params.append("switchStatus=").append(switchStatus);

        String result = InterfaceAccessUtil.doGetWithParam(path,params.toString(),"","");
        logger.info("开关控制接口调用,result="+result);
//        JSONObject jsonObject = JSONObject.parseObject(result);
        logger.info("开关控制接口调用,ip="+ip);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",result);
        return jsonObject;
    }


    /**
     * 显示屏开关控制
     * @param ip IP地址
//     * @param port 端口号
     * @param switchStatus 开关控制状态
     * @return
     */
    @ApiOperation(value="显示屏开关控制PDU", notes="显示屏开关控制PDU")
    @RequestMapping(value = "/screenSwitchControl", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject screenSwitchControl(
            @ApiParam(value = "显示屏IP地址", required = true) @RequestParam String ip,
//            @ApiParam(value = "端口号", required = true) @RequestParam String port,
            @ApiParam(value="开关状态，开 1 关 0",required = true) @RequestParam String switchStatus) {
        //根据显示屏IP地址查询关联的PDU IP地址
        String pduIp = pointService.queryPduIpByScreenIp(ip);

        //pdu控制
        String path = lightPoleConfig.switchControlUrl;
        StringBuffer params = new StringBuffer();
        params.append("ip=").append(pduIp).append("&");
//        params.append("port=").append(port).append("&");
        //传递参数：显示屏的code
        params.append("controlObject=").append(SwitchControlObject.screen.getCode()).append("&");
        params.append("switchStatus=").append(switchStatus);

        String result = InterfaceAccessUtil.doGetWithParam(path,params.toString(),"","");
//        JSONObject jsonObject = JSONObject.parseObject(result);
        logger.info("显示屏开关控制接口调用,ip="+pduIp);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",result);
        return jsonObject;
    }


    /**
     * 获取开关状态
     * @param ip IP地址
//     * @param port 端口号
     * @param controlObject 控制对象，显示屏、音柱、摄像头等
     * @return
     */
    @ApiOperation(value="获取开关状态PDU", notes="获取开关状态PDU")
    @RequestMapping(value = "/getSwitchStatus", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getSwitchStatus(
            @ApiParam(value = "IP地址", required = true) @RequestParam String ip,
//            @ApiParam(value = "端口号", required = true) @RequestParam String port,
            @ApiParam(value = "控制对象，显示屏、音柱、摄像头等", required = true) @RequestParam SwitchControlObject controlObject) {

        //pdu控制，获取状态
        String path = lightPoleConfig.switchStatusUrl;
        StringBuffer params = new StringBuffer();
        params.append("ip=").append(ip).append("&");
        params.append("controlObject=").append(controlObject.getCode());

        String result = InterfaceAccessUtil.doGetWithParam(path,params.toString(),"","");
//        JSONObject jsonObject = JSONObject.parseObject(result);
        logger.info("获取开关状态接口调用,ip="+ip);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",result);
        return jsonObject;
    }

    /**
     * 获取显示屏开关状态PDU
     * @param ip IP地址
//     * @param port 端口号
     * @return
     */
    @ApiOperation(value="获取显示屏开关状态PDU", notes="获取显示屏开关状态PDU")
    @RequestMapping(value = "/getScreenSwitchStatus", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getScreenSwitchStatus(
            @ApiParam(value = "显示屏IP地址", required = true) @RequestParam String ip
//            ,@ApiParam(value = "端口号", required = true) @RequestParam String port
    ) {
        //根据显示屏IP地址查询关联的PDU IP地址
        String pduIp = pointService.queryPduIpByScreenIp(ip);

        //pdu控制，获取状态
        String path = lightPoleConfig.switchStatusUrl;
        StringBuffer params = new StringBuffer();
        params.append("ip=").append(pduIp).append("&");
//        params.append("port=").append(port).append("&");
        //传递参数：显示屏的code
        params.append("controlObject=").append(SwitchControlObject.screen.getCode());

        String result = InterfaceAccessUtil.doGetWithParam(path,params.toString(),"","");
//        JSONObject jsonObject = JSONObject.parseObject(result);
        logger.info("获取显示屏开关状态接口调用,ip="+pduIp);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",result);
        return jsonObject;
    }

    /**
     * 接收开关状态数据
     * @return
     */
    @RequestMapping(value = "/receiveSwitchStatus", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value="显示屏的开关状态推送", notes="显示屏的开关状态推送")
    public JSONObject receiveSwitchStatus(@RequestBody JSONObject jsonObject){
        JSONObject resultJson = new JSONObject();
        logger.info("平台接收到的PDU开关状态：" + jsonObject.toJSONString());

            JSONArray realData = jsonObject.getJSONArray("data");
            String ip = jsonObject.getString("ip");
            int count = realData.size();
            Map map = new HashMap();
            Map screenMap = new HashMap();
            for(int i = count - 1; i >= 0; i--){
                Object obj = realData.get(i);
                String str = String.valueOf(obj);
                Integer num = Integer.parseInt(str);
                //解析顺序与控制顺序相反，第一位控制第8路，第8位控制第一路
                String code = String.valueOf(8 - i);
                String name = SwitchControlObject.getName(code);
                //开关： 1-开，0-关
                map.put(name,num);

                //显示屏开关状态
                if(SwitchControlObject.screen.getCode().equals(code)){
                    screenMap.put("switchStatus",num);
                }
            }
            resultJson.put("result",true);
            resultJson.put("data",map);
            resultJson.put("ip",ip);

            //更新PDU开关状态缓存
        PduStatusData.switchStatusMap.put(ip,map);

            //向其他系统推送开关状态--向信息发布推送显示屏的开关状态
            //筛选显示屏数据
            //根据PDU IP地址查询关联的显示屏IP地址
            String screenIp = pointService.queryScreenIpByPduIp(ip);
            screenMap.put("ip",screenIp);
            screenMap.put("result",true);
            logger.info("向信息发布推送显示屏的开关状态数据：" + screenMap.toString());
            ThreadPool.executor.execute(()->{
                String json = JSON.toJSONString(screenMap, SerializerFeature.WriteMapNullValue);
                StringEntity stringEntity = new StringEntity(json,"UTF-8");
                stringEntity.setContentType("application/json");
                String result = InterfaceAccessUtil.doHttpEntity(lightPoleConfig.pushScreenSwitchStatusUrl,stringEntity);
                logger.info("向其他平台推送PDU开关状态数据返回结果：" + result);
            });

            return resultJson;

    }
}
