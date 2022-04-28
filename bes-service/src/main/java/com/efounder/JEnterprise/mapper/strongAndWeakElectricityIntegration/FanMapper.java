package com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration;

import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.FanVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** 
 * 类名称：FanMapper
 * 类描述：风机数据mapper
 * 创建人：yangjx
 * @version 1.0.0 
 *
 */
 @Mapper
 public interface FanMapper{
	 
	 /**
	  * 分页查询
	  * @return
	  */
	 List<FanVo> paging(@Param("page")Integer page,@Param("limit") Integer limit); 
	 
	 /**
	  * 分页总数
	  * @return
	  */
	 public int pageCount();
	 
	 
}
