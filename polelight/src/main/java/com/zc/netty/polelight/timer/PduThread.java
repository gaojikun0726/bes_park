package com.zc.netty.polelight.timer;

import com.zc.netty.polelight.handle.ChannelManager;
import com.zc.netty.polelight.protocol.modbus.ModbusCmdGenerator;
import com.zc.netty.polelight.protocol.modbus.ModbusCmdType;
import com.zc.netty.polelight.util.MsgUtil;
import com.zc.netty.polelight.util.ThreadPool;
import io.netty.channel.Channel;
import org.apache.commons.codec.DecoderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * describe: 定时发送命令，保持PDU可用
 * 长时间不通信socket会断开，设定每一分钟下发一次查询电压的指令【改为开关状态查询指令】
 * 【硬件通信问题，厂家提出的解决方案】
 * @author zs
 * @date 2021/12/16
 */
@Component
public class PduThread {

    private static final Logger log = LogManager.getLogger(PduThread.class);


    /**
     * 每隔一分钟发送一次查询电压指令,保持与PDU通信连接不失效【改为开关状态查询指令】
     */
    public void pushThread(){
        ThreadPool.executor.execute(()->{
            while(true){
                try {
//                    sendReadVoltageCmd();
                    sendReadSwitchStatusCmd();
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    log.error("不间断发送查询电压指令方法报错："+e.getMessage());
                }
            }

        });
    }


    /**
     * 向所有PDU发送查询电压指令
     */
    public void sendReadVoltageCmd() {

        ChannelManager.pduSerialMap.forEach((ip,serial) ->{
            if(serial == null){
                serial = ChannelManager.getNewSerial();
                ChannelManager.pduSerialMap.put(ip,serial);
            }
            String command = ModbusCmdGenerator.getReadCommand("", ModbusCmdType.READ_VOLTAGE,serial);
            Channel channel = ChannelManager.channelMap.get(ip);
            if(channel != null && channel.isActive()){
                //通道有效，下发指令
                try {
                    System.out.println("发送电压指令："+command+"时间："+new Date());
                    channel.writeAndFlush(MsgUtil.convertStringToByteBuf(command.replace(" ","")));
                } catch (DecoderException e) {
                    log.error("电压读取命令下发报错："+e.getMessage());
                }
            }else{
                log.error(command+"指令下发失败，设备连接失败");
            }
        });

    }

    /**
     * 向所有PDU发送查询开关状态指令
     */
    public void sendReadSwitchStatusCmd() {

        ChannelManager.pduSerialMap.forEach((ip,serial) ->{
            if(serial == null){
                serial = ChannelManager.getNewSerial();
                ChannelManager.pduSerialMap.put(ip,serial);
            }
            String command = ModbusCmdGenerator.getReadCommand("", ModbusCmdType.SWITCH_STATUS,serial);
            Channel channel = ChannelManager.channelMap.get(ip);
            if(channel != null && channel.isActive()){
                //通道有效，下发指令
                try {
                    System.out.println("发送开关状态指令："+command+"时间："+new Date());
                    channel.writeAndFlush(MsgUtil.convertStringToByteBuf(command.replace(" ","")));
                } catch (DecoderException e) {
                    log.error("开关状态读取命令下发报错："+e.getMessage());
                }
            }else{
                log.error(command+"开关状态指令下发失败，设备连接失败");
            }
        });

    }


}
