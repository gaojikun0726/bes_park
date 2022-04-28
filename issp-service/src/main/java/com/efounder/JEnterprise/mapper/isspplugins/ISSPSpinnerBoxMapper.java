package com.efounder.JEnterprise.mapper.isspplugins;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESEquipmentType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/** 
 * 类名称：ISSPSpinnerBoxMapper
 * 创建人：huangxianbo
 * 修改人：huangxianbo
 * @version 1.0.0 
 *
 */
@Mapper
public interface ISSPSpinnerBoxMapper extends BaseMapper<String ,BESEquipmentType>{
	/**
	 * 根据所传表名获取对应数据
	 * 
	 * @param dataId ID名
	 * @param dataTxt 列名
	 * @param dataTabName 表名
	 * @return
	 */
	List<Map<String, String>> getData(@Param("dataId") String dataId, @Param("dataTxt") String dataTxt, @Param("dataTabName") String dataTabName, @Param("conColumn") String conColumn, @Param("conValue") String conValue, @Param("conSql") String conSql);
	
}
