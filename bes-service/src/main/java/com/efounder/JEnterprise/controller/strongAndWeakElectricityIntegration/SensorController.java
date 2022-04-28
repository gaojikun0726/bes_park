package com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration;

import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.FanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 类名称：SensorController
 *  类描述：传感器数据监控页面
 *   创建人：Yangjx
 *   创建时间：2019/9/03
 *   修改人：
 * 
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/view/strongAndWeakElectricityIntegration/sensor")
public class SensorController {
	private static final Logger log = LoggerFactory.getLogger(SensorController.class);
	
	@Autowired
	private FanService fanService;
	
	/*
	 * 初始加载页面 未传参
	 */
    @GetMapping("/showInitPage")
    public String showInitPage() {
        log.info("#SensorController 进入传感器监控页面");
        return "view/strongAndWeakElectricityIntegration/sensor";
    }
   
   /**
	 * 查询风机监控信息数据列表,分页
	 * @return
	 */
//	@RequestMapping(value = "/inquireTask")
//	@ResponseBody
//	public JsonResult getFanDataList(Integer page,Integer limit) {
//		log.info("#FanController 查询风机数据信息任务");
//		JsonResult jsonResult = new JsonResult();
//		List<FanVo> pageInfo = fanService.paging(page,limit);//查询出风机监控所有数据
//		Integer totalNum = fanService.pageCount();//查询出所有数据总量
//		jsonResult.setCode("0");
//		jsonResult.setData(pageInfo);
//		jsonResult.setResult(true);
//		jsonResult.setCount(Long.valueOf(ObjectUtil.notNull(totalNum)?totalNum:0));
//		jsonResult.setMsg("成功");
//		return jsonResult;
//	}
//   
//	@PostMapping("/testView")
//	public String testView(String name) {
//		log.info("#FanController testView数据信息任务");
////		return "redirect:/login";
////		return "view/integratedPlatform/ammeter";
//		return "view/integratedPlatform/divFan";
//	}
   
   
   
    
}













