package com.efounder.JEnterprise.service.app;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.app.WkqConfigModel;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author wzx
 * @date  2020-5-12 14:14:45
 */
public interface WkqConfigService {

    ISSPReturnObject create(WkqConfigModel wkqConfigModel);

    PageInfo<Object> getPaging(Integer pageSize, Integer pageNum, WkqConfigModel wkqConfigModel);

    ISSPReturnObject query(WkqConfigModel wkqConfigModel);

    ISSPReturnObject update(WkqConfigModel wkqConfigModel);

    ISSPReturnObject delete(WkqConfigModel wkqConfigModel);

    /**
     * 导入excel数据
     * @param request
     * @param multipartFile
     * @return
     * @throws IOException
     */
    ISSPReturnObject impExcel(HttpServletRequest request,
                              @RequestParam(required = false, value = "file") MultipartFile multipartFile) throws IOException;
}
