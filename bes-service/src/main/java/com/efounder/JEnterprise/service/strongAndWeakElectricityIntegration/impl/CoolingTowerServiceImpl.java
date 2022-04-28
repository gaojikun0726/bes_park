package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.impl;

import com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration.CoolingTowerMapper;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.CoolingTowerVo;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.CoolingTowerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CoolingTowerService")
public class CoolingTowerServiceImpl implements CoolingTowerService{
	private static final Logger log = LoggerFactory.getLogger(CoolingTowerServiceImpl.class);
	@Autowired
	private CoolingTowerMapper coolingTowerMapper;
	
	/**
	 * 分页查询
	 */
	@Override
	public List<CoolingTowerVo> paging(Integer page,Integer limit) {
		log.info("CoolingTowerMapper 冷却塔分页查询");
		if(page == null) {
			page = 1;
		}
		if(limit == null) {
			limit = 10;
		}
		List<CoolingTowerVo> list = coolingTowerMapper.paging((page-1)*limit,limit);
		return list;
	}
	@Override
	public int pageCount() {
		int pageCount = coolingTowerMapper.pageCount();
		return pageCount;
	}
}
