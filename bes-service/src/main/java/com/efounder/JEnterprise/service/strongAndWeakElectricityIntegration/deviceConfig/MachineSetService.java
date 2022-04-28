package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration.deviceConfig;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.deviceConfig.MachineSetModel;
import com.github.pagehelper.PageInfo;

/**
 * 机组
 * @author xiepufeng
 * @date 2020/11/24 17:19
 */
public interface MachineSetService
{
    ISSPReturnObject create(MachineSetModel machineSetModel);

    ISSPReturnObject query(MachineSetModel machineSetModel);

    ISSPReturnObject update(MachineSetModel machineSetModel);

    ISSPReturnObject delete(MachineSetModel machineSetModel);

    PageInfo<MachineSetModel> queryPage(String keywords, Integer pageNum);
}
