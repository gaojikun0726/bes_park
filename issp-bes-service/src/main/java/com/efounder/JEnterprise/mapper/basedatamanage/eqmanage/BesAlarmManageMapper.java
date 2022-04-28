package com.efounder.JEnterprise.mapper.basedatamanage.eqmanage;

import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BesAlarmModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;


/**
 * @author xiepufeng
 *
 */
@Mapper
public interface BesAlarmManageMapper {

    Boolean insertBatch(List<BesAlarmModel> besAlarmModels);

    Map findByFSysName(@Param("fSysName")String fSysName);

    List<BesAlarmModel> findPage(BesAlarmModel besAlarmModel);
}