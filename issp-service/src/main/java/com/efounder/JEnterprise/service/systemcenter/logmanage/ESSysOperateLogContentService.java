package com.efounder.JEnterprise.service.systemcenter.logmanage;

import com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysOperateLogContent;

import java.util.List;

/**
 * @Description 系统操作日志详细信息展示
 * @author liuhoujun
 *
 */
public interface ESSysOperateLogContentService {

	// 根据条件加载系统操作日志信息
		public List<ESSysOperateLogContent> loadsysOperatelogContentByKey(String f_id);
}
