package com.efounder.JEnterprise.service.app.impl;

import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.app.BESFreshairconfigMapper;
import com.efounder.JEnterprise.service.app.BESFreshairconfigService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 新风接口
 */
@Service
public class BESFreshairconfigServiceImpl implements BESFreshairconfigService {

    @Resource
    private BESFreshairconfigMapper besFreshairconfigMapper;

    @Override
    public PageInfo<Object> selectFreshair(Integer pageSize, Integer pageNum, Map<String,Object> map1) {
        if (pageNum == null){
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = Constants.PAGE_SIZE;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Object> list = besFreshairconfigMapper.selectFreshair();
        // 用PageInfo对结果进行包装
        PageInfo<Object> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public Map<String, Object> selectFreshairByID(String id) {
        Map<String, Object> map=besFreshairconfigMapper.selectFreshairByID(id);
        return map;
    }

    @Override
    public boolean updateFreshair(Map<String, Object> map) {
        boolean flag = besFreshairconfigMapper.updateFreshair(map);
        return flag;
    }

    @Override
    public boolean insertFreshair(Map<String, Object> map) {
        boolean flag = besFreshairconfigMapper.insertFreshair(map);
        return flag;
    }

    @Override
    public boolean deleteFreshairByID(String id) {
        boolean flag = besFreshairconfigMapper.deleteFreshairByID(id);
        return flag;
    }
}
