package com.efounder.JEnterprise.service.usercenter;

import com.efounder.JEnterprise.model.usercenter.ESUserGnqxManage;

import java.util.List;

/**
 * @Description 用户功能权限维护接口
 * @author gongfanfei
 */
public interface ESUserGnqxManageService {

	/**
	 * 查询组织下所有用户功能权限信息
	 *
	 * @return
	 */
	public List<ESUserGnqxManage> findUserGnqx();
	
	/**
	 * 根据用户编号查询功能权限编码集合
	 *
	 * @return
	 */
	public List<ESUserGnqxManage> findUserGnqxByYhbh(String f_yhbh,String usergnqxTb);

	/**
	 * 添加用户功能权限
	 * 
	 * @return
	 */
	public boolean addUserGnqx(List<ESUserGnqxManage> gnqxList,String tablename);

	/**
	 * 删除用户功能权限信息
	 * 
	 * @param userGnqx
	 * @return
	 */
	public boolean delUserGnqx(List<ESUserGnqxManage> gnqxList,String tablename);

	/**
	 * 更新用户功能权限信息
	 * 
	 * @param userGnqx
	 * @return
	 */
	public boolean upUserGnqx(List<ESUserGnqxManage> gnqxList,String tablename,String f_yhbh);
}
