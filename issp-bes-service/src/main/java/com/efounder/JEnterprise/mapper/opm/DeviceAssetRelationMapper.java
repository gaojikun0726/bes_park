package com.efounder.JEnterprise.mapper.opm;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * describe: bes设备与资产设备关联关系
 *
 * @author zs
 * @date 2020/11/30
 */
@Mapper
public interface DeviceAssetRelationMapper {

    List<Map<String,String>> queryRelationBySysname(Map<String, Object> map);
}
