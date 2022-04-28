package com.efounder.JEnterprise.mapper.systemcenter.interfaceconfig;

import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceConfigurationModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionPointModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceTypeModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 14:48 2020/12/10
 * @Modified By:
 */
@Mapper
public interface DeviceConfigurationMapper {

    /**
     * 获取所有设备信息
     * @return
     */
    List<DeviceConfigurationModel> findAll();

    List<DeviceConfigurationModel> queryPage(@Param("deviceTypeId") String deviceTypeId,@Param("param") String param);

    /**
     * 查询设备信息，关联位置表查询
     * @param deviceTypeId
     * @param param
     * @return
     */
    List<DeviceConfigurationModel> queryPageNew(@Param("deviceTypeId") String deviceTypeId,@Param("param") String param);

    /**
     *
     * @Description: 获取所有的设备类型
     *
     * @auther: wanghongjie
     * @date: 16:16 2020/12/10
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
     * @date: 16:58 2020/12/10
     * @param: []
     * @return: java.util.List<com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceTypeModel>
     *
     * @param deviceConfigurationModel
     */
    Boolean create(DeviceConfigurationModel deviceConfigurationModel);

    /**
     *
     * @Description: 查询设备配置信息
     *
     * @auther: wanghongjie
     * @date: 9:22 2020/12/11
     * @param: [id]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    DeviceConfigurationModel query(String id);

    /**
     *
     * @Description: 修改设备配置信息
     *
     * @auther: wanghongjie
     * @date: 10:00 2020/12/11
     * @param: [deviceConfigurationModel]
     * @return: java.lang.Boolean
     *
     */
    Boolean update(DeviceConfigurationModel deviceConfigurationModel);

    /**
     *
     * @Description: 删除设备配置信息
     *
     * @auther: wanghongjie
     * @date: 11:09 2020/12/11
     * @param: [deviceConfigurationModel]
     * @return: java.lang.Boolean
     *
     */
    Boolean delete(DeviceConfigurationModel deviceConfigurationModel);

    /**
     *
     * @Description: 查询表中设备代码是否重复
     *
     * @auther: wanghongjie
     * @date: 11:52 2020/12/11
     * @param: [code]
     * @return: java.lang.Boolean
     *
     */
    List<DeviceConfigurationModel> selectCodeFromBesDevice(@Param("code") String code);
    /**
     *
     * @Description: 根据设备id查询功能点位表中是否关联此设备类型
     *
     * @auther: wanghongjie
     * @date: 8:57 2020/12/16
     * @param: [hdm]
     * @return: java.util.List<com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionModel>
     *
     */
    List<DeviceConfigurationModel> findDeviceFunctionPointList(DeviceConfigurationModel model);

    /**
     *
     * @Description: 查询当前设备类型下的设备功能和功能点位
     *
     * @auther: wanghongjie
     * @date: 15:44 2021/5/27
     * @param: [deviceTypeId, param]
     * @return: java.util.List<com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceConfigurationModel>
     *
     */
    List<DeviceFunctionModel> queryPage_function(@Param("deviceId") String deviceId,@Param("deviceTypeId") String deviceTypeId);

    /**
     *
     * @Description: 根据功能id查询关联的点位信息
     *
     * @auther: wanghongjie
     * @date: 9:45 2021/5/28
     * @param: [id]
     * @return: com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceConfigurationModel
     *
     */
    DeviceFunctionPointModel queryPoint(@Param("id") Integer id,@Param("deviceId") String deviceId);

    /**
     *
     * @Description: 删除配置的功能点位
     *
     * @auther: wanghongjie
     * @date: 8:30 2021/7/3
     * @param: [deviceFunctionPointID]
     * @return: java.lang.Boolean
     *
     */
    Boolean deletePoint(String deviceFunctionPointID);
}
