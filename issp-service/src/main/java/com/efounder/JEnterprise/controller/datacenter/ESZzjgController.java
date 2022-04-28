package com.efounder.JEnterprise.controller.datacenter;

import com.core.common.ISSPReturnObject;
import com.core.common.security.ISSPSecurityObject;
import com.core.common.security.LimitParamObject;
import com.core.common.tree.IBaseTree;
import com.core.common.tree.ISSPTreeBuilder;
import com.core.common.tree.ISSPTreeNode;
import com.core.config.db.OperationConfig;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.config.shiro.vo.Principal;
import com.efounder.JEnterprise.controller.usercenter.ESUserController;
import com.efounder.JEnterprise.model.applicationmanage.ESBmjg;
import com.efounder.JEnterprise.model.basedatamanage.zzjg.ESZzjg;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.efounder.JEnterprise.service.applicationmanage.ESBmjgService;
import com.efounder.JEnterprise.service.basedatamanage.zzjg.ESZzjgService;
import com.framework.common.utils.OperationLog;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description 组织机构自定义
 * @author gongfanfei
 */
@Controller
@RequestMapping(value = "/view/datacenter")
public class ESZzjgController {

	private static final Logger log = LoggerFactory.getLogger(ESUserController.class);

	@Autowired
	private ESZzjgService esZzjgService;
	@Autowired
	private ESBmjgService esBmjgService;
	@Autowired
	private OperationConfig operationConfig;
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	/**
	 * @description 初始化到组织机构自定义主界面
	 * @return data_zzjg.ftl
	 */
	@RequestMapping(value = "/zzjgnew", method = RequestMethod.GET)
	public String getZzjg(ModelMap map) {
		log.info("ESZzjgController获取信息");
		return "isspview/organizationmanage/zzjg";
	}

	/**
	 * @description 搜索组织机构
	 * @param zzjg_in（关键字）
	 * @return returnObject
	 */
	@RequestMapping(value = "/zzjg_treegrid", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getZzjgList(String zzjg_in) {
		log.info("ESZzjgController搜索组织机构");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESZzjg> list = new ArrayList<ESZzjg>();
		list = esZzjgService.getZzjgList(zzjg_in);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getF_pzzjgbh() == null || "".equals(list.get(i).getF_pzzjgbh())) {
				list.get(i).setF_pzzjgbh(null);
			}
		}
		returnObject.setList(list);
		return returnObject;
	}

	/**
	 * 生成组织机构树
	 * @return returnObject
	 */
	@RequestMapping(value = "/zzjgTree", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getTree() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("ESUserControllers获取组织机构树");
		String checkDataLimit = "1=1";
		try {
			List<IBaseTree> zzjglist = esZzjgService.loadAll(checkDataLimit);// 根据数据权限配置查询组织机构
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

	/**
	 * 生成组织机构树
	 * @return returnObject
	 */
	@RequestMapping(value = "/zzjgTreeZC", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getTreeZC() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("ESUserControllers获取正晨部门树");
		String checkDataLimit = "1=1";
		try {
			List<IBaseTree> zzjglist = esZzjgService.loadAllZC(checkDataLimit);// 根据数据权限配置查询组织机构
			List<ISSPTreeNode> treeJson = ISSPTreeBuilder.getTreeJson(zzjglist);
			returnObject.setList(ISSPTreeBuilder.buildTree(treeJson));
			returnObject.setMsg("获取正晨部门树成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取正晨部门树失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 * @description 查找子类
	 * @param f_zzjgbh
	 * @return returnObject
	 */
	@RequestMapping(value = "/zzjg_chlildtreegrid", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getChildList(String f_zzjgbh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESZzjg> list = new ArrayList<ESZzjg>();
		list = esZzjgService.findChildByBh(f_zzjgbh);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getF_pzzjgbh() == null || "".equals(list.get(i).getF_pzzjgbh())) {
				list.get(i).setF_pzzjgbh(null);
			}
		}
		returnObject.setList(list);
		return returnObject;
	}

	/**
	 * @description 新增组织机构
	 * @param zzjg
	 * @return returnObject
	 */
	@RequestMapping(value = "/zzjg_add", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject addZzjg(@RequestBody ESZzjg zzjg) {
		log.info("#ESZzjgController添加组织机构信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			//自动生成f_id
			String fid =  UUIDUtil.getRandom32BeginTimePK();
			zzjg.setF_id(fid);
			// 自动生成功能编码
			log.info("#ESZzjgControllers 获取 功能字典表编码结构");
			ESBmjg bmjg = esBmjgService.findBmjg("es_zzjg");
			// 级数其实是父节点的级数，所以将级数+1
			int zzjgJs = Integer.parseInt(zzjg.getF_js()) + 1;
			zzjg.setF_js(Integer.toString(zzjgJs));
			if (zzjgJs > bmjg.getBmjg().length()) {
				returnObject.setMsg("新增失败，级数已超过预设编码结构位数!");
				returnObject.setStatus("0");
				return returnObject;
			}
			// 根据级数 从编码结构中获取当前级位数
			String sbm = bmjg.getBmjg().substring(zzjgJs - 1, zzjgJs);
			int ibm = Integer.parseInt(sbm);
			// 获取父节点下所有子节点
			List<ESZzjg> zzjgList = esZzjgService.findChildByBh(zzjg.getF_pzzjgbh());
			if (zzjgList.size() > 0) {
				// //获取子节点最大编号
				int maxBh = getMaxBh(zzjgList);
				// //编号长度
				int bhLen = zzjg.getF_pzzjgbh().length() + ibm;
				// //生成组织机构编号 补齐编号
				String newBh = String.format("%0" + bhLen + "d", maxBh + 1);
				zzjg.setF_zzjgbh(newBh);
			} else {
				// //生成组织机构编号 补齐编号
				String newBh = zzjg.getF_pzzjgbh() + String.format("%0" + ibm + "d", 1);
				zzjg.setF_zzjgbh(newBh);
			}
			if (zzjg != null) {
				//增加之后执行
				boolean flag = esZzjgService.addZzjg(zzjg);
				if (flag) {
					if("1".equals(operationConfig.getSysDataBaseUseable())){
						OperationLog.insert(zzjg.getF_id(), "es_zzjg");
					}
					returnObject.setStatus("1");
					returnObject.setMsg("添加组织机构成功");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					zzjg.setF_chdate(sdf.format(new Date()));
					zzjg.setF_crdate(sdf.format(new Date()));
					returnObject.setData(zzjg);
				} else {
					returnObject.setStatus("0");
					returnObject.setMsg("添加组织机构失败");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}

	// 获取最大编号
	private int getMaxBh(List<ESZzjg> zzjgList) {
		int maxZzjgbh = 0;
		for (int i = 0; i < zzjgList.size(); i++) {
			String sBh = zzjgList.get(i).getF_zzjgbh();
			int iBh = Integer.parseInt(sBh);
			if (maxZzjgbh < iBh) {
				maxZzjgbh = iBh;
			}
		}
		return maxZzjgbh;
	}

	/**
	 * @description ajax加载编辑对象
	 * @param f_id
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/loadeditobj",method = RequestMethod.POST)
	@ResponseBody
	public ESZzjg loadedit(@RequestBody ESZzjg zzjgeditdata) {
		log.info("# ajax加载编辑组织机构对象");
		ESZzjg zzjg = esZzjgService.findById(zzjgeditdata.getF_id());
		return zzjg;
	}
	/**
	 * @description ajax加载编辑对象
	 * @param f_id
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/findZzjgListByLikeBhAndCheckDataLimit",method = RequestMethod.POST)
	@ResponseBody
	public List<ESZzjg> findZzjgListByLikeBhAndCheckDataLimit(@RequestBody ESZzjg zzjgeditdata) {
		log.info("# ajax加载编辑组织机构对象");
		Principal principa = (Principal) SecurityUtils.getSubject().getPrincipal();
		ESUser user = principa.getUser();
		// 获取当前用户的用户名
		String username = user.getF_yhbh();
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
		List<ESZzjg> zzjglist = esZzjgService.findZzjgListByLikeBhAndCheckDataLimit(zzjgeditdata.getF_id(),checkDataLimit);
		return zzjglist;
	}

	@RequestMapping(value = "/zzjg_edit", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject editZzjg(ESZzjg zzjg) {
		log.info("#ESZzjgController修改组织机构信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//修改前后都执行
		Map<String, Object> startMap = null;
		try {
			boolean flag = true;
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				startMap = OperationLog.updateStart(zzjg.getF_id(), "es_zzjg");
				flag = esZzjgService.editZzjg(zzjg);
				OperationLog.updateEnd(zzjg.getF_id(), "es_zzjg", startMap);
			}else{
				flag = esZzjgService.editZzjg(zzjg);
			}
			if (flag) {
				returnObject.setMsg("修改组织机构成功");
				returnObject.setStatus("1");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				zzjg.setF_chdate(sdf.format(new Date()));
				returnObject.setData(zzjg);
			} else {
				returnObject.setMsg("修改组织机构失败");
				returnObject.setStatus("0");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}
	/**
     * @description 分页查询
     * @param keywords
     * @param pageNum
     * @param map
     * @return
     */

    @RequestMapping(value = "/zzjg_page", method = RequestMethod.POST)
    public String list_page(@RequestParam(value = "keywords", required = false) String keywords, 
    	@RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map) {
        log.info("#分页查询 pageNum={} , keywords={}", pageNum, keywords);
        PageInfo<ESZzjg> page = esZzjgService.findZzjgByPage(pageNum, keywords);
        map.put("page", page);
        map.put("keywords", keywords);
        return "view/datacenter/zzjg_page";
    }
    /**
     * @description 子节点分页查询
     * @param f_zzjgbh
     * @param pageNum
     * @param map
     * @return
     */
    
    @RequestMapping(value = "/zzjg_son_page", method = RequestMethod.POST)
    public String zzjg_list_page(@RequestParam(value = "f_zzjgbh", required = false) String f_zzjgbh, 
    		@RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map) {
    	log.info("#分页查询 pageNum={} , f_zzjgbh={}", pageNum, f_zzjgbh);
    	PageInfo<ESZzjg> page = esZzjgService.findSonZzjgByPage(pageNum, f_zzjgbh);
    	map.put("page", page);
    	map.put("f_zzjgbh", f_zzjgbh);
    	return "view/datacenter/zzjg_page";
    }
	/**
	 * @description 删除组织机构信息
	 * @param f_zzjgbh
	 * @return
	 */
	@RequestMapping(value = "/zzjg_del", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject delZzjg(@RequestBody ESZzjg zzjg) {
		log.info("#ESZzjgControllers删除组织机构信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//删除之前删除
		try {
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				OperationLog.delete(zzjg.getF_id(), "es_zzjg");
			}
			boolean flag = esZzjgService.delZzjg(zzjg);
			if (flag) {
				returnObject.setMsg("删除成功");
				returnObject.setStatus("1");
			} else {
				returnObject.setMsg("删除失败");
				returnObject.setStatus("0");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}
	
	@RequestMapping(value = "/getId",method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getId(String f_zzjgbh) {
		log.info("#BESMonitoringHomeController ajax组织机构编号查询id");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESZzjg> list =  esZzjgService.getId(f_zzjgbh);
		returnObject.setList(list);
		return returnObject;
	}
	
	@RequestMapping(value = "/convertZzjgbh",method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getbh(String id) {
		log.info("#BESMonitoringHomeController ajax组织机构编号查询bh");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESZzjg> list =  esZzjgService.getbh(id);
		returnObject.setList(list);
		return returnObject;
	}
	

}
