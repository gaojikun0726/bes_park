package com.efounder.JEnterprise.service.common.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.mapper.basedatamanage.enegrycollectionmanage.BESEnergyTypeMapper;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESEnergyType;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.common.BESQueryConditionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * 页面公用的查询条件接口实现类
 * @author LvSihan
 *
 */
@Service("BESQueryConditionsService")
public class BESQueryConditionsServiceImpl implements BESQueryConditionsService,ESBaseService{
	@Autowired
	private BESEnergyTypeMapper besEnergyTypeMapper;

	/**
	 * 获取能源类型。编号
	 */
	@Override
	public ISSPReturnObject selectfNybhList() {
			ISSPReturnObject returnObject = new ISSPReturnObject();	
			try {
				List<BESEnergyType> list = besEnergyTypeMapper.getEnergyTypeList(null);
				returnObject.setList(list);
				returnObject.setMsg("查询能源编号列表成功");
				returnObject.setStatus("1");
			} catch (Exception e) {
				returnObject.setMsg("查询能源编号列表失败");
				returnObject.setStatus("0");
			}
			return returnObject;
	}
	

}
