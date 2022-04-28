package com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration;

import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.CoolingTowerVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** 
 * 类名称：CoolingTowerMapper
 * 类描述：冷却塔数据mapper
 * 创建人：yangjx
 * @version 1.0.0 
 *
 */
 @Mapper
 public interface CoolingTowerMapper{
	 
	 /**
	  * 分页查询
	  * @return
	  */
	 List<CoolingTowerVo> paging(@Param("page")Integer page,@Param("limit") Integer limit); 
	 
	 /**
	  * 分页总数
	  * @return
	  */
	 public int pageCount();
	 
	 
}
