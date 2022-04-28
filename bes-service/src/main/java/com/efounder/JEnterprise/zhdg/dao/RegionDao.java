package com.efounder.JEnterprise.zhdg.dao;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.zhdg.entity.RegionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 区域表
 * 
 * @author YangChao
 * @date 2020-08-28 11:48:44
 */
@Mapper
public interface RegionDao extends BaseMapper<String,RegionEntity> {

    List<RegionEntity> getSearch(@Param(value = "keywords")String keywords);

    boolean add(RegionEntity dto);

    int update(RegionEntity dto);

    int delete(String FId);

    RegionEntity getSearchById(String FId);
	
}
