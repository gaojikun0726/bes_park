package com.efounder.JEnterprise.service.systemcenter.interfaceconfig;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceTypeModel;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 设备类型（接口管理模块）
 * @author xiepufeng
 * @date 2020/12/8 20:32
 */
public interface DeviceTypeService
{

    List<DeviceTypeModel> findAll();

    PageInfo<DeviceTypeModel> queryPage(String keywords, Integer pageNum);

    ISSPReturnObject create(DeviceTypeModel deviceTypeModel);

    ISSPReturnObject query(DeviceTypeModel deviceTypeModel);

    ISSPReturnObject update(DeviceTypeModel deviceTypeModel);

    ISSPReturnObject delete(DeviceTypeModel deviceTypeModel);
}
