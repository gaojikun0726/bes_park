package com.efounder.JEnterprise.service.datacenter;

import com.efounder.JEnterprise.model.datacenter.ESEmployee;
import com.efounder.JEnterprise.service.ESBaseService;
import com.github.pagehelper.PageInfo;

public interface ESEmployeeService extends ESBaseService{
	
	public PageInfo<ESEmployee> findByPage(Integer pageNum, String keywords);

}
