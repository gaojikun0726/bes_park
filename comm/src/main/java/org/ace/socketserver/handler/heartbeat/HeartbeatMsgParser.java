package org.ace.socketserver.handler.heartbeat;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.ace.socketserver.dto.param.HeartbeatParam;
import org.ace.socketserver.dto.result.CmdMsg;
import org.ace.socketserver.handler.JsonMsgParser;

import java.lang.reflect.Type;
import java.util.logging.Logger;

/**
 * 心跳消息解析器
 */
public class HeartbeatMsgParser implements JsonMsgParser
{
    private static final Logger log = Logger.getLogger(HeartbeatMsgParser.class.getName());

    @Override
    public Object parse(JsonObject jsonObject)
    {

        Type type = new TypeToken<CmdMsg<HeartbeatParam>>()
        {
        }.getType();

        Gson gson = new Gson();

        return gson.fromJson(jsonObject, type);
    }

}
