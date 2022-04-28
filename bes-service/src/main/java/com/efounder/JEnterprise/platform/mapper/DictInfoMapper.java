package com.efounder.JEnterprise.platform.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * describe: 字典数据持久层接口
 *
 * @author zs
 * @date 2021/10/13
 */
@Mapper
public interface DictInfoMapper {

    /**
     * 按字典类型查询
     * @param map
     * @return
     */
    List<Map> getDictByType(Map<String, String> map);
}
