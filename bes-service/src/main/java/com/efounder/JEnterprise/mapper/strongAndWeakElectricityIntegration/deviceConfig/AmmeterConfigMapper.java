package com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration.deviceConfig;

import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.deviceConfig.AmmeterConfigModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xiepufeng
 * @date 2019/12/16 14:59
 */
@Mapper
public interface AmmeterConfigMapper {

    void insert(AmmeterConfigModel ammeterConfigModel);

    List<Object> findList(AmmeterConfigModel ammeterConfigModel);

    void update(AmmeterConfigModel ammeterConfigModel);

    void delete(Integer id);
}
