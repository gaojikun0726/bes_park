package com.efounder.JEnterprise.service.datareported.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.datareported.DataUploadRecordMapper;
import com.efounder.JEnterprise.model.datareported.DataUploadRecord;
import com.efounder.JEnterprise.service.datareported.DataUploadRecordService;
import com.efounder.datareported.service.DataReportActuator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 数据上报记录
 * @author xiepufeng
 * @date 2020/11/23 15:46
 */
@Service
public class DataUploadRecordServiceImpl implements DataUploadRecordService
{
    @Resource
    private DataUploadRecordMapper dataUploadRecordMapper;

    @Autowired
    private DataReportActuator dataReportActuator;


    @Override
    public ISSPReturnObject findNewDataRecord()
    {

        ISSPReturnObject result = new ISSPReturnObject();

        try
        {
            DataUploadRecord dataUploadRecord = dataUploadRecordMapper.findNewDataRecord();

            if (dataUploadRecord == null)
            {
                result.setStatus("0");
                return result;
            }

            DataUploadRecord dur = new DataUploadRecord();

            dur.setFileName(dataUploadRecord.getFileName());
            dur.setId(dataUploadRecord.getId());

            result.setData(dur);
            result.setStatus("1");
            return result;

        } catch (Exception e)
        {
            e.printStackTrace();
            result.setStatus("0");
            return result;
        }
    }

    /**
     * 下载 zip
     * @param dataUploadRecord
     */
    @Override
    public void download(DataUploadRecord dataUploadRecord, HttpServletResponse response, HttpServletRequest request)
    {
        if (dataUploadRecord == null || dataUploadRecord.getId() == null)
        {
            dataUploadRecord = dataUploadRecordMapper.findNewDataRecord();
        }
        else
        {
            dataUploadRecord = dataUploadRecordMapper.findById(dataUploadRecord.getId());
        }

        if (dataUploadRecord == null)
        {
            return;
        }

        String fileName = dataUploadRecord.getFileName();
        String filePath = dataUploadRecord.getFilePath();


        String fullPath = filePath + File.separator + fileName + ".zip";

        File file = new File(fullPath);
        if (!file.exists())
        {
            return;
        }

        ServletContext context = request.getServletContext();

        // get MIME type of the file
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null)
        {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
            System.out.println("context getMimeType is null");
        }

        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) file.length());

        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue;


        try
        {
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        catch (UnsupportedEncodingException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        headerValue = String.format("attachment; filename=\"%s\"", fileName + ".zip");

        response.setHeader(headerKey, headerValue);

        // Copy the stream to the response's output stream.
        try
        {
            InputStream myStream = new FileInputStream(fullPath);
            IOUtils.copy(myStream, response.getOutputStream());
            response.flushBuffer();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        Integer downloadCount = dataUploadRecord.getDownloadCount();

        if (downloadCount == null)
        {
            downloadCount = 0;
        }

        dataUploadRecord.setDownloadCount(downloadCount + 1);

        dataUploadRecordMapper.update(dataUploadRecord);

    }

    /**
     * 分页查询
     * @param keywords
     * @param pageNum
     * @return
     */
    @Override
    public PageInfo<DataUploadRecord> getArchiveManageListPage(String keywords, Integer pageNum)
    {
        if (pageNum == null)
        {
            pageNum = 1;
        }

        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);

        DataUploadRecord dataUploadRecord = new DataUploadRecord();

        dataUploadRecord.setFileName(keywords);

        List<DataUploadRecord> list = dataUploadRecordMapper.queryByParam(dataUploadRecord);
        return new PageInfo<>(list);
    }

    /**
     * 生成能耗数据包
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public ISSPReturnObject createDataPackage(String startTime, String endTime) {

        ISSPReturnObject returnObject = new ISSPReturnObject();

        if (!StringUtils.hasText(startTime) || !StringUtils.hasText(endTime))
        {
            returnObject.setStatus("0");
            returnObject.setMsg("参数错误");
            return returnObject;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");

        try {
            Date startDate = sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);

            if (endDate.compareTo(startDate) < 0) {
                returnObject.setStatus("0");
                returnObject.setMsg("结束时间不能早于开始时间");
                return returnObject;
            }

            do {
                try {
                    dataReportActuator.generateDate(startDate);
                }catch (Exception e) {
                    e.printStackTrace();
                }

                Calendar calendar = Calendar.getInstance();

                calendar.setTime(startDate);

                calendar.add(Calendar.HOUR_OF_DAY,1);

                startDate = calendar.getTime();

            }while (startDate.compareTo(endDate) <= 0);

        } catch (ParseException e) {
            e.printStackTrace();
            returnObject.setStatus("0");
            return returnObject;
        }

        returnObject.setStatus("1");
        return returnObject;
    }
}
