package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.impl;

import com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration.CgqMapper;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.CgqVo;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.CgqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CgqService")
public class CgqServiceImpl implements CgqService{
	private static final Logger log = LoggerFactory.getLogger(CgqServiceImpl.class);
	@Autowired
	private CgqMapper cgqMapper;

	/**
	 * 分页查询
	 */
	@Override
	public List<CgqVo> paging(Integer page,Integer limit) {
		log.info("CgqMapper 传感器分页查询");
		if(page == null) {
			page = 1;
		}
		if(limit == null) {
			limit = 10;
		}
		List<CgqVo> list = cgqMapper.paging((page-1)*limit,limit);
		return list;
	}
	@Override
	public int pageCount() {
		int pageCount = cgqMapper.pageCount();
		return pageCount;
	}
	@Override
	public List<CgqVo> getCgqTypeList(String f_type, Integer page, Integer limit) {
		log.info("CgqMapper 传感器根据传感器类型进行分页查询");
		if(page == null) {
			page = 1;
		}
		if(limit == null) {
			limit = 10;
		}
		List<CgqVo> list = cgqMapper.getCgqTypeList(f_type,(page-1)*limit,limit);
		return list;
	}
}
