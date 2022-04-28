package com.efounder.JEnterprise.mapper.dataAnalysises;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.dataAnalysises.BesEnergyData;
import org.apache.ibatis.annotations.Mapper;

/**
 * 能源数据Mapper接口
 * @author LvSihan
 *
 */
@Mapper
public interface BesEnergyDataMapper extends BaseMapper<String, BesEnergyData>{

	BesEnergyData queryEnergyExists(BesEnergyData besEnergyData);

	public void saveEnergyData(BesEnergyData besEnergyData);

	public void updateEnergy(BesEnergyData besEnergyData);

	Boolean updateEnergyData(BesEnergyData besEnergyData);

}