package com.efounder.JEnterprise.platform.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * describe: 公摊仪表
 *
 * @author zs
 * @date 2020/11/9
 */
@Mapper
public interface ShareAmmeterMapper {


    /**
     * 查询列表
     *
     * @param map 参数
     * @return
     */
    List<Map> queryList(Map<String, Object> map);


    /**
     * 配置公摊仪表
     * @param map 仪表id
     * @return
     */
    Integer shareMeterConfig(Map<String, Object> map);

    /**
     * 配置普通仪表
     * @param map 仪表id
     * @return
     */
    Integer plainMeterConfig(Map<String, Object> map);

    /**
     * 查询是公摊仪表或普通仪表的数量
     * @param map 仪表id,仪表状态
     * @return
     */
    Integer queryMeterState(Map<String, Object> map);

    /**
     * 配置公摊仪表时查询电表是否已关联房间
     * @param map 仪表id
     * @return
     */
    List<Map> queryRelativeAmmeter(Map<String, Object> map);


    /**
     * 查询仪表类型数据
     * @return
     */
    List<Map> queryAmmeterType();




}
