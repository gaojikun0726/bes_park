package org.ace.websocket.dto.result;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 指令结果定义
 * @param <T>
 */
public class CmdMsg<T> extends JsonMsg //implements JsonDeserializer, JsonSerializer
{

    /**
     * 命令或者方法名称
     */
    private String method;

    private T params;

    public String getMethod()
    {
        return method;
    }

    public void setMethod(String method)
    {
        this.method = method;
    }

    public T getParams()
    {
        return params;
    }

    public void setParams(T params)
    {
        this.params = params;
    }

    public CmdMsg<T> parseResult(String json, final Type typeOfData)
    {
        Type resultType = new ParameterizedType()
        {
            @Override
            public Type[] getActualTypeArguments()
            {
                return new Type[] { typeOfData };
            }

            @Override
            public Type getOwnerType()
            {
                return null;
            }

            @Override
            public Type getRawType()
            {
                return CmdMsg.class;
            }
        };

        Gson gson = new Gson();
        return gson.fromJson(json, resultType);
    }

}
