package org.ace.websocket.handler;


import org.ace.websocket.dto.result.CmdExeResult;

/**
 * 消息发送器
 */
public interface ResultSendable
{
    void send(CmdExeResult result);
}
