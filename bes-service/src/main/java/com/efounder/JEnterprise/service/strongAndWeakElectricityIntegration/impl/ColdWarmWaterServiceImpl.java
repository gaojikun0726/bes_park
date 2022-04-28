package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.impl;

import com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration.ColdWarmWaterMapper;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.ColdWarmWaterVo;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.ColdWarmWaterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ColdWarmWaterService")
public class ColdWarmWaterServiceImpl implements ColdWarmWaterService{
	private static final Logger log = LoggerFactory.getLogger(ColdWarmWaterServiceImpl.class);
	@Autowired
	private ColdWarmWaterMapper coldWarmWaterMapper;
	
	/**
	 * 分页查询
	 */
	@Override
	public List<ColdWarmWaterVo> paging(Integer page,Integer limit) {
		log.info("ColdWarmWaterMapper 冷热源分页查询");
		if(page == null) {
			page = 1;
		}
		if(limit == null) {
			limit = 10;
		}
		List<ColdWarmWaterVo> list = coldWarmWaterMapper.paging((page-1)*limit,limit);
		return list;
	}
	@Override
	public int pageCount() {
		int pageCount = coldWarmWaterMapper.pageCount();
		return pageCount;
	}
}
