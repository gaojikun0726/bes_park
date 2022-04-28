package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.impl;

import com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration.AmmeterMapper;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.AmmeterVo;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.AmmeterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AmmeterService")
public class AmmeterServiceImpl implements AmmeterService{
	private static final Logger log = LoggerFactory.getLogger(AmmeterServiceImpl.class);
	@Autowired
	private AmmeterMapper ammeterMapper;
	
	/**
	 * 分页查询
	 */
	@Override
	public List<AmmeterVo> paging(Integer page,Integer limit) {
		log.info("AmmeterMapper 电表分页查询");
		if(page == null) {
			page = 1;
		}
		if(limit == null) {
			limit = 10;
		}
		List<AmmeterVo> list = ammeterMapper.paging((page-1)*limit,limit);
		return list;
	}
	@Override
	public int pageCount() {
		int pageCount = ammeterMapper.pageCount();
		return pageCount;
	}
}
