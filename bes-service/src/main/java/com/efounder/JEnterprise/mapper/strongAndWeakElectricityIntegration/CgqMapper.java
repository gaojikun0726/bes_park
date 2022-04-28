package com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration;

import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.CgqVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类名称：CgqMapper
 * 类描述：传感器数据mapper
 * 创建人：wzx
 * @version 1.0.0
 *
 */
 @Mapper
 public interface CgqMapper {

	 /**
	  * 分页查询
	  * @return
	  */
	 List<CgqVo> paging(@Param("page")Integer page,@Param("limit") Integer limit);

	 /**
	  * 分页总数
	  * @return
	  */
	 public int pageCount();
	 /**
	  * 根据传感器类型获取列表
	  * @return
	  */
	 List<CgqVo> getCgqTypeList(@Param("f_type")String f_type,@Param("page")Integer page,@Param("limit") Integer limit);


}
