package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.impl;

import com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration.CoolingWaterMapper;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.CoolingWaterVo;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.CoolingWaterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CoolingWaterService")
public class CoolingWaterServiceImpl implements CoolingWaterService{
	private static final Logger log = LoggerFactory.getLogger(CoolingWaterServiceImpl.class);
	@Autowired
	private CoolingWaterMapper coolingWaterMapper;
	
	/**
	 * 分页查询
	 */
	@Override
	public List<CoolingWaterVo> paging(Integer page,Integer limit) {
		log.info("CoolingWaterMapper 冷热源分页查询");
		if(page == null) {
			page = 1;
		}
		if(limit == null) {
			limit = 10;
		}
		List<CoolingWaterVo> list = coolingWaterMapper.paging((page-1)*limit,limit);
		return list;
	}
	@Override
	public int pageCount() {
		int pageCount = coolingWaterMapper.pageCount();
		return pageCount;
	}
}
