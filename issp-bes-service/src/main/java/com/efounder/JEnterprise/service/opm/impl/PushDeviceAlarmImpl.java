package com.efounder.JEnterprise.service.opm.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.core.config.sso.SsoConfig;
import com.efounder.JEnterprise.mapper.opm.DeviceAssetRelationMapper;
import com.efounder.JEnterprise.service.opm.InterfaceUtil;
import com.efounder.JEnterprise.service.opm.OpmValues;
import com.efounder.JEnterprise.service.opm.PushDeviceAlarm;
import org.apache.commons.lang.ObjectUtils;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * describe: 推送设备告警信息
 *
 * @author zs
 * @date 2020/11/30
 */
@Service
public class PushDeviceAlarmImpl implements PushDeviceAlarm {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(PushDeviceAlarmImpl.class);


    @Resource
    private DeviceAssetRelationMapper deviceAssetRelationMapper;


    @Resource
    private OpmValues opmValues;


    /**
     * 获取访问接口的token
     * @return
     */
    public String getToken(){
        Map<String, String> map = new HashMap<>();
        map.put("appId",opmValues.appId);
        map.put("clientSecret",opmValues.clientSecret);
        JSONObject dataObject = InterfaceUtil.postForForm(opmValues.tokenUrl,map);
        String accessToken = dataObject.getString("access_token");
        return accessToken;
    }

    /**
     * 推送设备离线信息
     *
     * @param sysname 离线的设备sysname
     * @param alertMsg 报警信息
     */
    @Override
    public void pushDeviceOfflineInfo(String sysname,String alertMsg) {
        String token = getToken();
        Map<String,String> typeMap = getDeviceAlarmInfo(token);
        Map<String,Object> map = new HashMap<>();
        map.put("sysname",sysname);
        map.put("source",opmValues.appId);
        map.put("alertMsg",alertMsg);
        map.putAll(typeMap);
        List<Map<String,String>> list = deviceAssetRelationMapper.queryRelationBySysname(map);
        if(list != null && list.size() > 0){
            Map<String,String> alarmMap = list.get(0);
//            String busCode = alarmMap.get("busCode");
//            String deviceCode = alarmMap.get("deviceCode");
//            String deviceName = alarmMap.get("deviceName");
//            String deviceType = alarmMap.get("deviceType");
//            String alertRemarks = alarmMap.get("alertRemarks");
//            String alertGrade = alarmMap.get("alertGrade");
//            String faultCode = alarmMap.get("faultCode");
//            String faultName = alarmMap.get("faultName");
//            String triggerTime = alarmMap.get("triggerTime");
//            String addressId = alarmMap.get("addressId");
//            String status = alarmMap.get("status");
//            String source = alarmMap.get("source");
//
//            StringEntity  stringEntity = new StringEntity("{\"busCode\":\""+busCode+"\",\"deviceCode\":\""+deviceCode+"\",\"deviceName\":\""+deviceName
//                    +"\",\"deviceType\":\""+deviceType+"\",\"alertRemarks\":\""+alertRemarks+"\",\"alertGrade\":\""+alertGrade
//                    +"\",\"faultCode\":\""+faultCode+"\",\"faultName\":\""+faultName+"\",\"triggerTime\":\""+triggerTime
//                    +"\",\"addressId\":\""+addressId+"\",\"status\":\""+status+"\",\"source\":\""+opmValues.appId+"\"}","UTF-8");
            String json = JSON.toJSONString(alarmMap, SerializerFeature.WriteMapNullValue);
            StringEntity stringEntity = new StringEntity(json,"UTF-8");
            stringEntity.setContentType("application/json");

            String result = InterfaceUtil.doHttpEntityWithBearerToken(opmValues.alarmUrl,token,stringEntity);
            logger.info("推送报警消息，结果：" + result);
        }

    }


//    /**
//     * 获取暂时的access_token
//     * @return
//     */
//    @Override
//    public String getTempToken(){
//        String ssoToken = getTestSsoToken();
//        //http://172.16.26.56:8080/auth/sso/token?code=8d5d28c93af05c507fcb05aaa8703ab3&app_id=qz_opm
//        StringBuffer path = new StringBuffer(ssoConfig.ssoTokenUri);
//        path.append("?code=").append(ssoToken).append("&app_id=qz_opm");
////        String path = opmConfig.accessIp + "/auth/sso/token?code=" + ssoToken+"&app_id=qz_opm";
//        String result = InterfaceUtil.doGet(path.toString());
//        JSONObject dataObject = JSONObject.parseObject(result);
//        String accessToken = dataObject.getString("access_token");
//        return accessToken;
//    }

//    /**
//     * 获取暂时的测试用ssotoken
//     * @return
//     */
//    public String getTestSsoToken(){
//        String ssoToken = "";
////        http://172.16.26.56:8080/auth/sso/test/code?username=admin
//        String path = ssoConfig.testSsoTokenUrl;
////        String path = opmConfig.accessIp + "/auth/sso/test/code?username=admin";
//        String result = InterfaceUtil.doGet(path);
//        JSONObject dataObject = JSONObject.parseObject(result);
//        ssoToken = dataObject.getString("data");
//        return ssoToken;
//    }

//    /**
//     * 获取token
//     * @return
//     */
//    @Override
//    public String getToken(){
//        String token = null;
//
//        //需要系统的一个账号信息作为参数，访问接口
//        StringEntity stringEntity = new StringEntity("{\"username\":\""+opmValues.username+"\",\"password\":\""+opmValues.password+"\",\"captcha\":\""+opmValues.captcha+"\",\"uuid\":\""+opmValues.uuid+"\"}","UTF-8");
//        String result = InterfaceUtil.doHttpEntity(opmValues.tokenUrl,stringEntity);
//        JSONObject dataObject = JSONObject.parseObject(result);
//        String msg = dataObject.getString("msg");
//        logger.info("获取中台token, msg:["+msg+"]");
//        JSONObject jsonObject = dataObject.getJSONObject("data");
//        if(jsonObject != null){
//            token = jsonObject.getString("token");
//        }
//        logger.info("获取token，结果：" + result);
//
//        return token;
//    }


    /**
     * 获取报警类型接口数据
     * @return
     */
    @Override
    public JSONArray getAlarmTypeList(String token){
//        String token = getToken();
//http://172.31.0.79:8282/api/ops/v1/alarm/alarm_type_by_systemcode?appId=qz_nengyuan
        StringBuffer params = new StringBuffer();
        params.append("appId="+"qz_nengyuan");
        String result = InterfaceUtil.doGetBearerTokenWithParam(opmValues.alarmTypeUrl,token,params.toString());
        JSONObject jsonObject = JSONObject.parseObject(result);
        String msg = jsonObject.getString("msg");
        logger.info("获取报警类型数据接口, msg:["+msg+"]");
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        return jsonArray;
    }


    /**
     * 获取设备报警信息：编码和名称
     * @return
     */
    @Override
    public Map<String,String> getDeviceAlarmInfo(String token){
        JSONArray jsonArray = getAlarmTypeList(token);
        Map<String,String> resultMap = new HashMap<>();
        if(jsonArray != null && jsonArray.size() > 0){
            for(Object object : jsonArray){
                Map map = (Map)object;
                String code = ObjectUtils.toString(map.get("code"));
                String name = ObjectUtils.toString(map.get("alarmName"));
                if(opmValues.deviceAlarmCode.equals(code)){
                    resultMap.put("faultCode",code);
                    resultMap.put("faultName",name);
                    break;
                }
            }
        }
        return resultMap;
    }
}
