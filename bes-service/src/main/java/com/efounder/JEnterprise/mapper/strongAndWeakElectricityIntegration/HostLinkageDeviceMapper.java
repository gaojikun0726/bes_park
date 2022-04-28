package com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration;

import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.HostLinkageDeviceModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 主机联动（设备）
 * @author xiepufeng
 * @date 2020/2/11 15:54
 */
@Mapper
public interface HostLinkageDeviceMapper {

    void insert(HostLinkageDeviceModel hostLinkageDeviceModel);

    List<Object> findList(HostLinkageDeviceModel hostLinkageDeviceModel);

    void update(HostLinkageDeviceModel hostLinkageDeviceModel);

    void delete(Integer id);
}
