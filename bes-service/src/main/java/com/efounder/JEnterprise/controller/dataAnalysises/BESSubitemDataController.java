package com.efounder.JEnterprise.controller.dataAnalysises;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.dataAnalysises.BESSubitemData;
import com.efounder.JEnterprise.service.dataAnalysises.BESSubitemDataService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
/**
 * 能效分析->趋势分析
 * @author LvSihan
 *
 */
@Controller
@RequestMapping(value = "/view")
public class BESSubitemDataController {	
	private static final Logger log = LoggerFactory.getLogger(BESSubitemDataController.class);

	
	@Autowired
	private BESSubitemDataService subitemdataservice;
	
	
	/**
	 * 初始化到趋势统计分析界面
	 * 
	 * @param map
	 * @return 
	 */
	@RequestMapping(value = "/dataAnalysis/fx_qstjfx", method = RequestMethod.GET)
	public String getQstjfx(ModelMap map) {
		log.info("分项用能->趋势统计分析");
		return "view/dataAnalysis/fx-qstjfx";
	}
	/**
	 * 初始化到趋势统计分析界面(以分项为单位)
	 *
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/dataAnalysis/fx_qstjfx_dw", method = RequestMethod.GET)
	public String getQstjfxdw(ModelMap map) {
		log.info("分项用能->趋势统计分析");
		return "view/dataAnalysis/fxyn/fx-qstjfx-dw";
	}


	/**
	 * 初始化到同比环比分析界面(以分项为单位)
	 *
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/dataAnalysis/fx_tbhbfx_dw", method = RequestMethod.GET)
	public String getQstjfxDw(ModelMap map) {
		log.info("分项用能->同比环比分析");
		return "view/dataAnalysis/fxyn/fx-tbhbfx-dw";
	}
	
	/**
	 * 获取分项用能趋势统计数据
	 * @param besSubitemData
	 * @return
	 */
	@RequestMapping(value = "/dataAnalysis/getQstjSubitemData", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getQstjSubitemData (BESSubitemData besSubitemData){
		log.info("#获取分项用能趋势统计数据");
		ISSPReturnObject returnObject =  subitemdataservice.getQstjSubitemData(besSubitemData);
		return returnObject;
	}
	
	/**
	 * 获取分项用能同比环比统计数据
	 * @param besSubitemData
	 * @return
	 */
	@RequestMapping(value = "/dataAnalysis/getTbhbSubitemData", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getTbhbSubitemData (BESSubitemData besSubitemData){
		log.info("#获取分项用能趋势统计数据");
		ISSPReturnObject returnObject =  subitemdataservice.getTbhbSubitemData(besSubitemData);
		return returnObject;
	}
	
	
	/**
	 * 初始化到同比环比界面
	 * 
	 * @param map
	 * @return 
	 */
	@RequestMapping(value = "/dataAnalysis/fx_tbhbfx", method = RequestMethod.GET)
	public String getTbhbfx(ModelMap map) {
		log.info("分项用能->同比环比分析");
		return "view/dataAnalysis/fx-tbhbfx";
	}
	
	@RequestMapping(value = "/subitemData", method = RequestMethod.GET)
	public String getZzjg(@RequestParam(value = "keywords", required = false) String keywords,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,BESSubitemData subitemdata, ModelMap map) {
		PageInfo<BESSubitemData> page = subitemdataservice.getSubitemDataTab(pageNum, keywords);
		map.put("page", page);
		return "view/dataAnalysis/subitemData";
	}
	
	@RequestMapping(value = "/getSubitemDataList", method = RequestMethod.POST)
	@ResponseBody
	public List<BESSubitemData> getSubitemDataList (BESSubitemData besSubitemData){
		List<BESSubitemData> list = new ArrayList<BESSubitemData>();
		list = subitemdataservice.getSubitemDataList(besSubitemData);
		return list;
	}
	/**
	 * 图表数据查询
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/getSubitemData", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getSubitemData(BESSubitemData besSubitemData) {
		//统一返回格式
		ISSPReturnObject returnObject = subitemdataservice.getSubitemData(besSubitemData);
		return returnObject;
	}

	@RequestMapping(value = "/getSubentryData", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getSubentryData(BESSubitemData besSubitemData) {
		ISSPReturnObject returnObject = subitemdataservice.getSubentryData(besSubitemData);
		return returnObject;
	}


}
