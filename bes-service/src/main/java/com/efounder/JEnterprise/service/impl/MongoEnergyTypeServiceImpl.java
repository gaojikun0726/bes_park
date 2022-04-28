//package com.efounder.JEnterprise.service.impl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESEnergyType;
//import com.efounder.JEnterprise.service.MongoEnergyTypeService;
//@Component
//@Service("MongoEnergyTypeService")
//public class MongoEnergyTypeServiceImpl implements MongoEnergyTypeService{
//
//	@Autowired
//    private MongoTemplate mongoTemplate;
//
//
//
//    /**
//     * 保存
//     * 实体类无id字段则插入时自动创建id，实体类有id则根据id判断，数据库中有相同id的更新，没有就插入
//     * @param user
//     */
//    public void saveEnergyType(BESEnergyType besEnergyType) {
//    	mongoTemplate.save(besEnergyType);
//    }
//
//    /**
//     * 批量插入
//     */
//    public void insertEnergyTypeList(List<BESEnergyType> energyTypeList) {
//    	mongoTemplate.insertAll(energyTypeList);
//    }
//
//    /**
//     * 查询fNybh为fNybh的一条记录
//     */
//    public BESEnergyType findOneByfNybh (String fNybh) {
//    	Query query=new Query(Criteria.where("fNybh").is(fNybh));
//    	BESEnergyType besEnergyType =  mongoTemplate.findOne(query , BESEnergyType.class);
//        return besEnergyType;
//    }
//    /**
//     * 查询fNybh为fNybh的所有记录（包含多条件查询）
//     */
//    public List<BESEnergyType> findByfNybh (String fNybh,String fNymc){
//    	Query query=new Query(Criteria.where("fNybh").is(fNybh));
//    	//多条件查询
////    	Query queryMany=new Query(Criteria.where("fNybh").is(fNybh).and("fNymc").is(fNymc));
//    	List<BESEnergyType> besEnergyTypeList =  mongoTemplate.find(query , BESEnergyType.class);
//        return besEnergyTypeList;
//    }
//	/**
//	 * 查询集合下所有数据
//	 */
//	@Override
//	public List<BESEnergyType> findAll() {
//		List<BESEnergyType> besEnergyTypeList = mongoTemplate.findAll(BESEnergyType.class);
//		return besEnergyTypeList;
//	}
//    /**
//     * 更新
//     */
//	@Override
//	public void updateByfNybh(BESEnergyType besEnergyType) {
//		Query query=new Query(Criteria.where("fNybh").is(besEnergyType.getfNybh()));
//        Update update= new Update().set("fNymc", besEnergyType.getfNymc()).set("fPrice",besEnergyType.getfPrice());
//        //更新查询返回结果集的第一条
////        mongoTemplate.updateFirst(query,update,BESEnergyType.class);
//        //更新查询返回结果集的所有
//        mongoTemplate.updateMulti(query,update,BESEnergyType.class);
//	}
//
//	/**
//	 * 删除一条
//	 */
//	@Override
//	public void deleteByfNybh(String fNybh) {
//		Query query=new Query(Criteria.where("fNybh").is(fNybh));
//        mongoTemplate.remove(query,BESEnergyType.class);
//	}
//
//
//
//}
