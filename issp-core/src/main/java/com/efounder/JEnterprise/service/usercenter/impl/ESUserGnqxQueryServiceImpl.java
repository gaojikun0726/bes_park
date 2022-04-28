package com.efounder.JEnterprise.service.usercenter.impl;

import com.efounder.JEnterprise.mapper.usercenter.ESUserGnqxQueryMapper;
import com.efounder.JEnterprise.model.usercenter.ESGnzdManage;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.usercenter.ESUserGnqxQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 用户组数据权限查询接口实现类
 * @author tianjiangwei
 */
@Service("esUserGnqxQueryService")
public class ESUserGnqxQueryServiceImpl implements ESUserGnqxQueryService,ESBaseService {

	private static final Logger log = LoggerFactory.getLogger(ESUserGnqxQueryServiceImpl.class);

	@Autowired
	private ESUserGnqxQueryMapper userGnqxQueryMapper;

	@Override
	public List<ESGnzdManage> queryGnzdData(String f_yhbh) {
		log.info("#userGnqxQueryMapper获取全部用户功能权限信息");
		return userGnqxQueryMapper.loadGnzdData(f_yhbh);
	}

	@Override
	public List<ESGnzdManage> loadAllGnzdData() {
		log.info("#userGnqxQueryMapper获取全部菜单功能字典");
		return userGnqxQueryMapper.loadAllGnzdData();
	}

	@Override
	public List<ESGnzdManage> loadAllGnzdDatasc() {
		// TODO Auto-generated method stub
		return userGnqxQueryMapper.loadAllGnzdDatasc();
	}

	@Override
	public List<ESGnzdManage> loadAllModuleData() {
		// TODO Auto-generated method stub
		return userGnqxQueryMapper.loadAllModuleData();
	}

	

	
}