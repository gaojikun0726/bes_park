package com.efounder.JEnterprise.controller.usercenter;

import com.core.common.ISSPReturnObject;
import com.core.common.tree.ISSPTreeBuilder;
import com.core.common.tree.ISSPTreeNode;
import com.core.config.db.OperationConfig;
import com.efounder.JEnterprise.model.applicationmanage.ESBmjg;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.efounder.JEnterprise.model.usercenter.ESUserGroup;
import com.efounder.JEnterprise.model.usercenter.UserGrouprlgl;
import com.efounder.JEnterprise.service.applicationmanage.ESBmjgService;
import com.efounder.JEnterprise.service.usercenter.ESUserGroupService;
import com.efounder.JEnterprise.service.usercenter.ESUserService;
import com.efounder.JEnterprise.service.usercenter.UserGrouprlglService;
import com.framework.common.utils.OperationLog;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 类名称：UserGroupController
 * 类描述：用户组定义
 * 创建人：gongfanfei
 * 修改人：gongfanfei
 * 修改人：liuhoujun
 * 修改时间：2018年4月26日
 * 修改时间：2018年12月19日
 * @version 1.0.0 
 */
@Controller
@RequestMapping(value = "/view/usergroup")
public class ESUserGroupController {
	private static final Logger log = LoggerFactory.getLogger(ESUserGroupController.class);
	 @Autowired
	    private ESUserGroupService userGroupService;
	 @Autowired
	 	private UserGrouprlglService userGroupRlglService;
	 @Autowired
		private ESUserService esUserService;
	 @Autowired
		private ESBmjgService esBmjgService;
	 @Autowired
		private OperationConfig operationConfig;
	 /** 
	    * @Description 初始化到用户组主界面
	    * @author gongfanfei
	    * @param 未传参
	    * @return  
	    */
	@RequestMapping(value = "/user_group", method = RequestMethod.GET)
	public String getUsergroup() {
         log.info("#ESUserGroupController loding view/user/user_group");
        return "/isspview/usermanage/user_group";
    }
	
	@RequestMapping(value = "/user_group_treegrid", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getUserGroupList(String f_zmc) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESUserGroup> list = new ArrayList<ESUserGroup>();
		list = userGroupService.getUserGroupList(f_zmc);
		for(int i=0;i<list.size();i++){
			if(list.get(i).getF_pzbh()!=null&&!"".equals(list.get(i).getF_pzbh())) {
				list.get(i).setF_pzbh(list.get(i).getF_pzbh());
			}else {
				list.get(i).setF_pzbh(null);
			}
		}
		returnObject.setList(list);
		return returnObject;
    }
	/**
	 * 查找子类
	 * 
	 * @param keywords
	 * @return
	 */
	@RequestMapping(value = "/group_chlildtreegrid", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getChildList(String f_zbh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESUserGroup> list = new ArrayList<ESUserGroup>();
		//list = userGroupService.findChildByZbh_all(f_zbh);
		list = userGroupService.findChildByZbh(f_zbh);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getF_pzbh() == null || "".equals(list.get(i).getF_pzbh())) {
				list.get(i).setF_pzbh(null);
			}
		}
		returnObject.setList(list);
		return returnObject;
	}
	/** 
	    * @Description 分页查询
	    * @author gongfanfei
	    * @param pageNum
	    * @return  
	    */
   @RequestMapping(value = "/user_group_page", method = RequestMethod.POST)
    public String list_page(@RequestParam(value = "f_zmc", required = false) String f_zmc, @RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map) {
        log.info("#分页查询 pageNum={} , f_zmc={}", pageNum, f_zmc);
        PageInfo<ESUserGroup> page = userGroupService.findUserGroupByPage(pageNum, f_zmc);
        map.put("page", page);
        map.put("f_zmc", f_zmc);
        return "view/usergroup/user_group_page";
    }
   
   /**
	 * 生成用户组树
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ugroup_tree", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getTree() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("NodeController获取信息");
		try {
			List<ISSPTreeNode> tree = getTreeJson();// 获得一棵树模型的数据
			returnObject.setList(tree);
			returnObject.setMsg("获取用户组树成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取用户组树失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	public List<ISSPTreeNode> getTreeJson() {
		List<ESUserGroup> ugrouplist = userGroupService.loadAll();// 从数据库获取所有资源
		List<ISSPTreeNode> nodes = new ArrayList<ISSPTreeNode>();// 把所有资源转换成树模型的节点集合，此容器用于保存所有节点
		for (ESUserGroup ugroup : ugrouplist) {
			ISSPTreeNode node = new ISSPTreeNode();
			node.setNodeTreeId(ugroup.getF_zbh());
			node.setId(ugroup.getF_zbh());
			node.setPid(ugroup.getF_pzbh());
			node.setText(ugroup.getF_zmc());
			//node.setJs(ugroup.getF_js());
			nodes.add(node);// 添加到节点容器
		}

		List<ISSPTreeNode> buildTree = ISSPTreeBuilder.buildTree(nodes);// Node类里面包含了一个创建树的方法。这个方法就是通过节点的信息（nodes）来构建一颗多叉树manytree->mt。
		return buildTree;
	}
   
   
   
   /** 
     * @Description ajax保存用户组信息
     * @author gongfanfei
     * @param usergroup
     * @return  
     */
   @RequestMapping(value = "/add_usergroup", method = RequestMethod.POST)
   @ResponseBody
   public ISSPReturnObject add(@RequestBody ESUserGroup usergroup) {
	   log.info("#UserGroupController添加用户组信息");
	   ISSPReturnObject returnObject = new ISSPReturnObject();
		// 自动生成功能编码
	   log.info("#ESGnzdController 获取 功能字典表编码结构");
		ESBmjg bmjg = esBmjgService.findBmjg("es_user_group");
		// 级数其实是父节点的级数，所以将级数+1
				int usergroupJs = Integer.parseInt(usergroup.getF_js()) + 1;
				usergroup.setF_js(Integer.toString(usergroupJs));
				if (usergroupJs > bmjg.getBmjg().length()) {
					returnObject.setMsg("新增失败，级数已超过预设编码结构位数!");
					returnObject.setStatus("0");
					return returnObject;
				}
				// 根据级数 从编码结构中获取当前级位数
				String sbm = bmjg.getBmjg().substring(usergroupJs - 1, usergroupJs);
				int ibm = Integer.parseInt(sbm);

				// 获取父节点下所有子节点
				List<ESUserGroup> usergroupList = userGroupService.findChildByZbh(usergroup.getF_pzbh());
				if (usergroupList.size() > 0) {
					// //获取子节点最大编号
					int maxBh = getMaxBh(usergroupList);
					// //编号长度
					int bhLen = usergroup.getF_pzbh().length() + ibm;
					// //生成组织机构编号 补齐编号
					String newBh = String.format("%0" + bhLen + "d", maxBh + 1);
					usergroup.setF_zbh(newBh);
				} else {
					// //生成组织机构编号 补齐编号
					String newBh = usergroup.getF_pzbh() + String.format("%0" + ibm + "d", 1);
					usergroup.setF_zbh(newBh);
				}
				if (usergroup != null) {
					try {
						boolean flag = userGroupService.addUserGroup(usergroup);
						if("1".equals(operationConfig.getSysDataBaseUseable())){
							OperationLog.insert(usergroup.getF_zbh(), "es_user_group");
						}
						if (flag) {
							returnObject.setMsg("添加用户组成功");
							returnObject.setStatus("1");
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							usergroup.setF_chdate(sdf.format(new Date()));
							usergroup.setF_crdate(sdf.format(new Date()));
							returnObject.setData(usergroup);
						} else {
							returnObject.setMsg("添加用户组失败");
							returnObject.setStatus("0");
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return returnObject;
   }
   // 获取最大编号
	private int getMaxBh(List<ESUserGroup> usergroupList) {
		int maxugbh = 0;
		for (int i = 0; i < usergroupList.size(); i++) {
			String sBh = usergroupList.get(i).getF_zbh();
			int iBh = Integer.parseInt(sBh);
			if (maxugbh < iBh) {
				maxugbh = iBh;
			}
		}
		return maxugbh;
	}
   /**
	 * @Description 删除用户组信息
	 * @author gongfanfei
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "user_group_del", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject delUserRoles(@RequestBody ESUserGroup usergroup) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#UserGroupController删除用户组信息");
		try {
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				OperationLog.delete(usergroup.getF_zbh(), "es_user_group");
			}
			boolean flag = userGroupService.delUserGroup(usergroup);

			if (flag) {
				returnObject.setStatus("1");
				returnObject.setMsg("删除成功");
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("删除失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}
	 /** 
       * @Description ajax加载编辑对象
       * @author gongfanfei
       * @param 主键
       * @return  
       */
    @RequestMapping(value = "/load/{f_zbh}", method = RequestMethod.GET)
    public String load(@PathVariable String f_zbh, ModelMap map) {
        log.info("# ajax加载编辑用户组对象");
        ESUserGroup usergroup = userGroupService.findById(f_zbh);
        map.addAttribute("usergroup", usergroup);
        return "view/usergroup/user_group_edit_form";
    }
    /**
     * @Description ajax更新用户组信息
     * @author gongfanfei
     * @param usergroup
     * @return
     */
    @RequestMapping(value = "/usergroup_edit", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject edit(ESUserGroup usergroup) {
    	  ISSPReturnObject returnObject = new ISSPReturnObject();
    	log.info("#UserGroupController修改用户组信息");
    	Map<String, Object> startMap;
		try {
			boolean flag = true;
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				startMap = OperationLog.updateStart(usergroup.getF_zbh(), "es_user_group");
				flag = userGroupService.editUserGroup(usergroup);
				OperationLog.updateEnd(usergroup.getF_zbh(), "es_user_group", startMap);
			}else{
				flag = userGroupService.editUserGroup(usergroup);
			}
	        if (flag) {
	            returnObject.setStatus("1");
				returnObject.setMsg("用户组修改成功");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				usergroup.setF_chdate(sdf.format(new Date()));
				returnObject.setData(usergroup);
	        } else {
	        	returnObject.setStatus("0");
				returnObject.setMsg("用户组修改失败");
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return returnObject;
    }
    /**
	 * ajax加载编辑对象
	 * @author gongfanfei
	 * @param f_zbh
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/loadeditobj",method = RequestMethod.POST)
	@ResponseBody
	public ESUserGroup loadedit(@RequestBody ESUserGroup usergroup) {
		log.info("# ajax加载编辑用户组对象");
		ESUserGroup usergroupdata = userGroupService.findById(usergroup.getF_zbh());
		return usergroupdata;
	}
	/**
	 * 加载用户组关系对应的用户信息
	 * @author gongfanfei
	 * @param f_zbh
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/loadGroupRlglUser", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getUserGroupRlglList(UserGrouprlgl ugrl,String keywords) {
		log.info("# ajax加载编辑用户组包含用户");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESUser> EUserlist = new ArrayList<ESUser>();
		List<ESUser> EUserResult = new ArrayList<ESUser>();
		List<ESUser> newEuserL = new ArrayList<ESUser>();
		List<UserGrouprlgl> rlglList = new ArrayList<UserGrouprlgl>();
		rlglList = userGroupRlglService.findChildRlglByZbh(ugrl.getF_zbh());
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
		returnObject.setList(newEuserL);
		return returnObject;
	}

	/**
	 * 加载用户组关系未包含的用户
	 * @author gongfanfei
	 * @param f_zbh
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/loadNoIncludeUser", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getNoUserGroupRlglList(UserGrouprlgl ugrl,String keywords) {
		log.info("# ajax加载编辑用户组包含用户");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<UserGrouprlgl> rlglList = new ArrayList<UserGrouprlgl>();
		//得到所有用户信息EUserlist
		List<ESUser> EUserlist = new ArrayList<ESUser>();
		EUserlist = esUserService.findUsersBykey(keywords);
		//通过f_zbh得到用户组关系中用户rlglList
		rlglList = userGroupRlglService.findChildRlglByZbh(ugrl.getF_zbh());
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
     * @Description ajax添加用户组成员信息
     * @author gongfanfei
     * @param usergrouprlgl
     * @return
     */
    @RequestMapping(value = "/usergroup_insertuser", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject insertUserToGroup(UserGrouprlgl usergrouprlgl) {
    	
    	log.info("#UserGroupController添加用户组信息");
    	boolean flag = userGroupRlglService.addUserGroupRlgl(usergrouprlgl);
    	ISSPReturnObject returnObject = new ISSPReturnObject();
    	ESUser userdata = esUserService.findUserById(usergrouprlgl.getF_yhbh());
    	if (flag) {
    		returnObject.setStatus("1");
    		returnObject.setMsg("添加成功");
    		returnObject.setData(userdata);
    	} else {
    		returnObject.setStatus("1");
    		returnObject.setMsg("添加失败");
    	}
    	return returnObject;
    }
    /**
     * @Description ajax删除用户组成员信息
     * @author gongfanfei
     * @param usergrouprlgl
     * @return
     */
    @RequestMapping(value = "/usergroup_deleteuser", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject deleteUserToGroup(@RequestBody UserGrouprlgl usergrouprlgl) {
    	
    	log.info("#UserGroupController移除用户组信息");
    	boolean flag = userGroupRlglService.delUserGroupRlgl(usergrouprlgl);
    	ISSPReturnObject returnObject = new ISSPReturnObject();
    	ESUser userdata = esUserService.findUserById(usergrouprlgl.getF_yhbh());
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
     * @Description 清空用户组成员
     * @Description ajax删除对应用户组所有成员信息
     * @author gongfanfei
     * @param usergrouprlgl
     * @return
     */
    @RequestMapping(value = "/usergroup_leftmoveuser", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject deleteAllUserToGroup(@RequestBody UserGrouprlgl usergrouprlgl) {
    	
    	log.info("#UserGroupController清空用户组信息");
    	boolean flag = userGroupRlglService.leftMoveAllUsers(usergrouprlgl);
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
     * @Description 添加全部用户组成员
     * @Description ajax添加至用户组所有成员信息
     * @author gongfanfei
     * @param usergrouprlgl
     * @return
     */
    @RequestMapping(value = "/usergroup_rightmoveuser", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject addAllUserToGroup(@RequestBody UserGrouprlgl usergrouprlgl) {
    	log.info("#UserGroupController添加全部用户组信息");
    	ISSPReturnObject returnObject = new ISSPReturnObject();
    	List<ESUser> EUserlist = esUserService.findUsers();//查找所有用户
    	List<UserGrouprlgl> rlglList = userGroupRlglService.findChildRlglByZbh(usergrouprlgl.getF_zbh());//查找用户组编号对应所有用户编码信息
    	for (int i = 0; i < rlglList.size(); i++) {
			for (int j = 0; j < EUserlist.size(); j++) {
				if(EUserlist.get(j).getF_yhbh().equals(rlglList.get(i).getF_yhbh())){
					EUserlist.remove(j);
				}
			}
		}
    	for (int i = 0; i < EUserlist.size(); i++) {
    		usergrouprlgl.setF_yhbh(EUserlist.get(i).getF_yhbh());
    		boolean flag = userGroupRlglService.addUserGroupRlgl(usergrouprlgl);
    		if(flag == false){
    			log.info("#UserGroupController添加全部用户组信息失败");	
    		}
		}
    	returnObject.setList(EUserlist);
    	return returnObject;
    }
}
