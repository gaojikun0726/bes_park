package com.efounder.JEnterprise.service;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.BESEquipmentMold;

import java.util.List;

/**
 * 
 * 类名称：BESEquipmentMoldService 类描述：设备类型service 创建人：huangxianbo
 * 创建时间：2018年6月25日
 * 
 * @version 1.0.0
 *
 */
public interface BESEquipmentMoldService {
	
	/**
	 * 获取设备类型信息
	 * @return
	 */
	public ISSPReturnObject getEquipmentModuleInfo();
	
	/**
	 * 加载设备类型树
	 * @return
	*/
	List<BESEquipmentMold> loadAll();
	
	/**
	  * 查找子类
	  * @param f_type
	  * @return
	  */
	public List<BESEquipmentMold> findChildByZtype(String f_type);
	/**
	 * 根据条件查询设备类型表
	 * @param f_sbmc
	 * @return
	 */
	public List<BESEquipmentMold> getEqTypeList(String f_sbmc);
	
	/**
     * 新增设备类型信息
     *
     * @param besEquipmentMold
     *            设备类型
     * 
     */
    public boolean add_eqType(BESEquipmentMold besEquipmentMold);
    
    /**
	 * 根据设备类型查询设备信息
	 * @param id
	 * @return
	 */
	public BESEquipmentMold findeqTypeById(String f_type);
	
	/**
	 * 更新设备类型
	 * @param 
	 * @return
	 */
	public boolean upeqType(BESEquipmentMold besEquipmentMold);
	/**
	 * 删除设备信息
	 * @param usergroup
	 * @return
	 */  
	public boolean deleqType(BESEquipmentMold besEquipmentMold);
	/**
	 * 查询设备类型下的子节点
	 */

	//public List<BESEquipmentMold> getNodesList(String f_type);
	/**
	 * 根据（多个）f_type查询设备名称
	 */
	public  List<BESEquipmentMold> getEquipmentMoldById(String f_type);
	/**
	 *
	 * @Description: 查询bes_txt_end表里面有没有当前id关联的txt文本
	 *
	 * @auther: wanghongjie
	 * @date: 17:21 2020/4/29
	 * @param: [f_id]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	public  ISSPReturnObject selectProgrammInfo(Integer f_id);
}
