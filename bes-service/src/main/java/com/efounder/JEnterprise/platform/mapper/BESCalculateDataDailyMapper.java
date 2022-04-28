package com.efounder.JEnterprise.platform.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BESCalculateDataDailyMapper {

    void insertBulkAmmeterData(List<Map<String, Object>> list);
}
