package com.core.common.data;

import com.core.common.util.MapUtil;

import javax.swing.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * 行数据对象，用于行数据存储
 * 
 * @author Alvin
 *
 */
public class ISSPRowSet implements ISPRowSet, java.io.Serializable, KeyValue {

	public static final String _CHILD_DATA_SET_ = "_CDS_";
	/**
	 * 前台ID2NameMap
	 */
	protected transient java.util.Map frontID2NameMap = null;

	/**
	 * 获取前台ID2NameMap
	 * 
	 * @return Map
	 */
	public java.util.Map getFrontID2NameMap() {
		return frontID2NameMap;
	}

	/**
	 * 设置前台ID2NameMap
	 * 
	 * @param fi2n
	 *            Map
	 */
	public void setFrontID2NameMap(java.util.Map fi2n) {
		frontID2NameMap = fi2n;
	}

	/**
	 * 前台ID2NameMap
	 */
	protected java.util.Map backID2NameMap = null;

	/**
	 * 获取前台ID2NameMap
	 * 
	 * @return Map
	 */
	public java.util.Map getBackID2NameMap() {
		return backID2NameMap;
	}

	/**
	 * 设置前台ID2NameMap
	 * 
	 * @param bi2n
	 *            Map
	 */
	public void setBackID2NameMap(java.util.Map bi2n) {
		backID2NameMap = bi2n;
	}

	/**
	 * 克隆ID2NameMap，前后台Map均处理
	 * 
	 * @return Map
	 */
	public Map CloneID2NameMap() {
		Map map = new HashMap();
		if (frontID2NameMap != null)
			map.putAll(frontID2NameMap);
		if (backID2NameMap != null)
			map.putAll(backID2NameMap);
		return map;
	}

	/**
	 * 增加ID2Name数据，存于前台Map
	 * 
	 * @param map
	 *            Map
	 */
	public void setID2NameFront(Map map) {
		getID2Name("SB", "", "");
		if (frontID2NameMap == null)
			frontID2NameMap = new HashMap();
		frontID2NameMap.putAll(map);
	}

	public String getID2Name(String DCT_ID, String COL_ID, String defName) {
		if (frontID2NameMap == null) {
			frontID2NameMap = backID2NameMap;
			backID2NameMap = null;
		}
		Object res = null;
		if (frontID2NameMap != null) {
			res = frontID2NameMap.get(DCT_ID + "_" + COL_ID);
			if (res != null)
				return res.toString();
		}
		return defName;
	}

	/**
	 *
	 * @param DCT_ID
	 *            String
	 * @param COL_ID
	 *            String
	 * @return ISPRowSet
	 */
	public ISPRowSet getID2RowSet(String DCT_ID, String COL_ID) {
		if (frontID2NameMap == null) {
			frontID2NameMap = backID2NameMap;
			backID2NameMap = null;
		}
		Object res = null;
		if (frontID2NameMap != null) {
			res = frontID2NameMap.get(DCT_ID + "_RS_" + COL_ID);
		}
		return (ISPRowSet) res;
	}

	/**
	 *
	 * @return String
	 */
	public String toString() {
		return this.getString("caption", "");
	}

	/**
	 *
	 * @param s
	 *            String
	 */
	public void setCaption(String s) {
		this.putString("caption", s);
	}

	/**
	 *
	 * @param DCT_ID
	 *            String
	 * @param COL_ID
	 *            String
	 * @param Name
	 *            String
	 */
	public void setID2Name(String DCT_ID, String COL_ID, String Name) {
		// 如果frontID2NameMap不为空，说明是在前台，所有放入其中，直接返回
		if (frontID2NameMap != null) {
			frontID2NameMap.put(DCT_ID + "_" + COL_ID, Name);
			return;
		}
		// 执行到这，说明是在后台
		if (backID2NameMap == null)
			backID2NameMap = new java.util.HashMap();
		// 放入后台的Map中，可以序列化
		backID2NameMap.put(DCT_ID + "_" + COL_ID, Name);
	}

	/**
	 *
	 * @param DCT_ID
	 *            String
	 * @param COL_ID
	 *            String
	 * @param rowSet
	 *            ISPRowSet
	 */
	public void setID2RowSet(String DCT_ID, String COL_ID, ISPRowSet rowSet) {
		// 如果frontID2NameMap不为空，说明是在前台，所有放入其中，直接返回
		if (frontID2NameMap != null) {
			frontID2NameMap.put(DCT_ID + "_RS_" + COL_ID, rowSet);
			return;
		}
		// 执行到这，说明是在后台
		if (backID2NameMap == null)
			backID2NameMap = new java.util.HashMap();
		// 放入后台的Map中，可以序列化
		backID2NameMap.put(DCT_ID + "_RS_" + COL_ID, rowSet);
	}

	/**
	 *
	 * @param DCT_ID
	 *            String
	 * @param COL_ID
	 *            String
	 * @param rowSet
	 *            ISPRowSet
	 */
	public void setID2RowSetFront(String DCT_ID, String COL_ID, ISPRowSet rowSet) {
		if (frontID2NameMap == null)
			frontID2NameMap = new java.util.HashMap();
		if (backID2NameMap != null)
			frontID2NameMap.putAll(backID2NameMap);
		backID2NameMap = null;
		frontID2NameMap.put(DCT_ID + "_RS_" + COL_ID, rowSet);
	}

	//
	static final long serialVersionUID = 1L;
	// 无需序列化
	protected transient java.util.List dataSetViewList = null;
	// 无需序列化
	protected transient java.util.List dataSetMapList = null;
	// 需要序列化
	protected java.util.List dataSetContList = null;

	/**
	 *
	 * @return List
	 */
	public java.util.List getDataSetContList() {
		return dataSetContList;
	}

	/**
	 *
	 * @param dscl
	 *            List
	 */
	public void setDataSetContList(java.util.List dscl) {
		dataSetContList = dscl;
	}

	/**
	 *
	 * @param dataSet
	 *            ISSPDataSet
	 */
	public void insertDataSetManager(ISSPDataSet dataSet) {
		if (dataSet.getDataSetType() == ISSPDataSet._DATA_SET_CONT_) {
			this.insertDataSetCont(dataSet);
			return;
		}
		if (dataSet.getDataSetType() == ISSPDataSet._DATA_SET_MAP_) {
			this.insertDataSetMap(dataSet);
			return;
		}
		if (dataSet.getDataSetType() == ISSPDataSet._DATA_SET_VIEW_) {
			this.insertDataSetView(dataSet);
			return;
		}
	}

	/**
	 *
	 * @param dataSet
	 *            ISSPDataSet
	 */
	public void removeDataSetManager(ISSPDataSet dataSet) {
		if (dataSet.getDataSetType() == ISSPDataSet._DATA_SET_CONT_) {
			this.removeDataSetCont(dataSet);
			return;
		}
		if (dataSet.getDataSetType() == ISSPDataSet._DATA_SET_MAP_) {
			this.removeDataSetMap(dataSet);
			return;
		}
		if (dataSet.getDataSetType() == ISSPDataSet._DATA_SET_VIEW_) {
			this.removeDataSetView(dataSet);
			return;
		}
	}

	/**
	 *
	 * @param dataSet
	 *            ISSPDataSet
	 */
	protected void insertDataSetMap(ISSPDataSet dataSet) {
		if (dataSetMapList == null)
			dataSetMapList = new java.util.ArrayList();
		dataSetMapList.add(dataSet);
	}

	/**
	 *
	 * @param dataSet
	 *            ISSPDataSet
	 */
	protected void removeDataSetMap(ISSPDataSet dataSet) {
		if (dataSetMapList == null)
			return;
		dataSetMapList.remove(dataSet);
	}

	/**
	 *
	 * @param dataSet
	 *            ISSPDataSet
	 */
	protected void insertDataSetCont(ISSPDataSet dataSet) {
		if (dataSetContList == null)
			dataSetContList = new java.util.ArrayList();
		dataSetContList.add(dataSet);
	}

	/**
	 *
	 * @param dataSet
	 *            ISSPDataSet
	 */
	protected void removeDataSetCont(ISSPDataSet dataSet) {
		if (dataSetContList == null)
			return;
		dataSetContList.remove(dataSet);
	}

	/**
	 *
	 * @param dataSet
	 *            ISSPDataSet
	 */
	protected void insertDataSetView(ISSPDataSet dataSet) {
		if (dataSetViewList == null)
			dataSetViewList = new java.util.ArrayList();
		dataSetViewList.add(dataSet);
	}

	/**
	 *
	 * @param dataSet
	 *            ISSPDataSet
	 */
	protected void removeDataSetView(ISSPDataSet dataSet) {
		if (dataSetViewList == null)
			return;
		dataSetViewList.remove(dataSet);
	}

	/**
	 *
	 */
	protected transient boolean rowSelected = false;

	/**
	 *
	 * @return boolean
	 */
	public boolean isRowSelected() {
		return rowSelected;
	}

	/**
	 *
	 * @param rowSelected
	 *            boolean
	 */
	public void setRowSelected(boolean rowSelected) {
		boolean oldValue = this.rowSelected;
		this.rowSelected = rowSelected;
		fireDataSetEvent("_ROW_SELECT_", oldValue, rowSelected);
	}

	/**
	 *
	 */
	protected transient Icon rowIcon = null;

	/**
	 *
	 * @return Icon
	 */
	public Icon getRowIcon() {
		return rowIcon;
	}

	/**
	 *
	 * @param rowIcon
	 *            Icon
	 */
	public void setRowIcon(Icon rowIcon) {
		Icon oldIcon = this.rowIcon;
		this.rowIcon = rowIcon;
		fireDataSetEvent("_ROW_ICON_", oldIcon, rowIcon);
	}

	/**
	 *
	 */
	public ISSPRowSet() {
		dataMap = new HashMap();
		dataMapBak = new HashMap();
		dataMap.put("_Self_RowSet", this);
	}

	/**
	 *
	 * @return ISSPRowSet
	 */
	public static ISSPRowSet getInstance() {
		ISSPRowSet rowSet = new ISSPRowSet();
		return rowSet;
	}

	/**
	 *
	 */
	private Map dataMap = null;

	/**
	 *
	 */
	// private ISSPDataSet childDataSet = null;
	/**
	 * @return the dataMap
	 */
	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	/**
	 * @param dataMap
	 *            the dataMap to set
	 */
	public void setDataMap(Map dataMap) {
		this.dataMap = dataMap;
	}

	/**
	 * @return the formDataSet
	 */
	public ISSPDataSet getChildDataSet() {
		return this.getDataSet(_CHILD_DATA_SET_);
	}

	/**
	 * 设置子数据集
	 * 
	 * @param childDataSet
	 *            ISSPDataSet
	 */
	public void setChildDataSet(ISSPDataSet childDataSet) {
		this.setDataSet(_CHILD_DATA_SET_, childDataSet);
	}

	/**
	 *
	 * @param keyName
	 *            String
	 * @param defValue
	 *            Boolean
	 * @return Boolean
	 */
	public Boolean getBoolean(String keyName, Boolean defValue) {
		return MapUtil.getBoolean(dataMap, keyName, defValue,
				this.getExtRowSet(ISPRowSet._dependDCT_ITEM_, null));
	}

	/**
	 *
	 * @param keyName
	 *            String
	 * @param defValue
	 *            Date
	 * @return Date
	 */
	public Date getDate(String keyName, Date defValue) {
		return MapUtil.getDate(dataMap, keyName, defValue,
				this.getExtRowSet(ISPRowSet._dependDCT_ITEM_, null));
	}

	/**
	 *
	 * @param keyName
	 *            String
	 * @param defValue
	 *            Number
	 * @return Number
	 */
	public Number getNumber(String keyName, Number defValue) {
		return MapUtil.getNumber(dataMap, keyName, defValue,
				this.getExtRowSet(ISPRowSet._dependDCT_ITEM_, null));
	}

	/**
	 *
	 * @param keyName
	 *            String
	 * @param defValue
	 *            String
	 * @return String
	 */
	public String getString(String keyName, String defValue) {
		return MapUtil.getString(dataMap, keyName, defValue,
				this.getExtRowSet(ISPRowSet._dependDCT_ITEM_, null));
	}

	/**
	 *
	 * @param keyName
	 *            String
	 * @param defValue
	 *            String
	 * @return String
	 */
	public String getStringByTrim(String keyName, String defValue) {
		String value = getString(keyName, defValue);
		if (value != null && value.trim().length() == 0)
			return defValue;
		return value;
	}

	/**
	 *
	 * @param keyName
	 *            String
	 * @param value
	 *            Boolean
	 */
	public void putBoolean(String keyName, Boolean value) {
		putObject(keyName, value);
	}

	/**
	 *
	 * @param keyName
	 *            String
	 * @param value
	 *            Date
	 */
	public void putDate(String keyName, Date value) {
		putObject(keyName, value);
	}

	/**
	 *
	 * @param keyName
	 *            String
	 * @param value
	 *            Number
	 */
	public void putNumber(String keyName, Number value) {
		putObject(keyName, value);
	}

	/**
	 *
	 * @param keyName
	 *            String
	 * @param value
	 *            String
	 */
	public void putString(String keyName, String value) {
		putString(keyName, value, true);
	}

	/**
	 *
	 * @param keyName
	 *            String
	 * @param defValue
	 *            Object
	 * @return Object
	 */
	public Object getObject(String keyName, Object defValue) {
		Object obj = MapUtil.getObject(dataMap, keyName, defValue);
		if (obj instanceof Double)
			obj = new BigDecimal((Double) obj);
		return obj;
	}

	/**
	 *
	 * @param keyName
	 *            String
	 * @param value
	 *            Object
	 */
	public void putString(String keyName, String value, boolean btrim) {
		if (btrim)
			putObject(keyName, value);
		else {
			Object oldValue = MapUtil.getObject(dataMap, keyName, null);
			MapUtil.putObject(dataMap, keyName, value);
			// 调用事件处理器
			fireDataSetEvent(keyName, oldValue, value);
		}
	}

	public void putObject(String keyName, Object value) {
		// 去掉字符串中的空格
		if (value != null && value instanceof String) {
			value = ((String) value).trim();
		}
		if (value != null && value instanceof BigDecimal) {
			value = ((BigDecimal) value).doubleValue();
		}
		if (value != null && value instanceof BigInteger) {
			value = ((BigInteger) value).longValue();
		}
		Object oldValue = MapUtil.getObject(dataMap, keyName, null);
		MapUtil.putObject(dataMap, keyName, value);
		// 调用事件处理器
		fireDataSetEvent(keyName, oldValue, value);
	}

	/**
	 *
	 * @param keyName
	 *            String
	 * @param newValue
	 *            Object
	 */
	protected void fireDataSetEvent(String keyName, Object oldValue,
			Object newValue) {
		// 如果对象相等，不再触发事件
		if (oldValue == newValue)
			return;
		if (oldValue != null && oldValue.equals(newValue))
			return;
		// 如果不是新建状态，则将状态改为修改
		if (this.dataStatus != ISPRowSet._DATA_STATUS_INSERT_)
			this.dataStatus = ISPRowSet._DATA_STATUS_UPDATE_;
		java.util.List dataSetList = new java.util.ArrayList();
		if (dataSetViewList != null)
			dataSetList.addAll(dataSetViewList);
		if (dataSetContList != null)
			dataSetList.addAll(dataSetContList);
		if (dataSetList.size() == 0)
			return;
		ISSPDataSet dataSet = null;
		for (int i = 0; i < dataSetList.size(); i++) {
			dataSet = (ISSPDataSet) dataSetList.get(i);
		}
	}

	/**
	 *
	 */
	public java.util.HashMap<String, ISSPDataSet> dataSetMap = null;

	public java.util.HashMap<String, ISSPDataSet> getDataSetMap() {
		return dataSetMap;
	}

	public void setDataSetMap(java.util.HashMap<String, ISSPDataSet> dsm) {
		dataSetMap = dsm;
	}

	/**
	 *
	 * @param dsType
	 *            String
	 * @return ISSPDataSet
	 */
	public ISSPDataSet getDataSet(String dsType) {
		if (dataSetMap == null)
			return null;
		return dataSetMap.get(dsType);
	}

	/**
	 *
	 * @param dsType
	 *            String
	 * @param dataSet
	 *            ISSPDataSet
	 */
	public void setDataSet(String dsType, ISSPDataSet dataSet) {
		if (dataSetMap == null)
			dataSetMap = new java.util.HashMap<String, ISSPDataSet>();
		dataSetMap.put(dsType, dataSet);
		// 如果为空，则空指针
		if (dataSet == null)
			return;
		dataSet.setMasterKey(this);
		dataSet.setParentRowSet(this); // 设置DataSet的ParentRowSet
		dataSet.insertChildRowSet(this);
	}

	/**
	 *
	 * @param dsType
	 *            String
	 * @return ISSPDataSet
	 */
	public ISSPDataSet removeDataSet(String dsType) {
		if (dataSetMap == null)
			return null;
		ISSPDataSet dataSet = dataSetMap.remove(dsType);
		if (dataSet != null) {
			dataSet.setParentRowSet(null);
			dataSet.removeChildRowSet(this);
		}
		return dataSet;
	}

	/**
	 *
	 * @return String[]
	 */
	public String[] getDSTypes() {
		if (dataSetMap == null)
			return null;
		String[] dsTypes = new String[dataSetMap.size()];
		dsTypes = (String[]) dataSetMap.keySet().toArray(dsTypes);
		return dsTypes;
	}

	/**
	 *
	 */
	protected Object master = null;

	/**
	 *
	 * @param o
	 *            Object
	 */
	public void setMasterKey(Object o) {
		if (master != o) {
			master = o;
		}
	}

	/**
	 *
	 * @return Object
	 */
	public Object getMasterKey() {
		return master;
	}

	/**
	 *
	 * @param key
	 *            String
	 * @param def
	 *            double
	 * @return double
	 */
	public int getInt(String key, int def) {
		Object o = this.getObject(key, null);
		if (o == null)
			return def;
		if (o instanceof String)
			return Integer.parseInt((String) o);
		if (o instanceof Number) {// BigDecimal
			return ((Number) o).intValue();
		}
		// if(o instanceof Double){
		// return ((Double)o).intValue();
		// }
		// if(o instanceof Long){
		// return ((Long)o).intValue();
		// }
		return def;
	}

	/**
	 *
	 * @param key
	 *            String
	 * @param def
	 *            double
	 * @return double
	 */
	public double getDouble(String key, double def) {
		if (getNumber(key, null) != null)
			return getNumber(key, null).doubleValue();
		String cysl = getString(key, String.valueOf(def));
		if (cysl.equals(""))
			cysl = "0";
		try {
			double dd = Double.parseDouble(cysl);
			if (Double.isNaN(dd))
				return def;
			return dd;
		} catch (Exception e) {
			return def;
		}
	}

	/**
	 *
	 * @param key
	 *            Object
	 * @param def
	 *            Object
	 * @return Object
	 */
	public Object getValue(Object key, Object def) {
		return getObject(key.toString(), def);
	}

	/**
	 *
	 * @param key
	 *            Object
	 * @param val
	 *            Object
	 */
	public void setValue(Object key, Object val) {
		this.putObject(key.toString(), val);
	}

	/**
	 *
	 * @return Map
	 */
	public Map getAttriMap() {
		return dataMap;
	}

	/**
	 * 支持双向链表方式的数据集 链表的构造由外部系统以工具类的方式构造，ISPRowSet不负责链表的构造，只提供链表方式的结构
	 */

	/**
	 *
	 */
	protected ISPRowSet priorRowSet = null;

	/**
	 *
	 * @return ISPRowSet
	 */
	public ISPRowSet getPrior() {
		return priorRowSet;
	}

	/**
	 *
	 * @param rowSet
	 *            ISPRowSet
	 */
	public void setPrior(ISPRowSet rowSet) {
		priorRowSet = rowSet;
	}

	/**
	 *
	 */
	protected ISPRowSet nextRowSet = null;

	/**
	 *
	 * @return ISPRowSet
	 */
	public ISPRowSet getNext() {
		return nextRowSet;
	}

	/**
	 *
	 * @param rowSet
	 *            ISPRowSet
	 */
	public void setNext(ISPRowSet rowSet) {
		nextRowSet = rowSet;
	}

	/**
	 *
	 * @return ISPRowSet
	 */
	public ISPRowSet getFirst() {
		return null;
	}

	/**
	 *
	 * @return ISPRowSet
	 */
	public ISPRowSet getLast() {
		return null;
	}

	/**
	 *
	 */
	protected int dataStatus = _DATA_STATUS_NORMAL_;

	/**
	 *
	 * @return int
	 */
	public int getDataStatus() {
		return dataStatus;
	}

	/**
	 *
	 * @param dataStatus
	 *            int
	 */
	public void setDataStatus(int dataStatus) {
		this.dataStatus = dataStatus;
	}

	/**
	 *
	 */
	public void setNewRowSetFlag() {
		this.dataStatus = _DATA_STATUS_INSERT_;
	}

	/**
	 * 扩展属性存储，这些扩展属性不于数据列一起处理
	 */
	protected java.util.Map extPropertys = null;

	/**
	 *
	 * @return Map
	 */
	public java.util.Map getExtPropertys() {
		return extPropertys;
	}

	/**
	 *
	 * @param eps
	 *            Map
	 */
	public void setExtPropertys(java.util.Map eps) {
		extPropertys = eps;
	}

	/**
	 *
	 * @param key
	 *            Object
	 * @param value
	 *            Object
	 */
	public void setExtProperty(Object key, Object value) {
		if (extPropertys == null)
			extPropertys = new java.util.HashMap();
		extPropertys.put(key, value);
	}

	/**
	 *
	 * @param key
	 *            Object
	 * @param def
	 *            Object
	 * @return Object
	 */
	public Object getExtProperty(Object key, Object def) {
		if (extPropertys == null)
			return def;
		Object res = extPropertys.get(key);
		return res == null ? def : res;
	}

	/**
	 *
	 */
	protected int rowSetAppType = -1;

	/**
	 *
	 * @return int
	 */
	public int getRowSetAppType() {
		return rowSetAppType;
	}

	/**
	 *
	 * @param rowSetType
	 *            int
	 */
	public void setRowSetAppType(int rowSetType) {
		rowSetAppType = rowSetType;
	}

	/**
	 *
	 * @param key
	 *            String
	 * @return boolean
	 */
	public boolean hasKey(String key) {
		if (dataMap == null)
			return false;
		Object[] keyObject = dataMap.keySet().toArray();
		String[] keyString = new String[keyObject.length];
		System.arraycopy(keyObject, 0, keyString, 0, keyString.length);
		return Arrays.asList(keyString).contains(key);
	}

	/**
	 *
	 */
	protected java.util.Map<String, ISPRowSet> extRowSetMap = null;

	/**
	 *
	 * @param key
	 *            String
	 * @param rowSet
	 *            ISPRowSet
	 */
	public void setExtRowSet(String key, ISPRowSet rowSet) {
		if (extRowSetMap == null) {
			extRowSetMap = new java.util.HashMap<String, ISPRowSet>();
		}
		extRowSetMap.put(key, rowSet);
	}

	/**
	 *
	 * @param key
	 *            String
	 * @param def
	 *            ISPRowSet
	 * @return ISPRowSet
	 */
	public ISPRowSet getExtRowSet(String key, ISPRowSet def) {
		if (extRowSetMap == null)
			return null;
		ISPRowSet rs = extRowSetMap.get(key);
		return (rs == null) ? def : rs;
	}

	public ISPRowSet getGroupDCT1RowSet() {
		return getExtRowSet(ISPRowSet._dependMDCT_ITEM_, null);
	}

	public ISPRowSet getGroupDCT2RowSet() {
		return getExtRowSet(ISPRowSet._dependDCT_ITEM_, null);
	}

	/**
	 *
	 */
	protected java.util.Map<String, String> rowColFormulaMap = null;

	/**
	 *
	 * @param col
	 *            String
	 * @return String
	 */
	public String getRowColFormula(String col) {
		if (rowColFormulaMap == null)
			return rowFormula;
		return MapUtil.getString(rowColFormulaMap, col, rowFormula);
	}

	/**
	 *
	 * @param col
	 *            String
	 * @param f1
	 *            String
	 */
	public void setRowColFormula(String col, String f1) {
		if (rowColFormulaMap == null)
			rowColFormulaMap = new HashMap<String, String>();
		rowColFormulaMap.put(col, f1);
	}

	/**
	 *
	 */
	protected String rowFormula = null;

	/**
	 *
	 * @param f1
	 *            String
	 */
	public void setRowFormula(String f1) {
		rowFormula = f1;
	}

	/**
	 *
	 */
	protected int level;

	/**
	 *
	 * @return int
	 */
	public int getLevel() {
		return level;
	}

	/**
	 *
	 * @param level
	 *            int
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	protected ISSPDataSet ownerDataSet = null;

	/**
	 * 获取ownerDataSet方法 
	 * 
	 * @return
	 */
	public ISSPDataSet getOwnerDataSet() {
		return ownerDataSet;
	}

	/**
	 * 设置ownerDataSet方法
	 * 
	 * @param ownerDataSet
	 */
	public void setOwnerDataSet(ISSPDataSet ownerDataSet) {
		this.ownerDataSet = ownerDataSet;
	}

	public ISSPDataSet getDataSet() {
		return ownerDataSet;
	}

	public void setDataSet(ISSPDataSet ds) {
		ownerDataSet = ds;
	}

	/**
	 *
	 * @param isTrim
	 *            存储前是否进行trim处理
	 */
	public void putObject(String keyName, Object value, boolean isTrim) {
		// 去掉字符串中的空格
		if (value != null && value instanceof String && isTrim) {
			value = ((String) value).trim();
		}
		if (value != null && value instanceof BigDecimal) {
			value = ((BigDecimal) value).doubleValue();
		}
		if (value != null && value instanceof BigInteger) {
			value = ((BigInteger) value).longValue();
		}
		Object oldValue = MapUtil.getObject(dataMap, keyName, null);
		MapUtil.putObject(dataMap, keyName, value);
		// 调用事件处理器
		fireDataSetEvent(keyName, oldValue, value);
	}

	public void clear() {
		this.dataMap.clear();
	}

	public boolean containsKey(Object arg0) {
		return this.dataMap.containsKey(arg0);
	}

	public boolean containsValue(Object arg0) {
		return this.dataMap.containsValue(arg0);
	}

	public Set entrySet() {
		return this.dataMap.entrySet();
	}

	public Object get(Object arg0) {
		return this.dataMap.get(arg0);
	}

	public boolean isEmpty() {
		return this.dataMap.isEmpty();
	}

	public Set keySet() {
		return this.dataMap.keySet();
	}

	public Object put(Object arg0, Object arg1) {
		return this.dataMap.put(arg0, arg1);
	}

	public void putAll(Map arg0) {
		this.dataMap.putAll(arg0);
	}

	public Object remove(Object arg0) {
		return this.dataMap.remove(arg0);
	}

	public int size() {
		return this.dataMap.size();
	}

	public Collection values() {
		return this.dataMap.values();
	}

	public Object clone() {
		ISSPRowSet rs = new ISSPRowSet();
		rs.setDataMap((Map) ((java.util.HashMap) this.dataMap).clone());
		return rs;
	}

	/**
	 * 所属主表ID
	 */
	String masterTableName = null;

	public String getMasterTableName() {
		return masterTableName;
	}

	public void setMasterTableName(String tableName) {
		if (masterTableName == null)
			masterTableName = tableName;
	}

	// 为LDAP 增加属性
	protected String ldapDN = null;

	public String getLdapDN() {
		return ldapDN;
	}

	public void setLdapDN(String dn) {
		ldapDN = dn;
	}

	protected String ldapRDN = null;

	public String getLdapRDN() {
		return ldapRDN;
	}

	public void setLdapRDN(String rdn) {
		ldapRDN = rdn;
	}

	private Map dataMapBak = null;

	public Map<String, Object> getDataMapBak() {
		return dataMapBak;
	}

	public void setDataMapBak(Map dataMap) {
		dataMapBak = dataMap;
	}

	public Object getObjectBak(String key) {
		if (dataMapBak == null)
			return null;
		return dataMapBak.get(key);
	}

	public void putObjectBak(String key, Object obj) {
		dataMapBak.put(key, obj);
	}

}