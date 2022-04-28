package com.efounder.JEnterprise.service.usercenter;

import com.efounder.JEnterprise.model.usercenter.ESUserGroupSjqxManage;

import java.util.List;

/**
 * @Description 用户组数据权限维护接口
 * @author gongfanfei
 */
public interface ESUserGroupSjqxManageService {

	/**
	 * 查询组织下所有用户组数据权限信息
	 *
	 * @return
	 */
	public List<ESUserGroupSjqxManage> findUserGroupSjqx();
	/**
	 * 根据三个主键联合查询组织下所有用户组数据权限信息
	 *
	 * @return
	 */
	public List<ESUserGroupSjqxManage> findUserGroupSjqxByThreeId(String f_zbh, String f_qxbh,String f_bhzd);

	/**
	 * 根据ID查询用户组数据权限信息
	 *
	 * @return
	 */
	//public ESUserGroupSjqxManage findUserGroupSjqxById(String f_zbh, String f_qxbh,String f_bhzd );
	public List<ESUserGroupSjqxManage> findUserGroupSjqxById(String f_zbh, String f_qxbh, String columns,
			String tableName, String classifysjqxTb);
	/**
	 * 搜索
	 */
	 public List<ESUserGroupSjqxManage> findUserGroupSjqxByKey(String keywords);
	

	/**
	 * 添加用户组数据权限
	 * 
	 * @return
	 */
	//public boolean addUserGroupSjqx(String f_zbh,String f_qxbh,String classifysjqxTb);
	public boolean addUserGroupSjqx(List<ESUserGroupSjqxManage> esuserGroupsjqxmanage,String classifysjqxTb);

	/**
	 * 删除用户组数据权限信息
	 * 
	 * @param usersjqx
	 * @return
	 */
	public boolean delUserGroupSjqx(String f_zbh,String f_qxbh,String classifysjqxTb);

	/**
	 * 更新用户组数据权限信息
	 * 
	 * @param usersjqx
	 * @return
	 */
	public boolean upUserGroupSjqx(ESUserGroupSjqxManage userGroupsjqx);
	/**
	 * 根据表名查询字段
	 * 
	 * @return
	 */
	public List<?> getcolumnsBytableName(String columns,String tableName);
}
