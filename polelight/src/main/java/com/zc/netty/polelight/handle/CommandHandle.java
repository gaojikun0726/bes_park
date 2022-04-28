package com.zc.netty.polelight.handle;

import com.alibaba.fastjson.JSONObject;
import com.zc.netty.polelight.protocol.modbus.ModbusCmdGenerator;
import com.zc.netty.polelight.protocol.modbus.ModbusCmdType;
import com.zc.netty.polelight.util.MsgUtil;
import com.zc.netty.polelight.util.ThreadPool;
import io.netty.channel.Channel;
import org.apache.commons.codec.DecoderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * describe: 命令处理类
 *
 * @author zs
 * @date 2021/11/19
 */
@Component
public class CommandHandle {

//    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private static final Logger log = LogManager.getLogger(CommandHandle.class);

    /**
     * 开关控制命令下发
     * @param ip
//     * @param port
     * @param controlObject
     * @param switchStatus
     */
    public JSONObject  switchControl( String ip,
//                                      String port,
                                      String controlObject, String switchStatus){
        JSONObject jsonObject = new JSONObject();

//        String switchWay = ModbusCmdGenerator.getSwitchWay(controlObject);
        Integer serial = ChannelManager.pduSerialMap.get(ip);
        if(serial == null){
            serial = ChannelManager.getNewSerial();
            ChannelManager.pduSerialMap.put(ip,serial);
        }
        String command = ModbusCmdGenerator.getWriteCommand("", ModbusCmdType.SWITCH_CONTROL,serial,controlObject,switchStatus);
        Channel channel = ChannelManager.channelMap.get(ip);
        if(channel != null && channel.isActive()){
            //通道有效，下发指令
            try {
                System.out.println("发送  指令："+command);
                channel.writeAndFlush(MsgUtil.convertStringToByteBuf(command.replace(" ","")));

                //重复下发指令
                repeatCmd(channel,command);

            } catch (DecoderException e) {
//                e.printStackTrace();
                log.error("开关控制命令下发报错："+e.getMessage());
            }
            jsonObject.put("result","true");
            jsonObject.put("msg","下发指令成功");
        }else{
            jsonObject.put("result","false");
            jsonObject.put("msg","下发指令失败，设备连接失败");
        }
        return jsonObject;
    }


    /**
     * 下发指令，获取控制对象的开关状态
     * @param ip IP地址
//     * @param port 端口号
     * @param controlObject 控制对象，显示屏、音柱、摄像头等
     * @return
     */
    public JSONObject getSwitchStatus(String ip,
//                                      String port,
                                      String controlObject){
        JSONObject jsonObject = new JSONObject();
//        String switchWay = ModbusCmdGenerator.getSwitchWay(controlObject);
        Integer serial = ChannelManager.pduSerialMap.get(ip);
        if(serial == null){
            serial = ChannelManager.getNewSerial();
            ChannelManager.pduSerialMap.put(ip,serial);
        }
        String command = ModbusCmdGenerator.getReadCommand("", ModbusCmdType.SWITCH_STATUS,serial);
        Channel channel = ChannelManager.channelMap.get(ip);
        if(channel != null && channel.isActive()){
            //通道有效，下发指令
            try {
                System.out.println("发送  指令："+command);
                channel.writeAndFlush(MsgUtil.convertStringToByteBuf(command.replace(" ","")));

                //重复下发指令
                repeatCmd(channel,command);
            } catch (DecoderException e) {
//                e.printStackTrace();
                log.error("开关状态读取命令下发报错："+e.getMessage());
            }
            jsonObject.put("result","true");
            jsonObject.put("msg","下发指令成功");
        }else{
            jsonObject.put("result","false");
            jsonObject.put("msg","下发指令失败，设备连接失败");
        }
        return jsonObject;

    }


    /**
     *  为保证成功率，间隔2秒钟，多发两次开关指令【硬件通信问题，厂家提出的解决方案】
     * @param channel
     * @param command
     */
    public void repeatCmd(Channel channel,String command){

        ThreadPool.executor.execute(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                log.error("发送开关指令方法，线程报错："+e.getMessage());
            }
            try {
                System.out.println("重复发送指令："+command);
                channel.writeAndFlush(MsgUtil.convertStringToByteBuf(command.replace(" ","")));
            } catch (DecoderException e) {
                log.error("发送开关指令报错："+e.getMessage());
            }
        });
    }
}
