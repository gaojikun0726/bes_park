package com.efounder.JEnterprise.service.usercenter;

import com.efounder.JEnterprise.model.usercenter.ESUserGroupGnqxManage;

import java.util.List;

/**
 * @Description 用户组功能权限维护接口
 * @author gongfanfei
 */
public interface ESUserGroupGnqxManageService {

	/**
	 * 查询组织下所有用户组功能权限信息
	 *
	 * @return
	 */
	public List<ESUserGroupGnqxManage> findUserGroupGnqx();
	/**
	 * 根据三个主键联合查询组织下所有用户组功能权限信息
	 *
	 * @return
	 */
	public List<ESUserGroupGnqxManage> findUserGroupGnqxByThreeId(String f_zbh,String f_qxbh, String f_gnbh);

	/**
	 * 根据ID查询用户组功能权限信息
	 *
	 * @return
	 */
	public List<ESUserGroupGnqxManage>  findUserGroupGnqxByYhbhAndQxbh(String f_zbh, String classifygnqxTb);
	/**
	 * 搜索
	 */
	 public List<ESUserGroupGnqxManage> findUserGroupGnqxByKey(String keywords);
	

	/**
	 * 添加用户组功能权限
	 * 
	 * @return
	 */
	public boolean addUserGroupGnqx(ESUserGroupGnqxManage userGroupGnqx);

	/**
	 * 删除用户组功能权限信息
	 * 
	 * @param userGroupGnqx
	 * @return
	 */
	public boolean delUserGroupGnqx(String f_zbh,String f_qxbh ,String guid);

	/**
	 * 更新用户组功能权限信息
	 * 
	 * @param userGroupGnqx
	 * @return
	 */
	public boolean upUserGroupGnqx(ESUserGroupGnqxManage userGroupGnqx);
	public boolean delUserGroupGnqx(List<ESUserGroupGnqxManage> deleteGgnqxList, String tablename);
	public boolean addUserGroupGnqx(List<ESUserGroupGnqxManage> gnqxList, String tablename);
	public boolean upUserGroupGnqx(List<ESUserGroupGnqxManage> gnqxList, String tablename, String f_zbh);
}
