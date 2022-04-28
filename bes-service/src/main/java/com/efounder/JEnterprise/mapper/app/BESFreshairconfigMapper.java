package com.efounder.JEnterprise.mapper.app;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BESFreshairconfigMapper {

    /**
     * 查询新风表
     * @return
     */
    List<Object> selectFreshair();

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
