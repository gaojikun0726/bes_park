package com.efounder.JEnterprise.mapper.strongAndWeakElectricityIntegration.deviceConfig;

import com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration.deviceConfig.MachineSetModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 机组
 * @author xiepufeng
 */
@Mapper
public interface MachineSetMapper
{

    void insert(MachineSetModel machineSetModel);

    List<MachineSetModel> findList(MachineSetModel machineSetModel);

    void update(MachineSetModel machineSetModel);

    void delete(Integer id);

    List<MachineSetModel> queryPage(@Param(value="keywords")String keywords);
}
