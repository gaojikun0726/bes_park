package com.efounder.JEnterprise.timer;

import com.alibaba.fastjson.JSONObject;
import com.core.ApplicationContextProvider;
import com.efounder.JEnterprise.platform.util.InterfaceAccessUtil;
import com.efounder.JEnterprise.zhdg.config.LightPoleConfig;
import com.efounder.JEnterprise.zhdg.service.PointService;
import com.efounder.util.spring.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * describe: 用于定时任务调用的方法
 *
 * @author zs
 * @date 2021/12/13
 */
public class PduTimer {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(PduTimer.class);

    private LightPoleConfig lightPoleConfig = ApplicationContextProvider.getBean(LightPoleConfig.class);

    private PointService pointService = ApplicationContextProvider.getBean(PointService.class);
//    private PointService pointService = SpringUtils.getBean(PointService.class);


    /**
     * PDU开关控制--同时控制所有PDU的同一对象，比如灯的开关
     * @param controlObject 控制对象
     * @param switchStatus 开关状态
     * @return
     */
    public void allSwitchControl(String controlObject, String switchStatus){
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
            logger.info("定时任务：开关控制接口调用,PDU_IP="+ip);
        });

    }


    /**
     * PDU开关控制--控制单个PDU的控制设备
     * @param ip IP地址
     * @param controlObject 控制对象，显示屏、音柱、摄像头等
     * @param switchStatus
     * @return
     */
    public JSONObject switchControl(String ip, String controlObject, String switchStatus) {
        //根据显示屏IP地址查询关联的PDU IP地址
        String pduIp = pointService.queryPduIpByScreenIp(ip);

        JSONObject jsonObject = new JSONObject();

        switch (controlObject){
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
                //pdu控制
                String path = lightPoleConfig.switchControlUrl;
                StringBuffer params = new StringBuffer();
                params.append("ip=").append(pduIp).append("&");
                params.append("controlObject=").append(controlObject).append("&");
                params.append("switchStatus=").append(switchStatus);

                String result = InterfaceAccessUtil.doGetWithParam(path,params.toString(),"","");
                jsonObject = JSONObject.parseObject(result);
                logger.info("定时任务：开关控制接口调用");
                break;
            default:
                break;
        }
        return jsonObject;
    }

}
