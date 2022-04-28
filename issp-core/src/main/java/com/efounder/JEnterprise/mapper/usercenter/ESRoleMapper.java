package com.efounder.JEnterprise.mapper.usercenter;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.usercenter.ESRole;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色Mapper
 * @author gongfanfei
 * @datetiem 2018年5月2日
 */
@Mapper
public interface ESRoleMapper extends BaseMapper<String, ESRole> {

	/**
	 * 获取所有角色信息
	 * @return
	 */
	List<ESRole> findRoles();
	
	/**
	 * 根据角色编号获取角色信息
	 * @param f_rolebh
	 * @return
	 */
	ESRole findRoleByRoleBh(@Param("f_rolebh")String f_rolebh);
	
	/**
	 * 根据角色ID获取角色信息
	 * @param id
	 * @return
	 */
	ESRole findRoleById(String id);

	/**
	 * 根据角色ID获取该角色下的所有用户
	 * @param id
	 * @return
	 */
	List<ESUser> findUserByRoleId(@Param("id") String id);
	
	/**
     * 根据用户查询对应所有角色
     * @param userId  用户编号
     * @return roles 所有角色
     */
    List<ESRole> findRoleByUserId(String userId);

	/**
	 * 分页查询
	 * @param keywords
	 * @return
	 */
	List<ESRole> findUserRoleByPage(@Param("keywords") String keywords);
	
	/**
	 * 搜索
	 * @param keywords
	 * @return
	 */
	List<ESRole> findUserRole(@Param("keywords") String keywords);

	/**
	 * 增加角色
	 * @param role
	 * @return
	 */
	boolean addRole(ESRole role);

	/**
	 * 删除角色
	 * @param role
	 * @return
	 */
	boolean delRole(ESRole role);

	/**
	 * 更新角色
	 * @param role
	 * @return
	 */
	boolean upRole(ESRole role);
	
	/**
	 * 查询编号最大值
	 * @param 
	 * @return
	 */
	public String findMaxBmbh();

	Integer roleGroupIbfk(String f_guid);
}
