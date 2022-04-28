package org.ace.websocket.handler;

import com.google.gson.JsonObject;

/*
 * 消息解析器
 */
public interface JsonMsgParser
{
    Object parse(JsonObject jsonObject);
}
