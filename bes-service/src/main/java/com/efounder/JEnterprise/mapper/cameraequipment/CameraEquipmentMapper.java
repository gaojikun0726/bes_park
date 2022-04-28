package com.efounder.JEnterprise.mapper.cameraequipment;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 摄像机设备Mapper
 *
 * @author huangxianbo
 * @datetime 2018年12月05日
 */
@Mapper
public interface CameraEquipmentMapper {


    /**
     * 查询摄像机设备数据
     *
     * @param devId
     * @return
     */
    List<Map<String, Object>> getCameraEquipmentInfo(@Param("devId") String devId, @Param("id") String id);

    /**
     * 查询摄像机的状态信息并返回查询结果
     *
     * @param deviceId
     * @return
     * @author liuhoujun
     */
    Map<String, Object> getCameraStatusInfo(@Param("deviceId") String deviceId);

    /**
     * 查询摄像机的视频流获取方式
     *
     * @param deviceId
     * @return
     * @author
     */
    Map<String, Object> getVideoMethodByDevId(@Param("deviceId") String deviceId);

    /**
     * 获取所有摄像机基础信息
     *
     * @return
     */
    List<Map<String, Object>> getCameraBaseInfo(@Param("localInfo") String localInfo);

    List<String> getsxtsb_list(List<String> Id);

    List<String> GetxlidList(List<String> Id);

    List<Map<String, String>> GetAllxlList(@Param("Id") List<String> Id, @Param("CJs") String CJs, @Param("PId") String PId);

    List<Map<String, String>> GetxlZzjgList(@Param("Id") List<String> Id, @Param("CJs") String CJs, @Param("XLid") String XLid, @Param("SJid") String SJid);

    List<Map<String, String>> GetzzjgsxtList(@Param("Id") List<String> Id, @Param("CJs") String CJs, @Param("SJid") String SJid);

    String GetSxtzcSum(List<String> Id);

    String GetSxtbjSum(List<String> Id);

    String GetSxtycSum(List<String> Id);

    String GetSxtXlzcSum(@Param("Id") List<String> Id, @Param("fStatus") String fStatus);
}
