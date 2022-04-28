package com.efounder.JEnterprise.service.basedatamanage.enegrycollectionmanage;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESElectricParams;
/**
 * 采集参数接口
 * @author LvSihan
 *
 */
public interface BESElectricParamsService {

	public ISSPReturnObject getElectricPList(String keywords);

	public ISSPReturnObject ElectricP_tree();

	public ISSPReturnObject ElectricP_chlildtreegrid(String fNybh);

	public ISSPReturnObject add_ElectricP(BESElectricParams besElectricParams);

	public ISSPReturnObject del_ElectricP(String fDnbh);

	public ISSPReturnObject getElectricP(String fDnbh);

	public ISSPReturnObject edit_ElectricP(BESElectricParams besElectricParams);

	public ISSPReturnObject getfbmgz();
}
