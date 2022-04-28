package com.efounder.JEnterprise.platform.service.impl;

import com.efounder.JEnterprise.platform.mapper.DictInfoMapper;
import com.efounder.JEnterprise.platform.service.DictInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * describe: 字典数据业务接口实现
 *
 * @author zs
 * @date 2021/10/13
 */
@Service
public class DictInfoServiceImpl implements DictInfoService {

    @Resource
    private DictInfoMapper dictInfoMapper;

    /**
     * 按字典类型查询
     *
     * @param map
     * @return
     */
    @Override
    public List<Map> getDictByType(Map<String, String> map) {
        return dictInfoMapper.getDictByType(map);
    }
}
