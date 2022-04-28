package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.deviceConfig;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.deviceConfig.AmmeterConfigModel;
import com.github.pagehelper.PageInfo;

/**
 * @author xiepufeng
 * @date 2019/12/16 14:31
 */
public interface AmmeterConfigService {

    ISSPReturnObject create(AmmeterConfigModel ammeterConfigModel);

    PageInfo<Object> getPaging(Integer pageSize, Integer pageNum, AmmeterConfigModel ammeterConfigModel);

    ISSPReturnObject query(AmmeterConfigModel ammeterConfigModel);

    ISSPReturnObject update(AmmeterConfigModel ammeterConfigModel);

    ISSPReturnObject delete(AmmeterConfigModel ammeterConfigModel);
}
