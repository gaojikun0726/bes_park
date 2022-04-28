package com.efounder.JEnterprise.mapper.basedatamanage.enegrycollectionmanage;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * describe:
 *
 * @author zs
 * @date 2020/11/20
 */
@Mapper
public interface AmmeterTypeDeleteMapper {

    /**
     * 保存一条电表类型删除的记录
     * @param map
     * @return
     */
    Integer insertAmmeterTypeDelete(Map<String, Object> map);

}
