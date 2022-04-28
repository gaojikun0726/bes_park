package com.efounder.JEnterprise.service.systemcenter.interfaceconfig;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceConfigurationModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionPointModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceTypeModel;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: wanghongjie
 * @Description:设备配置（接口管理模块）
 * @Date: Created in 14:42 2020/12/10
 * @Modified By:
 */
public interface DeviceConfigurationService {

    /**
     * 获取所有设备数据
     * @return
     */
    List<DeviceConfigurationModel> findAll();

    PageInfo<DeviceConfigurationModel> queryPage(String deviceTypeId, Integer pageNum,String param);

    /**
     *
     * @Description: 获取所有的设备类型
     *
     * @auther: wanghongjie
     * @date: 16:15 2020/12/10
     * @param: []
     * @return: java.util.List<com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceTypeModel>
     *
     */
    List<DeviceTypeModel> getDeviceType();

    /**
     *
     * @Description: 添加设备配置
     *
     * @auther: wanghongjie
     * @date: 16:57 2020/12/10
     * @param: [deviceConfigurationModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject create(DeviceConfigurationModel deviceConfigurationModel);

    /**
     *
     * @Description: 查询设备配置信息
     *
     * @auther: wanghongjie
     * @date: 9:15 2020/12/11
     * @param: [deviceConfigurationModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject query(DeviceConfigurationModel deviceConfigurationModel);

    /**
     *
     * @Description: 修改设备配置信息
     *
     * @auther: wanghongjie
     * @date: 9:58 2020/12/11
     * @param: [deviceConfigurationModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject update(DeviceConfigurationModel deviceConfigurationModel);

    /**
     *
     * @Description: 删除设备配置信息
     *
     * @auther: wanghongjie
     * @date: 11:08 2020/12/11
     * @param: [deviceConfigurationModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject delete(DeviceConfigurationModel deviceConfigurationModel);

    /**
     *
     * @Description: 查询当前设备类型下的设备功能和功能点位
     *
     * @auther: wanghongjie
     * @date: 15:43 2021/5/27
     * @param: [deviceTypeId, pageNum, param]
     * @return: com.github.pagehelper.PageInfo<com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceConfigurationModel>
     *
     */
    PageInfo<DeviceFunctionModel> queryPage_function(String deviceId,String deviceTypeId, Integer pageNum, String param);

    /**
     *
     * @Description: 根据功能id查询关联的点位信息
     *
     * @auther: wanghongjie
     * @date: 9:44 2021/5/28
     * @param: [deviceConfigurationModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject queryPoint(DeviceFunctionPointModel deviceFunctionPointModel);

    /**
     *
     * @Description: 删除配置的功能点位
     *
     * @auther: wanghongjie
     * @date: 8:29 2021/7/3
     * @param: [deviceFunctionPointID]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject deletePoint(String deviceFunctionPointID);
}
