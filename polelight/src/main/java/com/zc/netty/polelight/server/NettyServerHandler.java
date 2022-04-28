package com.zc.netty.polelight.server;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zc.netty.polelight.config.ServerConfig;
import com.zc.netty.polelight.handle.ChannelManager;
import com.zc.netty.polelight.protocol.modbus.ModbusCmdResolver;
import com.zc.netty.polelight.protocol.modbus.ModbusCmdType;
import com.zc.netty.polelight.protocol.modbus.ModbusFunctionCode;
import com.zc.netty.polelight.util.InterfaceAccessUtil;
import com.zc.netty.polelight.util.SpringUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.http.entity.StringEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * describe: 消息处理类
 *
 * @author zs
 * @date 2021/3/5
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger log = LogManager.getLogger(NettyServerHandler.class);

    private ServerConfig serverConfig = SpringUtils.getBean(ServerConfig.class);

//    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        Channel channel = ctx.channel();
        ByteBuf buf = (ByteBuf) msg;
        String data = ByteBufUtil.hexDump(buf).toUpperCase();
        System.out.println("接收到消息："+data);
        JSONObject jsonObject = ModbusCmdResolver.commandParse(data);
//        System.out.println("commandParse方法转换后的结果："+jsonObject.toJSONString());
        //00 01 00 00 00 06 01 03 00 9D 00 01
        String serial = jsonObject.getString("serial");
        int serialInt = Integer.parseInt(serial,16);
        String functionCode = jsonObject.getString("functionCode");
        String type = jsonObject.getString("type");
        if(ModbusFunctionCode.CODE_THREE.equals(functionCode) && ModbusCmdType.SWITCH_STATUS.equalsIgnoreCase(type)){
            //开关状态
            Map map = new HashMap();
            map.put("data",jsonObject.get("data"));
            //通过http请求将数据推送到开关状态接收接口
            ChannelManager.pduSerialMap.forEach((ip,serialNum)->{
                if(serialInt == serialNum){
                    map.put("ip",ip);
                }
            });

            String json = JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
            StringEntity stringEntity = new StringEntity(json,"UTF-8");
            stringEntity.setContentType("application/json");

            String result = InterfaceAccessUtil.doHttpEntity(serverConfig.switchStatusUrl,stringEntity);
            log.info("向平台推送PDU开关状态：" + result);
        }

        //功能码06不做任何操作

        ctx.flush();
    }


    /**
     * 异常发生时调用
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        Channel channel = ctx.channel();
        log.error("Client:" + channel.remoteAddress() + " exception");
        // 当出现异常就关闭连接
//        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        String address = inetSocketAddress.getAddress().getHostAddress();
        int port = inetSocketAddress.getPort();
        System.out.println("通道注册：ip="+address+",port="+port);
        ChannelManager.channelMap.put(address,ctx.channel());
        if(ChannelManager.pduSerialMap.get(address) == null){
            Integer serial = ChannelManager.getNewSerial();
            System.out.println("通道注册序列号：ip="+address+",serial="+serial);
            ChannelManager.pduSerialMap.put(address,serial);
        }

        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
//        InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
//        String address = inetSocketAddress.getAddress().getHostAddress();
//        int port = inetSocketAddress.getPort();
//        System.out.println("通道有效：ip="+address+",port="+port);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        String address = inetSocketAddress.getAddress().getHostAddress();
        //移除
        ChannelManager.channelMap.remove(address);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }


}
