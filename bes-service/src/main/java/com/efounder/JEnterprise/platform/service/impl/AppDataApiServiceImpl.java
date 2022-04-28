package com.efounder.JEnterprise.platform.service.impl;

import com.efounder.JEnterprise.platform.mapper.AppDataApiMapper;
import com.efounder.JEnterprise.platform.service.AppDataApiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * describe: APP数据接口
 *
 * @author zs
 * @date 2021/11/3
 */
@Service
public class AppDataApiServiceImpl implements AppDataApiService {

    @Resource
    private AppDataApiMapper appDataApiMapper;

    /**
     * 查询支路年能耗统计
     *
     * @param map
     * @return
     */
    @Override
    public List<Map> getBranchYearEnergy(Map<String, Object> map) {
        return appDataApiMapper.getBranchYearEnergy(map);
    }

    /**
     * 查询支路月能耗统计
     *
     * @param map
     * @return
     */
    @Override
    public List<Map> getBranchMonthEnergy(Map<String, Object> map) {
        return appDataApiMapper.getBranchMonthEnergy(map);
    }

    /**
     * 查询支路日能耗统计
     *
     * @param map
     * @return
     */
    @Override
    public List<Map> getBranchDayEnergy(Map<String, Object> map) {
        return appDataApiMapper.getBranchDayEnergy(map);
    }

    /**
     * 根据支路查询月排行数据
     *
     * @param map
     * @return
     */
    @Override
    public List<Map> getBranchMonthRankData(Map<String, Object> map) {
        return appDataApiMapper.getBranchMonthRankData(map);
    }

    /**
     * 获取园区支路月排行
     *
     * @param map
     * @return
     */
    @Override
    public List<Map> getParkBranchMonthRank(Map<String, Object> map) {
        return appDataApiMapper.getParkBranchMonthRank(map);
    }
}
