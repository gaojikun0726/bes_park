package com.efounder.JEnterprise.service.basedatamanage.energyinformation.impl;

import com.efounder.JEnterprise.mapper.basedatamanage.energyinformation.BESPark_EnergyTypeMapper;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESPark_EnergyType;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.basedatamanage.energyinformation.BESPark_EnergyTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description 园区能耗类型接口实现类
 * @author yujieying
 */
@Service("besPark_EnergyTypeService")
public class BESPark_EnergyTypeServiceImpl implements BESPark_EnergyTypeService,ESBaseService {

	private static final Logger log = LoggerFactory.getLogger(BESPark_EnergyTypeServiceImpl.class);

	@Autowired
	private BESPark_EnergyTypeMapper park_energytypeMapper;

	@Override
	public List<BESPark_EnergyType> findPark_EnergyType() {
		log.info("#park_energytypeMapper获取所有能耗类型信息");
		return park_energytypeMapper.findPark_EnergyType();
	}
	

	@Override
	public List<Map<String,Object>> getgllist(String f_yqbh) {
		log.info("#park_energytypeMapper获取园区与能耗类型关系信息");
		return park_energytypeMapper.getgllist(f_yqbh);
	}
	
	@Override
	public List<Map<String,Object>> getenrrgylist(String f_yqbh) {
		log.info("#park_energytypeMapper获取个别园区与能耗类型关系信息");
		return park_energytypeMapper.getenergylist(f_yqbh);
	}



	@Override
	public boolean addpark_energytype(BESPark_EnergyType bespark_energytype ) {
		log.info("#park_energytypeMapper添加园区能耗类型勾选信息");
		return park_energytypeMapper.addpark_energytype(bespark_energytype);
	}



	@Override
	public boolean delpark_energytype(BESPark_EnergyType bespark_energytype) {
		log.info("#park_energytypeMapper删除园区能耗类型勾选信息");
		return park_energytypeMapper.delpark_energytype(bespark_energytype);
	}

	@Override
	public List<Map<String, Object>> findpark_energytype(BESPark_EnergyType bespark_energytype)
	{
		log.info("#findpark_energytype查询园区能耗类型勾选信息");
		return park_energytypeMapper.findpark_energytype(bespark_energytype);
	}


}