package org.ace.socketserver.handler;

/**
 * @author xiepufeng
 */
public interface TokenSupport
{

    /**
     * 判断 TokenSN 是否存在
     * @return
     */
    boolean isTokenExist(String tokenSN);

}
