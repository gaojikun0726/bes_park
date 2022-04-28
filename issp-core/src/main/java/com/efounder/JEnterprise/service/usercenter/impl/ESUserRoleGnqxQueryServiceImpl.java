package com.efounder.JEnterprise.service.usercenter.impl;

import com.efounder.JEnterprise.mapper.usercenter.ESUserRoleGnqxQueryMapper;
import com.efounder.JEnterprise.model.usercenter.ESGnzdManage;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.usercenter.ESUserRoleGnqxQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 用户角色功能权限查询
 * @author tianjiangwei
 */
@Service("esUserRoleGnqxQueryService")
public class ESUserRoleGnqxQueryServiceImpl implements ESUserRoleGnqxQueryService,ESBaseService {

	private static final Logger log = LoggerFactory.getLogger(ESUserRoleGnqxQueryServiceImpl.class);

	@Autowired
	private ESUserRoleGnqxQueryMapper userRoleGnqxQueryMapper;

	@Override
	public List<ESGnzdManage> queryRoleGnzdData(String f_rolebh) {
		log.info("#userRoleGnqxQueryMapper获取全部用户角色功能权限信息");
		return userRoleGnqxQueryMapper.loadRoleGnzdData(f_rolebh);
	}

	/*@Override
	public List<ESGnzdManage> loadAllRoleGnzdData() {
		log.info("#userRoleGnqxQueryMapper获取全部菜单功能字典");
		return userRoleGnqxQueryMapper.loadAllRoleGnzdData();
	}

	@Override
	public List<ESGnzdManage> loadAllRoleGnzdDatasc() {
		// TODO Auto-generated method stub
		return userRoleGnqxQueryMapper.loadAllRoleGnzdDatasc();
	}*/

	
	

	
}