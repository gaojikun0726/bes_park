package com.efounder.JEnterprise.mapper.basedatamanage.energydataReport;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.energydataReport.BesBudingInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @author  杨超
* @version 创建时间：2018年8月29日 下午4:46:13
* @parameter 
* @version 1.0
*/
@Mapper
public interface BesBudingInformationMapper extends BaseMapper<String, BesBudingInformation>{
	
	List<BesBudingInformation> getBudingList(@Param(value = "keywords")String keywords);
	
	boolean del_BudingType(String fBudingId);

    boolean add_BudingType(BesBudingInformation record);

    BesBudingInformation getBudingType(String fBudingId);

    boolean edit_BudingType(BesBudingInformation record);
    
    List<Map<String, Object>> getSjzxTypeList();

	/**
	 * 查询所有建筑物信息
	 * @return
	 */
	List<Map<String, Object>> queryBuildingList();


    Map<String,Object> getBjxxCount();
    /**
     * 查询所有建筑信息
     * @return
     */
	List<BesBudingInformation> getBuildingList();

	/**
	 * 查询建筑物信息根据数据中心id
	 * @return
	 */
	List<BesBudingInformation> queryBuildingByCenterIdList(String fCenterId);

	/**
	 * 批量添加建筑信息
	 * @param list
	 * @return
	 */
	boolean addBudingInfo(List<BesBudingInformation> list);
}
