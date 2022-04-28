package com.efounder.JEnterprise.controller.basedatamanage.energyinformation;

import com.core.common.ISSPReturnObject;
import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESPark;
import com.efounder.JEnterprise.service.basedatamanage.energyinformation.BESParkService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** 
 * 类名称：BESParkController
 * 类描述：园区表Controller
 * 创建人：sunhao
 * 修改人：sunhao
 * 修改时间：2018年7月3日 
 * 修改备注：
 * @version 1.0.0 
 *
 */
@RequestMapping(value = "/view/park")
@Controller
public class BESParkController {
	private static final Logger log = LoggerFactory.getLogger(BESParkController.class);
	
	@Autowired 
	private BESParkService parkService;
	
	@RequestMapping(value = "/park_page", method = RequestMethod.GET)
	public String getBESPark(ModelMap map) {
		log.info("# BESParkController获取信息");
		return "view/basedatamanage/energyinformation/bespark";
	}
	/**
	 * 分页查询
	 * @param keywords
	 * @param pageNum
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/BESPark_page", method = RequestMethod.POST)
	public String list_page(@RequestParam(value = "keywords", required = false) String keywords,
			@RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map) {
		log.info("#分页查询 pageNum={} , keywords={}", pageNum, keywords);
		PageInfo<BESPark> page = parkService.findParkByPage(pageNum, keywords);
		map.put("page", page);
		//将数据转化为json字符串 add by wujf at 20180623
		map.put("dataset", JsonMapper.toJsonString(page.getList()));
		map.put("keywords", keywords);
		return "view/basedatamanage/energyinformation/bespark_page";
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
		list = parkService.findParkByKeywords(keywords);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getF_yqbh() == null|| "".equals(list.get(i).getF_yqbh())) {
				list.get(i).setF_yqbh(null);
			}
		}
		returnObject.setList(list);
		return returnObject;
	}

	/**
	 * 查询所有数据
	 * @return
	 */
	@RequestMapping(value = "/findAllBESPark", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject findBAllESPark() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<BESPark> parkList = parkService.findAllPark();
		returnObject.setList(parkList);
		return returnObject;
	}
	/**
	 * 添加信息
	 * @param json
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addBESPark", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject addBESPark(@RequestBody BESPark park, ModelMap model) {
		log.info("#BESParkController添加信息");
		DecimalFormat df=new DecimalFormat("0000");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<BESPark> parklist = parkService.findAllPark();
		int parkLen = parklist.size();
		if(parkLen==0){
			park.setF_yqbh("0000");                        
		}else{
			String maxBmbh = parkService.findMaxYqbh();
			Integer cuBESParkbh = (Integer.parseInt(maxBmbh)+1);
			String  currentBESParkbh = df.format(cuBESParkbh);
			park.setF_yqbh(currentBESParkbh);                    
		}
		boolean isSucc = parkService.addPark(park);
		if (isSucc) {
			returnObject.setStatus("1");
			returnObject.setMsg("添加信息成功");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			park.setF_chdate(sdf.format(new Date()));
			park.setF_crdate(sdf.format(new Date()));
			returnObject.setData(park);
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("添加信息失败");
		}
		return returnObject;
	}
	/**
	 * 删除信息
	 * @param f_brandbh
	 * @return
	 */
	@RequestMapping(value = "/deleteBESPark", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject delBESPark(@RequestBody BESPark park, ModelMap model) {
		log.info("#BESParkController删除信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		returnObject = parkService.deleteByYqbh(park);
		return returnObject;
	}
	/**
	 * 更新修改信息
	 * @param 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateBESPark", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject updateBESPark(BESPark park, ModelMap model) {
		log.info("#BESParkController更新修改信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		boolean isSucc = parkService.upDatePark(park);
		if (isSucc) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			park.setF_chdate(sdf.format(new Date()));
			returnObject.setStatus("1");
			returnObject.setMsg("更新修改成功");
			returnObject.setData(park);
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("更新修改失败");
		}
		return returnObject;
	}
	/**
	 * ajax加载编辑对象
	 * @param F_yqbh
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/loadeditobj", method = RequestMethod.POST)
	@ResponseBody
	public BESPark loadedit(@RequestBody BESPark Park) {
		log.info("# ajax加载编辑组织机构对象");
		BESPark park = parkService.findParkByYqbh(Park.getF_yqbh());
		return park;
	}
}
