package org.ace.websocket.handler;

import org.ace.websocket.dto.result.CmdExeResult;
import org.ace.websocket.dto.result.JsonMsg;

/**
 * 消息的处理器
 */
public interface CmdHandler
{
    CmdExeResult execute(JsonMsg jsonMsg);
}
