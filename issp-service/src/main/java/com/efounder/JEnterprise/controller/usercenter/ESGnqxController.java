package com.efounder.JEnterprise.controller.usercenter;

import com.core.common.ISSPReturnObject;
import com.core.config.db.OperationConfig;
import com.efounder.JEnterprise.model.applicationmanage.ESXtList;
import com.efounder.JEnterprise.model.usercenter.ESGnqx;
import com.efounder.JEnterprise.service.applicationmanage.ESXtListService;
import com.efounder.JEnterprise.service.usercenter.ESGnqxService;
import com.framework.common.utils.OperationLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 功能权限字典
 * @author gongfanfei
 */
@RequestMapping(value = "/view/gnqx/")
@Controller
public class ESGnqxController {

	private static final Logger log = LoggerFactory.getLogger(ESGnqxController.class);

	@Autowired
	private ESGnqxService esGnqxService;
	@Autowired
	private ESXtListService esXtService;
	@Autowired
	private OperationConfig operationConfig;
	
	
	/**
	 * @Description 跳转功能权限字典页面
	 */
	@RequestMapping(value = "dataGnqx", method = RequestMethod.GET)
	public String getUserGnqxs(ModelMap model) {
		log.info("#ESGnqxController跳转功能权限字典页");
		return "/isspview/usermanage/funauthoritymanage/gnqxzd";
	}

	/**
	 * @Description 加载所有gnqx信息
	 */
	@RequestMapping(value = "/loadAllGnqx", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getGnqxList() {
		log.info("#ESGnqxController 加载所有功能权限信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESGnqx> list = new ArrayList<ESGnqx>();
		list = esGnqxService.findGnqxs();
		for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getF_sfsy().equals("1") == true){
					list.get(i).setF_sfsy("是");
				}else{
					list.get(i).setF_sfsy("否");
				}
		}
		returnObject.setList(list);
		return returnObject;
	}
	/**
	 * @Description 搜索
	 */
	@RequestMapping(value = "/data_gnqx_search", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getGnqxList(String keywords,String f_xtbh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#ESGnqxController 根据关键字搜索功能权限对象");
		List<ESGnqx> list = new ArrayList<ESGnqx>();
		list = esGnqxService.getGnqxListByKeywords(f_xtbh,keywords);
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getF_sfsy().equals("1") == true){
				list.get(i).setF_sfsy("是");
			}else{
				list.get(i).setF_sfsy("否");
			}
		}
		returnObject.setList(list);
		return returnObject;
    }
	/**
	 * 
	 * @Description 加载编辑对象
	 */
	@RequestMapping(value = "/loadeditobj",method = RequestMethod.POST)
	@ResponseBody
	public ESGnqx loadedit(@RequestBody ESGnqx esgnqx) {
		log.info("#ESGnqxController ajax加载编辑功能权限对象");
		ESGnqx esgnqxinfo = esGnqxService.findGnqxById(esgnqx.getF_qxbh());
		return esgnqxinfo;
		}

	/**
	 * @Description  添加功能权限
	 */
	@RequestMapping(value = "data_gnqx_add", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject addGnqx(@RequestBody ESGnqx gnqx, ModelMap model) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#ESGnqxController添加功能权限信息");
		//设定四位数字，不够位数，自动补“0”
		DecimalFormat df=new DecimalFormat("0000");
		
		List<ESGnqx> gnqxlist = esGnqxService.findGnqxs();
		int len = gnqxlist.size();
		if(len==0){
			gnqx.setF_qxbh("0000");
		}else{
		String pqxbh = gnqxlist.get(len-1).getF_qxbh();
		Integer cuqxbh = (Integer.parseInt(pqxbh)+1);
		String  currentqxbh = df.format(cuqxbh);
		gnqx.setF_qxbh(currentqxbh);
		}
		boolean isSucc = esGnqxService.addGnqx(gnqx);//
		try {
			OperationLog.insert(gnqx.getF_qxbh(), "es_gnqx");
		if(gnqx.getF_sfsy().equals("1")){
			gnqx.setF_sfsy("是");
		}else{
			gnqx.setF_sfsy("否");
		}
	
		if (isSucc) {
			returnObject.setStatus("1");
			returnObject.setMsg("添加功能权限成功");
			returnObject.setData(gnqx);
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("添加功能权限失败");
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}

	/**
	 * @Description 删除功能权限信息
	 */
	@RequestMapping(value = "data_gnqx_del", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delGnqxByQxbh(@RequestBody ESGnqx gnqx) {
		Map<String, String> result = new HashMap<>();
		log.info("#ESGnqxController删除功能权限信息");
		try {
			OperationLog.delete(gnqx.getF_qxbh(), "es_gnqx");
		boolean isSucc = esGnqxService.delGnqx(gnqx);
	
		if (isSucc) {
			result.put("status", "1");
			result.put("msg", "删除功能权限成功");
		} else {
			result.put("status", "0");
			result.put("msg", "删除功能权限失败");
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
     * @Description ajax更新功能权限信息
     */
    @RequestMapping(value = "/gnqx_edit", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject edit(ESGnqx gnqx, ModelMap model) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
    	log.info("#ESGnqxController修改功能权限信息");
    	Map<String, Object> startMap;
		try {
			startMap = OperationLog.updateStart(gnqx.getF_qxbh(), "es_gnqx");
        boolean flag = esGnqxService.upGnqx(gnqx);
        OperationLog.updateEnd(gnqx.getF_qxbh(), "es_gnqx", startMap);
        if (flag) {
        	returnObject.setStatus("1");
        	returnObject.setMsg("功能权限修改成功!");
        	returnObject.setData(gnqx);
        } else {
        	returnObject.setStatus("0");
        	returnObject.setMsg("功能权限修改失败!");
        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return returnObject;
    }
    
    /**
     * @Description 子系统查询
     */
    @RequestMapping(value = "/loadAllXt", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getXtList() {
    	ISSPReturnObject returnObject = new ISSPReturnObject();
    	log.info("#ESGnqxController子系统查询列表");
    	List<ESXtList> list = new ArrayList<ESXtList>();
    	list = esXtService.findXts();
    	returnObject.setList(list);
    	return returnObject;
    }
    /**
	 * @Description 根据不同的系统编码查询对应的功能权限
	 */
	@RequestMapping(value = "/data_gnqxByXtbh", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getGnqxListByXtbh(String f_xtbh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESGnqx> list = new ArrayList<ESGnqx>();
		list = esGnqxService.findGnqxByXtbh(f_xtbh);
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getF_sfsy().equals("1") == true){
				list.get(i).setF_sfsy("是");
			}else{
				list.get(i).setF_sfsy("否");
			}
		}
		returnObject.setList(list);
		return returnObject;
    }
}
