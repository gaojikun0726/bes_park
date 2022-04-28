package com.yc.netty.nettyServer.handle;

/**
 * @CkassName: StringUtil
 * @Author: YangChao
 * @Date: 2019/11/15 16:54
 * @Descruotuib: 字符串转换工具类
 * @Version: 1.0
 **/
public class StringUtil {


    /**
     * 字符串分割 - 2字节 分割
     */
    public static String[] StrToArrayTwo(String str){
        StringBuffer s1 = new StringBuffer(str);
        int index;
        for (index = 2; index < s1.length(); index += 3) {
            s1.insert(index, ',');
        }
        String[] array = s1.toString().split(",");

        return array;
    }

    /**
    * @Description: 16进制转10进制(无高低转换)
    * @author YangChao
    * @date 2019/11/15 17:11
    */
    public static String hexToDecimal(String hex) {
        if (hex.startsWith("0x")) {
            return String.valueOf(Integer.parseInt(hex.substring(2), 16));
        } else {
            return String.valueOf(Integer.parseInt(hex, 16));
        }
    }

    /**
     * 将16进制转换为2进制
     *
     * @param hexString
     * @return
     */
    public static String hex2Binary(String hexString) {
        if (StringUtil.isNull(hexString))
            return null;
        long result = Long.parseLong(hexString, 16);
        return Long.toBinaryString(result);
    }

    /**
     * 判断字符串是否为空
     *
     * @param strs
     * @return
     */
    public static boolean isNull(String... strs) {
        for (String str : strs) {
            if (str == null || str.trim().length() <= 0)
                return true;
        }
        return false;
    }

    /**
     * 补位
     * @param data
     * @return
     */
    public static String add0forstr(String data) {
        while(data.length()<8) {
            StringBuffer sb = new StringBuffer();
            sb.append("0").append(data);// 左补0
            data = sb.toString();
        }
        return data;
    }

    /**
     * @Title:hexString2Bytes
     * @Description:16进制字符串转字节数组
     * @param src  16进制字符串
     * @return 字节数组
     */
    public static byte[] hexString2Bytes(String src) {
        int l = src.length() / 2;
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++) {
            ret[i] = (byte) Integer
                    .valueOf(src.substring(i * 2, i * 2 + 2), 16).byteValue();
        }
        return ret;
    }

    /**
     * @Description: 十六进制转字符串
     *
     */
    public static String hexStringToString(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }


}
