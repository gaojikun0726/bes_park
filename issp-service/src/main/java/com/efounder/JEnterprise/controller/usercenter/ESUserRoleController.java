package com.efounder.JEnterprise.controller.usercenter;

import com.core.common.ISSPReturnObject;
import com.core.config.db.OperationConfig;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.model.usercenter.ESRole;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.efounder.JEnterprise.model.usercenter.UserRoleRlgl;
import com.efounder.JEnterprise.service.usercenter.ESRoleService;
import com.efounder.JEnterprise.service.usercenter.ESUserService;
import com.efounder.JEnterprise.service.usercenter.UserRoleRlglService;
import com.framework.common.utils.OperationLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description 角色自定义
 * @author gongfanfei
 */
@RequestMapping(value = "/view/userrole/")
@Controller
public class ESUserRoleController {

	private static final Logger log = LoggerFactory.getLogger(ESUserRoleController.class);

	@Autowired
	private ESRoleService esRoleService;
	@Autowired
	private UserRoleRlglService userRoleRlglService;
	@Autowired
	private ESUserService esUserService;
	@Autowired
	private OperationConfig operationConfig;
	/**
	 * @Description 初始化到角色自定义主界面
	 */
	@RequestMapping(value = "user_role", method = RequestMethod.GET)
	public String getUserRoles() {
		log.info("#UserRoleController获取用户角色信息");
		return "/isspview/usermanage/user_role";
	}
	
	/**
	 * @Description 加载所有角色信息
	 * @return
	 */
	@RequestMapping(value = "/loadAllrole", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getRoleList() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESRole> list = new ArrayList<ESRole>();
		list = esRoleService.findRoles();
		returnObject.setList(list);
		return returnObject;
	}
	/**
	 * @Description 搜索角色
	 */
	@RequestMapping(value = "/user_role_search", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getUserGroupList(String keywords) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESRole> list = new ArrayList<ESRole>();
		list = esRoleService.getUserRoleList(keywords);
		returnObject.setList(list);
		return returnObject;
    }
	 /**
	  * @Description ajax加载编辑对象
	  * @author gongfanfei
	  * @param f_zbh
	  * @return
	  */
	@RequestMapping(value = "/loadeditobj",method = RequestMethod.POST)
	@ResponseBody
	public ESRole loadedit(@RequestBody ESRole esrole) {
		log.info("# ajax加载编辑角色对象");
		ESRole esroledata = esRoleService.findRoleById(esrole.getF_guid());
		return esroledata;
	}

	/**
	 * @Description 添加用户角色
	 * @return
	 */
	@RequestMapping(value = "user_role_add", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject addUserRole(@RequestBody ESRole role) {
		log.info("#ESUserRoleController添加角色信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			//设定四位数字，不够位数，自动补“0”
			DecimalFormat df=new DecimalFormat("0000");
			String f_guid =  UUIDUtil.getRandom32BeginTimePK();
			role.setF_guid(f_guid);
			List<ESRole> rolelist = esRoleService.findRoles();
			int roleLen = rolelist.size();
			if(roleLen==0){
				role.setF_rolebh("0000");
			}else{
				String maxBmbh = esRoleService.findMaxBmbh();
				Integer cuRolebh = (Integer.parseInt(maxBmbh)+1);
				String  currentRolebh = df.format(cuRolebh);
				role.setF_rolebh(currentRolebh);
			}
			boolean isSucc = esRoleService.addRole(role);
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				OperationLog.insert(role.getF_guid(), "es_role");
			}
			if (isSucc) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				returnObject.setStatus("1");
				returnObject.setMsg("添加角色成功");
				role.setF_chdate(sdf.format(new Date()));
				role.setF_crdate(sdf.format(new Date()));
				returnObject.setData(role);
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("添加角色失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}

	/**
	 * @Description 删除角色信息
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "user_role_del", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject delUserRoles(@RequestBody ESRole role) {
		log.info("#ESUserRoleControllers删除角色信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				OperationLog.delete(role.getF_guid(), "es_role");
			}
			boolean isSucc = esRoleService.delRole(role);
			if (isSucc) {
				returnObject.setStatus("1");
				returnObject.setMsg("删除角色成功");
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("删除角色失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}

	/**
     * @Description ajax更新角色信息
     * @author gongfanfei
     * @param usergroup
     * @return
     */
    @RequestMapping(value = "/userrole_edit", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject edit(ESRole esrole) {
    	ISSPReturnObject returnObject = new ISSPReturnObject();
    	try {
	    	log.info("#ESUserRoleControllers修改角色信息");
	    	boolean flag = true;
	    	if("1".equals(operationConfig.getSysDataBaseUseable())){
	    		Map<String, Object> updatestartMap = OperationLog.updateStart(esrole.getF_guid(), "es_role");
	    		flag = esRoleService.upRole(esrole);
	    		OperationLog.updateEnd(esrole.getF_guid(), "es_role",updatestartMap);
	    	}else{
	    		flag = esRoleService.upRole(esrole);
	    	}
	        if (flag) {
	            returnObject.setStatus("1");
				returnObject.setMsg("角色修改成功");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				esrole.setF_chdate(sdf.format(new Date()));
				returnObject.setData(esrole);
	        } else {
	        	returnObject.setStatus("0");
				returnObject.setMsg("角色修改失败");
	        }
    	} catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
        return returnObject;
    }
    
    //包含用户
    /**
	 * 加载角色关系对应的用户信息
	 * @author gongfanfei
	 * @param f_zbh
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/loadRoleRlglUser", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getUserRoleRlglList(String f_roleguid,String keywords) {
		log.info("# ajax加载编辑角色包含用户");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESUser> EUserlist = new ArrayList<ESUser>();
		List<ESUser> EUserResult = new ArrayList<ESUser>();
		List<ESUser> newEuserL = new ArrayList<ESUser>();
		List<UserRoleRlgl> rlglList = new ArrayList<UserRoleRlgl>();
		rlglList = userRoleRlglService.findChildRlglByRolebh(f_roleguid);
		for (int i = 0; i < rlglList.size(); i++) {
			ESUser user = esUserService.findUserById(rlglList.get(i).getF_yhbh());
			EUserlist.add(i, user);
		}
		if(keywords != null){
			EUserResult = esUserService.findUserBykeywords(keywords);//通过关键字查询到用户列表
			int index = 0;
			for (int i = 0; i < EUserlist.size(); i++) {
				for (int j = 0; j < EUserResult.size(); j++) {
					if(EUserResult.get(j).getF_yhbh().equals(EUserlist.get(i).getF_yhbh())){
						newEuserL.add(index++, EUserResult.get(j));
					}
				}
			}
		}else{
			newEuserL = EUserlist;
		}
		returnObject.setList(EUserlist);
		return returnObject;
	}

	/**
	 * 加载角色关系未包含的用户
	 * @author gongfanfei
	 * @param f_roleguid
	 * @param keywords
	 * @return
	 */
	@RequestMapping(value = "/loadNoIncludeUser", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getNoUserRoleRlglList(String f_roleguid,String keywords) {
		log.info("# ajax加载编辑角色包含用户");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<UserRoleRlgl> rlglList = new ArrayList<UserRoleRlgl>();
		//得到所有用户信息EUserlist
		List<ESUser> EUserlist = new ArrayList<ESUser>();
		EUserlist = esUserService.findUsersBykey(keywords);
		//通过f_zbh得到角色关系中用户rlglList
		rlglList = userRoleRlglService.findChildRlglByRolebh(f_roleguid);
		for (int i = 0; i < rlglList.size(); i++) {
			for (int j = 0; j < EUserlist.size(); j++) {
				if(EUserlist.get(j).getF_yhbh().equals(rlglList.get(i).getF_yhbh())){
					EUserlist.remove(j);
				}
				
			}
		}
		returnObject.setList(EUserlist);
		return returnObject;
	}
	
	 /**
     * @Description ajax添加角色成员信息
     * @author gongfanfei
     * @param usergrouprlgl
     * @return
     */
    @RequestMapping(value = "/userrole_insertuser", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject insertUserToRole(@RequestBody UserRoleRlgl userrolerlgl) {
    	
    	log.info("#ESUserRoleController添加角色成员信息");
    	String f_guid =  UUIDUtil.getRandom32BeginTimePK();
    	userrolerlgl.setF_guid(f_guid);
    	boolean flag = userRoleRlglService.addUserRoleRlgl(userrolerlgl);
    	ISSPReturnObject returnObject = new ISSPReturnObject();
    	ESUser userdata = esUserService.findUserById(userrolerlgl.getF_yhbh());
    	if (flag) {
    		returnObject.setStatus("1");
			returnObject.setMsg("添加成功");
			returnObject.setData(userdata);
    	} else {
    		returnObject.setStatus("0");
			returnObject.setMsg("添加失败");
    	}
    	return returnObject;
    }
    /**
     * @Description ajax删除角色成员信息
     * @author gongfanfei
     * @param usergrouprlgl
     * @return
     */
    @RequestMapping(value = "/userrole_deleteuser", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject deleteUserToRole(@RequestBody UserRoleRlgl userrolerlgl) {
    	
    	log.info("#ESUserRoleController移除角色信息");
    	boolean flag = userRoleRlglService.delUserRoleRlgl(userrolerlgl);
    	ISSPReturnObject returnObject = new ISSPReturnObject();
    	ESUser userdata = esUserService.findUserById(userrolerlgl.getF_yhbh());
    	if (flag) {
    		returnObject.setStatus("1");
			returnObject.setMsg("删除成功");
			returnObject.setData(userdata);
    	} else {
    		returnObject.setStatus("0");
			returnObject.setMsg("删除失败");
    	}
    	return returnObject;
    }
    /**
     * @Description 清空角色成员
     * @Description ajax删除对应角色所有成员信息
     * @author gongfanfei
     * @param usergrouprlgl
     * @return
     */
    @RequestMapping(value = "/userrole_leftmoveuser", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject deleteAllUserToRole(@RequestBody UserRoleRlgl userrolerlgl) {
    	log.info("#ESUserRoleController清空角色信息");
    	boolean flag = userRoleRlglService.leftMoveAllUsers(userrolerlgl);
    	ISSPReturnObject returnObject = new ISSPReturnObject();
    	if (flag) {
    		returnObject.setStatus("1");
			returnObject.setMsg("删除成功");
    	} else {
    		returnObject.setStatus("0");
			returnObject.setMsg("删除失败");
    	}
    	return returnObject;
    }
    /**
     * @Description 添加全部角色成员
     * @Description ajax添加至角色所有成员信息
     * @author gongfanfei
     * @param usergrouprlgl
     * @return
     */
    @RequestMapping(value = "/userrole_rightmoveuser", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject addAllUserToRole(@RequestBody UserRoleRlgl userrolerlgl) {
    	
    	log.info("#ESUserRoleController添加全部角色信息");
    	ISSPReturnObject returnObject = new ISSPReturnObject();
    	List<ESUser> EUserlist = esUserService.findUsers();//查找所有用户
    	List<UserRoleRlgl> rlglList = userRoleRlglService.findChildRlglByRolebh(userrolerlgl.getF_roleguid());//查找角色编号对应所有用户编码信息
    	for (int i = 0; i < rlglList.size(); i++) {
			for (int j = 0; j < EUserlist.size(); j++) {
				if(EUserlist.get(j).getF_yhbh().equals(rlglList.get(i).getF_yhbh())){
					EUserlist.remove(j);
				}
			}
		}
    	for (int i = 0; i < EUserlist.size(); i++) {
    		userrolerlgl.setF_yhbh(EUserlist.get(i).getF_yhbh());
    		String f_guid =  UUIDUtil.getRandom32BeginTimePK();
    		userrolerlgl.setF_guid(f_guid);
    		boolean flag = userRoleRlglService.addUserRoleRlgl(userrolerlgl);
    		if(flag == false){
    			log.info("#ESUserRoleController添加全部角色信息失败");	
    		}
		}
    	returnObject.setList(EUserlist);
    	return returnObject;
    }
    
    
    
    
}
