package com.efounder.JEnterprise.controller.basedatamanage.energyinformation;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.util.DateUtil;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESBranchConf;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BesHouseholdBranchRlgl;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BesHouseholdConf;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.efounder.JEnterprise.service.basedatamanage.energyinformation.BESPark_EnergyTypeService;
import com.efounder.JEnterprise.service.basedatamanage.energyinformation.BesHouseholdConfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 分户controller
 * @author suhang
 * @datetime 2018-07-26
 *
 */
@Controller
@RequestMapping(value = "/view/basedatamanage/energyinformation")

public class BesHouseholdConController {
	private static final Logger log = LoggerFactory.getLogger(BesHouseholdConController.class);
	@Autowired
	private BesHouseholdConfService besHouseholdConfService;
	@Autowired
	private BESPark_EnergyTypeService besParkEnergyTypeservice;
	
	/**
	 * 初始化主界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/gethouseholdConf", method = RequestMethod.GET)
	public String gethouseholdConf() {
		return "view/basedatamanage/energyinformation/householdConf";
	}
	

	/**
	 * 获取分户配置数据
	 * @param besHouseholdConf
	 * @return
	 */
	@RequestMapping(value = "/getHouseholdList", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getHouseholdList(BesHouseholdConf besHouseholdConf) {
		log.info("BesHouseholdConController获取分户数据配置数据");
		//统一返回格式
		ISSPReturnObject returnObject = besHouseholdConfService.getHouseholdList(besHouseholdConf);	
		return returnObject;
	}
	
	/**
	 * 生成分户配置树
	 * @return
	 */
	@RequestMapping(value = "/loadAllTree", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject loadAll (String fYqbh,String fNybh) {
		log.info("BesHouseholdConController生成分户配置树");
		//统一返回格式
		ISSPReturnObject returnObject = besHouseholdConfService.loadAll(fYqbh,fNybh);	
		return returnObject;
	}
	
	/**
	 * 查找子类
	 * @param fFhbh
	 * @return
	 */
	@RequestMapping(value = "/household_chlildtreegrid", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject household_chlildtreegrid (String fFhbh) {
		log.info("BESSubitemConfController查找分项配置树子类");
		//统一返回格式
		ISSPReturnObject returnObject = besHouseholdConfService.household_chlildtreegrid(fFhbh);	
		return returnObject;
	}
	
	/**
	 * 生成园区树
	 * @return returnObject
	 */
	
	@RequestMapping(value = "/park_tree" , method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject Park_tree() {
		log.info("# 生成园区树" );
		ISSPReturnObject returnObject = besHouseholdConfService.Park_tree();	
		return returnObject;
	}
	
	/**
	 * 添加分户
	 * @param besHouseholdConf
	 * @return
	 */
	@RequestMapping(value = "/add_household", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject add_household ( BesHouseholdConf besHouseholdConf) {
		log.info("新增分户配置");
		ISSPReturnObject returnObject = besHouseholdConfService.add_household(besHouseholdConf);
		String date = DateUtil.getCurrTime();
		String fCrdate = date;
		String fChdate = date;
		besHouseholdConf.setfCrdate(fCrdate);
		besHouseholdConf.setfChdate(fChdate);
		return returnObject;
	}
	
	/**
	 * 删除分户
	 * @param fFhbh
	 * @return
	 */
	@RequestMapping(value = "/del_household", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject del_household (String fFhbh) throws Exception {
		log.info("BESSubitemConfController删除分项配置");
		ISSPReturnObject returnObject = besHouseholdConfService.del_household(fFhbh);	
		return returnObject;
	}
	
	/**
	 * 查询需要编辑的分户配置
	 * @param fFhbh
	 * @return
	 */
	@RequestMapping(value = "/queryhousehold", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject queryhousehold (String fFhbh) {
		log.info("查询需要编辑的分户配置");
		ISSPReturnObject returnObject = besHouseholdConfService.queryhousehold(fFhbh);	
		return returnObject;
	}
	
	/**
	 * 编辑分项
	 * @param besHouseholdConf
	 * @return
	 */
	@RequestMapping(value = "/edit_household", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject edit_household (BesHouseholdConf besHouseholdConf) {
		log.info("编辑分户配置");
		ISSPReturnObject returnObject = besHouseholdConfService.edit_household(besHouseholdConf);
		String date = DateUtil.getCurrTime();
		String fChdate = date;
		besHouseholdConf.setfChdate(fChdate);
		return returnObject;
	}
	
	/**
	 * 查询分户未包含的支路信息
	 * @param besHBR
	 * @param besBranchConf
	 * @return
	 */
	@RequestMapping(value = "/loadNoBrc", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject loadNoBrc (BesHouseholdBranchRlgl besHBR,BESBranchConf besBranchConf) {
		log.info("查询分户未包含的支路信息");
		ISSPReturnObject returnObject = besHouseholdConfService.loadNoBrc(besHBR,besBranchConf);	
		return returnObject;
	}
	
	/**
	 * 查询分户包含的支路信息
	 * @param besHBR
	 * @param besBranchConf
	 * @return
	 */
	@RequestMapping(value = "/loadInBrc", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject loadInBrc (BesHouseholdBranchRlgl besHBR,BESBranchConf besBranchConf) {
		log.info("查询分户包含的支路信息");
		ISSPReturnObject returnObject = besHouseholdConfService.loadInBrc(besHBR,besBranchConf);	
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
	@RequestMapping(value = "/loadShowCascade", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject loadShowCascade (BesHouseholdBranchRlgl besHBR) {
		log.info("显示是否级联");
		ISSPReturnObject returnObject = besHouseholdConfService.loadShowCascade(besHBR);
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
	@RequestMapping(value = "/changef_jl", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject changef_jl (String fJl,String fFhbh) {
		log.info("修改是否级联");
		ISSPReturnObject returnObject = besHouseholdConfService.changef_jl(fJl,fFhbh);
		return returnObject;
	}
	
	/**
	 * 分户中添加支路
	 * @param besHBR
	 * @return
	 */
	@RequestMapping(value = "/beshousehold_instBranch", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject beshousehold_instBranch (BesHouseholdBranchRlgl besHBR) {
		log.info("添加分户配置");
		ISSPReturnObject returnObject = besHouseholdConfService.beshousehold_instBranch(besHBR);	
		return returnObject;
	}
	
	/**
	 * 分户中删除支路
	 * @param besHBR
	 * @return
	 */
	@RequestMapping(value = "/beshousehold_delBranch", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject beshousehold_delBranch (BesHouseholdBranchRlgl besHBR) {
		log.info("删除分户配置");
		ISSPReturnObject returnObject = besHouseholdConfService.beshousehold_delBranch(besHBR);	
		return returnObject;
	}
	
	/**
	 * 移除全部支路
	 * @param besHBR
	 * @return
	 */
	@RequestMapping(value = "/beshousehold_leftmoveAll", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject beshousehold_leftmoveAll (BesHouseholdBranchRlgl besHBR,BESBranchConf besBranchConf) {
		log.info("全部移除支路配置");
		ISSPReturnObject returnObject = besHouseholdConfService.beshousehold_leftmoveAll(besHBR,besBranchConf);
		return returnObject;
	}
	
	/**
	 * 添加全部支路
	 * @param besHBR
	 * @return
	 */
	@RequestMapping(value = "/beshousehold_rightmoveAll", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject beshousehold_rightmoveAll (BesHouseholdBranchRlgl besHBR,BESBranchConf besBranchConf) {
		log.info("BESSubitemConfController编辑分项配置");
		ISSPReturnObject returnObject = besHouseholdConfService.beshousehold_rightmoveAll(besHBR,besBranchConf);
		return returnObject;
	}
	
	/**
	 * 传入园区编号查询相关能源信息下拉框
	 * @param f_yqbh
	 * @return
	 */
	@RequestMapping(value = "/getlist" , method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getgllist(String f_yqbh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#传入园区编号查询相关能源信息");
		 List<Map<String,Object>> getgllist = besParkEnergyTypeservice.getgllist(f_yqbh);
		    returnObject.setList(getgllist);
		    return returnObject;
	}
	
	/**
	 *  sunzhiyuan 改改改 根据能源类型生成分项配置
	 * @param fNybh 能源编号
	 * @param fYqbh 园区N编号
	 * @return
	 */
	@RequestMapping(value = "/NyhouseHold_treegrid", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject NyhouseHold_treegrid (String fYqbh,String fNybh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("BesHouseholdConController根据能源类型生成分项配置树");
		//查询出所有的的园区编号
		ISSPReturnObject returnObject3 = besHouseholdConfService.removeYqbhCf();

		List<Map<String,Object>> list = (List<Map<String, Object>>) returnObject3.getList();

		//查询所有能源类型
		ISSPReturnObject  returnObject5 = besHouseholdConfService.selectAlleneryType();
		List<Map<String,Object>>  allType = (List<Map<String, Object>>) returnObject5.getList();
		//第一步
		List list11 = new ArrayList<>();//临时存放的集合 《能源编号+List》形式
		//根据能源类型查询园区
		int id = -1;
		for (int g =0;g<allType.size();g++){
			List nodes = new ArrayList();
			String nybh = (String) allType.get(g).get("F_NYBH");
			String text = (String) allType.get(g).get("F_NYMC");
			allType.get(g).put("text",text);//换名字
			//根据能源编号查询园区编号
			ISSPReturnObject returnObject4 =besHouseholdConfService.selectAllPark(nybh);
			List<Map<String,Object>> list1 =  (List<Map<String, Object>>) returnObject4.getList();
			id++;
			for (int i =0;i<list1.size();i++){
				String yqbh = (String) list1.get(i).get("F_YQBH");
				//根据园区编号查询园区名称作为text
				//String yqmc = besHouseholdConfService.selectYqmc(yqbh);
				ISSPReturnObject returnObject1 = besHouseholdConfService.houseHold_treegrid1(nybh, yqbh);//根据能源编号和能源类型查询特定数据
 				List info = returnObject1.getList();
 				if(info.size()!=0){
					nodes.add(info.get(0));
				}
				continue;
			}
			//allType.get(g).put("nodes",parkEnergy);

			if (nodes.size()!=0){
				allType.get(g).put("nodes",  nodes);
				allType.get(g).put("id",  id);
				allType.get(g).put("nodeTreeId",  id);
				list11.add(allType.get(g));
			}
		}
			returnObject.setList(list11);
		return returnObject;
	}

	/**
	 * 获取主页树数据
	 * @return
	 */
	@RequestMapping(value = "/getHomePageTreeData", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getHomePageTreeData (String fYqbh, String fNybh) {
		return besHouseholdConfService.getHomePageTreeData(fYqbh, fNybh);
	}



	/**
	 * add by liuzhen 20181105 根据能源类型生成分项配置
	 * @param fNybh 能源编号
	 * @param fYqbh 园区N编号
	 * @return
	 */
	@RequestMapping(value = "/houseHold_treegrid", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject houseHold_treegrid (String fYqbh,String fNybh) {
		log.info("BesHouseholdConController根据能源类型生成分项配置树");
		//统一返回格式
		ISSPReturnObject returnObject = besHouseholdConfService.houseHold_treegrid(fYqbh,fNybh);
		return returnObject;
	}

	/**
	 * 获取分项配置树
	 * @return
	 */
	@RequestMapping(value = "/getSubitemTreeData", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getSubitemTreeData () {
		return besHouseholdConfService.getSubitemTreeData();
	}

	/**
	 * 获取用户列表
	 * */
	@RequestMapping(value = "/getuserNameList", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getuserNameList() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESUser> getuserNameList = besHouseholdConfService.getuserNameList();
		returnObject.setList(getuserNameList);
		return returnObject;
	}

	/**
	 *
	 * @Description: 获取组织机构列表
	 *
	 * @auther: wanghongjie
	 * @date: 14:03 2021/1/5
	 * @param: []
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@RequestMapping(value = "/getZZJGList", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getZZJGList() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<Map<String,Object>> getuserNameList = besHouseholdConfService.getZZJGList();
		returnObject.setList(getuserNameList);
		return returnObject;
	}
	/**
	 *
	 * @Description: 获取建筑信息
	 *
	 * @auther: wanghongjie
	 * @date: 14:25 2021/5/25
	 * @param:
	 * @return:
	 *
	 */
	@RequestMapping(value = "/getBuildingList", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getBuildingList() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<Map<String,Object>> getBuildingList = besHouseholdConfService.getBuilding();
		returnObject.setList(getBuildingList);
		return returnObject;
	}
}
