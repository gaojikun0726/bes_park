package com.efounder.JEnterprise.mapper.basedatamanage.enegrycollectionmanage;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESAmmeterType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * 电表类型mapper接口
 * @author LvSihan
 * @modify suhang
 */
@Mapper
public interface BESAmmeterTypeMapper extends BaseMapper<String, BESAmmeterType>{

	List<BESAmmeterType> getAmmeterTypeList(@Param(value = "keywords")String keywords);
	
	boolean add_AmmeterType(BESAmmeterType besAmmeterType);

	BESAmmeterType getAmmeterType(String fLxbh);

	boolean del_AmmeterType(String fLxbh);

	boolean edit_AmmeterType(BESAmmeterType besAmmeterType);

	/**
	 * 查询全部或新增的仪表类型数据列表
	 * @param map 上次请求时间（可选）
	 * @return
	 */
	List<Map> queryAmmeterTypeList(Map<String,Object> map);
}