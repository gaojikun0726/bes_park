package com.zc.netty.polelight.util;

/**
 * describe:
 *
 * @author zs
 * @date 2021/2/6
 */
public class StringUtil {

    /**
     * 字符串位数不足，前端补0
     * @return
     */
    public static String fillStringWithZero(String str,int length){
        int len = str.length();
        if(len >= length){
            //长度满足或已超出
            return str;
        }
        int temp = length - len;
        StringBuffer s = new StringBuffer();
        for(int i = 0; i < temp; i++){
            s.append("0");
        }
        s.append(str);
        return s.toString();
    }
}
