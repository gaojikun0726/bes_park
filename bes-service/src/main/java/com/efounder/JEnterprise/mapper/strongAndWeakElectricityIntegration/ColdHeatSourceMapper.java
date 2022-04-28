package com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration;

import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.ColdHeatSourceVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** 
 * 类名称：BESAlertGradeMapper
 * 类描述：报警等级mapper接口
 * 创建人：yujieying
 * @version 1.0.0 
 *
 */
 @Mapper
 public interface ColdHeatSourceMapper{
	 
	 /**
	  * 分页查询
	  * @return
	  */
	 List<ColdHeatSourceVo> paging(@Param("page")Integer page,@Param("limit") Integer limit); 
	 
	 /**
	  * 分页总数
	  * @return
	  */
	 public int pageCount();
	 
	 
}
