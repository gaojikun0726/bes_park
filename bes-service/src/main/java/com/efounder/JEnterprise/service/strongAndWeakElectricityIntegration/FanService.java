package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration;

import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.FanVo;

import java.util.List;

public interface FanService {

	/*
	 * 分页查询
	 */
	List<FanVo> paging(Integer page,Integer limit);
	
	/*
	 * 分页总页数
	 */
	int pageCount();
	
}
