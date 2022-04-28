package com.efounder.JEnterprise.controller.fileDownload;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.service.fileDownload.expExcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @CkassName: ExpExcelController
 * @Author: YangChao
 * @Date: 2019/1/21 10:11
 * @Descruotuib:
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/expExcel")
public class expExcelController {
    private static final Logger log = LoggerFactory.getLogger(expExcelController.class);

    @Autowired
    private expExcelService expExcelService;

    /**
    * @Author:         YangChao
    * @CreateDate:     2019/1/21 10:14
    * @Description:    通用导出后台
    */
    @RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject exportExcel(HttpServletRequest request) throws Exception {
         ISSPReturnObject returnObject = expExcelService.exportExcel(request);
        return returnObject;
    }

    /**
    * @Author:         YangChao
    * @CreateDate:     2019/1/31 16:31
    * @Description:    通用删除后台
    */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject delete(HttpServletRequest request) throws Exception {
        ISSPReturnObject returnObject = expExcelService.delete(request);
        return returnObject;
    }

    /**
    * @Author:         YangChao
    * @CreateDate:     2019/1/31 17:39
    * @Description:    通用查询接口
    */
    @RequestMapping(value = "/selectVlaues", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject selectVlaues(HttpServletRequest request) throws Exception {
        ISSPReturnObject returnObject = expExcelService.selectVlaues(request);
        return returnObject;
    }
}
