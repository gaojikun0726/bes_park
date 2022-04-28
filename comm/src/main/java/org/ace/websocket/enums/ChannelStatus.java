package org.ace.websocket.enums;

/**
 * 通道状态定义
 */
public enum ChannelStatus
{
    /*
     * 初始态
     */
    INITIAL,

    /*
     * 绑定到Http session
     */
    SESSION_BOUND,

    /*
     * 绑定到TokenSN
     */
    TOKENSN_BOUND

}
