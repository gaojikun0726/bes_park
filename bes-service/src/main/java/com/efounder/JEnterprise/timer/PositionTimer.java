package com.efounder.JEnterprise.timer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.core.ApplicationContextProvider;
import com.efounder.JEnterprise.platform.config.OpmConfig;
import com.efounder.JEnterprise.platform.service.IOpmPositionInfoService;
import com.efounder.JEnterprise.platform.util.InterfaceAccessUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * describe: 获取中台资产数据定时任务调用方法
 *
 * @author zs
 * @date 2022/1/8
 */
public class PositionTimer {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(PositionTimer.class);

    private OpmConfig opmConfig = ApplicationContextProvider.getBean(OpmConfig.class);

    private IOpmPositionInfoService opmPositionInfoService = ApplicationContextProvider.getBean(IOpmPositionInfoService.class);

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
     * 调用获取区域位置数据的接口
     */
    public void getPositionInfoList() {
        logger.info("获取中台资产数据定时任务调用方法执行");
        String token = getToken();
        if(token == null || "".equals(token)){
            logger.error("定时任务获取区域位置数据--未获取到获取中台token，token:["+token+"]");
            return;
        }
        String result = InterfaceAccessUtil.doGetBearerToken(opmConfig.positionUrl,token);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String msg = jsonObject.getString("msg");
        logger.info("定时任务获取区域位置数据的接口, msg:["+msg+"]");
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        if(jsonArray != null && jsonArray.size() > 0){
            Integer num = opmPositionInfoService.handleAccessData(jsonArray);
            logger.info("定时任务调用获取区域位置数据的接口：更新全部"+num + "条数据");
        }
    }
}
