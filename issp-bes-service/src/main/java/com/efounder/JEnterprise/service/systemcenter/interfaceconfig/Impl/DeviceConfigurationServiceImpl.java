package com.efounder.JEnterprise.service.systemcenter.interfaceconfig.Impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.initializer.DeviceConfigurationCache;
import com.efounder.JEnterprise.mapper.systemcenter.interfaceconfig.DeviceConfigurationMapper;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceConfigurationModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionPointModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceTypeModel;
import com.efounder.JEnterprise.service.systemcenter.interfaceconfig.DeviceConfigurationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 14:45 2020/12/10
 * @Modified By:设备配置
 */
@Service
public class DeviceConfigurationServiceImpl implements DeviceConfigurationService {
    @Resource
    private DeviceConfigurationMapper deviceConfigurationMapper;

    @Resource
    private DeviceConfigurationCache deviceConfigurationCache;

    /**
     * 获取所有设备
     * @return
     */
    @Override
    public List<DeviceConfigurationModel> findAll()
    {
        return deviceConfigurationMapper.findAll();
    }

    @Override
    public PageInfo<DeviceConfigurationModel> queryPage(String deviceTypeId,String positionId, Integer pageNum,String param) {
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
        List<DeviceConfigurationModel> list = deviceConfigurationMapper.queryPageNew(deviceTypeId,positionId,param);
        return new PageInfo<>(list);
    }

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
    @Override
    public List<DeviceTypeModel> getDeviceType() {
        List<DeviceTypeModel> getDeviceType = deviceConfigurationMapper.getDeviceType();
        return getDeviceType;
    }

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
    @Override
    public ISSPReturnObject create(DeviceConfigurationModel deviceConfigurationModel) {
        String deviceId = UUID.randomUUID().toString();
        deviceConfigurationModel.setId(deviceId);
        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (deviceConfigurationModel == null)
        {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }

        //获取设备代码
        String code = deviceConfigurationModel.getCode();
        String deviceTypeId = deviceConfigurationModel.getDeviceTypeId();
        String name = deviceConfigurationModel.getName();

        if (!StringUtils.hasText(code) ||
                !StringUtils.hasText(deviceTypeId) ||
                !StringUtils.hasText(name)) {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }
        //查询表中设备代码是否重复
        List<DeviceConfigurationModel>  codeList = deviceConfigurationMapper.selectCodeFromBesDevice(code);
        if (codeList == null) {
            returnObject.setStatus("0");
            returnObject.setMsg("查询失败");
            return returnObject;
        }
        if (codeList.size() > 0) {
            returnObject.setStatus("0");
            returnObject.setMsg("添加失败,设备代码重复");
        } else {
            Boolean getDeviceType = deviceConfigurationMapper.create(deviceConfigurationModel);

            // 添加到缓存
            deviceConfigurationCache.addOneDeviceConfigurationCache(deviceConfigurationMapper.query(deviceId));

            if (getDeviceType) {
                returnObject.setStatus("1");
                returnObject.setMsg("添加成功");
            }else {
                returnObject.setStatus("0");
                returnObject.setMsg("添加失败");
            }
        }


        return returnObject;
    }

    /**
     *
     * @Description: 查询设备配置信息
     *
     * @auther: wanghongjie
     * @date: 9:22 2020/12/11
     * @param: [deviceConfigurationModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Override
    public ISSPReturnObject query(DeviceConfigurationModel deviceConfigurationModel) {
        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (deviceConfigurationModel == null)
        {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }

        String id = deviceConfigurationModel.getId();
        if (id == null) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }
        DeviceConfigurationModel deviceConfiguration = deviceConfigurationMapper.query(id);
        returnObject.setData(deviceConfiguration);
        return returnObject;
    }

    /**
     *
     * @Description: 修改设备配置信息
     *
     * @auther: wanghongjie
     * @date: 10:00 2020/12/11
     * @param: [deviceConfigurationModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Override
    public ISSPReturnObject update(DeviceConfigurationModel deviceConfigurationModel) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (deviceConfigurationModel == null)
        {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }

        String id = deviceConfigurationModel.getId();
        String code = deviceConfigurationModel.getCode();
        String deviceTypeId = deviceConfigurationModel.getDeviceTypeId();
        String name = deviceConfigurationModel.getName();

        if (id == null || !StringUtils.hasText(code) || !StringUtils.hasText(deviceTypeId) || !StringUtils.hasText(name))
        {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }

        DeviceConfigurationModel model = deviceConfigurationMapper.query(id);

        if (model == null)
        {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }

        //获取设备代码

        //查询表中设备代码是否重复
        List<DeviceConfigurationModel> codeList = deviceConfigurationMapper.selectCodeFromBesDevice(code);
        if (codeList.size() > 0) {
            if (!codeList.get(0).getId().equals(deviceConfigurationModel.getId())) {//如果修改的是本身的设备配置,则不判断设备代码是否重复
                returnObject.setStatus("0");
                returnObject.setMsg("修改失败,设备代码重复");
                return returnObject;
            }
        }

        Boolean DeviceConfiguration = deviceConfigurationMapper.update(deviceConfigurationModel);

        // 更新缓存
        DeviceConfigurationModel dcf = deviceConfigurationMapper.query(id);
        deviceConfigurationCache.updateOneDeviceConfigurationCache(dcf);

        if (DeviceConfiguration) {
            returnObject.setStatus("1");
            returnObject.setMsg("修改成功");
        } else {
            returnObject.setStatus("0");
            returnObject.setMsg("修改失败");
        }

        return returnObject;
    }

    /**
     *
     * @Description: 删除设备配置信息
     *
     * @auther: wanghongjie
     * @date: 11:09 2020/12/11
     * @param: [deviceConfigurationModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Override
    public ISSPReturnObject delete(DeviceConfigurationModel deviceConfigurationModel) {
        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (deviceConfigurationModel == null)
        {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }

        String id = deviceConfigurationModel.getId();

        if (id == null)
        {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }

        DeviceConfigurationModel model = new DeviceConfigurationModel();
        model.setId(id);
        //根据设备id查询功能点位表中是否关联此设备类型
        List<DeviceConfigurationModel> listFunctionPoint = deviceConfigurationMapper.findDeviceFunctionPointList(model);
        if (listFunctionPoint.size() > 0) {
            returnObject.setStatus("0");
            returnObject.setMsg("该设备已经被功能点位关联，请删除关联的功能点位，再进行此操作");
            return returnObject;
        }
        Boolean del = deviceConfigurationMapper.delete(deviceConfigurationModel);

        // 删除缓存
        deviceConfigurationCache.deleteOneDeviceConfigurationCache(id);

        if (del) {
            returnObject.setStatus("1");
            returnObject.setMsg("删除成功");
        } else {
            returnObject.setStatus("0");
            returnObject.setMsg("删除失败");
        }
        return returnObject;
    }

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
    @Override
    public PageInfo<DeviceFunctionModel> queryPage_function(String deviceId,String deviceTypeId, Integer pageNum, String param) {
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, 100000);
        List<DeviceFunctionModel> list = deviceConfigurationMapper.queryPage_function(deviceId,deviceTypeId);
        return new PageInfo<>(list);
    }


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
    @Override
    public ISSPReturnObject queryPoint(DeviceFunctionPointModel deviceFunctionPointModel) {
        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (deviceFunctionPointModel == null)
        {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }

        Integer id = deviceFunctionPointModel.getDeviceFunctionId();
        String deviceId = deviceFunctionPointModel.getDeviceId();
        if (id == null || deviceId == null) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }
        DeviceFunctionPointModel deviceFunctionPoint = deviceConfigurationMapper.queryPoint(id,deviceId);
        returnObject.setData(deviceFunctionPoint);
        return returnObject;
    }

    /**
     *
     * @Description: 删除配置的功能点位
     *
     * @auther: wanghongjie
     * @date: 8:30 2021/7/3
     * @param: [deviceFunctionPointID]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Override
    public ISSPReturnObject deletePoint(String deviceFunctionPointID) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        if (deviceFunctionPointID == null || deviceFunctionPointID.isEmpty()) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        } else {
            //删除配置的功能点位
            Boolean deletePoint = deviceConfigurationMapper.deletePoint(deviceFunctionPointID);
            if (deletePoint) {
                returnObject.setStatus("1");
            } else {
                returnObject.setStatus("0");
            }
        }
        return returnObject;
    }

    /**
     *
     * @Description: 获取所有的区域
     *
     */
    @Override
    public List<Map<String,Object>> queryPosition() {
        String pid = "1326432798285770754";
        List<Map<String,Object>> list = deviceConfigurationMapper.queryPositionByPid(pid);
        List<Map<String,Object>> positionList = this.queryPositionChild(list);
        return positionList;
    }

    private List<Map<String,Object>> queryPositionChild(List<Map<String,Object>> positionList){
        positionList.forEach((item ->{
            List<Map<String,Object>> childList = deviceConfigurationMapper.queryPositionByPid(item.get("id").toString());
            if (childList.size() > 0){
                this.queryPositionChild(childList);
            }
            item.put("children",childList);
        }));
        return positionList;
    }



}
