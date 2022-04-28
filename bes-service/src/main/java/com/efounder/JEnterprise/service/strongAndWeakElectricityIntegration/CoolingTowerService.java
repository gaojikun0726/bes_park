package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration;

import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.CoolingTowerVo;

import java.util.List;

public interface CoolingTowerService {

	/*
	 * 分页查询
	 */
	List<CoolingTowerVo> paging(Integer page,Integer limit);
	
	/*
	 * 分页总页数
	 */
	int pageCount();
	
}
