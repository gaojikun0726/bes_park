package com.efounder.JEnterprise.controller.basedatamanage.enegrycollectionmanage;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESElectricParams;
import com.efounder.JEnterprise.service.basedatamanage.enegrycollectionmanage.BESElectricParamsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 采集参数定义
 * @author LvSihan
 *
 */
@Controller
@RequestMapping(value = "/view/basedatamanage/enegrycollectionmanage")
public class BESElectricParamsController {
	private static final Logger log = LoggerFactory.getLogger(BESElectricParamsController.class);
	@Autowired
	private BESElectricParamsService besElectricParamsService;
	
	/**
	 * 初始化主页面
	 * @return
	 */
	@RequestMapping(value = "/getElectricParams" , method = RequestMethod.GET)
	public String getElectricParams() {
		log.info("# BESElectricParamsController #初始化‘采集参数定义’界面");
		return "view/basedatamanage/enegrycollectionmanage/electricParams";	
	}
	
	/**
	 * 获取采集参数数据
	 * @param besElectricParams
	 * @return
	 */
	@RequestMapping(value = "/getElectricPList" , method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getElectricPList(String keywords) {
		log.info("# BESElectricParamsController 查询采集参数信息" );
		ISSPReturnObject returnObject = besElectricParamsService.getElectricPList(keywords);	
		return returnObject;
	}
	
	/**
	 * 获取能源类型树
	 * @return
	 */
	@RequestMapping(value = "/ElectricP_tree" , method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject ElectricP_tree() {
		log.info("# BESElectricParamsController 生成能耗类型树" );
		ISSPReturnObject returnObject = besElectricParamsService.ElectricP_tree();	
		return returnObject;
	}
	
	/**
	 * 获取子类
	 * @return
	 */
	@RequestMapping(value = "/ElectricP_chlildtreegrid" , method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject ElectricP_chlildtreegrid(String fNybh) {
		log.info("# BESElectricParamsController 查找子类" );
		ISSPReturnObject returnObject = besElectricParamsService.ElectricP_chlildtreegrid(fNybh);	
		return returnObject;
	}
	
	/**
	 * 新增
	 * @param besElectricParams
	 * @return
	 */
	@RequestMapping(value = "/add_ElectricP" , method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject add_ElectricP(BESElectricParams besElectricParams) {
		log.info("# BESElectricParamsController 新增采集参数" );
		ISSPReturnObject returnObject = besElectricParamsService.add_ElectricP(besElectricParams);	
		return returnObject;
	}
	
	/**
	 * 删除采集参数
	 * @param fDnbh
	 * @return
	 */
	@RequestMapping(value = "/del_ElectricP" , method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject del_ElectricP(String fDnbh) {
		log.info("# BESElectricParamsController 删除采集参数" );
		ISSPReturnObject returnObject = besElectricParamsService.del_ElectricP(fDnbh);	
		return returnObject;
	}
	/**
	 * fDnbh获取采集参数
	 * @param fDnbh
	 * @return
	 */
	@RequestMapping(value = "/getElectricP" , method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getElectricP(String fDnbh) {
		log.info("# BESElectricParamsController 通过能耗编号获取采集参数" );
		ISSPReturnObject returnObject = besElectricParamsService.getElectricP(fDnbh);	
		return returnObject;
	}
	
	/**
	 * 编辑采集参数
	 * @param besElectricParams
	 * @return
	 */
	@RequestMapping(value = "/edit_ElectricP" , method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject edit_ElectricP(BESElectricParams besElectricParams) {
		log.info("# BESElectricParamsController 编辑采集参数" );
		ISSPReturnObject returnObject = besElectricParamsService.edit_ElectricP(besElectricParams);	
		return returnObject;
	}

	/**
	 * 获取编码规则
	 * @return
	 */
	@RequestMapping(value = "/getfbmgz" , method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getfbmgz() {
		log.info("# BESElectricParamsController 获取编码规则" );
		ISSPReturnObject returnObject = besElectricParamsService.getfbmgz();
		return returnObject;
	}
	

}
