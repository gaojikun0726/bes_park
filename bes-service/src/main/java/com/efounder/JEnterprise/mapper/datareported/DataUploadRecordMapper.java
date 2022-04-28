package com.efounder.JEnterprise.mapper.datareported;

import com.efounder.JEnterprise.model.datareported.DataUploadRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 数据上报记录
 * @author xiepufeng
 * @date 2020/11/23 15:45
 */
@Mapper
public interface DataUploadRecordMapper
{
    void insert(DataUploadRecord dataUploadRecord);

    void update(DataUploadRecord dataUploadRecord);

    DataUploadRecord findNewDataRecord();

    DataUploadRecord findById(Long id);

    List<DataUploadRecord> queryByParam(DataUploadRecord dataUploadRecord);
}
