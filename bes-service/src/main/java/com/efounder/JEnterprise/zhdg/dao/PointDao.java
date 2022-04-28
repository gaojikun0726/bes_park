package com.efounder.JEnterprise.zhdg.dao;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.zhdg.entity.PointEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 设备维护
 *
 * @author YangChao
 * @email @gmail.com
 * @date 2020-08-27 18:36:45
 */
@Mapper
public interface PointDao extends BaseMapper<String,PointEntity> {

    List<PointEntity> getSearch(@Param(value = "keywords")String keywords);

    boolean add(PointEntity dto);

    int update(PointEntity dto);

    int delete(String FGuid);

    /**
     * 查询唯一编码是否重复
     * @param pointEntity
     * @return
     */
    Integer querySameCode(PointEntity pointEntity);

    /**
     * 查询IP地址是否重复
     * @param pointEntity
     * @return
     */
    Integer querySameIp(PointEntity pointEntity);

    PointEntity getSearchById(String FGuid);

    List<PointEntity> GetofflinePointList();

    /**
     * 修改点位状态-改为离线状态
     * @param offlineList
     */
    void updatePointStatus(List<String> offlineList);

    /**
     * 修改点位状态-改为报警状态
     * @param newWarnList
     */
    void updatePointsWarnStatus(List<String> newWarnList);

    /**
     * 修改点位状态-改为在线状态
     */
    void updatePointsStatus(List<String> fCode);

    /**
     * 修改所有点位状态-改为离线状态
     */
    void updateAllPointsStatus();

    PointEntity getSearchByFcode(String fCode);

    /**
     * 根据大屏编码查询灯杆开关编码
     * @param screenCode 大屏编码
     * @return
     */
    List<String> queryCodeByScreenCode(String screenCode);

    /**
     * 根据显示屏IP地址查询关联的PDU IP地址
     * @param screenIp 显示屏IP地址
     * @return
     */
    List<String> queryPduIpByScreenIp(String screenIp);

    /**
     * 根据PDU IP地址查询关联的显示屏IP地址
     * @param pduIp PDU IP地址
     * @return
     */
    List<String> queryScreenIpByPduIp(String pduIp);


    /**
     * 查询所有的PDU_IP
     * @return
     */
    List<String> queryAllPduIp();


    /**
     * 查询所有气象的ip、状态数据
     * @return
     */
    List<Map> getWeatherData();


    /**
     * 保存所有气象的ip、状态数据
     * @param map
     * @return
     */
    Integer saveWeatherStatus(Map<String,Object> map);
}
