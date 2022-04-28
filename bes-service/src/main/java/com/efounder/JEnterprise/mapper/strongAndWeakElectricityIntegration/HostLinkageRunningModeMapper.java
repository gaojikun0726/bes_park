package com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration;

import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.HostLinkageRunningModeModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 主机联动（运行模式）
 * @author xiepufeng
 * @date 2020/2/11 15:54
 */
@Mapper
public interface HostLinkageRunningModeMapper
{

    void insert(HostLinkageRunningModeModel hostLinkageRunningModeModel);

    List<Object> findList(HostLinkageRunningModeModel hostLinkageRunningModeModel);

    void update(HostLinkageRunningModeModel hostLinkageRunningModeModel);

    void delete(Integer id);
}
