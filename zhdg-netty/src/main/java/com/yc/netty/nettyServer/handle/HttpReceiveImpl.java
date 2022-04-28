package com.yc.netty.nettyServer.handle;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.http.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @CkassName: HttpReceiveImpl
 * @Author: YangChao
 * @Date: 2020/4/8 9:27
 * @Descruotuib: 中间件给后端发送数据接口实现
 * @Version: 1.0
 **/
@Service
public class HttpReceiveImpl implements HttpReceive{
    private static final Logger log = LoggerFactory.getLogger(HttpReceiveImpl.class);
    @Value("${NettyReceive.Address}")
    private String address ;

    @Value("${NettyReceive.CleanAddress}")
    private String CleanAddress;

    /**
     * @Description: 中间件给上位机发送数据
     * @author YangChao
     * @date 2020/4/8 8:45
     */
    @Override
    public void httpGetJson(String clientIp,String DeviceId, String data){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("data", data);
        paramMap.put("DeviceId", DeviceId);
        paramMap.put("clientIp", clientIp);
        String result = "";
        try{
            result = HttpUtil.post(address, paramMap);
        }catch (IORuntimeException e){
            log.error("中间件给上位机发送数据==>"+"网络连接失败");
        }finally{
            log.info("中间件给上位机发送数据==>"+result);
        }
    }

    @Override
    public void httpCleanSend(String deviceId, String clientIp) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("DeviceId", deviceId);
        paramMap.put("clientIp", clientIp);
        String result = "";
        try{
            result = HttpUtil.post(CleanAddress, paramMap);
        }catch (IORuntimeException e){
            log.error("设备断线清空后端缓存数据==>"+"网络连接失败");
        }finally{
            log.info("设备断线清空后端缓存数据==>"+result);
        }
    }

}
