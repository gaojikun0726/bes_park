package com.framework.common.utils;

import com.core.ApplicationContextProvider;
import com.core.common.conn.db.MySQLDBObject;
import com.core.common.conn.db.MySQLDBUtil;
import com.core.common.data.ISSPDataSet;
import com.core.config.db.OperationConfig;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.config.shiro.vo.Principal;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import org.apache.shiro.SecurityUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
/**
 * 类名称： OperationLog 
 * 类描述： 操作日志 
 * 创建人： gongfanfei 
 * 修改时间： 2018年11月5日
 * @version 1.0.0
 */
public class OperationLog {
	
	
	
	
	/**
	 * 添加操作
	 * @param tableId
	 * @param tableName
	 * @return 
	 */
	public static void insertLog(String tableId,String tableName){
		OperationConfig operatotObject = (OperationConfig) ApplicationContextProvider.getBean("operationConfig");
		String sysDataBaseUseable = operatotObject.getSysDataBaseUseable();
		if ("1".equals(sysDataBaseUseable)) {
			insert(tableId, tableName);
		}
	}
	/**
	 * 删除操作
	 * @param tableId
	 * @param tableName
	 */
	public static void deleteLog(String tableId,String tableName){
		OperationConfig operatotObject = (OperationConfig) ApplicationContextProvider.getBean("operationConfig");
		String sysDataBaseUseable = operatotObject.getSysDataBaseUseable();
		if ("1".equals(sysDataBaseUseable)) {
			delete(tableId, tableName);
		}
	}
	/**
	 * 编辑前操作
	 * @param tableId
	 * @param tableName
	 */
	public static Map<String, Object> editStartLog(String tableId,String tableName){
		OperationConfig operatotObject = (OperationConfig) ApplicationContextProvider.getBean("operationConfig");
		String sysDataBaseUseable = operatotObject.getSysDataBaseUseable();
		Map<String, Object> returnMap = new HashMap<>();
		if ("1".equals(sysDataBaseUseable)) {
			returnMap = updateStart(tableId, tableName);
		}
		return returnMap;
	}
	/**
	 * 编辑后操作
	 * @param tableId
	 * @param tableName
	 */
	public static void  editEndLog(String tableId,String tableName,Map<String, Object> startMap){
		OperationConfig operatotObject = (OperationConfig) ApplicationContextProvider.getBean("operationConfig");
		String sysDataBaseUseable = operatotObject.getSysDataBaseUseable();
		if ("1".equals(sysDataBaseUseable)) {
			updateEnd(tableId, tableName,startMap);
		}
	}
	
	
	/**
	 * 获取数据库名
	 * @param conn
	 * @return
	 */
	public static String getDadabase(Connection conn){
		String database = "";
		try {
			String url;
			url = conn.getMetaData().getURL();
			database = url.substring(url.indexOf("3306/")+5, url.lastIndexOf("?useUnicode"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return database;
	}
	
	
	/**
	 * 添加操作
	 * @param tbid 查询指定表的id
	 * @param tbname 数据库表名
	 * @throws Exception
	 */
	public  static void insert(String tbid, String tbname){
		Connection conn = null;
		MySQLDBObject sqldbObject = (MySQLDBObject) ApplicationContextProvider.getBean("mySQLDBObject");
		conn = (Connection) sqldbObject.getConnection();
		try {
			String database = getDadabase(conn);
			String f_guid =  UUIDUtil.getRandom32BeginTimePK();
	    	insertMainTable(tbid,tbname,conn,"0",f_guid);//插入日志主表
			
	        //获取 key-value: 字段-字段注释
	    	Map<String, String> map = new HashMap<String, String>();
	    	map = getTableKeyComment(tbid,tbname,conn);	
	    	
			//获取表主键
			String getPrimarySql = "select * from information_schema.key_column_usage  where constraint_name='PRIMARY' AND table_schema = '"+database+"'  AND  table_name='"+tbname+"';";
			ISSPDataSet getPrimaryDataSet = MySQLDBUtil.executeQuerySQL(conn, getPrimarySql.toString(), null);
			int rowCounut3 = getPrimaryDataSet.getRowCount();
			List<String> parmaryKey = new ArrayList<String>();
			for (int i = rowCounut3 - 1; i >= 0; i--) {
				parmaryKey.add(getPrimaryDataSet.getRowSet(i).getString("COLUMN_NAME", ""));
			}
			String column_name = getPrimaryDataSet.getRowSet(0).getString("COLUMN_NAME", "");
			
			
			//查询所有字段信息，插入日志从表
	        String getDataSql = "select * from "+tbname+" where "+column_name+" = '"+tbid+"'";
	        ISSPDataSet allInfoDataSet = MySQLDBUtil.executeQuerySQL(conn, getDataSql.toString(), null);
	        
	        Map<String, String> newMap = new HashMap<String, String>();
	        Set<String> keySet = map.keySet();
	        for (String key : keySet) {  
	            newMap.put(key, allInfoDataSet.getRowSet(0).getString(key, ""));
	        } 
	        String keys = "";
	        String values = "";
	        String comments = "";
	        for (String key : keySet) {  
	        	 keys = keys+"#@"+key;
	        	 values = values+"#@"+newMap.get(key);
	        	 comments = comments+"#@"+map.get(key);
	        } 
	        List<Object> insertContentList = new ArrayList<>();
			String f_guid_content =  UUIDUtil.getRandom32BeginTimePK();//获取操作日志内容表guid
			
			insertContentList.add(f_guid_content);//id
			insertContentList.add(f_guid);//日志ID
			insertContentList.add(keys);//表键
			insertContentList.add(values);//表键值
			insertContentList.add(comments);//表键注释
			//插入日志内容表
	        String contentTableSql = "INSERT INTO es_operation_log_content (F_ID,F_LOG_ID,F_TB_KEY,F_TB_VALUE,F_CURRENTTB_VALUE,F_COMMENT,F_CRDATE,F_CHDATE) VALUES(?,?,?,?,'',?,current_timestamp(),current_timestamp())";
			MySQLDBUtil.executeUpdateSQL(conn, contentTableSql, insertContentList);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
    }
    /**
     * 删除操作
     * @param tbid
     * @param tbname
     * @throws Exception
     */
    public  static void delete(String tbid, String tbname){
    	Connection conn = null;
    	MySQLDBObject sqldbObject = (MySQLDBObject) ApplicationContextProvider.getBean("mySQLDBObject");
    	conn = (Connection) sqldbObject.getConnection();
    	try {
	    	String database = getDadabase(conn);
	    	String f_guid =  UUIDUtil.getRandom32BeginTimePK();
	    	
	    	//插入日志主表
	    	insertMainTable(tbid,tbname,conn,"1",f_guid);
	    	//获取 key-value: 字段-字段注释
	    	Map<String, String> map = new HashMap<String, String>();
	    	map = getTableKeyComment(tbid,tbname,conn);	
	    	
	    	//获取表主键
	    	String getPrimarySql = "select * from information_schema.key_column_usage  where constraint_name='PRIMARY' AND table_schema = '"+database+"' AND  table_name='"+tbname+"';";
	    	ISSPDataSet getPrimaryDataSet = MySQLDBUtil.executeQuerySQL(conn, getPrimarySql.toString(), null);
	    	int rowCounut3 = getPrimaryDataSet.getRowCount();
	    	List<String> parmaryKey = new ArrayList<String>();
	    	for (int i = rowCounut3 - 1; i >= 0; i--) {
	    		parmaryKey.add(getPrimaryDataSet.getRowSet(i).getString("COLUMN_NAME", ""));
	    	}
	    	String column_name = getPrimaryDataSet.getRowSet(0).getString("COLUMN_NAME", "");
	    	
	    	
	    	//查询所有字段信息，插入日志从表
	    	String getDataSql = "select * from "+tbname+" where "+column_name+" = '"+tbid+"'";
	    	ISSPDataSet allInfoDataSet = MySQLDBUtil.executeQuerySQL(conn, getDataSql.toString(), null);
	    	
	    	Map<String, String> newMap = new HashMap<String, String>();
	    	Set<String> keySet = map.keySet();
	    	for (String key : keySet) {  
	    		newMap.put(key, allInfoDataSet.getRowSet(0).getString(key, ""));
	    	} 
	    	String keys = "";
	    	String values = "";
	    	String comments = "";
	    	for (String key : keySet) {  
	    		keys = keys+"#@"+key;
	    		values = values+"#@"+newMap.get(key);
	    		comments = comments+"#@"+map.get(key);
	    	} 
	    	List<Object> insertContentList = new ArrayList<>();
	    	String f_guid_content =  UUIDUtil.getRandom32BeginTimePK();//获取操作日志内容表guid
	    	
	    	insertContentList.add(f_guid_content);//id
	    	insertContentList.add(f_guid);//日志ID
	    	insertContentList.add(keys);//表键
	    	insertContentList.add(values);//表键值
	    	insertContentList.add(comments);//表键注释
	    	//插入日志内容表
	    	String contentTableSql = "INSERT INTO es_operation_log_content (F_ID,F_LOG_ID,F_TB_KEY,F_TB_VALUE,F_CURRENTTB_VALUE,F_COMMENT,F_CRDATE,F_CHDATE) VALUES(?,?,?,?,'',?,current_timestamp(),current_timestamp())";
	    	MySQLDBUtil.executeUpdateSQL(conn, contentTableSql, insertContentList);
    	}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
    //更新前存储id对应一条数据：字段-字段值集合
    //static Map<String, String> globalMap = new HashMap<String, String>();
    //更新前存储日志主表对应的日志表guid
    //static String global_f_guid = "";
    /**
     * 更新前
     * @param tbid
     * @param tbname
     * @throws Exception
     */
    public static Map<String, Object> updateStart(String tbid, String tbname){
    	Connection conn = null;
    	MySQLDBObject sqldbObject = (MySQLDBObject) ApplicationContextProvider.getBean("mySQLDBObject");
    	conn = (Connection) sqldbObject.getConnection();
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try{
	    	String database = getDadabase(conn);
	    	//获取guid
	    	String f_guid =  UUIDUtil.getRandom32BeginTimePK();
	    	//global_f_guid = f_guid; 
	    	
	    	insertMainTable(tbid,tbname,conn,"2",f_guid);//插入日志主表
	    	
	    	//获取 key-value: 字段-字段注释
	    	Map<String, String> map = new HashMap<String, String>();
	    	map = getTableKeyComment(tbid,tbname,conn);
	    	
	    	//获取表主键
	    	String getPrimarySql = "select * from information_schema.key_column_usage  where constraint_name='PRIMARY' AND table_schema = '"+database+"' AND  table_name='"+tbname+"';";
	    	ISSPDataSet getPrimaryDataSet = MySQLDBUtil.executeQuerySQL(conn, getPrimarySql.toString(), null);
	    	int rowCounut3 = getPrimaryDataSet.getRowCount();
	    	List<String> parmaryKey = new ArrayList<String>();
	    	for (int i = rowCounut3 - 1; i >= 0; i--) {
	    		parmaryKey.add(getPrimaryDataSet.getRowSet(i).getString("COLUMN_NAME", ""));
	    	}
	    	String column_name = getPrimaryDataSet.getRowSet(0).getString("COLUMN_NAME", "");
	    	
	    	//查询所有字段信息，插入日志从表
	    	String getDataSql = "select * from "+tbname+" where "+column_name+" = '"+tbid+"'";
	    	ISSPDataSet allInfoDataSet = MySQLDBUtil.executeQuerySQL(conn, getDataSql.toString(), null);
	    	
	    	Map<String, String> newMap = new HashMap<String, String>();
	    	Set<String> keySet = map.keySet();
	    	for (String key : keySet) {  
	    		newMap.put(key, allInfoDataSet.getRowSet(0).getString(key, ""));
	    	} 
	    	//globalMap = newMap;
	    	returnMap.put("0", f_guid);
	    	returnMap.put("1", newMap);
    	}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
    	return returnMap;
    }
    
   
    /**
     * 更新后
     * @param tbid
     * @param tbname
     * @throws Exception
     */
    public  static void updateEnd(String tbid, String tbname,Map<String, Object> startMap){
    	Connection conn = null;
    	MySQLDBObject sqldbObject = (MySQLDBObject) ApplicationContextProvider.getBean("mySQLDBObject");
    	conn = (Connection) sqldbObject.getConnection();
    	try{
	    	String database = getDadabase(conn);
	    	//获取 key-value: 字段-字段注释
	    	Map<String, String> map = new HashMap<String, String>();
	    	map = getTableKeyComment(tbid,tbname,conn);
	    	
	    	//获取表主键
	    	String getPrimarySql = "select * from information_schema.key_column_usage  where  constraint_name='PRIMARY' AND table_schema = '"+database+"' AND  table_name='"+tbname+"';";
	    	ISSPDataSet getPrimaryDataSet = MySQLDBUtil.executeQuerySQL(conn, getPrimarySql.toString(), null);
	    	int rowCounut3 = getPrimaryDataSet.getRowCount();
	    	List<String> parmaryKey = new ArrayList<String>();
	    	for (int i = rowCounut3 - 1; i >= 0; i--) {
	    		parmaryKey.add(getPrimaryDataSet.getRowSet(i).getString("COLUMN_NAME", ""));
	    	}
	    	String column_name = getPrimaryDataSet.getRowSet(0).getString("COLUMN_NAME", "");
	    	
	    	
	    	@SuppressWarnings("unchecked")
			Map<String, String> globalMap  = (Map<String, String>) startMap.get("1");
	    	String global_f_guid = (String) startMap.get("0");
	    	
	    	//查询所有字段信息，插入日志从表
	    	String getDataSql = "select * from "+tbname+" where "+column_name+" = '"+tbid+"'";
	    	ISSPDataSet allInfoDataSet = MySQLDBUtil.executeQuerySQL(conn, getDataSql.toString(), null);
	    	
	    	Map<String, String> newMap = new HashMap<String, String>();
	    	List<String> initkeyList = new ArrayList<String>();
	    	Set<String> keySet = map.keySet();
	    	for (String key : keySet) {  
	    		newMap.put(key, allInfoDataSet.getRowSet(0).getString(key, ""));
	    	} 
	    	for (String key : keySet) {  
	    		if(!newMap.get(key).equals(globalMap.get(key))){
	    			initkeyList.add(key);
	    		}
	    	}     	
	    	String keys = "";
	    	String values = "";
	    	String currentValues = "";
	    	String comments = "";
	    	for (int i = 0; i < initkeyList.size(); i++) {
	    		keys = keys+"#@"+initkeyList.get(i);
	    		values = values+"#@"+globalMap.get(initkeyList.get(i));
	    		currentValues = currentValues+"#@"+newMap.get(initkeyList.get(i));
	    		comments = comments+"#@"+map.get(initkeyList.get(i));
	    	} 
	    	List<Object> insertContentList = new ArrayList<>();
	    	String f_guid_content =  UUIDUtil.getRandom32BeginTimePK();//获取操作日志内容表guid
	    	
	    	insertContentList.add(f_guid_content);//id
	    	insertContentList.add(global_f_guid);//日志ID
	    	insertContentList.add(keys);//表键
	    	insertContentList.add(values);//表键值
	    	insertContentList.add(currentValues);//表键当前值
	    	insertContentList.add(comments);//表键注释
	    	//插入日志内容表
	    	String contentTableSql = "INSERT INTO es_operation_log_content (F_ID,F_LOG_ID,F_TB_KEY,F_TB_VALUE,F_CURRENTTB_VALUE,F_COMMENT,F_CRDATE,F_CHDATE) VALUES(?,?,?,?,?,?,current_timestamp(),current_timestamp())";
	    	MySQLDBUtil.executeUpdateSQL(conn, contentTableSql, insertContentList);
	    	}catch (Exception e) {
				e.printStackTrace();
			}finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
	    }
    
    /**
     * 添加操作日志通用处理函数
     * @param tbid
     * @param tbname 
     * @param conn 
     * @param type 0：增加 1：删除 2： 修改'
     * @param f_guid
     */
    public static void insertMainTable(String tbid, String tbname,Connection conn,String type,String f_guid){
		try {
			String database = getDadabase(conn);
			//查询表注释
			StringBuffer sql = new StringBuffer("SELECT  * FROM information_schema.TABLES WHERE table_schema = '"+database+"' AND  table_name='"+tbname+"'" );
			ISSPDataSet dataSet;
			dataSet = MySQLDBUtil.executeQuerySQL(conn, sql.toString(), null);
	    	String comment = dataSet.getRowSet(0).getString("TABLE_COMMENT", "");
	    	// 获取当前用户的用户名
	    	Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
	    	ESUser user = principa.getUser();
	    	String yhbh = user.getF_yhbh();
	    	
	    	List<Object> insertList = new ArrayList<>();
	    	insertList.add(f_guid);//id
	    	insertList.add(yhbh);//用户名
	    	insertList.add(type);//2:修改
	    	insertList.add(tbname);//表名
	    	insertList.add(tbid);//表id
	    	insertList.add(comment);//表注释
	    	
	    	//插入日志主表
	    	String mainTableSql = "INSERT INTO es_operation_log (F_ID,F_YHBH,F_TYPE,F_TABLE_NAME,F_TABLE_ID,F_COMMENT,F_OPERATION_TIME,F_CRDATE,F_CHDATE) VALUES(?,?,?,?,?,?,current_timestamp(),current_timestamp(),current_timestamp())";
	    	MySQLDBUtil.executeUpdateSQL(conn, mainTableSql, insertList);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    /**
     * 获取表字段以及字段注释
     * @param tbid
     * @param tbname
     * @param conn
     * @return
     */
    public static Map<String, String> getTableKeyComment(String tbid, String tbname,Connection conn){
    	Map<String, String> map = new HashMap<String, String>();
    	try {
    		String database = getDadabase(conn);
    		//获取 key-value: 字段-字段注释
    		String keyValueSql = "select * from information_schema.columns where table_schema = '"+database+"' AND  table_name = '"+tbname+"';";
	    	ISSPDataSet keyValueDataSet;
				keyValueDataSet = MySQLDBUtil.executeQuerySQL(conn, keyValueSql.toString(), null);
	    	int rowCounut2 = keyValueDataSet.getRowCount();
	    	for (int i = rowCounut2 - 1; i >= 0; i--) {
	    		map.put(keyValueDataSet.getRowSet(i).getString("COLUMN_NAME", ""),keyValueDataSet.getRowSet(i).getString("COLUMN_COMMENT", "")) ;//将字段和字段注释放到map中，字段作为key
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return map;
    }
    
}
