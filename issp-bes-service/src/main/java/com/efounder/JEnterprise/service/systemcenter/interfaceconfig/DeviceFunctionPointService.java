package com.efounder.JEnterprise.service.systemcenter.interfaceconfig;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceConfigurationModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionPointModel;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: wanghongjie
 * @Description:设备功能（接口管理模块）
 * @Date: Created in 14:42 2020/12/10
 * @Modified By:
 */
public interface DeviceFunctionPointService {

    /**
     * 获取所属设备功能点位
     * @return
     */
    List<DeviceFunctionPointModel> findAll();

    PageInfo<DeviceFunctionPointModel> queryPage(String deviceId,String deviceFunctionId, Integer pageNum, String param);

    /**
     *
     * @Description: 获取所有的设备
     *
     * @auther: wanghongjie
     * @date: 16:15 2020/12/10
     * @param: []
     * @return: java.util.List<com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceTypeModel>
     *
     */
    List<DeviceConfigurationModel> getDevice();

    /**
     *
     * @Description: 获取所有的设备功能
     *
     * @auther: wanghongjie
     * @date: 16:15 2020/12/10
     * @param: []
     * @return: java.util.List<com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceTypeModel>
     *
     */
    ISSPReturnObject getDeviceFunction(DeviceConfigurationModel deviceConfigurationModel);

    /**
     *
     * @Description: 添加功能点位
     *
     * @auther: wanghongjie
     * @date: 16:57 2020/12/10
     * @param: [deviceConfigurationModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject create(DeviceFunctionPointModel deviceFunctionPointModel);

    /**
     *
     * @Description: 查询功能点位信息
     *
     * @auther: wanghongjie
     * @date: 9:15 2020/12/11
     * @param: [deviceConfigurationModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject query(DeviceFunctionPointModel deviceFunctionPointModel);

    /**
     *
     * @Description: 修改功能点位信息
     *
     * @auther: wanghongjie
     * @date: 9:58 2020/12/11
     * @param: [deviceConfigurationModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject update(DeviceFunctionPointModel deviceFunctionPointModel);

    /**
     *
     * @Description: 删除功能点位信息
     *
     * @auther: wanghongjie
     * @date: 11:08 2020/12/11
     * @param: [deviceConfigurationModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject delete(DeviceFunctionPointModel deviceFunctionPointModel);

    /**
     *
     * @Description: 设备配置添加功能点位的逻辑
     *
     * @auther: wanghongjie
     * @date: 10:11 2021/5/28
     * @param: [deviceFunctionPointModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject add_update(DeviceFunctionPointModel deviceFunctionPointModel);
}
