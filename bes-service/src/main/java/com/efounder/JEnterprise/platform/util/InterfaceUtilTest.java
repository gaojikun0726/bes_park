package com.efounder.JEnterprise.platform.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * describe: 接口访问测试方法
 *
 * @author zs
 * @date 2020/11/18
 */
public class InterfaceUtilTest {
    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(InterfaceUtilTest.class);

    /**
     * 测试接口访问
     * @param args
     */
    public static void main(String[] args) {
        JSONArray jsonArray = getPositionList();
//        JSONObject jsonObject = getAmmeterListTest();
    }

    /**
     * 测试 访问电表列表
     * @return
     */
    public static JSONArray getPositionList(){
        String path = "http://172.16.26.7:8282/api/v1/energy_control/building_info_list";
//        long time = 1594706386;
        String token = getTokenTest();
//        // 参数
        StringBuffer params = new StringBuffer();
        //获取10位系统时间戳
        long time = System.currentTimeMillis() / 1000;
//        long time = new Date().getTime();
        String appId = "qz_nengyuan";
        params.append("appId="+appId);
        params.append("&");
        params.append("syncTimeStamp="+time);
        String result = InterfaceAccessUtil.doGetWithParam(path,params.toString(),"token",token);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String msg = jsonObject.getString("msg");
        log.info("获取区域位置数据的接口, msg:["+msg+"]");
        JSONArray jsonArray = jsonObject.getJSONArray("data");
//        JSONObject jsonObject = getResultJSON(result);
        return jsonArray;
    }


    /**
     * 测试 访问电表列表
     * @return
     */
    public static JSONObject getAmmeterListTest(){
        String path = "http://172.31.14.209:8082/BESServer/api/public/getAmmeterList";
        long time = 1594706386;
        String token = getBesTokenTest();
//        // 参数
        StringBuffer params = new StringBuffer();
        params.append("lastTime="+time);
//        httpGet.setHeader("Cookie","JSESSIONID="+token);
        String result = InterfaceAccessUtil.doGetWithHeader(path,"Cookie","JSESSIONID="+token);
        JSONObject jsonObject = JSONObject.parseObject(result);
        return jsonObject;
    }

    /**
     * 测试 访问电表列表
     * @return
     */
    public static String getBesTokenTest(){
        String loginPath = "http://172.31.14.209:8082/BESServer/issp/v1.0/login";
        // 参数
        StringBuffer params = new StringBuffer();
        params.append("uName=admin");
        params.append("&");
        params.append("uPwd=123456");

        return getBesToken(loginPath,params.toString());
    }

    /**
     * 测试 访问电表列表
     * @return
     */
    public static String getTokenTest(){
        String loginPath = "http://172.16.26.7:8282/login";
//        String loginPath = "http://172.31.14.209:8082/BESServer/issp/v1.0/login";
        // 参数
//        "username": "admin",
//    "password": "hnsGx0lb9uJGh0AYTh5WVUVIZuVY0IJg1ouI4GkzJCKapLJ/fphISdq+zenyqSRD/sORQZc8ryrgmoLsPx7sag==",
//    "captcha": "9",
//    "uuid": "666"
//        StringBuffer params = new StringBuffer();
//        params.append("username=admin");
//        params.append("&");
//        params.append("password=hnsGx0lb9uJGh0AYTh5WVUVIZuVY0IJg1ouI4GkzJCKapLJ/fphISdq+zenyqSRD/sORQZc8ryrgmoLsPx7sag==");
//        params.append("&");
//        params.append("captcha=9");
//        params.append("&");
//        params.append("uuid=666");

        String json = "{\"username\":\"admin\",\"password\":\"hnsGx0lb9uJGh0AYTh5WVUVIZuVY0IJg1ouI4GkzJCKapLJ/fphISdq+zenyqSRD/sORQZc8ryrgmoLsPx7sag==\",\"captcha\":sdvz\",\"uuid\":\"1c6c77e8-2ce2-4156-89d9-339f5cfeccd8\"}";

        String username = "admin";
        String password = "hnsGx0lb9uJGh0AYTh5WVUVIZuVY0IJg1ouI4GkzJCKapLJ/fphISdq+zenyqSRD/sORQZc8ryrgmoLsPx7sag==";
        String captcha = "999";
        String uuid = "999";
//        String uuid = "1c6c77e8-2ce2-4156-89d9-339f5cfeccd8";
        return getTokenJson(loginPath,username,password,captcha,uuid);
    }

    /**
     * 获取token数据
     * @return
     */
    public static String getTokenJson(String loginPath,String username,String password,String captcha,String uuid){
        String token = null;
        //需要系统的一个账号信息作为参数，访问接口
        String loginData = InterfaceAccessUtil.doHttpBody(loginPath,username,password,captcha,uuid);
        JSONObject dataObject = JSONObject.parseObject(loginData);
        String msg = dataObject.getString("msg");
        log.info("获取区域位置数据--获取中台token, msg:["+msg+"]");
        JSONObject jsonObject = dataObject.getJSONObject("data");
//        JSONObject jsonObject = getResultJSON(loginData);
        if(jsonObject != null){
            token = jsonObject.getString("token");
        }
        return token;
    }

    /**
     * 请求结果String转换成JSONObject
     * @param data 请求结果
     * @return
     */
    public static JSONObject getResultJSON(String data){
        JSONObject dataObject = null;
        JSONObject jsonObject = JSONObject.parseObject(data);
        if(jsonObject != null){
            dataObject = jsonObject.getJSONObject("data");
        }

        return dataObject;
    }

    /**
     * 获取token数据
     * @return
     */
    public static String getBesToken(String loginPath,String params){
        //需要系统的一个账号信息作为参数，访问接口
        String loginData = InterfaceAccessUtil.doPostParam(loginPath,params);
        JSONObject jsonObject = JSONObject.parseObject(loginData);
        String token = jsonObject.getString("token");
        return token;
    }
}
