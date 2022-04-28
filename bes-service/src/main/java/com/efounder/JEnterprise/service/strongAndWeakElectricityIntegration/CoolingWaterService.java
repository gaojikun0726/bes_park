package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration;

import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.CoolingWaterVo;

import java.util.List;

public interface CoolingWaterService {

	/*
	 * 分页查询
	 */
	List<CoolingWaterVo> paging(Integer page,Integer limit);
	
	/*
	 * 分页总页数
	 */
	int pageCount();
	
}
