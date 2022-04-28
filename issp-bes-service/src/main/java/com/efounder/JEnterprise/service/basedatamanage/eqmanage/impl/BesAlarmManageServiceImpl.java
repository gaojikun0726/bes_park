package com.efounder.JEnterprise.service.basedatamanage.eqmanage.impl;

import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BesAlarmManageMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesAlarmModel;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BesAlarmManageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("BesAlarmManageService")
public class BesAlarmManageServiceImpl implements BesAlarmManageService {
	private static final Logger log = LoggerFactory.getLogger(BesAlarmManageServiceImpl.class);

	@Autowired
	private BesAlarmManageMapper besAlarmManageMapper;


	@Override
	public PageInfo<BesAlarmModel> getHistoryAlarmData(Integer pageNum, BesAlarmModel besAlarmModel) {

		if (pageNum == null)
		{
			pageNum = 1;
		}
		PageHelper.startPage(pageNum, Constants.PAGE_SIZE);

		List<BesAlarmModel> pageList = besAlarmManageMapper.findPage(besAlarmModel);

		PageInfo<BesAlarmModel> page = new PageInfo<>(pageList);

		return page;
	}
}
