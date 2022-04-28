package com.efounder.JEnterprise.service.usercenter;

import com.efounder.JEnterprise.model.usercenter.ESUserPostGnqxManage;

import java.util.List;

/**
 * @Description 岗位功能权限维护接口
 * @author gongfanfei
 *
 */
public interface ESPostGnqxManageService {

	/**
	 * 查询组织下所有岗位功能权限信息
	 *
	 * @return
	 */
	public List<ESUserPostGnqxManage> findUserPostGnqx();
	/**
	 * 根据三个主键联合查询组织下所有岗位功能权限信息
	 *
	 * @return
	 */
	public List<ESUserPostGnqxManage> findUserPostGnqxByThreeId(String f_gwguid, String f_qxbh, String f_sjbh);

	/**
	 * 根据ID查询岗位功能权限信息
	 *
	 * @return
	 */
	public List<ESUserPostGnqxManage> findUserPostGnqxByGwbhAndQxbh(String f_gwguid, String f_qxbh);
	/**
	 * 搜索
	 */
	 public List<ESUserPostGnqxManage> findUserPostGnqxByKey(String keywords);
	

	/**
	 * 添加岗位功能权限
	 * 
	 * @return
	 */
	public boolean addUserPostGnqx(ESUserPostGnqxManage userPostGnqx);

	/**
	 * 删除岗位功能权限信息
	 * 
	 * @param userPostGnqx
	 * @return
	 */
	//public boolean delUserPostGnqx(String f_gwguid,String f_qxbh,String guid);
	public boolean delUserPostGnqx(List<ESUserPostGnqxManage> deleteGgnqxList, String tablename);

	/**
	 * 更新岗位功能权限信息
	 * 
	 * @param userPostGnqx
	 * @return
	 */
	public boolean upUserPostGnqx(ESUserPostGnqxManage userPostGnqx);
	public List<ESUserPostGnqxManage> findUserPostGnqxByYhbhAndQxbh(String f_gwguid, String classifygnqxTb);
	public boolean addUserPostGnqx(List<ESUserPostGnqxManage> gnqxList, String tablename);
	public boolean upUserPostGnqx(List<ESUserPostGnqxManage> gnqxList, String tablename, String f_gwguid);
}
