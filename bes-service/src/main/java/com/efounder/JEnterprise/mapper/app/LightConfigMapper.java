package com.efounder.JEnterprise.mapper.app;

import com.efounder.JEnterprise.model.app.LightConfig;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESLight;
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
public interface LightConfigMapper {

    /**
     * 查询灯光配置列表
     * @param map
     * @return
     */
    List<LightConfig> queryLightConfigList(Map map);


    /**
     * 删除
     * @param id 主键
     */
    Integer delete(String id);

    /**
     * 修改
     * @param lightConfig
     * @return
     */
    Integer update(LightConfig lightConfig);

    /**
     * 新增
     * @param lightConfig
     * @return
     */
    Integer insert(LightConfig lightConfig);

    /**
     * 查询
     * @param id
     * @return
     */
    List<LightConfig> queryOne(String id);

    /**
     * 插入灯光配置
     * @param besLight
     * @return
     */
    Boolean insertLight(BESLight besLight);
}
