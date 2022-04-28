package com.efounder.JEnterprise.service.app;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.app.TgLightConfig;
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
public interface TgLightConfigService {

    /**
     * 查询灯光配置列表
     * @param map
     * @return
     */
    Map queryTgLightConfigList(Map map,String pageSize,String pageNumber);


    /**
     * 分页查询
     * @param pageSize
     * @param pageNumber
     * @param map
     * @return
     */
    PageInfo<TgLightConfig> getPaging(Integer pageSize, Integer pageNumber, Map map);

    /**
     * 删除
     * @param id 主键
     * @return
     */
    boolean delete(String id);

    /**
     * 修改
     * @param tglightConfig
     * @return
     */
    boolean update(TgLightConfig tglightConfig);

    /**
     * 新增
     * @param tglightConfig
     * @return
     */
    boolean insert(TgLightConfig tglightConfig);

    /**
     * 查询
     * @param id
     * @return
     */
    TgLightConfig queryOne(String id);

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
