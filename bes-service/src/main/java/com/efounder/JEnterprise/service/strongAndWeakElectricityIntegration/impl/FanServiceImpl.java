package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.impl;

import com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration.FanMapper;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.FanVo;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.FanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("FanService")
public class FanServiceImpl implements FanService{
	private static final Logger log = LoggerFactory.getLogger(FanServiceImpl.class);
	@Autowired
	private FanMapper fanMapper;
	
	/**
	 * 分页查询
	 */
	@Override
	public List<FanVo> paging(Integer page,Integer limit) {
		log.info("FanMapper 风机分页查询");
		if(page == null) {
			page = 1;
		}
		if(limit == null) {
			limit = 10;
		}
		List<FanVo> list = fanMapper.paging((page-1)*limit,limit);
		return list;
	}
	@Override
	public int pageCount() {
		int pageCount = fanMapper.pageCount();
		return pageCount;
	}
}
