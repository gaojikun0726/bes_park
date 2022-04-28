package org.ace.business.handler;

import com.google.auto.service.AutoService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.ace.socketserver.bo.ChannelProperty;
import org.ace.socketserver.constant.AttributeKeyConst;
import org.ace.socketserver.handler.MessageHandler;

import java.util.logging.Logger;

/**
 * @author xiepufeng
 */
@AutoService(MessageHandler.class)
public class CustomMessageHandler implements MessageHandler
{

    private static final Logger log = Logger.getLogger(CustomMessageHandler.class.getName());

    private ParseDiscern parseDiscern;

    @Override
    public void messageReceived(ChannelHandlerContext ctx, String message)
    {
        if (!CrcUtil.verifyCRC(message))
        {
            return;
        }

        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement;
        JsonObject jsonObject;

        Channel channel = ctx.channel();

        try
        {
            jsonElement = jsonParser.parse(message);
            jsonObject = jsonElement.getAsJsonObject();

            if (null == parseDiscern)
            {
                parseDiscern = new ParseDiscern(ctx);
            }

            parseDiscern.relayStation(jsonObject);

        }
        catch (JsonSyntaxException | IllegalStateException e)
        {
            e.printStackTrace();
            channel.close();
        }

    }

    // 设备离线时回调
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception
    {
        ClientMsgReceive clientMsgReceive = ReceiptMsgHandler.clientMsgReceive;

        // 设置控制器状态为离线
        if (null != clientMsgReceive)
        {
            // 设置 channel 属性
            Channel channel = ctx.channel();

            ChannelProperty channelProperty = channel.attr(AttributeKeyConst.CHANNEL_PROPERTY_KEY).get();

            String ip = channelProperty.getTokenSN();


            clientMsgReceive.controllerState(ip, false);
        }

    }


    public ParseDiscern getParseDiscern()
    {
        return parseDiscern;
    }

    public void setParseDiscern(ParseDiscern parseDiscern)
    {
        this.parseDiscern = parseDiscern;
    }
}
