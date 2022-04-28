package com.efounder.JEnterprise.controller.systemcenter;


import com.core.common.ISSPReturnObject;
import com.core.common.tree.ISSPTreeBuilder;
import com.core.common.tree.ISSPTreeNode;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.model.applicationmanage.ESXtList;
import com.efounder.JEnterprise.model.auth.PermissionModule;
import com.efounder.JEnterprise.model.datacenter.ESGnzd;
import com.efounder.JEnterprise.service.applicationmanage.ESXtListService;
import com.efounder.JEnterprise.service.auth.PermissionService;
import com.efounder.JEnterprise.service.datacenter.ESGnzdService;
import com.framework.common.utils.OperationLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description 菜单自定义
 * @author gongfanfei
 */
@Controller
@RequestMapping(value = "/view/systemcenter")
public class ESSysGnzdController {
	
	private static final Logger log = LoggerFactory.getLogger(ESSysGnzdController.class);
	@Autowired
	private ESGnzdService esGnzdService;
	@Autowired
	private ESXtListService esXtService;
	@Autowired
	private PermissionService permissionService;
	 /**
	  * @Description 初始化到主界面
	  * @return
	  */
	 @RequestMapping(value = "/gnzd", method = RequestMethod.GET)
	 public String getGnzdAbout(){
		 log.info("ESSysGnzdController获取信息");
		return "isspview/applicationmanage/gnzd";
	 }
	 
	 /**
	  * @Description 根据功能编号gnbh查询
	  * @param f_gnmc
	  * @return
	  */
	@RequestMapping(value = "/gnzd_treegrid", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getGnzdList(String gnbh,String mark) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#ESSysGnzdController通过菜单名称查询数据列表");
		List<ESGnzd> list = new ArrayList<ESGnzd>();
		if(mark.equals("root")){
			list = esGnzdService.getGnzdByMkbhAndNull(gnbh);
		}else{
			list = esGnzdService.loadGnzdListByPgnbh(gnbh);
		}
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPgnbh() == null || "".equals(list.get(i).getPgnbh())) {
				list.get(i).setPgnbh(null);
			}
		}
		List<ESGnzd> finalList = forlistsetValue(list);
		returnObject.setList(finalList);
		return returnObject;
	}
	
	/**
	 * @Description 根据guid模块编号查询所有模块下的功能菜单
	 * @param f_gnmc
	 * @return
	 */
	@RequestMapping(value = "/gnzd_list", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getGnzdListByGuid(String guid) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#ESSysGnzdController通过模块编号查询数据列表");
		List<ESGnzd> list = esGnzdService.loadGnzdListByMkuuid(guid);
		List<ESGnzd> finalList = forlistsetValue(list);
		returnObject.setList(finalList);
		return returnObject;
	}
	
	/**
	 * @Description 判断是否
	 * @param list
	 * @return list
	 */
	public List<ESGnzd> forlistsetValue(List<ESGnzd> list){
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getHide().equals("0")){
				list.get(i).setHide("启用");	
			}
			else{// if(list.get(i).getHide()=="1")
				list.get(i).setHide("停用");	
			}
			if(list.get(i).getFullScreenType().equals("0")){
			list.get(i).setFullScreenType("带导航区域全屏");	
			}
			else if(list.get(i).getFullScreenType().equals("1")){
				list.get(i).setFullScreenType("内容区域全屏");	
			}
			else{// if(list.get(i).getFullScreenType()=="2")
				list.get(i).setFullScreenType("只显示内容区域");	
			}
			if(list.get(i).getOpenType().equals("0")){
				list.get(i).setOpenType("DIV");	
			}
			else{// if(list.get(i).getOpenType().equals("1"))
				list.get(i).setOpenType("newpage");	
			}
			if(list.get(i).getTabcloseable().equals("0")){
				list.get(i).setTabcloseable("否");//不可关闭
			}
			else{// if(list.get(i).getTabcloseable().equals("1"))
				list.get(i).setTabcloseable("是");//可关闭
			}
			list.get(i).setGnbh("gnzd"+list.get(i).getGnbh());
			list.get(i).setGuid("gnzd"+list.get(i).getGuid());
			list.get(i).setPgnbh("gnzd"+list.get(i).getPgnbh());
		}
		return list;
	}
	/**
     * @author gongfanfei
     * @Desciption 系统列表
     */
    @RequestMapping(value = "/loadAllXt", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getXtList() {
    	ISSPReturnObject returnObject = new ISSPReturnObject();
    	List<ESXtList> list = new ArrayList<ESXtList>();
    	list = esXtService.findXts();
    	returnObject.setList(list);
    	return returnObject;
    }
    /**
     * @author gongfanfei
     * @Desciption 当前系统列表
     */
    @RequestMapping(value = "/loadCurrentXt", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getCurrentXtList() {
    	ISSPReturnObject returnObject = new ISSPReturnObject();
    	List<ESXtList> list = new ArrayList<ESXtList>();
    	list = esXtService.findCurrentXt();
    	returnObject.setList(list);
    	return returnObject;
    }
    /**
	 * @Description 模块列表
	 */
	@RequestMapping(value = "/module_list", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject loadModulelist(String xtbh) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<PermissionModule> list = new ArrayList<PermissionModule>();
		list = permissionService.getHomeModulesByXtbh(xtbh);
		returnObject.setList(list);
		return returnObject;
	}
	/**
	 * @Description 生成功能字典树
	 * @author gongfanfei
	 * @return
	 */
	@RequestMapping(value = "/gnzd_tree", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject getTree(String guid) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("ESSysGnzdController获取信息");
		try {
			PermissionModule homeObj = permissionService.getPermissionMenusByMkbh(guid);
			ESGnzd Moulde = new ESGnzd();
			Moulde.setGnbh(homeObj.getGuid());
			Moulde.setPgnbh("root");
			Moulde.setGnmc(homeObj.getMkmc());
			Moulde.setJs("1");
			Moulde.setMx("0");
			
			List<ESGnzd> gnzdlist = esGnzdService.loadGnzdListByMkuuid(guid);
			
			for (int i = 0; i < gnzdlist.size(); i++) {
				if(gnzdlist.get(i).getPgnbh()==null || gnzdlist.get(i).getPgnbh().equals("")){
					gnzdlist.get(i).setPgnbh(gnzdlist.get(i).getMkbh());
				}
			}
			gnzdlist.add(Moulde);
			List<ISSPTreeNode> nodes = new ArrayList<ISSPTreeNode>();// 把所有资源转换成树模型的节点集合，此容器用于保存所有节点
			for (ESGnzd gnzd : gnzdlist) {
				ISSPTreeNode node = new ISSPTreeNode();
				node.setNodeTreeId("gnzd"+gnzd.getGnbh());
				node.setId("gnzd"+gnzd.getGnbh());
				node.setPid("gnzd"+gnzd.getPgnbh());
				node.setText(gnzd.getGnmc());
				nodes.add(node);// 添加到节点容器
			}
			List<ISSPTreeNode> buildTree = ISSPTreeBuilder.buildTree(nodes);
			
			returnObject.setList(buildTree);
			returnObject.setMsg("获取功能字典树成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取功能字典树失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	/**
     * @Description ajax更新功能菜单
     * @param gnzd
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject edit(ESGnzd gnzd) {
    	
    	if(gnzd.getGnbh().contains("gnzd")){
    		gnzd.setGnbh(gnzd.getGnbh().substring(4));
    		gnzd.setGuid(gnzd.getGnbh());
    	}else{
    		gnzd.setGuid(gnzd.getGnbh());
    	}
    	ISSPReturnObject returnObject = new ISSPReturnObject();
    	log.info("#ESSysGnzdController修改功能菜单");
        boolean flag = esGnzdService.editGnzdByGnbh(gnzd);
        Map<String, Object> startMap;
        //修改前后都执行
		try {
			startMap = OperationLog.updateStart(gnzd.getGnbh(), "es_gnzd");
        List<ESGnzd> list = esGnzdService.loadGnzdListByGnbh(gnzd.getGnbh());
        OperationLog.updateEnd(gnzd.getGnbh(), "es_gnzd", startMap);
        List<ESGnzd> finalList = forlistsetValue(list);
        gnzd = finalList.get(0);
        if (flag) {
        	
        	returnObject.setStatus("1");
            returnObject.setMsg("功能菜单修改成功!");         
            returnObject.setData(gnzd);       
        } else {
        	returnObject.setStatus("0");
            returnObject.setMsg("功能菜单修改失败");      
        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return returnObject;
    }
    /**
	 * @Description 编辑时加载数据
	 * @param gnbh
	 * @return
	 */
	@RequestMapping(value = "/edit_gnzd_obj", method = RequestMethod.GET)
	@ResponseBody
	public ISSPReturnObject getEditGnzdResult(String gnbh) {
		if(gnbh.contains("gnzd")){
			gnbh = gnbh.substring(4);
		}
		log.info("#ESSysGnzdController通过菜单名称查询数据列表");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESGnzd> list = new ArrayList<ESGnzd>();
		list = esGnzdService.loadGnzdListByGnbh(gnbh);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPgnbh() == null || "".equals(list.get(i).getPgnbh())) {
				list.get(i).setPgnbh(null);
			}
		}
		returnObject.setList(list);
		return returnObject;
	}
    /**
	 * @Description 新增功能菜单
	 * @param gnzd
	 * @return
	 */
	@RequestMapping(value = "/add_gnzd", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject addGnzd(@RequestBody ESGnzd gnzd) {
		log.info("#ESSysGnzdController新增功能菜单");
		//自动生成功能编码
		String guid = UUIDUtil.getRandom32BeginTimePK();
		gnzd.setGuid(guid);
		gnzd.setGnbh(guid);
		//级数其实是父节点的级数，所以将级数+1
		if(gnzd.getJs() == null){
			gnzd.setJs("1");
		}
		int cJs = Integer.parseInt(gnzd.getJs()) + 1;
		gnzd.setJs(Integer.toString(cJs));
		
		ISSPReturnObject returnObject = new ISSPReturnObject();
		if(gnzd != null)
		{
			boolean flag = esGnzdService.addGnzd(gnzd);
			//增加之后增加
			try {
				OperationLog.insert(gnzd.getGuid(), "es_gnzd");
			if (flag) {
				returnObject.setStatus("1");
				returnObject.setMsg("新增功能菜单成功!");
				List<ESGnzd> list = new ArrayList<ESGnzd>();
				list.add(gnzd);
				List<ESGnzd> finalList = forlistsetValue(list);
				returnObject.setData(finalList.get(0));
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("新增功能菜单失败");
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return returnObject;
	}
	/**
	 * @Description 删除功能菜单
	 * @param f_zzjgbh
	 * @return
	 */
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject delZzjg(@RequestBody ESGnzd gnzd) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#ESSysGnzdController删除功能菜单信息");
		//删除之前删除
		try {
			OperationLog.delete(gnzd.getGnbh(), "es_gnzd");
		boolean flag = esGnzdService.deleteGnzd(gnzd.getGnbh(), gnzd.getMkbh());

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

}
