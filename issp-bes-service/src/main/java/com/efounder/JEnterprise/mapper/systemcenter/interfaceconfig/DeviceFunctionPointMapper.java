package com.efounder.JEnterprise.mapper.systemcenter.interfaceconfig;

import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceConfigurationModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionPointModel;
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
public interface DeviceFunctionPointMapper {

    /**
     * 获取所属设备功能点位
     * @return
     */
    List<DeviceFunctionPointModel> findAll();

    List<DeviceFunctionPointModel> queryPage(@Param("deviceId") String deviceId,@Param("deviceFunctionId") String deviceFunctionId, @Param("param") String param);

    /**
     *
     * @Description: 获取所有的设备
     *
     * @auther: wanghongjie
     * @date: 16:16 2020/12/10
     */
    List<DeviceConfigurationModel> getDevice();

    /**
     *
     * @Description: 获取所有的设备功能
     *
     * @auther: wanghongjie
     * @date: 16:16 2020/12/10
     */
    List<DeviceFunctionModel> getDeviceFunction(@Param("deviceTypeId") String deviceTypeId);

    /**
     *
     * @Description: 添加功能点位
     *
     * @auther: wanghongjie
     * @date: 16:58 2020/12/10
     * @param deviceFunctionPointModel
     */
    Boolean create(DeviceFunctionPointModel deviceFunctionPointModel);

    /**
     *
     * @Description: 查询功能点位信息
     *
     * @auther: wanghongjie
     * @date: 9:22 2020/12/11
     * @param: [id]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    DeviceFunctionPointModel query(Integer id);

    /**
     *
     * @Description: 修改功能点位信息
     *
     * @auther: wanghongjie
     * @date: 10:00 2020/12/11
     * @param: [deviceConfigurationModel]
     * @return: java.lang.Boolean
     *
     */
    Boolean update(DeviceFunctionPointModel deviceFunctionPointModel);

    /**
     *
     * @Description: 删除功能点位信息
     *
     * @auther: wanghongjie
     * @date: 11:09 2020/12/11
     * @param: [deviceConfigurationModel]
     * @return: java.lang.Boolean
     *
     */
    Boolean delete(DeviceFunctionPointModel deviceFunctionPointModel);

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
    List<DeviceFunctionPointModel> selectCodeFromBesDevice(@Param("code") String code);

    /**
     *
     * @Description: 添加前判断此点位是否配置过
     *
     * @auther: wanghongjie
     * @date: 10:25 2021/5/28
     * @param: [sysName]
     * @return: com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionPointModel
     *
     */
    List<DeviceFunctionPointModel> queryBySysName(String sysName);

}
