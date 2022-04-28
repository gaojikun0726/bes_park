package com.efounder.JEnterprise.controller.basedatamanage.enegrycollectionmanage;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESCollMethod;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESElectric_Coll_Rlgl;
import com.efounder.JEnterprise.service.basedatamanage.enegrycollectionmanage.BESCollMethodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 采集方案定义
 * @author LvSihan
 *
 */
@Controller
@RequestMapping(value = "/view/basedatamanage/enegrycollectionmanage")
public class BESCollMethodController {
	private static final Logger log = LoggerFactory.getLogger(BESCollMethodController.class);
	@Autowired
	private BESCollMethodService besCollMethodService;
	
	/**
	 * 初始化主页面
	 * @return
	 */
	@RequestMapping(value = "/getCollMethod", method = RequestMethod.GET)
	public String getCollMethod () {
		log.info("# BESCollMethodController #初始化‘采集方案’界面");
		return "view/basedatamanage/enegrycollectionmanage/collMethod";
	}
	/**
	 * 查询采集方案
	 * @param keywords
	 * @return
	 */
	@RequestMapping(value = "/getCollMethodList", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getCollMethodList (String keywords) {
		log.info("# BESCollMethodController 获取采集方案");
		ISSPReturnObject returnObject = besCollMethodService.getCollMethodList(keywords);	
		return returnObject;
	}
	/**
	 * 查询组织机构下的采集方案
	 * @param fZzjgbh
	 * @return
	 */
	@RequestMapping(value = "/getCMbyZzjgbh", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getCMbyZzjgbh(String fyqbh,String fnybh) {
		log.info("# BESCollMethodController 根据组织机构获取采集方案");
		ISSPReturnObject returnObject = besCollMethodService.getCMbyZzjgbh(fyqbh,fnybh);
		return returnObject;
	}
	/**
	 * 通过方案编号获取
	 * @param fCjfabh
	 * @return
	 */
	@RequestMapping(value = "/queryCollM", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject queryCollM(String fCjfabh) {
		log.info("# BESCollMethodController 根据采集方案编号获取采集方案");
		ISSPReturnObject returnObject = besCollMethodService.getCMbyCjfabh(fCjfabh);	
		return returnObject;
	}
	/**
	 * 新增采集方案
	 * @param besCollMethod
	 * @return
	 */
	@RequestMapping(value = "/add_CollM", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject add_CollM(BESCollMethod besCollMethod) {
		log.info("# BESCollMethodController 新增采集方案");
		ISSPReturnObject returnObject = besCollMethodService.add_CollM(besCollMethod);	
		return returnObject;
	}
	
	/**
	 * 编辑采集方案
	 * @param besCollMethod
	 * @return
	 */
	@RequestMapping(value = "/edit_CollM", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject edit_CollM(BESCollMethod besCollMethod) {
		log.info("# BESCollMethodController 编辑采集方案");
		ISSPReturnObject returnObject = besCollMethodService.edit_CollM(besCollMethod);	
		return returnObject;
	}
	
	/**
	 * 删除采集方案
	 * @param besCollMethod
	 * @return
	 */
	@RequestMapping(value = "/del_CollM", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject del_CollM(BESCollMethod besCollMethod) {
		log.info("# BESCollMethodController 删除采集方案");
		ISSPReturnObject returnObject = besCollMethodService.del_CollM(besCollMethod);	
		return returnObject;
	}
	/**
	 * 查询未包含的能耗参数
	 * @param besCollMethod
	 * @return
	 */
	@RequestMapping(value = "/loadNoIncludeCollM", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject loadNoIncludeCollM(BESElectric_Coll_Rlgl besECR,String keywords) {
		log.info("# BESCollMethodController 查询采集方案");
		ISSPReturnObject returnObject = besCollMethodService.loadNoIncludeCollM(besECR,keywords);	
		return returnObject;
	}
	/**
	 * 查询已包含的能耗参数
	 * @param besCollMethod
	 * @return
	 */
	@RequestMapping(value = "/loadIncludeCollM", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject loadIncludeCollM(BESElectric_Coll_Rlgl besECR,String keywords) {
		log.info("# BESCollMethodController 查询已包含的能耗参数");
		ISSPReturnObject returnObject = besCollMethodService.loadIncludeCollM(besECR,keywords);	
		return returnObject;
	}
	/**
	 * 添加一条能耗参数
	 * @param besCollMethod
	 * @return
	 */
	@RequestMapping(value = "/CollMethod_insertElectricP", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject CollMethod_insertElectricP(BESElectric_Coll_Rlgl besECR) {
		log.info("# BESCollMethodController 添加一条能耗参数");
		ISSPReturnObject returnObject = besCollMethodService.CollMethod_insertElectricP(besECR);	
		return returnObject;
	}
	/**
	 * 删除一条能耗参数
	 * @param besCollMethod
	 * @return
	 */
	@RequestMapping(value = "/CollMethod_deleteElectricP", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject CollMethod_deleteElectricP(BESElectric_Coll_Rlgl besECR) {
		log.info("# BESCollMethodController 删除一条能耗参数");
		ISSPReturnObject returnObject = besCollMethodService.CollMethod_deleteElectricP(besECR);	
		return returnObject;
	}
	/**
	 * 移除全部能耗参数
	 * @param besCollMethod
	 * @return
	 */
	@RequestMapping(value = "/CollMethod_leftmoveAll", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject CollMethod_leftmoveAll(BESElectric_Coll_Rlgl besECR) {
		log.info("# BESCollMethodController 移除全部能耗参数");
		ISSPReturnObject returnObject = besCollMethodService.CollMethod_leftmoveAll(besECR);	
		return returnObject;
	}
	/**
	 * 添加全部能耗参数
	 * @param besCollMethod
	 * @return
	 */
	@RequestMapping(value = "/CollMethod_rightmoveAll", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject CollMethod_rightmoveAll(BESElectric_Coll_Rlgl besECR) {
		log.info("# BESCollMethodController 添加全部能耗参数");
		ISSPReturnObject returnObject = besCollMethodService.CollMethod_rightmoveAll(besECR);	
		return returnObject;
	}
	/**
	 * 更新采集方案包含采集参数中 是否作为统计参数的值
	 * @param besECR
	 * @return
	 */
	@RequestMapping(value = "/update_inclu_fStatisticalParam", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject update_inclu_fStatisticalParam(BESElectric_Coll_Rlgl besECR) {
		log.info("# BESCollMethodController 更新统计参数的值");
		ISSPReturnObject returnObject = besCollMethodService.update_inclu_fStatisticalParam(besECR);	
		return returnObject;
	}
	/**
	 * 更新采集方案包含采集参数中 是否变比
	 * @param besECR
	 * @return
	 */
	@RequestMapping(value = "/update_inclu_fIsRate", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject update_inclu_fIsRate(BESElectric_Coll_Rlgl besECR) {
		log.info("# BESCollMethodController 更新统计参数的值");
		ISSPReturnObject returnObject = besCollMethodService.update_inclu_fIsRate(besECR);
		return returnObject;
	}

	/**
	 *  获取园区能源类型树
	 * @param fyqbh
	 * @return
	 */
	@RequestMapping(value = "/getParkEnergyTree", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getParkEnergyTree(String fyqbh) {
		log.info("# BESCollMethodController 获取园区能源类型树");
		ISSPReturnObject returnObject = besCollMethodService.getParkEnergytree(fyqbh);
		return returnObject;
	}

}
