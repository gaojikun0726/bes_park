package com.efounder.JEnterprise.zhdg.dao;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.zhdg.entity.WarntypeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 报警类型维护表
 * 
 * @author YangChao
 * @date 2020-09-14 11:20:24
 */
@Mapper
public interface WarntypeDao extends BaseMapper<String,WarntypeEntity> {

    List<WarntypeEntity> getSearch(@Param(value = "keywords")String keywords);

    boolean add(WarntypeEntity dto);

    int update(WarntypeEntity dto);

    int delete(String Id);

    WarntypeEntity getSearchById(String Id);
	
}
