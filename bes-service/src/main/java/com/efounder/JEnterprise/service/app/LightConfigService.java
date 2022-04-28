package com.efounder.JEnterprise.service.app;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.app.LightConfig;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * describe: 灯光配置
 *
 * @author zs
 * @date 2020/05/12
 */
public interface LightConfigService {

    /**
     * 查询灯光配置列表
     * @param map
     * @return
     */
    Map queryLightConfigList(Map map,String pageSize,String pageNumber);


    /**
     * 分页查询
     * @param pageSize
     * @param pageNumber
     * @param map
     * @return
     */
    PageInfo<LightConfig> getPaging(Integer pageSize, Integer pageNumber, Map map);

    /**
     * 删除
     * @param id 主键
     * @return
     */
    boolean delete(String id);

    /**
     * 修改
     * @param lightConfig
     * @return
     */
    boolean update(LightConfig lightConfig);

    /**
     * 新增
     * @param lightConfig
     * @return
     */
    boolean insert(LightConfig lightConfig);

    /**
     * 查询
     * @param id
     * @return
     */
    LightConfig queryOne(String id);

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
