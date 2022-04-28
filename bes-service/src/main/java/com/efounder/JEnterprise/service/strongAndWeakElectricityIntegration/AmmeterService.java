package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration;

import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.AmmeterVo;

import java.util.List;

public interface AmmeterService {

	/*
	 * 分页查询
	 */
	List<AmmeterVo> paging(Integer page,Integer limit);
	
	/*
	 * 分页总页数
	 */
	int pageCount();
	
}
