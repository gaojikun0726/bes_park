package com.zc.netty.polelight.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


/**
 * @author xiepufeng
 */
public class MsgUtil
{

    private static final Logger log = LogManager.getLogger(MsgUtil.class);


    /**
     * ByteBuf 转 String
     * @param buf
     * @return
     */
    public static String convertByteBufToString(ByteBuf buf)
    {

        byte[] req = new byte[buf.readableBytes()];

        buf.readBytes(req);

        return Hex.encodeHexString(req);
    }


    /**
     * String(十六进制字符串) 转 ByteBuf
     * @param str
     * @return
     * @throws DecoderException
     */
    public static ByteBuf convertStringToByteBuf(String str) throws DecoderException
    {
        byte[] msgBytes = Hex.decodeHex(str.toCharArray());

        return Unpooled.buffer().writeBytes(msgBytes);
    }

    /**
     * @param: [content]
     * @return: int
     * @description: 十六进制转十进制
     */
    public static int hex2Decimal(String content){
        content = content.toUpperCase();
        int number=0;
        String [] HighLetter = {"A","B","C","D","E","F"};
        Map<String,Integer> map = new HashMap<>();
        for(int i = 0;i <= 9;i++){
            map.put(i+"",i);
        }
        for(int j= 10;j<HighLetter.length+10;j++){
            map.put(HighLetter[j-10],j);
        }
        String[]str = new String[content.length()];
        for(int i = 0; i < str.length; i++){
            str[i] = content.substring(i,i+1);
        }
        for(int i = 0; i < str.length; i++){
            number += map.get(str[i])*Math.pow(16,str.length-1-i);
        }
        return number;
    }
}
