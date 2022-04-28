package org.ace.socketserver.handler;

import com.google.gson.JsonObject;

/*
 * T 目标类型
 */
public interface JsonMsgParser
{
    Object parse(JsonObject jsonObject);
}
