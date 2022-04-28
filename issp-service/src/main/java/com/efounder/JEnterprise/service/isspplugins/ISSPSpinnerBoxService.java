package com.efounder.JEnterprise.service.isspplugins;

import com.core.common.ISSPReturnObject;

/**
 * 
 * 类名称：ISSPSpinnerBoxService 
 * 类描述：帮助组件加载数据service 
 * 创建人：huangxianbo 
 * 创建时间：2018年8月10日
 * 
 * @version 1.0.0
 *
 */
public interface ISSPSpinnerBoxService {

	/**
	 * 获取对应数据
	 * 
	 * @return
	 */
	public ISSPReturnObject getData(String currId, String dataId, String dataTxt, String dataTabName, String conColumn, String conValue);
}
