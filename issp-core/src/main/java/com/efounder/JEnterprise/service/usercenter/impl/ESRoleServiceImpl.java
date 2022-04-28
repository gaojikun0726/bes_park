package com.efounder.JEnterprise.service.usercenter.impl;

import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.initializer.RoleCache;
import com.efounder.JEnterprise.mapper.usercenter.ESRoleMapper;
import com.efounder.JEnterprise.model.usercenter.ESRole;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.usercenter.ESRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 角色自定义接口实现类
 * @author gongfanfei
 */
@Service("esRoleService")
public class ESRoleServiceImpl implements ESRoleService,ESBaseService {

	private static final Logger log = LoggerFactory.getLogger(ESRoleServiceImpl.class);

	@Autowired
	private ESRoleMapper roleMapper;

	@Resource
	private RoleCache roleCache;

	@Override
	public List<ESRole> findRoles() {
		log.info("#userRoleMapper获取用户角色信息");
		return roleMapper.findRoles();
	}

	@Override
	public ESRole findRoleById(String id) {
		log.info("#userRoleMapper获取用户角色信息");
		return roleMapper.findRoleById(id);
	}

	@Override
	public List<ESUser> findUserByRoleId(String id) {
		log.info("#userRoleMapper通过角色id加载用户信息");
		return roleMapper.findUserByRoleId(id);
	}

	@Override
	public PageInfo<ESRole> findUserRoleByPage(Integer pageNum, String keywords) {
		log.info("#userRoleMapper分页获取用户角色信息");
		if (pageNum == null)
			pageNum = 1;
		PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
		// 紧跟着的第一个select方法会被分页
		List<ESRole> userGroup = roleMapper.findUserRoleByPage(keywords);
		// 用PageInfo对结果进行包装
		PageInfo<ESRole> page = new PageInfo<ESRole>(userGroup);
		return page;
	}
	@Override
	public List<ESRole> getUserRoleList(String keywords) {
		// TODO Auto-generated method stub
	        List<ESRole> list = roleMapper.findUserRole(keywords);
	        log.info("# 查询默认数据库 page.toString()={}", list.toString());
	        return list;
	}
	@Override
    public List<ESRole> findRoleByUserId(String userId) {
        return roleMapper.findRoleByUserId(userId);
    }

	@Transactional
	@Override
	public boolean addRole(ESRole role) {
		log.info("#userRoleMapper添加用户角色信息");

		boolean result = roleMapper.addRole(role);

		if (result)
		{
			// 添加到缓存
			roleCache.addOneRoleCache(roleMapper.findRoleById(role.getF_guid()));
		}
		return result;
	}

	@Override
	public boolean delRole(ESRole role) {
		log.info("#userRoleMapper删除用户角色信息");

		ESRole esRole = roleMapper.findRoleById(role.getF_guid());

		if (esRole == null)
		{
			return false;
		}

		// 查询是否被数据权限关联
		Integer roleGroup = roleMapper.roleGroupIbfk(role.getF_guid());

		if (roleGroup != null && roleGroup > 0)
		{
			return false;
		}

		boolean result = roleMapper.delRole(role);

		if (result)
		{
			// 删除缓存
			roleCache.deleteOneRoleCache(esRole.getF_rolebh());
		}

		return result;
	}

	@Override
	public boolean upRole(ESRole role) {
		log.info("#userRoleMapper更新用户角色信息");

		boolean result = roleMapper.upRole(role);

		if (result)
		{
			// 更新到缓存
			roleCache.updateOneRoleCache(roleMapper.findRoleById(role.getF_guid()));
		}

		return result;
	}
	@Override
	public String findMaxBmbh() {
		log.info("#userRoleMapper查询编号最大值");
		return roleMapper.findMaxBmbh();
	}

	@Override
	public ESRole findRoleByRoleBh(String f_rolebh) {
		// TODO Auto-generated method stub
		return roleMapper.findRoleByRoleBh(f_rolebh);	
	}
}