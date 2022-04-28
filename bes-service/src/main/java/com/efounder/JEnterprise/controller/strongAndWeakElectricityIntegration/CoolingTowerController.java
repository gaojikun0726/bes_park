package com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration;

import com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.common.JsonResult;
import com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.common.util.ObjectUtil;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.CoolingTowerVo;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.CoolingTowerService;
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
 * 类名称：CoolingTowerController
 *  类描述：冷却塔数据监控页面
 *   创建人：Yangjx
 *   创建时间：2019/8/15
 *   修改人：
 * 
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/view/strongAndWeakElectricityIntegration/coolingTower")
public class CoolingTowerController {
	private static final Logger log = LoggerFactory.getLogger(CoolingTowerController.class);
	
	@Autowired
	private CoolingTowerService coolingTowerService;
	
	/*
	 * 初始加载页面 未传参
	 */
    @GetMapping("/showInitPage")
    public String showInitPage() {
        log.info("#CoolingTowerController 进入冷却塔监控页面");
        return "view/strongAndWeakElectricityIntegration/coolingTower";
    }
   
   /**
	 * 查询冷却塔监控信息数据列表,分页
	 * @return
	 */
	@RequestMapping(value = "/inquireTask")
	@ResponseBody
	public JsonResult getCoolingTowerDataList(Integer page,Integer limit) {
		log.info("#CoolingTowerController 查询冷却塔数据信息任务");
		JsonResult jsonResult = new JsonResult();
		List<CoolingTowerVo> pageInfo = coolingTowerService.paging(page,limit);//查询出冷却塔监控所有数据
		Integer totalNum = coolingTowerService.pageCount();//查询出所有数据总量
		jsonResult.setCode("0");
		jsonResult.setData(pageInfo);
		jsonResult.setResult(true);
		jsonResult.setCount(Long.valueOf(ObjectUtil.notNull(totalNum)?totalNum:0));
		jsonResult.setMsg("成功");
		return jsonResult;
	}
   
	@PostMapping("/testView")
	public String testView(String name, String id,String electric_meter_number, String fanSwitch, ModelMap map) {
		log.info("#CoolingTowerController testView数据信息任务");
		map.put("name", name);
		map.put("id", id);
		map.put("fanSwitch", fanSwitch);
		map.put("electric_meter_number", electric_meter_number);
		return "view/strongAndWeakElectricityIntegration/divCoolingTower";
	}
   
   
   
   
    
}













