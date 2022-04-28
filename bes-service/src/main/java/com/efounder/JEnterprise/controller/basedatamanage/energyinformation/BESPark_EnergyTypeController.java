package com.efounder.JEnterprise.controller.basedatamanage.energyinformation;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESPark;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESPark_EnergyType;
import com.efounder.JEnterprise.service.basedatamanage.energyinformation.BESParkService;
import com.efounder.JEnterprise.service.basedatamanage.energyinformation.BESPark_EnergyTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 园区能耗类型
 * @author yujieying
 */
@RequestMapping(value = "/view/basedatamanage/energyinformation")
@Controller
public class BESPark_EnergyTypeController {

private static final Logger log = LoggerFactory.getLogger(BESPark_EnergyTypeController.class);

	@Autowired
	private BESPark_EnergyTypeService besPark_EnergyTypeService;
	//@Autowired
	//private BESEnergyTypeService besEnergyTypeService;
	@Autowired
	private BESParkService besparkService;
	/**
	 * @Description 初始加载园区能耗类型主页面
	 * @return
	 */
	@RequestMapping(value = "Park_EnergyType_page", method = RequestMethod.GET)
	public String getPark_EnergyTypePage() {
		log.info("#BESPark_EnergyTypeController初始加载园区能耗类型主页面");
		return "/view/basedatamanage/energyinformation/park_energytype";
	}
	/**
	 * @Description 初始加载园区列表
	 * * 
	  */ 
	@RequestMapping(value = "/park_list", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject findParkinfoListByYqbh() {//不带参数的话就是加载整个表
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#BESPark_EnergyTypeController 获取园区列表");
		List<BESPark> list = new ArrayList<BESPark>();
		list = besparkService.findParkinfoListByYqbh();
		returnObject.setList(list);
		return returnObject;
    }
	
	
	/**
	 * @Description 查询个别园区与能源类型关联
	 * * 
	  */ 
	@RequestMapping(value = "/getenrrgylist", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getenrrgylist(String f_yqbh){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#BESPark_EnergyTypeController 获取个别园区与能源类型关系");
		List<Map<String,Object>> eneryMap = besPark_EnergyTypeService.getenrrgylist(f_yqbh);
		for (int i = 0; i < eneryMap.size(); i++) {
			//重新定义map
			Map<String,Object> newMap=new HashMap<String,Object>();
	
			String fnybh = (String)eneryMap.get(i).get("F_NYBH");//能源编号
			String fnymc = (String)eneryMap.get(i).get("F_NYMC");//能源名称
			String fPrice = (String)eneryMap.get(i).get("F_PRICE");//单价
			String fCoalAmount = (String)eneryMap.get(i).get("F_COAL_AMOUNT");//耗煤量
			String fCo2 = (String)eneryMap.get(i).get("F_CO2");//二氧化碳
		
			Object rl = eneryMap.get(i).get("F_PARK_ENENGGY_RLGL");//关系表中勾选标记
			
			String rlgl =  (String)rl;//转换成string类型
			Boolean rlglmark = false;//初始化布尔类型为false
			//判断关系标记是否为空，当不为空的情况下，布尔标记为true,则对应表格中勾选状态
			if(rlgl!=null){//判断非空  isEmpty()  !=""  !=null
				rlglmark = true;
			}
			//map中重新set值
			newMap.put("rlgl",rlglmark);
			newMap.put("fnybh",fnybh);
			newMap.put("fnymc",fnymc);
			newMap.put("fCoalAmount",fCoalAmount);
			newMap.put("fPrice",fPrice);
			newMap.put("fCo2",fCo2);
			eneryMap.set(i, newMap);
		}
		
		    returnObject.setList(eneryMap);
		    return returnObject;
	}	 
	
	
	
	/**
	 * @Description 园区与能源类型关联
	 * * 
	  */ 
	@RequestMapping(value = "/getgllist", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getgllist(String f_yqbh){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#BESPark_EnergyTypeController 获取园区与能源类型关系");
		List<Map<String,Object>> eneryMap = besPark_EnergyTypeService.getgllist(f_yqbh);
		for (int i = 0; i < eneryMap.size(); i++) {
			//重新定义map
			Map<String,Object> newMap=new HashMap<String,Object>();
	
			String fnybh = (String)eneryMap.get(i).get("F_NYBH");//能源编号
			String fnymc = (String)eneryMap.get(i).get("F_NYMC");//能源名称
			String fPrice = (String)eneryMap.get(i).get("F_PRICE");//单价
			String fCoalAmount = (String)eneryMap.get(i).get("F_COAL_AMOUNT");//耗煤量
			String fCo2 = (String)eneryMap.get(i).get("F_CO2");//二氧化碳
		
			Object rl = eneryMap.get(i).get("F_PARK_ENENGGY_RLGL");//关系表中勾选标记
			
			String rlgl =  (String)rl;//转换成string类型
			Boolean rlglmark = false;//初始化布尔类型为false
			//判断关系标记是否为空，当不为空的情况下，布尔标记为true,则对应表格中勾选状态
			if(rlgl!=null){//判断非空  isEmpty()  !=""  !=null
				rlglmark = true;
			}
			//map中重新set值
			newMap.put("rlgl",rlglmark);
			newMap.put("fnybh",fnybh);
			newMap.put("fnymc",fnymc);
			newMap.put("fCoalAmount",fCoalAmount);
			newMap.put("fPrice",fPrice);
			newMap.put("fCo2",fCo2);
			eneryMap.set(i, newMap);
		}
		
		    returnObject.setList(eneryMap);
		    return returnObject;
	}	 
	/**
	 * @Description 添加/更新园区能源类型的选择
	 * @return
	 */
	@RequestMapping(value = "park_energytype_add", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject addpark_energytype(String f_yqbh,String f_nybh, String mark) {
		
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#BESPark_EnergyTypeController添加园区能耗类型的选择信息");
		
		   BESPark_EnergyType bespark_energytype = new BESPark_EnergyType();
		   bespark_energytype.setF_nybh(f_nybh);
		   bespark_energytype.setF_yqbh(f_yqbh);

		   List<Map<String, Object>>  isfindPe = besPark_EnergyTypeService.findpark_energytype(bespark_energytype);

		  if (null == isfindPe)
		  {
			  returnObject.setStatus("0");
			  returnObject.setMsg("更新园区类型失败");
			  return returnObject;
		  }

		   if (isfindPe.isEmpty())
		   {
			   if(mark.equals("1")){//当打勾的时候执行添加，没有打勾的不添加
				   besPark_EnergyTypeService.addpark_energytype(bespark_energytype);
			   }

		   }else
		   {

				if (mark.equals("0"))
				{
					besPark_EnergyTypeService.delpark_energytype(bespark_energytype);
				}
		   }

			returnObject.setStatus("1");
			returnObject.setMsg("更新园区类型成功");

		   return returnObject;
	}
	
	/**
	 * 根据关键字查询信息
	 * @param keywords
	 * @param pageNum
	 * @param map
	 * @return
	 */
	 @RequestMapping(value = "/findByKeywords", method = RequestMethod.GET)
	 @ResponseBody
	 public ISSPReturnObject findBESParkByKeywords(@RequestParam(value = "keywords", required = false) String keywords) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<BESPark> list = new ArrayList<BESPark>();
		list = besparkService.findParkByKeywords(keywords);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getF_yqbh() == null|| "".equals(list.get(i).getF_yqbh())) {
				list.get(i).setF_yqbh(null);
			}
		}
		returnObject.setList(list);
		return returnObject;
	}
	    
}
