package com.core.common.conn.db;

import com.core.config.db.ISSPMongoDBConfig;
import com.mongodb.MongoClient;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * MongoDB JDBC
 * @Description:
 * @author LvSihan
 * @version: 
 * @date:2018年8月29日
 */
@Component
public class MongoDBObject {
	private static final Logger log = LoggerFactory.getLogger(MongoDBObject.class);
	
	@Autowired
	private ISSPMongoDBConfig mongoDBConfig;
	/**
	 * MongoDB服务链接
	 */
	private MongoClient mongoClient;
	/**
	 * 支持事务的session
	 */
	private ClientSession clientSession;

	/**
	 * 获取mongoDB连接
	 * @return
	 */
//	public MongoDatabase getMongoDataBase() {
//		 MongoDatabase mongoDataBase = null;
//		try {
//			//连接到mongodb服务
//			MongoClientURI uri = new MongoClientURI(mongoDBConfig.getUri());
//			MongoClient mongoClient = new MongoClient(uri);
//			setMongoClient(mongoClient);
//			// 连接到mongodb数据库
//			mongoDataBase =mongoClient.getDatabase(mongoDBConfig.getDefaultDB());
//			//设置session
//			ClientSession clientSession = mongoClient.startSession();
//			setClientSession(clientSession);
//			log.info("MongoDb连接成功！");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return mongoDataBase;
//	}
	
    public void closeMongoClient(MongoDatabase mongoDataBase,MongoClient mongoClient ) {  
        if (mongoDataBase != null) {  
            mongoDataBase = null;  
        }  
        if (mongoClient != null) {  
            mongoClient.close();  
        }  
        log.info("CloseMongoClient successfully");
    } 
    
	public ClientSession getClientSession() {
		return clientSession;
	}
	public void setClientSession(ClientSession clientSession) {
		this.clientSession = clientSession;
	}

	public MongoClient getMongoClient() {
		return mongoClient;
	}

	public void setMongoClient(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}

}
