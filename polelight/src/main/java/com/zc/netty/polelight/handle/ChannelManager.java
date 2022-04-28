package com.zc.netty.polelight.handle;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * describe: channel管理
 *
 * @author zs
 * @date 2021/11/19
 */
public class ChannelManager {


    /**
     * 存储channel
     */
   public static Map<String, Channel> channelMap = new ConcurrentHashMap<>();


    /**
     * 指令序列号记录
     */
    public static int serialCount = 0;


    /**
     * 关联PDU IP和指令序列号，方便区分指令返回数据属于哪个PDU
     */
    public static Map<String,Integer> pduSerialMap = new ConcurrentHashMap<>();


    /**
     * 获取最新的序列号
     * @return
     */
    public static int getNewSerial(){
        return ++serialCount;
    }
}
