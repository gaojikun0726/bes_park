package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration;

import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.ColdWarmWaterVo;

import java.util.List;

public interface ColdWarmWaterService {

	/*
	 * 分页查询
	 */
	List<ColdWarmWaterVo> paging(Integer page,Integer limit);
	
	/*
	 * 分页总页数
	 */
	int pageCount();
	
}
