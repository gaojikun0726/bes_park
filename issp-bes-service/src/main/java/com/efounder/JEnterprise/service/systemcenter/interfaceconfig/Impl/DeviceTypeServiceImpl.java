package com.efounder.JEnterprise.service.systemcenter.interfaceconfig.Impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.initializer.DeviceTypeCache;
import com.efounder.JEnterprise.mapper.systemcenter.interfaceconfig.DeviceTypeMapper;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceConfigurationModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceFunctionModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceTypeModel;
import com.efounder.JEnterprise.service.systemcenter.interfaceconfig.DeviceTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 设备类型（接口管理模块）
 * @author xiepufeng
 * @date 2020/12/8 20:32
 */
@Service
public class DeviceTypeServiceImpl implements DeviceTypeService
{
    @Resource
    private DeviceTypeMapper deviceTypeMapper;

    @Resource
    private DeviceTypeCache deviceTypeCache;

    @Override
    public List<DeviceTypeModel> findAll()
    {
        return deviceTypeMapper.findAll();
    }


    @Override
    public PageInfo<DeviceTypeModel> queryPage(String keywords, Integer pageNum)
    {
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
        List<DeviceTypeModel> list = deviceTypeMapper.queryPage(keywords);
        return new PageInfo<>(list);
    }

    @Override
    public ISSPReturnObject create(DeviceTypeModel deviceTypeModel)
    {
        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == deviceTypeModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        String name = deviceTypeModel.getName();
        String code = deviceTypeModel.getCode();

        if (!StringUtils.hasText(name) || !StringUtils.hasText(code) ) {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;

        }

        DeviceTypeModel dtm = new DeviceTypeModel();
        dtm.setCode(code);

        List<DeviceTypeModel> deviceTypeModelList =  deviceTypeMapper.findList(dtm);

        if (null != deviceTypeModelList && !deviceTypeModelList.isEmpty()) {
            returnObject.setStatus("0");
            returnObject.setMsg("设备类型编号重复！");
            return returnObject;
        }


        try {
            deviceTypeMapper.insert(deviceTypeModel);

            // 添加到缓存
            deviceTypeCache.addOneDeviceTypeCache(deviceTypeMapper.findList(dtm).get(0));

            returnObject.setStatus("1");
            returnObject.setMsg("添加成功");
            returnObject.setData(deviceTypeModel);

        } catch (Exception e) {

            returnObject.setStatus("0");
            returnObject.setMsg("添加失败");

            e.printStackTrace();
        }

        return returnObject;
    }

    @Override
    public ISSPReturnObject query(DeviceTypeModel deviceTypeModel)
    {
        ISSPReturnObject isspReturnObject = new ISSPReturnObject();

        try {
            List<DeviceTypeModel> list = deviceTypeMapper.findList(deviceTypeModel);
            isspReturnObject.setData(list);
            isspReturnObject.setStatus("1");
        } catch (Exception e) {
            isspReturnObject.setStatus("0");
            e.printStackTrace();
        }

        return isspReturnObject;
    }

    @Override
    public ISSPReturnObject update(DeviceTypeModel deviceTypeModel)
    {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == deviceTypeModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        Integer id   = deviceTypeModel.getId();
        String  name = deviceTypeModel.getName();
        String code = deviceTypeModel.getCode();

        if (null == id || !StringUtils.hasText(name) || !StringUtils.hasText(code)) {

            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;

        }
        DeviceTypeModel deviceTypeModel1 = new DeviceTypeModel();
        deviceTypeModel1.setCode(code);

        List<DeviceTypeModel> deviceTypeModelList = deviceTypeMapper.findList(deviceTypeModel1);

        if (deviceTypeModelList != null && !deviceTypeModelList.isEmpty())
        {

            if (!deviceTypeModelList.get(0).getId().equals(id))
            {
                returnObject.setStatus("0");
                returnObject.setMsg("设备类型代码不能重复！");
                return returnObject;
            }

        }

        DeviceTypeModel ms = new DeviceTypeModel();
        ms.setId(id);

        try {
            List<DeviceTypeModel> list = deviceTypeMapper.findList(ms);

            if (null == list || list.isEmpty()){

                returnObject.setStatus("0");
                returnObject.setMsg("参数错误！");
                return returnObject;
            }

        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        try {
            deviceTypeMapper.update(deviceTypeModel);
            // 更新缓存
            DeviceTypeModel dtm = new DeviceTypeModel();
            dtm.setId(id);
            List<DeviceTypeModel> list = deviceTypeMapper.findList(dtm);
            deviceTypeCache.updateOneDeviceTypeCache(list.get(0));
            returnObject.setStatus("1");
            returnObject.setMsg("修改成功！");
        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        return returnObject;
    }

    @Override
    public ISSPReturnObject delete(DeviceTypeModel deviceTypeModel)
    {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (null == deviceTypeModel) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        Integer id = deviceTypeModel.getId();

        if (null == id) {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误！");
            return returnObject;
        }

        DeviceTypeModel ms = new DeviceTypeModel();
        ms.setId(id);

        try {
            List<DeviceTypeModel> list = deviceTypeMapper.findList(ms);

            if (null == list || list.isEmpty()){

                returnObject.setStatus("0");
                returnObject.setMsg("实体不存在！");
                return returnObject;
            }

        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        DeviceConfigurationModel hdm = new DeviceConfigurationModel();
        hdm.setDeviceTypeId(Integer.toString(id));

        try {
            //根据设备类型id查询设备表中是否关联此设备类型
            List<DeviceConfigurationModel> list = deviceTypeMapper.findDeviceConfigurationList(hdm);

            if (list.size() > 0) {
                returnObject.setStatus("0");
                returnObject.setMsg("该设备类型已经被设备关联，请删除关联的设备，再进行此操作");
                return returnObject;
            }
            //根据设备类型id查询设备功能表中是否关联此设备类型
            List<DeviceFunctionModel> listFunction = deviceTypeMapper.findDeviceFunctionList(hdm);

            if (listFunction.size() > 0) {
                returnObject.setStatus("0");
                returnObject.setMsg("该设备类型已经被设备功能关联，请删除关联的设备功能，再进行此操作");
                return returnObject;
            }


        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        try {
            deviceTypeMapper.delete(id);

            // 删除缓存中数据
            deviceTypeCache.deleteOneDeviceTypeCache(id);

            returnObject.setStatus("1");
        } catch (Exception e) {
            returnObject.setStatus("0");
            e.printStackTrace();
        }

        return returnObject;
    }
}
