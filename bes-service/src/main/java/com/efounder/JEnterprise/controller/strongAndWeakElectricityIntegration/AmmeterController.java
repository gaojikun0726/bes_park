package com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration;

import com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.common.JsonResult;
import com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.common.util.ObjectUtil;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.AmmeterVo;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.AmmeterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 类名称：AmmeterController
 *  类描述：电表数据监控页面
 *   创建人：Yangjx
 *   创建时间：2019/8/15
 *   修改人：
 * 
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/view/strongAndWeakElectricityIntegration/ammeter")
public class AmmeterController {
	private static final Logger log = LoggerFactory.getLogger(AmmeterController.class);
	
	@Autowired
	private AmmeterService ammeterService;
	
	/*
	 * 初始加载页面 未传参
	 */
    @GetMapping("/showInitPage")
    public String showInitPage() {
        log.info("#AmmeterController 进入电表监控页面");
        return "view/strongAndWeakElectricityIntegration/ammeter";
    }
   
   /**
	 * 查询电表监控信息数据列表,分页
	 * @return
	 */
	@RequestMapping(value = "/inquireTask")
	@ResponseBody
	public JsonResult getAmmeterDataList(Integer page,Integer limit) {
		log.info("#AmmeterController 查询电表数据信息任务");
		JsonResult jsonResult = new JsonResult();
		List<AmmeterVo> pageInfo = ammeterService.paging(page,limit);//查询出电表监控所有数据
		Integer totalNum = ammeterService.pageCount();//查询出所有数据总量
		jsonResult.setCode("0");
		jsonResult.setData(pageInfo);
		jsonResult.setResult(true);
		jsonResult.setCount(Long.valueOf(ObjectUtil.notNull(totalNum)?totalNum:0));
		jsonResult.setMsg("成功");
		return jsonResult;
	}
   
	@PostMapping("/testView")
	public String testView(String name) {
		log.info("#AmmeterController testView数据信息任务");
//		return "redirect:/login";
//		return "view/integratedPlatform/ammeter";
		return "view/strongAndWeakElectricityIntegration/divAmmeter";
	}
   
}













