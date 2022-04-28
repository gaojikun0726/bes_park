package com.efounder.JEnterprise.service.basedatamanage.eqmanage;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesZone;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesZoneinfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;

/**
* @author  杨超
* @version 创建时间：2018年9月26日 下午2:54:18
* @parameter 
* @version 1.0
*/
public interface BESCjpzService {

	//场景配置树查询
	ISSPReturnObject cjpz_tree();
	
	//新增文件夹
	ISSPReturnObject addFolder(BesZoneinfo obj);
	
	//删除操作
	ISSPReturnObject del_tree(String nodeId,String f_type) throws UnsupportedEncodingException, RemoteException, ServiceException;
	
	//场景模式下拉框
	ISSPReturnObject select_cjms();
	
	//场景模式根绝id查询详情
	ISSPReturnObject select_cjmsxq(String id);
	
	//新建场景保存
	ISSPReturnObject addScene(BesZone obj,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException, RemoteException, ServiceException;
	
	//根据id查询详情
	ISSPReturnObject select_zonexq(String id);
	
	//cross id
	ISSPReturnObject getipaddr(String f_sys_name,String f_psys_name);
	
	//lamp id
	ISSPReturnObject getlampipaddr(String f_sys_name,String f_psys_name);
	
	//场景配置场景id查询信息
	ISSPReturnObject CjTbsj(String cjid) throws UnsupportedEncodingException, RemoteException, ServiceException;
	
	//场景配置对比数据
	ISSPReturnObject CjDbsj(String cjid) throws UnsupportedEncodingException, RemoteException, ServiceException;

}
