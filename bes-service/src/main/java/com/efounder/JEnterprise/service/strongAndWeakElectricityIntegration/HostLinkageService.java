package com.efounder.JEnterprise.service.strongAndWeakElectricityIntegration;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.HostLinkageDeviceModel;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.HostLinkageModel;
import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.HostLinkageRunningModeModel;

/**
 * 主机联动（主机）
 * @author xiepufeng
 * @date 2020/2/11 15:37
 */
public interface HostLinkageService {

    ISSPReturnObject createHost(HostLinkageModel hostLinkageModel);

    ISSPReturnObject queryHost(HostLinkageModel hostLinkageModel);

    ISSPReturnObject deleteHost(HostLinkageModel hostLinkageModel);

    ISSPReturnObject updateHost(HostLinkageModel hostLinkageModel);

    ISSPReturnObject createDevice(HostLinkageDeviceModel hostLinkageDeviceModel);

    ISSPReturnObject queryDevice(HostLinkageDeviceModel hostLinkageDeviceModel);

    ISSPReturnObject deleteDevice(HostLinkageDeviceModel hostLinkageDeviceModel);

    ISSPReturnObject updateDevice(HostLinkageDeviceModel hostLinkageDeviceModel);

    ISSPReturnObject queryJoinPoint(HostLinkageRunningModeModel hostLinkageRunningModeModel);

    ISSPReturnObject createJoinPoint(HostLinkageRunningModeModel hostLinkageRunningModeModel);

    ISSPReturnObject updateJoinPoint(HostLinkageRunningModeModel hostLinkageRunningModeModel);
}
