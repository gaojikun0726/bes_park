package com.efounder.JEnterprise.service.systemcenter.interfaceconfig.Impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.initializer.DeviceFunctionPointCache;
import com.efounder.JEnterprise.initializer.SbPzStructCache;
import com.efounder.JEnterprise.mapper.systemcenter.interfaceconfig.DeviceFunctionPointMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESSbPzStruct;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceConfigurationModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionPointModel;
import com.efounder.JEnterprise.service.systemcenter.interfaceconfig.DeviceFunctionPointService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 14:45 2020/12/10
 * @Modified By:功能点位
 */
@Service
public class DeviceFunctionPointServiceImpl implements DeviceFunctionPointService
{
    @Resource
    private DeviceFunctionPointMapper deviceFunctionPointMapper;

    @Resource
    private DeviceFunctionPointCache deviceFunctionPointCache;

    @Resource
    private SbPzStructCache sbPzStructCache;


    /**
     * 获取所属设备功能点位
     *
     * @return
     */
    @Override
    public List<DeviceFunctionPointModel> findAll()
    {

        List<DeviceFunctionPointModel> data = new ArrayList<>();

        List<DeviceFunctionPointModel> deviceFunctionPointModels = deviceFunctionPointMapper.findAll();

        if (deviceFunctionPointModels == null || deviceFunctionPointModels.isEmpty())
        {
            return data;
        }

        deviceFunctionPointModels.forEach(item -> {
            String sysNameOld = item.getSysName();

            BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElementBySysNameOld(sysNameOld);

            if (besSbPzStruct == null)
            {
                return;
            }

            item.setValue(besSbPzStruct.getF_init_val());
        });

        return deviceFunctionPointModels;
    }

    @Override
    public PageInfo<DeviceFunctionPointModel> queryPage(String deviceId, String deviceFunctionId, Integer pageNum, String param)
    {
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
        List<DeviceFunctionPointModel> list = deviceFunctionPointMapper.queryPage(deviceId, deviceFunctionId, param);
        return new PageInfo<>(list);
    }

    /**
     * @Description: 获取所有的设备
     * @auther: wanghongjie
     * @date: 16:15 2020/12/10
     * @param: []
     * @return: java.util.List<com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceConfigurationModel>
     */
    @Override
    public List<DeviceConfigurationModel> getDevice()
    {
        List<DeviceConfigurationModel> getDeviceType = deviceFunctionPointMapper.getDevice();
        return getDeviceType;
    }

    /**
     * @Description: 获取所有的功能
     * @auther: wanghongjie
     * @date: 16:15 2020/12/10
     * @param: []
     * @return: java.util.List<com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionModel>
     */
    @Override
    public ISSPReturnObject getDeviceFunction(DeviceConfigurationModel deviceConfigurationModel)
    {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        if (deviceConfigurationModel == null)
        {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }
        String deviceTypeId = deviceConfigurationModel.getDeviceTypeId();

        if (!StringUtils.hasText(deviceTypeId))
        {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }
        //根据设备所关联的设备类型查询当前设备类型的功能
        List<DeviceFunctionModel> getDeviceType = deviceFunctionPointMapper.getDeviceFunction(deviceTypeId);
        returnObject.setList(getDeviceType);
        return returnObject;
    }

    /**
     * @Description: 添加功能点位
     * @auther: wanghongjie
     * @date: 16:57 2020/12/10
     * @param: [deviceConfigurationModel]
     * @return: com.core.common.ISSPReturnObject
     */
    @Override
    public ISSPReturnObject create(DeviceFunctionPointModel deviceFunctionPointModel)
    {
        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (deviceFunctionPointModel == null)
        {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }

        Integer deviceFunctionId = deviceFunctionPointModel.getDeviceFunctionId();
        String deviceId = deviceFunctionPointModel.getDeviceId();
        String name = deviceFunctionPointModel.getName();
        String sysName = deviceFunctionPointModel.getSysName();
        if (deviceFunctionId == null ||
                deviceId == null ||
                !StringUtils.hasText(name) ||
                !StringUtils.hasText(sysName))
        {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }

        Boolean getDeviceType = deviceFunctionPointMapper.create(deviceFunctionPointModel);

        if (getDeviceType)
        {
            BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElementBySysNameOld(sysName);

            if (besSbPzStruct != null)
            {
                deviceFunctionPointModel.setValue(besSbPzStruct.getF_init_val());
            }

            // 添加加到缓存
            deviceFunctionPointCache.addOneDeviceFunctionPointCache(deviceFunctionPointModel);

            returnObject.setStatus("1");
            returnObject.setMsg("添加成功");
        } else
        {
            returnObject.setStatus("0");
            returnObject.setMsg("添加失败");
        }


        return returnObject;
    }

    /**
     * @Description: 查询功能点位信息
     * @auther: wanghongjie
     * @date: 9:22 2020/12/11
     * @param: [deviceConfigurationModel]
     * @return: com.core.common.ISSPReturnObject
     */
    @Override
    public ISSPReturnObject query(DeviceFunctionPointModel deviceFunctionPointModel)
    {
        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (deviceFunctionPointModel == null)
        {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }

        Integer id = deviceFunctionPointModel.getId();
        if (id == null)
        {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }
        DeviceFunctionPointModel deviceConfiguration = deviceFunctionPointMapper.query(id);
        returnObject.setData(deviceConfiguration);
        return returnObject;
    }

    /**
     * @Description: 修改功能点位信息
     * @auther: wanghongjie
     * @date: 10:00 2020/12/11
     * @param: [deviceConfigurationModel]
     * @return: com.core.common.ISSPReturnObject
     */
    @Override
    public ISSPReturnObject update(DeviceFunctionPointModel deviceFunctionPointModel)
    {
        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (deviceFunctionPointModel == null)
        {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }

        Integer id = deviceFunctionPointModel.getId();
        Integer deviceFunctionId = deviceFunctionPointModel.getDeviceFunctionId();
        String deviceId = deviceFunctionPointModel.getDeviceId();
        String name = deviceFunctionPointModel.getName();
        String sysName = deviceFunctionPointModel.getSysName();
        if (deviceFunctionId == null ||
                deviceId == null ||
                !StringUtils.hasText(name) ||
                !StringUtils.hasText(sysName) ||
                id == null)
        {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }


        Boolean deviceFunctionPoint = deviceFunctionPointMapper.update(deviceFunctionPointModel);
        if (deviceFunctionPoint)
        {

            BESSbPzStruct besSbPzStruct = sbPzStructCache.getCachedElementBySysNameOld(sysName);

            if (besSbPzStruct != null)
            {
                deviceFunctionPointModel.setValue(besSbPzStruct.getF_init_val());
            }

            // 更新缓存
            deviceFunctionPointCache.updateOneDeviceFunctionPointCache(deviceFunctionPointModel);

            returnObject.setStatus("1");
            returnObject.setMsg("修改成功");
        } else
        {
            returnObject.setStatus("0");
            returnObject.setMsg("修改失败");
        }

        return returnObject;
    }

    /**
     * @Description: 删除功能点位信息
     * @auther: wanghongjie
     * @date: 11:09 2020/12/11
     * @param: [deviceConfigurationModel]
     * @return: com.core.common.ISSPReturnObject
     */
    @Override
    public ISSPReturnObject delete(DeviceFunctionPointModel deviceFunctionPointModel)
    {
        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (deviceFunctionPointModel == null)
        {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }

        Integer id = deviceFunctionPointModel.getId();
        if (id == null)
        {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }
        Boolean del = deviceFunctionPointMapper.delete(deviceFunctionPointModel);
        if (del)
        {
            // 删除缓存数据
            deviceFunctionPointCache.deleteOneDeviceFunctionPointCache(id);

            returnObject.setStatus("1");
            returnObject.setMsg("删除成功");
        } else
        {
            returnObject.setStatus("0");
            returnObject.setMsg("删除失败");
        }
        return returnObject;
    }

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
    @Override
    public ISSPReturnObject add_update(DeviceFunctionPointModel deviceFunctionPointModel) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        ISSPReturnObject returnObject1 = new ISSPReturnObject();

        if (deviceFunctionPointModel == null)
        {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }

        Integer deviceFunctionId = deviceFunctionPointModel.getDeviceFunctionId();
        String deviceId = deviceFunctionPointModel.getDeviceId();
        String name = deviceFunctionPointModel.getName();
        String sysName = deviceFunctionPointModel.getSysName();
        if (deviceFunctionId == null ||
                deviceId == null ||
                !StringUtils.hasText(name) ||
                !StringUtils.hasText(sysName))
        {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }

        Integer id = deviceFunctionPointModel.getId();

        //添加前判断此点位是否配置过
        List<DeviceFunctionPointModel> deviceConfiguration = deviceFunctionPointMapper.queryBySysName(sysName);
        if (deviceConfiguration.size() > 0) {

            if (id != null) {
                if (deviceConfiguration.get(0).getId() != id) {
                    returnObject.setStatus("0");
                    returnObject.setMsg("当前点位已配置,请重新选择点位");
                    return returnObject;
                }
            } else {
                returnObject.setStatus("0");
                returnObject.setMsg("当前点位已配置,请重新选择点位");
                return returnObject;
            }
        }

        if(id == null) {//添加

            returnObject1 = create(deviceFunctionPointModel);
            returnObject.setStatus(returnObject1.getStatus());
            returnObject.setMsg(returnObject1.getMsg());
//            Boolean getDeviceType = deviceFunctionPointMapper.create(deviceFunctionPointModel);
//            if (getDeviceType)
//            {
//                // 添加加到缓存
//                deviceFunctionPointCache.addOneDeviceFunctionPointCache(deviceFunctionPointModel);
//
//                returnObject.setStatus("1");
//                returnObject.setMsg("添加成功");
//            } else
//            {
//                returnObject.setStatus("0");
//                returnObject.setMsg("添加失败");
//            }

        } else {//修改
            returnObject1 = update(deviceFunctionPointModel);
            returnObject.setStatus(returnObject1.getStatus());
            returnObject.setMsg(returnObject1.getMsg());
//            Boolean DeviceFunctionPoint = deviceFunctionPointMapper.update(deviceFunctionPointModel);
//            if (DeviceFunctionPoint)
//            {
//                // 更新缓存
//                deviceFunctionPointCache.updateOneDeviceFunctionPointCache(deviceFunctionPointMapper.query(id));
//
//                returnObject.setStatus("1");
//                returnObject.setMsg("修改成功");
//            } else
//            {
//                returnObject.setStatus("0");
//                returnObject.setMsg("修改失败");
//            }
        }

        return returnObject;
    }

}