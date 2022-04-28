package com.efounder.JEnterprise.mapper.collectorJob;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.collectorJob.BESSysConf;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BesSysConfMapper extends BaseMapper<String, BESSysConf>{
    int deleteByPrimaryKey(String fGuid);

    int insert(BESSysConf record);

    int insertSelective(BESSysConf record);

    BESSysConf selectByPrimaryKey(String fGuid);

    int updateByPrimaryKeySelective(BESSysConf record);

    int updateByPrimaryKey(BESSysConf record);

	List<Map<String, Object>> queryMailByPark(String parkid);

	String selectSysConfAlarm();
}