package com.core.common.util;

import com.core.common.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * 字符串工具类。
 * ClassName: StringUtil <br/>
 *
 * @author yangqichao
 * @since V1.0.0
 */
public class StringUtil extends org.apache.commons.lang3.StringUtils {

    /**
     * 日志操作对象。
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtil.class);

    /**
     * 检测字符串是否是空。 <br/>
     *
     * @param value 字符串参数
     * @return 如果字符串是null、空返回真，否则返回假
     * @author yangqichao
     * @since V1.0.0
     */
    public static boolean isEmpty(final String value) {
        return value == null || value.length() == 0;
    }

    /**
     * 检测字符串是否是空或空格字符。 <br/>
     *
     * @param value 字符串参数
     * @return 如果字符串是null、空或纯空格返回真，否则返回假
     * @author yangqichao
     * @since V1.0.0
     */
    public static boolean isEmptyOrBlank(final String value) {
        return value == null || value.trim().length() == 0;
    }

    /**
     * 对象转字符串。 <br/>
     *
     * @param obj 对象
     * @return 如果对象是null返回空，否则返回对象的字符串值。
     * @author yangqichao
     * @since V1.0.0
     */
    public static String obj2String(Object obj) {
        return (obj != null ? obj.toString()
                : "");
    }

    /**
     * 对象转字符串。 <br/>
     *
     * @param obj        对象
     * @param defaultStr 默认字符串值
     * @return 如果对象是null或空返回默认字符串值，否则返回对象的字符串值
     * @author yangqichao
     * @since V1.0.0
     */
    public static String obj2String(Object obj, String defaultStr) {
        return (obj == null ? defaultStr
                : ("".equals(obj.toString()) ? defaultStr
                : obj.toString()));
    }

    /**
     * 验证字符串数组是否包含指定字符串,完全匹配方式。 <br/>
     *
     * @param strArray 字符串数组
     * @param str      查找的字符串
     * @return 如果存在返回true，否则返回false。
     * @author yangqichao
     * @since V1.0.0
     */
    public static boolean contrian(String[] strArray, String str) {
        if (strArray == null || strArray.length < 0 || str == null || "".equals(str)) {
            return false;
        }
        for (String s : strArray) {
            if (str.equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 进行字符串前置零填充。 <br/>
     *
     * @param string 字符串
     * @param strlen 填充后的总长度
     * @return 零填充后返回值
     * @author yangqichao
     * @since V1.0.0
     */
    public static String fillZero(String string, int strlen) {
        String zero = "";
        string = obj2String(string);
        if (string.length() < strlen) {
            for (int i = 0; i < (strlen - string.length()); i++) {
                zero += "0";
            }
        }
        return (zero + string);
    }

    /**
     * 中英文字符串截取,UTF8编码。 <br/>
     *
     * @param text      源字符串
     * @param length    截取长度，一个中文字符长度为三
     * @param appendStr 未全部截取后末尾追加的字符串
     * @return 截取结果
     * @author yangqichao
     * @since V1.0.0
     */
    public static String subStringUtf8(String text, int length, String appendStr) {
        int textLength = text.length();
        int byteLength = 0;
        StringBuffer returnStr = new StringBuffer();
        for (int i = 0; i < textLength && byteLength < length; i++) {
            String strTmp = text.substring(i, i + 1);
            if (strTmp.getBytes().length == 1) {
                // 英文
                byteLength++;
            } else {
                // 中文
                byteLength += Constants.THREE;
            }
            returnStr.append(strTmp);
        }
        try {
            if (byteLength < text.getBytes("UTF-8").length) {
                returnStr.append(appendStr);
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.warn(e.getMessage());
        }
        return returnStr.toString();
    }

    /**
     * 字符串命名格式转换："test_test_test"格式转为"TestTestTest"。 <br/>
     *
     * @param string 源字符串
     * @return 格式化的字符串结果
     * @author yangqichao
     * @since V1.0.0
     */
    public static String capitalizeFormat(String string) {
        if (isEmpty(string)) {
            return string;
        }
        StringBuffer strBuffer = new StringBuffer();
        String[] fieldStr = string.toLowerCase().split("_");
        for (int i = 0; i < fieldStr.length; i++) {
            strBuffer.append(changeFirstCharacterCaseField(fieldStr[i], true));
        }
        return strBuffer.toString();
    }

    /**
     * 首字母大小写转换。 <br/>
     *
     * @param string     源字符串
     * @param capitalize true则转换大写，否则转换为小写
     * @return 转换结果
     * @author yangqichao
     * @since V1.0.0
     */
    public static String changeFirstCharacterCaseField(String string, boolean capitalize) {
        if (string == null || string.length() < 1) {
            return string;
        }
        StringBuffer strBuffer = new StringBuffer(string.length());
        strBuffer.append(capitalize ? Character.toUpperCase(string.charAt(0))
                : Character.toLowerCase(string.charAt(0)));
        strBuffer.append(string.substring(1));
        return strBuffer.toString();
    }
}