package com.efounder.JEnterprise.controller.basedatamanage.energyinformation;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESBranchConf;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESSubitemConf;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESSubitem_Branch_Rlgl;
import com.efounder.JEnterprise.service.basedatamanage.energyinformation.BESSubitemConfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 分项配置
 * @author LvSihan
 *
 */
@Controller
@RequestMapping(value = "/view/basedatamanage/energyinformation")
public class BESSubitemConfController {
	private static final Logger log = LoggerFactory.getLogger(BESSubitemConfController.class);
	@Autowired
	private BESSubitemConfService besSubitemConfService;
	
	/**
	 * 初始化主界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getSubitemConf", method = RequestMethod.GET)
	public String getSubitemConf() {
		return "view/basedatamanage/energyinformation/subitemConf";
	}
	
	/**
	 * 查询分项配置数据
	 * @param besSubitemConf
	 * @return
	 */
	@RequestMapping(value = "/getSubitemConfList", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getSubitemConfList(BESSubitemConf besSubitemConf) {
		log.info("BESSubitemConfController获取分项数据配置数据");
		//统一返回格式
		ISSPReturnObject returnObject = besSubitemConfService.getSubitemConfList(besSubitemConf);	
		return returnObject;
	}
	
	/**
	 * 生成分项配置树
	 * @return
	 */
	@RequestMapping(value = "/subitem_tree", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject subitem_tree (String fZzjgbh) {
		log.info("BESSubitemConfController生成分项配置树");
		//统一返回格式
		ISSPReturnObject returnObject = besSubitemConfService.subitem_tree(fZzjgbh);	
		return returnObject;
	}
	
	/**
	 * add by liuzhen 20181018 根据能源类型生成分项配置 
	 * @return
	 */
	@RequestMapping(value = "/subitem_treegrid", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject subitem_treegrid (String fNybh) {
		log.info("BESSubitemConfController根据能源类型生成分项配置树");
		//统一返回格式
		ISSPReturnObject returnObject = besSubitemConfService.subitem_treegrid(fNybh);	
		return returnObject;
	}

	/**
	 * add by liuzhen 20181018 根据能源类型和园区编号生成分项配置
	 * @return
	 */
	@RequestMapping(value = "/subitemConftree", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject subitemConftree (String fNybh, String fYqbh,String fBudingId) {
		log.info("BESSubitemConfController根据能源类型和园区编号生成分项配置树");
		//统一返回格式
		ISSPReturnObject returnObject = besSubitemConfService.subitemConfTree(fNybh,fYqbh,fBudingId);
		return returnObject;
	}
	
	
	/**
	 * 查找子类
	 * @param fFxbh
	 * @return
	 */
	@RequestMapping(value = "/subitem_chlildtreegrid", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject subitem_chlildtreegrid (String fFxbh) {
		log.info("BESSubitemConfController查找分项配置树子类");
		//统一返回格式
		ISSPReturnObject returnObject = besSubitemConfService.subitem_chlildtreegrid(fFxbh);	
		return returnObject;
	}
	
	
	
	/**
	 * 添加分项
	 * @param besSubitemConf
	 * @return
	 */
	@RequestMapping(value = "/add_subitem", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject add_subitem (BESSubitemConf besSubitemConf) {
		log.info("BESSubitemConfController新增分项配置");
		ISSPReturnObject returnObject = besSubitemConfService.add_subitem(besSubitemConf);	
		return returnObject;
	}
	
	/**
	 * 删除分项
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/del_subitem", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject del_subitem (String fGuid) throws Exception {
		log.info("BESSubitemConfController删除分项配置");
		ISSPReturnObject returnObject = besSubitemConfService.del_subitem(fGuid);	
		return returnObject;
	}
	
	/**
	 * 查询需要编辑的分项配置信息
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/querySubitem", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject querySubitem (String fGuid) {
		log.info("BESSubitemConfController查询需要编辑的分项配置");
		ISSPReturnObject returnObject = besSubitemConfService.querySubitem(fGuid);	
		return returnObject;
	}
	
	/**
	 * 编辑分项
	 * @param besSubitemConf
	 * @return
	 */
	@RequestMapping(value = "/edit_subitem", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject edit_subitem (BESSubitemConf besSubitemConf) {
		log.info("BESSubitemConfController编辑分项配置");
		ISSPReturnObject returnObject = besSubitemConfService.edit_subitem(besSubitemConf);	
		return returnObject;
	}

	/**
	 *
	 * @Description: 显示是否级联-分项
	 *
	 * @auther: wanghongjie
	 * @date: 9:53 2020/11/21
	 * @param: [besSubitemConf]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/loadShowCascade_subitem", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject loadShowCascade_subitem (BESSubitemConf besSubitemConf) {
		log.info("显示是否级联-分项");
		ISSPReturnObject returnObject = besSubitemConfService.loadShowCascade_subitem(besSubitemConf);
		return returnObject;
	}

	/**
	 *
	 * @Description: 修改是否级联-分项
	 *
	 * @auther: wanghongjie
	 * @date: 9:53 2020/11/21
	 * @param: [besSubitemConf]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/changef_jl_subitem", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject changef_jl_subitem (BESSubitemConf besSubitemConf) {
		log.info("修改是否级联-分项");
		ISSPReturnObject returnObject = besSubitemConfService.changef_jl_subitem(besSubitemConf);
		return returnObject;
	}
	/**
	 * 查询分项未包含的支路信息
	 * @param besSBR
	 * @param keywords
	 * @return
	 */
	@RequestMapping(value = "/loadNoIncludeBrc", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject loadNoIncludeBrc (BESSubitem_Branch_Rlgl besSBR,BESBranchConf besBranchConf) {
		log.info("BESSubitemConfController查询分项未包含的支路信息");
		ISSPReturnObject returnObject = besSubitemConfService.loadNoIncludeBrc(besSBR,besBranchConf);	
		return returnObject;
	}
	
	/**
	 * 查询分项包含的支路信息
	 * @param besSBR
	 * @param keywords
	 * @return
	 */
	@RequestMapping(value = "/loadIncludeBrc", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject loadIncludeBrc (BESSubitem_Branch_Rlgl besSBR,BESBranchConf besBranchConf) {
		log.info("BESSubitemConfController查询分项包含的支路信息");
		ISSPReturnObject returnObject = besSubitemConfService.loadIncludeBrc(besSBR,besBranchConf);	
		return returnObject;
	}
	
	/**
	 * 分项中添加支路
	 * @param besSBR
	 * @return
	 */
	@RequestMapping(value = "/subitemconf_insertBranch", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject subitemconf_insertBranch (BESSubitem_Branch_Rlgl besSBR) {
		log.info("BESSubitemConfController添加分项配置");
		ISSPReturnObject returnObject = besSubitemConfService.subitemconf_insertBranch(besSBR);	
		return returnObject;
	}
	
	/**
	 * 分项中删除支路
	 * @param besSBR
	 * @return
	 */
	@RequestMapping(value = "/subitemconf_deleteBranch", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject subitemconf_deleteBranch (BESSubitem_Branch_Rlgl besSBR) {
		log.info("BESSubitemConfController删除分项配置");
		ISSPReturnObject returnObject = besSubitemConfService.subitemconf_deleteBranch(besSBR);	
		return returnObject;
	}
	
	/**
	 * 添加全部支路
	 * @param besSBR
	 * @return
	 */
	@RequestMapping(value = "/subitemconf_rightmoveAll", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject subitemconf_rightmoveAll (BESSubitem_Branch_Rlgl besSBR,BESBranchConf besBranchConf) {
		log.info("BESSubitemConfController分项添加全部支路配置");
		ISSPReturnObject returnObject = besSubitemConfService.subitemconf_rightmoveAll(besSBR,besBranchConf);	
		return returnObject;
	}
	
	/**
	 * 移除全部支路
	 * @param besSBR
	 * @return
	 */
	@RequestMapping(value = "/subitemconf_leftmoveAll", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject subitemconf_leftmoveAll (BESSubitem_Branch_Rlgl besSBR,BESBranchConf besBranchConf) {
		log.info("BESSubitemConfController分项移除全部支路配置");
		ISSPReturnObject returnObject = besSubitemConfService.subitemconf_leftmoveAll(besSBR,besBranchConf);
		return returnObject;
	}

	/**
	 *
	 * @Description: 一键新增分项配置
	 *
	 * @auther: wanghongjie
	 * @date: 17:34 2021/5/25
	 * @param: [persons]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/addsubitem_OneClickAdd", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject addsubitem_OneClickAdd (String persons,String fNybh,String fYqbh,String buildingbh) {
		log.info("BESSubitemConfController一键新增分项配置");
//		Gson gson = new Gson();
//		List<BESSubitemConf> list = gson.fromJson(persons, new TypeToken<List<BESSubitemConf>>() {}.getType());
//		ISSPReturnObject returnObject = besSubitemConfService.addsubitem_OneClickAdd(list,fNybh,fYqbh,buildingbh);
		ISSPReturnObject returnObject = besSubitemConfService.addsubitem_OneClickAdd(fNybh,fYqbh,buildingbh);
//		ISSPReturnObject returnObject = new ISSPReturnObject();
		return returnObject;
	}
	
}
