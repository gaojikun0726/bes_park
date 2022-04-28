package com.efounder.JEnterprise.service.systemcenter.logmanage.Impl;

import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.systemcenter.logmanage.ESSysLoginLogMapper;
import com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysLoginLog;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.systemcenter.logmanage.ESSysLoginLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 系统登录日志
 * @author yujieying
 */
@Service("ESSysLoginLogService")
public class ESSysLoginLogServiceImpl implements ESSysLoginLogService, ESBaseService {

	private static final Logger log = LoggerFactory.getLogger(ESSysLoginLogServiceImpl.class);

	@Autowired
	private ESSysLoginLogMapper esSysLoginLogMapper;

	// 根据条件加载系统登录日志信息
	public PageInfo<ESSysLoginLog> loadsysLoginlogByKey(Integer bars,Integer pageNum, String keywords) {
		if (pageNum == null)
			pageNum = 1;
		if (bars == null) {
			bars = Constants.PAGE_SIZE;
		}
		PageHelper.startPage(pageNum,bars );
		List<ESSysLoginLog> list = esSysLoginLogMapper.loadsysLoginlogByKey(keywords);
		// 用PageInfo对结果进行包装
		PageInfo<ESSysLoginLog> page = new PageInfo<ESSysLoginLog>(list);
		return page;
	}

	// 用户登录日志后插入日志信息
	@Override
	public boolean addSysLoginLogid(ESSysLoginLog esSysLoginLog) {
		log.info("#esSysLoginLogMapper添加日志信息");
		return esSysLoginLogMapper.addSysLoginLogid(esSysLoginLog);
	}

}