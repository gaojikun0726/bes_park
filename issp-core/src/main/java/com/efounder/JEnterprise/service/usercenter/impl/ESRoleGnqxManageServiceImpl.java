package com.efounder.JEnterprise.service.usercenter.impl;

import com.efounder.JEnterprise.mapper.usercenter.ESUserRoleGnqxManageMapper;
import com.efounder.JEnterprise.model.usercenter.ESUserRoleGnqxManage;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.usercenter.ESRoleGnqxManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 角色功能权限接口实现类
 * @author gongfanfei
 */
@Service("esRoleGnqxManageService")
public class ESRoleGnqxManageServiceImpl implements ESRoleGnqxManageService,ESBaseService {

	private static final Logger log = LoggerFactory.getLogger(ESRoleGnqxManageServiceImpl.class);

	@Autowired
	private ESUserRoleGnqxManageMapper userRolegnqxMapper;

	@Override
	public List<ESUserRoleGnqxManage> findUserRoleGnqx() {
		log.info("#userRolegnqxMapper获取全部用户角色功能权限信息");
		return userRolegnqxMapper.findUserRoleGnqx();
	}
	@Override
	public List<ESUserRoleGnqxManage> findUserRoleGnqxByRoleBh(String f_rolebh,String classifygnqxTb) {
		log.info("#userRolegnqxMapper获取对应ID用户角色功能权限信息");
		return userRolegnqxMapper.findUserRoleGnqxByRoleId(f_rolebh,classifygnqxTb);
	}
	@Override
	public boolean addUserRoleGnqx(List<ESUserRoleGnqxManage> gnqxList,String tablename) {
		log.info("#userRolegnqxMapper添加用户角色功能权限信息");
		return userRolegnqxMapper.addUserRoleGnqx(gnqxList,tablename);
	}

	@Override
	public boolean delUserRoleGnqx(List<ESUserRoleGnqxManage> gnqxList,String tablename) {
		log.info("#userRolegnqxMapper删除用户角色功能权限信息");
		String f_rolebh = null;
		if(gnqxList.size()>0){
			f_rolebh = gnqxList.get(0).getF_rolebh();	
		}
		return userRolegnqxMapper.delUserRoleGnqx(gnqxList,tablename,f_rolebh);
	}

	@Override
	public boolean upUserRoleGnqx(List<ESUserRoleGnqxManage> gnqxList,String tablename,String f_rolebh) {
		log.info("#userRolegnqxMapper更新用户角色功能权限信息");
		return userRolegnqxMapper.upUserRoleGnqx(gnqxList,tablename,f_rolebh);
	}
}