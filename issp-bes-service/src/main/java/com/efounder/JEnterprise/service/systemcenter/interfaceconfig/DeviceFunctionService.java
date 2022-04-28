package com.efounder.JEnterprise.service.systemcenter.interfaceconfig;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionValueModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceTypeModel;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: wanghongjie
 * @Description:设备功能（接口管理模块）
 * @Date: Created in 14:42 2020/12/10
 * @Modified By:
 */
public interface DeviceFunctionService {

    /**
     * 获取所有设备功能
     * @return
     */
    List<DeviceFunctionModel> findAll();

    PageInfo<DeviceFunctionModel> queryPage(String deviceTypeId, Integer pageNum, String param);

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
    List<DeviceTypeModel> getDeviceType();

    /**
     *
     * @Description: 添加设备功能
     *
     * @auther: wanghongjie
     * @date: 16:57 2020/12/10
     * @param: [deviceConfigurationModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject create(DeviceFunctionModel deviceFunctionModel);

    /**
     *
     * @Description: 查询设备功能信息
     *
     * @auther: wanghongjie
     * @date: 9:15 2020/12/11
     * @param: [deviceConfigurationModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject query(DeviceFunctionModel deviceFunctionModel);

    /**
     *
     * @Description: 修改设备功能信息
     *
     * @auther: wanghongjie
     * @date: 9:58 2020/12/11
     * @param: [deviceConfigurationModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject update(DeviceFunctionModel deviceFunctionModel);

    /**
     *
     * @Description: 删除设备功能信息
     *
     * @auther: wanghongjie
     * @date: 11:08 2020/12/11
     * @param: [deviceConfigurationModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject delete(DeviceFunctionModel deviceFunctionModel);

    /**
     *
     * @Description: 加载功能值的页面
     *
     * @auther: wanghongjie
     * @date: 14:50 2021/5/28
     * @param: [deviceFunctionId]
     * @return: com.github.pagehelper.PageInfo<com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionValueModel>
     *
     */
    PageInfo<DeviceFunctionValueModel> query_valuePage(String deviceFunctionId);

    /**
     *
     * @Description: 添加功能值
     *
     * @auther: wanghongjie
     * @date: 15:30 2021/5/28
     * @param: [deviceFunctionValueModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject create_value(DeviceFunctionValueModel deviceFunctionValueModel);

    /**
     *
     * @Description: 回显功能值
     *
     * @auther: wanghongjie
     * @date: 16:45 2021/5/28
     * @param: [deviceFunctionValueModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject query_value(DeviceFunctionValueModel deviceFunctionValueModel);

    /**
     *
     * @Description: 修改功能值
     *
     * @auther: wanghongjie
     * @date: 16:52 2021/5/28
     * @param: [deviceFunctionValueModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject update_value(DeviceFunctionValueModel deviceFunctionValueModel);

    /**
     *
     * @Description: 删除功能值
     *
     * @auther: wanghongjie
     * @date: 17:41 2021/5/28
     * @param: [deviceFunctionValueModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject delete_value(DeviceFunctionValueModel deviceFunctionValueModel);
}
