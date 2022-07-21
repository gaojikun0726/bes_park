package com.efounder.JEnterprise.service.systemcenter.interfaceconfig;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.*;
import com.github.pagehelper.PageInfo;


/**
 * @Author: wanghongjie
 * @Description:设备异常日志
 * @Date: Created in 14:42 2020/12/10
 * @Modified By:
 */
public interface DeviceExceptionLogService {

    PageInfo<DeviceExceptionLogModel> queryPage(String deviceTypeId, String positionId, Integer pageNum, String param);


    ISSPReturnObject deleteAll(String deviceTypeId, String positionId);

    ISSPReturnObject delete(DeviceExceptionLogModel deviceExceptionLogModel);


}
