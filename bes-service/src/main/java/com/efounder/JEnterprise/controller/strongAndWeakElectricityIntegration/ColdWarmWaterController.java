package com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration;

import com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.common.JsonResult;
import com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.common.util.ObjectUtil;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.ColdWarmWaterVo;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.ColdWarmWaterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/**
 * 类名称：ColdWarmWaterController
 *  类描述：冷温水数据监控页面
 *   创建人：Yangjx
 *   创建时间：2019/8/14
 *   修改人：
 * 
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/view/strongAndWeakElectricityIntegration/coldWarmWater")
public class ColdWarmWaterController {
	private static final Logger log = LoggerFactory.getLogger(ColdWarmWaterController.class);
	
	@Autowired
	private ColdWarmWaterService coldWarmWaterService;
	
	/*
	 * 初始加载页面 未传参
	 */
    @GetMapping("/showInitPage")
    public String showInitPage() {
        log.info("#ColdWarmWaterController 进入冷温水监控页面");
        return "view/strongAndWeakElectricityIntegration/coldWarmWater";
    }
   
   /**
	 * 查询冷温水监控信息数据列表,分页
	 * @return
	 */
	@RequestMapping(value = "/inquireTask")
	@ResponseBody
	public JsonResult getColdWarmWaterDataList(Integer page,Integer limit) {
		log.info("#ColdWarmWaterController 查询冷温水数据信息任务");
		JsonResult jsonResult = new JsonResult();
		List<ColdWarmWaterVo> pageInfo = coldWarmWaterService.paging(page,limit);//查询出冷温水监控所有数据
		Integer totalNum = coldWarmWaterService.pageCount();//查询出所有数据总量
		jsonResult.setCode("0");
		jsonResult.setData(pageInfo);
		jsonResult.setResult(true);
		jsonResult.setCount(Long.valueOf(ObjectUtil.notNull(totalNum)?totalNum:0));
		jsonResult.setMsg("成功");
		return jsonResult;
	}


	@PostMapping("/testView")
	public String testView(String f_NAME, String f_ID,String f_Electric_meter_number, String f_SBQD, ModelMap map) {
		log.info("#ColdHeatSourceController testView数据信息任务");
//		return "redirect:/login";
//		return "view/integratedPlatform/ammeter";
		map.put("f_NAME", f_NAME);
		map.put("f_ID", f_ID);
		map.put("f_SBQD", f_SBQD);
		map.put("f_Electric_meter_number", f_Electric_meter_number);
		return "view/strongAndWeakElectricityIntegration/divColdWarmWater";
	}
   
   
   
   
    
}













