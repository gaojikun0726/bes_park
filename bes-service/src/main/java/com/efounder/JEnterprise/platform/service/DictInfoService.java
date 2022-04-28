package com.efounder.JEnterprise.platform.service;

import java.util.List;
import java.util.Map;

/**
 * describe: 字典数据业务接口
 *
 * @author zs
 * @date 2021/10/13
 */
public interface DictInfoService {

    /**
     * 按字典类型查询
     * @param map
     * @return
     */
    List<Map> getDictByType(Map<String, String> map);

}
