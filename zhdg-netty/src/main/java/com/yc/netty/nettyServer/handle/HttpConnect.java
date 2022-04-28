package com.yc.netty.nettyServer.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * @CkassName: HttpConnect
 * @Author: YangChao
 * @Date: 2020/4/7 15:44
 * @Descruotuib: 后端接收接口
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/netty/receive")
public class HttpConnect {
    private static final Logger log = LoggerFactory.getLogger(HttpConnect.class);

    @Autowired
    private com.yc.netty.nettyServer.core.ServerHandler ServerHandler;

    /**
    * @Description: 中间件接收下发指令
    * @author YangChao
    * @date 2020/4/7 15:47
    */
    @PostMapping("/sendMsg")
    public String sendMsg(HttpServletRequest request) throws Exception {
        String data = request.getParameter("data");
        String DeviceId = request.getParameter("DeviceId");
        log.info("中间件收到id："+DeviceId+"的下发指令码："+data);
        ServerHandler.sendMsg(DeviceId, data);
        return "下发指令："+data;
    }

}
