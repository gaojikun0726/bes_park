package com.efounder.JEnterprise.mapper.basedatamanage.eqmanage;

import com.alibaba.fastjson.JSONObject;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.*;
import com.efounder.JEnterprise.model.systemcenter.Interfaceconfig.DeviceTypeModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 定时同步设备树
 * */
@Mapper
public interface BesTimeTaskSyncMapper {

    List<Map<String,Object>> queryPage(@Param("keywords") String keywords);

    String selectSyncMaxId();

    Boolean insertSyncInfo(BesTimeTaskSync besTimeTaskSync);

    void insertSbInfo(BesTimeTaskSyncSb besTimeTaskSyncSb);

    Boolean insertTimeTaskSyncJobId(@Param("jobId") String jobId,@Param("syncId") String syncId);

    List<Map<String,Object>> querySbList(String syncId);

    Map<String,Object> selectTimeTaskSyncInfo(String syncId);

    Boolean deleteSyncInfo(String syncId);

    void deleteSyncSbList(String syncId);

    Map<String,Object> selectSysJobInfo(@Param("syncId") String syncId,@Param("invokeTarget")String invokeTarget);

    void deleteJobInfo(String jobId);

    Boolean updateTimeTaskSync(BesTimeTaskSync besTimeTaskSync);

    Boolean updateSysJobInfo(@Param("object") JSONObject object);

    List<BesSyncLog> getSyncLogPage(BesSyncLog besSyncLog);

}
