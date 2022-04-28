package com.efounder.JEnterprise.zhdg.dao;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.zhdg.entity.WarnRecordEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 报警历史记录表
 * 
 * @author YangChao
 * @date 2020-09-14 11:54:54
 */
@Mapper
public interface WarnRecordDao extends BaseMapper<String,WarnRecordEntity> {

    List<WarnRecordEntity> getSearch(@Param(value = "warnid")String warnid,@Param(value = "beginTime")String beginTime,@Param(value = "endTime")String endTime);

    boolean add(WarnRecordEntity dto);

    int update(WarnRecordEntity dto);

    int delete(String Id);

    WarnRecordEntity getSearchById(String Id);

    int clean();

    void insertRealWarn(@Param("pin") String pin);
}
