package com.efounder.JEnterprise.service.basedatamanage.workbench.impl;

import com.efounder.JEnterprise.mapper.dataAnalysises.EnergyTotalMapper;
import com.efounder.JEnterprise.service.basedatamanage.workbench.EnergyTotalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * describe: 统计某天、某月、某年的用水量、用电量
 * 供向外部系统开放的接口调用
 *
 * @author zs
 * @date 2021/9/2
 */
@Service
public class EnergyTotalServiceImpl implements EnergyTotalService {

    @Resource
    private EnergyTotalMapper energyTotalMapper;

    /**
     * 按照时间查询某天的能耗统计（以分户为单位计算）
     * 统计每个园区下的总分户节点的某天的能耗之和
     *
     * @param map 时间
     * @return
     */
    @Override
    public List<Map> getDailyTotalEnergy(Map<String, Object> map) {
        List<Map> list = energyTotalMapper.getDailyTotalEnergy(map);
        return list;
    }

    /**
     * 按照时间查询某月的能耗统计
     * 统计每个园区下的总分户节点的某月的能耗之和
     *
     * @param map 时间
     * @return
     */
    @Override
    public List<Map> getMonthTotalEnergy(Map<String, Object> map) {
        return energyTotalMapper.getMonthTotalEnergy(map);
    }

    /**
     * 按照时间查询某年的能耗统计
     * 统计每个园区下的总分户节点的某年的能耗之和
     *
     * @param map 时间
     * @return
     */
    @Override
    public List<Map> getYearTotalEnergy(Map<String, Object> map) {
        return energyTotalMapper.getYearTotalEnergy(map);
    }

    /**
     * 查询年能耗统计
     *
     * @param map
     * @return
     */
    @Override
    public List<Map> getYearEnergy(Map<String, Object> map) {
        return energyTotalMapper.getYearEnergy(map);
    }

    /**
     * 查询月能耗统计
     *
     * @param map
     * @return
     */
    @Override
    public List<Map> getMonthEnergy(Map<String, Object> map) {
        return energyTotalMapper.getMonthEnergy(map);
    }

    /**
     * 查询日能耗统计
     *
     * @param map
     * @return
     */
    @Override
    public List<Map> getDayEnergy(Map<String, Object> map) {
        return energyTotalMapper.getDayEnergy(map);
    }

    /**
     * 查询年度能耗趋势
     *
     * @param map
     * @return
     */
    @Override
    public List<Map> getAnnualEnergyTrend(Map<String, Object> map) {
        return energyTotalMapper.getAnnualEnergyTrend(map);
    }

    /**
     * 查询月环比能耗数据
     *
     * @param map
     * @return
     */
    @Override
    public List<Map> getMonthLinkData(Map<String, Object> map) {
        return energyTotalMapper.getMonthLinkData(map);
    }

    /**
     * 查询月同比能耗数据
     *
     * @param map
     * @return
     */
    @Override
    public List<Map> getSameMonthComparedData(Map<String, Object> map) {
        return energyTotalMapper.getSameMonthComparedData(map);
    }

    /**
     * 查询分时能耗统计
     *
     * @param map
     * @return
     */
    @Override
    public List<Map> getLiveEnergyData(Map<String, Object> map) {
        return energyTotalMapper.getLiveEnergyData(map);
    }

    /**
     * 本周的每日能耗数据统计
     *
     * @param map
     * @return
     */
    @Override
    public List<Map> getWeekByDayEnergy(Map<String, Object> map) {
        return energyTotalMapper.getWeekByDayEnergy(map);
    }

    /**
     * 获取各单位能耗统计排行
     *
     * @param map
     * @return
     */
    @Override
    public List<Map> getCompanyMonthRank(Map<String, Object> map) {
        return energyTotalMapper.getCompanyMonthRank(map);
    }

    /**
     * 获取各楼宇的按日能耗统计
     *
     * @param map
     * @return
     */
    @Override
    public List<Map> getBuildingEnergyByDay(Map<String, Object> map) {
        return energyTotalMapper.getBuildingEnergyByDay(map);
    }
}
