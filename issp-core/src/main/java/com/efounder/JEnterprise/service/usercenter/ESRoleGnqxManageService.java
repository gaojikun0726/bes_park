package com.efounder.JEnterprise.service.usercenter;

import com.efounder.JEnterprise.model.usercenter.ESUserRoleGnqxManage;

import java.util.List;

/**
 * @Description 角色功能权限维护接口
 * @author gongfanfei
 */
public interface ESRoleGnqxManageService {

	/**
	 * 查询组织下所有角色功能权限信息
	 *
	 * @return
	 */
	public List<ESUserRoleGnqxManage> findUserRoleGnqx();
	/**
	 * 根据ID查询角色功能权限信息
	 *
	 * @return
	 */
	public List<ESUserRoleGnqxManage> findUserRoleGnqxByRoleBh(String f_rolebh,String classifygnqxTb);
	
	/**
	 * 添加角色功能权限
	 * 
	 * @return
	 */
	public boolean addUserRoleGnqx(List<ESUserRoleGnqxManage> gnqxList,String tablename);

	/**
	 * 删除角色功能权限信息
	 * 
	 * @param userRoleGnqx
	 * @return
	 */
	public boolean delUserRoleGnqx(List<ESUserRoleGnqxManage> gnqxList,String tablename);

	/**
	 * 更新角色功能权限信息
	 * 
	 * @param userRoleGnqx
	 * @return
	 */
	public boolean upUserRoleGnqx(List<ESUserRoleGnqxManage> gnqxList,String tablename,String f_rolebh);
}
