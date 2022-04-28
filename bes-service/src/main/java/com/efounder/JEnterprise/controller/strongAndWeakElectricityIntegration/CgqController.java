package com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration;

import com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.common.JsonResult;
import com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.common.util.ObjectUtil;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.CgqVo;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.CgqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 类名称：CgqController
 *  类描述：传感器数据监控页面
 *   创建人：wzx
 *   创建时间：2020-8-12 10:21:44
 *   修改人：
 *
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/view/strongAndWeakElectricityIntegration/cgq")
public class CgqController {
	private static final Logger log = LoggerFactory.getLogger(CgqController.class);

	@Autowired
	private CgqService cgqService;

	/*
	 * 初始加载页面 未传参
	 */
    @GetMapping("/showInitPage")
    public String showInitPage() {
        log.info("#CgqController 进入传感器监控页面");
        return "view/strongAndWeakElectricityIntegration/cgq";
    }

   /**
	 * 查询传感器监控信息数据列表,分页
	 * @return
	 */
	@RequestMapping(value = "/inquireTask")
	@ResponseBody
	public JsonResult getCgqDataList(Integer page,Integer limit) {
		log.info("#CgqController 查询传感器数据信息任务");
		JsonResult jsonResult = new JsonResult();
		List<CgqVo> pageInfo = cgqService.paging(page,limit);//查询出传感器监控所有数据
		Integer totalNum = cgqService.pageCount();//查询出所有数据总量
		jsonResult.setCode("0");
		jsonResult.setData(pageInfo);
		jsonResult.setResult(true);
		jsonResult.setCount(Long.valueOf(ObjectUtil.notNull(totalNum)?totalNum:0));
		jsonResult.setMsg("成功");
		return jsonResult;
	}
	/**
	 * 搜索
	 * @return
	 */
	@RequestMapping(value = "/getCgqTypeList" ,method = RequestMethod.POST)
	@ResponseBody
	public JsonResult getCgqTypeList (String f_type, Integer page, Integer limit) {
		log.info("# CgqController #分页查询，关键字查询");
		JsonResult jsonResult = new JsonResult();
		List<CgqVo> pageInfo = cgqService.getCgqTypeList(f_type,page,limit);
		Integer totalNum = cgqService.pageCount();//查询出所有数据总量
		jsonResult.setCode("0");
		jsonResult.setData(pageInfo);
		jsonResult.setResult(true);
		jsonResult.setCount(Long.valueOf(ObjectUtil.notNull(totalNum)?totalNum:0));
		jsonResult.setMsg("成功");
		return jsonResult;
	}

}













