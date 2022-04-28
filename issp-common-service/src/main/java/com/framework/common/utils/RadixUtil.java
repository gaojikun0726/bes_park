package com.framework.common.utils;

/**
 * 2进制、16进制、byte数组、GB2312码等之间的相互转换
 * 
 * @author Alvin
 * @date 2018年7月13日
 *
 */
public class RadixUtil {

	private RadixUtil() {

	}

	/**
	 * 将2进制转换为16进制。
	 * 
	 * @param binayString
	 * @return
	 */
	public static String binary2Hex(String binayString) {
		if (CommonUtil.isNull(binayString))
			return null;
		long result = Long.parseLong(binayString, 2);
		return Long.toHexString(result);
	}

	/**
	 * 将16进制转换为2进制
	 * 
	 * @param hexString
	 * @return
	 */
	public static String hex2Binary(String hexString) {
		if (CommonUtil.isNull(hexString))
			return null;
		long result = Long.parseLong(hexString, 16);
		return Long.toBinaryString(result);
	}

	/**
	 * 把一个十六进制的字符串数组，转化成byte数组
	 * 
	 * @param hexString
	 * @return
	 */
	public static byte[] hex2Byte(String[] hexString) {
		byte[] result = new byte[hexString.length];
		int count = 0;
		for (String s : hexString) {
			result[count] = ((byte) Integer.parseInt(s, 16));
			count++;
		}
		return result;
	}
	
	/**
	 * 把一个 十六进制的字符串转化成byte数组
	 *
	 * @param hexString
	 * @return
	 */
	public static byte[] hexToByte(String str) {
		String[] hexString = toStringArray(str);
		byte[] result = new byte[hexString.length];
		int count = 0;
		for (String s : hexString) {
			result[count] = ((byte) Integer.parseInt(s, 16));
			count++;
		}
		return result;
	}
	
	/**
	 * 把一个十六进制的字符串，转化成String数组
	 * 
	 * @param str
	 * @return
	 */
	public static String[] toStringArray(String str) {
		StringBuffer sb = new StringBuffer(str);
		int index;
		for (index = 2; index < sb.length(); index += 3) {
			sb.insert(index, ',');
		}
		return sb.toString().split(",");
	}
	
	/**
	 * 把一个十六进制的字符串 高低位互换
	 * @param str 以字节为单位的数字字符串(不包含0x和空格)
	 * @return
	 */
	public static String lowOrHighReverse(String str) {
		StringBuffer sb = new StringBuffer("");
		for (int i = str.length(); i > 0; i = i - 2) {
			sb.append(str.substring(i - 2, i));
		}
		return sb.toString();
	}
	
	/**
	 * 十六进制字符串转换成十进制字符串
	 * 
	 * @param hex
	 * @return
	 */
	public static String hexToDecimal(String hex) {
		if (hex.startsWith("0x")) {
			return String.valueOf(Integer.parseInt(hex.substring(2), 16));
		} else {
			return String.valueOf(Integer.parseInt(hex, 16));
		}
	}
	
	/**
	 * 将byte转化为十六进制字符串
	 * 
	 * @param bytes
	 * @return
	 */
	public static String byte2Hex(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (byte b : bytes) {
			sb.append(String.format("%x ", b));
		}
		return sb.toString().trim();
	}

	/**
	 * 将byte转化为十六进制字符串 长度为1的在前面追加0
	 * 
	 * @param bytes
	 * @return
	 */
	public static String byte2HexStr(byte[] b) {
		String stmp = "";
		StringBuilder sb = new StringBuilder("");
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0xFF);
			sb.append((stmp.length() == 1) ? "0" + stmp : stmp);
		}
		return sb.toString().toUpperCase().trim();
	}
	
	/**
	 * 将int类型转换为16进制
	 * 
	 * @param number
	 * @param byteNumber
	 * @return
	 */
	public static String int2Hex(int number, int byteNumber) {
		String result = Integer.toHexString(number);
		if (result.length() == 1) {
			if (byteNumber == 2)
				return "00 0" + result;
			return "0" + result;
		}
		if (result.length() == 2) {
			if (byteNumber == 2)
				return "00 " + result;
			return result;
		}
		if (result.length() == 3) {
			if (byteNumber == 1)
				return result.substring(1);
			result = "0" + result;
			return result.substring(0, 2) + " " + result.substring(2);
		}
		if (result.length() == 4) {
			if (byteNumber == 1)
				return result.substring(2);
			return result.substring(0, 2) + " " + result.substring(2);
		}
		if (result.length() > 4) {
			if (byteNumber == 1)
				return result.substring(result.length() - 2);
			return result.substring(result.length() - 4);
		}

		return null;
	}

	/**
	 * int类型转换为十六进制
	 * 
	 * @param number
	 * @return
	 */
	public static String int2Hex(int number) {
		return int2Hex(number, 1);
	}

	/**
	 * 将十六进制文本转化位ascii码
	 * 
	 * @param hexString
	 * @return
	 */
	public static char hexStringToAscii(String hexString) {
		if (CommonUtil.isNull(hexString))
			return 0;
		int result = Integer.parseInt(hexString, 16);
		return (char) result;
	}

	/**
	 * 将汉字的GB2312/GBK码转化为汉字
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String gb2312ToWord(String str) throws Exception {
		String result = "";
		if (CommonUtil.isNull(str))
			return result;
		str = str.replaceAll(" ", "");
		byte[] bytes = new byte[str.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			byte high = Byte.parseByte(str.substring(i * 2, i * 2 + 1), 16);
			byte low = Byte.parseByte(str.substring(i * 2 + 1, i * 2 + 2), 16);
			bytes[i] = (byte) (high << 4 | low);
		}
		if (bytes.length > 0) {
			result = new String(bytes, "gbk");
		}

		return result;
	}

	/**
	 * 将汉字转化为GB2312/GBK码
	 * 
	 * @param word
	 * @return
	 * @throws Exception
	 */
	public static String wordToGb2312(String word) throws Exception {
		byte[] gb2312 = word.getBytes("GB2312");
		return RadixUtil.byte2Hex(gb2312);
	}

	/**
	 * String 转 HEX ASCII码
	 * 
	 * @param str
	 * @return
	 */
	public static String String2Hex(String str) {
		char[] chars = str.toCharArray();
		StringBuffer hex = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			hex.append(Integer.toHexString((int) chars[i]));
		}
		return hex.toString();
	}

	/**
	 * HEX ASCII码 转 String
	 * 
	 * @param str
	 * @return
	 */
	public static String Hex2String(String hex) {
		StringBuilder sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < hex.length() - 1; i += 2) {
			String output = hex.substring(i, (i + 2));
			int decimal = Integer.parseInt(output, 16);
			sb.append((char) decimal);
			temp.append(decimal);
		}
		return sb.toString().trim();
	}

}