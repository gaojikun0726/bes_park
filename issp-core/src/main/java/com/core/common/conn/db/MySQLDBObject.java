package com.core.common.conn.db;

import com.core.config.db.ISSPDBConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * MySQL JDBC
 * 
 * @author Alvin
 * @datetime 2018年5月23日
 *
 */
@Component
public class MySQLDBObject {
	
	private static final Logger log = LoggerFactory.getLogger(MySQLDBObject.class);

	@Autowired
	private ISSPDBConfig dbConfig;

	/**
	 * 初始化Conn
	 * 
	 * @return conn
	 */
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(dbConfig.getDriverClassName());
			conn = DriverManager.getConnection(dbConfig.getUrl(), dbConfig.getUsername(), dbConfig.getPassword());// 获取连接
			log.info("数据库Conn加载成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}