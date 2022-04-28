package com.efounder.JEnterprise.mapper.basedatamanage.enegrycollectionmanage;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESEnergyType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 能效类型mapper接口
 * @author LvSihan
 *
 */
@Mapper
public interface BESEnergyTypeMapper extends BaseMapper<String, BESEnergyType>{
    int deleteByPrimaryKey(String fNybh);

    int insert(BESEnergyType record);

    int insertSelective(BESEnergyType record);

    BESEnergyType selectByPrimaryKey(String fNybh);

    int updateByPrimaryKeySelective(BESEnergyType record);

    int updateByPrimaryKey(BESEnergyType record);

	List<BESEnergyType> getEnergyTypeList(@Param("keywords") String keywords);

	boolean add_EnergyType(BESEnergyType besEnergyType);

	BESEnergyType getEnergyType(String fGuid);

	boolean energyType_del(String fGuid);

	boolean edit_EnergyType(BESEnergyType besEnergyType);
	
	/**
	 * 查询全部信息
	 * @author suhang
	 * @param 
	 * @return
	 */
	public List<BESEnergyType> getAllEnergyType();

	int checkRepeatFNybh(@Param(value = "fNybh") String fNybh);

	int checkRepeatFNymc(@Param(value = "fNymc") String fNymc);

	int checkEditRepeatFNymc(@Param(value = "fNymc") String fNymc,@Param(value = "fGuid") String fGuid);
}