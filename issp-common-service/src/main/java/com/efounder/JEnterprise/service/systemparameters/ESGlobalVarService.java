package com.efounder.JEnterprise.service.systemparameters;


import com.core.common.ISSPReturnObject;
/**
 * 全局参数
 * @author huangxianbo
 *
 */
public interface ESGlobalVarService {

	/**
	 * 获取系统全局参数
	 * @param key 参数对应的key值
	 * @return
	 */
	public ISSPReturnObject getGlobalVarByKey(String key);

}
