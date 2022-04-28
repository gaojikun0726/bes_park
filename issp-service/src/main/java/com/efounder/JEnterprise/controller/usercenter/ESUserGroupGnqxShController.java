package com.efounder.JEnterprise.controller.usercenter;

import com.core.common.ISSPReturnObject;
import com.core.common.tree.ISSPTreeBuilder;
import com.core.common.tree.ISSPTreeNode;
import com.core.config.qxpz.QxConfig;
import com.efounder.JEnterprise.model.datacenter.ESGnzd;
import com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysQxOperateLog;
import com.efounder.JEnterprise.model.usercenter.ESUserGroup;
import com.efounder.JEnterprise.model.usercenter.ESUserGroupGnqxManage;
import com.efounder.JEnterprise.service.datacenter.ESGnzdService;
import com.efounder.JEnterprise.service.systemcenter.logmanage.ESSysQxOperateLogService;
import com.efounder.JEnterprise.service.usercenter.ESUserGroupGnqxManageService;
import com.efounder.JEnterprise.service.usercenter.ESUserGroupService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Description 用户组功能权限审核
 * @author tianjiangwei
 */
@RequestMapping(value = "/view/usergroupgnqxsh/")
@Controller
public class ESUserGroupGnqxShController {

	private static final Logger log = LoggerFactory.getLogger(ESUserGroupGnqxShController.class);

	@Autowired
	private ESUserGroupGnqxManageService esUserGroupGnqxManageService;
	@Autowired
	private ESGnzdService esGnzdService;
	@Autowired
	private ESUserGroupService esUserGroupService;
	@Autowired
	private QxConfig qxConfig;
	@Autowired
	private ESSysQxOperateLogService esqxoperatelogservice ;
	
	/**
	 * @Description 获取所有角色功能权限
	 * @return
	 */
	@RequestMapping(value = "usergroup_gnqx", method = RequestMethod.GET)
	public String getUserRoleGnqxManagePage() {
		log.info("#UserGroupGnqxManageController获取用户组功能权限审核信息");
		return "/isspview/usermanage/authoritymanage/usergroupgnqxsh";
	}
	


	/**
	 * @Description 生成功能字典树
	 * @author tianjiangwei
	 * @return
	 */
	@RequestMapping(value = "/gnzd_tree", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getTree(String f_zbh,String guid) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("ESUserGroupGnqxManageController获取信息");
		try {
			String classifygnqxTb = qxConfig.getGroupGnqxb();
			//建立集合——存放模块下的功能编号
			List<String> sjbhlist = new ArrayList<String>();
			//通过模块编号查找到对应所有功能编号集合
			List<ESGnzd> gnzdlist = esGnzdService.loadGnzdListByMkuuidAndTableAndShAndGroup(guid,classifygnqxTb,f_zbh);
			
			//建立集合——存放用户功能权限表的功能编号
			List<String> idList = new ArrayList<String>();
			//通过用户编号和权限编号查询所有勾选信息
			List<ESUserGroupGnqxManage> gouxuanlist = esUserGroupGnqxManageService.findUserGroupGnqxByYhbhAndQxbh(f_zbh,classifygnqxTb);
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
				node.setNodeTreeId("groupgnqxsh_"+gnzd.getGnbh());
				node.setId("groupgnqxsh_"+gnzd.getGnbh());
				node.setPid("groupgnqxsh_"+gnzd.getPgnbh());
				node.setText(gnzd.getGnmc());
				nodes.add(node);// 添加到节点容器
			}
			List<ISSPTreeNode> tree = ISSPTreeBuilder.buildTree(nodes);
			//建立集合——存放用户功能权限表的功能编号
			List<String> usergnqxlist = new ArrayList<String>();
			for (int i = 0; i < idList.size(); i++) {
				if(sjbhlist.contains(idList.get(i))){
					usergnqxlist.add("groupgnqxsh_"+idList.get(i));
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
		return returnObject;
	}
    
	/**
	 * @Description 添加/更新角色功能权限
	 * @return
	 */
	@RequestMapping(value = "group_gnqxsh_add", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject addGroupGnqx(String f_zbh,String mkguid,String[] f_gnzds) {
		//获取当前登录用户
		Subject currentUser = SecurityUtils.getSubject();
		Object loginUser = currentUser.getPrincipal();
		String user=loginUser.toString();
		
		String tablename = qxConfig.getGroupGnqxb();
		List<String> gnzds = new ArrayList<String>();//最新修改功能字典list
		
		if(f_gnzds!=null){
			for (int i = 0; i < f_gnzds.length; i++) {
				gnzds.add(f_gnzds[i].substring(12));
			}
		}
		log.info("#ESUserGroupGnqxShControllers审核用户组功能权限信息");
		List<ESUserGroupGnqxManage> gnqxList = new ArrayList<ESUserGroupGnqxManage>();
		boolean isUpdateSucc = true;
			if(gnzds.size()>0){
				for (int j = 0; j < gnzds.size(); j++) {
					ESUserGroupGnqxManage usergnqx = new ESUserGroupGnqxManage();
					usergnqx.setF_gnzdid(gnzds.get(j));
					gnqxList.add(j, usergnqx);
				}
			if(gnqxList.size()>0){
				isUpdateSucc = esUserGroupGnqxManageService.upUserGroupGnqx(gnqxList, tablename, f_zbh);
				
				
				Date now = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// 可以方便地修改日期格式
				String currentTime = dateFormat.format(now);
				// 当前用户   当前时间  审核人   审核对象rolebh  
				ESUserGroup role = esUserGroupService.findGroupByGroupBh(f_zbh);
				String groupname = role.getF_zmc();
				List<ESGnzd> esgnzdlist = esGnzdService.getGnzdListBygnzdlist(gnzds);
				String gnzdname="";
				for (int i = 0; i < esgnzdlist.size(); i++) {
					gnzdname = gnzdname +"、"+esgnzdlist.get(i).getGnmc();
				}
				gnzdname = gnzdname.substring(1);
				String content = "用户组功能权限审核中对"+groupname+"审核了"+gnzdname+"功能权限";
				// 张三在2018/09/10 10:00:00在角色功能权限审核中对 普通管理员 通过了用户定义、角色定义、岗位定义功能权限
				ESSysQxOperateLog record = new ESSysQxOperateLog();
				record.setfOpname(user);//操作人
				record.setfOptype("12");//操作类型 12：角色功能权限审核
				record.setfOpcontent(content);
				esqxoperatelogservice.insertOperateLog(record);
				
			}else{
				isUpdateSucc = true;
			}
		}
		ISSPReturnObject returnObject = new ISSPReturnObject();
		if (isUpdateSucc) {
			returnObject.setStatus("1");
			returnObject.setMsg("保存成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("保存失败");
		}
		return returnObject;
	}
}
