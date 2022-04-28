package com.core.common.data;

import com.core.common.util.JFNumber;
import org.apache.commons.collections.comparators.ComparatorChain;

import java.text.SimpleDateFormat;
import java.util.Arrays;

public class RowSetUtils {

	/**
	 * 获取ISSPRowSet的DataMap中的所有的key
	 *
	 * @param rowSet
	 *            ISSPRowSet
	 * @return String[]
	 */
	public static String[] getKeys(ISSPRowSet rowSet) {
		if (rowSet == null || rowSet.getDataMap() == null)
			return null;
		Object[] keys = rowSet.getDataMap().keySet().toArray();
		String[] key2 = new String[keys.length];
		System.arraycopy(keys, 0, key2, 0, key2.length);
		return key2;
	}

	/**
	 *
	 * @param rowSet
	 *            ISSPRowSet
	 * @param prefix
	 *            String 前缀
	 * @param key
	 *            String 关键字
	 * @param dateFormat
	 *            String 日期型的格式
	 * @param scale
	 *            int 数值型的精度
	 * @return String
	 */
	public static String asString(ISSPRowSet rowSet, String prefix, String key,
			String dateFormat, int scale) {
		if (rowSet == null)
			return null;
		Object value = rowSet.getObject(key, null);
		if (value == null)
			value = rowSet.getObject(prefix + "." + key, null);
		if (value == null)
			return null;
		// 日期型
		if (value instanceof java.util.Date)
			return date2String((java.util.Date) value, dateFormat);
		// 数值型
		if (value instanceof Number)
			return number2String((Number) value, scale);
		// 其他情况，一视同仁
		return value.toString();
	}

	/**
	 *
	 * @param date
	 *            Date
	 * @param format
	 *            String
	 * @return String
	 */
	public static String date2String(java.util.Date date, String format) {
		if (date == null)
			return null;
		if (format == null || format.trim().length() == 0) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat formater = new SimpleDateFormat(format);
		return formater.format(date);
	}

	/**
	 *
	 * @param num
	 *            Number
	 * @param scale
	 *            int
	 * @return String
	 */
	public static String number2String(Number num, int scale) {
		if (num instanceof Integer)
			return String.valueOf(num.intValue());
		if (num instanceof Double)
			return String.valueOf(JFNumber.round(num.doubleValue(), scale));
		if (num instanceof java.math.BigDecimal)
			return String.valueOf(JFNumber.round(num.doubleValue(), scale));
		return num.toString();
	}

	/**
	 * 判断两个ISSPRowSet在多个关键字上是否都相等
	 *
	 * @param rs1
	 *            ISSPRowSet
	 * @param rs2
	 *            ISSPRowSet
	 * @param key
	 *            String[]
	 * @return boolean
	 */
	public static boolean equals(ISSPRowSet rs1, ISSPRowSet rs2, String[] key) {
		// 基于实现了Compare接口的对象的比较
		return 0 == compare(rs1, rs2, key);
	}

	/**
	 * 在多个关键字上比较两个ISSPRowSet
	 *
	 * @param rs1
	 *            ISSPRowSet
	 * @param rs2
	 *            ISSPRowSet
	 * @param key
	 *            String[]
	 * @return int
	 */
	public static int compare(ISSPRowSet rs1, ISSPRowSet rs2, String[] key) {
		ComparatorChain chain = new ComparatorChain();
		for (int i = 0; key != null && i < key.length; i++) {
			ISSPRowSetComparator comparator = new ISSPRowSetComparator(key[i], true);
			chain.addComparator(comparator);
		}
		if (chain.size() == 0)
			return 0;
		return chain.compare(rs1, rs2);
	}

	/**
	 * 复制rs1的数据到rs2，key为不参与复制的列
	 *
	 * @param rs1
	 *            ISSPRowSet
	 * @param rs2
	 *            ISSPRowSet
	 * @param key
	 *            String[]
	 */
	public static void copyValue(ISSPRowSet rs1, ISSPRowSet rs2, String[] key) {
		String[] allKeys = getKeys(rs1);
		for (int i = 0; allKeys != null && i < allKeys.length; i++) {
			// 排除不参与复制的值
			if (key != null && Arrays.asList(key).contains(allKeys[i]))
				continue;
			rs2.setValue(allKeys[i], rs1.getObject(allKeys[i], null));
		}
	}

	/**
	 *
	 * @param rowSet
	 *            ISSPRowSet
	 * @param prefix
	 *            String
	 * @param metaData
	 *            ISSPRowSet[]
	 * @param connector
	 *            String
	 * @param containKey
	 *            boolean
	 * @return String
	 */
	public static String getConnectString(ISSPRowSet rowSet, String prefix,
			ISSPRowSet[] metaData, String connector, boolean containKey) {
		return getConnectString(rowSet, prefix, metaData, connector, " = ",
				containKey);
	}

	/**
	 * 根据关键字的元数据、关键字前缀、ISSPRowSet以及连接符形成一个条件语句， 如1：F_DJBH = 'xxxx' and F_DATE =
	 * '201011' 如2：F_DJBH = 'xxxx' or F_DATE = '201011' 如3：F_DJBH = 'xxxx' ,
	 * F_DATE = '201011' 如4：
	 * 'xxxx','201010',3.22,to_timestamp('2010111012002111','yyyymmddhh24missff6
	 * ' 不考虑创建日期、修改日期，由调用者自行处理，比如如果是update，修改日期改为最新，去掉创建日期
	 * 如果是insert，创建日期、修改日期都置为当前时间
	 * 
	 * @param rowSet
	 *            ISSPRowSet
	 * @param prefix
	 *            String 关键字前缀
	 * @param metaData
	 *            ISSPRowSet[] 关键字的元数据
	 * @param connector
	 *            String 连接符
	 * @param connector1
	 *            String
	 * @param containKey
	 *            boolean 形成的语句中是否包含关键字
	 * @return String
	 */
	public static String getConnectString(ISSPRowSet rowSet, String prefix,
			ISSPRowSet[] metaData, String connector, String connector1,
			boolean containKey) {
		StringBuffer sb = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		for (int i = 0; metaData != null && i < metaData.length; i++) {
			// 列名
			String COL_ID = metaData[i].getString("COL_ID", "");
			// 如果是rowSet中没有这个key，则继续
			if (!Arrays.asList(getKeys(rowSet)).contains(COL_ID)
					&& !Arrays.asList(getKeys(rowSet)).contains(
							prefix + "." + COL_ID))
				continue;
			// 精度
			int decn = metaData[i].getInt("COL_SCALE", 2);
			if (decn == 0 || decn >= 6)
				decn = 2;
			// 允许空否
			String COL_ISNULL = metaData[i].getString("COL_ISNULL", "1");
			// 默认值
			String COL_DEFAULT = metaData[i].getString("COL_DEFAULT", "");
			if ("0".equals(COL_ISNULL) && COL_DEFAULT.length() == 0)
				COL_DEFAULT = " ";
			// 值为null则使用默认值
			String val = asString(rowSet, prefix, COL_ID, "yyyyMMddHHmmssSSS",
					decn);
			val = val == null || val.length() == 0 ? COL_DEFAULT : val;
			// 连接符
			if (sb.length() > 0)
				sb.append(connector);
			// 字符型
			if (metaData[i].getString("COL_TYPE", "C").equals("C")) {
				if (containKey)
					sb.append(COL_ID + connector1); // 包含关键字
				sb.append("'" + val + "'");
			}
			// 数值型
			if (metaData[i].getString("COL_TYPE", "C").equals("N")) {
				val = val == null || val.trim().length() == 0 ? "0.0" : val;
				if (containKey)
					sb.append(COL_ID + connector1); // 包含关键字
				sb.append(val);
			}
			// 整数型
			if (metaData[i].getString("COL_TYPE", "C").equals("I")) {
				val = val == null || val.trim().length() == 0 ? "0" : val;
				if (containKey)
					sb.append(COL_ID + connector1); // 包含关键字
				sb.append(val);
			}
			// 日期型
			if (metaData[i].getString("COL_TYPE", "C").equals("D")
					|| metaData[i].getString("COL_TYPE", "C").equals("T")) {
				val = val == null || val.trim().length() == 0 ? sdf
						.format(new java.util.Date()) : val;
				if (containKey)
					sb.append(COL_ID + connector1); // 包含关键字
				sb.append("to_timestamp('" + val + "','yyyymmddhh24missff6')");
			}
		}
		return sb.toString();
	}

	/**
	 *
	 * @param rs
	 *            ISSPRowSet
	 * @return String
	 */
	public static String toString(ISSPRowSet rs) {
		if (rs == null)
			return null;
		Object[] a = rs.getDataMap().keySet().toArray();
		StringBuffer s = new StringBuffer("[");
		for (int i = 0; a != null && i < a.length; i++) {
			s.append(a[i]).append("=")
					.append(rs.getObject((String) a[i], null)).append(";");
		}
		s.append("]");
		return s.toString();
	}

}