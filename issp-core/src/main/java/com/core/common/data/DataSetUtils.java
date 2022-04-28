package com.core.common.data;

import com.core.common.util.StringFunction;
import com.mongodb.client.MongoCursor;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.bson.Document;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.Comparator;
import java.util.Set;

/**
 * ResultSet转成数据集 如：ISSPDataSet
 * 
 * @author Alvin
 *
 */
public class DataSetUtils {

	protected DataSetUtils() {

	}

	/**
	 *
	 * @param resultSet
	 *            ResultSet
	 * @return String[]
	 * @throws Exception
	 */
	public static String[] getColNames(ResultSet resultSet) throws Exception {
		ResultSetMetaData rmd = resultSet.getMetaData();
		int colCount = rmd.getColumnCount();
		String[] colNames = new String[colCount];
		for (int i = 0; i < colCount; i++) {
			colNames[i] = rmd.getColumnName(i + 1);
		}
		return colNames;
	}

	/**
	 *
	 * @param resultSet
	 *            ResultSet
	 * @param dataSet
	 *            ISSPDataSet
	 * @return ISSPDataSet
	 * @throws Exception
	 */
	public static ISSPDataSet resultSet2DataSet(ResultSet resultSet,
			ISSPDataSet dataSet) throws Exception {
		String[] colNames = getColNames(resultSet);
		return resultSet2DataSet(resultSet, dataSet, colNames);
	}
	
	/**
	 *
	 * @param resultSet
	 *            ResultSet
	 * @param dataSet
	 *            ISSPDataSet
	 * @return ISSPDataSet
	 * @throws Exception
	 */
	public static ISSPDataSet resultSet2DataSet(ResultSet resultSet,
			ISSPDataSet dataSet, String[] colNames) throws Exception {
		return resultSet2DataSet(resultSet, dataSet, true, colNames);
	}

	/**
	 *
	 * @param resultSet
	 *            ResultSet
	 * @param dataSet
	 *            ISSPDataSet
	 * @param btrim
	 *            boolean
	 * @return ISSPDataSet
	 * @throws Exception
	 */
	public static ISSPDataSet resultSet2DataSet(ResultSet resultSet,
			ISSPDataSet dataSet, boolean btrim) throws Exception {
		String[] colNames = getColNames(resultSet);
		return resultSet2DataSet(resultSet, dataSet, btrim, colNames);
	}

	/**
	 * 数据集ResultSet转ISSPDataSet
	 * 
	 * @param resultSet
	 * @param dataSet
	 * @param btrim
	 * @param colNames
	 * @return
	 * @throws Exception
	 */
	public static ISSPDataSet resultSet2DataSet(ResultSet resultSet,
			ISSPDataSet dataSet, boolean btrim, String[] colNames)
			throws Exception {
		ISPRowSet rowSet = null;
		while (resultSet.next()) {
			// 生成ISPRowSet
			rowSet = ISSPRowSet.getInstance();
			// 将ResultSet中的一行生成到RowSet
			rowSet = resultSet2RowSet(resultSet, rowSet, btrim, colNames);
			// 插入DataSet中
			dataSet.insertRowSet(rowSet);
			// 形成主键索引
			dataSet.buildPrimeKeyIndex(rowSet);
		}
		return dataSet;
	}

	/**
	 *
	 * @param resultSet
	 *            ResultSet
	 * @param dataSet
	 *            ISSPDataSet
	 * @param btrim
	 *            boolean
	 * @param unique
	 *            boolean
	 * @return ISSPDataSet
	 * @throws Exception
	 */
	public static ISSPDataSet resultSet2DataSet(ResultSet resultSet,
			ISSPDataSet dataSet, boolean btrim, boolean unique)
			throws Exception {
		String[] colNames = getColNames(resultSet);
		return resultSet2DataSet(resultSet, dataSet, btrim, unique, colNames);
	}

	/**
	 *
	 * @param resultSet
	 *            ResultSet
	 * @param dataSet
	 *            ISSPDataSet
	 * @param btrim
	 *            boolean
	 * @param unique
	 *            boolean
	 * @return ISSPDataSet
	 * @throws Exception
	 */
	public static ISSPDataSet resultSet2DataSet(ResultSet resultSet,
			ISSPDataSet dataSet, boolean btrim, boolean unique,
			String[] colNames) throws Exception {
		ISPRowSet rowSet = null;
		while (resultSet.next()) {
			// 生成ISPRowSet
			rowSet = ISSPRowSet.getInstance();
			// 将ResultSet中的一行生成到RowSet
			rowSet = resultSet2RowSet(resultSet, rowSet, btrim, colNames);
			// 插入DataSet中
			dataSet.insertRowSet(rowSet, unique);
			// 形成主键索引
			dataSet.buildPrimeKeyIndex(rowSet);
		}
		return dataSet;
	}

	/**
	 *
	 * @param resultSet
	 *            ResultSet
	 * @param rowSet
	 *            ISPRowSet
	 * @param btrim
	 *            boolean
	 * @return ISPRowSet
	 * @throws Exception
	 */
	public static ISPRowSet resultSet2RowSet(ResultSet resultSet,
			ISPRowSet rowSet, boolean btrim) throws Exception {
		String[] colNames = getColNames(resultSet);
		return resultSet2RowSet(resultSet, rowSet, btrim, colNames);
	}

	/**
	 * 转RowSet
	 * 
	 * @param resultSet
	 *            ResultSet
	 * @param rowSet
	 *            ISPRowSet
	 * @param btrim
	 *            boolean
	 * @param colNames
	 * @return ISPRowSet
	 * @throws Exception
	 */
	public static ISPRowSet resultSet2RowSet(ResultSet resultSet,
			ISPRowSet rowSet, boolean btrim, String[] colNames)
			throws Exception {
		if (colNames == null || colNames.length == 0) {
			colNames = getColNames(resultSet);
		}
		// 不动原来的方法
		if (btrim) {
			return resultSet2RowSet(resultSet, rowSet, colNames);
		}
		String colName = null;
		Object value;
		for (int i = 0; i < colNames.length; i++) {
			colName = colNames[i];
			value = resultSet.getObject(colName);
			if (value instanceof Blob) {
				value = getBlogData(resultSet, colName);
			} else if (value instanceof Clob) {
				value = getClobData(resultSet, colName);
			} else if (value instanceof Date) {
				Timestamp timestamp = resultSet.getTimestamp(colName);
				value = timestamp;
			}
			if (value instanceof Timestamp) {
				long timestamp = ((Timestamp) value).getTime();// timestampValue();
				value = timestamp;
			}

			if (value != null && value instanceof String) {
				rowSet.putString(colName, (String) value, btrim);
			} else if (value != null) {
				rowSet.putObject(colName, value);
			}
		}
		rowSet.setDataStatus(ISPRowSet._DATA_STATUS_NORMAL_);
		return rowSet;
	}

	public static ISPRowSet resultSet2RowSet(ResultSet resultSet,
			ISPRowSet rowSet) throws Exception {
		String[] colNames = getColNames(resultSet);
		return resultSet2RowSet(resultSet, rowSet, colNames);
	}

	/**
	 * ResultSet转换ISPRowSet
	 * 
	 * @param resultSet
	 *            ResultSet
	 * @param rowSet
	 *            ISPRowSet
	 * @param colNames
	 *            String[]
	 * @return ISPRowSet
	 * @throws Exception
	 */
	public static ISPRowSet resultSet2RowSet(ResultSet resultSet,
			ISPRowSet rowSet, String[] colNames) throws Exception {
		if (colNames == null || colNames.length == 0) {
			colNames = getColNames(resultSet);
		}
		String colName = null;
		Object value;
		for (int i = 0; i < colNames.length; i++) {
			colName = colNames[i];
			value = resultSet.getObject(colName);
			if (value instanceof Blob) {
				value = getBlogData(resultSet, colName);
			} else if (value instanceof Clob) {
				value = getClobData(resultSet, colName);
			} else if (value instanceof Date) {
				Timestamp timestamp = resultSet.getTimestamp(colName);
				value = timestamp;
			}
			if (value instanceof Timestamp) {
				long timestamp = ((Timestamp) value).getTime();// timestampValue();
				value = timestamp;
			}
			if (value != null) {
				rowSet.putObject(colName, value);
			}
		}
		/**
		 *
		 */
		rowSet.setDataStatus(ISPRowSet._DATA_STATUS_NORMAL_);
		return rowSet;
	}

	/**
	 * 对一个ISSPDataSet排序，可以指定一列或者多列以及每列进行升序或者降序
	 * 
	 * @param ds
	 *            ISSPDataSet
	 * @param column
	 *            String[]
	 * @param ascending
	 *            boolean[]
	 * @param compar
	 *            Object[]
	 */
	public static void sortISSPDataSet(ISSPDataSet ds, String[] column,
			boolean[] ascending, Object[] compar) {
		ComparatorChain chain = new ComparatorChain();
		for (int i = 0; i < column.length; i++) {
			boolean ascend = true;
			if (ascending == null)
				ascend = true;
			else
				ascend = ascending[i];
			Comparator cp = null;
			if (compar != null && compar.length > i)
				cp = (Comparator) compar[i];
			ISSPRowSetComparator comparator = new ISSPRowSetComparator(
					column[i], ascend, cp);
			chain.addComparator(comparator);
		}
		// 一个比较器也没有，直接返回
		if (chain.size() == 0 || ds.getRowCount() == 0)
			return;
		Object[] rss = ds.getRowSetList().toArray();
		java.util.Arrays.sort(rss, chain);
		// 把排序后的RowSet赋回去
		ds.getRowSetList().clear();
		for (int i = 0; i < rss.length; i++) {
			ISSPRowSet rs = (ISSPRowSet) rss[i];
			ds.addRowSet(rs);
		}
	}

	public static void sortISSPDataSet(ISSPDataSet ds, String[] column,
			boolean[] ascending) {
		sortISSPDataSet(ds, column, ascending, null);
	}

	/**
	 * 恢复每个RowSet在某列排序前的索引
	 *
	 * @param ds
	 *            ISSPDataSet
	 * @param column
	 *            String
	 */
	public static void recoverRowSetIndex(ISSPDataSet ds, String column) {
		String[] columns = new String[] { column + "_ROWID" };
		sortISSPDataSet(ds, columns, null);
	}

	/**
	 * 设置每个RowSet在某列排序前的索引
	 *
	 * @param ds
	 *            ISSPDataSet
	 * @param column
	 *            String
	 */
	public static void setRowSetIndex(ISSPDataSet ds, String column) {
		java.util.List rowSets = ds.getRowSetList();
		if (rowSets == null)
			return;
		for (int i = 0; i < ds.getRowCount(); i++) {
			ISSPRowSet rs = ds.getRowSet(i);
			String rowid = StringFunction.FillZeroFromBegin(i, 10); // 10位长度，很给力了
			rs.putString(column + "_ROWID", rowid);
		}
	}

	/**
	 * 并集
	 * 
	 * @param lists
	 *            List
	 * @return List
	 */
	public static java.util.List unionList(java.util.List<java.util.List> lists) {
		java.util.Set hashSet = new java.util.HashSet();
		java.util.List list = null;
		for (int i = 0; lists != null && i < lists.size(); i++) {
			list = lists.get(i);
			if (list == null)
				continue;
			hashSet.addAll(list);
		}
		java.util.List result = new java.util.ArrayList();
		result.addAll(hashSet);
		return result;
	}

	/**
	 * 交集
	 * 
	 * @param lists
	 *            List
	 * @return List
	 */
	public static java.util.List intersectList(
			java.util.List<java.util.List> lists) {
		java.util.List unionList = unionList(lists);
		java.util.List result = new java.util.ArrayList();
		java.util.List list = null;
		Object value = null;
		for (int i = 0; unionList != null && i < unionList.size(); i++) {
			value = unionList.get(i);
			boolean exists = true;
			for (int j = 0; lists != null && j < lists.size(); j++) {
				list = lists.get(j);
				if (!list.contains(value)) {
					exists = false;
					break;
				}
			}
			if (exists)
				result.add(value);
		}
		return result;
	}

	public static Object getBlogData(ResultSet rs, String BlobField)
			throws Exception {
		ByteArrayOutputStream bout = null;
		Blob blob = null;
		blob = rs.getBlob(BlobField);
		if (blob != null) {
			InputStream is = blob.getBinaryStream();
			// int Length = ( (BLOB) blob).getBufferSize();
			int Length = 0;
			Class clazz = blob.getClass();
			Method method = clazz.getMethod("getBufferSize", new Class[] {});
			Object object = method.invoke(blob, new Object[] {});
			if (object != null && object instanceof Integer) {
				Length = ((Integer) object).intValue();
			} else {
				Length = (int) blob.length();
			}
			byte[] data = new byte[Length];
			int readLength = 0;
			while ((readLength = is.read(data)) > 0) {
				if (bout == null)
					bout = new ByteArrayOutputStream();
				bout.write(data, 0, readLength);
			}
			if (bout != null) {
				data = bout.toByteArray();
				bout.close();
			}
			return data;
		}
		return null;
	}

	public static Object getClobData(ResultSet rs, String ClobField)
			throws Exception {
		Clob clob = null;
		clob = rs.getClob(ClobField);
		String detailInfo = "";
		if (clob != null) {
			detailInfo = clob.getSubString((long) 1, (int) clob.length());
		}
		return detailInfo;
	}

	/**
	 * 备份列数据 
	 * 
	 * @param rowSet
	 * @param colNames
	 * @throws Exception
	 */
	public static void resultSet2RowSetBak(ISSPRowSet rowSet, String[] colNames)
			throws Exception {
		for (int i = 0; i < colNames.length; i++) {
			rowSet.putObjectBak(colNames[i],
					rowSet.getObject(colNames[i], null));
		}
	}
	/**
	 * MongoCursor<Document> 转ISSPDataSet
	 * @param cursor
	 * @param dataSet
	 * @return
	 * @throws Exception
	 */
	public static ISSPDataSet mongoCursor2DataSet(MongoCursor<Document> cursor, ISSPDataSet dataSet) throws Exception {
		ISPRowSet rowSet = null;
		while (cursor.hasNext()) {
			Document doc = cursor.next();
	        Set<String> keyList = doc.keySet();
	        String[] keyNames  = keyList.toArray(new String[keyList.size()]);
			// 生成ISPRowSet
			rowSet = ISSPRowSet.getInstance();
			// 将ResultSet中的一行生成到RowSet
			rowSet = mongoCursor2DataSeta(doc,rowSet,dataSet,keyNames);
			// 插入DataSet中
			dataSet.insertRowSet(rowSet);
			// 形成主键索引
			dataSet.buildPrimeKeyIndex(rowSet);
		}
		return dataSet;
	}
	/**
	 * 转RowSet
	 * @param doc
	 * @param rowSet
	 * @param dataSet
	 * @param keyNames
	 * @return
	 * @throws Exception
	 */
	public static ISPRowSet mongoCursor2DataSeta(Document doc, ISPRowSet rowSet, ISSPDataSet dataSet, String[] keyNames)
			throws Exception {
		String keyName = null;
		Object value;
		for (int i = 0; i < keyNames.length; i++) {
			keyName = keyNames[i];
			value = doc.get(keyName);
			if (value instanceof Timestamp) {
				long timestamp = ((Timestamp) value).getTime();// timestampValue();
				value = timestamp;
			}

			if (value != null && value instanceof String) {
				rowSet.putString(keyName, (String) value);
			} else if (value != null) {
				rowSet.putObject(keyName, value);
			}
		}
		rowSet.setDataStatus(ISPRowSet._DATA_STATUS_NORMAL_);
		return rowSet;
	}
}