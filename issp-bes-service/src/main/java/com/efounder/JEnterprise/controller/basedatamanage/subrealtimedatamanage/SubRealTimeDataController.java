package com.efounder.JEnterprise.controller.basedatamanage.subrealtimedatamanage;


import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.service.subrealtimedatamanage.SubRealTimeDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 客户端实时数据订阅
 */
@Controller
@RequestMapping("/view/basedatamanage/pubsubmanage")
public class SubRealTimeDataController {


	@Resource
	private SubRealTimeDataService subRealTimeDataService;

	private static final Logger log = LoggerFactory.getLogger(SubRealTimeDataController.class);

	/**
	 * 客户端订阅消息
	 * @param event
	 * @return
	 */
	@RequestMapping(value = "/subscribe", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject subscribe(String event) {
		log.info("#订阅消息");
		return subRealTimeDataService.subscribe(event);
	}

	/**
	 * 客户端取消订阅
	 * @param event
	 * @return
	 */
	@RequestMapping(value = "/unsubscribe", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject unsubscribe(String event) {
		log.info("#取消订阅消息");
		return subRealTimeDataService.unsubscribe(event);
	}


}
