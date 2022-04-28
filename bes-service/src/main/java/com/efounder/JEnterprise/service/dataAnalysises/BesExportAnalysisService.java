package com.efounder.JEnterprise.service.dataAnalysises;
/**
* @author  杨超
* @version 创建时间：2018年11月16日 下午2:15:32
* @parameter 
* @version 1.0
*/

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.dataAnalysises.exportAnalysis.BESExportReport;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface BesExportAnalysisService {

    ISSPReturnObject getExportReport(BESExportReport dto);

    ISSPReturnObject exportExcel(HttpServletRequest request, BESExportReport dto) throws Exception;

    ISSPReturnObject impExcel(HttpServletRequest request,
            @RequestParam(required = false, value = "file") MultipartFile multipartFile) throws Exception;

}
