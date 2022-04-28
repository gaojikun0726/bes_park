package org.ace.socketserver.handler.login;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.ace.socketserver.dto.param.LoginParam;
import org.ace.socketserver.dto.result.CmdMsg;
import org.ace.socketserver.handler.JsonMsgParser;

import java.lang.reflect.Type;

public class LoginMsgParser implements JsonMsgParser
{

    @Override
    public Object parse(JsonObject jsonObject)
    {
        Type type = new TypeToken<CmdMsg<LoginParam>>()
        {
        }.getType();

        Gson gson = new Gson();

        return gson.fromJson(jsonObject, type);

    }

}
