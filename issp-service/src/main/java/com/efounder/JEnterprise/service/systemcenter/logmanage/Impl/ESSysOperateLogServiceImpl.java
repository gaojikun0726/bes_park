package com.efounder.JEnterprise.service.systemcenter.logmanage.Impl;

import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.systemcenter.logmanage.ESSysOperateLogMapper;
import com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysLoginLog;
import com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysOperateLog;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.systemcenter.logmanage.ESSysOperateLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 系统操作日志维护的实现类
 * @author liuhoujun
 */
@Service("ESSysOperateLogService")
public class ESSysOperateLogServiceImpl implements ESSysOperateLogService, ESBaseService{
	
	private static final Logger log = LoggerFactory.getLogger(ESSysOperateLogServiceImpl.class);

	@Autowired
	private ESSysOperateLogMapper esSysOperateLogMapper;
	
	//加载日志的信息
	@Override
	public PageInfo<ESSysOperateLog> getlmcxList(Integer bars,Integer pageNum,String fcomment, String fname, String ftype, String startTime, String endTime) {
		if (pageNum == null)
			pageNum = 1;
		if (bars == null) {
			bars = Constants.PAGE_SIZE;
		}
		PageHelper.startPage(pageNum, bars);
		List<ESSysOperateLog> list = esSysOperateLogMapper.getlmcxList(fcomment, fname, ftype, startTime, endTime);
		// 用PageInfo对结果进行包装
		return new PageInfo<ESSysOperateLog>(list);
	}

	// 根据条件加载操作日志信息
	public PageInfo<ESSysLoginLog> loadsysOperateloglistss(Integer bars, Integer pageNum, String keywords) {
		if (pageNum == null)
			pageNum = 1;
		if (bars == null) {
			bars = Constants.PAGE_SIZE;
		}
		PageHelper.startPage(pageNum,bars );
		List<ESSysLoginLog> list = esSysOperateLogMapper.loadsysOperateloglistss(keywords);
		// 用PageInfo对结果进行包装
		PageInfo<ESSysLoginLog> page = new PageInfo<ESSysLoginLog>(list);
		return page;
	}

}
