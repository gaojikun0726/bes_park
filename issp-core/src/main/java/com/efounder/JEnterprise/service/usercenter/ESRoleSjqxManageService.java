package com.efounder.JEnterprise.service.usercenter;

import com.efounder.JEnterprise.model.usercenter.ESUserRoleSjqxManage;

import java.util.List;

/**
 * @Description 角色数据权限维护接口
 * @author gongfanfei
 */
public interface ESRoleSjqxManageService {

	/**
	 * 查询组织下所有角色数据权限信息
	 *
	 * @return
	 */
	public List<ESUserRoleSjqxManage> findUserRoleSjqx();
	/**
	 * 根据三个主键联合查询组织下所有角色数据权限信息
	 *
	 * @return
	 */
	public List<ESUserRoleSjqxManage> findUserRoleSjqxByThreeId(String f_roleguid, String f_qxbh,String f_bhzd);

	/**
	 * 根据ID查询角色数据权限信息
	 *
	 * @return
	 */
	public List<ESUserRoleSjqxManage> findUserRoleSjqxById(String f_roleguid, String f_qxbh,String columns, String tableName,String classifysjqxTb);
	/**
	 * 搜索
	 */
	 public List<ESUserRoleSjqxManage> findUserRoleSjqxByKey(String keywords);
	

	/**
	 * 添加角色数据权限
	 * 
	 * @return
	 */
	public boolean addUserRoleSjqx(List<ESUserRoleSjqxManage> userRolesjqx,String classifysjqxTb);

	/**
	 * 删除角色数据权限信息
	 * 
	 * @param userRolesjqx
	 * @return
	 */
	public boolean delUserRoleSjqx(String f_roleguid,String f_qxbh,String classifysjqxTb);

	/**
	 * 更新角色数据权限信息
	 * 
	 * @param userRolesjqx
	 * @return
	 */
	public boolean upUserRoleSjqx(ESUserRoleSjqxManage userRolesjqx);
	/**
	 * 根据表名查询字段
	 * 
	 * @return
	 */
	public List<?> getcolumnsBytableName(String columns,String tableName);
}
