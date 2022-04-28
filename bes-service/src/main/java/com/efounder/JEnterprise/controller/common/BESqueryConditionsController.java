package com.efounder.JEnterprise.controller.common;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.service.common.BESQueryConditionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 页面中的公用查询筛选条件
 * 地区，站点 ，能源类型
 * @author LvSihan
 *
 */
@Controller
@RequestMapping(value = "/view/common")
public class BESqueryConditionsController {
	private static final Logger log = LoggerFactory.getLogger(BESqueryConditionsController.class);
	@Autowired
	private BESQueryConditionsService besQueryConditionsService;

	/**
	 * 查询能源编号列表
	 * @return
	 */
	@RequestMapping(value = "/selectfNybhList", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject selectfNybhList() {
		log.info("# BESSubitemConfController 获取能源编号列表");
		ISSPReturnObject returnObject = besQueryConditionsService.selectfNybhList();	
		return returnObject;
	}
}
