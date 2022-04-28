//package com.efounder.JEnterprise.controller;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.bson.Document;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//
//import com.core.common.conn.db.MongoDBObject;
//import com.core.common.conn.db.MongoDBUtil;
//import com.core.common.data.ISSPDataSet;
//import com.core.common.data.ISSPRowSet;
//import com.efounder.JEnterprise.mapper.basedatamanage.enegrycollectionmanage.BESEnergyTypeMapper;
//import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESEnergyType;
//import com.efounder.JEnterprise.service.MongoEnergyTypeService;
//import com.mongodb.BasicDBObject;
//import com.mongodb.MongoClient;
//import com.mongodb.client.ClientSession;
//import com.mongodb.client.MongoDatabase;
//@Component
//@Controller
//public class MongoEnergyTypeController {
//	@Autowired
//	private MongoEnergyTypeService mongoEnergyTypeService;
//	@Autowired
//	private BESEnergyTypeMapper besEnergyTypeMapper;
//	@Autowired
//	private MongoDBObject mongoDBObject;
//
//	/**
//	 * 实体类操作mongodb
//	 */
//    public void EntityMongoCRUD(){
//    	List<BESEnergyType> energyTypeList = besEnergyTypeMapper.getAllEnergyType();
//    	//插入一条
//    	mongoEnergyTypeService.saveEnergyType(energyTypeList.get(1));
//
//    	//批量插入
//    	mongoEnergyTypeService.insertEnergyTypeList(energyTypeList);
//
//    	//删除一条
//    	mongoEnergyTypeService.deleteByfNybh("0005");
//
//    	//查询fNybh为“0002”的一条数据
//    	BESEnergyType besEnergyType = mongoEnergyTypeService.findOneByfNybh("0002");
//    	System.out.println("besEnergyType is " +besEnergyType);
//
//    	//查询fNybh为“0002”的所有数据(包括多条件查询)
//    	List<BESEnergyType> MongoList = mongoEnergyTypeService.findByfNybh("0002","水");
//    	System.out.println(MongoList.size());
//
//    	//查询一个集合中的所有数据
//    	List<BESEnergyType> MongoList1 = mongoEnergyTypeService.findAll();
//    	System.out.println(MongoList1.size());
//
//    	//更新fNybh为“0002”的第一条数据/全部数据
//    	BESEnergyType besEnergyType1 = energyTypeList.get(1);
//    	besEnergyType1.setfNymc("太阳能能");
//    	besEnergyType1.setfPrice("100");
//    	mongoEnergyTypeService.updateByfNybh(besEnergyType1);
//
//    }
//
//    /**
//     * JDBC驱动操作
//     * @throws Exception
//     */
//	public void JDBCMongnCRUD() throws Exception {
////		//获取服务链接
////		MongoClient mongoClient = mongoDBObject.getMongoClient();
////    	//获取数据库连接
////    	MongoDatabase db = mongoDBObject.getMongoDataBase();
////    	//获取session
////    	ClientSession clientSession = mongoDBObject.getClientSession();
////
////
////    	//单条件查询（注意字段类型是字符串还是int）
////    	ISSPDataSet resultList = MongoDBUtil.querybyKeyword(db, "bESEnergyType", "fNybh","0003");
////    	int rowCount = resultList.getRowCount();
////		ISSPRowSet rowset = null;
////		for (int i = 0; i < rowCount; i++) {
////			rowset = resultList.getRowSet(i);
////		}
////    	System.out.println(rowset.getString("fNybh", ""));
////
////    	//多条件查询
////    	BasicDBObject document = new BasicDBObject("fNybh","0002");
////    	document.append("fNymc", "水");
////    	ISSPDataSet resultList2 = MongoDBUtil.queryByDoc(db, "bESEnergyType", document);//检索doc,可以根据doc(key,value)来查找,当doc是空的时候，检索全部
////    	int rowCount2 = resultList2.getRowCount();
////		ISSPRowSet rowset2 = null;
////		for (int i = 0; i < rowCount2; i++) {
////			rowset2 = resultList2.getRowSet(i);
////		}
////
////    	//查询全部
////		ISSPDataSet resultList3 = MongoDBUtil.queryAll(db, "bESEnergyType");
////		int rowCount3 = resultList3.getRowCount();
////		ISSPRowSet rowset3 = null;
////		for (int i = 0; i < rowCount3; i++) {
////			rowset3 = resultList3.getRowSet(i);
////		}
////
////    	//插入一条
////    	Map<String,Object> areaMap= new HashMap<String,Object>();
////    	areaMap.put("_id", 3);
////    	areaMap.put("name", "小李");
////    	areaMap.put("age", 14);
////    	boolean flag = MongoDBUtil.insertOne(db, "bESEnergyType", new Document(areaMap));
////
////    	//插入多条
////		Map<String, Object> areaMap2 = new HashMap<String, Object>();
////		Map<String, Object> areaMap3 = new HashMap<String, Object>();
////		areaMap2.put("_id", 1);
////		areaMap2.put("name", "小张234");
////		areaMap3.put("_id", 6);
////		areaMap3.put("name", "小王222");
////		List<Map<String, Object>> areaMapList = new ArrayList<Map<String,Object>>();
////		areaMapList.add(areaMap2);
////		areaMapList.add(areaMap3);
////		List<Document> docList = new ArrayList<Document>();
////		for(int i=0;i<areaMapList.size();i++) {
////			docList.add(i, new Document(areaMapList.get(i)));
////		}
////		boolean flag1 = MongoDBUtil.insertMany(db, clientSession,"bESEnergyType", docList);
////		System.out.println(flag1);
////
////    	//删除
////		Map<String, Object> areaMap4 = new HashMap<String, Object>();
////		areaMap4.put("_id", 6);
////		boolean flag2 = MongoDBUtil.delete(db,clientSession, "bESEnergyType", new BasicDBObject(areaMap4));
////		System.out.println(flag2);
////
////    	//修改
////		Map<String, Object> updateDoc = new HashMap<String, Object>();
////		Map<String, Object> wehereDoc = new HashMap<String, Object>();
////		wehereDoc.put("_id", 4);
////		updateDoc.put("name", "小张改");
////		boolean flag3 = MongoDBUtil.update(db,clientSession, "bESEnergyType", new BasicDBObject(wehereDoc), new BasicDBObject(updateDoc));
////		System.out.println(flag3);
////
////
////		//关闭链接
////		mongoDBObject.closeMongoClient(db, mongoClient);
//    }
//}
