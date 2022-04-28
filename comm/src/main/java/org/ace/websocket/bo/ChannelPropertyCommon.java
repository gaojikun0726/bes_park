package org.ace.websocket.bo;

/**
 * @author xiepufeng
 */
public class ChannelPropertyCommon
{
    private int port;

    private String path;

    private String password;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
