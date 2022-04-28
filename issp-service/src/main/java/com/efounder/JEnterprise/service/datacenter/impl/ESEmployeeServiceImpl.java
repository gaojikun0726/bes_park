package com.efounder.JEnterprise.service.datacenter.impl;

import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.datacenter.ESEmployeeMapper;
import com.efounder.JEnterprise.model.datacenter.ESEmployee;
import com.efounder.JEnterprise.service.datacenter.ESEmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ESEmployeeService")
public class ESEmployeeServiceImpl implements ESEmployeeService{
	
	@Autowired
	private ESEmployeeMapper esEmployeeMapper;
	
	@Override
	public PageInfo<ESEmployee> findByPage(Integer pageNum, String keywords) {
		if (pageNum == null)
			pageNum = 1;
		PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
		List<ESEmployee> list = esEmployeeMapper.findByPage(keywords);
		PageInfo<ESEmployee> page = new PageInfo<ESEmployee>(list);
		return page;
	}
	
}
