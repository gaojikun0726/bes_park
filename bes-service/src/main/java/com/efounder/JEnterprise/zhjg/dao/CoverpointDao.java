package com.efounder.JEnterprise.zhjg.dao;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.zhjg.entity.CoverpointEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 设备维护
 * 
 * @author YangChao
 * @date 2020-10-09 14:28:58
 */
@Mapper
public interface CoverpointDao extends BaseMapper<String,CoverpointEntity> {

    List<CoverpointEntity> getSearch(@Param(value = "keywords")String keywords);

    boolean add(CoverpointEntity dto);

    int update(CoverpointEntity dto);

    int delete(String FGuid);

    CoverpointEntity getSearchById(String FGuid);
	
}
