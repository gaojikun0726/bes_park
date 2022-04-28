package com.efounder.JEnterprise.mapper.basedatamanage.enegrycollectionmanage;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESCollMethod;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * 采集方案mapper接口
 * @author LvSihan
 *
 */
@Mapper
public interface BESCollMethodMapper extends BaseMapper<String, BESCollMethod>{
    int insert(BESCollMethod record);

    int insertSelective(BESCollMethod record);

	List<BESCollMethod> getCollMethodList(@Param(value = "keywords")String keywords);
	
	List<BESCollMethod> getCMbyZzjgbh(@Param(value = "fyqbh")String fyqbh,@Param(value = "fnybh")String fnybh);

	boolean add_CollM(BESCollMethod besCollMethod);

	BESCollMethod getCMbyCjfabh(String fCjfabh);

	boolean edit_CollM(BESCollMethod besCollMethod);
	
	boolean del_CollM(BESCollMethod besCollMethod);

	List <Map<String,String >> findPark_EnergyType(@Param(value = "fyqbh")String fyqbh);

	/**
	 * 获取全部采集方案数据
	 * @return
	 */
	List<BESCollMethod> loadAll();
}