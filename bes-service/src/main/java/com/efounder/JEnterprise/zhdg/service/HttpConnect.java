package com.efounder.JEnterprise.zhdg.service;

/**
 * @CkassName: HttpConnect
 * @Author: YangChao
 * @Date: 2020/4/7 15:53
 * @Descruotuib: 和中间件连接 接口
 * @Version: 1.0
 **/
public interface HttpConnect {
    void sendMsg(String DeviceId, String toString);
}
