package com.efounder.JEnterprise.zhdg.util;

/**
 * @CkassName: AgreeHandleUtil
 * @Author: YangChao
 * @Date: 2020/4/15 16:15
 * @Descruotuib: 协议操作处理工具类
 * @Version: 1.0
 **/
public class AgreeHandleUtil {

    /**
     * @Description: 默认不作处理, 返回十六进制
     */
    public static String Transformation0(String data, int start, int end) {
        return data.substring(start, end);
    }

    /**
     * @Description: 1.0---精度0.1，高低转换，十六转十
     */
    public static String Transformation1(String data, int start, int end) {
        data = precisionTurn(revSubstrTurn(data, start, end), 1);
        return data;
    }

    /**
     * @Description: 2.0---精度0.01，高低转换，十六转十
     */
    public static String Transformation2(String data, int start, int end) {
        data = precisionTurn(revSubstrTurn(data, start, end), 2);
        return data;
    }

    /**
     * @Description: 3.0---精度0.001，高低转换，十六转十
     */
    public static String Transformation3(String data, int start, int end) {
        data = precisionTurn(revSubstrTurn(data, start, end), 3);
        return data;
    }

    /**
     * @Description: 4.0---精度0.0001，高低转换，十六转十
     */
    public static String Transformation4(String data, int start, int end) {
        data = precisionTurn(revSubstrTurn(data, start, end), 4);
        return data;
    }

    /**
     * @Description: 5.0---十六转十
     */
    public static String Transformation5(String data, int start, int end) {
        data = substrTurn(data, start, end);
        return data;
    }

    /**
     * @Description: 6.0---十六转二，不足八位补足八位
     */
    public static String Transformation6(String data, int start, int end) {
        data = add0forstr(RadixUtil.hex2Binary(data.substring(start, end)));
        return data;
    }

    /**
     * @Description: 7.0---高低转换，十六转十
     */
    public static String Transformation7(String data, int start, int end) {
        data = revSubstrTurn(data, start, end);
        return data;
    }

    /**
     * @Description: 8.0---协议位数据截取
     */
    public static String Transformation8(String data, int start, int end, int position) {
        data = Transformation6(data,start,end);
        data = data.substring(8 - position, 9 - position);
        return data;
    }

    /**
     * @Description: 9.0---十六转二，不足十六位补足十六位
     */
    public static String Transformation9(String data, int start, int end) {
        data = add16forstr(RadixUtil.hex2Binary(data.substring(start, end)));
        return data;
    }

    /**
     * @Description: 10.0---高低转换，十六转float
     */
    public static String Transformation10(String data, int start, int end) {
        String aa = RadixUtil.lowOrHighReverse(data.substring(start, end));// 高低转换
        Float value = Float.intBitsToFloat(Integer.valueOf(aa.trim(), 16));
        return String.valueOf(value);
    }


    /**
     * 参数截取，高低转换，16转10
     */
    public static String revSubstrTurn(String data, int start, int end) {
        return RadixUtil.hexToDecimal(RadixUtil.lowOrHighReverse(data.substring(start, end)));
    }

    public static void main(String[] args) {
        String a = "7B14B641";
        String aa = RadixUtil.lowOrHighReverse(a);// 高低转换
        RadixUtil.hexToDecimal(aa);// 十六转十进制

        Float value = Float.intBitsToFloat(Integer.valueOf(aa.trim(), 16));
        System.out.println(value);
        System.err.println(aa);
    }

    /**
     * 高低转换 低位在前=》高位在前
     * 十六进制转字符串
     * */
    public static String hexStringToString(String hexStr) {
        hexStr = RadixUtil.lowOrHighReverse(hexStr);
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

    /**
     * 精度
     */
    public static String precisionTurn(String data, int precision) {
        double real = 0.0;
        int rawdata = Integer.parseInt(data);
        real = rawdata / (Math.pow(10, (precision)));
        String realData = String.valueOf(real);
        return realData;
    }

    /**
     * 参数截取，16转10
     */
    public static String substrTurn(String data, int start, int end) {
        return RadixUtil.hexToDecimal(data.substring(start, end));
    }

    /**
     * 补8位
     */
    public static String add0forstr(String data) {
        while (data.length() < 8) {
            StringBuffer sb = new StringBuffer();
            sb.append("0").append(data);// 左补0
            data = sb.toString();
        }
        return data;
    }

    /**
     * 补16位
     */
    public static String add16forstr(String data) {
        while (data.length() < 16) {
            StringBuffer sb = new StringBuffer();
            sb.append("0").append(data);// 左补0
            data = sb.toString();
        }
        return data;
    }

    /**
     * @Description: 补位
     */
    public static String add20forstr(String data) {
        while (data.length() < 2) {
            StringBuffer sb = new StringBuffer();
            sb.append("0").append(data);// 左补0
            data = sb.toString();
        }
        return data;
    }

    /**
     * @Description: 4字节补位-高低转换
     * demo:5ed5b7a7---a7b7d55e
     */
    public static String add32forstr(String data) {
        data = RadixUtil.lowOrHighReverse(Integer.toHexString(Integer.parseInt(data)));//高低转换
        while (data.length() < 32) {
            StringBuffer sb = new StringBuffer();
            sb.append(data).append("0");// 左补0
            data = sb.toString();
        }
        return data;
    }


}
