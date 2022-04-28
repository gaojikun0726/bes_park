package com.efounder.JEnterprise.controller.basedatamanage.eqmanage;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BESSbdyExcelTableImportService;
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
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 8:26 2020/9/18
 * @Modified By:
 */
@Controller
@RequestMapping(value = "/view/basesbdy/excel")
public class BESSbdyExcelTableImportController {
    private static final Logger log = LoggerFactory.getLogger(BESSbdyExcelTableImportController.class);

    @Autowired
    private BESSbdyExcelTableImportService besSbdyExcelTableImportService;

    /**
     * @Description: 设备配置的excel表格导入
     *
     * @auther: wanghongjie
     * @date: 11:05 2020/8/26
     * @param: [request, multipartFile]
     * @return: java.lang.Object  sbdyTypeImportExcel
     *
     */
    @RequestMapping(value = "/bessbdyTypeFileUpload", method = RequestMethod.POST)
    @ResponseBody
    public Object fileUpload(HttpServletRequest request,
                             @RequestParam(required = false, value = "file") MultipartFile multipartFile) throws Exception {
        ISSPReturnObject returnObject = besSbdyExcelTableImportService.impExcel(request, multipartFile);

        if("1".equals(returnObject.getStatus()))
        {
            besSbdyExcelTableImportService.syncCache();
        }

        return returnObject;
    }

    /**
     * @Description: 设备配置的导出
     *
     * @auther:
     * @param: request
     * @return: java.lang.Object  sbdyTypeImportExcel
     *
     */
    @RequestMapping(value = "/exportPoint", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject exportPoint(HttpServletRequest request) throws Exception {
        ISSPReturnObject returnObject = besSbdyExcelTableImportService.exportPoint(request);

        return returnObject;
    }

}
