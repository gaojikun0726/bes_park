package com.efounder.JEnterprise.service.basedatamanage.eqmanage;

import com.core.common.ISSPReturnObject;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: wanghongjie
 * @Description:
 * @Date: Created in 8:28 2020/9/18
 * @Modified By:
 */
public interface BESSbdyExcelTableImportService {

    /**
     *
     * @Description: 设备配置的excel表格导入
     *
     * @auther: wanghongjie
     * @date: 11:08 2020/8/26
     * @param: [request, multipartFile]
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject impExcel(HttpServletRequest request,
                              @RequestParam(required = false, value = "file") MultipartFile multipartFile) throws IOException, Exception;

    /**
     * 同步缓存数据
     */
    void syncCache();

    /**
     *
     * @Description: 设备配置的导出
     *
     * @param: request
     * @return: com.core.common.ISSPReturnObject
     *
     */
    ISSPReturnObject exportPoint(HttpServletRequest request) throws IOException, Exception;
}
