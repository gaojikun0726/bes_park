package com.efounder.JEnterprise.service.systemcenter.logmanage.Impl;

import com.efounder.JEnterprise.mapper.systemcenter.logmanage.ESSysOperateLogContentMapper;
import com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysOperateLogContent;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.systemcenter.logmanage.ESSysOperateLogContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Description 系统操作日志查看信息
 * @author liuhoujun
 */
@Service("ESSysOperateLogContentService")
public class ESSysOperateLogContentServiceImpl implements ESSysOperateLogContentService,ESBaseService{

	private static final Logger log = LoggerFactory.getLogger(ESSysOperateLogContentServiceImpl.class);
	@Autowired
	private ESSysOperateLogContentMapper esSysOperateLogContentMapper;
	
	
	@Override
	public List<ESSysOperateLogContent> loadsysOperatelogContentByKey(String f_id) {
		// TODO Auto-generated method stub
	return esSysOperateLogContentMapper.loadsysOperatelogContentByKey(f_id);
	}

}
