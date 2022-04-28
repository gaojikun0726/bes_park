package com.efounder.JEnterprise.controller.usercenter;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.usercenter.ESGnzdManage;
import com.efounder.JEnterprise.service.usercenter.ESUserGnqxQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


/**
 * @Description 用户功能权限
 * @author tianjiangwei
 */
@RequestMapping(value = "/view/usergnqxquery/")
@Controller
public class ESUserGnqxQueryController {

	private static final Logger log = LoggerFactory.getLogger(ESUserGnqxQueryController.class);

	@Autowired
	private ESUserGnqxQueryService esUserGnqxQueryService;
	
	/**
	 * @Description 初始化加载用户功能权限查询主页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "user_gnqxquery", method = RequestMethod.GET)
	public String getUserGnqxQueryPage() {    
		log.info("#ESUserGnqxQueryController初始化加载用户功能权限查询主页面");
		return "/isspview/usermanage/authorityquery/usergnqxquery";
	}
	
	/**
	 * 根据用户查询对应功能能权限
	 * @param f_yhbh
	 * @return
	 */
	@RequestMapping(value = "/queryGnzdData", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject queryGnzdData(String f_yhbh) {
/*		log.info("#获取功能权限数据");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESGnzdManage> query_list = new ArrayList<ESGnzdManage>();
		List<ESGnzdManage> list = new ArrayList<ESGnzdManage>();
		List<ESGnzdManage> list1 = new ArrayList<ESGnzdManage>();
		query_list = esUserGnqxQueryService.queryGnzdData(f_yhbh);//根据用户编号过滤功能字典
		list = esUserGnqxQueryService.loadAllGnzdData();//查询所有的功能字典
		list1 = esUserGnqxQueryService.loadAllGnzdDatasc();//查询所有的功能字典
		for(int i = 0;i<list.size();i++){
			for(int j = 0;j<query_list.size();j++){
				if((list.get(i).getF_guid()).equals(query_list.get(j).getF_gnzdid())){
					String f_sh = query_list.get(j).getF_sh();
					list.get(i).setF_sh(f_sh);
					list.get(i).setF_sq("1");//用户功能权限表中存在的即为已授权的，否则是未授权的
					break;				
					}else{
					list.get(i).setF_sh("0");
					list.get(i).setF_sq("0");
				}
			}
		}
		
		returnObject.setList(list);
		return returnObject;*/
		
		
		log.info("#获取功能权限数据");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESGnzdManage> query_list = new ArrayList<ESGnzdManage>();
		query_list = esUserGnqxQueryService.queryGnzdData(f_yhbh);//根据用户编号过滤功能字典
		
		List<ESGnzdManage> tree = getTreeJson();// 获得一棵树模型的数据
		List<ESGnzdManage> module_list = new ArrayList<ESGnzdManage>();//查询模块信息
		module_list= esUserGnqxQueryService.loadAllModuleData();
		List<ESGnzdManage> module = new ArrayList<ESGnzdManage>();
		for(int i = 0;i<module_list.size();i++){
			ESGnzdManage node = new ESGnzdManage();
			node.setF_gnmc(module_list.get(i).getF_mkmc());
			node.setF_parentid(null);//一级节点父节点为null
			node.setF_gnbh(module_list.get(i).getF_guid());
			module.add(node);
		}
		//审核、授权赋值    一级二级节点
		if(query_list.size() == 0){
			for(int i = 0;i<tree.size();i++){
				tree.get(i).setF_sh("0");
				tree.get(i).setF_sq("0");
			}
		}
		else{
			for(int i = 0;i<tree.size();i++){
				for(int j = 0;j<query_list.size();j++){
					if((tree.get(i).getF_guid()).equals(query_list.get(j).getF_gnzdid())){
						String f_sh = query_list.get(j).getF_sh();
						tree.get(i).setF_sh(f_sh);
						tree.get(i).setF_sq("1");//用户功能权限表中存在的即为已授权的，否则是未授权的
						break;				
						}else{
							tree.get(i).setF_sh("0");
							tree.get(i).setF_sq("0");
					}
				}
			}
		}
		
		//根节点菜单审核赋值
		for(int k = 0;k < module.size();k++){
			for(int q = 0;q <tree.size();q++){
				if((tree.get(q).getF_parentid()).equals(module.get(k).getF_gnbh())){
					if("1".equals(tree.get(q).getF_sh())){
						module.get(k).setF_sh("1");
						break;
					}
					else{
						module.get(k).setF_sh("0");
					}
				}
			}
		}
		//根节点菜单授权赋值
		for(int k = 0;k < module.size();k++){
			for(int q = 0;q <tree.size();q++){
				if((tree.get(q).getF_parentid()).equals(module.get(k).getF_gnbh())){
					if("1".equals(tree.get(q).getF_sq())){
						module.get(k).setF_sq("1");
						break;
					}
					else{
						module.get(k).setF_sq("0");
					}
				}
			}
		}
		tree.addAll(module);
		returnObject.setList(tree);
		return returnObject;
		
	}
	
	public List<ESGnzdManage> getTreeJson() {
		List<ESGnzdManage> gnzd_list = new ArrayList<ESGnzdManage>();
		List<ESGnzdManage> module_list = new ArrayList<ESGnzdManage>();//查询模块信息
		gnzd_list = esUserGnqxQueryService.loadAllGnzdData();//查询所有的功能字典
		module_list= esUserGnqxQueryService.loadAllModuleData();
		List<ESGnzdManage> nodes = new ArrayList<ESGnzdManage>();// 把所有资源转换成树模型的节点集合，此容器用于保存所有节点
		List<ESGnzdManage> module = new ArrayList<ESGnzdManage>();
		for(int i = 0;i<module_list.size();i++){
			ESGnzdManage node = new ESGnzdManage();
			node.setF_gnmc(module_list.get(i).getF_mkmc());
			node.setF_parentid(null);//一级节点父节点为null
			node.setF_guid(module_list.get(i).getF_guid());
			module.add(node);
		}
		
		for(int j = 0;j<gnzd_list.size();j++){
			for(int k = 0;k< module.size();k++){
				if("1".equals(gnzd_list.get(j).getF_js())){//二级节点赋父节点值
					if((module.get(k).getF_guid()).equals(gnzd_list.get(j).getF_mkguid())){
						gnzd_list.get(j).setF_parentid(module_list.get(k).getF_guid());
						gnzd_list.get(j).setF_gnbh(gnzd_list.get(j).getF_gnbh());
					}
				}else{//三级几点赋父节点值
					gnzd_list.get(j).setF_parentid(gnzd_list.get(j).getF_pgnbh());
					gnzd_list.get(j).setF_gnbh(gnzd_list.get(j).getF_gnbh());
				}
				
			}
		}
		//nodes.addAll(module);
		nodes.addAll(gnzd_list);
		return nodes;
	}
  
	
	
	
}



