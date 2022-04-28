package org.ace.business.handler;

import com.google.gson.JsonObject;
import io.netty.channel.ChannelHandlerContext;
import org.ace.business.constant.Cmd;
import org.ace.business.constant.JsonAttr;

/**
 * 数据类型转发类
 * @author xiepufeng
 */
public class ParseDiscern
{
    private ChannelHandlerContext ctx;

    public ParseDiscern(ChannelHandlerContext ctx)
    {
        this.ctx = ctx;
    }

    public void relayStation(JsonObject jsonObject)
    {
        if (null == jsonObject || jsonObject.isJsonNull())
        {
            return;
        }

        boolean indexNameHas = jsonObject.has(JsonAttr.INDEX);

        if (!indexNameHas)
        {
            return;
        }

        int indexAttrValue = jsonObject.get(JsonAttr.INDEX).getAsInt();

        switch (indexAttrValue)
        {
            case Cmd.HEARTBEAT: // 心跳

                HeartbeatHandler.handler(ctx, jsonObject);
                break;
            case Cmd.IP_BIND: // 绑定 ip

                TokenHandler.handler(ctx, jsonObject);
                break;
            default:
                ReceiptMsgHandler.handler(ctx, jsonObject);
                HeartbeatHandler.refresh(ctx);
                break;
        }

    }

}
