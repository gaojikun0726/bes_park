package com.efounder.JEnterprise.service.systemcenter.interfaceconfig.Impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.initializer.DeviceFunctionCache;
import com.efounder.JEnterprise.mapper.systemcenter.interfaceconfig.DeviceFunctionMapper;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionValueModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceTypeModel;
import com.efounder.JEnterprise.service.systemcenter.interfaceconfig.DeviceFunctionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 14:45 2020/12/10
 * @Modified By:设备功能
 */
@Service
public class DeviceFunctionServiceImpl implements DeviceFunctionService {
    @Resource
    private DeviceFunctionMapper deviceFunctionMapper;

    @Resource
    private DeviceFunctionCache deviceFunctionCache;


    /**
     * 获取所有设备功能
     * @return
     */
    @Override
    public List<DeviceFunctionModel> findAll()
    {
        return deviceFunctionMapper.findAll();
    }

    @Override
    public PageInfo<DeviceFunctionModel> queryPage(String deviceTypeId, Integer pageNum, String param) {
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
        List<DeviceFunctionModel> list = deviceFunctionMapper.queryPage(deviceTypeId,param);
        return new PageInfo<>(list);
    }

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
    @Override
    public List<DeviceTypeModel> getDeviceType() {
        List<DeviceTypeModel> getDeviceType = deviceFunctionMapper.getDeviceType();
        return getDeviceType;
    }

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
    @Override
    public ISSPReturnObject create(DeviceFunctionModel deviceFunctionModel) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        if (deviceFunctionModel == null)
        {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }
        //获取设备代码
        String code = deviceFunctionModel.getCode();
        String deviceTypeId = deviceFunctionModel.getDeviceTypeId();
        String name = deviceFunctionModel.getName();

        if (!StringUtils.hasText(code) ||
                !StringUtils.hasText(deviceTypeId) ||
                !StringUtils.hasText(name)) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }
        //查询表中设备代码是否重复
        List<DeviceFunctionModel>  codeList = deviceFunctionMapper.selectCodeFromBesDevice(code);

        if (codeList == null) {
            returnObject.setStatus("0");
            returnObject.setMsg("查询失败");
            return returnObject;
        }
        if (codeList.size() > 0) {
            returnObject.setStatus("0");
            returnObject.setMsg("添加失败,设备代码重复");
        } else {
            Boolean getDeviceType = deviceFunctionMapper.create(deviceFunctionModel);

            // 添加到缓存
            deviceFunctionCache.addOneDeviceFunctionCache(deviceFunctionModel);

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
     * @Description: 查询设备功能信息
     *
     * @auther: wanghongjie
     * @date: 9:22 2020/12/11
     * @param: [deviceConfigurationModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Override
    public ISSPReturnObject query(DeviceFunctionModel deviceFunctionModel) {
        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (deviceFunctionModel == null)
        {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }
        Integer id = deviceFunctionModel.getId();

        if (id == null) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }
        DeviceFunctionModel deviceConfiguration = deviceFunctionMapper.query(id);

        if (deviceConfiguration == null) {
            returnObject.setStatus("0");
            returnObject.setMsg("查询失败");
            return returnObject;
        }

        returnObject.setData(deviceConfiguration);
        return returnObject;
    }

    /**
     *
     * @Description: 修改设备功能信息
     *
     * @auther: wanghongjie
     * @date: 10:00 2020/12/11
     * @param: [deviceConfigurationModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Override
    public ISSPReturnObject update(DeviceFunctionModel deviceFunctionModel) {
        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (deviceFunctionModel == null)
        {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }

        //获取设备代码
        String code = deviceFunctionModel.getCode();
        Integer id = deviceFunctionModel.getId();
        String deviceTypeId = deviceFunctionModel.getDeviceTypeId();
        String name = deviceFunctionModel.getName();

        if (!StringUtils.hasText(code) ||
                !StringUtils.hasText(deviceTypeId) ||
                !StringUtils.hasText(name) ||
                id == null) {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }
        //查询表中设备功能代码是否重复
        List<DeviceFunctionModel> codeList = deviceFunctionMapper.selectCodeFromBesDevice(code);

        if (codeList == null) {
            returnObject.setStatus("0");
            returnObject.setMsg("查询失败");
            return returnObject;
        }
        if (codeList.size() > 0) {
            if (!codeList.get(0).getId().equals(id)) {//如果修改的是本身的设备功能,则不判断设备代码是否重复
                returnObject.setStatus("0");
                returnObject.setMsg("修改失败,设备代码重复");
                return returnObject;
            }
        }

        Boolean DeviceFunction = deviceFunctionMapper.update(deviceFunctionModel);

        if (DeviceFunction) {
            // 更新缓存
            deviceFunctionCache.updateOneDeviceFunctionCache(deviceFunctionMapper.query(id));
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
     * @Description: 删除设备功能信息
     *
     * @auther: wanghongjie
     * @date: 11:09 2020/12/11
     * @param: [deviceConfigurationModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Override
    public ISSPReturnObject delete(DeviceFunctionModel deviceFunctionModel) {
        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (deviceFunctionModel == null)
        {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }
        Integer id = deviceFunctionModel.getId();
        if (id == null) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }
        DeviceFunctionModel model = new DeviceFunctionModel();
        model.setId(id);
        //根据设备功能id查询功能点位表中是否关联此设备功能
        List<DeviceFunctionModel> listFunctionPoint = deviceFunctionMapper.findDeviceFunctionPointList(model);

        if (listFunctionPoint == null) {
            returnObject.setStatus("0");
            returnObject.setMsg("查询失败");
            return returnObject;
        }
        if (listFunctionPoint.size() > 0) {
            returnObject.setStatus("0");
            returnObject.setMsg("该设备功能已经被功能点位关联，请删除关联的功能点位，再进行此操作");
            return returnObject;
        }

        //判断当前设备功能是否关联功能值
        List<DeviceFunctionValueModel> deviceFunctionValueModelList = deviceFunctionMapper.deviceFunctionValueModelList(id);
        if (deviceFunctionValueModelList.size() > 0) {
            returnObject.setStatus("0");
            returnObject.setMsg("删除失败,请先删除配置的点位值");
            return returnObject;
        }
        Boolean del = deviceFunctionMapper.delete(deviceFunctionModel);
        if (del) {

            // 删除缓存
            deviceFunctionCache.deleteOneDeviceFunctionCache(id);

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
     * @Description: 加载功能值的页面
     *
     * @auther: wanghongjie
     * @date: 14:50 2021/5/28
     * @param: [deviceFunctionId]
     * @return: com.github.pagehelper.PageInfo<com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionValueModel>
     *
     */
    @Override
    public PageInfo<DeviceFunctionValueModel> query_valuePage(String deviceFunctionId) {
        Integer pageNum = 1;
        PageHelper.startPage(pageNum, 100000);
        List<DeviceFunctionValueModel> list = deviceFunctionMapper.query_valuePage(deviceFunctionId);
        return new PageInfo<>(list);
    }

    /**
     *
     * @Description: 添加功能值
     *
     * @auther: wanghongjie
     * @date: 15:33 2021/5/28
     * @param: [deviceFunctionValueModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Override
    public ISSPReturnObject create_value(DeviceFunctionValueModel deviceFunctionValueModel) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        if (deviceFunctionValueModel == null)
        {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }
        //获取设备代码
        String deviceFunctionId = deviceFunctionValueModel.getDeviceFunctionId();
        String value = deviceFunctionValueModel.getValue();
        String name = deviceFunctionValueModel.getName();

        if (!StringUtils.hasText(deviceFunctionId) ||
                !StringUtils.hasText(value) ||
                !StringUtils.hasText(name)) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }
        Boolean getDeviceType = deviceFunctionMapper.create_value(deviceFunctionValueModel);

        // 添加到缓存
//        deviceFunctionCache.addOneDeviceFunctionCache(deviceFunctionModel);

        if (getDeviceType) {
            returnObject.setStatus("1");
            returnObject.setMsg("添加成功");
        }else {
            returnObject.setStatus("0");
            returnObject.setMsg("添加失败");
        }

        return returnObject;
    }

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
    @Override
    public ISSPReturnObject query_value(DeviceFunctionValueModel deviceFunctionValueModel) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        Integer id = deviceFunctionValueModel.getId();
        if (id == null) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }
        DeviceFunctionValueModel deviceFunctionValue = deviceFunctionMapper.query_value(id);
        if (deviceFunctionValue == null) {
            returnObject.setStatus("0");
            returnObject.setMsg("查询失败");
            return returnObject;
        } else {
            returnObject.setStatus("1");
            returnObject.setData(deviceFunctionValue);
            returnObject.setMsg("查询成功");
        }
        return returnObject;
    }

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
    @Override
    public ISSPReturnObject update_value(DeviceFunctionValueModel deviceFunctionValueModel) {
        ISSPReturnObject returnObject = new ISSPReturnObject();
        if (deviceFunctionValueModel == null)
        {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }

        //获取设备代码
        Integer id = deviceFunctionValueModel.getId();
        String name = deviceFunctionValueModel.getName();
        String value = deviceFunctionValueModel.getValue();

        if (!StringUtils.hasText(name) ||
                !StringUtils.hasText(value) ||
                id == null) {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }

        Boolean DeviceFunctionValue = deviceFunctionMapper.update_value(deviceFunctionValueModel);

        if (DeviceFunctionValue) {
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
     * @Description: 删除功能值
     *
     * @auther: wanghongjie
     * @date: 17:42 2021/5/28
     * @param: [deviceFunctionValueModel]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    @Override
    public ISSPReturnObject delete_value(DeviceFunctionValueModel deviceFunctionValueModel) {
        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (deviceFunctionValueModel == null)
        {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }
        Integer id = deviceFunctionValueModel.getId();
        if (id == null) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }
        Boolean del = deviceFunctionMapper.delete_value(id);
        if (del) {
            returnObject.setStatus("1");
            returnObject.setMsg("删除成功");
        } else {
            returnObject.setStatus("0");
            returnObject.setMsg("删除失败");
        }
        return returnObject;
    }
}
