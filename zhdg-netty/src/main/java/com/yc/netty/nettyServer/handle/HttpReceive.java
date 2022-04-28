package com.yc.netty.nettyServer.handle;

public interface HttpReceive {

    void httpGetJson(String clientIp,String DeviceId, String data);

    void httpCleanSend(String deviceId, String clientIp);
}
