package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.impl;

import com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration.ColdHeatSourceMapper;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.ColdHeatSourceVo;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.ColdHeatSourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ColdHeatSourceService")
public class ColdHeatSourceServiceImpl implements ColdHeatSourceService{
	private static final Logger log = LoggerFactory.getLogger(ColdHeatSourceServiceImpl.class);
	@Autowired
	private ColdHeatSourceMapper coldHeatSourceMapper;
	
	/**
	 * 分页查询
	 */
	@Override
	public List<ColdHeatSourceVo> paging(Integer page,Integer limit) {
		log.info("ColdHeatSourceMapper 分页查询");
		if(page == null) {
			page = 1;
		}
		if(limit == null) {
			limit = 10;
		}
		List<ColdHeatSourceVo> list = coldHeatSourceMapper.paging((page-1)*limit,limit);
		return list;
	}
	@Override
	public int pageCount() {
		int pageCount = coldHeatSourceMapper.pageCount();
		return pageCount;
	}
}
