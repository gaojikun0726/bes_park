package com.efounder.JEnterprise.service.usercenter.impl;

import com.efounder.JEnterprise.mapper.usercenter.ESUserGroupGnqxQueryMapper;
import com.efounder.JEnterprise.model.usercenter.ESGnzdManage;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.usercenter.ESUserGroupGnqxQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 用户组功能权限查询
 * @author tianjiangwei
 */
@Service("esUserGroupGnqxQueryService")
public class ESUserGroupGnqxQueryServiceImpl implements ESUserGroupGnqxQueryService,ESBaseService {

	private static final Logger log = LoggerFactory.getLogger(ESUserGroupGnqxQueryServiceImpl.class);

	@Autowired
	private ESUserGroupGnqxQueryMapper userGroupGnqxQueryMapper;


	@Override
	public List<ESGnzdManage> queryGroupGnzdData(String f_zbh) {
		log.info("#userRoleGnqxQueryMapper获取全部用户角色功能权限信息");
		return userGroupGnqxQueryMapper.loadGroupGnzdData(f_zbh);
	}

	
}