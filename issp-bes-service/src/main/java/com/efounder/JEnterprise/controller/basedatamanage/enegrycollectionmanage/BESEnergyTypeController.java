package com.efounder.JEnterprise.controller.basedatamanage.enegrycollectionmanage;

import com.core.common.ISSPReturnObject;
import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESEnergyType;
import com.efounder.JEnterprise.service.basedatamanage.enegrycollectionmanage.BESEnergyTypeService;
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
/**
 * 能源类型定义
 * @author LvSiHan
 *
 */
@Controller
@RequestMapping(value = "/view/basedatamanage/enegrycollectionmanage")
public class BESEnergyTypeController {
	private static final Logger log = LoggerFactory.getLogger(BESEnergyTypeController.class);
	@Autowired
	private BESEnergyTypeService besEnergyTypeService;

	/**
	 * 初始化‘能源类型定义’界面
	 * @return
	 */
	@RequestMapping(value = "/getEnergyType",method = RequestMethod.GET)
	public String getEnergyType () {
		log.info("# BESEnergyTypeController #初始化‘能源类型定义’界面");
		return "view/basedatamanage/enegrycollectionmanage/energyType";
	}
	
	@RequestMapping(value = "/getEnergyTypeList",method = RequestMethod.POST)
	public String getEnergyTypeList (@RequestParam(value = "keywords", required = false)String keywords,
			@RequestParam(value = "pageNum", required = false)Integer pageNum,ModelMap map) {
		log.info("# BESEnergyTypeController #分页查询'能源类型'");
		PageInfo<BESEnergyType> page = besEnergyTypeService.getEnergyTypeList(keywords,pageNum);
		map.put("page", page);
		map.put("dataset", JsonMapper.toJsonString(page.getList()));
		map.put("keywords", keywords);
		return "view/basedatamanage/enegrycollectionmanage/energyType_page";
	}
	
	/**
	 * 添加能源类型信息
	 * @param besEnergyType
	 * @return
	 */
	@RequestMapping(value = "/add_EnergyType", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject add_EnergyType (BESEnergyType besEnergyType) {
		ISSPReturnObject returnObject = besEnergyTypeService.add_EnergyType(besEnergyType);	
		return returnObject;		
	}
	
	/**
	 * 删除能源类型信息
	 * @param fNybh
	 * @return
	 */
	@RequestMapping(value = "/energyType_del", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject energyType_del (String fGuid) {
		ISSPReturnObject returnObject = besEnergyTypeService.energyType_del(fGuid);	
		return returnObject;
	}
	
	/**
	 * 根据fNybh查询能源类型信息
	 * @param fNybh
	 * @return
	 */
	@RequestMapping(value = "/queryEnergyType", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject queryEnergyType(String fGuid) {
		ISSPReturnObject returnObject = besEnergyTypeService.queryEnergyType(fGuid);	
		return returnObject;
	}
	
	/**
	 * 编辑能源类型
	 * @param besEnergyType
	 * @return
	 */
	@RequestMapping(value = "/edit_EnergyType", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject edit_EnergyType(BESEnergyType besEnergyType) {
		ISSPReturnObject returnObject = besEnergyTypeService.edit_EnergyType(besEnergyType);	
		return returnObject;
	}

	/**
	 *  新增去除重复字段
	 * @param fNybh
	 * @return
	 */
	@RequestMapping(value = "/checkRepeat", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject checkRepeat(String fNybh,String fNymc) {
		log.info("# BESCollMethodController 去重能源名称与能源编号");
		ISSPReturnObject returnObject = besEnergyTypeService.checkRepeat(fNybh,fNymc);
		return returnObject;
	}

	/**
	 *  修改去除重复字段
	 * @param fNymc
	 * @return
	 */
	@RequestMapping(value = "/checkEnergyEditRepeat", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject checkEnergyEditRepeat(String fNymc,String fGuid) {
		log.info("# BESCollMethodController 去重能源名称与能源编号");
		ISSPReturnObject returnObject = besEnergyTypeService.checkEditRepeat(fNymc,fGuid);
		return returnObject;
	}

}
