package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration;

import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.CgqVo;

import java.util.List;

public interface CgqService {

	/*
	 * 分页查询
	 */
	List<CgqVo> paging(Integer page,Integer limit);

	/*
	 * 分页总页数
	 */
	int pageCount();
	/*
	 * 根据设备类型分页查询
	 */
	List<CgqVo> getCgqTypeList(String f_type, Integer page, Integer limit);


}
