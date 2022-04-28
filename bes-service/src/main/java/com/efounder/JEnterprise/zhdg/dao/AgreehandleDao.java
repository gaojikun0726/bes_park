package com.efounder.JEnterprise.zhdg.dao;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.zhdg.entity.AgreehandleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 协议处理类型维护
 * 
 * @author YangChao
 * @date 2020-08-31 17:41:28
 */
@Mapper
public interface AgreehandleDao extends BaseMapper<String,AgreehandleEntity> {

    List<AgreehandleEntity> getSearch(@Param(value = "keywords")String keywords);

    boolean add(AgreehandleEntity dto);

    int update(AgreehandleEntity dto);

    int delete(String Id);

    AgreehandleEntity getSearchById(String Id);
	
}
