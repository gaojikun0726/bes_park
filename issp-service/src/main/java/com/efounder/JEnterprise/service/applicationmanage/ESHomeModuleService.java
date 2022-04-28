package com.efounder.JEnterprise.service.applicationmanage;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.applicationmanage.ESHomeModule;
import com.github.pagehelper.PageInfo;

public interface ESHomeModuleService {
	/**
	 * 分页查询方法
	 * @param pageNum
	 * @param keywords
	 * @return
	 */
	public PageInfo<ESHomeModule> findByPage(Integer bars,Integer pageNum, String keywords);
	
	/**
	 * 添加模块信息
	 * @param esHomeModule
	 * @return
	 */
	public ISSPReturnObject addESHomeModule(ESHomeModule esHomeModule);
	
	/**
	 * 删除模块信息
	 * @param guid
	 * @return
	 */
	public ISSPReturnObject delESHomeModule(String guid);
	
	/**
	 * 更新模块信息
	 * @param esHomeModule
	 * @return
	 */
	public ISSPReturnObject updESHomeModule(ESHomeModule esHomeModule);
	
	/**
	 * 根据guid查询模块信息
	 * @param guid
	 * @return
	 */
	public ISSPReturnObject loadESHomeModuleByGuid(String guid);
}
