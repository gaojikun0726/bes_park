package com.zc.netty.polelight.util;

/**
 * describe: 数据进制转换
 *
 * @author zs
 * @date 2021/2/5
 */
public class NumberSystemConvert {

    /**
     * 转换16进制32位浮点数
     * @return
     */
    public static Float convertHexToFloat(String s){
        Float value = Float.intBitsToFloat(Integer.valueOf(s.replace(" ","").trim(), 16));
        return value;
    }

    /**
     * 转换16进制用1位表示的整数
     * @param s
     * @return
     */
    public static Integer convertHexToInteger(String s){
        Integer value = Integer.parseInt(s,16);
        return value;
    }

    /**
     * 16进制数据转成二进制字符串
     * @param s
     * @return
     */
    public static String convertHexToBit(String s){
        //8位数字表示的最大数为FF，255
        int num = Integer.parseInt(s,16);
        //Byte最大为127
//        Byte b = Byte.parseByte(s);
//        String result = Integer.toBinaryString((b & 0xFF) + 0x100).substring(1);
        String result = Integer.toBinaryString(num + 0x100).substring(1);
        return result;
    }
}
