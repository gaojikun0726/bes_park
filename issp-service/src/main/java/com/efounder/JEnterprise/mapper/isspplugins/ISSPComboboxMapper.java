package com.efounder.JEnterprise.mapper.isspplugins;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESEquipmentType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/** 
 * 类名称：ISSPComboboxMapper
 * 创建人：huangxianbo
 * 修改人：huangxianbo
 * @version 1.0.0 
 *
 */
@Mapper
public interface ISSPComboboxMapper extends BaseMapper<String ,BESEquipmentType>{
	/**
	 * 根据所传表名获取对应数据
	 * @param tabName 表名
	 * @param disCol 列名
	 * @return
	 */
	List<Map<String, String>> getData(@Param("tabName") String tabName,@Param("disCol") String disCol,@Param("disColId") String disColId,  @Param("conColumn") String conColumn, @Param("conValue") String conValue, @Param("conSql") String conSql);
	
}
