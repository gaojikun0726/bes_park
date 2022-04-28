package com.efounder.JEnterprise.service.basedatamanage.energyinformation.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.basedatamanage.energyinformation.BESParkMapper;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESPark;
import com.efounder.JEnterprise.service.basedatamanage.energyinformation.BESParkService;
import com.framework.common.utils.ScopeDataUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/** 
 * 类名称：BESParkServiceImpl
 * 类描述：园区表service接口实现类
 * 创建人：sunhao
 * 修改人：sunhao
 * 修改时间：2018年7月3日 
 * 修改备注：
 * @version 1.0.0 
 *
 */
@Service("BESParkService")
public class BESParkServiceImpl implements BESParkService {
	private static final Logger log = LoggerFactory.getLogger(BESParkServiceImpl.class);

	@Autowired
	private BESParkMapper parkMapper;
	@Override
	public PageInfo<BESPark> findParkByPage(Integer pageNum,String keywords) {
		// request: url?pageNum=1&pageSize=10
		// 支持 ServletRequest,Map,POJO 对象，需要配合 params 参数
		if (pageNum == null)
			pageNum = 1;
		PageHelper.startPage(pageNum, Constants.PAGE_SIZE);

		//获取用户和组织编号
		List<String> groupbh = ScopeDataUtil.getGroupIdScope();
		String userId = ScopeDataUtil.getUserIdScope();
		// 紧跟着的第一个select方法会被分页
		List<BESPark> equipmenttype = parkMapper.findParkByPage(keywords,groupbh,userId);
		// 用PageInfo对结果进行包装
		PageInfo<BESPark> page = new PageInfo<BESPark>(equipmenttype);
		// 测试PageInfo全部属性
		// PageInfo包含了非常全面的分页属性
		log.info("# 查询默认数据库 page.toString()={}", page.toString());
		return page;
	}

	@Override
	public List<BESPark> findAllPark() {
		log.info("#parkMapper获取表中全部信息");
		List<String> groupbh = ScopeDataUtil.getGroupIdScope();
		String userId = ScopeDataUtil.getUserIdScope();
		return parkMapper.findAllPark(groupbh,userId);
	}

	@Override
	public BESPark findParkByYqbh(String f_yqbh) {
		log.info("#parkMapper根据编号查询信息");
		return parkMapper.findParkByYqbh(f_yqbh);
	}

	@Override
	public List<BESPark> findParkByYqmc(String f_yqmc) {
		log.info("#parkMapper根据名称查询信息");
		return parkMapper.findParkByYqmc(f_yqmc);
	}

	@Override
	public List<BESPark> findParkByKeywords(String keywords) {
		log.info("#parkMapper根据关键字查询信息");
		return parkMapper.findParkByKeywords(keywords);
	}

	@Override
	public List<BESPark> loadAll() {
		log.info("#parkMapper加载信息");
		return parkMapper.loadAll();
	}

	@Override
	public boolean addPark(BESPark park) {
		log.info("#parkMapper添加信息");
		return parkMapper.addPark(park);
	}

	@Override
	public ISSPReturnObject deleteByYqbh(BESPark park) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#parkMapper删除信息");

		String yqbh = park.getF_yqbh();
		//删除之前做关联判断
		//1.判断分项配置中是否在此园区下新增分项
		List<Map<String,Object>> ubOptionDeploy = parkMapper.subOptionDeploy(yqbh);
		if (ubOptionDeploy.size() > 0) {
			returnObject.setStatus("0");
			returnObject.setMsg("园区删除失败,请删除当前园区下配置的分项配置");
			return  returnObject;
		}
		//2.判断分户配置中是否在此园区下新增分户
		List<Map<String,Object>> householdDeploy = parkMapper.householdDeploy(yqbh);
		if (householdDeploy.size() > 0) {
			returnObject.setStatus("0");
			returnObject.setMsg("园区删除失败,请删除当前园区下配置的分户配置");
			return  returnObject;
		}
		//3.判断支路配置中是否在此园区下新增支路
		List<Map<String,Object>> branchRoadDeploy = parkMapper.branchRoadDeploy(yqbh);
		if (branchRoadDeploy.size() > 0) {
			returnObject.setStatus("0");
			returnObject.setMsg("园区删除失败,请删除当前园区下配置的支路配置");
			return  returnObject;
		}
		//4.判断能耗配置中是否在此园区下配置
		List<Map<String,Object>> energyTypeDeploy = parkMapper.energyTypeDeploy(yqbh);
		if (energyTypeDeploy.size() > 0) {
			returnObject.setStatus("0");
			returnObject.setMsg("园区删除失败,请删除能耗配置下配置的能耗");
			return  returnObject;
		}
		//5.判断采集方案中是否在此园区下配置采集方案-------------这个采集方案待修改,现有逻辑并未关联园区
//		List<Map<String,Object>> collectionPlanDeploy = parkMapper.collectionPlanDeploy(yqbh);
		//7.判断设备配置中是否在此园区下配置设备
		List<Map<String,Object>> deviceConfigurationDeploy = parkMapper.deviceConfigurationDeploy(yqbh);
		if (deviceConfigurationDeploy.size() > 0) {
			returnObject.setStatus("0");
			returnObject.setMsg("园区删除失败,请删除当前园区下配置的设备配置");
			return  returnObject;
		}

		Boolean deleteByYqbh = parkMapper.deleteByYqbh(park);
		if (deleteByYqbh) {
			returnObject.setStatus("1");
			returnObject.setMsg("园区删除成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("园区删除失败");
		}
		return returnObject;
	}

	@Override
	public boolean upDatePark(BESPark park) {
		log.info("#parkMapper修改信息");
		return parkMapper.upDatePark(park);
	}

	@Override
	public String findMaxYqbh() {
		log.info("#parkMapper查询编号最大值");
		return parkMapper.findMaxYqbh();
	}

	@Override
	public List<BESPark> findParkinfoListByYqbh() {
		log.info("#parkMapper根据编号查询信息");
		return parkMapper.findParkinfoListByYqbh();
	}
}
