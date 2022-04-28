package com.efounder.JEnterprise.platform.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.core.config.sso.SsoConfig;
import com.efounder.JEnterprise.platform.config.OpmConfig;
import com.efounder.JEnterprise.platform.service.AccessDataService;
import com.efounder.JEnterprise.platform.service.IOpmPositionInfoService;
import com.efounder.JEnterprise.platform.util.InterfaceAccessUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * describe: 接口调用定时任务
 *
 * @author zs
 * @date 2020/11/18
 */
@Component
public class InterfaceTimer {

    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(InterfaceTimer.class);

    @Resource
    private IOpmPositionInfoService opmPositionInfoService;

    @Resource
    private AccessDataService accessDataService;

    /**
     * 中台对接配置信息
     */
    @Resource
    private OpmConfig opmConfig;



    /**
     * 获取访问接口的token
     * @return
     */
    public String getToken(){
        String result = InterfaceAccessUtil.getToken(opmConfig.tokenUrl,opmConfig.appId,opmConfig.clientSecret);
        JSONObject dataObject = JSONObject.parseObject(result);
        String accessToken = dataObject.getString("access_token");
        return accessToken;
    }

    /**
     * 每天7点调用获取区域位置数据的接口
     */
//    @Scheduled(cron = "0 0 2,7,9,12,16,20,22 * * ?")
    public void getPositionInfoList() {
       String token = getToken();
       if(token == null || "".equals(token)){
           log.error("获取区域位置数据--未获取到获取中台token，token:["+token+"]");
           return;
       }
//        StringBuffer params = new StringBuffer();
//        params.append("appId="+opmConfig.appId);
//        params.append("&");
//        params.append("syncTimeStamp="+opmConfig.syncTimeStamp);
        String result = InterfaceAccessUtil.doGetBearerToken(opmConfig.positionUrl,token);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String msg = jsonObject.getString("msg");
        log.info("获取区域位置数据的接口, msg:["+msg+"]");
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        if(jsonArray != null && jsonArray.size() > 0){
            Integer num = opmPositionInfoService.handleAccessData(jsonArray);
            log.info("每天7点调用获取区域位置数据的接口：更新全部"+num + "条数据");
        }
    }

    /**
     * 获取全部的固定资产列表
     * 每天1点30分调用获取数据的接口
     */
    @Scheduled(cron = "0 30 1 * * ?")
    public void getAllAssetsList(){
        String token = getToken();
        if(token == null || "".equals(token)){
            log.error("获取全部的固定资产列表--未获取到获取中台token，token:["+token+"]");
            return;
        }
//        StringBuffer params = new StringBuffer();
//        params.append("appId="+opmConfig.appId);
        String result = InterfaceAccessUtil.doGetBearerToken(opmConfig.allAssetsUrl,token);
        JSONArray jsonArray = JSONArray.parseArray(result);
//        JSONObject jsonObject = JSONObject.parseObject(result);
//        String msg = jsonObject.getString("msg");
//        log.info("获取全部的固定资产列表接口, msg:["+msg+"]");
//        JSONArray jsonArray = jsonObject.getJSONArray("data");
        if(jsonArray != null && jsonArray.size() > 0){
            Integer num = accessDataService.handleAllAssetsData(jsonArray);
            log.info("获取全部的固定资产列表的接口：更新全部"+num + "条数据");
            if(num > 0){
                //将资产设备数据插入到设备表bes_device中,将能源设备和资产设备关联起来
                Integer deviceNum = accessDataService.handleDeviceData();
                log.info("设备表中新增"+deviceNum+"条数据");
            }
        }
    }
//    /**
//     * 获取token
//     * @return
//     */
//    public String getToken(){
//        String token = null;
//        //需要系统的一个账号信息作为参数，访问接口
//        String loginData = InterfaceAccessUtil.doHttpBody(opmConfig.tokenUrl,opmConfig.username,opmConfig.password,opmConfig.captcha,opmConfig.uuid);
//        JSONObject dataObject = JSONObject.parseObject(loginData);
//        String msg = dataObject.getString("msg");
//        log.info("获取中台token, msg:["+msg+"]");
//        JSONObject jsonObject = dataObject.getJSONObject("data");
//        if(jsonObject != null){
//            token = jsonObject.getString("token");
//        }
//        return token;
//    }

//    /**
//     * 获取暂时的access_token
//     * @return
//     */
//    public String getTempToken(){
//        String ssoToken = getTestSsoToken();
//        //http://172.16.26.56:8080/auth/sso/token?code=8d5d28c93af05c507fcb05aaa8703ab3&app_id=qz_opm
//        StringBuffer path = new StringBuffer(ssoConfig.ssoTokenUri);
//        path.append("?code=").append(ssoToken).append("&app_id=qz_opm");
////        String path = opmConfig.accessIp + "/auth/sso/token?code=" + ssoToken+"&app_id=qz_opm";
//        String result = InterfaceAccessUtil.doGet(path.toString());
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
//        String result = InterfaceAccessUtil.doGet(path);
//        JSONObject dataObject = JSONObject.parseObject(result);
//         ssoToken = dataObject.getString("data");
//        return ssoToken;
//    }

//    /**
//     * 每天零点调用获取用户数据的接口
//     */
////    @Scheduled(cron = "0 20 0 * * ?")
//    @Deprecated
//    public void getUserList(){
//        String token = getToken();
//        if(token == null || "".equals(token)){
//            log.error("获取用户数据--未获取到获取中台token，token:["+token+"]");
//            return;
//        }
//        String result = InterfaceAccessUtil.doGetWithHeader(opmConfig.userUrl,"token",token);
//        JSONObject jsonObject = JSONObject.parseObject(result);
//        String msg = jsonObject.getString("msg");
//        log.info("获取用户数据接口, msg:["+msg+"]");
//        JSONArray jsonArray = jsonObject.getJSONArray("data");
//        if(jsonArray != null && jsonArray.size() > 0){
//            Integer num = accessDataService.handleUserData(jsonArray);
//            log.info("每天零点调用获取用户数据的接口：更新全部"+num + "条数据");
//        }
//    }



//    /**
//     * 每天零点10分调用获取用户数据的接口
//     */
////    @Scheduled(cron = "0 30 0 * * ?")
//    @Deprecated
//    public void getRoleList(){
//        String token = getToken();
//        if(token == null || "".equals(token)){
//            log.error("获取角色数据--未获取到获取中台token，token:["+token+"]");
//            return;
//        }
//        String result = InterfaceAccessUtil.doGetWithHeader(opmConfig.roleUrl,"token",token);
//        JSONObject jsonObject = JSONObject.parseObject(result);
//        String msg = jsonObject.getString("msg");
//        log.info("获取角色数据接口, msg:["+msg+"]");
//        JSONArray jsonArray = jsonObject.getJSONArray("data");
//        if(jsonArray != null && jsonArray.size() > 0){
//            Integer num = accessDataService.handleRoleData(jsonArray);
//            log.info("获取角色数据的接口：更新全部"+num + "条数据");
//        }
//    }


    /**
     * 每天零点10分调用获取组织机构数据的接口
     */
    @Scheduled(cron = "0 10 0 * * ?")
    public void getDeptList(){
        String token = getToken();
        if(token == null || "".equals(token)){
            log.error("获取组织机构数据--未获取到获取中台token，token:["+token+"]");
            return;
        }
        String params = "syncTimeStamp=0";
        String result = InterfaceAccessUtil.doGetBearerTokenWithParam(opmConfig.deptUrl,token,params.toString());
        JSONObject jsonObject = JSONObject.parseObject(result);
        String msg = jsonObject.getString("msg");
        log.info("获取组织机构数据接口, msg:["+msg+"]");
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        if(jsonArray != null && jsonArray.size() > 0){
            Integer num = accessDataService.handleDeptData(jsonArray);
            log.info("获取组织机构数据的接口：更新全部"+num + "条数据");
        }
    }


//    /**
//     * 每天零点30分调用获取角色菜单关联关系数据的接口
//     */
////    @Scheduled(cron = "0 30 0 * * ?")
//    @Deprecated
//    public void getRoleMenuList(){
//        String token = getToken();
//        if(token == null || "".equals(token)){
//            log.error("获取角色菜单关联关系数据--未获取到获取中台token，token:["+token+"]");
//            return;
//        }
//        StringBuffer params = new StringBuffer();
//        params.append("appId="+opmConfig.appId);
//        String result = InterfaceAccessUtil.doGetWithParam(opmConfig.roleMenuUrl,params.toString(),"token",token);
//        JSONObject jsonObject = JSONObject.parseObject(result);
//        String msg = jsonObject.getString("msg");
//        log.info("获取角色菜单关联关系数据接口, msg:["+msg+"]");
//        JSONArray jsonArray = jsonObject.getJSONArray("data");
//        if(jsonArray != null && jsonArray.size() > 0){
//            Integer num = accessDataService.handleRoleMenuData(jsonArray);
//            log.info("获取角色菜单关联关系数据的接口：更新全部"+num + "条数据");
//        }
//    }


//    /**
//     * 固定资产设备--空调
//     * 每天零点35分调用获取数据的接口
//     */
////    @Scheduled(cron = "0 35 0 * * ?")
//    public void getConditionerList(){
//        String token = getToken();
//        if(token == null || "".equals(token)){
//            log.error("获取固定资产-空调数据--未获取到获取中台token，token:["+token+"]");
//            return;
//        }
//        StringBuffer params = new StringBuffer();
//        params.append("appId="+opmConfig.appId);
//        params.append("&");
//        params.append("categoryCode="+opmConfig.conditionerCode);
//        String result = InterfaceAccessUtil.doGetWithParam(opmConfig.assetsUrl,params.toString(),"token",token);
//        JSONObject jsonObject = JSONObject.parseObject(result);
//        String msg = jsonObject.getString("msg");
//        log.info("获取固定资产-空调数据接口, msg:["+msg+"]");
//        JSONArray jsonArray = jsonObject.getJSONArray("data");
//        if(jsonArray != null && jsonArray.size() > 0){
//            Integer num = accessDataService.handleAssetsData(jsonArray,opmConfig.conditionerCode);
//            log.info("获取固定资产-空调数据的接口：更新全部"+num + "条数据");
//        }
//    }

    /**
     * 获取本系统所有设备编码的设备列表
     * 每天零点40分调用获取数据的接口
     */
//    @Scheduled(cron = "0 40 0 * * ?")
    public void getAllDeviceList(){
        String token = getToken();
        if(token == null || "".equals(token)){
            log.error("获取本系统所有设备编码的设备列表--未获取到获取中台token，token:["+token+"]");
            return;
        }
        JSONArray jsonArray = new JSONArray();
        for(String deviceCode : opmConfig.getCodeArray()){
            JSONArray itemJsonArray = getSingleDeviceList(token,deviceCode);
            if(itemJsonArray != null){
                jsonArray.addAll(itemJsonArray);
            }
        }

        if(jsonArray.size() > 0){
            Integer num = accessDataService.handleAllAssetsData(jsonArray);
            log.info("获取本系统所有设备编码的设备列表接口：更新全部"+num + "条数据");
            if(num > 0){
                //将资产设备数据插入到设备表bes_device中,将能源设备和资产设备关联起来
                Integer deviceNum = accessDataService.handleDeviceData();
                log.info("设备表中新增"+deviceNum+"条数据");
            }
        }
    }

    /**
     * 获取单个设备编码的设备数据
     * @param token token
     * @param deviceCode 设备编码
     * @return
     */
    public JSONArray getSingleDeviceList(String token,String deviceCode){
        StringBuffer params = new StringBuffer();
        params.append("appId="+opmConfig.appId);
        params.append("&");
        params.append("categoryCode="+ deviceCode);

        String result = InterfaceAccessUtil.doGetBearerTokenWithParam(opmConfig.assetsUrl,token,params.toString());
        JSONArray jsonArray = JSONArray.parseArray(result);
        return jsonArray;
    }



}
