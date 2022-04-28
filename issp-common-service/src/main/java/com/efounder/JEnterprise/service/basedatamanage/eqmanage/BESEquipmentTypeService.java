package com.efounder.JEnterprise.service.basedatamanage.eqmanage;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESEquipmentType;
import com.github.pagehelper.PageInfo;

import java.util.List;
/**
 * 设备型号接口
 * @author LvSihan
 *
 */
public interface BESEquipmentTypeService {

	/**
	 * 新增设备型号信息
	 *
	 * @param equipmenttype
	 */
	public ISSPReturnObject addEquipmentType(BESEquipmentType equipmenttype);

	/**
	 * 删除用户
	 * 
	 * @param equipmenttype
	 * @return
	 */
	public ISSPReturnObject delEquipmentType(BESEquipmentType equipmenttype);

	/**
	 * 根据id查询用户
	 * 
	 * @param id
	 * @return
	 */
	public ISSPReturnObject findEmtById(String f_type);

	/**
	 * 分页查询
	 * 
	 * @param pageNum
	 * @param keywords
	 * @return
	 */
	public PageInfo<BESEquipmentType> findemTByPage(Integer bars,Integer pageNum, String f_sblxbh,String keywords);
	
	/**
	 * 设备类型查询
	 */
	public PageInfo<BESEquipmentType> findSblxPage(Integer pageNum, String f_sblxbh,String treeId);
	
	//树table
	public ISSPReturnObject tree_table(String treeId);
	
	/**
	 * 生成设备类型树
	 * @param 
	 * @return
	 */
	public ISSPReturnObject sblx_tree();
	
	/**
	 * 编辑
	 * 
	 * @param equipmenttype
	 * @return
	 */
	public ISSPReturnObject upEmt(BESEquipmentType equipmenttype);

	/**
	 * 查询
	 * 
	 * @param keywords
	 * @return
	 */
	public List<BESEquipmentType> getEmtList(Integer pageNum, String keywords);
	
	/**
	 * 查询子集设备型号信息
	 * @param f_sblxbh
	 * @return
	 */
	public ISSPReturnObject seleqtypechildren (String f_sblxbh);
	
	/**
	 * 根据设备型号编号查询设备类型编号
	 * @param f_sblxbh
	 * @return
	 */
	public List<BESEquipmentType> getbh(String f_type);

}
