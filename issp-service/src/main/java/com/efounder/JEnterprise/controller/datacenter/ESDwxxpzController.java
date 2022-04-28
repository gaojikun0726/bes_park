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
import com.efounder.JEnterprise.model.basedatamanage.dwxxpz.ESDwxxpz;
import com.efounder.JEnterprise.model.usercenter.ESUser;
import com.efounder.JEnterprise.service.applicationmanage.ESBmjgService;
import com.efounder.JEnterprise.service.basedatamanage.dwxxpz.ESDwxxpzService;
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
 * @description 单位信息自定义
 * @author gongfanfei
 */
@Controller
@RequestMapping(value = "/view/datacenterdemo")
public class ESDwxxpzController {

	private static final Logger log = LoggerFactory.getLogger(ESUserController.class);

	@Autowired
	private ESDwxxpzService esDwxxpzService;
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
	 * @description 初始化到单位信息自定义主界面
	 * @return data_dwxx.ftl
	 */
	@RequestMapping(value = "/dwxxpz", method = RequestMethod.GET)
	public String getDwxx(ModelMap map) {
		log.info("ESDwxxpzController获取信息");
		return "isspview/organizationmanage/dwxxpz";
	}

	/**
	 * @description 搜索单位信息
	 * @param dwxx_in（关键字）
	 * @return returnObject
	 */
	@RequestMapping(value = "/dwxx_treegrid", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getDwxxList(String dwxx_in) {
		log.info("ESDwxxpzController搜索单位信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESDwxxpz> list = new ArrayList<ESDwxxpz>();
		list = esDwxxpzService.getDwxxList(dwxx_in);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getF_pdwxxbh() == null || "".equals(list.get(i).getF_pdwxxbh())) {
				list.get(i).setF_pdwxxbh(null);
			}
		}
		returnObject.setList(list);
		return returnObject;
	}

	/**
	 * 生成单位信息树
	 * @return returnObject
	 */
	@RequestMapping(value = "/dwxxTree", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getTree() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("ESUserControllers获取单位信息树");
		String checkDataLimit = "1=1";
		try {
			List<IBaseTree> dwxxlist = esDwxxpzService.loadAll(checkDataLimit);// 根据数据权限配置查询单位信息
			List<ISSPTreeNode> treeJson = ISSPTreeBuilder.getTreeJson(dwxxlist);
			returnObject.setList(ISSPTreeBuilder.buildTree(treeJson));
			returnObject.setMsg("获取单位信息树成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取单位信息树失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	/**
	 * @description 查找子类
	 * @param f_dwxxbh
	 * @return returnObject
	 */
	@RequestMapping(value = "/dwxx_chlildtreegrid", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getChildList(String f_dwxxbh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESDwxxpz> list = new ArrayList<ESDwxxpz>();
		list = esDwxxpzService.findChildByBh(f_dwxxbh);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getF_pdwxxbh() == null || "".equals(list.get(i).getF_pdwxxbh())) {
				list.get(i).setF_pdwxxbh(null);
			}
		}
		returnObject.setList(list);
		return returnObject;
	}

	/**
	 * @description 新增单位信息
	 * @param dwxx
	 * @return returnObject
	 */
	@RequestMapping(value = "/dwxx_add", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject addDwxx(@RequestBody ESDwxxpz dwxx) {
		log.info("#ESDwxxpzController添加单位信息信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			//自动生成f_id
			String fid =  UUIDUtil.getRandom32BeginTimePK();
			dwxx.setF_id(fid);
			// 自动生成功能编码
			log.info("#ESDwxxpzControllers 获取 功能字典表编码结构");
			ESBmjg bmjg = esBmjgService.findBmjg("es_dwxxpz");
			// 级数其实是父节点的级数，所以将级数+1
			int dwxxJs = Integer.parseInt(dwxx.getF_js()) + 1;
			dwxx.setF_js(Integer.toString(dwxxJs));
			if (dwxxJs > bmjg.getBmjg().length()) {
				returnObject.setMsg("新增失败，级数已超过预设编码结构位数!");
				returnObject.setStatus("0");
				return returnObject;
			}
			// 根据级数 从编码结构中获取当前级位数
			String sbm = bmjg.getBmjg().substring(dwxxJs - 1, dwxxJs);
			int ibm = Integer.parseInt(sbm);
			// 获取父节点下所有子节点
			List<ESDwxxpz> dwxxList = esDwxxpzService.findChildByBh(dwxx.getF_pdwxxbh());
			if (dwxxList.size() > 0) {
				// //获取子节点最大编号
				int maxBh = getMaxBh(dwxxList);
				// //编号长度
				int bhLen = dwxx.getF_pdwxxbh().length() + ibm;
				// //生成单位信息编号 补齐编号
				String newBh = String.format("%0" + bhLen + "d", maxBh + 1);
				dwxx.setF_dwxxbh(newBh);
			} else {
				// //生成单位信息编号 补齐编号
				String newBh = dwxx.getF_pdwxxbh() + String.format("%0" + ibm + "d", 1);
				dwxx.setF_dwxxbh(newBh);
			}
			if (dwxx != null) {
				//增加之后执行
				boolean flag = esDwxxpzService.addDwxx(dwxx);
				if (flag) {
					if("1".equals(operationConfig.getSysDataBaseUseable())){
						OperationLog.insert(dwxx.getF_id(), "es_dwxxpz");
					}
					returnObject.setStatus("1");
					returnObject.setMsg("添加单位信息成功");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					dwxx.setF_chdate(sdf.format(new Date()));
					dwxx.setF_crdate(sdf.format(new Date()));
					returnObject.setData(dwxx);
				} else {
					returnObject.setStatus("0");
					returnObject.setMsg("添加单位信息失败");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}

	// 获取最大编号
	private int getMaxBh(List<ESDwxxpz> dwxxList) {
		int maxDwxxbh = 0;
		for (int i = 0; i < dwxxList.size(); i++) {
			String sBh = dwxxList.get(i).getF_dwxxbh();
			int iBh = Integer.parseInt(sBh);
			if (maxDwxxbh < iBh) {
				maxDwxxbh = iBh;
			}
		}
		return maxDwxxbh;
	}

	/**
	 * @description ajax加载编辑对象
	 * @param f_id
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/loadeditobj",method = RequestMethod.POST)
	@ResponseBody
	public ESDwxxpz loadedit(@RequestBody ESDwxxpz dwxxeditdata) {
		log.info("# ajax加载编辑单位信息对象");
		ESDwxxpz dwxx = esDwxxpzService.findById(dwxxeditdata.getF_id());
		return dwxx;
	}
	/**
	 * @description ajax加载编辑对象
	 * @param f_id
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/findDwxxListByLikeBhAndCheckDataLimit",method = RequestMethod.POST)
	@ResponseBody
	public List<ESDwxxpz> findDwxxListByLikeBhAndCheckDataLimit(@RequestBody ESDwxxpz dwxxeditdata) {
		log.info("# ajax加载编辑单位信息对象");
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
		List<ESDwxxpz> dwxxlist = esDwxxpzService.findDwxxListByLikeBhAndCheckDataLimit(dwxxeditdata.getF_id(),checkDataLimit);
		return dwxxlist;
	}

	@RequestMapping(value = "/dwxx_edit", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject editDwxx(ESDwxxpz dwxx) {
		log.info("#ESDwxxpzController修改单位信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//修改前后都执行
		Map<String, Object> startMap = null;
		try {
			boolean flag = true;
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				startMap = OperationLog.updateStart(dwxx.getF_id(), "es_dwxxpz");
				flag = esDwxxpzService.editDwxx(dwxx);
				OperationLog.updateEnd(dwxx.getF_id(), "es_dwxxpz", startMap);
			}else{
				flag = esDwxxpzService.editDwxx(dwxx);
			}
			if (flag) {
				returnObject.setMsg("修改单位信息成功");
				returnObject.setStatus("1");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				dwxx.setF_chdate(sdf.format(new Date()));
				returnObject.setData(dwxx);
			} else {
				returnObject.setMsg("修改单位信息失败");
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

    @RequestMapping(value = "/dwxx_page", method = RequestMethod.POST)
    public String list_page(@RequestParam(value = "keywords", required = false) String keywords,
    	@RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map) {
        log.info("#分页查询 pageNum={} , keywords={}", pageNum, keywords);
        PageInfo<ESDwxxpz> page = esDwxxpzService.findDwxxByPage(pageNum, keywords);
        map.put("page", page);
        map.put("keywords", keywords);
        return "view/datacenter/dwxx_page";
    }
    /**
     * @description 子节点分页查询
     * @param f_dwxxbh
     * @param pageNum
     * @param map
     * @return
     */

    @RequestMapping(value = "/dwxx_son_page", method = RequestMethod.POST)
    public String dwxx_list_page(@RequestParam(value = "f_dwxxbh", required = false) String f_dwxxbh,
    		@RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map) {
    	log.info("#分页查询 pageNum={} , f_dwxxbh={}", pageNum, f_dwxxbh);
    	PageInfo<ESDwxxpz> page = esDwxxpzService.findSonDwxxByPage(pageNum, f_dwxxbh);
    	map.put("page", page);
    	map.put("f_dwxxbh", f_dwxxbh);
    	return "view/datacenter/dwxx_page";
    }
	/**
	 * @description 删除单位信息
	 * @param f_dwxxbh
	 * @return
	 */
	@RequestMapping(value = "/dwxx_del", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject delDwxx(@RequestBody ESDwxxpz dwxx) {
		log.info("#ESDwxxpzControllers删除单位信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//删除之前删除
		try {
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				OperationLog.delete(dwxx.getF_id(), "es_dwxxpz");
			}
			boolean flag = esDwxxpzService.delDwxx(dwxx);
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
	public ISSPReturnObject getId(String f_dwxxbh) {
		log.info("#BESMonitoringHomeController ajax单位信息编号查询id");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESDwxxpz> list =  esDwxxpzService.getId(f_dwxxbh);
		returnObject.setList(list);
		return returnObject;
	}

	@RequestMapping(value = "/convertDwxxbh",method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getbh(String id) {
		log.info("#BESMonitoringHomeController ajax单位信息编号查询bh");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESDwxxpz> list =  esDwxxpzService.getbh(id);
		returnObject.setList(list);
		return returnObject;
	}


}
