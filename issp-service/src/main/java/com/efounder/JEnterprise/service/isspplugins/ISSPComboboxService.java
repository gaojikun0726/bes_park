package com.efounder.JEnterprise.service.isspplugins;

import com.core.common.ISSPReturnObject;

/**
 * 
 * 类名称：ISSPComboboxService 
 * 类描述：帮助组件加载数据service 
 * 创建人：huangxianbo 
 * 创建时间：2018年6月25日
 * 
 * @version 1.0.0
 *
 */
public interface ISSPComboboxService {

	/**
	 * 获取对应数据
	 * 
	 * @return
	 */
	public ISSPReturnObject getData(String tabName, String disCol, String disColId, String conColumn, String conValue);
}
