package com.efounder.JEnterprise.platform.service;

import java.util.List;
import java.util.Map;

/**
 * describe: APP数据接口
 *
 * @author zs
 * @date 2021/11/3
 */
public interface AppDataApiService {


    /**
     *  查询支路年能耗统计
     * @param map
     * @return
     */
    List<Map> getBranchYearEnergy(Map<String,Object> map);


    /**
     * 查询支路月能耗统计
     * @param map
     * @return
     */
    List<Map> getBranchMonthEnergy(Map<String,Object> map);

    /**
     * 查询支路日能耗统计
     * @param map
     * @return
     */
    List<Map> getBranchDayEnergy(Map<String,Object> map);


    /**
     * 根据支路查询月排行数据
     * @param map
     * @return
     */
    List<Map> getBranchMonthRankData(Map<String,Object> map);


    /**
     * 获取园区支路月排行
     * @param map
     * @return
     */
    List<Map> getParkBranchMonthRank(Map<String,Object> map);

}
