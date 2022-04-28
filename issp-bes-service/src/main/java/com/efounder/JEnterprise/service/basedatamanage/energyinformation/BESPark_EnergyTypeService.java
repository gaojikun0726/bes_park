package com.efounder.JEnterprise.service.basedatamanage.energyinformation;

import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESPark_EnergyType;

import java.util.List;
import java.util.Map;


/**
 * @Description 园区能耗类型接口
 * @author yujieying
 */
public interface BESPark_EnergyTypeService {

	/**
	 * 查询所有能耗类型信息
	 *
	 * @return
	 */
	public List<BESPark_EnergyType> findPark_EnergyType();
	/**
	 * 查询园区与能耗类型关系信息
	 *
	 * @return
	 */
	public List<Map<String,Object>> getgllist(String f_yqbh);
	/**
	 * 添加园区能耗类型选择信息
	 * 
	 * @return
	 */
	public boolean addpark_energytype(BESPark_EnergyType bespark_energytype);

	/**
	 * 删除园区能耗类型选择信息
	 * 
	 * 
	 * @return
	 */
	public boolean delpark_energytype(BESPark_EnergyType bespark_energytype);

	/**
	 * 查询园区能耗类型选择信息
	 *
	 *
	 * @return
	 */
	List<Map<String,Object>> findpark_energytype(BESPark_EnergyType bespark_energytype);

	/**
	 * 查询个别园区与能耗类型关系信息
	 *
	 * @return
	 */
	public List<Map<String,Object>> getenrrgylist(String f_yqbh);

}
