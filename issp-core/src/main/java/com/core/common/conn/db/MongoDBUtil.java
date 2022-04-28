package com.core.common.conn.db;

import com.alibaba.fastjson.JSONObject;
import com.core.common.data.DataSetUtils;
import com.core.common.data.ISSPDataSet;
import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class MongoDBUtil {
	private static final Logger log = LoggerFactory.getLogger(MongoDBUtil.class);
	/**
	 * 插入一条
	 * @param db
	 * @param table
	 * @param document
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static boolean insertOne (MongoDatabase db, String table, Document document) {
		if (db == null) {
			return false;
		}
		//获取数据集合
		MongoCollection<Document> collection = db.getCollection(table);
		try {
			collection.insertOne(document);
			long count = collection.count();
			log.info("文档插入成功,count: " + count);
			return true;
		} catch (Exception e) {
			log.info("文档插入失败");
			return false;
		}
	}

	/**
	 * 插入多条
	 * @param db
	 * @param clientSession 
	 * @param table
	 * @param documents
	 * @return
	 */
    @SuppressWarnings("deprecation")
	public static boolean insertMany(MongoDatabase db, ClientSession clientSession, String table, List<Document> documents ) {
    	if (db == null) {
			return false;
		}
        MongoCollection<Document> collection = db.getCollection(table);
       
        long preCount = collection.count();
        try {
        	clientSession.startTransaction();
			collection.insertMany(clientSession,documents);
			clientSession.commitTransaction();
			long nowCount = collection.count();
	        log.info("文档插入成功,插入的数量: "+(nowCount-preCount));
            return true;
		} catch (Exception e) {
			clientSession.abortTransaction();
			log.info("文档插入失败");
	        return false;
		}

    }
    /**
     * 删除
     * @param db
     * @param table
     * @param document
     * @return
     */
    public static boolean delete(MongoDatabase db, ClientSession clientSession,String table, BasicDBObject document) {
    	if (db == null) {
			return false;
		}
        MongoCollection<Document> collection = db.getCollection(table);
        DeleteResult deleteOneResult;
		try {
			clientSession.startTransaction();
			deleteOneResult = collection.deleteMany(clientSession,document);
			clientSession.commitTransaction();
			long deletedCount = deleteOneResult.getDeletedCount();
			log.info("文档删除成功,删除的数量: "+deletedCount);
            return true;
		} catch (Exception e) {
			clientSession.abortTransaction();
			log.info("文档删除失败");
            return false;
		}
    }
    
	/**
	 * 修改
	 * 
	 * @param db
	 * @param table
	 * @param whereDoc
	 * @param updateDoc
	 * @return
	 */
	public static boolean update(MongoDatabase db, ClientSession clientSession, String table, BasicDBObject whereDoc,
			BasicDBObject updateDoc) {
		if (db == null) {
			return false;
		}
		MongoCollection<Document> collection = db.getCollection(table);
		
		long modifiedCount;
		try {
			clientSession.startTransaction();
			UpdateResult updateManyResult = collection.updateMany(clientSession, whereDoc, new Document("$set", updateDoc));
			clientSession.commitTransaction();
			modifiedCount = updateManyResult.getModifiedCount();
			log.info("文档更新成功,修改的数量: " + modifiedCount);
			return true;
		} catch (Exception e) {
			clientSession.abortTransaction();
			log.info("文档更新失败");
			return false;
		}
	}
	/**
	 * 单条件查询
	 * @param db
	 * @param table
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception 
	 */
	public static ISSPDataSet querybyKeyword(MongoDatabase db, String table, String key,Object value) throws Exception{
		if (db == null) {
			return null;
		}
		ISSPDataSet dataSet = new ISSPDataSet();
		//获取数据集合
		MongoCollection<Document> collection = db.getCollection(table);
		//查询条件
		BasicDBObject query = new BasicDBObject (key, value);
		//
		FindIterable <Document> iterable = collection.find(query);
		MongoCursor<Document> cursor = iterable.iterator();	
		//查询结果封装
        DataSetUtils.mongoCursor2DataSet(cursor,dataSet);
        log.info("检索id完毕");
        return dataSet;
    }
	
	/**
	 * 多条件查询，当document是空的时候，检索全部
	 * @param db
	 * @param table
	 * @param document
	 * @throws Exception 
	 */
	public static ISSPDataSet queryByDoc(MongoDatabase db, String table, BasicDBObject document) throws Exception {
		if (db == null) {
			return null;
		}
		ISSPDataSet dataSet = new ISSPDataSet();
		MongoCollection<Document> collection = db.getCollection(table);
        FindIterable<Document> iterable = collection.find(document);
         /** 
         * 1. 获取迭代器FindIterable<Document> 
         * 2. 获取游标MongoCursor<Document> 
         * 3. 通过游标遍历检索出的文档集合 
         * */  
        MongoCursor<Document> cursor = iterable.iterator();
        //查询结果封装
        DataSetUtils.mongoCursor2DataSet(cursor,dataSet);
        log.info("检索doc完毕");
        return dataSet;		
	}
	
	/**
	 * 查询全部
	 * @param db
	 * @param string
	 * @return
	 * @throws Exception 
	 */
	public static ISSPDataSet queryAll(MongoDatabase db, String table) throws Exception {
		if (db == null) {
			return null;
		}
		ISSPDataSet dataSet = new ISSPDataSet();
		MongoCollection<Document> collection = db.getCollection(table);
        FindIterable<Document> iterable = collection.find();
        MongoCursor<Document> cursor = iterable.iterator();
        //查询结果封装
        DataSetUtils.mongoCursor2DataSet(cursor,dataSet);
        log.info("检索全部完毕");
        return dataSet;
	}
	
	public static Map<String, Object> jsonStrToMap(String jsonstring) {
		JSONObject parseobj = JSONObject.parseObject(jsonstring); // 反序列化 把json 转化为对象
        Map<String, Object> Map = parseobj; // 把对象转化为map
        return Map;
    }

}
