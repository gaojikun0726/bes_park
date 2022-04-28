package com.efounder.JEnterprise.controller.basedatamanage.eqmanage;

import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesAlarmModel;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BesAlarmManageService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 1. 获取实时告警数据；
 * 2. 获取历史告警数据
 * @author xiepufeng
 *
 */
@Controller
@RequestMapping(value = "/alarm")
public class BesAlarmManageController {
	private static final Logger log = LoggerFactory.getLogger(BesAlarmManageController.class);

	@Autowired
	private BesAlarmManageService besAlarmManageService;


	/**
	 * 获取历史告警数据
	 * @return
	 */
	@RequestMapping(value = "/history", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo<BesAlarmModel> getHistoryAlarmData(Integer pageNum, BesAlarmModel besAlarmModel){

		return besAlarmManageService.getHistoryAlarmData(pageNum, besAlarmModel);
	}

	/**
	 * 获取报警信息记录
	 *
	 * @param map
	 * @param pageNum
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getHistoryAlarmData", method = RequestMethod.POST)
	public String getWarningInfoData(ModelMap map, Integer pageNum, BesAlarmModel besAlarmModel) {

		PageInfo<BesAlarmModel> page = besAlarmManageService.getHistoryAlarmData(pageNum,
				besAlarmModel);
		map.put("page", page);
		map.put("endtime", besAlarmModel.getEndTime());
		map.put("starttime", besAlarmModel.getStartTime());
		map.put("dataset", JsonMapper.toJsonString(page.getList()));
		return "view/dataAnalysis/wainingInfo/wainingInfoData_page";
	}
}