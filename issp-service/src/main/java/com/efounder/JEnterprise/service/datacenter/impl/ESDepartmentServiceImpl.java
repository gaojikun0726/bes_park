package com.efounder.JEnterprise.service.datacenter.impl;

import com.efounder.JEnterprise.mapper.datacenter.ESDepartmentMapper;
import com.efounder.JEnterprise.model.datacenter.ESDepartment;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.efounder.JEnterprise.service.datacenter.ESDepartmentService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/** 
 * 类名称：ESDepartmentServiceImpl
 * 类描述：部门定义service接口实现类
 * 创建人：sunhao
 * 修改人：sunhao
 * 修改时间：2018年5月31日 
 * 修改备注：
 * @version 1.0.0 
 *
 */
@Service("ESDepartmentService")
public class ESDepartmentServiceImpl implements ESDepartmentService {
	private static final Logger log = LoggerFactory.getLogger(ESDepartmentServiceImpl.class);
	
	@Autowired
	private ESDepartmentMapper esDepartmentMapper;
	@Override
	public List<ESDepartment> findAllESDepartment() {
		log.info("#esDepartmentMapper获取部门信息表中全部信息");
		return esDepartmentMapper.findAllESDepartment();
	}

	@Override
	public ESDepartment findESDepartmentByBmbh(@Param("f_bmbh") String f_bmbh) {
		log.info("#esDepartmentMapper根据部门编号查询部门信息");
		return esDepartmentMapper.findESDepartmentByBmbh(f_bmbh);
	}

	@Override
	public List<ESDepartment> findESDepartmentByZzbh(String f_zzjgbh) {
		log.info("#esDepartmentMapper根据组织机构编号查询部门信息");
		return esDepartmentMapper.findESDepartmentByZzbh(f_zzjgbh);
	}

	@Override
	public List<ESDepartment> findChildByBmmc(String f_bmmc) {
		log.info("#esDepartmentMapper根据部门名称查询部门信息");
		return esDepartmentMapper.findChildByBmmc(f_bmmc);
	}

	@Override
	public List<ESDepartment> findESDepartmentByKeywords(String keywords) {
		log.info("#esDepartmentMapper根据关键字查询部门信息");
		return esDepartmentMapper.findESDepartmentByKeywords(keywords);
	}

	@Override
	public List<ESDepartment> loadAll() {
		log.info("#esDepartmentMapper加载部门信息");
		return esDepartmentMapper.loadAll();
	}

	@Override
	public boolean addESDepartment(ESDepartment esdEpartment) {
		log.info("#esDepartmentMapper添加部门信息");
		return esDepartmentMapper.addESDepartment(esdEpartment);
	}

	@Override
	public boolean deleteByBmbh(ESDepartment esdEpartment) {
		log.info("#esDepartmentMapper删除部门信息");
		return esDepartmentMapper.deleteByBmbh(esdEpartment);
	}

	@Override
	public boolean upDateESDepartment(ESDepartment esdEpartment) {
		log.info("#esDepartmentMapper修改部门信息");
		return esDepartmentMapper.upDateESDepartment(esdEpartment);
	}

	@Override
	public List<ESUser> findESDepartmentByPage(String keywords) {
		log.info("#esDepartmentMapper分页查询");
		return esDepartmentMapper.findESDepartmentByPage(keywords);
	}

	@Override
	public String findMaxBmbh() {
		log.info("#esDepartmentMapper查询部门编号最大值");
		return esDepartmentMapper.findMaxBmbh();
	}

}
