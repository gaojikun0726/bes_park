package com.efounder.JEnterprise.service.usercenter.impl;

import com.efounder.JEnterprise.mapper.usercenter.ESRoleSjqxManageMapper;
import com.efounder.JEnterprise.model.usercenter.ESUserRoleSjqxManage;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.usercenter.ESRoleSjqxManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 角色数据权限接口实现类
 * @author gongfanfei
 */
@Service("esUserRoleSjqxManageService")
public class ESRoleSjqxManageServiceImpl implements ESRoleSjqxManageService,ESBaseService {

	private static final Logger log = LoggerFactory.getLogger(ESRoleSjqxManageServiceImpl.class);

	@Autowired
	private ESRoleSjqxManageMapper userRolesjqxMapper;

	@Override
	public List<ESUserRoleSjqxManage> findUserRoleSjqx() {
		log.info("#userRolesjqxMapper获取全部用户组数据权限信息");
		return userRolesjqxMapper.findUserRoleSjqx();
	}
	@Override
	public List<ESUserRoleSjqxManage> findUserRoleSjqxByThreeId(String f_roleguid, String f_qxbh,String f_bhzd) {
		log.info("#userRolesjqxMapper获取全部用户组数据权限信息");
		return userRolesjqxMapper.findUserRoleSjqxByThreeId(f_roleguid,f_qxbh,f_bhzd);
	}

	@Override
	public List<ESUserRoleSjqxManage> findUserRoleSjqxById(String f_yhbh, String f_qxbh,String columns, String tableName,String classifysjqxTb) {
		log.info("#userRolesjqxMapper获取对应ID用户组数据权限信息");
		return userRolesjqxMapper.findUserRoleSjqxById(f_yhbh,f_qxbh,columns,tableName,classifysjqxTb);
	}

	
	@Override
    public List<ESUserRoleSjqxManage> findUserRoleSjqxByKey(String keywords) {
		log.info("#userRolesjqxMapper获取模糊搜索用户组数据权限信息");
        return userRolesjqxMapper.findUserRoleSjqxByKey(keywords);
    }

	@Transactional
	@Override
	public boolean addUserRoleSjqx(List<ESUserRoleSjqxManage> rolesjqx,String classifysjqxTb) {
		log.info("#userRolesjqxMapper添加用户组数据权限信息");
		return userRolesjqxMapper.addUserRoleSjqx(rolesjqx,classifysjqxTb);
	}


	@Override
	public boolean upUserRoleSjqx(ESUserRoleSjqxManage rolesjqx) {
		log.info("#userRolesjqxMapper更新用户组数据权限信息");
		return userRolesjqxMapper.upUserRoleSjqx(rolesjqx);
	}

	@Override
	public List<?> getcolumnsBytableName(String columns, String tableName) {
		// TODO Auto-generated method stub
		return userRolesjqxMapper.getcolumnsBytableName(columns, tableName);
	}
	@Override
	public boolean delUserRoleSjqx(String f_roleguid, String f_qxbh, String classifysjqxTb) {
		// TODO Auto-generated method stub
		return userRolesjqxMapper.delUserRoleSjqx(f_roleguid, f_qxbh, classifysjqxTb);
	}
}