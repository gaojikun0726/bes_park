package com.efounder.JEnterprise.controller.datareported;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.datareported.DataUploadRecord;
import com.efounder.JEnterprise.service.datareported.DataUploadRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 数据上报记录
 * @author xiepufeng
 * @date 2020/11/23 15:40
 */

@CrossOrigin
@RequestMapping(value = "/dataCenter")
@Controller
public class DataUploadRecordController
{
    private static final Logger log = LoggerFactory.getLogger(DataUploadRecordController.class);

    @Autowired
    private DataUploadRecordService dataUploadRecordService;

    @RequestMapping(value = "/record", method = RequestMethod.GET)
    @ResponseBody
    public ISSPReturnObject findNewDataRecord() {
        log.info("#DataUploadRecordController 查询最新节能办能耗能耗上传记录");
        return dataUploadRecordService.findNewDataRecord();
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ResponseBody
    public void download(DataUploadRecord dataUploadRecord, HttpServletResponse response, HttpServletRequest request) {
        log.info("#DataUploadRecordController 下载节能办能耗能耗数据");
        dataUploadRecordService.download(dataUploadRecord, response, request);
    }
}
