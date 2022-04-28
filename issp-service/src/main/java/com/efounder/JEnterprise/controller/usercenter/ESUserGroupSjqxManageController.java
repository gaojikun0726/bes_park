package com.efounder.JEnterprise.controller.usercenter;

import com.core.common.ISSPReturnObject;
import com.core.common.util.ListCompare;
import com.core.config.qxpz.QxConfig;
import com.efounder.JEnterprise.model.usercenter.ESSjqx;
import com.efounder.JEnterprise.model.usercenter.ESUserGroup;
import com.efounder.JEnterprise.model.usercenter.ESUserGroupSjqxManage;
import com.efounder.JEnterprise.service.usercenter.ESSjqxService;
import com.efounder.JEnterprise.service.usercenter.ESUserGroupService;
import com.efounder.JEnterprise.service.usercenter.ESUserGroupSjqxManageService;
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
 * @Description 用户组数据权限
 * @author gongfanfei
 */
@RequestMapping(value = "/view/groupsjqx/")
@Controller
public class ESUserGroupSjqxManageController {

	private static final Logger log = LoggerFactory.getLogger(ESUserGroupSjqxManageController.class);

	@Autowired
	private ESUserGroupSjqxManageService esUserGroupSjqxManageService;
	@Autowired
	private ESSjqxService esSjqxService;
	@Autowired
	private ESUserGroupService esUserGroupService;
	
	@Autowired
	private QxConfig qxConfig;
	private static String groupqxb ;
	
	public static void setGroupqxb(String groupqxb) {
		ESUserGroupSjqxManageController.groupqxb = groupqxb;
	}
	private static String getGroupqxb() {
		return groupqxb;
	}
	/**
	 * 初始化加载用户数据权限主页面
	 * @return
	 */
	@RequestMapping(value = "group_sjqx", method = RequestMethod.GET)
	public String getUserSjqxManagePage() {
		log.info("#ESUserGroupSjqxManageController初始化加载用户数据权限主页面");
		return "/isspview/usermanage/dataauthoritymanage/groupsjqx";
	}
	
	/**
	 * @Description 用户组列表
	 */
	@RequestMapping(value = "/group_list", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject loadUserlist(String keywords) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESUserGroup> list = new ArrayList<ESUserGroup>();
		list = esUserGroupService.getUserGroupList(keywords);
		returnObject.setList(list);
		return returnObject;
    }
	 /**
	  * @Description 数据权限字典列表
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
		log.info("#ESUserGroupSjqxManageController 根据关键字搜索数据权限对象");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESSjqx> list = new ArrayList<ESSjqx>();
		list = esSjqxService.getSjqxListByKey(keywords);
		returnObject.setList(list);
		return returnObject;
    }
	 /**
	  * @Description 用户组数据权限列表
	  */
	@RequestMapping(value = "/groupsjqx_list",method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject loadUserSjqxlist(String f_zbh,String f_qxbh,String f_tabn,String f_bhzd,String f_qxbz,String currentsjqxtb,String mczd) {
		
		ISSPReturnObject returnObject = new ISSPReturnObject();
		if(currentsjqxtb !="undefined" && currentsjqxtb != "" && currentsjqxtb != null ){
			ESUserGroupSjqxManageController.setGroupqxb(currentsjqxtb);
			String classifysjqxTb = qxConfig.getClassifySjqxb();
			if(!classifysjqxTb.equals(currentsjqxtb)){
				returnObject.setList(null);
				returnObject.setStatus("0");
				returnObject.setMsg("未启用用户组数据权限表！");
				return returnObject;
				/*if(currentsjqxtb!=""){
					
					classifysjqxTb = currentsjqxtb;
				}*/
			}
			String columns = f_bhzd;//主键字段
			String tableName = f_tabn;//表名
			List<ESUserGroupSjqxManage> classifysjqxlist = new ArrayList<ESUserGroupSjqxManage>();
			
			List<ESUserGroupSjqxManage> groupsj = esUserGroupSjqxManageService.findUserGroupSjqxById(f_zbh, f_qxbh, columns, tableName,classifysjqxTb);
			
			String col = f_bhzd;
			if(!mczd.equals("") && mczd!=null){
				col = col + ","+mczd;
			}
			List<?> list = esUserGroupSjqxManageService.getcolumnsBytableName(col, tableName);
			//封装needMap容器，盛放id-name键值对
			Map<String, String> needMap = new HashMap<String, String>();
			List<String> newGroupBhList = new ArrayList<String>();//容器存放最新的编号集合--最新的数据
			List<String> oldGroupBhList = new ArrayList<String>();//容器存放最新的编号集合--角色数据权限中原先存储的数据
			
			for (int i = 0; i < list.size(); i++) {
				Map<?, ?> map = (Map<?, ?>)list.get(i);
				needMap.put((String)map.get(f_bhzd), (String)map.get(mczd));
				String bhzd = (String)map.get(f_bhzd);//得到主键编码
				newGroupBhList.add(bhzd);
			}
			
			if(groupsj.size()<1){                           
				//List<?> list = esUserGroupSjqxManageService.getcolumnsBytableName(columns, tableName);//[{F_ROLEBH=0000}, {F_ROLEBH=0001}]
				for (int i = 0; i < list.size(); i++) {
					Map<?, ?> map = (Map<?, ?>)list.get(i);
					String bhzd = (String)map.get(f_bhzd);//得到主键编码
					String mc = "";
					if(!mczd.equals("") && mczd!=null){
						 mc = (String)map.get(mczd);//得到名称字段
					}
					ESUserGroupSjqxManage usjObj = new ESUserGroupSjqxManage();
						usjObj.setF_zbh(f_zbh);
						usjObj.setF_qxbz(f_qxbz);
						usjObj.setF_qxbh(f_qxbh);
						usjObj.setF_sjbh(bhzd);
						usjObj.setF_sjmc(mc);
						classifysjqxlist.add(i, usjObj);
				}
				returnObject.setStatus("1");
			}else{
				//赋值数据名称
				for (int i = 0; i < groupsj.size(); i++) {
					groupsj.get(i).setF_sjmc((String) needMap.get(groupsj.get(i).getF_sjbh()));
				}
				//如果操作数据表数据大小未改变
				if(groupsj.size() == list.size()){
					classifysjqxlist = groupsj;
				}else{
					///List<ESUserRoleSjqxManage> finalRoleList = new ArrayList<ESUserRoleSjqxManage>();
					List<String> compareAddRoleList = new ArrayList<String>();
					for (int i = 0; i < groupsj.size(); i++) {
						oldGroupBhList.add(groupsj.get(i).getF_sjbh());
					}	
					compareAddRoleList = ListCompare.getAddaListThanbList(newGroupBhList,oldGroupBhList);//相对于初始字典list新增
					for (int i = 0; i < compareAddRoleList.size(); i++) {
						ESUserGroupSjqxManage addGsjObj = new ESUserGroupSjqxManage();
						addGsjObj.setF_zbh(f_zbh);
						addGsjObj.setF_qxbz(f_qxbz);
						addGsjObj.setF_qxbh(f_qxbh);
						addGsjObj.setF_sjbh(compareAddRoleList.get(i));
						addGsjObj.setF_sjmc((String) needMap.get(compareAddRoleList.get(i)));
						groupsj.add(addGsjObj);
					}
					classifysjqxlist = groupsj;
				}
			}
			returnObject.setStatus("1");
			returnObject.setList(classifysjqxlist);
			return returnObject;
		}else{
			returnObject.setList(null);
			returnObject.setStatus("0");
			returnObject.setMsg("未启用用户组数据权限表！");
			return returnObject;
		}
	}
//	classifysjqxlist = groupsj;

	/**
	 * @Description 添加/更新用户组数据权限
	 * @return
	 */
	@RequestMapping(value = "group_sjqx_add", method = RequestMethod.POST)
	@ResponseBody                       
	public ISSPReturnObject addUserSjqx(@RequestBody List<ESUserGroupSjqxManage> esuserGroupsjqxmanage) {
		log.info("#UserGroupSjqxManageController添加用户组数据权限信息");
		
		/*ESUserGroupSjqxManage usergroupsjqx = new ESUserGroupSjqxManage();
		usergroupsjqx.setF_zbh(esuserGroupsjqxmanage.get(0).getF_zbh());
		usergroupsjqx.setF_qxbh(esuserGroupsjqxmanage.get(0).getF_qxbh());*/
		
		String f_zbh =  esuserGroupsjqxmanage.get(0).getF_zbh();
		String f_qxbh = esuserGroupsjqxmanage.get(0).getF_qxbh();
		
		String classifysjqxTb = qxConfig.getClassifySjqxb();//默认表
		String newsjqxtab = ESUserGroupSjqxManageController.getGroupqxb();//读取过来的表
		String sjqxtb = "";
		if(newsjqxtab !="" && !newsjqxtab.equals(classifysjqxTb)){
			sjqxtb = newsjqxtab;
		}else{
			sjqxtb = classifysjqxTb;
		}
		boolean isDelSc = esUserGroupSjqxManageService.delUserGroupSjqx(f_zbh, f_qxbh,sjqxtb);
		if(isDelSc == false){
			log.info("#UserGroupSjqxManageController清空当前用户组下数据权限信息失败");	
		}							
		boolean isAddSucc = esUserGroupSjqxManageService.addUserGroupSjqx(esuserGroupsjqxmanage,sjqxtb);
		ISSPReturnObject returnObject = new ISSPReturnObject();
		if (isAddSucc) {
			returnObject.setStatus("1");
			returnObject.setMsg("更新用户组数据权限成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("更新用户组数据权限失败");
		}
		return returnObject;
	}
    
    
}
