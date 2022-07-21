package com.efounder.JEnterprise.service.systemcenter.interfaceconfig.Impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.systemcenter.interfaceconfig.DeviceExceptionLogMapper;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceConfigurationModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceExceptionLogModel;
import com.efounder.JEnterprise.service.systemcenter.interfaceconfig.DeviceExceptionLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 14:45 2020/12/10
 * @Modified By:设备异常日志
 */
@Service
public class DeviceExceptionLogServiceImpl implements DeviceExceptionLogService {
    @Resource
    private DeviceExceptionLogMapper deviceExceptionLogMapper;


    @Override
    public PageInfo<DeviceExceptionLogModel> queryPage(String deviceTypeId, String positionId, Integer pageNum, String param) {
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
        List<DeviceExceptionLogModel> list = deviceExceptionLogMapper.queryPage(deviceTypeId,positionId,param);
        return new PageInfo<>(list);
    }

    /**
     *
     * @Description: 批量删除设备异常日志
     *
     * @auther: liuwenge
     *
     */
    @Override
    public ISSPReturnObject deleteAll(String deviceTypeId, String positionId) {
        ISSPReturnObject returnObject = new ISSPReturnObject();

        boolean flag = deviceExceptionLogMapper.deleteAll(deviceTypeId,positionId);

        if (flag) {
            returnObject.setStatus("1");
            returnObject.setMsg("批量删除成功");
        } else {
            returnObject.setStatus("0");
            returnObject.setMsg("批量删除失败");
        }
        return returnObject;
    }

    /**
     *
     * @Description: 单个删除设备异常日志
     *
     * @auther: liuwenge
     *
     */
    @Override
    public ISSPReturnObject delete(DeviceExceptionLogModel deviceExceptionLogModel) {
        ISSPReturnObject returnObject = new ISSPReturnObject();

        boolean flag = deviceExceptionLogMapper.delete(deviceExceptionLogModel);

        if (flag) {
            returnObject.setStatus("1");
            returnObject.setMsg("删除成功");
        } else {
            returnObject.setStatus("0");
            returnObject.setMsg("删除失败");
        }
        return returnObject;
    }


}
