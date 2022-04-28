package com.efounder.JEnterprise.controller.datacenter;

import com.core.common.ISSPReturnObject;
import com.core.common.security.ISSPSecurityObject;
import com.core.common.security.LimitParamObject;
import com.core.common.tree.IBaseTree;
import com.core.common.tree.ISSPTreeBuilder;
import com.core.common.tree.ISSPTreeNode;
import com.core.config.db.OperationConfig;
import com.efounder.JEnterprise.config.shiro.vo.Principal;
import com.efounder.JEnterprise.model.datacenter.ESDepartment;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.efounder.JEnterprise.service.basedatamanage.zzjg.ESZzjgService;
import com.efounder.JEnterprise.service.datacenter.ESDepartmentService;
import com.framework.common.utils.OperationLog;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/** 
 * 类名称：ESDepartmentController
 * 类描述：部门定义控制层
 * 创建人：sunhao
 * 修改人：sunhao
 * 修改时间：2018年5月31日 
 * 修改备注：
 * @version 1.0.0 
 *
 */
@RequestMapping(value = "/view/department")
@Controller
public class ESDepartmentController {
	private static final Logger log = LoggerFactory.getLogger(ESDepartmentController.class);
	
	@Autowired 
	private ESDepartmentService esDepartmentService;
	@Autowired
	private ESZzjgService esZzjgService;
	@Autowired
	private OperationConfig operationConfig;
	
	@RequestMapping(value = "/depa", method = RequestMethod.GET)
	public String getESDepartment(ModelMap map) {
		log.info("# ESUserController获取用户信息");
		/*PageInfo<ESDepartment> page = esDepartmentService.findESDepartmentByPage(null, null);
		map.put("page", page);*/
		return "isspview/organizationmanage/bmdy";
	}
	
	//
	@RequestMapping(value="/department",method=RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject findAllDepartment() {
		log.info("# ESUserController查询所有信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESDepartment> list = new ArrayList<ESDepartment>();
		list = esDepartmentService.findAllESDepartment();
		returnObject.setList(list);
		return returnObject;
	}
	
	//
	@RequestMapping(value="/findByZzjg",method=RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject findByZzjg(String f_zzjgbh) {
		log.info("# ESUserController根据组织机构编号查询部门信息");
		List<ESDepartment> list = new ArrayList<ESDepartment>();
		ISSPReturnObject returnObject = new ISSPReturnObject();
		list = esDepartmentService.findESDepartmentByZzbh(f_zzjgbh);
		returnObject.setList(list);
		return returnObject;
	}
	
	//
	@RequestMapping(value="/findByBmbh",method=RequestMethod.GET)
	@ResponseBody
	public ESDepartment findByBmbh(String f_bmbh) {
		log.info("# ESUserController根据部门编号查询部门信息");
		ESDepartment esdepartment = esDepartmentService.findESDepartmentByBmbh(f_bmbh);
		return esdepartment;
	}
	
	/**
	 * 查询
	 * 
	 * @param keywords
	 * @param pageNum
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/findByKeywords", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject findESDepartmentByKeywords(
			@RequestParam(value = "keywords", required = false) String keywords) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESDepartment> list = new ArrayList<ESDepartment>();
		list = esDepartmentService.findESDepartmentByKeywords(keywords);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getF_bmbh() == null
					|| "".equals(list.get(i).getF_bmbh())) {
				list.get(i).setF_bmbh(null);
			}
		}
		returnObject.setList(list);
		return returnObject;
	}
	
	/**
	 * 添加用户信息
	 * 
	 * @param json
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addESDepartment", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject addUser(@RequestBody ESDepartment esdEpartment, ModelMap model) {
		log.info("#ESUserController添加部门信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
		DecimalFormat df=new DecimalFormat("0000");
		
		List<ESDepartment> departmentlist = esDepartmentService.findAllESDepartment();
		int departmentLen = departmentlist.size();
		if(departmentLen==0){
			esdEpartment.setF_bmbh("0000");
		}else{
			String maxBmbh = esDepartmentService.findMaxBmbh();
			Integer cuDepartmentbh = (Integer.parseInt(maxBmbh)+1);
			String  currentDepartmentbh = df.format(cuDepartmentbh);
			esdEpartment.setF_bmbh(currentDepartmentbh);
		}
		boolean isSucc = esDepartmentService.addESDepartment(esdEpartment);
//		添加之后添加
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				OperationLog.insert(esdEpartment.getF_bmbh(), "es_department");
			}
			if (isSucc) {
				returnObject.setStatus("1");
				returnObject.setMsg("添加部门成功");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				esdEpartment.setF_chdate(sdf.format(new Date()));
				esdEpartment.setF_crdate(sdf.format(new Date()));
				returnObject.setData(esdEpartment);
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("添加部门失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;

	}
	/**
	 * 删除用户信息
	 * 
	 * @param f_yhbh
	 * @return
	 */
	@RequestMapping(value = "/deleteESDepartment", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject delUser(@RequestBody ESDepartment esdEpartment, ModelMap model) {
		log.info("#UserControllers删除部门信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//删除之前删除
		try {
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				OperationLog.delete(esdEpartment.getF_bmbh(), "es_department");
			}
			boolean isSucc = esDepartmentService.deleteByBmbh(esdEpartment);
			if (isSucc) {
				returnObject.setStatus("1");
				returnObject.setMsg("删除部门成功");
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("删除部门失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}
	
	/**
	 * 更新修改用户信息
	 * 
	 * @param 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateESDepartment", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject updateESDepartment(ESDepartment esdEpartment, ModelMap model) {

		log.info("#UserControllers更新修改用户信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//更新前后都执行操作日志
		Map<String, Object> startMap;
		try {
			boolean isSucc = true;
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				startMap = OperationLog.updateStart(esdEpartment.getF_bmbh(), "es_department");
				isSucc = esDepartmentService.upDateESDepartment(esdEpartment);
				OperationLog.updateEnd(esdEpartment.getF_bmbh(), "es_department", startMap);
			}else{
				isSucc = esDepartmentService.upDateESDepartment(esdEpartment);
			}
			if (isSucc) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				esdEpartment.setF_chdate(sdf.format(new Date()));
				returnObject.setStatus("1");
				returnObject.setMsg("更新修改用户成功");
				returnObject.setData(esdEpartment);
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("更新修改用户失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}
	
	/**
	 * ajax加载编辑对象
	 * 
	 * @param f_zzjgbh
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/loadeditobj", method = RequestMethod.POST)
	@ResponseBody
	public ESDepartment loadedit(@RequestBody ESDepartment esdepartment) {
		log.info("# ajax加载编辑组织机构对象");
		ESDepartment esDepartment = esDepartmentService.findESDepartmentByBmbh(esdepartment.getF_bmbh());
		return esDepartment;
	}
	
	/**
	 * 生成组织机构树
	 * 
	 * @return
	 */
	@RequestMapping(value = "/zzjg_tree", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getTree() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("NodeController获取信息");
		Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
		ESUser user = principa.getUser();
		// 获取当前用户的用户名
		String username = user.getF_yhbh();
		try {
			String qxbh = "0005";
			String pscol = "";
			String yhbh = username;
			String pibzw = "1";
			LimitParamObject paramObject = new LimitParamObject(); 
			paramObject.setQxbh(qxbh);
			paramObject.setPscol(pscol);
			paramObject.setYhbh(yhbh);
			paramObject.setPibzw(pibzw);
			String checkDataLimit = ISSPSecurityObject.CheckDataLimit(paramObject);
			
			List<IBaseTree> zzjglist = esZzjgService.loadAll(checkDataLimit);// 从数据库获取所有资源
			List<ISSPTreeNode> treeJson = ISSPTreeBuilder.getTreeJson(zzjglist);
			returnObject.setList(ISSPTreeBuilder.buildTree(treeJson));
			returnObject.setMsg("获取组织机构树成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取组织机构树失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}


}
