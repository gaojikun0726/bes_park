package com.efounder.JEnterprise.service.usercenter;

import com.efounder.JEnterprise.model.usercenter.ESUserPostSjqxManage;

import java.util.List;

/**
 * @Description 岗位数据权限维护接口
 * @author gongfanfei
 */
public interface ESPostSjqxManageService {

	/**
	 * 查询组织下所有岗位数据权限信息
	 *
	 * @return
	 */
	public List<ESUserPostSjqxManage> findUserPostSjqx();
	/**
	 * 根据三个主键联合查询组织下所有岗位数据权限信息
	 *
	 * @return
	 */
	public List<ESUserPostSjqxManage> findUserPostSjqxByThreeId(String f_roleguid, String f_qxbh,String f_bhzd);

	/**
	 * 根据ID查询岗位数据权限信息
	 *
	 * @return
	 */
	//public ESUserPostSjqxManage findUserPostSjqxById(String f_gwguid, String f_qxbh,String f_bhzd );
	public List<ESUserPostSjqxManage> findUserPostSjqxById(String f_gwguid, String f_qxbh, String columns,
			String tableName, String classifysjqxTb);
	/**
	 * 搜索
	 */
	 public List<ESUserPostSjqxManage> findUserPostSjqxByKey(String keywords);
	

	/**
	 * 添加岗位数据权限
	 * 
	 * @return
	 */
	public boolean addUserPostSjqx(List<ESUserPostSjqxManage> userPostsjqx,String classifysjqxTb);
	
	/**
	 * 删除岗位数据权限信息
	 * 
	 * @param userPostsjqx
	 * @return
	 */
	public boolean delUserPostSjqx(String f_gwguid,String f_qxbh,String classifysjqxTb);

	/**
	 * 更新岗位数据权限信息
	 * 
	 * @param userPostsjqx
	 * @return
	 */
	public boolean upUserPostSjqx(ESUserPostSjqxManage userPostsjqx);
	/**
	 * 根据表名查询字段
	 * 
	 * @return
	 */
	public List<?> getcolumnsBytableName(String columns,String tableName);
	
}
