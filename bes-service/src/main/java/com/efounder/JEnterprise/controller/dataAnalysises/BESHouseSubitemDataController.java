package com.efounder.JEnterprise.controller.dataAnalysises;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.dataAnalysises.BesHouseholdSubitemData;
import com.efounder.JEnterprise.service.dataAnalysises.BesHouseholdSubitemDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 分户用能分户分项同比环比
 * @author liuzhen
 *
 */
@Controller
@RequestMapping(value = "/view/dataAnalysises")
public class BESHouseSubitemDataController {	
	private static final Logger log = LoggerFactory.getLogger(BESHouseSubitemDataController.class);
	
	
	@Autowired
	private BesHouseholdSubitemDataService besHouseholdSubitemDataService;
	
	/**
	 * 初始化到分户用能分户分项同比环比界面
	 * 
	 * @param map
	 * @return 
	 */
	@RequestMapping(value = "/fhfx_tbhbfx", method = RequestMethod.GET)
	public String getQstjfx(ModelMap map) {
		log.info("分户用能->分户分项同比环比");
		return "view/dataAnalysis/fhyn/fhfx-tbhbfx";
	}


	/**
	 * 初始化到分户用能分户分项同比环比界面
	 *
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/fhfx_tbhbfx_dw", method = RequestMethod.GET)
	public String getQstjfxdw(ModelMap map) {
		log.info("分户用能->分户分项同比环比");
		return "view/dataAnalysis/fhyn/fhfx-tbhbfx-dw";
	}
	
	
	/**
	 * 获取分户用能同比环比分析数据
	 * @param besSubitemData
	 * @return
	 */
	@RequestMapping(value = "/getTbhbHouseHoldSubitemData", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getTbhbHouseholdData (BesHouseholdSubitemData besHouseholdSubitemData){
		log.info("#获取分户用能同比环比数据");
		ISSPReturnObject returnObject =  besHouseholdSubitemDataService.getHouseholdSubitemData(besHouseholdSubitemData);
		return returnObject;
	}

}
