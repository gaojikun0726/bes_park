package com.efounder.JEnterprise.controller.app;

import com.core.common.ISSPReturnObject;
import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.model.app.TgLightConfig;
import com.efounder.JEnterprise.service.app.TgLightConfigService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * describe: 可调光灯光配置
 *
 * @author zs
 * @date 2020/05/12
 */
@Controller
@RequestMapping("/view/app/tglight")
public class TgLightConfigController {

    @Resource
    private TgLightConfigService tglightConfigService;

    @RequestMapping("/tglightConfig")
    public String view(){
        return "/view/app/light/tglightConfig";
    }


    /**
     * 查询可调光灯光配置列表
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @RequestMapping("/queryTgLightConfigList")
    @ResponseBody
    public Map queryLightConfigList(String pageSize,String pageNumber){

        return tglightConfigService.queryTgLightConfigList(new HashMap(),pageSize,pageNumber);
    }

    /**
     *
     * 筛选查询，分页查询
     */
    @RequestMapping(value = "/getPaging", method = RequestMethod.POST)
    public String getPaging(Map map, Integer pageSize, Integer pageNum) {

        PageInfo<TgLightConfig> page = tglightConfigService.getPaging(pageSize, pageNum, map);
        map.put("page", page);
        map.put("dataset", JsonMapper.toJsonString(page.getList()));
        map.put("pageSize", page.getPageSize() + "");
        return "/view/app/light/tglightConfigPage";
    }

    /**
     * 删除
     * @param id 主键
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(String id){
        boolean result = tglightConfigService.delete(id);
        return String.valueOf(result);
    }

    /**
     * 修改
     * @param tglightConfig
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String update(TgLightConfig tglightConfig){
        boolean result = tglightConfigService.update(tglightConfig);
        return String.valueOf(result);
    }

    /**
     * 新增
     * @param tglightConfig
     * @return
     */
    @RequestMapping("/insert")
    @ResponseBody
    public String insert(TgLightConfig tglightConfig){
        boolean result = tglightConfigService.insert(tglightConfig);
        return String.valueOf(result);
    }

    /**
     * 查询
     * @param id
     * @return
     */
    @RequestMapping("/queryOne")
    @ResponseBody
    public TgLightConfig queryOne(String id){
        return tglightConfigService.queryOne(id);
    }

    /**
     *
     * @Description:文件上传 后台接收方法
     * @param request
     * @param multipartFile
     * @return
     * @throws Exception ISSPReturnObject
     */
    @RequestMapping(value = "/besTgLightFileUpload", method = RequestMethod.POST)
    @ResponseBody
    public Object fileUpload(HttpServletRequest request,
                             @RequestParam(required = false, value = "file") MultipartFile multipartFile) throws Exception {
        ISSPReturnObject returnObject = tglightConfigService.impExcel(request, multipartFile);
        return returnObject;
    }
}
