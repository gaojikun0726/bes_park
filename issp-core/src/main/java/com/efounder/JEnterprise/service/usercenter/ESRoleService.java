package com.efounder.JEnterprise.service.usercenter;

import com.efounder.JEnterprise.model.usercenter.ESRole;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Description 用户角色维护接口
 * @author gongfanfei
 *
 */
public interface ESRoleService {

	/**
	 * 查询组织下所有角色信息
	 *
	 * @return
	 */
	public List<ESRole> findRoles();
	
	
	ESRole findRoleByRoleBh(String f_rolebh);

	/**
	 * 根据ID查询角色信息
	 *
	 * @return
	 */
	public ESRole findRoleById(String id);
	/**
	 * 搜索
	 */
	 public List<ESRole> getUserRoleList(String keywords);
	/**
	 * 通过角色id加载用户信息
	 *
	 * @return
	 */
	public List<ESUser> findUserByRoleId(String id);
	
	/**
     * 根据用户查询对应所有角色
     *
     * @param userId
     *            用户Id
     * @return roles 所有角色
     */
    public List<ESRole> findRoleByUserId(String userId);

	/**
	 * 分页查询
	 * 
	 * @param pageNum
	 * @param keywords
	 * @return
	 */
	public PageInfo<ESRole> findUserRoleByPage(Integer pageNum, String keywords);

	/**
	 * 添加角色
	 * 
	 * @return
	 */
	public boolean addRole(ESRole role);

	/**
	 * 删除角色信息
	 * 
	 * @param role
	 * @return
	 */
	public boolean delRole(ESRole role);

	/**
	 * 更新角色信息
	 * 
	 * @param role
	 * @return
	 */
	public boolean upRole(ESRole role);
	/**
	 * 查询编号最大值
	 * @param 
	 * @return
	 */
	public String findMaxBmbh();

}
