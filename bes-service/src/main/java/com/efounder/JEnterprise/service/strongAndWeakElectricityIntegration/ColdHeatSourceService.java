package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration;

import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.ColdHeatSourceVo;

import java.util.List;

public interface ColdHeatSourceService {

	/*
	 * 分页查询
	 */
	List<ColdHeatSourceVo> paging(Integer page,Integer limit);
	
	/*
	 * 分页总页数
	 */
	int pageCount();
	
}
