package com.efounder.JEnterprise.zhdg.dao;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.zhdg.entity.PointwarnEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 点位报警维护
 * 
 * @author YangChao
 * @date 2020-09-16 10:20:05
 */
@Mapper
public interface PointwarnDao extends BaseMapper<String,PointwarnEntity> {

    List<PointwarnEntity> getSearch(@Param(value = "pointid")String pointid,@Param(value = "warnid")String warnid);

    boolean add(PointwarnEntity dto);

    int update(PointwarnEntity dto);

    int delete(String Id);

    PointwarnEntity getSearchById(String Id);

    List<String> getWarnTypeConunt(@Param("warnid")long warnid,@Param("pointIdList") String [] pointIdList);

    public int insertSebPointwarn(@Param("pinSql") String pinSql);

    List<PointwarnEntity> selectSebPointwarnList(PointwarnEntity pointwarnEntity);
}
