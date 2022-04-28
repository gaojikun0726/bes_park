package com.efounder.JEnterprise.mapper.app;

import com.efounder.JEnterprise.model.app.TgLightConfig;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESTgLight;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * describe: 灯光配置
 *
 * @author zs
 * @date 2020/05/12
 */
@Mapper
public interface TgLightConfigMapper {

    /**
     * 查询灯光配置列表
     * @param map
     * @return
     */
    List<TgLightConfig> queryTgLightConfigList(Map map);


    /**
     * 删除
     * @param id 主键
     */
    Integer delete(String id);

    /**
     * 修改
     * @param tglightConfig
     * @return
     */
    Integer update(TgLightConfig tglightConfig);

    /**
     * 新增
     * @param tglightConfig
     * @return
     */
    Integer insert(TgLightConfig tglightConfig);

    /**
     * 查询
     * @param id
     * @return
     */
    List<TgLightConfig> queryOne(String id);

    /**
     * 插入可调光灯光
     * @param besTgLight
     * @return
     */
    Boolean insertTgLight(BESTgLight besTgLight);
}
