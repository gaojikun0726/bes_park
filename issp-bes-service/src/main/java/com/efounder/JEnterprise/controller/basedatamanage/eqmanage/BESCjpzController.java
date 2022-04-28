package com.efounder.JEnterprise.controller.basedatamanage.eqmanage;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesZone;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesZoneinfo;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BESCjpzService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;

/**
* @author  杨超
* @version 创建时间：2018年9月26日 下午2:51:52
* @parameter 场景配置
* @version 1.0
*/
@Controller
@RequestMapping(value = "/view/basedatamanage/eqmanage")
public class BESCjpzController {
	private static final Logger log = LoggerFactory.getLogger(BESCjpzController.class);
	
	@Autowired
	private BESCjpzService bescjpzservice;	//場景配置接口
	
	/**
	 * 初始化場景配置頁面
	 */
	@RequestMapping(value="/bes_cjpz" , method = RequestMethod.GET)
	public String getCj_list() {
		log.info("BESCjpzController    初始化場景配置list頁面");
		return "/besview/basedatamanage/eqmanage/eqconfiguration/bes_cjpz";
	}
	
	/**
	 * 生成场景配置树
	 * @return
	 */
	@RequestMapping(value = "/cjpz_tree", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject cjpz_tree() {
		log.info("# BESCjpzController 生成场景配置树" );
		ISSPReturnObject returnObject = bescjpzservice.cjpz_tree();
		return returnObject;
	}
	/**
	 * addFolder 新增文件及
	 * @param obj
	 * @return
	 */
	@RequestMapping(value = "/addFolder", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject addFolder(BesZoneinfo obj) {
		ISSPReturnObject returnObject = bescjpzservice.addFolder(obj);// 根据页面信息更新
		return returnObject;
	}
	
	/**
	 * 删除操作
	 * @param obj
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/del_tree", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject del_tree(String nodeId,String f_type) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = bescjpzservice.del_tree(nodeId,f_type);// 根据页面信息更新
		return returnObject;
	}
	
	/**
	 * 查询下拉框 场景模式
	 * @return
	 */
	@RequestMapping(value = "/select_cjms", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject select_cjms() {
		ISSPReturnObject returnObject = bescjpzservice.select_cjms();
		return returnObject;
	}
	
	/**
	 * 根据场景模式id查询详情数据
	 * @return
	 */
	@RequestMapping(value = "/select_cjmsxq", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject select_cjmsxq(String id) {
		ISSPReturnObject returnObject = bescjpzservice.select_cjmsxq(id);
		return returnObject;
	}
	
	/**
	 * Scene 新建场景保存
	 * @param obj
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/addScene", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject addScene(BesZone obj,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException, RemoteException, ServiceException {
		ISSPReturnObject returnObject = bescjpzservice.addScene(obj,request,response);// 根据页面信息更新
		return returnObject;
	}
	
	/**
	 * 根绝id查询右侧详情
	 * @return	bes_zone
	 */
	@RequestMapping(value = "/select_zonexq", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject select_zonexq(String id) {
		ISSPReturnObject returnObject = bescjpzservice.select_zonexq(id);
		return returnObject;
	}
	
	/**
	 * 查询ip地址
	 * @param f_sys_name
	 * @param f_psys_name
	 * @param nodeLevel
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/GetIPAddr", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject GetIPAddr(String f_sys_name,String f_psys_name,String nodeLevel){
		ISSPReturnObject returnObject = new ISSPReturnObject();
		//根据逻辑点节点级数判断，7属于楼宇Cross，5属于照明lamp
		if (nodeLevel.equals("7")) {
			returnObject = bescjpzservice.getipaddr(f_sys_name, f_psys_name);
		}else {
			// Lamp同步逻辑点数据
			returnObject = bescjpzservice.getlampipaddr(f_sys_name, f_psys_name);
		}
		return returnObject;
	}

	/**
	 * 场景同步数据
	 * @param cjid
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/CjTbsj", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject CjTbsj(String cjid) throws UnsupportedEncodingException, RemoteException, ServiceException{
		ISSPReturnObject returnObject = new ISSPReturnObject();
		returnObject = bescjpzservice.CjTbsj(cjid);
		return returnObject;
	}
	
	/**
	 * 场景获取对比
	 * @param cjid
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/CjDbsj", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject CjDbsj(String cjid) throws UnsupportedEncodingException, RemoteException, ServiceException{
		ISSPReturnObject returnObject = new ISSPReturnObject();
		returnObject = bescjpzservice.CjDbsj(cjid);
		return returnObject;
	}

}
