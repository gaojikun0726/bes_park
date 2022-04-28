package com.core.common.data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public interface ISPRowSet extends Serializable, KeyValue {
	
	public static final String _dependDCT_ITEM_ = "_dependDCT_ITEM_";
	public static final String _dependMDCT_ITEM_ = "_dependMDCT_ITEM_";

	/**
	 * 正常状态
	 */
	public static final int _DATA_STATUS_NORMAL_ = 0x0000;
	/**
	 * 更新状态
	 */
	public static final int _DATA_STATUS_UPDATE_ = 0x0001;
	/**
	 * 插入状态
	 */
	public static final int _DATA_STATUS_INSERT_ = 0x0002;
	/**
	 * 子数据集有修改
	 */
	public static final int _DATA_STATUS_CHILD_ = 0x0004;

	/**
	 *
	 * @param o
	 *            Object
	 */
	public void setMasterKey(Object o);

	/**
	 *
	 * @param key
	 *            String
	 * @param def
	 *            double
	 * @return double
	 */
	public int getInt(String key, int def);

	/**
	 *
	 * @param key
	 *            String
	 * @param def
	 *            double
	 * @return double
	 */
	public double getDouble(String key, double def);

	/**
	 *
	 * @param keyName
	 *            String
	 * @param value
	 *            Number
	 */
	public void putNumber(String keyName, Number value);

	/**
	 *
	 * @param keyName
	 * @param defValue
	 * @return Number
	 */
	public Number getNumber(String keyName, Number defValue);

	/**
	 *
	 * @param keyName
	 * @param value
	 */
	public void putString(String keyName, String value);

	/**
	 *
	 * @param keyName
	 *            String
	 * @param value
	 *            String
	 * @param btrim
	 *            boolean
	 */
	public void putString(String keyName, String value, boolean btrim);

	/**
	 *
	 * @param keyName
	 * @param defValue
	 * @return String
	 */
	public String getString(String keyName, String defValue);

	/**
	 *
	 * @param keyName
	 *            String
	 * @param defValue
	 *            String
	 * @return String
	 */
	public String getStringByTrim(String keyName, String defValue);

	/**
	 *
	 * @param keyName
	 * @param value
	 */
	public void putBoolean(String keyName, Boolean value);

	/**
	 *
	 * @param keyName
	 * @param defValue
	 * @return Boolean
	 */
	public Boolean getBoolean(String keyName, Boolean defValue);

	/**
	 *
	 * @param keyName
	 * @param value
	 */
	public void putDate(String keyName, Date value);

	/**
	 *
	 * @param keyName
	 * @param defValue
	 * @return Date
	 */
	public Date getDate(String keyName, Date defValue);

	/**
	 *
	 * @param keyName
	 * @param value
	 */
	public void putObject(String keyName, Object value);

	/**
	 * 
	 * @param keyName
	 * @param value
	 * @param isTrim
	 *            存储前是否进行trim处理
	 */
	public void putObject(String keyName, Object value, boolean isTrim);

	/**
	 *
	 * @param keyName
	 * @param defValue
	 * @return Object
	 */
	public Object getObject(String keyName, Object defValue);

	/**
	 *
	 * @param dsType
	 *            String
	 * @return ISSPDataSet
	 */
	public ISSPDataSet getDataSet(String dsType);

	/**
	 *
	 * @param dsType
	 *            String
	 * @param dataSet
	 *            ISSPDataSet
	 */
	public void setDataSet(String dsType, ISSPDataSet dataSet);

	/**
	 *
	 * @return String[]
	 */
	public String[] getDSTypes();

	/**
	 *
	 * @param dsType
	 *            String
	 */
	public ISSPDataSet removeDataSet(String dsType);

	/**
	 *
	 * @param dataSet
	 *            ISSPDataSet
	 */
	public void insertDataSetManager(ISSPDataSet dataSet);

	/**
	 *
	 * @param dataSet
	 *            ISSPDataSet
	 */
	public void removeDataSetManager(ISSPDataSet dataSet);

	/**
	 * getID2Name
	 * 
	 * @param ID
	 *            String
	 * @param defName
	 *            String
	 * @return String
	 */
	public String getID2Name(String ColID, String ID, String defName);

	/**
	 *
	 * @param DCT_ID
	 *            String
	 * @param COL_ID
	 *            String
	 * @return ISPRowSet
	 */
	public ISPRowSet getID2RowSet(String DCT_ID, String COL_ID);

	/**
	 * 获取名称
	 * 
	 * @param ColID
	 *            String
	 * @param ID
	 *            String
	 * @param Name
	 *            String
	 */
	public void setID2Name(String ColID, String ID, String Name);

	/**
	 *
	 * @param DCT_ID
	 *            String
	 * @param COL_ID
	 *            String
	 * @param rowSet
	 *            ISPRowSet
	 */
	public void setID2RowSet(String DCT_ID, String COL_ID, ISPRowSet rowSet);

	/**
	 *
	 * @param DCT_ID
	 *            String
	 * @param COL_ID
	 *            String
	 * @param rowSet
	 *            ISPRowSet
	 */
	public void setID2RowSetFront(String DCT_ID, String COL_ID, ISPRowSet rowSet);

	/**
	 *
	 * @return int
	 */
	public int getDataStatus();

	/**
	 *
	 * @param dataStatus
	 *            int
	 */
	public void setDataStatus(int dataStatus);

	/**
	 *
	 * @return ISPRowSet
	 */
	public ISPRowSet getPrior();

	/**
	 *
	 * @param rowSet
	 *            ISPRowSet
	 */
	public void setPrior(ISPRowSet rowSet);

	/**
	 *
	 * @return ISPRowSet
	 */
	public ISPRowSet getNext();

	/**
	 *
	 * @param rowSet
	 *            ISPRowSet
	 */
	public void setNext(ISPRowSet rowSet);

	/**
	 *
	 * @return ISPRowSet
	 */
	public ISPRowSet getFirst();

	/**
	 *
	 * @return ISPRowSet
	 */
	public ISPRowSet getLast();

	/**
	 *
	 * @param key
	 *            Object
	 * @param value
	 *            Object
	 */
	public void setExtProperty(Object key, Object value);

	/**
	 *
	 * @param key
	 *            Object
	 * @param def
	 *            Object
	 * @return Object
	 */
	public Object getExtProperty(Object key, Object def);

	/**
	 *
	 * @return Map
	 */
	public java.util.Map getExtPropertys();

	/**
	 *
	 * @param extMap
	 *            Map
	 */
	public void setExtPropertys(java.util.Map extMap);

	/**
	 *
	 * @return int
	 */
	public int getRowSetAppType();

	/**
	 *
	 * @param rowSetType
	 *            int
	 */
	public void setRowSetAppType(int rowSetType);

	/**
	 *
	 * @param key
	 *            String
	 * @param rowSet
	 *            ISPRowSet
	 */
	public void setExtRowSet(String key, ISPRowSet rowSet);

	/**
	 *
	 * @param key
	 *            String
	 * @param def
	 *            ISPRowSet
	 * @return ISPRowSet
	 */
	public ISPRowSet getExtRowSet(String key, ISPRowSet def);

	/**
	 *
	 * @param col
	 *            String
	 * @return String
	 */
	public String getRowColFormula(String col);

	/**
	 *
	 * @param col
	 *            String
	 * @param f1
	 *            String
	 */
	public void setRowColFormula(String col, String f1);

	/**
	 *
	 * @param f1
	 *            String
	 */
	public void setRowFormula(String f1);

	/**
	 *
	 * @return int
	 */
	public int getLevel();

	/**
	 *
	 * @param level
	 *            int
	 */
	public void setLevel(int level);

	/**
	 * 
	 * @return ISSPDataSet
	 */
	public ISSPDataSet getDataSet();

	/**
	 *
	 * @param ds
	 *            ISSPDataSet
	 */
	public void setDataSet(ISSPDataSet ds);

	/**
	 * 所属主表ID
	 * 
	 * @param tableName
	 */
	public void setMasterTableName(String tableName);

	public String getMasterTableName();

	/**
	 * 数据备份
	 * 
	 * @return
	 */
	public Map<String, Object> getDataMapBak();

	public void setDataMapBak(Map dataMap);

}
