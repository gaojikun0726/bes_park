package com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.common;

import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.*;
import com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.IntegrationCommonConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
/**
 * 类名称：IntegrationCommonConfigController
 *  类描述：强弱电一体化通用配置
 *   创建人：Yangjx
 *   创建时间：2019/12/16
 *   修改人：
 * 
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/view/strongAndWeakElectricityIntegration/integrationCommonConfig")
public class IntegrationCommonConfigController {
	private static final Logger log = LoggerFactory.getLogger(IntegrationCommonConfigController.class);
	
	@Autowired
	private IntegrationCommonConfigService integrationCommonConfigService;
   
    @RequestMapping(value="/loadDDCOption",method =RequestMethod.GET)
	@ResponseBody
	public JsonResult loadDDCOption() {
		log.info("#IntegrationCommonConfigController 加载DDC信息");
		JsonResult result = new JsonResult();
		List<CommonPointLocationConfigVo> infos = new ArrayList<CommonPointLocationConfigVo>();
		infos = integrationCommonConfigService.queryDDCInfos();
		result.setCode("0");
		result.setData(infos);
		result.setResult(true);
		result.setCount(Long.valueOf(infos.size()));
		result.setMsg("返回成功");
		return result;
	}
   
    @RequestMapping(value="/loadPointLocationOption",method =RequestMethod.GET)
    @ResponseBody
    public JsonResult loadPointLocationOption(String f_sys_name) {
    	log.info("#IntegrationCommonConfigController 加载ddc所属点位信息");
    	JsonResult result = new JsonResult();
    	List<CommonPointLocationConfigVo> infos = new ArrayList<CommonPointLocationConfigVo>();
    	infos = integrationCommonConfigService.queryPointLocationInfos(f_sys_name);
    	result.setCode("0");
    	result.setData(infos);
    	result.setResult(true);
    	result.setCount(Long.valueOf(infos.size()));
    	result.setMsg("返回成功");
    	return result;
    }
   
    /**
	 * 添加强弱电一体化通用页面显示div最大数目信息
	 * @param integrationPageDivNumConfigVo
	 * @return
	 */
	@RequestMapping(value = "/confirmIntegrationDivNumConfig", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult confirmIntegrationDivNumConfig(@RequestBody IntegrationPageDivNumConfigVo integrationPageDivNumConfigVo) {
		log.info("#IntegrationCommonConfigController 添加配置通用页面显示DIV最大数目信息");
		JsonResult inserResult = integrationCommonConfigService.confirmIntegrationDivNumConfig(integrationPageDivNumConfigVo);
		return inserResult;
	}

	/**
	 * 添加强弱电一体化通用页面显示div最大数目信息左侧的div数量
	 *wanghongjie
	 * @param integrationPageDivNumConfigVo
	 * @return
	 */
	@RequestMapping(value = "/confirmIntegrationDivNumConfigByLeft", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult confirmIntegrationDivNumConfigByLeft(@RequestBody IntegrationPageDivNumConfigVo integrationPageDivNumConfigVo) {
		log.info("#IntegrationCommonConfigController 添加配置通用页面显示DIV最大数目信息");
		JsonResult inserResult = integrationCommonConfigService.confirmIntegrationDivNumConfigByLeft(integrationPageDivNumConfigVo);
		return inserResult;
	}

	/**
	 * 更新通用页面显示div最大数目信息
	 * @param integrationPageDivNumConfigVo
	 * @return
	 */
	@RequestMapping(value = "/updateIntegrationDivNumConfig", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult updateIntegrationDivNumConfig(@RequestBody IntegrationPageDivNumConfigVo integrationPageDivNumConfigVo) {
		log.info("#IntegrationCommonConfigController 更新通用页面显示DIV最大数目信息");
		JsonResult inserResult = integrationCommonConfigService.updateIntegrationDivNumConfig(integrationPageDivNumConfigVo);
		return inserResult;
	}

	/*/*
	 *
	 * @Description: 更新通用页面显示div最大数目信息  左侧的div数量
	 *
	 * @auther: wanghongjie
	 * @date: 16:50 2020/2/26
	 * @param: [integrationPageDivNumConfigVo]
	 * @return: com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.common.JsonResult
	 *
	 */
	@RequestMapping(value = "/updateIntegrationDivNumConfigByLeft", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult updateIntegrationDivNumConfigByLeft(@RequestBody IntegrationPageDivNumConfigVo integrationPageDivNumConfigVo) {
		log.info("#IntegrationCommonConfigController 更新通用页面显示DIV最大数目信息");
		JsonResult inserResult = integrationCommonConfigService.updateIntegrationDivNumConfigByLeft(integrationPageDivNumConfigVo);
		return inserResult;
	}
	
	/**
	 * 查看页面配置div展示数目信息
	 * @param f_equipment_id
	 * @return
	 */
	@RequestMapping(value="/checkDivNumDataInfo",method =RequestMethod.GET)
	@ResponseBody
	public JsonResult checkDivNumDataInfo(String f_equipment_id,String f_type_id) {
		log.info("#IntegrationCommonConfigController 检验页面配置DIV展示数目信息");
		return integrationCommonConfigService.checkDivNumDataInfo(f_equipment_id,f_type_id);
	}

	/**
	 * 查看页面配置div展示数目信息左侧的div配置
	 *wanghongjie
	 * @param f_equipment_id
	 * @return
	 */
	@RequestMapping(value="/checkDivNumDataInfoByLeft",method =RequestMethod.GET)
	@ResponseBody
	public JsonResult checkDivNumDataInfoByLeft(String f_equipment_id,String f_type_id) {
		log.info("#IntegrationCommonConfigController 检验页面配置DIV展示数目信息");
		return integrationCommonConfigService.checkDivNumDataInfoByLeft(f_equipment_id,f_type_id);
	}
	
	/**
	 * 更新通用页面显示div信息
	 * @param integrationPageCommonConfigVo
	 * @return
	 */
	@RequestMapping(value = "/updateIntegrationPageCommonConfig", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult updateIntegrationPageCommonConfig(@RequestBody IntegrationPageCommonConfigVo integrationPageCommonConfigVo) {
		log.info("#IntegrationCommonConfigController 更新通用页面显示DIV信息");
		JsonResult inserResult = integrationCommonConfigService.updateIntegrationPageCommonConfig(integrationPageCommonConfigVo);
		return inserResult;
	}


	/**
	 *
	 * @Description: 更新通用页面显示div信息  top
	 *
	 * @auther: wanghongjie
	 * @date: 10:50 2020/2/27
	 * @param: [integrationPageCommonConfigVo]
	 * @return: com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.common.JsonResult
	 *
	 */
	@RequestMapping(value = "/updateIntegrationPageCommonConfigtop", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult updateIntegrationPageCommonConfigtop(@RequestBody IntegrationPageCommonConfigtop integrationPageCommonConfigtop) {
		log.info("#IntegrationCommonConfigController 更新通用页面显示DIV信息");
		JsonResult inserResult = integrationCommonConfigService.updateIntegrationPageCommonConfigtop(integrationPageCommonConfigtop);
		return inserResult;
	}

	/**
	 * 查看页面配置div展示信息
	 * wanghongjie 修改,,增加f_type_id的判断
	 * @param f_equipment_id
	 * @param f_seq
	 * @return
	 */
	@RequestMapping(value="/checkPageCommonConfig",method =RequestMethod.GET)
	@ResponseBody
	public JsonResult checkPageCommonConfig(String f_equipment_id,String f_seq,String f_type_id) {
		log.info("#IntegrationCommonConfigController 检验页面配置DIV展示信息");
		return integrationCommonConfigService.checkPageCommonConfig(f_equipment_id,f_seq,f_type_id);
	}
	/*
	 *
	 * @Description: 配置点位的时候,做判断,如果数据库中有重复的点位名称,则不进行添加
	 *
	 * @auther: wanghongjie
	 * @date: 18:41 2020/3/10
	 * @param: [f_equipment_id, f_seq, f_sys_name, f_type_id]
	 * @return: com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.common.JsonResult
	 *
	 */
	@RequestMapping(value="/checkPageCommonConfigRepetition",method =RequestMethod.GET)
	@ResponseBody
	public JsonResult checkPageCommonConfigRepetition(String f_equipment_id,String f_seq,String f_sys_name,String f_type_id,String f_desc,String f_formula) {
		log.info("#IntegrationCommonConfigController 检验页面配置DIV展示信息");
		return integrationCommonConfigService.checkPageCommonConfigRepetition(f_equipment_id,f_seq,f_sys_name,f_type_id,f_desc,f_formula);
	}
	/**
	 *
	 * @Description: 查看页面配置div展示信息 top
	 *
	 * @auther: wanghongjie
	 * @date: 10:18 2020/2/27
	 * @param: [f_equipment_id, f_seq]
	 * @return: com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.common.JsonResult
	 *
	 */
	@RequestMapping(value="/checkPageCommonConfigtop",method =RequestMethod.GET)
	@ResponseBody
	public JsonResult checkPageCommonConfigtop(String f_equipment_id,String f_seq,String f_type_id) {
		log.info("#IntegrationCommonConfigController 检验页面配置DIV展示信息");
		return integrationCommonConfigService.checkPageCommonConfigtop(f_equipment_id,f_seq,f_type_id);
	}

	/**
	 * 添加页面配置展示点位信息
	 * @param integrationPageCommonConfigVo
	 * @return
	 */
	@RequestMapping(value = "/insertPageCommonConfig", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult insertPageCommonConfig(@RequestBody IntegrationPageCommonConfigVo integrationPageCommonConfigVo) {
		log.info("#IntegrationCommonConfigController 添加页面配置设备展示点位信息");
		JsonResult inserResult = integrationCommonConfigService.insertPageCommonConfig(integrationPageCommonConfigVo);
		return inserResult;
	}

	/**
	 *
	 * @Description: 添加页面配置展示点位信息 top
	 *
	 * @auther: wanghongjie
	 * @date: 11:21 2020/2/27
	 * @param: [integrationPageCommonConfigVo]
	 * @return: com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.common.JsonResult
	 *
	 */
	@RequestMapping(value = "/insertPageCommonConfigtop", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult insertPageCommonConfigtop(@RequestBody IntegrationPageCommonConfigtop integrationPageCommonConfigtop) {
		log.info("#IntegrationCommonConfigController 添加页面配置设备展示点位信息");
		JsonResult inserResult = integrationCommonConfigService.insertPageCommonConfigtop(integrationPageCommonConfigtop);
		return inserResult;
	}
	
	/**
	 * 查找页面所配置展示点位信息
	 * @param f_equipment_id
	 * @return
	 */
    @RequestMapping(value="/searchPageConfigData",method =RequestMethod.GET)
    @ResponseBody
    public JsonResult searchPageConfigData(String f_equipment_id,String f_type_id) {
    	log.info("#IntegrationCommonConfigController 查找页面配置所展示点位信息");
    	JsonResult result = new JsonResult();
    	List<IntegrationPageCommonConfigVo> infos = new ArrayList<IntegrationPageCommonConfigVo>();
    	infos = integrationCommonConfigService.searchPageConfigData(f_equipment_id,f_type_id);
    	result.setCode("0");
    	result.setData(infos);
    	result.setResult(true);
    	result.setCount(Long.valueOf(infos.size()));
    	result.setMsg("返回成功");
    	return result;
    }
	
    /**
     *
     * @Description: 查找页面所配置展示点位信息 top
     * 
     * @auther: wanghongjie
     * @date: 16:10 2020/2/27 
     * @param: [f_equipment_id, f_type_id]
     * @return: com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.common.JsonResult
     *
     */
	@RequestMapping(value="/searchPageConfigDatatop",method =RequestMethod.GET)
	@ResponseBody
	public JsonResult searchPageConfigDatatop(String f_equipment_id,String f_type_id) {
		log.info("#IntegrationCommonConfigController 查找页面配置所展示点位信息");
		JsonResult result = new JsonResult();
		List<IntegrationPageCommonConfigtop> infos = new ArrayList<IntegrationPageCommonConfigtop>();
		infos = integrationCommonConfigService.searchPageConfigDatatop(f_equipment_id,f_type_id);
		result.setCode("0");
		result.setData(infos);
		result.setResult(true);
		result.setCount(Long.valueOf(infos.size()));
		result.setMsg("返回成功");
		return result;
	}
    
    /**
     * 查找页面所配置展示点位信息
     * @param f_equipment_id
     * @return
     */
    @RequestMapping(value="/searchIntegrationPageDivConfigNum",method =RequestMethod.GET)
    @ResponseBody
    public JsonResult searchIntegrationPageDivConfigNum(String f_equipment_id,String f_type_id) {
    	log.info("#IntegrationCommonConfigController 查找页面配置所需展示div数目");
    	JsonResult result = new JsonResult();
    	int infos = integrationCommonConfigService.searchIntegrationPageDivConfigNum(f_equipment_id,f_type_id);
    	result.setCode("0");
    	result.setData(infos);
    	result.setResult(true);
    	result.setMsg("查找成功");
    	return result;
    }
	/*/*
	 *
	 * @Description: 查找页面所配置展示点位信息  左侧的div数量
	 *
	 * @auther: wanghongjie
	 * @date: 17:31 2020/2/26
	 * @param: [f_equipment_id]
	 * @return: com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.common.JsonResult
	 *
	 */
	@RequestMapping(value="/searchIntegrationPageDivConfigNumByLeft",method =RequestMethod.GET)
	@ResponseBody
	public JsonResult searchIntegrationPageDivConfigNumByLeft(String f_equipment_id,String f_type_id) {
		log.info("#IntegrationCommonConfigController 查找页面配置所需展示div数目");
		JsonResult result = new JsonResult();
		int infos = integrationCommonConfigService.searchIntegrationPageDivConfigNumByLeft(f_equipment_id,f_type_id);
		result.setCode("0");
		result.setData(infos);
		result.setResult(true);
		result.setMsg("查找成功");
		return result;
	}

	@RequestMapping(value = "/loadInitValAndEngineerUnit", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult loadInitValAndEngineerUnit(@RequestBody String f_sysName_list) {
		log.info("#IntegrationCommonConfigController 查询加载页面配置设备展示点位数据及单位信息");
		JsonResult result = new JsonResult();
		List<IntegrationInitValAndEngineerUnitVo> infos = new ArrayList<IntegrationInitValAndEngineerUnitVo>();
		System.out.println(f_sysName_list);
		//f_sysName_list = f_sysName_list.replace("{\"f_sysName_list\":[", "(").replace("]}", ")");
		f_sysName_list = f_sysName_list.replace("{\"f_sysName_list\":[", "(").replace("]}", ")");
		//f_sysName_list = f_sysName_list.replace("[","(").replace("]", ")");
		System.out.println(f_sysName_list);
		//List<Map<String, String>> listString = 
		infos = integrationCommonConfigService.loadInitValAndEngineerUnit(f_sysName_list);
		result.setCode("0");
		result.setData(infos);
		result.setResult(true);
		result.setCount(Long.valueOf(infos.size()));
		result.setMsg("返回成功");
		return result;
	}
    
    /**
     * 查找页面所配置活动div数据信息
     * @param 
     * @return
     */
    @RequestMapping(value="/searchIntegrationAliveDivPageConfig",method =RequestMethod.GET)
    @ResponseBody
	public JsonResult searchIntegrationAliveDivPageConfig() {
		log.info("#IntegrationCommonConfigController 查找页面所配置活动div数据信息");
		JsonResult result = new JsonResult();
		List<AliveDivConfigVo> infos = new ArrayList<AliveDivConfigVo>();
		infos = integrationCommonConfigService.searchIntegrationAliveDivPageConfig();
		result.setCode("0");
		result.setData(infos);
		result.setResult(true);
		result.setCount(Long.valueOf(infos.size()));
		result.setMsg("返回成功");
		return result;
	}
    
    /**
	 * 添加强弱电一体化活动div通用配置信息
	 * @param aliveDivConfigVo
	 * @return
	 */
	@RequestMapping(value = "/insertAliveDivCommonConfig", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult insertAliveDivCommonConfig(@RequestBody AliveDivConfigVo aliveDivConfigVo) {
		log.info("#IntegrationCommonConfigController 添加强弱电一体化活动DIV通用配置信息");
		JsonResult inserResult = integrationCommonConfigService.insertAliveDivCommonConfig(aliveDivConfigVo);
		return inserResult;
	}
	
	/**
	 * 查看验证是否有无配置活动div信息
	 * @param f_div_seq
	 * @return
	 */
	@RequestMapping(value="/checkConfigDivSequenceInfo",method =RequestMethod.GET)
	@ResponseBody
	public JsonResult checkConfigDivSequenceInfo(String f_div_seq) {
		log.info("#IntegrationCommonConfigController 检验页面配置DIV展示信息");
		return integrationCommonConfigService.checkConfigDivSequenceInfo(f_div_seq);
	}
	
	
	/**
	 * 更新通用页面活动div配置信息
	 * @param aliveDivConfigVo
	 * @return
	 */
	@RequestMapping(value = "/updateAliveDivCommonConfig", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult updateAliveDivCommonConfig(@RequestBody AliveDivConfigVo aliveDivConfigVo) {
		log.info("#IntegrationCommonConfigController 更新通用页面活动div配置信息");
		JsonResult inserResult = integrationCommonConfigService.updateAliveDivCommonConfig(aliveDivConfigVo);
		return inserResult;
	}
	
	/**
	 * 删除配置活动div信息
	 * @param f_div_seq
	 * @return
	 */
	@RequestMapping(value="/deleteAliveCommonConfigInfo",method =RequestMethod.GET)
	@ResponseBody
	public JsonResult deleteAliveCommonConfigInfo(String f_div_seq) {
		log.info("#IntegrationCommonConfigController 删除页面配置DIV展示信息");
		return integrationCommonConfigService.deleteAliveCommonConfigInfo(f_div_seq);
	}
	
	 /**
     * 查找页面所配置表格展示数据信息
     * @param 
     * @return
     */
    @RequestMapping(value="/searchIntegrationTableDataConfig",method =RequestMethod.GET)
    @ResponseBody
	public JsonResult searchIntegrationTableDataConfig(String f_page_name,String f_device_id) {
		log.info("#IntegrationCommonConfigController 查找页面所配置表格展示数据信息");
		JsonResult result = new JsonResult();
		List<TableDataConfigVo> infos = new ArrayList<TableDataConfigVo>();
		infos = integrationCommonConfigService.searchIntegrationTableDataConfig(f_page_name,f_device_id);
		result.setCode("0");
		result.setData(infos);
		result.setResult(true);
		result.setCount(Long.valueOf(infos.size()));
		result.setMsg("返回成功");
		return result;
	}
    
    /**
     * 添加强弱电一体化表数据配置信息
     * @param tableDataConfigVo
     * @return
     */
   	@RequestMapping(value = "/insertTableDataCommonConfig", method = RequestMethod.POST)
   	@ResponseBody
   	public JsonResult insertTableDataCommonConfig(@RequestBody TableDataConfigVo tableDataConfigVo) {
   		log.info("#IntegrationCommonConfigController 添加强弱电一体化表数据配置信息");
   		JsonResult inserResult = integrationCommonConfigService.insertTableDataCommonConfig(tableDataConfigVo);
   		return inserResult;
   	}
	
   	/**
	 * 更新页面展示表数据配置信息
	 * @param tableDataConfigVo
	 * @return
	 */
	@RequestMapping(value = "/updateTableDataCommonConfig", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult updateTableDataCommonConfig(@RequestBody TableDataConfigVo tableDataConfigVo) {
		log.info("#IntegrationCommonConfigController 更新通用页面活动div配置信息");
		JsonResult inserResult = integrationCommonConfigService.updateTableDataCommonConfig(tableDataConfigVo);
		return inserResult;
	}
   	
	/**
	 * 删除页面展示表数据配置信息
	 * @param f_id
	 * @return
	 */
	@RequestMapping(value="/deleteTableDataCommonConfig",method =RequestMethod.GET)
	@ResponseBody
	public JsonResult deleteTableDataCommonConfig(String f_id) {
		log.info("#IntegrationCommonConfigController 删除页面展示表数据配置信息");
		return integrationCommonConfigService.deleteTableDataCommonConfig(f_id);
	}
	
	/**
	 * 查看系统能效页面配置div展示信息
	 * @param f_seq
	 * @return
	 */
	@RequestMapping(value="/checkEnergyEfficiencyConfig",method =RequestMethod.GET)
	@ResponseBody
	public JsonResult checkEnergyEfficiencyConfig(String f_seq) {
		log.info("#IntegrationCommonConfigController 检验系统能效页面配置DIV展示信息");
		return integrationCommonConfigService.checkEnergyEfficiencyConfig(f_seq);
	}
	
	
	/**
	 * 添加系统能效页面配置展示点位信息
	 * @param integrationPageCommonConfigVo
	 * @return
	 */
	@RequestMapping(value = "/insertEnergyEfficiencyConfig", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult insertEnergyEfficiencyConfig(@RequestBody IntegrationPageCommonConfigVo integrationPageCommonConfigVo) {
		log.info("#IntegrationCommonConfigController 添加页面配置设备展示点位信息");
		JsonResult inserResult = integrationCommonConfigService.insertEnergyEfficiencyConfig(integrationPageCommonConfigVo);
		return inserResult;
	}
	
	
	
	/**
	 * 更新系统能效页面显示div信息
	 * @param integrationPageCommonConfigVo
	 * @return
	 */
	@RequestMapping(value = "/updateIntegrationEnergyEfficiencyConfig", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult updateIntegrationEnergyEfficiencyConfig(@RequestBody IntegrationPageCommonConfigVo integrationPageCommonConfigVo) {
		log.info("#IntegrationCommonConfigController 更新系统能效页面显示DIV信息");
		JsonResult inserResult = integrationCommonConfigService.updateIntegrationEnergyEfficiencyConfig(integrationPageCommonConfigVo);
		return inserResult;
	}
	
	/**
	 * 查找系统能效页面配置div信息
	 * @return
	 */
	@RequestMapping(value="/searchEnergyEfficiencyPageConfigData",method =RequestMethod.GET)
    @ResponseBody
    public JsonResult searchPageConfigData() {
    	log.info("#IntegrationCommonConfigController 查找系统能效页面配置所展示div信息");
    	JsonResult result = new JsonResult();
    	List<IntegrationPageCommonConfigVo> infos = new ArrayList<IntegrationPageCommonConfigVo>();
    	infos = integrationCommonConfigService.searchEnergyEfficiencyPageConfigData();
    	result.setCode("1");
    	result.setData(infos);
    	result.setResult(true);
    	result.setCount(Long.valueOf(infos.size()));
    	result.setMsg("返回成功");
    	return result;
    }

	/**
	 * 添加页面能效评估展示点位信息
	 * @param integrationPageCommonConfigVo
	 * @return
	 */
	@RequestMapping(value = "/insertAssessment", method = RequestMethod.POST)
	@ResponseBody
    public JsonResult insertAssessment(@RequestBody IntegrationPageCommonConfigVo integrationPageCommonConfigVo){
		log.info("#IntegrationCommonConfigController 添加页面能效评估展示点位信息");
		JsonResult result = integrationCommonConfigService.insertAssessment(integrationPageCommonConfigVo);
		return result;
	}


	/**
	 * 添加页面能效评估展示点位信息
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/selectAssessment", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult selectAssessment(){
		log.info("#IntegrationCommonConfigController 添加页面能效评估展示点位信息");
		JsonResult result = new JsonResult();
		List<IntegrationPageCommonConfigVo> infos = new ArrayList<IntegrationPageCommonConfigVo>();
		infos = integrationCommonConfigService.selectAssessment();
		result.setCode("1");
		result.setData(infos);
		return result;
	}
}













