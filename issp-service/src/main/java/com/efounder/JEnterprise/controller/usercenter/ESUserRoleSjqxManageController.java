package com.efounder.JEnterprise.controller.usercenter;

import com.core.common.ISSPReturnObject;
import com.core.common.util.ListCompare;
import com.core.config.qxpz.QxConfig;
import com.efounder.JEnterprise.model.usercenter.ESRole;
import com.efounder.JEnterprise.model.usercenter.ESSjqx;
import com.efounder.JEnterprise.model.usercenter.ESUserRoleSjqxManage;
import com.efounder.JEnterprise.service.usercenter.ESRoleService;
import com.efounder.JEnterprise.service.usercenter.ESRoleSjqxManageService;
import com.efounder.JEnterprise.service.usercenter.ESSjqxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 角色数据权限
 * @author gongfanfei
 */
@RequestMapping(value = "/view/rolesjqx/")
@Controller
public class ESUserRoleSjqxManageController {

	private static final Logger log = LoggerFactory.getLogger(ESUserRoleSjqxManageController.class);

	@Autowired
	private ESRoleSjqxManageService esRoleSjqxManageService;
	@Autowired
	private ESSjqxService esSjqxService;
	@Autowired
	private ESRoleService esRoleService;
	@Autowired
	private QxConfig qxConfig;
	private static String roleqxb ;
	
	
	public static String getRoleqxb() {
		return roleqxb;
	}

	public static void setRoleqxb(String roleqxb) {
		ESUserRoleSjqxManageController.roleqxb = roleqxb;
	}

	/**
	 * @Description 获取所有角色数据权限
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "role_sjqx", method = RequestMethod.GET)
	public String getUserSjqxManagePage(ModelMap model) {
		log.info("#ESUserRoleSjqxManageController获取角色数据权限信息");
		return "/isspview/usermanage/dataauthoritymanage/rolesjqx";
	}
	
	/**
	 * @Description 角色列表
	 */
	@RequestMapping(value = "/role_list", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject loadUserlist(String keywords) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESRole> list = new ArrayList<ESRole>();
		list = esRoleService.getUserRoleList(keywords);
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
		log.info("#ESUserRoleSjqxManageController 根据关键字搜索数据权限对象");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESSjqx> list = new ArrayList<ESSjqx>();
		list = esSjqxService.getSjqxListByKey(keywords);
		returnObject.setList(list);
		return returnObject;
    }
	 /**
	  * @Description 角色数据权限列表
	  */
	@RequestMapping(value = "/rolesjqx_list",method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject loadUserSjqxlist(String f_roleguid,String f_qxbh,String f_tabn,String f_bhzd,String f_qxbz,String currentsjqxtb,String mczd) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		if(currentsjqxtb !="undefined" && currentsjqxtb != "" && currentsjqxtb != null ){
			ESUserRoleSjqxManageController.setRoleqxb(currentsjqxtb);
			String classifysjqxTb = qxConfig.getClassifySjqxb();
			if(!classifysjqxTb.equals(currentsjqxtb)){
				if(currentsjqxtb!=""){
					classifysjqxTb = currentsjqxtb;
				}
			}
			String columns = f_bhzd;//主键字段
			String tableName = f_tabn;//表名
			List<ESUserRoleSjqxManage> classifysjqxlist = new ArrayList<ESUserRoleSjqxManage>();
			
			List<ESUserRoleSjqxManage> rolesj = esRoleSjqxManageService.findUserRoleSjqxById(f_roleguid, f_qxbh, columns, tableName,classifysjqxTb);
			String col = f_bhzd;
			if(!mczd.equals("") && mczd!=null){
				col = col + ","+mczd;
			}
			List<?> list = esRoleSjqxManageService.getcolumnsBytableName(col, tableName);
			
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
			if(rolesj.size()<1){
				for (int i = 0; i < list.size(); i++) {
					Map<?, ?> map = (Map<?, ?>)list.get(i);
					String bhzd = (String)map.get(f_bhzd);//得到主键编码
					//String mc = (String)map.get(mczd);//得到名称字段
					String mc = "";
					if(!mczd.equals("") && mczd!=null){
						 mc = (String)map.get(mczd);//得到名称字段
					}
					ESUserRoleSjqxManage usjObj = new ESUserRoleSjqxManage();
						usjObj.setF_roleguid(f_roleguid);
						usjObj.setF_qxbz(f_qxbz);
						usjObj.setF_qxbh(f_qxbh);
						usjObj.setF_sjbh(bhzd);
						usjObj.setF_sjmc(mc);
						classifysjqxlist.add(i, usjObj);
				}
				returnObject.setStatus("1");
			}else{
				//赋值数据名称
				for (int i = 0; i < rolesj.size(); i++) {
					rolesj.get(i).setF_sjmc((String) needMap.get(rolesj.get(i).getF_sjbh()));
				}
				//如果操作数据表数据大小未改变
				if(rolesj.size() == list.size()){
					classifysjqxlist = rolesj;
				}else{
					///List<ESUserRoleSjqxManage> finalRoleList = new ArrayList<ESUserRoleSjqxManage>();
					List<String> compareAddRoleList = new ArrayList<String>();
					for (int i = 0; i < rolesj.size(); i++) {
						oldRoleBhList.add(rolesj.get(i).getF_sjbh());
					}	
					compareAddRoleList = ListCompare.getAddaListThanbList(newRoleBhList,oldRoleBhList);//相对于初始字典list新增
					for (int i = 0; i < compareAddRoleList.size(); i++) {
						ESUserRoleSjqxManage addRsjObj = new ESUserRoleSjqxManage();
						addRsjObj.setF_roleguid(f_roleguid);
						addRsjObj.setF_qxbz(f_qxbz);
						addRsjObj.setF_qxbh(f_qxbh);
						addRsjObj.setF_sjbh(compareAddRoleList.get(i));
						addRsjObj.setF_sjmc((String) needMap.get(compareAddRoleList.get(i)));
						rolesj.add(addRsjObj);
					}
					classifysjqxlist = rolesj;
				}
				returnObject.setStatus("1");
			}
			returnObject.setList(classifysjqxlist);
			return returnObject;
		}else{
			returnObject.setList(null);
			returnObject.setStatus("0");
			returnObject.setMsg("未启用角色数据权限表！");
			return returnObject;
		}
	}

	/**
	 * @Description 添加/更新角色数据权限
	 * @return
	 */
	@RequestMapping(value = "role_sjqx_add", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject addUserSjqx(@RequestBody List<ESUserRoleSjqxManage> esuserRolesjqxmanage) {
		/*ESUserRoleSjqxManage userrolesjqx = new ESUserRoleSjqxManage();
		userrolesjqx.setF_roleguid(esuserRolesjqxmanage.get(0).getF_roleguid());
		userrolesjqx.setF_qxbh(esuserRolesjqxmanage.get(0).getF_qxbh());*/
		String f_roleguid = esuserRolesjqxmanage.get(0).getF_roleguid();
		String f_qxbh = esuserRolesjqxmanage.get(0).getF_qxbh();
		
		String classifysjqxTb = qxConfig.getClassifySjqxb();//默认表
		String newsjqxtab = ESUserRoleSjqxManageController.getRoleqxb();//读取过来的表
		String sjqxtb = "";
		if(newsjqxtab !="" && !newsjqxtab.equals(classifysjqxTb)){
			sjqxtb = newsjqxtab;
		}else{
			sjqxtb = classifysjqxTb;
		}
		
		boolean isDelSc = esRoleSjqxManageService.delUserRoleSjqx(f_roleguid, f_qxbh, sjqxtb);
		if(isDelSc == false){
			log.info("#UserRoleController清空当前角色下数据权限信息失败");	
		}
		boolean isAddSucc = esRoleSjqxManageService.addUserRoleSjqx(esuserRolesjqxmanage,sjqxtb);
		ISSPReturnObject returnObject = new ISSPReturnObject();
		if (isAddSucc) {
			returnObject.setStatus("1");
			returnObject.setMsg("更新角色数据权限成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("更新角色数据权限失败");
		}
		return returnObject;
	}
    
    
}
