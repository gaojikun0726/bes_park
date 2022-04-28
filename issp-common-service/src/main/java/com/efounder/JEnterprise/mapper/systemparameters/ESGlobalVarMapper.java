package com.efounder.JEnterprise.mapper.systemparameters;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.systemparameters.ESGlobalVar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
/** 
 * 类名称：ESGlobalVarMapper
 * 类描述  全局参数mapper接口
 * 创建人：huangxianbo
 * 修改人：huangxianbo
 * @version 1.0.0 
 *
 */
@Mapper
public interface ESGlobalVarMapper extends BaseMapper<String ,ESGlobalVar>{
	
	/**
	 * 根据key获取全局参数
	 * @param key
	 * @return
	 */
	ESGlobalVar getGlobalVarByKey(@Param("key") String key );
	
}
