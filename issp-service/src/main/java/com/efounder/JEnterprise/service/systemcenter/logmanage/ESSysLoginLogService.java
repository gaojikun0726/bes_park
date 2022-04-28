package com.efounder.JEnterprise.service.systemcenter.logmanage;

import com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysLoginLog;
import com.github.pagehelper.PageInfo;

/**
 * @Description 系统登录日志维护
 * @author  yujieying
 *
 */
public interface ESSysLoginLogService {
	

	    //根据条件加载系统登录日志信息
		public PageInfo<ESSysLoginLog> loadsysLoginlogByKey(Integer bars,Integer pageNum,  String keywords);

		//用户登录后插入日志信息
		public boolean addSysLoginLogid(ESSysLoginLog esSysLoginLog);	
		
		
}
