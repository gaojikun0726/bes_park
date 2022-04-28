package com.efounder.JEnterprise.service.usercenter;

import com.efounder.JEnterprise.model.usercenter.ESUserSjqxManage;

import java.util.List;

/**
 * @Description 用户数据权限维护接口
 * @author gongfanfei
 */
public interface ESUserSjqxManageService {

	/**
	 * 查询组织下所有用户数据权限信息
	 *
	 * @return
	 */
	public List<ESUserSjqxManage> findUserSjqx();
	/**
	 * 根据三个主键联合查询组织下所有用户数据权限信息
	 *
	 * @return
	 */
	public List<ESUserSjqxManage> findUserSjqxByThreeId(String f_yhbh, String f_qxbh,String f_bhzd);

	/**
	 * 根据ID查询用户数据权限信息
	 *
	 * @return
	 */
	public ESUserSjqxManage findUserSjqxById(String f_yhbh, String f_qxbh,String f_bhzd );
	/**
	 * 搜索
	 */
	 public List<ESUserSjqxManage> findUserSjqxByKey(String keywords);
	 
	 //=====
	 public List<ESUserSjqxManage> findUserSjqxList(String f_yhbh, String f_qxbh,String columns,String tableName,String usersjqxTb);
	

	/**
	 * 添加用户数据权限
	 * 
	 * @return
	 */
	public boolean addUserSjqx(List<ESUserSjqxManage> esusersjqxmanage,String usersjqxTb);

	/**
	 * 删除用户数据权限信息
	 * 
	 * @param usersjqx
	 * @return
	 */
	public boolean delUserSjqx(String f_yhbh,String f_qxbh,String usersjqxTb);
	/**
	 * 删除部分用户数据权限信息
	 * 
	 * @param usersjqx
	 * @return
	 */
	public boolean delPartUserSjqx(List<String> delsjList,String f_yhbh,String f_qxbh,String usersjqxTb);

	/**
	 * 更新用户数据权限信息
	 * 
	 * @param usersjqx
	 * @return
	 */
	public boolean upUserSjqx(ESUserSjqxManage usersjqx);
	/**
	 * 根据表名查询字段
	 * 
	 * @return
	 */
	public List<?> getcolumnsBytableName(String columns,String tableName);
}
