package com.efounder.JEnterprise.mapper.basedatamanage.energyinformation;

import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESPark_EnergyType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 园区能耗类型Mapper
 * @author yujieying
 * @datetiem 2018年7月20日
 */
@Mapper
public interface BESPark_EnergyTypeMapper {

	/**
	 * 取所有园区能耗信息
	 * 
	 * @return
	 */
	List<BESPark_EnergyType> findPark_EnergyType();
	
	/**
	 * 取所有园区与能耗对应关系
	 * 
	 * @return
	 */
	List<Map<String,Object>> getgllist(@Param("f_yqbh")String f_yqbh);
	
	
	/**
	 * 取个别园区园能耗对应关系
	 * 
	 * @return
	 */
	List<Map<String,Object>> getenergylist(@Param("f_yqbh")String f_yqbh);
	
	/**
	 * 根据能耗类型ID获取能耗类型信息
	 * 
	 * @param id
	 * @return
	
	BESPark_EnergyType findPark_EnergyTypeById(@Param("f_yqbh")String f_yqbh,@Param("f_nybh")String f_nybh);
 */
	
	/**
	 * 增加园区能耗类型选择信息
	 * 
	 * @param 
	 * @return
	*/
	 boolean addpark_energytype(BESPark_EnergyType bespark_energytype);

	/**
	 * 删除园区能耗类型选择信息
	 * 
	 * @param 
	 * @return
	 */
	 boolean delpark_energytype(BESPark_EnergyType bespark_energytype);

	 List<Map<String, Object>>  findpark_energytype(BESPark_EnergyType bespark_energytype);

}
