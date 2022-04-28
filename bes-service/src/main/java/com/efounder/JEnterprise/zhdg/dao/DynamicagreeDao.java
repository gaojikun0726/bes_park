package com.efounder.JEnterprise.zhdg.dao;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.zhdg.entity.DynamicagreeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 动态协议配置
 * 
 * @author YangChao
 * @date 2020-09-03 09:40:12
 */
@Mapper
public interface DynamicagreeDao extends BaseMapper<String,DynamicagreeEntity> {

    List<DynamicagreeEntity> getSearch(@Param(value = "keywords")String keywords);

    boolean add(DynamicagreeEntity dto);

    int update(DynamicagreeEntity dto);

    int delete(String Id);

    DynamicagreeEntity getSearchById(String Id);

    List<DynamicagreeEntity> GetDynamicAgreeList(String configCode);
}
