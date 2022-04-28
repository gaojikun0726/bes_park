package com.efounder.JEnterprise.controller.basedatamanage.energyinformation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.util.DateUtil;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESBranchConf;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BesDepartmentConf;
import com.efounder.JEnterprise.service.basedatamanage.energyinformation.BESPark_EnergyTypeService;
import com.efounder.JEnterprise.service.basedatamanage.energyinformation.BesDepartmentConfService;
import org.apache.ibatis.annotations.Param;
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
 *
 * @author suhang
 * @datetime 2018-07-26
 */
@Controller
@RequestMapping(value = "/view/basedatamanage/energyinformation")

public class BesDepartmentConController {
    private static final Logger log = LoggerFactory.getLogger(BesDepartmentConController.class);
    @Autowired
    private BesDepartmentConfService besDepartmentConfService;
    @Autowired
    private BESPark_EnergyTypeService besParkEnergyTypeservice;

    /**
     * 初始化主界面
     *
     * @return
     */
    @RequestMapping(value = "/getDepartmentConf", method = RequestMethod.GET)
    public String getDepartmentConf() {
        return "view/basedatamanage/energyinformation/DepartmentConf";
    }


    /**
     * 获取部门配置数据
     *
     * @param f_zzjgbh
     * @return
     */
    @RequestMapping(value = "/getDepartmentList", method = RequestMethod.GET)
    @ResponseBody
    public ISSPReturnObject getDepartmentList(String f_zzjgbh) {
        log.info("BesDepartmentConController获取分户数据配置数据");
        //统一返回格式
        ISSPReturnObject returnObject = besDepartmentConfService.getDepartmentList(f_zzjgbh);
        return returnObject;
    }

    /**
     * 获取部门配置数据
     *
     * @param f_zzjgbh
     * @return
     */
    @RequestMapping(value = "/getDepartmentAllList", method = RequestMethod.GET)
    @ResponseBody
    public ISSPReturnObject getDepartmentAllList(String f_zzjgbh,String zzjginfo) {
        log.info("BesDepartmentConController获取分户数据配置数据");
        //统一返回格式
        ISSPReturnObject returnObject = besDepartmentConfService.getDepartmentAllList(f_zzjgbh,zzjginfo);
        return returnObject;
    }

    /**
     * 生成部门配置树
     *
     * @return
     */
    @RequestMapping(value = "/loadAllTreeDep", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject loadAll(String fYqbh, String fNybh) {
        log.info("BesDepartmentConController生成分户配置树");
        //统一返回格式
//		ISSPReturnObject returnObject = besDepartmentConfService.loadAll(fYqbh,fNybh);
        return null;
    }

    /**
     * 查找子类
     *
     * @param fFhbh
     * @return
     */
    @RequestMapping(value = "/Department_chlildtreegrid", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject Department_chlildtreegrid(String fFhbh) {
        log.info("BESSubitemConfController查找分项配置树子类");
        //统一返回格式
//		ISSPReturnObject returnObject = besDepartmentConfService.Department_chlildtreegrid(fFhbh);
        return null;
    }

    /**
     * 生成园区树
     *
     * @return returnObject
     */

    @RequestMapping(value = "/park_tree_Dep", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject Park_tree() {
        log.info("# 生成园区树");
//		ISSPReturnObject returnObject = besDepartmentConfService.Park_tree();
        return null;
    }

    /**
     * 添加部门
     *
     * @param list
     * @return
     */
    @RequestMapping(value = "/add_Department", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject add_Department(String list) {
        log.info("新增部门配置");
        List<Map<String,Object>> listObjectFir = (List<Map<String,Object>>) JSONArray.parse(list);
        ISSPReturnObject returnObject = besDepartmentConfService.add_Department(listObjectFir);
        return returnObject;
    }

    /**
     * 删除部门
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/del_Department", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject del_Department(String id) throws Exception {
        log.info("BESSubitemConfController删除分项配置");
        ISSPReturnObject returnObject = besDepartmentConfService.del_Department(id);
        return returnObject;
    }

    /**
     * 查询需要编辑的部门配置
     *
     * @param fFhbh
     * @return
     */
    @RequestMapping(value = "/queryDepartment", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject queryDepartment(String fFhbh) {
        log.info("查询需要编辑的分户配置");
        ISSPReturnObject returnObject = besDepartmentConfService.queryDepartment(fFhbh);
        return returnObject;
    }

    /**
     * 编辑分项
     *
     * @param besDepartmentConf
     * @return
     */
    @RequestMapping(value = "/edit_Department", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject edit_Department(BesDepartmentConf besDepartmentConf) {
        log.info("编辑分户配置");
        ISSPReturnObject returnObject = besDepartmentConfService.edit_Department(besDepartmentConf);
        String date = DateUtil.getCurrTime();
        String fChdate = date;
        besDepartmentConf.setUpdateTime(fChdate);
        return returnObject;
    }

    /**
     * 查询分户未包含的支路信息
     *
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "/loadNoBrcDep", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject loadNoBrc(/*BesDepartmentBranchRlgl besHBR, BESBranchConf besBranchConf*/) {
//        log.info("查询分户未包含的支路信息");
//        ISSPReturnObject returnObject = besDepartmentConfService.loadNoBrc(besHBR, besBranchConf);
//        return returnObject;
        return null;
    }

    /**
     * 查询分户包含的支路信息
     *
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "/loadInBrcDep", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject loadInBrc(/*BesDepartmentBranchRlgl besHBR, BESBranchConf besBranchConf*/) {
//        log.info("查询分户包含的支路信息");
//        ISSPReturnObject returnObject = besDepartmentConfService.loadInBrc(besHBR, besBranchConf);
//        return returnObject;
        return null;
    }

    /**
     * @Description: 显示是否级联
     * @auther: wanghongjie
     * @date: 9:16 2020/11/18
     * @param: [besHBR, besBranchConf]
     * @return: com.core.common.ISSPReturnObject
     */
    @RequestMapping(value = "/loadShowCascadeDep", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject loadShowCascade(/*BesDepartmentBranchRlgl besHBR*/) {
//        log.info("显示是否级联");
//        ISSPReturnObject returnObject = besDepartmentConfService.loadShowCascade(besHBR);
//        return returnObject;
        return null;
    }

    /**
     * @Description: 修改是否级联
     * @auther: wanghongjie
     * @date: 9:49 2020/11/18
     * @param: [fJl, fFhbh]
     * @return: com.core.common.ISSPReturnObject
     */
    @RequestMapping(value = "/changef_jl_Dep", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject changef_jl(String fJl, String fFhbh) {
//        log.info("修改是否级联");
//        ISSPReturnObject returnObject = besDepartmentConfService.changef_jl(fJl, fFhbh);
//        return returnObject;
        return null;
    }

    /**
     * 分户中添加支路
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/besDepartment_instBranch", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject besDepartment_instBranch(/*BesDepartmentBranchRlgl besHBR*/) {
//        log.info("添加分户配置");
//        ISSPReturnObject returnObject = besDepartmentConfService.besDepartment_instBranch(besHBR);
//        return returnObject;
        return null;
    }

    /**
     * 分户中删除支路
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/besDepartment_delBranch", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject besDepartment_delBranch(/*BesDepartmentBranchRlgl besHBR*/) {
//        log.info("删除分户配置");
//        ISSPReturnObject returnObject = besDepartmentConfService.besDepartment_delBranch(besHBR);
//        return returnObject;
        return null;
    }

    /**
     * 移除全部支路
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/besDepartment_leftmoveAll", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject besDepartment_leftmoveAll(/*BesDepartmentBranchRlgl besHBR, BESBranchConf besBranchConf*/) {
//        log.info("全部移除支路配置");
//        ISSPReturnObject returnObject = besDepartmentConfService.besDepartment_leftmoveAll(besHBR, besBranchConf);
//        return returnObject;
        return null;
    }

    /**
     * 添加全部支路
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/besDepartment_rightmoveAll", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject besDepartment_rightmoveAll(/*BesDepartmentBranchRlgl besHBR, BESBranchConf besBranchConf*/) {
//        log.info("BESSubitemConfController编辑分项配置");
//        ISSPReturnObject returnObject = besDepartmentConfService.besDepartment_rightmoveAll(besHBR, besBranchConf);
//        return returnObject;
        return null;
    }

    /**
     * 传入园区编号查询相关能源信息下拉框
     *
     * @param f_yqbh
     * @return
     */
    @RequestMapping(value = "/getlistDep", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getgllist(String f_yqbh) {
//		ISSPReturnObject returnObject = new ISSPReturnObject();
//		log.info("#传入园区编号查询相关能源信息");
//		 List<Map<String,Object>> getgllist = besParkEnergyTypeservice.getgllist(f_yqbh);
//		    returnObject.setList(getgllist);
//		    return returnObject;
        return null;
    }

    /**
     * sunzhiyuan 改改改 根据能源类型生成分项配置
     *
     * @param fNybh 能源编号
     * @param fYqbh 园区N编号
     * @return
     */
    @RequestMapping(value = "/NyDepartment_treegrid_Dep", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject NyDepartment_treegrid(String fYqbh, String fNybh) {
//		ISSPReturnObject returnObject = new ISSPReturnObject();
//		log.info("BesDepartmentConController根据能源类型生成分项配置树");
//		//查询出所有的的园区编号
//		ISSPReturnObject returnObject3 = besDepartmentConfService.removeYqbhCf();
//
//		List<Map<String,Object>> list = (List<Map<String, Object>>) returnObject3.getList();
//
//		//查询所有能源类型
//		ISSPReturnObject  returnObject5 = besDepartmentConfService.selectAlleneryType();
//		List<Map<String,Object>>  allType = (List<Map<String, Object>>) returnObject5.getList();
//		//第一步
//		List list11 = new ArrayList<>();//临时存放的集合 《能源编号+List》形式
//		//根据能源类型查询园区
//		int id = -1;
//		for (int g =0;g<allType.size();g++){
//			List nodes = new ArrayList();
//			String nybh = (String) allType.get(g).get("F_NYBH");
//			String text = (String) allType.get(g).get("F_NYMC");
//			allType.get(g).put("text",text);//换名字
//			//根据能源编号查询园区编号
//			ISSPReturnObject returnObject4 =besDepartmentConfService.selectAllPark(nybh);
//			List<Map<String,Object>> list1 =  (List<Map<String, Object>>) returnObject4.getList();
//			id++;
//			for (int i =0;i<list1.size();i++){
//				String yqbh = (String) list1.get(i).get("F_YQBH");
//				//根据园区编号查询园区名称作为text
//				//String yqmc = besDepartmentConfService.selectYqmc(yqbh);
//				ISSPReturnObject returnObject1 = besDepartmentConfService.Department_treegrid1(nybh, yqbh);//根据能源编号和能源类型查询特定数据
// 				List info = returnObject1.getList();
// 				if(info.size()!=0){
//					nodes.add(info.get(0));
//				}
//				continue;
//			}
//			//allType.get(g).put("nodes",parkEnergy);
//
//			if (nodes.size()!=0){
//				allType.get(g).put("nodes",  nodes);
//				allType.get(g).put("id",  id);
//				allType.get(g).put("nodeTreeId",  id);
//				list11.add(allType.get(g));
//			}
//		}
//			returnObject.setList(list11);
//		return returnObject;
        return null;
    }

    /**
     * 获取主页树数据
     *
     * @return
     */
    @RequestMapping(value = "/getHomePageTreeDataDep", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getHomePageTreeData(String fYqbh, String fNybh) {
//		return besDepartmentConfService.getHomePageTreeData(fYqbh, fNybh);
        return null;
    }


    /**
     * add by liuzhen 20181105 根据能源类型生成分项配置
     *
     * @param fNybh 能源编号
     * @param fYqbh 园区N编号
     * @return
     */
    @RequestMapping(value = "/Department_treegrid_Dep", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject Department_treegrid(String fYqbh, String fNybh) {
//		log.info("BesDepartmentConController根据能源类型生成分项配置树");
//		//统一返回格式
//		ISSPReturnObject returnObject = besDepartmentConfService.Department_treegrid(fYqbh,fNybh);
//		return returnObject;
        return null;
    }

    /**
     * 获取分项配置树
     *
     * @return
     */
    @RequestMapping(value = "/getSubitemTreeDataDep", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getSubitemTreeData() {
//		return besDepartmentConfService.getSubitemTreeData();
        return null;
    }

    /**
     * 获取用户列表
     */
    @RequestMapping(value = "/getuserNameListDep", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getuserNameList() {
//		ISSPReturnObject returnObject = new ISSPReturnObject();
//		List<ESUser> getuserNameList = besDepartmentConfService.getuserNameList();
//		returnObject.setList(getuserNameList);
//		return returnObject;
        return null;
    }

    /**
     * @Description: 获取组织机构列表
     * @auther: wanghongjie
     * @date: 14:03 2021/1/5
     * @param: []
     * @return: com.core.common.ISSPReturnObject
     */
    @RequestMapping(value = "/getZZJGListDep", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getZZJGList() {
//		ISSPReturnObject returnObject = new ISSPReturnObject();
//		List<Map<String,Object>> getuserNameList = besDepartmentConfService.getZZJGList();
//		returnObject.setList(getuserNameList);
//		return returnObject;
        return null;
    }

    /**
     * @Description: 获取建筑信息
     * @auther: wanghongjie
     * @date: 14:25 2021/5/25
     * @param:
     * @return:
     */
    @RequestMapping(value = "/getBuildingListDep", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getBuildingList() {
//		ISSPReturnObject returnObject = new ISSPReturnObject();
//		List<Map<String,Object>> getBuildingList = besDepartmentConfService.getBuilding();
//		returnObject.setList(getBuildingList);
        return null;
    }

    /**
     * @Description: 所有支路
     * @auther: wanghongjie
     * @date: 14:25 2021/5/25
     * @param:
     * @return:
     */
    @RequestMapping(value = "/getSelectBranchInfo", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getSelectBranchInfo(String bmbh,String keywords) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<Map<String,Object>> selectBranchInfoList = besDepartmentConfService.getSelectBranchInfoList(bmbh,keywords);
		returnObject.setList(selectBranchInfoList);
		return returnObject;
    }

    @RequestMapping(value = "/getSelectBranchInfoChoose", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getSelectBranchInfoChoose(String bmbh,String F_LEVEL) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        List<Map<String,Object>> selectBranchInfoList = besDepartmentConfService.getSelectBranchInfoChoose(bmbh,F_LEVEL);
        returnObject.setList(selectBranchInfoList);
        return returnObject;
    }




    @RequestMapping(value = "/getSelectBranchInfoById", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getSelectBranchInfoById(String fZlbh) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        List<Map<String,Object>> selectBranchInfoList = besDepartmentConfService.getSelectBranchInfoById(fZlbh);
        returnObject.setList(selectBranchInfoList);
        return returnObject;
    }

    @RequestMapping(value = "/getSelectBranchCount", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getSelectBranchCount(String F_DEP_ID,String fZlbh,String F_LEVEL) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        List<Map<String,Object>> selectBranchInfoList = besDepartmentConfService.getSelectBranchCount(F_DEP_ID,fZlbh, F_LEVEL);
        returnObject.setList(selectBranchInfoList);
        return returnObject;
    }


    /**
     * @Description: 所有电表
     * @auther: wanghongjie
     * @date: 14:25 2021/5/25
     * @param:
     * @return:
     */
    @RequestMapping(value = "/getSelectElectricityMeterInfo", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getSelectElectricityMeterInfo(String bmbh,String keywords) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        List<Map<String,Object>> selectElectricityMeterInfoList = besDepartmentConfService.getSelectElectricityMeterInfoList(bmbh,keywords);
        returnObject.setList(selectElectricityMeterInfoList);
        return returnObject;
    }

    @RequestMapping(value = "/getSelectElectricityMeterInfoChoose", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getSelectElectricityMeterInfoChoose(String bmbh,String F_LEVEL) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        List<Map<String,Object>> selectElectricityMeterInfoList = besDepartmentConfService.getSelectElectricityMeterInfoChoose(bmbh,F_LEVEL);
        returnObject.setList(selectElectricityMeterInfoList);
        return returnObject;
    }

    @RequestMapping(value = "/getSelectElectricityMeterInfoById", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getSelectElectricityMeterInfoById(String fZlbh) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        List<Map<String,Object>> selectBranchInfoList = besDepartmentConfService.getSelectElectricityMeterInfoById(fZlbh);
        returnObject.setList(selectBranchInfoList);
        return returnObject;
    }

    @RequestMapping(value = "/getSelectElectricityMeterCount", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getSelectElectricityMeterCount(String F_DEP_ID,String fZlbh,String F_LEVEL) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        List<Map<String,Object>> selectBranchInfoList = besDepartmentConfService.getSelectElectricityMeterCount(F_DEP_ID,fZlbh, F_LEVEL);
        returnObject.setList(selectBranchInfoList);
        return returnObject;
    }


    @RequestMapping(value = "/getDepPeopleNumber", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getDepPeopleNumber(String F_DEP_ID) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        Map<String,Object> numberMap = besDepartmentConfService.getDepPeopleNumber(F_DEP_ID);
        returnObject.setData(numberMap);
        return returnObject;
    }

    @RequestMapping(value = "/setDepPeopleNumber", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject setDepPeopleNumber(String F_DEP_ID,int number) {
        ISSPReturnObject returnObject = besDepartmentConfService.setDepPeopleNumber(F_DEP_ID,number);
        return returnObject;
    }
}
