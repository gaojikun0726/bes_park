package com.yc.netty.nettyServer.core;

import java.io.Serializable;

/**
 * @CkassName: ServerMessage
 * @Author: YangChao
 * @Date: 2019/10/23 9:50
 * @Descruotuib:
 * @Version: 1.0
 **/
public class ServerMessage implements Serializable {
    private String serverName;
    private String clientAddress;
    private String message = "Server to Client.";

    public ServerMessage(String serverName, String clientAddress) {
        this.serverName = serverName;
        this.clientAddress = clientAddress;
    }

    public String getServerName() {
        return serverName;
    }
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getClientAddress() {
        return clientAddress;
    }
    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString(){

        return "{ " + this.serverName + " " + this.clientAddress + " }";
    }
}