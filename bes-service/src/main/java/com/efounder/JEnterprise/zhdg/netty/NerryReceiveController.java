package com.efounder.JEnterprise.zhdg.netty;

import com.efounder.JEnterprise.zhdg.service.ISebDynamicAgreeHandleService;
import com.efounder.JEnterprise.zhdg.util.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @CkassName: NerryReceiveController
 * @Author: YangChao
 * @Date: 2020/3/26 10:03
 * @Descruotuib: swagger netty数据接收
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/netty/receive")
public class NerryReceiveController{
    private static final Logger log = LoggerFactory.getLogger(NerryReceiveController.class);
    /**动态协议解析接口*/
    @Autowired
    private ISebDynamicAgreeHandleService AgreeHandleService;

    @PostMapping("/list")
    public AjaxResult nettyData(HttpServletRequest request) throws Exception {
        String data = request.getParameter("data");
        String DeviceId = request.getParameter("DeviceId");
        String clientIp = request.getParameter("clientIp");
        Map<String, String> map = new HashMap<>();
        map.put("status", "1");
        map.put("msg", "数据接收成功");
        AgreeHandleService.DynamicAgreeHandle(clientIp, DeviceId, data);
//        log.info("#NerryReceiveController 接收zhdg中间件传输数据："+data);
        return AjaxResult.success(map);
    }

    @PostMapping("/cleanlist")
    public AjaxResult cleanlist(HttpServletRequest request) throws Exception {
        String DeviceId = request.getParameter("DeviceId");
        String clientIp = request.getParameter("clientIp");
        Map<String, String> map = new HashMap<>();
        map.put("status", "1");
        map.put("msg", "清空缓存指令数据接收成功");
        AgreeHandleService.CleanRealData( DeviceId, clientIp);
//        log.info("#NerryReceiveController 设备心跳检测中断："+DeviceId);
        return AjaxResult.success(map);
    }
}
