package com.efounder.JEnterprise.service.datareported;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.datareported.DataUploadRecord;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 数据上报记录
 * @author xiepufeng
 * @date 2020/11/23 15:43
 */
public interface DataUploadRecordService
{
    ISSPReturnObject findNewDataRecord();

    void download(DataUploadRecord dataUploadRecord, HttpServletResponse response, HttpServletRequest request);

    PageInfo<DataUploadRecord> getArchiveManageListPage(String keywords, Integer pageNum);

    /**
     * 生成能耗数据包
     * @param startTime
     * @param endTime
     * @return
     */
    ISSPReturnObject createDataPackage(String startTime, String endTime);
}
