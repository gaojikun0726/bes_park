package com.efounder.JEnterprise.service.basedatamanage.enegrycollectionmanage;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESEnergyType;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 能源类型接口
 * @author LvSihan
 *
 */
public interface BESEnergyTypeService {

	PageInfo<BESEnergyType> getEnergyTypeList(String keywords, Integer pageNum);

	public ISSPReturnObject add_EnergyType(BESEnergyType besEnergyType);

	public ISSPReturnObject energyType_del(String fGuid);

	ISSPReturnObject queryEnergyType(String fGuid);

	ISSPReturnObject edit_EnergyType(BESEnergyType besEnergyType);

    List<BESEnergyType> findAll();

	/**
	 * 去重
	 * @param fNybh
	 * @param fNymc
	 * @return
	 */
	public ISSPReturnObject checkRepeat(String fNybh,String fNymc);

	/**
	 * 去重
	 * @param fNymc
	 * @return
	 */
	public ISSPReturnObject checkEditRepeat(String fNymc,String fGuid);

}
