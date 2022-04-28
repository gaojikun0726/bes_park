package com.core.common.data;

import com.core.common.util.StringFunction;

import java.util.*;

/**
 * 数据集对象
 * 
 * @author Alvin
 *
 */
public class ISSPDataSet implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 宿主对象
	 */
	protected Object master;
	/**
   *
   */
	private String tableName = null;

	/**
	 * 获取数据集ID，即数据集表名
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * 设置数据集ID，即数据集表名
	 * 
	 * @param tableName
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	private String parentTableName = null;

	/**
	 * 获取父数据集ID
	 * 
	 * @return String
	 */
	public String getParentTableName() {
		return this.parentTableName;
	}

	/**
	 * 设置父数据集ID
	 * 
	 * @param name
	 *            String
	 */
	public void setParentTableName(String name) {
		this.parentTableName = name;
	}

	/**
	 * 父数据行
	 */
	protected ISSPRowSet parentRowSet = null;

	/**
	 * 设置父数据行
	 * 
	 * @param rowSet
	 *            ISSPRowSet
	 */
	public void setParentRowSet(ISSPRowSet rowSet) {
		this.parentRowSet = rowSet;
	}

	/**
	 * 父数据行
	 * 
	 * @return ISSPRowSet
	 */
	public ISSPRowSet getParentRowSet() {
		return this.parentRowSet;
	}

	/**
	 * 主键
	 */
	protected String[] primeKey = null;

	/**
	 * 获取主键
	 * 
	 * @return String[]
	 */
	public String[] getPrimeKey() {
		return primeKey;
	}

	/**
	 * 设置主键
	 * 
	 * @param primeKey
	 *            String
	 */
	public void setPrimeKey(String[] primeKey) {
		this.primeKey = primeKey;
	}

	/**
	 * 数据映射，啥也不管
	 */
	public static final int _DATA_SET_MAP_ = 0x0000;

	/**
	 * 数据视图，只监听事件，不管理事件
	 */
	public static final int _DATA_SET_VIEW_ = 0x0001;

	/**
	 * 数据容器，啥也管
	 */
	public static final int _DATA_SET_CONT_ = 0x0002;

	/**
	 * 数据集类型 默认：数据映射
	 */
	protected int dataSetType = _DATA_SET_MAP_;

	/**
	 * 数据集类型 默认：数据映射
	 * 
	 * @return int
	 */
	public int getDataSetType() {
		return dataSetType;
	}

	/**
	 * 对象初始化
	 * 
	 * @param tableName
	 *            String
	 * @return ISSPDataSet
	 */
	public static ISSPDataSet getInstance(String tableName) {
		return getInstance(tableName, _DATA_SET_MAP_);
	}

	/**
	 * 对象初始化
	 * 
	 * @param tableName
	 * @param dataSetType
	 * @return ISSPDataSet
	 */
	public static ISSPDataSet getInstance(String tableName, int dataSetType) {
		ISSPDataSet dataSet = new ISSPDataSet();
		dataSet.dataSetType = dataSetType;
		dataSet.tableName = tableName;
		dataSet.setDeleteDataPool(getDataPoolInstance(tableName));
		dataSet.setInsertDataPool(getDataPoolInstance(tableName));
		dataSet.setUpdateDataPool(getDataPoolInstance(tableName));
		return dataSet;
	}

	/**
	 * 对象初始化
	 * 
	 * @param tableName
	 *            String
	 * @return ISSPDataSet
	 */
	public static ISSPDataSet getDataPoolInstance(String tableName) {
		ISSPDataSet dataSet = new ISSPDataSet();
		dataSet.dataSetType = _DATA_SET_MAP_;
		dataSet.tableName = tableName;
		dataSet.setDataSetPool(true);
		dataSet.setStartDataPool(false);
		return dataSet;
	}

	/**
	 * 是否第一行
	 * 
	 * @return int
	 */
	public final boolean atFirst() {
		return (currentRowIndex == 0);
	}

	/**
	 * 是否最后一行
	 * 
	 * @return int
	 */
	public final boolean atLast() {
		if (rowSetList == null || rowSetList.size() == 0)
			return true;
		return (currentRowIndex == (rowSetList.size() - 1));
	}

	/**
	 * 光标移至第一行
	 * 
	 * @return int
	 */
	public final int first() {
		if (rowSetList == null || rowSetList.size() == 0)
			return -1;
		// 如果数据为空，则返回-1
		return this.goToRow(0);
	}

	/**
	 * 光标移至前一行
	 * 
	 * @return int
	 */
	public final int prior() {
		int rowIndex = currentRowIndex - 1;
		if (rowIndex >= 0)
			return this.goToRow(rowIndex);
		else
			return -1;
	}

	/**
	 * 光标移至下一行
	 * 
	 * @return int
	 */
	public final int next() {
		if (rowSetList == null || rowSetList.size() == 0)
			return -1;
		int rowCount = rowSetList.size();
		int rowIndex = currentRowIndex + 1;
		if (rowIndex < rowCount)
			return this.goToRow(rowIndex);
		else
			return -1;
	}

	/**
	 * 光标移至最后一行
	 * 
	 * @return int
	 */
	public final int last() {
		if (rowSetList == null || rowSetList.size() == 0)
			return -1;
		int rowIndex = rowSetList.size() - 1;
		return this.goToRow(rowIndex);
	}

	/**
	 * 根据ISPRowSet定位光标索引
	 * 
	 * @param rowSet
	 * @return int
	 */
	public int goToRow(ISPRowSet rowSet) {
		if (rowSetList == null || rowSetList.size() == 0)
			return -1;
		int index = rowSetList.indexOf(rowSet);
		if (index != -1) {
			return goToRow(index);
		}
		return -1;
	}

	/**
	 * 根据索引值定位光标索引
	 * 
	 * @param rowIndex
	 *            int
	 * @return int
	 */
	public int goToRow(int rowIndex) {
		if (rowSetList == null || rowSetList.size() == 0)
			return -1;
		if (rowIndex < 0 || rowIndex >= rowSetList.size())
			return -1;
		int oldIndex = 0;
		if (currentRowIndex != rowIndex) {
			oldIndex = currentRowIndex;
			currentRowIndex = rowIndex;
		}
		return currentRowIndex;
	}

	/**
	 * 移动光标，不产生数据集事件
	 * 
	 * @param rowIndex
	 *            int
	 * @return int
	 */
	public int goToRowNoWithCursorEvent(int rowIndex) {
		if (rowSetList == null || rowSetList.size() == 0)
			return -1;
		if (rowIndex < 0 || rowIndex >= rowSetList.size())
			return -1;
		int oldIndex = 0;
		if (currentRowIndex != rowIndex) {
			oldIndex = currentRowIndex;
			currentRowIndex = rowIndex;
			// 不产生游标移动事件
			// fireCursorChange(oldIndex,currentRowIndex);
		}
		return currentRowIndex;
	}

	/**
	 * 获取当前行记录
	 * 
	 * @return ISPRowSet
	 */
	public ISSPRowSet getRowSet() {
		if (rowSetList == null || rowSetList.size() == 0)
			return null;
		if (currentRowIndex < 0 || currentRowIndex >= rowSetList.size())
			return null;
		return (ISSPRowSet) rowSetList.get(currentRowIndex);
	}

	/**
	 * 根据索引获取行记录
	 * 
	 * @param rowIndex
	 *            int
	 * @return ISSPRowSet
	 */
	public ISSPRowSet getRowSet(int rowIndex) {
		if (rowSetList == null || rowSetList.size() == 0)
			return null;
		if (rowIndex < 0 || rowIndex >= rowSetList.size())
			return null;
		return (ISSPRowSet) rowSetList.get(rowIndex);
	}

	/**
	 * 当前活动索引
	 */
	protected transient int currentRowIndex = -1;

	/**
	 * 获取所有行数据对象
	 * 
	 * @return List
	 */
	public java.util.List getRowSetArray() {
		if (rowSetList == null || rowSetList.size() == 0)
			return null;
		java.util.List arrayList = new java.util.ArrayList();
		arrayList.addAll(rowSetList);
		return arrayList;
	}

	/**
	 * 设置行数据对象
	 * 
	 * @param list
	 */
	public void setRowSetArray(java.util.List list) {
		if (list == null || list.size() == 0) {
			rowSetList = null;
			return;
		}
		rowSetList = new Vector<ISPRowSet>();
		rowSetList.addAll(list);
	}

	private List<ISPRowSet> rowSetList = null;

	/**
	 * 获取所有行数据对象
	 * 
	 * @return List
	 */
	public List<ISPRowSet> getRowSetList() {
		return rowSetList;
	}

	/**
	 * 获取记录行数
	 * 
	 * @return int
	 */
	public int getRowCount() {
		if (rowSetList == null)
			return 0;
		return rowSetList.size();
	}

	/**
	 * 获取记录行列表
	 * 
	 * @param rowSetList
	 */
	public void setRowSetList(List<ISPRowSet> rowSetList) {
		this.rowSetList = rowSetList;
		setChildMasterKey();
	}

	/**
	 * 设置数据集类型
	 * 
	 * @param dataSetType
	 */
	public void setDataSetType(int dataSetType) {
		this.dataSetType = dataSetType;
	}

	/**
	 * 插入一行记录ISPRowSet
	 * 
	 * @param rowSet
	 */
	public void insertRowSet(ISPRowSet rowSet) {
		if (rowSetList == null) {
			rowSetList = new Vector<ISPRowSet>();
		}
		rowSet.setMasterKey(master);
		int index = currentRowIndex == -1 ? rowSetList.size() + 1
				: currentRowIndex + 1;
		insertRowSet(index, rowSet);
	}

	/**
	 * 插入一行记录ISPRowSet
	 * 
	 * @param rowSet
	 *            ISPRowSet
	 * @param unique
	 *            boolean 是否检查数据唯一
	 */
	public void insertRowSet(ISPRowSet rowSet, boolean unique) {
		if (rowSetList == null) {
			rowSetList = new Vector<ISPRowSet>();
		}
		rowSet.setMasterKey(master);
		int index = currentRowIndex == -1 ? rowSetList.size() + 1
				: currentRowIndex + 1;
		insertRowSet(index, rowSet, unique);
	}

	/**
	 * 增加一行记录，放置尾部
	 * 
	 * @param rowSet
	 *            ISPRowSet
	 */
	public void addRowSet(ISPRowSet rowSet) {
		int index = 0;
		if (rowSetList != null)
			index = rowSetList.size() + 1;
		insertRowSet(index, rowSet);
	}

	/**
	 * 增加一行记录，放置尾部
	 * 
	 * @param rowSet
	 *            ISPRowSet
	 */
	public void appendRowSet(ISPRowSet rowSet) {
		int index = 0;
		if (rowSetList != null)
			index = rowSetList.size() + 1;
		insertRowSet(index, rowSet);
	}

	/**
	 * 根据索引值，插入一行记录
	 * 
	 * @param index
	 *            int
	 * @param rowSet
	 *            ISPRowSet
	 */
	public void insertRowSet(int index, ISPRowSet rowSet) {
		insertRowSet(index, rowSet, false);
	}

	/**
	 * 按照索引值插入一行记录，并按主键检查数据唯一性，存在重复记录不进行插入
	 * 
	 * @param index
	 *            int 索引
	 * @param rowSet
	 *            ISPRowSet 行记录对象
	 * @param unique
	 *            boolean 是否检查数据唯一
	 */
	public void insertRowSet(int index, ISPRowSet rowSet, boolean unique) {
		if (rowSetList == null)
			rowSetList = new Vector<ISPRowSet>();
		// 主键唯一
		if (unique && containsKey(getPrimeKeyValue(rowSet)))
			return;
		// 插入前面一行
		if (index < rowSetList.size()) {// <=改的
			if (index > 0)
				index--;
			rowSetList.add(index, rowSet);// .insertElementAt(
		} else {
			rowSetList.add(rowSet);
		}

		monitorDataJVM();
		rowSet.setMasterKey(master);
		rowSet.setMasterTableName(this.getTableName());
		// 创建主键索引
		buildPrimeKeyIndex(rowSet);
		// 增加到
		rowSet.insertDataSetManager(this);
		rowSet.setDataSet(this);

		// 插入数据到缓冲区
		insertDataToInsertPool(rowSet);
	}

	private void monitorDataJVM() {
		// TODO Auto-generated method stub
		if (true)
			return;
		int count = rowSetList.size();
		if (count % 1000 == 0) {
			long freeMemory = Runtime.getRuntime().freeMemory() / 1024;
			long totalMemory = Runtime.getRuntime().totalMemory() / 1024;
			System.out.println("JVM TotalMemory：" + String.valueOf(totalMemory)
					+ "K,FreeMemory：" + String.valueOf(freeMemory) + "K.");
			System.out.println("JVM Warming:ISSPDataSet[" + this.tableName
					+ "] RowCount:" + String.valueOf(count) + ".");
		}

	}

	/**
	 * 判断索引是否重复
	 * 
	 * @param key
	 *            String
	 * @return boolean
	 */
	public boolean containsKey(String key) {
		if (primeKey == null || key == null || primeKeyRowSetMap == null)
			return false;
		return primeKeyRowSetMap.containsKey(key);
	}

	/**
	 * 根据索引值移除行记录
	 * 
	 * @param row
	 *            int
	 * @return ISPRowSet
	 */
	public ISPRowSet removeRowSet(int row) {
		if (row == -1 || row >= rowSetList.size())
			return null;
		ISPRowSet rowSet = rowSetList.remove(row);
		if (rowSet != null) {
			String primeKeyValue = getPrimeKeyValue(rowSet);
			if (primeKeyRowSetMap != null)
				primeKeyRowSetMap.remove(primeKeyValue);
			rowSet.removeDataSetManager(this);
		}
		// 删除后，可能需要修改当前行索引
		if (this.currentRowIndex >= rowSetList.size()) {
			this.goToRow(rowSetList.size() - 1);
		}
		// 删除数据到缓冲区 
		insertDataToDeletePool(rowSet);
		return rowSet;
	}

	/**
	 * 根据行记录对象移除
	 * 
	 * @param rowSet
	 *            RowSet
	 * @return RowSet
	 */
	public ISPRowSet removeRowSet(ISPRowSet rowSet) {
		if (rowSetList == null)
			return null;
		int index = rowSetList.indexOf(rowSet);
		return removeRowSet(index);
	}

	/**
	 * 按主键Key存放行数据对象Map
	 */
	protected Map<String, ISPRowSet> primeKeyRowSetMap = null;

	/**
	 * 设置主键Key存放行数据对象Map
	 * 
	 * @param primeKeyRowSetMap
	 *            Map<String, ISPRowSet>
	 */
	public void setPrimeKeyRowSetMap(Map<String, ISPRowSet> primeKeyRowSetMap) {
		this.primeKeyRowSetMap = primeKeyRowSetMap;
	}

	/**
	 * 获取主键Key存放行数据对象Map
	 * 
	 * @return Map<String, ISPRowSet>
	 */
	public Map<String, ISPRowSet> getPrimeKeyRowSetMap() {
		return primeKeyRowSetMap;
	}

	/**
	 * 获取当前活动索引
	 * 
	 * @return int
	 */
	public int getCurrentRowIndex() {
		return currentRowIndex;
	}

	/**
	 * 创建索引，遍历所有行数据对象
	 */
	public void buildPrimeKeyIndex() {
		if (rowSetList == null || primeKey == null) {
			return;
		}
		primeKeyRowSetMap = new java.util.HashMap<String, ISPRowSet>();
		// primeKeyRowSetMap = new ConcurrentHashMap<String, ISPRowSet> ();
		ISPRowSet rowSet = null;
		for (int i = 0; i < rowSetList.size(); i++) {
			rowSet = rowSetList.get(i);
			String primeKeyValue = getPrimeKeyValue(rowSet);
			if (primeKeyValue != null) {
				primeKeyRowSetMap.put(primeKeyValue, rowSet);
			}
			// KEYKEYKEY
			buildPrimeKeyKeyIndex(rowSet);
		}
	}

	/**
	 * 创建索引，指定行数据对象
	 * 
	 * @param rowSet
	 *            ISPRowSet
	 */
	public void buildPrimeKeyIndex(ISPRowSet rowSet) {
		if (primeKey == null || rowSet == null)
			return;
		if (primeKeyRowSetMap == null) // primeKeyRowSetMap = new
										// ConcurrentHashMap<String, ISPRowSet>
										// ();
			primeKeyRowSetMap = new java.util.HashMap<String, ISPRowSet>();
		String primeKeyValue = getPrimeKeyValue(rowSet);
		if (primeKeyValue != null)
			primeKeyRowSetMap.put(primeKeyValue, rowSet);
		// KEYKEYKEY
		buildPrimeKeyKeyIndex(rowSet);
	}

	/**
	 * 根据ISPRowSet获取主键Key值，多主键用-分开
	 * 
	 * @param rowSet
	 *            RowSet
	 * @return String
	 */
	public String getPrimeKeyValue(ISPRowSet rowSet) {
		if (primeKey == null || primeKey.length == 0)
			return null;
		String value = "";
		for (int i = 0; i < primeKey.length; i++) {
			value += (((i == 0) ? "" : "-") + rowSet.getString(primeKey[i], ""));
		}
		return value;
	}

	/**
	 * 根据String[]获取主键Key，多主键用-分开
	 * 
	 * @param keyValue
	 *            String[]
	 * @return String
	 */
	protected String getPrimeKeyValue(String[] keyValue) {
		String value = "";
		for (int i = 0; i < keyValue.length; i++) {
			value += (((i == 0) ? "" : "-") + (keyValue[i] == null ? ""
					: keyValue[i]));
		}
		return value;
	}

	/**
	 * 根据主键Key获取行数据对象
	 * 
	 * @param keyValue
	 *            String[]
	 * @return ISPRowSet
	 */
	public ISPRowSet getRowSet(String[] keyValue) {
		if (primeKeyRowSetMap == null)
			return null;
		return primeKeyRowSetMap.get(getPrimeKeyValue(keyValue));
	}

	/**
	 * 根据主键Key获取行数据对象
	 * 
	 * @param keyValue
	 *            String 多主键用,分开
	 * @return ISPRowSet
	 */
	public ISPRowSet getRowSet(String keyValue) {
		String[] keys = keyValue.split(",");
		return getRowSet(keys);
	}

	/**
	 * 设置主Key，将ISSPDataSet存放ISSPRowSet中，则主Key为ISSPRowSet
	 * 
	 * @param o
	 *            Object
	 */
	public void setMasterKey(Object o) {
		if (master != o) {
			master = o;
			setChildMasterKey();
		}
	}

	/**
	 * 设置子主Key，默认同ISSPDataSet相同
	 */
	private void setChildMasterKey() {
		for (int i = 0; rowSetList != null && i < rowSetList.size(); i++) {
			ISPRowSet es = rowSetList.get(i);
			es.setMasterKey(master);
		}
	}

	/**
	 * 获取主Key
	 * 
	 * @return Object
	 */
	public Object getMasterKey() {
		return master;
	}

	/**
	 * 激活当前数据集
	 */
	public void activeDataSet() {
		if (this.currentRowIndex == -1)
			this.first();

	}

	/**
	 * 过滤数据集Map，不传递后台服务器
	 */
	protected transient java.util.Map filterDataSetMap = null;

	/**
	 * 存放过滤数据集，并设置事件监听
	 * 
	 * @param cont
	 *            String
	 * @param dataSet
	 *            ISSPDataSet
	 */
	public void putFilterDataSet(String cont, ISSPDataSet dataSet) {
		if (filterDataSetMap == null)
			filterDataSetMap = new java.util.HashMap();
		filterDataSetMap.put(cont, dataSet);
	}

	/**
	 * 获取过滤数据集
	 * 
	 * @param cont
	 *            String
	 * @return ISSPDataSet
	 */
	public ISSPDataSet getFilterDataSet(String cont) {
		if (filterDataSetMap == null)
			return null;
		return (ISSPDataSet) filterDataSetMap.get(cont);
	}

	/**
	 * 获取选中的行记录，组织数据集对象返回
	 * 
	 * @return ISSPDataSet
	 */
	public ISSPDataSet getSelectedDataSet() {
		if (this.rowSetList == null || this.rowSetList.size() == 0)
			return null;
		ISSPDataSet dataSet = null;
		ISSPRowSet rowSet = null;
		for (int i = 0; i < rowSetList.size(); i++) {
			rowSet = (ISSPRowSet) rowSetList.get(i);
			if (rowSet.isRowSelected()) {
				if (dataSet == null)
					dataSet = ISSPDataSet.getInstance(this.getTableName(),
							_DATA_SET_MAP_);
				dataSet.addRowSet(rowSet);
			}
		}
		return dataSet;
	}

	/**
	 * 交换行数据对象索引位置
	 * 
	 * @param i1
	 *            int
	 * @param i2
	 *            int
	 */
	public void swapISSPRowSet(int i1, int i2) {
		ISSPRowSet rs1 = getRowSet(i1);
		ISSPRowSet rs2 = getRowSet(i2);
		getRowSetList().set(i1, rs2);
		getRowSetList().set(i2, rs1);
	}

	/**
	 * 多主键的情况下，记录多个主键之间的关系
	 */
	protected Map<String, Map> primeKeyKeyMap = null;

	/**
	 * 获取主键
	 * 
	 * @return Map<String, Map>
	 */
	public Map<String, Map> getPrimeKeyKeyMap() {
		return primeKeyKeyMap;
	}

	/**
	 * 设置主键
	 * 
	 * @param primeKeyKeyMap
	 *            Map<String, Map>
	 */
	public void setPrimeKeyKeyMap(Map<String, Map> primeKeyKeyMap) {
		this.primeKeyKeyMap = primeKeyKeyMap;
	}

	/**
	 * 创建主键索引
	 * 
	 * @param rowSet
	 *            ISPRowSet
	 */
	public void buildPrimeKeyKeyIndex(ISPRowSet rowSet) {
		String[] primeKey = getPrimeKey();
		String key = null, val = null;
		java.util.Map<String, java.util.List> map = null;
		java.util.List<String> list = null;
		for (int i = 0; primeKey != null && i < primeKey.length; i++) {
			map = getPrimeKeyKeyMap(primeKey[i]);
			key = rowSet.getString(primeKey[i], "");
			val = getPrimeKeyValue(rowSet);
			if (map == null || val == null)
				continue;
			list = map.get(key);
			if (list == null) {
				list = new ArrayList<String>();
				map.put(key, list);
			}
			if (!list.contains(val))
				list.add(val);
		}
	}

	/**
	 * 根据关键字枚举关键值
	 * 
	 * @param keys
	 *            String[]
	 * @return java.util.List<String[]>
	 */
	public java.util.List<String[]> listPrimeValByKey(String[] keys) {
		if (primeKey == null || keys == null)
			return null;
		for (int i = 0; keys != null && i < keys.length; i++) {
			if (!java.util.Arrays.asList(primeKey).contains(keys[i]))
				return null;
		}
		if (primeKeyRowSetMap == null)
			// primeKeyRowSetMap = new ConcurrentHashMap();
			primeKeyRowSetMap = new java.util.HashMap();
		java.util.List<String[]> keyValList = new ArrayList<String[]>();
		java.util.List<String> tmpKeyValStr = new ArrayList<String>();
		Object[] keyVals = primeKeyRowSetMap.keySet().toArray();
		for (int i = 0; keyVals != null && i < keyVals.length; i++) {
			String keyVal = (String) keyVals[i];
			// 和primeKey的索引一致
			String[] vals = StringFunction.split(keyVal, "-");
			String[] data = new String[keys.length];
			String tmpKey = "";
			for (int j = 0; keys != null && j < keys.length; j++) {
				int index = java.util.Arrays.asList(primeKey).indexOf(keys[j]);
				if (index < 0 || index >= vals.length)
					continue;
				data[j] = vals[index];
				tmpKey += vals[index];
				if (j > 0)
					tmpKey += "-";
			}
			// 值重复的只放一遍
			if (!tmpKeyValStr.contains(tmpKey)) {
				tmpKeyValStr.add(tmpKey);
				keyValList.add(data);
			}
		}
		return keyValList;
	}

	/**
	 * 获取主键值列表，一般List存放一串索引值
	 * 
	 * @param primeKey
	 *            String
	 * @param primeKeyVal
	 *            String
	 * @return java.util.List<String>
	 */
	public java.util.List<String> getPrimeKeyByKey(String primeKey,
			String primeKeyVal) {
		java.util.Map map = getPrimeKeyKeyMap(primeKey);
		return (java.util.List<String>) map.get(primeKeyVal);
	}

	/**
	 * 获取所有主键
	 * 
	 * @param keys
	 *            String[]
	 * @param vals
	 *            String[]
	 * @return List
	 */
	public java.util.List<String> getPrimeKeyByKey(String[] keys, String[] vals) {
		if (keys == null || vals == null || keys.length != vals.length)
			return null;
		java.util.List lists = new java.util.ArrayList();
		for (int i = 0; keys != null && i < keys.length; i++) {
			lists.add(getPrimeKeyByKey(keys[i], vals[i]));
		}
		return DataSetUtils.intersectList(lists);
	}

	/**
	 * 根据主键获取行数据对象数据集
	 * 
	 * @param primeKey
	 *            String
	 * @param primeKeyVal
	 *            String
	 * @return ISSPDataSet
	 */
	public ISSPDataSet getDataSetByKey(String primeKey, String primeKeyVal) {
		ISSPDataSet eds = ISSPDataSet.getInstance(getTableName(),
				ISSPDataSet._DATA_SET_VIEW_);
		eds.setPrimeKey(this.getPrimeKey());
		java.util.List<String> list = getPrimeKeyByKey(primeKey, primeKeyVal);
		for (int i = 0; list != null && i < list.size(); i++) {
			eds.insertRowSet(getRowSet(list.get(i)));
		}
		return eds;
	}

	/**
	 * 根据所有主键获取行数据对象数据集
	 * 
	 * @param keys
	 *            String[]
	 * @param vals
	 *            String[]
	 * @return ISSPDataSet
	 */
	public ISSPDataSet getDataSetByKey(String[] keys, String[] vals) {
		ISSPDataSet eds = ISSPDataSet.getInstance(getTableName(),
				ISSPDataSet._DATA_SET_VIEW_);
		eds.setPrimeKey(this.getPrimeKey());
		java.util.List<String> list = getPrimeKeyByKey(keys, vals);
		for (int i = 0; list != null && i < list.size(); i++) {
			eds.insertRowSet(getRowSet(list.get(i)));
		}
		return eds;
	}

	/**
	 * 多主键的情况下，根据主键Key获取主键索引
	 * 
	 * @param primeKey
	 *            String
	 * @return int
	 */
	public int getPrimeKeyIndex(String primeKey) {
		if (getPrimeKey() == null)
			return -1;
		return Arrays.asList(getPrimeKey()).indexOf(primeKey);
	}

	/**
	 * 多主键的情况下，主键列获取最应该主键索引列表
	 * 
	 * @param primeKey
	 *            String
	 * @return Map
	 */
	public java.util.Map getPrimeKeyKeyMap(String primeKey) {
		if (primeKeyKeyMap == null)
			primeKeyKeyMap = new java.util.HashMap<String, Map>();
		if (primeKeyKeyMap.get(primeKey) == null) {
			java.util.Map<String, java.util.List<String>> map = new java.util.HashMap<String, java.util.List<String>>();
			primeKeyKeyMap.put(primeKey, map);
		}
		return primeKeyKeyMap.get(primeKey);
	}

	/**
	 * 移除所以行记录key对象的数值，设为null
	 * 
	 * @param key
	 *            String
	 */
	public void RemoveObject(String key) {
		int count = this.getRowCount();
		for (int i = 0; i < count; i++) {
			this.getRowSet(i).putObject(key, null);
		}
	}

	/**
	 * rows:行数
	 */
	public String toString() {
		String aa = super.toString();
		if (this.getRowCount() > 0)
			aa += " rows:" + getRowCount();
		return aa;
	}

	/**
	 * 将参数ISSPDataSet所以行记录增加到本对象中
	 * 
	 * @param dataSet
	 *            ISSPDataSet
	 */
	public void addAll(ISSPDataSet dataSet) {
		boolean add = dataSet != null && dataSet.getRowCount() > 0;
		for (int i = 0; dataSet != null && i < dataSet.getRowCount(); i++) {
			ISSPRowSet rowSet = dataSet.getRowSet(i);
			addRowSet(rowSet);
		}
		if (add && this.getPrimeKey() != null)
			this.buildPrimeKeyIndex();
	}

	/**
	 * 子行记录对象，参数传输不进行序列化
	 */
	protected transient java.util.List childRowSet = null;

	/**
	 * 插入子行记录对象
	 * 
	 * @param rowSet
	 *            ISSPRowSet
	 */
	protected void insertChildRowSet(ISSPRowSet rowSet) {
		if (childRowSet == null)
			childRowSet = new java.util.ArrayList();
		if (childRowSet.indexOf(rowSet) == -1)
			childRowSet.add(rowSet);
	}

	/**
	 * 删除子行记录对象
	 * 
	 * @param rowSet
	 *            ISSPRowSet
	 * @return ISSPRowSet
	 */
	protected ISSPRowSet removeChildRowSet(ISSPRowSet rowSet) {
		if (childRowSet == null)
			return null;
		if (childRowSet.remove(rowSet))
			return rowSet;
		return null;
	}

	/**
	 * 分级数据集对象
	 */
	protected ISSPDataSet hierarchyDataSet = null;

	/**
	 * 获取分级数据集对象
	 * 
	 * @return ISSPDataSet
	 */
	public ISSPDataSet getHierarchyDataSet() {
		return hierarchyDataSet;
	}

	/**
	 * 设置分级数据集对象
	 * 
	 * @param ds
	 */
	public void setHierarchyDataSet(ISSPDataSet ds) {
		hierarchyDataSet = ds;
	}

	/**
	 * 已删除行的记录<key:主键, value:ISSPRowSet>
	 */
	protected Map<String, ISSPRowSet> deletedRowsMap = null;

	/**
	 * 获取已删除行的记录
	 * 
	 * @return Map<String, ISSPRowSet>
	 */
	public Map<String, ISSPRowSet> getDeletedRowsMap() {
		return deletedRowsMap;
	}

	/**
	 * 设置已删除行的记录
	 * 
	 * @param deletedRowsMap
	 *            Map<String, ISSPRowSet>
	 */
	public void setDeletedRowsMap(Map<String, ISSPRowSet> deletedRowsMap) {
		this.deletedRowsMap = deletedRowsMap;
	}

	private java.util.Map<String, ISSPDataSet> dataSetMap = null;

	/**
	 * 获取数据集对象Map
	 * 
	 * @return java.util.Map<String,ISSPDataSet>
	 */
	public java.util.Map<String, ISSPDataSet> getDataSetMap() {
		return dataSetMap;
	}

	/**
	 * 设置数据集对象Map
	 * 
	 * @param dsm
	 *            Map<String, ISSPRowSet>
	 */
	public void setDataSetMap(java.util.Map<String, ISSPDataSet> dsm) {
		dataSetMap = dsm;
	}

	/**
	 * 获取数据集对象
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
	 * 存放数据集对象
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
	}

	/**
	 * 根据权限标志位存放权限数据集对象
	 * 
	 * @param F_Gn
	 *            int
	 * @param dataSet
	 *            ISSPDataSet
	 */
	public void setLimitDataSet(int F_Gn, ISSPDataSet dataSet) {
		String key = "F_G" + F_Gn;
		setDataSet(key, dataSet);
	}

	/**
	 * 根据权限标志位获取权限数据集对象
	 * 
	 * @param F_Gn
	 *            int
	 * @return ISSPDataSet
	 */
	public ISSPDataSet getLimitDataSet(int F_Gn) {
		String key = "F_G" + F_Gn;
		return getDataSet(key);
	}

	private Object userObject = null;

	/**
	 * 获取自定义对象
	 * 
	 * @return Object
	 */
	public Object getUserObject() {
		return userObject;
	}

	/**
	 * 设置自定义对象
	 * 
	 * @param userObject
	 *            Object
	 */
	public void setUserObject(Object userObject) {
		this.userObject = userObject;
	}

	private ISSPDataSet insertDataPool;

	/**
	 * 插入数据缓冲区
	 * 
	 * @return
	 */
	public ISSPDataSet getInsertDataPool() {
		return insertDataPool;
	}

	public void setInsertDataPool(ISSPDataSet insertDataPool) {
		this.insertDataPool = insertDataPool;
	}

	private ISSPDataSet updateDataPool;

	/**
	 * 更新数据缓冲区
	 * 
	 * @return
	 */
	public ISSPDataSet getUpdateDataPool() {
		return updateDataPool;
	}

	public void setUpdateDataPool(ISSPDataSet updateDataPool) {
		this.updateDataPool = updateDataPool;
	}

	private ISSPDataSet deleteDataPool;

	/**
	 * 删除数据缓冲区
	 * 
	 * @return
	 */
	public ISSPDataSet getDeleteDataPool() {
		return deleteDataPool;
	}

	public void setDeleteDataPool(ISSPDataSet deleteDataPool) {
		this.deleteDataPool = deleteDataPool;
	}

	private boolean dataSetPool = false;

	/**
	 * 是否缓冲数据的数据集
	 * 
	 * @return
	 */
	public boolean isDataSetPool() {
		return dataSetPool;
	}

	public void setDataSetPool(boolean dataSetPool) {
		this.dataSetPool = dataSetPool;
	}

	// 数据处理缓存，默认，不启用
	private boolean startDataPool = false;

	/**
	 * 是否启用数据缓冲池
	 * 
	 * @return
	 */
	public boolean isStartDataPool() {
		return startDataPool;
	}

	public void setStartDataPool(boolean startDataPool) {
		this.startDataPool = startDataPool;
	}

	/**
	 * 删除数据到缓冲区
	 * 
	 * @param updateRowSet
	 */
	private void insertDataToDeletePool(ISPRowSet deleteRowSet) {
		if (!dataSetPool)
			return;
		if (!startDataPool)
			return;

		// 插入状态的记录集只允许插入插入数据的缓冲区
		if (deleteRowSet.getDataStatus() == ISPRowSet._DATA_STATUS_INSERT_) {
			this.getInsertDataPool().removeRowSet(deleteRowSet);
		} else {
			this.getUpdateDataPool().removeRowSet(deleteRowSet);
			this.getDeleteDataPool().removeRowSet(deleteRowSet);
			this.getDeleteDataPool().insertRowSet(deleteRowSet);
		}
	}

	/**
	 * 插入数据到缓冲区
	 * 
	 * @param updateRowSet
	 */
	private void insertDataToInsertPool(ISPRowSet insertRowSet) {
		if (!dataSetPool)
			return;
		if (!startDataPool)
			return;
		this.getInsertDataPool().removeRowSet(insertRowSet);
		this.getInsertDataPool().insertRowSet(insertRowSet);
	}

}