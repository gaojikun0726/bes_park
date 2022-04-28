package com.efounder.JEnterprise.mapper.systemcenter.interfaceconfig;

import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceConfigurationModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceTypeModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 设备类型（接口管理模块）
 * @author xiepufeng
 * @date 2020/12/8 20:36
 */
@Mapper
public interface DeviceTypeMapper
{
    /**
     *  获取所有所有数据
     * @return
     */
    List<DeviceTypeModel> findAll();

    List<DeviceTypeModel> queryPage(@Param("keywords") String keywords);

    List<DeviceTypeModel> findList(DeviceTypeModel deviceTypeModel);

    void insert(DeviceTypeModel deviceTypeModel);

    void update(DeviceTypeModel deviceTypeModel);

    void delete(Integer id);
    /**
     *
     * @Description: 查询表中设备类型代码是否重复
     *
     * @auther: wanghongjie
     * @date: 15:39 2020/12/11
     * @param: [code]
     * @return: java.util.List<com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceTypeModel>
     *
     */
    List<DeviceTypeModel> selectCodeFromBesDeviceType(@Param("code") String code);

    /**
     *
     * @Description: 根据设备类型id查询设备表中是否关联此设备类型
     *
     * @auther: wanghongjie
     * @date: 16:20 2020/12/11
     * @param: [hdm]
     * @return: java.util.List<com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceConfigurationModel>
     *
     */
    List<DeviceConfigurationModel> findDeviceConfigurationList(DeviceConfigurationModel hdm);

    /**
     *
     * @Description: 根据设备类型id查询设备功能表中是否关联此设备类型
     *
     * @auther: wanghongjie
     * @date: 16:36 2020/12/14
     * @param: [hdm]
     * @return: java.util.List<com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceConfigurationModel>
     *
     */
    List<DeviceFunctionModel> findDeviceFunctionList(DeviceConfigurationModel hdm);

}
