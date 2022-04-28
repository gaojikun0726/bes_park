package com.core.common.conn.db;

import com.core.common.data.DataSetUtils;
import com.core.common.data.ISSPDataSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * MySQL CRUD操作类
 * 
 * @author Alvin
 * @datetime 2018年5月23日
 *
 */
public class MySQLDBUtil {
	
	private static final Logger log = LoggerFactory.getLogger(MySQLDBUtil.class);
	
	public Statement getStatement(Connection conn) {
		if (conn == null) {
			return null;
		}
		Statement statement = null;
		try {
			statement = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return statement;
	}

	/**
	 * executeUpdate 用于执行INSERT、UPDATE 或 DELETE 语句以及 SQL DDL（数据定义语言）语句
	 * 
	 * @param conn
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public static boolean executeUpdateSQL(Connection conn, String sql, List<Object> params) throws SQLException {
		if (conn == null) {
			return false;
		}
		boolean flag = false;
		int result = -1;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			log.info("executeUpdateSQL: " + sql);
			int index = 1;
			if (params != null && !params.isEmpty()) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(index++, params.get(i));
				}
				log.info("SQLParam: " + params.toString());
			}
			result = pstmt.executeUpdate();
			flag = result >= 0 ? true : false;
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	/**
	 * execute：用于执行返回多个结果集、多个更新计数或二者组合的语句。
	 * 
	 * @param conn
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public static boolean executeSQL(Connection conn, String sql, List<Object> params) throws SQLException {
		if (conn == null) {
			return false;
		}
		boolean flag = false;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			log.info("executeSQL: " + sql);
			int index = 1;
			if (params != null && !params.isEmpty()) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(index++, params.get(i));
				}
				log.info("SQLParam: " + params.toString());
			}
			flag = pstmt.execute();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	/**
	 * 用于产生单个结果集的语句，例如 SELECT 语句。
	 * 
	 * @param conn
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	public static ISSPDataSet executeQuerySQL(Connection conn, String sql, List<Object> params) throws Exception {
		if (conn == null) {
			return null;
		}
		int index = 1;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		ISSPDataSet dataSet = null;
		try {
			pstmt = conn.prepareStatement(sql);
			log.info("executeQuerySQL: " + sql);
			if (params != null && !params.isEmpty()) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(index++, params.get(i));
				}
				log.info("SQLParam: " + params.toString());
			}
			rs = pstmt.executeQuery();
			dataSet = new ISSPDataSet();
			DataSetUtils.resultSet2DataSet(rs, dataSet);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dataSet;
	}
	
	/**
	 * 批量执行
	 * 
	 * @param conn
	 * @param sql
	 * @param params
	 * @return
	 */
	public static boolean executeBatchSQL(Connection conn, String sql, Map<String, List<Object>> params) {
		return executeBatchSQL(conn, sql, params, true);
	}
	
	/**
	 * 迭代批量执行方法，autoCommit为false时需自己设置事务提交方式
	 * 
	 * @param conn
	 * @param sql
	 * @param params
	 * @param autoCommit
	 * @return
	 */
	public static boolean executeBatchSQL(Connection conn, String sql, Map<String, List<Object>> params, boolean autoCommit) {
		if (conn == null) {
			return false;
		}
		boolean flag = false;
		PreparedStatement pstmt = null;
		try {
			if (autoCommit) {
				//设置事务提交方式false
				MySQLDBUtil.setAutoCommit(conn, false);
			}
			pstmt = conn.prepareStatement(sql);
			log.info("executeBatchSQL: " + sql);
			int sqlTag = 0;
			//遍历map中的值 
			for (List<Object> value : params.values()) {
				int index = 1;
				if (value != null && !value.isEmpty()) {
					for (int i = 0; i < value.size(); i++) {
						pstmt.setObject(index++, value.get(i));
					}
					pstmt.addBatch();
					log.info("SQLParam: " + value.toString());
					if ((sqlTag + 1) % 500 == 0) {
						pstmt.executeBatch();
						pstmt.clearBatch();
					}
					sqlTag++;
				}
			}
			pstmt.executeBatch();
			//提交事务
			MySQLDBUtil.commit(conn);
			if (autoCommit) {				
				MySQLDBUtil.setAutoCommit(conn, true);
			}
			flag = true;
		} catch (SQLException e) {
			//回滚
			try {
				MySQLDBUtil.rollback(conn);
				if (autoCommit) {					
					MySQLDBUtil.setAutoCommit(conn, true);
				}
			} catch (SQLException e1) {
			}
			return flag;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
	

	/**
	 * 回滚
	 * @throws SQLException 
	 */
	public static void rollback(Connection conn) throws SQLException {
		if (conn == null) {
			return;
		}
		conn.rollback();
		log.info("事物回滚！");
	}

	/**
	 *
	 * @param savepoint
	 *            Savepoint
	 */
	public static void rollback(Connection conn, Savepoint savepoint)
			throws SQLException {
		if (conn == null) {
			return;
		}
		conn.rollback(savepoint);
	}

	/**
	 * 设置事务提交方式
	 * 
	 * @param autoCommit
	 *            boolean
	 * @throws SQLException 
	 */
	public static void setAutoCommit(Connection conn, boolean autoCommit) throws SQLException {
		if (conn == null) {
			return;
		}
		conn.setAutoCommit(autoCommit);
	}

	/**
	 * 事物提交
	 * @throws SQLException 
	 */
	public static void commit(Connection conn) throws SQLException {
		if (conn == null) {
			return;
		}
		conn.commit();
	}

	public static void closeConnnection(Connection conn) throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}

	public static void closeStatement(PreparedStatement pstmt) throws SQLException {
		if (pstmt != null) {
			pstmt.close();
		}
	}

	/**
	 * 关闭conn、pstmt连接
	 * @throws SQLException 
	 */
	public static void closeAll(Connection conn, PreparedStatement pstmt) throws SQLException {
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

}