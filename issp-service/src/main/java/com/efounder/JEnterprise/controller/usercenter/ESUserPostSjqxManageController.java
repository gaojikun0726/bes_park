package com.efounder.JEnterprise.controller.usercenter;

import com.core.common.ISSPReturnObject;
import com.core.common.util.ListCompare;
import com.core.config.qxpz.QxConfig;
import com.efounder.JEnterprise.model.usercenter.ESPost;
import com.efounder.JEnterprise.model.usercenter.ESSjqx;
import com.efounder.JEnterprise.model.usercenter.ESUserPostSjqxManage;
import com.efounder.JEnterprise.service.usercenter.ESPostService;
import com.efounder.JEnterprise.service.usercenter.ESPostSjqxManageService;
import com.efounder.JEnterprise.service.usercenter.ESSjqxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 岗位数据权限
 * @author gongfanfei
 */
@RequestMapping(value = "/view/postsjqx/")
@Controller
public class ESUserPostSjqxManageController {

	private static final Logger log = LoggerFactory.getLogger(ESUserPostSjqxManageController.class);

	@Autowired
	private ESPostSjqxManageService esPostSjqxManageService;
	@Autowired
	private ESSjqxService esSjqxService;
	@Autowired
	private ESPostService esPostService;
	@Autowired
	private QxConfig qxConfig;
	private static String postqxb ;
	
	public static void setPostqxb(String postqxb) {
		ESUserPostSjqxManageController.postqxb = postqxb;
	}
	private static String getPostqxb() {
		// TODO Auto-generated method stub
		return postqxb;
	}
	/**
	 * @Description 初始加载岗位数据权限主页面
	 * @return
	 */
	@RequestMapping(value = "post_sjqx", method = RequestMethod.GET)
	public String getPostSjqxManagePage() {
		log.info("#ESUserPostSjqxManageController初始加载岗位数据权限主页面");
		return "/isspview/usermanage/dataauthoritymanage/postsjqx";
	}
	
	/**
	 * @Description 岗位列表
	 */
	@RequestMapping(value = "/post_list", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject loadPostlist(String keywords) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESPost> list = new ArrayList<ESPost>();
		list = esPostService.getUserPostList(keywords);
		returnObject.setList(list);
		return returnObject;
    }
	 /**
	  * @Description 数据权限字典列表
	  * 
	  */
	@RequestMapping(value = "/sjqx_list",method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject loadeSjqxlist() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESSjqx> list = new ArrayList<ESSjqx>();
		list = esSjqxService.findSjqxs();
		returnObject.setList(list);
		return returnObject;
	}
	/**
	 * @Description 搜索数据权限
	 */
	@RequestMapping(value = "/data_sjqx_search", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getSjqxList(String keywords) {
		log.info("#ESSjqxController 根据关键字搜索数据权限对象");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESSjqx> list = new ArrayList<ESSjqx>();
		list = esSjqxService.getSjqxListByKey(keywords);
		returnObject.setList(list);
		return returnObject;
    }
	
	 /**
	  * @Description 岗位数据权限列表
	  * 
	  */
	@RequestMapping(value = "/postsjqx_list",method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject loadPostSjqxlist(String f_gwguid,String f_qxbh,String f_tabn,String f_bhzd,String f_qxbz,String currentsjqxtb,String mczd) {
		
		ISSPReturnObject returnObject = new ISSPReturnObject();
		if(currentsjqxtb !="undefined" && currentsjqxtb != "" && currentsjqxtb != null ){
			ESUserPostSjqxManageController.setPostqxb(currentsjqxtb);
			String classifysjqxTb = qxConfig.getClassifySjqxb();
			if(!classifysjqxTb.equals(currentsjqxtb)){
				/*if(currentsjqxtb!=""){
					classifysjqxTb = currentsjqxtb;
				}*/
				returnObject.setList(null);
				returnObject.setStatus("0");
				returnObject.setMsg("未启用岗位数据权限表！");
				return returnObject;
			}
			String columns = f_bhzd;//主键字段
			String tableName = f_tabn;//表名
			List<ESUserPostSjqxManage> classifysjqxlist = new ArrayList<ESUserPostSjqxManage>();
			
			List<ESUserPostSjqxManage> postsj = esPostSjqxManageService.findUserPostSjqxById(f_gwguid, f_qxbh, columns, tableName,classifysjqxTb);
			String col = f_bhzd;
			if(!mczd.equals("") && mczd!=null){
				col = col + ","+mczd;
			}
			List<?> list = esPostSjqxManageService.getcolumnsBytableName(col, tableName);
			
			//封装needMap容器，盛放id-name键值对
			Map<String, String> needMap = new HashMap<String, String>();
			List<String> newRoleBhList = new ArrayList<String>();//容器存放最新的编号集合--最新的数据
			List<String> oldRoleBhList = new ArrayList<String>();//容器存放最新的编号集合--角色数据权限中原先存储的数据
			for (int i = 0; i < list.size(); i++) {
				Map<?, ?> tempMap = (Map<?, ?>)list.get(i);
				needMap.put((String)tempMap.get(f_bhzd), (String)tempMap.get(mczd));
				
				String bhzd = (String)tempMap.get(f_bhzd);//得到主键编码
				newRoleBhList.add(bhzd);//容器存放最新的编号集合
			}
			
			
			if(postsj.size()<1){
			//	List<?> list = esPostSjqxManageService.getcolumnsBytableName(columns, tableName);//[{F_ROLEBH=0000}, {F_ROLEBH=0001}]
				for (int i = 0; i < list.size(); i++) {
					Map<?, ?> map = (Map<?, ?>)list.get(i);
					String bhzd = (String)map.get(f_bhzd);//得到主键编码
					String mc = "";
					if(!mczd.equals("") && mczd!=null){
						 mc = (String)map.get(mczd);//得到名称字段
					}
					ESUserPostSjqxManage usjObj = new ESUserPostSjqxManage();
						usjObj.setF_gwguid(f_gwguid);
						usjObj.setF_qxbz(f_qxbz);
						usjObj.setF_qxbh(f_qxbh);
						usjObj.setF_sjbh(bhzd);
						usjObj.setF_sjmc(mc);
						classifysjqxlist.add(i, usjObj);
				}
				returnObject.setStatus("1");
			}else{
				//赋值数据名称
				for (int i = 0; i < postsj.size(); i++) {
					postsj.get(i).setF_sjmc((String) needMap.get(postsj.get(i).getF_sjbh()));
				}
				//如果操作数据表数据大小未改变
				if(postsj.size() == list.size()){
					classifysjqxlist = postsj;
				}else{
					///List<ESUserRoleSjqxManage> finalRoleList = new ArrayList<ESUserRoleSjqxManage>();
					List<String> compareAddRoleList = new ArrayList<String>();
					for (int i = 0; i < postsj.size(); i++) {
						oldRoleBhList.add(postsj.get(i).getF_sjbh());
					}	
					compareAddRoleList = ListCompare.getAddaListThanbList(newRoleBhList,oldRoleBhList);//相对于初始字典list新增
					for (int i = 0; i < compareAddRoleList.size(); i++) {
						ESUserPostSjqxManage addPsjObj = new ESUserPostSjqxManage();
						addPsjObj.setF_gwguid(f_gwguid);
						addPsjObj.setF_qxbz(f_qxbz);
						addPsjObj.setF_qxbh(f_qxbh);
						addPsjObj.setF_sjbh(compareAddRoleList.get(i));
						addPsjObj.setF_sjmc((String) needMap.get(compareAddRoleList.get(i)));
						postsj.add(addPsjObj);
					}
					classifysjqxlist = postsj;
				}
				returnObject.setStatus("1");
			}
			returnObject.setList(classifysjqxlist);
			return returnObject;
		}else{
			returnObject.setList(null);
			returnObject.setStatus("0");
			returnObject.setMsg("未启用岗位数据权限表！");
			return returnObject;
		}
	}

	/**
	 * @Description 添加/更新岗位数据权限
	 * @return
	 */
	@RequestMapping(value = "post_sjqx_add", method = RequestMethod.POST)
	@ResponseBody                          
	public ISSPReturnObject addPostSjqx(@RequestBody List<ESUserPostSjqxManage> esuserPostsjqxManage) {
		log.info("#ESUserPostSjqxManageController添加岗位数据权限信息");
		
		//boolean isDelSc = esPostSjqxManageService.delUserPostSjqx(esUserPostSjqxManage);
		/*if(isDelSc == false){
			log.info("#ESUserPostSjqxManageController清空当前岗位下数据权限信息失败");	
			}*/
		//boolean isAddSucc = esPostSjqxManageService.addUserPostSjqx(esUserPostSjqxManage);
		//ISSPReturnObject returnObject = new ISSPReturnObject();
		
		/*ESUserPostSjqxManage userpostsjqx = new ESUserPostSjqxManage();
		userpostsjqx.setF_gwguid(esuserPostsjqxManage.get(0).getF_gwguid());
		userpostsjqx.setF_qxbh(esuserPostsjqxManage.get(0).getF_qxbh());*/
		
		String f_gwguid = esuserPostsjqxManage.get(0).getF_gwguid();
		String f_qxbh = esuserPostsjqxManage.get(0).getF_qxbh();
		
		
		String classifysjqxTb = qxConfig.getClassifySjqxb();//默认表
		String newsjqxtab = ESUserPostSjqxManageController.getPostqxb();//读取过来的表
		String sjqxtb = "";
		if(newsjqxtab !="" && !newsjqxtab.equals(classifysjqxTb)){
			sjqxtb = newsjqxtab;
		}else{
			sjqxtb = classifysjqxTb;
		}
		boolean isDelSc = esPostSjqxManageService.delUserPostSjqx(f_gwguid, f_qxbh, sjqxtb);
		if(isDelSc == false){
			log.info("#UserRoleController清空当前角色下数据权限信息失败");	
		}
		
		boolean isAddSucc = esPostSjqxManageService.addUserPostSjqx(esuserPostsjqxManage,sjqxtb);
		ISSPReturnObject returnObject = new ISSPReturnObject();
		
		
		
		if (isAddSucc) {
			returnObject.setStatus("1");
			returnObject.setMsg("更新岗位数据权限成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("更新岗位数据权限失败");
		}
		return returnObject;
	}
	
}
