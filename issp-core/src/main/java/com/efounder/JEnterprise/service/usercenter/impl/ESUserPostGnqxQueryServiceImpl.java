package com.efounder.JEnterprise.service.usercenter.impl;

import com.efounder.JEnterprise.mapper.usercenter.ESUserPostGnqxQueryMapper;
import com.efounder.JEnterprise.model.usercenter.ESGnzdManage;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.usercenter.ESUserPostGnqxQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 岗位功能权限查询
 * @author tianjiangwei
 */
@Service("esUserPostGnqxQueryService")
public class ESUserPostGnqxQueryServiceImpl implements ESUserPostGnqxQueryService,ESBaseService {

	private static final Logger log = LoggerFactory.getLogger(ESUserPostGnqxQueryServiceImpl.class);

	@Autowired
	private ESUserPostGnqxQueryMapper userPostGnqxQueryMapper;

	@Override
	public List<ESGnzdManage> queryPostGnzdData(String f_gwguid) {
		log.info("#userRoleGnqxQueryMapper获取全部用户角色功能权限信息");
		return userPostGnqxQueryMapper.loadPostGnzdData(f_gwguid);
	}

	
}