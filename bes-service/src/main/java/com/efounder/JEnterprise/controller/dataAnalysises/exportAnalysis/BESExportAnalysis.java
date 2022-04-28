package com.efounder.JEnterprise.controller.dataAnalysises.exportAnalysis;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.dataAnalysises.exportAnalysis.BESExportReport;
import com.efounder.JEnterprise.service.dataAnalysises.BesExportAnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 杨超
 * @version 创建时间：2018年11月16日 下午2:12:36
 * @parameter 导出节能分析报表 poi编写
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/view/dataAnalysis")
public class BESExportAnalysis {
	private static final Logger log = LoggerFactory.getLogger(BESExportAnalysis.class);

	@Autowired
	private BesExportAnalysisService besexportanalysisservice; // 导出节能分析报表service

	/**
	 * @Description:报表导出首页
	 * @return
	 */
	@RequestMapping(value = "/Export_list", method = RequestMethod.GET)
	public String Export_list() {
		log.info("\r" + "BESExportAnalysis报表导出");
        return "view/dataAnalysis/exportAnalysis/export_report";
	}

	/**
     * 
     * @Title: getExportReport
     * @Description:获取报警监控信息
     * @return: String
     * @throws
     */
    @RequestMapping(value = "/getExportReport", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject getExportReport(BESExportReport dto) {
        ISSPReturnObject returnObject = besexportanalysisservice.getExportReport(dto);// 重写
        return returnObject;
    }
	
	/**
	 * 
	 * @Title: exportExcel
	 * @Description:导出excel
	 * @return: ISSPReturnObject
	 * @throws
	 */
    @RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject exportExcel(HttpServletRequest request, BESExportReport dto) throws Exception {
        ISSPReturnObject returnObject = besexportanalysisservice.exportExcel(request, dto);
        return returnObject;
    }

    /**
     * 
     * @author: YangChao
     * @createTime: 2019年1月15日 上午9:19:16
     * @Description:文件上传 后台接收方法
     * @param request
     * @param dto
     * @return
     * @throws Exception ISSPReturnObject
     */
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public Object fileUpload(HttpServletRequest request,
            @RequestParam(required = false, value = "file") MultipartFile multipartFile) throws Exception {
        ISSPReturnObject returnObject = besexportanalysisservice.impExcel(request, multipartFile);
        return returnObject;
    }

}
