package com.efounder.JEnterprise.service.fileDownload;

import com.core.common.ISSPReturnObject;

import javax.servlet.http.HttpServletRequest;

/**
 * @CkassName: expExcelService
 * @Author: YangChao
 * @Date: 2019/1/21 10:17
 * @Descruotuib:
 * @Version: 1.0
 **/
public interface expExcelService{

    /**
    * @Author:         YangChao
    * @CreateDate:     2019/1/21 11:17
    * @Description:    通用导出接口
    */
    ISSPReturnObject exportExcel(HttpServletRequest request) throws Exception;

    /**
    * @Author:         YangChao
    * @CreateDate:     2019/1/31 16:33
    * @Description:    通用删除接口
    */
    ISSPReturnObject delete(HttpServletRequest request) throws Exception;

    /**
    * @Author:         YangChao
    * @CreateDate:     2019/1/31 17:51
    * @Description:    通用查询接口
    */
    ISSPReturnObject selectVlaues(HttpServletRequest request) throws Exception;
}
