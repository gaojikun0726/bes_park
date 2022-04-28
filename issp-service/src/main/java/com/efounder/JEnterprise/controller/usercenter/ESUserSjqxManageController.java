package com.efounder.JEnterprise.controller.usercenter;

import com.core.common.ISSPReturnObject;
import com.core.common.util.ListCompare;
import com.core.config.qxpz.QxConfig;
import com.efounder.JEnterprise.model.usercenter.ESSjqx;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.efounder.JEnterprise.model.usercenter.ESUserSjqxManage;
import com.efounder.JEnterprise.service.usercenter.ESSjqxService;
import com.efounder.JEnterprise.service.usercenter.ESUserService;
import com.efounder.JEnterprise.service.usercenter.ESUserSjqxManageService;
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
 * @Description 用户数据权限
 * @author gongfanfei
 */
@RequestMapping(value = "/view/usersjqx/")
@Controller
public class ESUserSjqxManageController {

	private static final Logger log = LoggerFactory.getLogger(ESUserSjqxManageController.class);

	@Autowired
	private ESUserSjqxManageService esUserSjqxManageService;
	@Autowired
	private ESSjqxService esSjqxService;
	@Autowired
	private ESUserService esUserService;
	@Autowired
	private QxConfig qxConfig;
	
	
	private static String userqxb ;
	
	public static String getUserqxb() {
		return userqxb;
	}

	public static void setUserqxb(String userqxb) {
		ESUserSjqxManageController.userqxb = userqxb;
	}

	/**
	 * @Description 初始加载用户数据权限主页面
	 * @return
	 */
	@RequestMapping(value = "user_sjqx", method = RequestMethod.GET)
	public String getUserSjqxManagePage() {
		log.info("#ESUserSjqxManageController初始加载用户数据权限主页面");
		return "/isspview/usermanage/dataauthoritymanage/usersjqx";
	}
	
	/**
	 * @Description 用户列表
	 */
	@RequestMapping(value = "/user_list", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject loadUserlist(String keywords) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESUser> list = new ArrayList<ESUser>();
		list = esUserService.findUserBykeywords(keywords);
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
		log.info("#ESUserSjqxManageController 根据关键字搜索数据权限对象");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESSjqx> list = new ArrayList<ESSjqx>();
		list = esSjqxService.getSjqxListByKey(keywords);
		returnObject.setList(list);
		return returnObject;
    }
	 /**
	  * @Description 用户数据权限列表
	  */
	@RequestMapping(value = "/usersjqx_list",method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject loadUserSjqxlist(String f_yhbh,String f_qxbh,String f_tabn,String f_bhzd,String f_qxbz,String currentsjqxtb,String mczd) {
		
		ISSPReturnObject returnObject = new ISSPReturnObject();
		if(currentsjqxtb !="undefined" && currentsjqxtb != "" && currentsjqxtb != null ){
			ESUserSjqxManageController.setUserqxb(currentsjqxtb);
			String usersjqxTb = qxConfig.getUserSjqxb();
			if(!usersjqxTb.equals(currentsjqxtb)){
				if(currentsjqxtb!=""){
					usersjqxTb = currentsjqxtb;
				}
			}
			String columns = f_bhzd;//主键字段
			String tableName = f_tabn;//表名
			List<ESUserSjqxManage> usersjqxlist = new ArrayList<ESUserSjqxManage>();
			
			List<ESUserSjqxManage> usersj = esUserSjqxManageService.findUserSjqxList(f_yhbh, f_qxbh, columns, tableName,usersjqxTb);
			String col = f_bhzd;
			if(!mczd.equals("") && mczd!=null){
				col = col + ","+mczd;
			}
			List<?> list = esUserSjqxManageService.getcolumnsBytableName(col, tableName);
			//封装needMap容器，盛放id-name键值对
					Map<String, String> needMap = new HashMap<String, String>();
					List<String> newUserBhList = new ArrayList<String>();//容器存放最新的编号集合--最新的数据
					List<String> oldUserBhList = new ArrayList<String>();//容器存放最新的编号集合--角色数据权限中原先存储的数据
					for (int i = 0; i < list.size(); i++) {
						Map<?, ?> tempMap = (Map<?, ?>)list.get(i);
						needMap.put((String)tempMap.get(f_bhzd), (String)tempMap.get(mczd));
						
						String bhzd = (String)tempMap.get(f_bhzd);//得到主键编码
						newUserBhList.add(bhzd);//容器存放最新的编号集合
					}
			
			if(usersj.size()<1){
				for (int i = 0; i < list.size(); i++) {
					Map<?, ?> map = (Map<?, ?>)list.get(i);
					String bhzd = (String)map.get(f_bhzd);//得到主键编码
					String mc = "";
					if(!mczd.equals("") && mczd!=null){
						 mc = (String)map.get(mczd);//得到名称字段
					}
					ESUserSjqxManage usjObj = new ESUserSjqxManage();
						usjObj.setF_yhbh(f_yhbh);
						usjObj.setF_qxbz(f_qxbz);
						usjObj.setF_qxbh(f_qxbh);
						usjObj.setF_sjbh(bhzd);
						usjObj.setF_sjmc(mc);
						usersjqxlist.add(i, usjObj);
				}
				returnObject.setStatus("1");
			}else{
				//赋值数据名称
				for (int i = 0; i < usersj.size(); i++) {
					usersj.get(i).setF_sjmc((String) needMap.get(usersj.get(i).getF_sjbh()));
				}
				//如果操作数据表数据大小未改变  数据权限数据usersj长度 等于 最新数据list长度
				if(usersj.size() == list.size()){
					usersjqxlist = usersj;
				//数据权限数据usersj长度 小于 最新数据list长度
				}else if(usersj.size() < list.size()){
					
					List<String> compareAddRoleList = new ArrayList<String>();
					for (int i = 0; i < usersj.size(); i++) {
						oldUserBhList.add(usersj.get(i).getF_sjbh());
					}	
					compareAddRoleList = ListCompare.getAddaListThanbList(newUserBhList,oldUserBhList);//相对于初始字典list新增
					for (int i = 0; i < compareAddRoleList.size(); i++) {
						ESUserSjqxManage addUsjObj = new ESUserSjqxManage();
						addUsjObj.setF_yhbh(f_yhbh);
						addUsjObj.setF_qxbz(f_qxbz);
						addUsjObj.setF_qxbh(f_qxbh);
						addUsjObj.setF_sjbh(compareAddRoleList.get(i));
						addUsjObj.setF_sjmc((String) needMap.get(compareAddRoleList.get(i)));
						usersj.add(addUsjObj);
					}
					usersjqxlist = usersj;
				//数据权限数据usersj长度 大于  最新数据list长度
				}else {
					List<String> compareReduceUserList = new ArrayList<String>();
					//Map<String, String> sjqxMap = new HashMap<String, String>();
					for (int i = 0; i < usersj.size(); i++) {
						oldUserBhList.add(usersj.get(i).getF_sjbh());
					}	
					compareReduceUserList = ListCompare.getReduceaListThanbList(newUserBhList,oldUserBhList);//相对于初始字典list减少
					//删除多余的权限数据   保持同步
					esUserSjqxManageService.delPartUserSjqx(compareReduceUserList, f_yhbh, f_qxbh, currentsjqxtb);
					//查询最新同步的权限数据
					List<ESUserSjqxManage> newusersj = esUserSjqxManageService.findUserSjqxList(f_yhbh, f_qxbh, columns, tableName,usersjqxTb);
					
					usersjqxlist = newusersj;
				}
				returnObject.setStatus("1");
			}
			returnObject.setList(usersjqxlist);
			return returnObject;
		}else{
			returnObject.setList(null);
			returnObject.setStatus("0");
			returnObject.setMsg("未启用角色数据权限表！");
			return returnObject;
		}
	}

//	usersjqxlist = usersj;
	/**
	 * @Description 添加/更新用户数据权限
	 * @return
	 */
	@RequestMapping(value = "user_sjqx_add", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject addUserSjqx(@RequestBody List<ESUserSjqxManage> esusersjqxmanage) {
		
		log.info("#ESUserSjqxManageController添加用户数据权限信息");
		
		String f_yhbh = esusersjqxmanage.get(0).getF_yhbh();
		String f_qxbh = esusersjqxmanage.get(0).getF_qxbh();
		/*ESUserSjqxManage usersjqx = new ESUserSjqxManage();
		usersjqx.setF_yhbh(esusersjqxmanage.get(0).getF_yhbh());
		usersjqx.setF_qxbh(esusersjqxmanage.get(0).getF_qxbh());*/
		String newsjqxtab = ESUserSjqxManageController.getUserqxb();//读取过来的表
		String initsjqxTb = qxConfig.getUserSjqxb();//默认表
		String sjqxtb = "";
		if(newsjqxtab !="" && !newsjqxtab.equals(initsjqxTb)){
			sjqxtb = newsjqxtab;
		}else{
			sjqxtb = initsjqxTb;
		}
		boolean isDelSc = esUserSjqxManageService.delUserSjqx(f_yhbh, f_qxbh, sjqxtb);
		if(isDelSc == false){
			log.info("#ESUserSjqxManageController清空当前用户下数据权限信息失败");	
		}
		boolean isAddSucc = esUserSjqxManageService.addUserSjqx(esusersjqxmanage,sjqxtb);
		ISSPReturnObject returnObject = new ISSPReturnObject();
		if (isAddSucc) {
			returnObject.setStatus("1");
			returnObject.setMsg("更新用户数据权限成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("更新用户数据权限失败");
		}
		return returnObject;
	}
}
