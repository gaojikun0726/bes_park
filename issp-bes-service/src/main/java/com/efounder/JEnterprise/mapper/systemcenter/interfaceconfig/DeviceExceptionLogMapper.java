package com.efounder.JEnterprise.mapper.systemcenter.interfaceconfig;

import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceConfigurationModel;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceExceptionLogModel;
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
public interface DeviceExceptionLogMapper {

    List<DeviceExceptionLogModel> queryPage(@Param("deviceTypeId") String deviceTypeId, @Param("positionId") String positionId, @Param("param") String param);


    boolean deleteAll(@Param("deviceTypeId") String deviceTypeId, @Param("positionId") String positionId);

    boolean delete(DeviceExceptionLogModel deviceExceptionLogModel);
}
