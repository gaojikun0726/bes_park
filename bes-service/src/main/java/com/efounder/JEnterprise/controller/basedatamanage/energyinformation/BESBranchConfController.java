package com.efounder.JEnterprise.controller.basedatamanage.energyinformation;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESBranchConf;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESBranch_Ammeter_Rlgl;
import com.efounder.JEnterprise.service.basedatamanage.energyinformation.BESBranchConfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 支路配置
 * @author LvSiHan
 *
 */

@Controller
@RequestMapping(value = "/view/basedatamanage/energyinformation")
public class BESBranchConfController {
	private static final Logger log = LoggerFactory.getLogger(BESBranchConfController.class);
	@Autowired
	private BESBranchConfService besBranchConfService;

	/**
	 * 初始化主界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getBranchConf", method = RequestMethod.GET)
	public String getBranchConf() {
		return "view/basedatamanage/energyinformation/branchConf";
	}

	/**
	 * 查询tabulator填充数据
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getBranchConf_treegrid", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getBranchConfList(BESBranchConf besBranchConf) {
		log.info("BESBranchConfController 通过支路名称、编号查询");
		ISSPReturnObject returnObject = besBranchConfService.getBranchConfList(besBranchConf);
		return returnObject;
	}

	/**
	 * 生成支路配置树
	 * @return
	 */
	@RequestMapping(value = "/branch_tree", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getTree(String fnybh) {
		log.info("NodeController获取信息");
		ISSPReturnObject returnObject = besBranchConfService.getTree(fnybh);
		return returnObject;
	}


	/**
	 * 查找子类
	 * @param keywords
	 * @return
	 */
	@RequestMapping(value = "/branch_chlildtreegrid", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getChildList(String fZlbh) {
		//统一返回格式
		ISSPReturnObject returnObject = besBranchConfService.findChildByZlbh(fZlbh);
		return returnObject;
	}
	
	/**
	 * 新增支路
	 * @param besBranchConf
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add_branch", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject add_branch(@RequestBody BESBranchConf besBranchConf, ModelMap model) {
		ISSPReturnObject returnObject = besBranchConfService.add_branch(besBranchConf);		
		return returnObject;		
	}
	/**
	 * 删除支路
	 * @param fZlbh
	 * @return
	 */
	@RequestMapping(value = "/del_branch", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject del_branch(@RequestParam(value = "fZlbh", required = false)String fZlbh) throws DataAccessException {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			returnObject = besBranchConfService.del_branch(fZlbh);
		} catch (DataAccessException e) {
			returnObject.setMsg("删除支路失败,当前支路已存入数据");
			returnObject.setStatus("0");
		}

		return returnObject;
	}
	
	/**
	 * 按fZlbh查询单条支路信息
	 * @param fZlbh
	 * @return
	 */
	@RequestMapping(value = "/queryBranch", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject queryBranch(@RequestParam(value = "fZlbh", required = false)String fZlbh) {
		ISSPReturnObject returnObject = besBranchConfService.queryBranch(fZlbh);		
		return returnObject;
	}
	
	/**
	 * 编辑支路信息
	 * @param besBranchConf
	 * @return
	 */
	@RequestMapping(value = "/edit_branch", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject edit_branch(@RequestBody BESBranchConf besBranchConf) {
		ISSPReturnObject returnObject = besBranchConfService.edit_branch(besBranchConf);		
		return returnObject;		
	}
	
	/**
	 * 查询未包含进支路的电表
	 * @param keywords
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/loadNoIncludeAmt", method = RequestMethod.POST)
	@ResponseBody	
	public ISSPReturnObject loadNoIncludeAmt(BESBranch_Ammeter_Rlgl besBAR,String keywords) {
		ISSPReturnObject returnObject = besBranchConfService.loadNoIncludeAmt(besBAR,keywords);		
		return returnObject;		
	}
	
	/**
	 * 查询支路包含的电表
	 * @param keywords
	 * @param besBAR
	 * @return
	 */
	@RequestMapping(value = "/loadIncludeAmt", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject loadIncludeAmt(BESBranch_Ammeter_Rlgl besBAR,String keywords) {
		ISSPReturnObject returnObject = besBranchConfService.loadIncludeAmt(besBAR,keywords);		
		return returnObject;				
	}

	/**
	 *
	 * @Description: 显示是否级联
	 *
	 * @auther: wanghongjie
	 * @date: 9:16 2020/11/18
	 * @param: [besHBR, besBranchConf]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/loadShowCascade_Branch", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject loadShowCascade (BESBranch_Ammeter_Rlgl besHBR) {
		log.info("显示是否级联");
		ISSPReturnObject returnObject = besBranchConfService.loadShowCascade(besHBR);
		return returnObject;
	}

	/**
	 *
	 * @Description: 修改是否级联
	 *
	 * @auther: wanghongjie
	 * @date: 9:49 2020/11/18
	 * @param: [fJl, fFhbh]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/changef_jl_branch", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject changef_jl (String fJl,String fZlbh) {
		log.info("修改是否级联");
		ISSPReturnObject returnObject = besBranchConfService.changef_jl(fJl,fZlbh);
		return returnObject;
	}

	/**
	 * 支路中添加电表
	 * @param besBAR
	 * @return
	 */
    @RequestMapping(value = "/branchconf_insertAmmeter", method = RequestMethod.POST)
    @ResponseBody
	public ISSPReturnObject branchconf_insertAmmeter(BESBranch_Ammeter_Rlgl besBAR) {
    	log.info("#BESBranchConfController添加支路电表信息");
		ISSPReturnObject returnObject = besBranchConfService.branchconf_insertAmmeter(besBAR);		
		return returnObject;
		
	}
    
    /**
     * 支路中删除电表信息
     * @param besBAR
     * @return
     */
    @RequestMapping(value = "/branchconf_deleteAmmeter", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject branchconf_deleteAmmeter(BESBranch_Ammeter_Rlgl besBAR) {
    	log.info("#BESBranchConfController删除支路电表信息");
		ISSPReturnObject returnObject = besBranchConfService.branchconf_deleteAmmeter(besBAR);		
		return returnObject;
    }
    
    /**
     * 支路中添加全部电表（或者搜索出来的）
     * @param besBAR
     * @return
     */
    @RequestMapping(value = "/branchconf_rightmoveAll", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject branchconf_rightmoveAll(BESBranch_Ammeter_Rlgl besBAR,String keywords) {
    	log.info("#BESBranchConfController支路中添加全部电表");
		ISSPReturnObject returnObject = besBranchConfService.branchconf_rightmoveAll(besBAR,keywords);		
		return returnObject;
    }
    /**
     * 支路中移除全部电表（或者搜索出来的）
     * @param besBAR
     * @return
     */
    @RequestMapping(value = "/branchconf_leftmoveAll", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject branchconf_leftmoveAll(BESBranch_Ammeter_Rlgl besBAR,String keywords) {
    	log.info("#BESBranchConfController支路中移除全部电表");
		ISSPReturnObject returnObject = besBranchConfService.branchconf_leftmoveAll(besBAR,keywords);		
		return returnObject;
    }
    @RequestMapping(value = "/update_inclu_fOperator", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject update_inclu_fOperator (String fZlbh,String fSysName,String fOperator) {
    	log.info("#BESBranchConfController更新支路包含电表的运算符");
		ISSPReturnObject returnObject = besBranchConfService.update_inclu_fOperator( fZlbh, fSysName, fOperator);		
		return returnObject;
    }

	/**
	 * 生成设备定义树（未格式化）
	 * @return
	 */
//	@RequestMapping(value = "/loadOrganizationTree", method = RequestMethod.POST)
//	@ResponseBody
//    public ISSPReturnObject loadOrganizationTree(String fZlbh){
//		log.info("BESSbdyController生成单独树结构");
//		ISSPReturnObject returnObject = besBranchConfService.loadOrganization(fZlbh);
//		return  returnObject;
//	}


	/**
	 *
	 * @Description: 批量整理支路树
	 *
	 * @auther: wanghongjie
	 * @date: 9:09 2022/4/23
	 * @param:
	 * @return:
	 *
	 */
	@RequestMapping(value = "/tidyTree", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject tidyTree() throws Exception {
		ISSPReturnObject returnObject = besBranchConfService.tidyTree();

		return returnObject;
	}

	/**
	 *
	 * @Description: 获取所有的电表
	 *
	 * @auther: wanghongjie
	 * @date: 9:51 2022/4/25
	 * @param:
	 * @return:
	 *
	 */
	@RequestMapping(value = "/loadAllAmmeter", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject loadAllAmmeter() {
		ISSPReturnObject returnObject = besBranchConfService.loadAllAmmeter();
		return returnObject;
	}

	/**
	 *
	 * @Description: 根据支路编号查询所有的电表
	 *
	 * @auther: wanghongjie
	 * @date: 10:15 2022/4/25
	 * @param:
	 * @return:
	 *
	 */
	@RequestMapping(value = "/loadAmmeterByBranchId", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject loadAmmeterByBranchId(BESBranch_Ammeter_Rlgl besBAR) {
		ISSPReturnObject returnObject = besBranchConfService.loadAmmeterByBranchId(besBAR);
		return returnObject;
	}

}
