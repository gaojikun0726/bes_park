package com.efounder.JEnterprise.controller.usercenter;

import com.core.common.ISSPReturnObject;
import com.core.common.tree.ISSPTreeBuilder;
import com.core.common.tree.ISSPTreeNode;
import com.core.common.util.ListCompare;
import com.core.config.qxpz.QxConfig;
import com.efounder.JEnterprise.model.auth.PermissionModule;
import com.efounder.JEnterprise.model.datacenter.ESGnzd;
import com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysQxOperateLog;
import com.efounder.JEnterprise.model.usercenter.ESGnqx;
import com.efounder.JEnterprise.model.usercenter.ESPost;
import com.efounder.JEnterprise.model.usercenter.ESUserPostGnqxManage;
import com.efounder.JEnterprise.service.auth.PermissionService;
import com.efounder.JEnterprise.service.datacenter.ESGnzdService;
import com.efounder.JEnterprise.service.systemcenter.logmanage.ESSysQxOperateLogService;
import com.efounder.JEnterprise.service.usercenter.ESGnqxService;
import com.efounder.JEnterprise.service.usercenter.ESPostGnqxManageService;
import com.efounder.JEnterprise.service.usercenter.ESPostService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Description 岗位功能权限
 * @author gongfanfei
 */                            
@RequestMapping(value = "/view/userpostgnqx/")
@Controller
public class ESUserPostGnqxManageController {

	private static final Logger log = LoggerFactory.getLogger(ESUserPostGnqxManageController.class);

	@Autowired
	private ESPostGnqxManageService esUserPostGnqxManageService;
	@Autowired
	private ESGnzdService esGnzdService;
	@Autowired
	private ESPostService esUserPostService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private ESGnqxService esGnqxService;
	@Autowired
	private QxConfig qxConfig;
	@Autowired
	private ESSysQxOperateLogService esqxoperatelogservice ;
	/**
	 * @Description 初始化加载岗位功能权限主页面
	 * @return
	 */
	@RequestMapping(value = "userpost_gnqx", method = RequestMethod.GET)
	public String getUserPostGnqxManagePage() {
		log.info("#ESUserPostGnqxManageController初始化加载岗位功能权限主页面");
		return "/isspview/usermanage/funauthoritymanage/userpostgnqx";
	}
	
	/**
	 * @Description 岗位列表
	 */
	@RequestMapping(value = "/userpost_list", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject loadUserPostlist(String keywords) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESPost> list = new ArrayList<ESPost>();
		list = esUserPostService.getUserPostList(keywords);
		returnObject.setList(list);
		return returnObject;
    }
	 /**
	  * @Description 功能权限字典列表
	  */
	@RequestMapping(value = "/gnqx_list",method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject loadeGnqxlist(String f_xtbh,String keywords) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESGnqx> list = new ArrayList<ESGnqx>();
		list = esGnqxService.getGnqxListByKeywords(f_xtbh,keywords);
		returnObject.setList(list);
		return returnObject;
		}
	/**
	 * @Description 模块列表
	 */
	@RequestMapping(value = "/module_list", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject loadModulelist(String f_xtbh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<PermissionModule> list = new ArrayList<PermissionModule>();
		list = permissionService.getHomeModulesByXtbh(f_xtbh);
		returnObject.setList(list);
		return returnObject;
	}
	/**
	 * @Description 生成功能字典树
	 * @author gongfanfei
	 * @editauthor  tianjiangwei
	 * @editdate:2018/09/28
	 * @return
	 */
	@RequestMapping(value = "/gnzd_tree", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getTree(String f_gwguid,String guid) {
		
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("ESUserGroupGnqxManageController获取信息");
		try {
			//建立集合——存放模块下的功能编号
			List<String> sjbhlist = new ArrayList<String>();
			//通过模块编号查找到对应所有功能编号集合
			List<ESGnzd> gnzdlist = esGnzdService.loadGnzdListByMkuuid(guid);
			//建立集合——存放用户功能权限表的功能编号
			List<String> idList = new ArrayList<String>();
			String classifygnqxTb = qxConfig.getPostGnqxb();
			//通过用户编号和权限编号查询所有勾选信息
			List<ESUserPostGnqxManage> gouxuanlist =  esUserPostGnqxManageService.findUserPostGnqxByYhbhAndQxbh(f_gwguid, classifygnqxTb);
			for (int i = 0; i < gouxuanlist.size(); i++) {
				idList.add(gouxuanlist.get(i).getF_gnzdid());
			}
			for (int i = 0; i < gnzdlist.size(); i++) {
				if(gnzdlist.get(i).getPgnbh()==null || gnzdlist.get(i).getPgnbh().equals("")){
					gnzdlist.get(i).setPgnbh("root");
				}
				sjbhlist.add(gnzdlist.get(i).getGnbh());
			}
			/*List<IBaseTree> listtree= new ArrayList<IBaseTree>();
			for (int i = 0; i < gnzdlist.size(); i++) {
				listtree.add(i, gnzdlist.get(i));
			}
			
			List<ISSPTreeNode> treeJson = ISSPTreeBuilder.getTreeJson(listtree);
			List<ISSPTreeNode> tree = ISSPTreeBuilder.buildTree(treeJson);*/
			
			List<ISSPTreeNode> nodes = new ArrayList<ISSPTreeNode>();
			for (ESGnzd gnzd : gnzdlist) {
				ISSPTreeNode node = new ISSPTreeNode();
				node.setNodeTreeId("postgnqx_"+gnzd.getGnbh());
				node.setId("postgnqx_"+gnzd.getGnbh());
				node.setPid("postgnqx_"+gnzd.getPgnbh());
				node.setText(gnzd.getGnmc());
				nodes.add(node);// 添加到节点容器
			}
			List<ISSPTreeNode> tree = ISSPTreeBuilder.buildTree(nodes);
			
			//建立集合——存放用户功能权限表的功能编号
			List<String> groupgnqxlist = new ArrayList<String>();
			for (int i = 0; i < idList.size(); i++) {
				if(sjbhlist.contains(idList.get(i))){
					groupgnqxlist.add("postgnqx_"+idList.get(i));
				}
			}
			HashMap<Object, Object> map = new HashMap<>();
			map.put(0, tree);
			map.put(1, groupgnqxlist);//回显勾选nodetreeid集合
			returnObject.setMap(map);
			returnObject.setMsg("获取功能字典树成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取功能字典树失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
		
		
		
		
		
		
	/*	ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("ESUserPostGnqxManageController获取信息");
		try {
			//建立集合——存放模块下的功能编号
			List<String> sjbhlist = new ArrayList<String>();
			//通过模块编号查找到对应所有功能编号集合
			List<ESGnzd> gnzdlist = esGnzdService.loadGnzdListByMkuuid(guid);
			//建立集合——存放用户功能权限表的功能编号
			List<String> idList = new ArrayList<String>();
			//通过用户编号和权限编号查询所有勾选信息
			List<ESUserPostGnqxManage> gouxuanlist = esUserPostGnqxManageService.findUserPostGnqxByGwbhAndQxbh(f_gwguid, f_qxbh);
			for (int i = 0; i < gouxuanlist.size(); i++) {
				idList.add(gouxuanlist.get(i).getF_sjbh());
			}
			for (int i = 0; i < gnzdlist.size(); i++) {
				if(gnzdlist.get(i).getPgnbh()==null || gnzdlist.get(i).getPgnbh().equals("")){
					gnzdlist.get(i).setPgnbh("root");
				}
				sjbhlist.add(gnzdlist.get(i).getGnbh());
			}
			List<IBaseTree> listtree= new ArrayList<IBaseTree>();
			for (int i = 0; i < gnzdlist.size(); i++) {
				listtree.add(i, gnzdlist.get(i));
			}
			
			List<ISSPTreeNode> treeJson = ISSPTreeBuilder.getTreeJson(listtree);
			List<ISSPTreeNode> tree = ISSPTreeBuilder.buildTree(treeJson);
			//建立集合——存放用户功能权限表的功能编号
			List<String> usergnqxlist = new ArrayList<String>();
			for (int i = 0; i < idList.size(); i++) {
				if(sjbhlist.contains(idList.get(i))){
					usergnqxlist.add(idList.get(i));
				}
			}
			HashMap<Object, Object> map = new HashMap<>();
			map.put(0, tree);
			map.put(1, usergnqxlist);//回显勾选nodetreeid集合
			returnObject.setMap(map);
			returnObject.setMsg("获取功能字典树成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取功能字典树失败");
			returnObject.setStatus("0");
		}
		return returnObject;*/
	

	/**
	 * @Description 删除对应模块下的菜单权限
	 * @param f_gwguid
	 * @return
	 */
/*	@RequestMapping(value = "/userpost_gnqx_del", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject delUserPostGnqx(String f_gwguid,String f_qxbh,String guid) {
		log.info("#ESUserPostGnqxManageController删除岗位功能数据信息");
		boolean isSucc = esUserPostGnqxManageService.delUserPostGnqx(f_gwguid,f_qxbh,guid);
		ISSPReturnObject returnObject = new ISSPReturnObject();
		if (isSucc) {
			returnObject.setStatus("1");
			returnObject.setMsg("删除岗位功能数据成功");
		} else {
			returnObject.setStatus("1");
			log.info("#ESUserPostGnqxManageController删除岗位功能数据信息失败：没有符合删除条件的信息");
			//result.put("msg", "删除岗位失败");
		}
		return returnObject;
	}*/
	/**
	 * @Description 添加/更新岗位功能权限
	 * @return
	 */
	@RequestMapping(value = "userpost_gnqx_add", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject addUserPostGnqx(String f_gwguid,String mkguid,String[] f_gnzds,String[] init_gnzds) {
		String tablename = qxConfig.getPostGnqxb();
		List<String> initgnzd = new ArrayList<String>();//初始功能字典list
		List<String> newgnzd = new ArrayList<String>();//最新修改功能字典list
		//添加至权限日志
		Subject currentUser = SecurityUtils.getSubject();//获取当前登录用户
		Object loginUser = currentUser.getPrincipal();
		String user=loginUser.toString();
		
		ESPost post = esUserPostService.findPostByPostBh(f_gwguid);
		String postname = post.getF_gwmc();
		
		if(init_gnzds !=null){
			for (int i = 0; i < init_gnzds.length; i++) {
				initgnzd.add(init_gnzds[i].substring(9));
			}
		}
		if(f_gnzds!=null){
			
			for (int i = 0; i < f_gnzds.length; i++) {
				newgnzd.add(f_gnzds[i].substring(9));
			}
		}
		List<String> insertgnzds = new ArrayList<String>();//需要新增的
		List<String> deletegnzds = new ArrayList<String>();//需要删除的
		
		insertgnzds = ListCompare.getAddaListThanbList(newgnzd,initgnzd);//相对于初始字典list新增
		deletegnzds = ListCompare.getReduceaListThanbList(newgnzd,initgnzd);//相对于初始字典list减少
		
		List<ESUserPostGnqxManage> deleteGgnqxList = new ArrayList<ESUserPostGnqxManage>();
		for (int i = 0; i < deletegnzds.size(); i++) {
			ESUserPostGnqxManage userpostgnqx = new ESUserPostGnqxManage();
			userpostgnqx.setF_gnzdid(deletegnzds.get(i));
			userpostgnqx.setF_gwguid(f_gwguid);
			deleteGgnqxList.add(i, userpostgnqx);
		}
		log.info("#ESUserGnqxController删除岗位功能权限信息");
		boolean isSucc = true;
		if(deleteGgnqxList.size()>0){
			isSucc = esUserPostGnqxManageService.delUserPostGnqx(deleteGgnqxList,tablename);
			List<ESGnzd> esgnzdlist = esGnzdService.getGnzdListBygnzdlist(deletegnzds);
			String gnzdname="";
			for (int i = 0; i < esgnzdlist.size(); i++) {
				gnzdname = gnzdname +"、"+esgnzdlist.get(i).getGnmc();
			}
			gnzdname = gnzdname.substring(1);
			String content = "岗位权限中对"+postname+"取消分配了"+gnzdname+"功能权限";
			ESSysQxOperateLog record = new ESSysQxOperateLog();
			record.setfOpname(user);//操作人
			record.setfOptype("13");//操作类型 13：角色功能权限取消分配
			record.setfOpcontent(content);
			esqxoperatelogservice.insertOperateLog(record);
		}
		
		if (!isSucc)
			log.info("#ESUserGnqxControllers删除用户功能权限信息失败：没有符合删除条件的信息");
		
		log.info("#ESUserGnqxControllers添加用户功能权限信息");
		List<ESUserPostGnqxManage> gnqxList = new ArrayList<ESUserPostGnqxManage>();
		boolean isAddSucc = true;
		
			if(insertgnzds.size()>0){
				for (int j = 0; j < insertgnzds.size(); j++) {
					ESUserPostGnqxManage userpostgnqx = new ESUserPostGnqxManage();
					userpostgnqx.setF_gnzdid(insertgnzds.get(j));
					userpostgnqx.setF_gwguid(f_gwguid);
					userpostgnqx.setF_sh("0");
					gnqxList.add(j, userpostgnqx);
				}
			if(gnqxList.size()>0){
				isAddSucc = esUserPostGnqxManageService.addUserPostGnqx(gnqxList, tablename);
				
				List<ESGnzd> esgnzdlist = esGnzdService.getGnzdListBygnzdlist(insertgnzds);
				String gnzdname="";
				for (int i = 0; i < esgnzdlist.size(); i++) {
					gnzdname = gnzdname +"、"+esgnzdlist.get(i).getGnmc();
				}
				gnzdname = gnzdname.substring(1);
				String content = "用户组功能权限中对"+postname+"分配了"+gnzdname+"功能权限";
				// 张三在2018/09/10 10:00:00在角色功能权限审核中对 普通管理员 通过了用户定义、角色定义、岗位定义功能权限
				ESSysQxOperateLog record = new ESSysQxOperateLog();
				record.setfOpname(user);//操作人
				record.setfOptype("11");//操作类型 11：角色功能权限分配
				record.setfOpcontent(content);
				esqxoperatelogservice.insertOperateLog(record);
			}else{
				isAddSucc = true;
			}
		}
		ISSPReturnObject returnObject = new ISSPReturnObject();
		if (isAddSucc) {
			returnObject.setStatus("1");
			returnObject.setMsg("保存成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("保存失败");
		}
		return returnObject;
	}	
		/*log.info("#ESUserGroupGnqxManageController添加用户组功能权限信息");
		boolean isAddSucc = esUserGroupGnqxManageService.addUserGroupGnqx(esusergnqxmanage);
		ISSPReturnObject returnObject = new ISSPReturnObject();
		if (isAddSucc) {
			returnObject.setStatus("1");
			returnObject.setMsg("更新用户组功能权限成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("更新用户组功能权限失败");
		}
		return returnObject;*/
	
	
	//public ISSPReturnObject addUserPostGnqx(@RequestBody ESUserPostGnqxManage esusergnqxmanage) {
		/*log.info("#ESUserPostGnqxManageController添加岗位功能权限信息");
		
		boolean isAddSucc = esUserPostGnqxManageService.addUserPostGnqx(esusergnqxmanage);
		ISSPReturnObject returnObject = new ISSPReturnObject();
		if (isAddSucc) {
			returnObject.setStatus("1");
			returnObject.setMsg("更新岗位功能权限成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("更新岗位功能权限失败");
		}
		return returnObject;*/
	//}
}
