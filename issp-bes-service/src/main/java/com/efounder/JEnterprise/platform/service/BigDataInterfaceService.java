package com.efounder.JEnterprise.platform.service;

import java.util.List;
import java.util.Map;

/**
 * describe: 大数据系统接口
 *
 * @author zs
 * @date 2021/6/24
 */
public interface BigDataInterfaceService {

    /**
     * 向大数据系统推送用电量数据
     * @param list 用电量数据
     * @return 结果
     */
    String pushElectricData(List<Map<String, Object>> list);
}
