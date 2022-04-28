package com.efounder.JEnterprise.controller.dataAnalysises;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.dataAnalysises.BesHouseholdData;
import com.efounder.JEnterprise.service.dataAnalysises.BesHouseholdDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 分户用能趋势统计分析和同比环比分析
 * @author liuzhen
 *
 */
@Controller
@RequestMapping(value = "/view/dataAnalysises")
public class BESHouseHoldDataController {	
	
	private static final Logger log = LoggerFactory.getLogger(BESHouseHoldDataController.class);
	
	
	@Autowired
	private BesHouseholdDataService besHouseholdDataService;
	
	/**
	 * 初始化到分户用能趋势统计分析界面
	 * 
	 * @param map
	 * @return 
	 */
	@RequestMapping(value = "/fh_qstjfx", method = RequestMethod.GET)
	public String getQstjfx(ModelMap map) {
		log.info("分户用能->趋势统计分析");
		return "view/dataAnalysis/fhyn/fh-qstjfx";
	}


	/**
	 * 初始化到分户用能趋势统计分析界面(按单位)
	 *
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/fh_qstjfx_dw", method = RequestMethod.GET)
	public String getQstjfxdw(ModelMap map) {
		log.info("分户用能->趋势统计分析");
		return "view/dataAnalysis/fhyn/fh-qstjfx-dw";
	}
	
	/**
	 * 初始化到分户用能趋势统计分析界面
	 * 
	 * @param map
	 * @return 
	 */
	@RequestMapping(value = "/fh_tbhbfx", method = RequestMethod.GET)
	public String getTbhbfx(ModelMap map) {
		log.info("分户用能->同比环比分析");
		return "view/dataAnalysis/fhyn/fh-tbhbfx";
	}

	/**
	 * 初始化到分户用能趋势统计分析界面(以分户为单位)
	 *
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/fh_tbhbfx_dw", method = RequestMethod.GET)
	public String getTbhbfxdw(ModelMap map) {
		log.info("分户用能->同比环比分析");
		return "view/dataAnalysis/fhyn/fh-tbhbfx-dw";
	}
	
	
	/**
	 * 获取分户用能趋势统计分析数据
	 * @param besHouseholdData
	 * @return
	 */
	@RequestMapping(value = "/getHouseHoldData", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getHouseHoldData (BesHouseholdData besHouseholdData, HttpServletRequest request){
		log.info("#获取分户用能趋势统计数据");
		ISSPReturnObject returnObject =  besHouseholdDataService.getHouseholdData(besHouseholdData,request);
		return returnObject;
	}
	
		/**
	 * @Description: 获取分户用能趋势统计分析数据
	 * @param:
	 * @return:
	 */
	@RequestMapping(value = "/queryHouseHoldData", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject queryHouseHoldData(BesHouseholdData besHouseholdData){
		log.info("#获取高速(服务区,收费站,隧道)的数据");
		ISSPReturnObject returnObject =  besHouseholdDataService.queryHouseHoldData(besHouseholdData);
		return returnObject;
	}
	
	/**
	 * 获取分户用能同比环比分析数据
	 * @param besHouseholdData
	 * @return
	 */
	@RequestMapping(value = "/getTbhbHouseHoldData", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getTbhbHouseholdData (BesHouseholdData besHouseholdData){
		log.info("#获取分户用能同比环比数据");
		ISSPReturnObject returnObject =  besHouseholdDataService.getTbhbHouseholdData(besHouseholdData);
		return returnObject;
	}

	/**
	 * 获取分户用能趋势统计分析数据
	 * @param besHouseholdData
	 * @return
	 */
	@RequestMapping(value = "/getHouseHoldEveryData", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getHouseHoldEveryData (BesHouseholdData besHouseholdData, HttpServletRequest request){
		log.info("#获取分户用能趋势统计数据");
		ISSPReturnObject returnObject =  besHouseholdDataService.getHouseholdEveryData(besHouseholdData,request);
		return returnObject;
	}


	/**
	 * 获取分户年数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getHouseDataYearData", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getHouseDataYearData (HttpServletRequest request){
		log.info("#获取分户年数据和月峰值");
		ISSPReturnObject returnObject =  besHouseholdDataService.getHouseDataYearData(request);
		return returnObject;
	}



}
