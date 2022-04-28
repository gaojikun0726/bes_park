package com.efounder.JEnterprise.controller.usercenter;

import com.core.common.ISSPReturnObject;
import com.core.config.db.OperationConfig;
import com.core.config.qxpz.QxConfig;
import com.efounder.JEnterprise.model.applicationmanage.ESXtList;
import com.efounder.JEnterprise.model.usercenter.ESSjqx;
import com.efounder.JEnterprise.service.applicationmanage.ESXtListService;
import com.efounder.JEnterprise.service.usercenter.ESSjqxService;
import com.framework.common.utils.OperationLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description 数据权限字典
 * @author gongfanfei
 */
@RequestMapping(value = "/view/sjqx/")
@Controller
public class ESSjqxController {

	private static final Logger log = LoggerFactory.getLogger(ESSjqxController.class);

	@Autowired
	private ESSjqxService esSjqxService;
	@Autowired
	private ESXtListService esXtService;
	@Autowired
	private QxConfig qxConfig;
	@Autowired
	private OperationConfig operationConfig;
	
	/**
	 * @Description 初始化数据权限字典主页面
	 */
	@RequestMapping(value = "dataSjqx", method = RequestMethod.GET)
	public String getUserSjqxs() {
		log.info("#ESSjqxController跳转数据权限字典页");
		return "/isspview/usermanage/dataauthoritymanage/sjqxzd";
	}

	/**
	 * @Description 加载所有sjqx信息
	 */
	@RequestMapping(value = "/loadAllSjqx", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getSjqxList() {
		log.info("#ESSjqxController 加载所有数据权限信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESSjqx> list = new ArrayList<ESSjqx>();
		list = esSjqxService.findSjqxs();
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
	@RequestMapping(value = "/data_sjqx_search", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getSjqxList(String keywords,String f_xtbh) {
		log.info("#ESSjqxController 根据关键字搜索数据权限对象");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESSjqx> list = new ArrayList<ESSjqx>();
		list = esSjqxService.getSjqxListByKeywords(keywords);
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
	public ESSjqx loadedit(@RequestBody ESSjqx essjqx) {
		log.info("#ESSjqxController ajax加载编辑数据权限对象");
		ESSjqx essjqxinfo = esSjqxService.findSjqxById(essjqx.getF_qxbh());
		return essjqxinfo;
		}
	/**
	 * 
	 * @Description 添加前加载权限配置表
	 */
	@RequestMapping(value = "/loadqxpztable",method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject loadTableName() {
		log.info("#ESSjqxController ajax加载编辑数据权限对象");
		String classifySjqxb = qxConfig.getClassifySjqxb();
		String usersjqx = qxConfig.getUserSjqxb();
		List<String> list = new ArrayList<String>();
		list.add(usersjqx);
		list.add(classifySjqxb);
		ISSPReturnObject returnObject = new ISSPReturnObject();
		returnObject.setList(list);
		return returnObject;
	}
	
	/**
	 * @Description  添加数据权限
	 */
	@RequestMapping(value = "data_sjqx_add", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject addSjqx(@RequestBody ESSjqx sjqx) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#ESSjqxController添加数据权限信息");
		try {
			boolean isSucc = false;
			ESSjqx checkQxbhExist = esSjqxService.checkQxbhExist(sjqx.getF_qxbh());
			if(checkQxbhExist != null){
				returnObject.setStatus("0");
				returnObject.setMsg("权限标识已存在");
				
				
			}else{
				isSucc = esSjqxService.addSjqx(sjqx);
				String dataBaseUseable = operationConfig.getSysDataBaseUseable();
				if("1".equals(dataBaseUseable)){
					OperationLog.insert(sjqx.getF_qxbh(), "es_sjqx");
			}
			if(sjqx.getF_sfsy().equals("1")){
				sjqx.setF_sfsy("是");
			}else{
				sjqx.setF_sfsy("否");
			}
			if (isSucc) {
				returnObject.setStatus("1");
				returnObject.setMsg("添加数据权限成功");
				returnObject.setData(sjqx);
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("添加数据权限失败");
			}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}
	
	/**
	 * @Description 删除数据权限信息
	 */
	@RequestMapping(value = "data_sjqx_del", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject delSjqxByQxbh(@RequestBody ESSjqx sjqx) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#ESSjqxController删除数据权限信息");
		try {
			String dataBaseUseable = operationConfig.getSysDataBaseUseable();
			if("1".equals(dataBaseUseable)){
				OperationLog.delete(sjqx.getF_qxbh(), "es_sjqx");
			}
			boolean isSucc = esSjqxService.delSjqx(sjqx);
			
			if (isSucc) {
				returnObject.setStatus("1");
				returnObject.setMsg("删除数据权限成功");
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("删除数据权限失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}

	/**
     * @Description ajax更新数据权限信息
     */
    @RequestMapping(value = "/sjqx_edit", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject edit(ESSjqx sjqx) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
    	log.info("#ESSjqxController修改数据权限信息");
		try {
			String dataBaseUseable = operationConfig.getSysDataBaseUseable();
			boolean flag = false;
			if("1".equals(dataBaseUseable)){
				Map<String, Object> updateStart = OperationLog.updateStart(sjqx.getF_qxbh(), "es_sjqx");
				flag = esSjqxService.upSjqx(sjqx);
				OperationLog.updateEnd(sjqx.getF_qxbh(), "es_sjqx", updateStart);
			}
	        if (flag) {
	        	returnObject.setStatus("1");
	        	returnObject.setMsg("数据权限修改成功!");
	        	returnObject.setData(sjqx);
	        } else {
	        	returnObject.setStatus("0");
	        	returnObject.setMsg("数据权限修改失败!");
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
    	log.info("#ESSjqxController子系统查询列表");
    	ISSPReturnObject returnObject = new ISSPReturnObject();
    	List<ESXtList> list = new ArrayList<ESXtList>();
    	list = esXtService.findXts();
    	returnObject.setList(list);
    	return returnObject;
    }
    
    /**
	 * @Description 根据不同的系统编码查询对应的数据权限
	 */
	@RequestMapping(value = "/data_sjqxByXtbh", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getSjqxListByXtbh(String f_xtbh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESSjqx> list = new ArrayList<ESSjqx>();
		list = esSjqxService.findSjqxByXtbh(f_xtbh);
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
