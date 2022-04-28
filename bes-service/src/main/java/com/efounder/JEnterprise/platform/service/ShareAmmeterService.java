package com.efounder.JEnterprise.platform.service;

import com.alibaba.fastjson.JSONArray;
import com.core.common.ISSPReturnObject;

import java.util.List;
import java.util.Map;

/**
 * describe: 公摊仪表
 *
 * @author zs
 * @date 2020/11/9
 */
public interface ShareAmmeterService {

    /**
     * 查询列表
     * @param map 参数
     * @param pageNumber 页码
     * @param pageSize 页大小
     * @return
     */
    Map queryList(Map<String,Object> map, Integer pageNumber, Integer pageSize);


    /**
     * 配置公摊仪表
     * @param meterIds 仪表id
     * @return
     */
    ISSPReturnObject shareMeterConfig(String[] meterIds);

    /**
     * 配置普通仪表
     * @param meterIds 仪表id
     * @return
     */
    ISSPReturnObject plainMeterConfig(String[] meterIds);


    /**
     * 查询是否全部是公摊仪表或普通仪表
     * @param meterIds 仪表id
     * @param meterState 仪表状态
     * @return
     */
    ISSPReturnObject queryMeterState(JSONArray meterIds, String meterState);


    /**
     * 查询仪表类型数据
     * @return
     */
    List<Map> queryAmmeterType();

}
