package com.efounder.JEnterprise.service.app;

import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * 新风接口
 */
public interface BESFreshairconfigService {
    /**
     * 查询新风表
     * @return
     */
    PageInfo<Object> selectFreshair(Integer pageSize, Integer pageNum, Map<String,Object> map1);

    /**
     * 根据ID查询回显信息
     * @param id
     * @return
     */
    Map<String,Object> selectFreshairByID(String id);

    /**
     * 根据ID更改新风表
     * @param map
     * @return
     */
    boolean updateFreshair(Map<String,Object> map);

    /**
     * 添加新风表新数据
     * @param map
     * @return
     */
    boolean insertFreshair(Map<String,Object> map);

    /**
     * 根据ID删除新风表数据
     * @param id
     * @return
     */
    boolean deleteFreshairByID(String id);
}
