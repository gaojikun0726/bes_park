package com.efounder.JEnterprise.mapper.filedownload;

import com.efounder.JEnterprise.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface expExcelMapper extends BaseMapper<String, Object> {


    List<Map<String, Object>> expExcel(@Param("keys") String keys, @Param("tableName") String tablename);

    boolean Delete(@Param("id") String id,@Param("col") String col,@Param("TableName") String TableName);

    List<Map<String,Object>> selectVlaues(@Param("id") String id,@Param("col") String col,@Param("TableName") String TableName);
}

	