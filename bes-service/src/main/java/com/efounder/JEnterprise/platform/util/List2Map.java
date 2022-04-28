package com.efounder.JEnterprise.platform.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * describe: 将查询结果集转换成map格式
 *
 * @author zs
 * @date 2020/11/5
 */
public class List2Map {


    /**
     * 将List<Map>转为Map<String,String>格式，其中List<Map> 的某个字段作为key,某个字段作为value
     * @return map
     */
    public static Map<String,Object> list2Map(List<Map> list,String keyName,String valueName){
        Map<String,Object> map = new HashMap<>();
        for(Map itemMap : list){
            String key = itemMap.get(keyName) == null ? "" : itemMap.get(keyName).toString();
            String value = itemMap.get(valueName) == null ? "" : itemMap.get(valueName).toString();
            map.put(key,value);
        }
        return map;
    }
}
