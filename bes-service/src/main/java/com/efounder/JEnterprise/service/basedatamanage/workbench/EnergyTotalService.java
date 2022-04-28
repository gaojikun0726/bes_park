package com.efounder.JEnterprise.service.basedatamanage.workbench;


import java.util.List;
import java.util.Map;

/**
 * describe: 统计某天、某月、某年的用水量、用电量
 * 供向外部系统开放的接口调用
 * @author zs
 * @date 2021/9/2
 */
public interface EnergyTotalService {



    /**
     * 按照时间查询某天的能耗统计（以分户为单位计算）
     * 统计每个园区下的总分户节点的某天的能耗之和
     * @param map 时间
     * @return
     */
    List<Map> getDailyTotalEnergy(Map<String,Object> map);


    /**
     * 按照时间查询某月的能耗统计
     * 统计每个园区下的总分户节点的某月的能耗之和
     * @param map 时间
     * @return
     */
    List<Map> getMonthTotalEnergy(Map<String,Object> map);

    /**
     * 按照时间查询某年的能耗统计
     * 统计每个园区下的总分户节点的某年的能耗之和
     * @param map 时间
     * @return
     */
    List<Map> getYearTotalEnergy(Map<String,Object> map);


    /**
     *  查询年能耗统计
     * @param map
     * @return
     */
    List<Map> getYearEnergy(Map<String,Object> map);


    /**
     * 查询月能耗统计
     * @param map
     * @return
     */
    List<Map> getMonthEnergy(Map<String,Object> map);

    /**
     * 查询日能耗统计
     * @param map
     * @return
     */
    List<Map> getDayEnergy(Map<String,Object> map);

    /**
     * 查询年度能耗趋势
     * @param map
     * @return
     */
    List<Map> getAnnualEnergyTrend(Map<String,Object> map);


    /**
     * 查询月环比能耗数据
     * @param map
     * @return
     */
    List<Map> getMonthLinkData(Map<String,Object> map);

    /**
     * 查询月同比能耗数据
     * @param map
     * @return
     */
    List<Map> getSameMonthComparedData(Map<String,Object> map);


    /**
     * 查询分时能耗统计
     * @param map
     * @return
     */
    List<Map> getLiveEnergyData(Map<String,Object> map);

    /**
     * 本周的每日能耗数据统计
     * @param map
     * @return
     */
    List<Map> getWeekByDayEnergy(Map<String,Object> map);

    /**
     * 获取各单位能耗统计排行
     * @param map
     * @return
     */
    List<Map> getCompanyMonthRank(Map<String,Object> map);


    /**
     * 获取各楼宇的按日能耗统计
     * @param map
     * @return
     */
    List<Map> getBuildingEnergyByDay(Map<String,Object> map);
}
