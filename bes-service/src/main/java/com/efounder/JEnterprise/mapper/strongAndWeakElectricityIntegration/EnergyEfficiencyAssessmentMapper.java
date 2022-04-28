package com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/** 
 * 类名称：EnergyEfficiencyAssessmentMapper
 * 类描述：
 * 创建人：tjw
 * @version 1.0.0 
 *
 */
 @Mapper
 public interface EnergyEfficiencyAssessmentMapper {


	List<Map> getRelateNodeListType(Map<String, Object> queryMap);

    String findRelativeNodeTable(String sysname);

    List<Map> queryRelativeTypeNodeList(Map<String, Object> queryMap);

    List<Map> queryPieRelativeInfo(Map map);

    Integer addPieRelativeInfo(Map map);

    Integer updatePieRelativeInfo(Map map);

    List<Map> queryAllPieRelativeInfo(Map<String, Object> queryMap);

    List<Map> queryLableRelativeTypeNodeList(Map<String, Object> queryMap);
}
