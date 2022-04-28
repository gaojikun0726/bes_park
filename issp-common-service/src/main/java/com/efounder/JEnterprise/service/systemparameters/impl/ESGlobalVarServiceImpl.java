package com.efounder.JEnterprise.service.systemparameters.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.mapper.systemparameters.ESGlobalVarMapper;
import com.efounder.JEnterprise.model.systemparameters.ESGlobalVar;
import com.efounder.JEnterprise.service.systemparameters.ESGlobalVarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/** 
 * 类名称：ESGlobalVarServiceImpl
 * 类描述：获取全局变量参数
 * 创建人：huangxianbo
 * 修改人：huangxianbo
 * 修改时间：2018年7月24日 
 * 修改备注：
 * @version 1.0.0 
 *
 */
@Service("ESGlobalVarService")
public class ESGlobalVarServiceImpl implements ESGlobalVarService {

	@Autowired
	private ESGlobalVarMapper esGlobalVarMapper;

	@Override
	public ISSPReturnObject getGlobalVarByKey(String key) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			ESGlobalVar esGlobalVar = esGlobalVarMapper.getGlobalVarByKey(key);
			if (esGlobalVar == null) {
				returnObject.setStatus("0");
				return returnObject;
			}
			returnObject.setData(esGlobalVar.getfVarValue());
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setStatus("0");
		}
		return returnObject;
	}
}
