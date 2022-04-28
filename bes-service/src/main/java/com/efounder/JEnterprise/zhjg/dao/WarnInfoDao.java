package com.efounder.JEnterprise.zhjg.dao;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.zhjg.entity.WarnInfoEntity;
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
public interface WarnInfoDao extends BaseMapper<String, WarnInfoEntity> {

    List<WarnInfoEntity> getSearch(@Param(value = "warnid") String warnid, @Param(value = "beginTime") String beginTime, @Param(value = "endTime") String endTime);

    boolean add(WarnInfoEntity dto);

    int update(WarnInfoEntity dto);

    int delete(String id);

    WarnInfoEntity getSearchById(String id);

    int clean();

    void insertRealWarn(@Param("pin") String pin);
}
