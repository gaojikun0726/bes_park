package com.efounder.JEnterprise.mapper.systemcenter.interfaceconfig;

import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionValueModel;
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
public interface DeviceFunctionMapper {

    /**
     * 获取所有设备功能
     * @return
     */
    List<DeviceFunctionModel> findAll();

    List<DeviceFunctionModel> queryPage(@Param("deviceTypeId") String deviceTypeId, @Param("param") String param);

    /**
     *
     * @Description: 获取所有的设备类型
     *
     * @auther: wanghongjie
     * @date: 16:16 2020/12/10
     */
    List<DeviceTypeModel> getDeviceType();

    /**
     *
     * @Description: 添加设备功能
     *
     * @auther: wanghongjie
     * @date: 16:58 2020/12/10
     * @param deviceFunctionModel
     */
    Boolean create(DeviceFunctionModel deviceFunctionModel);

    /**
     *
     * @Description: 查询设备功能信息
     *
     * @auther: wanghongjie
     * @date: 9:22 2020/12/11
     * @param: [id]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    DeviceFunctionModel query(Integer id);

    /**
     *
     * @Description: 修改设备功能信息
     *
     * @auther: wanghongjie
     * @date: 10:00 2020/12/11
     * @param: [deviceConfigurationModel]
     * @return: java.lang.Boolean
     *
     */
    Boolean update(DeviceFunctionModel deviceFunctionModel);

    /**
     *
     * @Description: 删除设备功能信息
     *
     * @auther: wanghongjie
     * @date: 11:09 2020/12/11
     * @param: [deviceConfigurationModel]
     * @return: java.lang.Boolean
     *
     */
    Boolean delete(DeviceFunctionModel deviceFunctionModel);

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
    List<DeviceFunctionModel> selectCodeFromBesDevice(@Param("code") String code);

    /**
     *
     * @Description: 根据设备功能id查询功能点位表中是否关联此设备功能
     *
     * @auther: wanghongjie
     * @date: 9:13 2020/12/16
     * @param: [model]
     * @return: java.util.List<com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionModel>
     *
     */
    List<DeviceFunctionModel> findDeviceFunctionPointList(DeviceFunctionModel model);

    /**
     *
     * @Description: 加载功能值的页面
     *
     * @auther: wanghongjie
     * @date: 14:52 2021/5/28
     * @param: [deviceFunctionId]
     * @return: java.util.List<com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionValueModel>
     *
     */
    List<DeviceFunctionValueModel> query_valuePage(String deviceFunctionId);

    /**
     *
     * @Description: 添加功能值
     *
     * @auther: wanghongjie
     * @date: 15:33 2021/5/28
     * @param: [deviceFunctionValueModel]
     * @return: java.lang.Boolean
     *
     */
    Boolean create_value(DeviceFunctionValueModel deviceFunctionValueModel);

    /**
     *
     * @Description: 回显功能值
     *
     * @auther: wanghongjie
     * @date: 16:47 2021/5/28
     * @param: [id]
     * @return: com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionValueModel
     *
     */
    DeviceFunctionValueModel query_value(Integer id);

    /**
     * 获取全部功能值
     * @return
     */
    List<DeviceFunctionValueModel> findAllFunctionValue();

    /**修改功能值
     *
     * @param deviceFunctionValueModel
     * @return
     */
    Boolean update_value(DeviceFunctionValueModel deviceFunctionValueModel);

    /**
     *
     * @Description: 删除功能值
     *
     * @auther: wanghongjie
     * @date: 17:44 2021/5/28
     * @param: [id]
     * @return: java.lang.Boolean
     *
     */
    Boolean delete_value(Integer id);

    /**
     *
     * @Description: 判断当前设备功能是否关联功能值
     *
     * @auther: wanghongjie
     * @date: 17:48 2021/5/28
     * @param: [id]
     * @return: java.util.List<com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionValueModel>
     *
     */
    List<DeviceFunctionValueModel> deviceFunctionValueModelList(Integer id);
}
