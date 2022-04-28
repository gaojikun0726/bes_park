package com.efounder.JEnterprise.controller.app;

import com.core.common.ISSPReturnObject;
import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.model.app.WkqConfigModel;
import com.efounder.JEnterprise.service.app.WkqConfigService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 空调配置
 * @author wzx
 */
@Controller
@RequestMapping(value = "/view/app/wkqConfig")
public class WkqConfigController {
	private static final Logger log = LoggerFactory.getLogger(WkqConfigController.class);

    @Resource
	private WkqConfigService wkqConfigService;

   @GetMapping("/page")
    public String showPage() {
        log.info("#ColdHeatSourceController 进入温控器监控页面");
        return "view/app/wkqConfig";
    }

    /**
     * 添加空调配置
     * */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject create(WkqConfigModel wkqConfigModel) {
        log.info("#添加温控器配置");
        return wkqConfigService.create(wkqConfigModel);
    }


    /**
     *
     * 筛选查询，分页查询
     */
    @RequestMapping(value = "/getPaging", method = RequestMethod.POST)
    public String getPaging(ModelMap map, Integer pageSize, Integer pageNum, WkqConfigModel wkqConfigModel) {

        PageInfo<Object> page = wkqConfigService.getPaging(pageSize, pageNum, wkqConfigModel);
        map.put("page", page);
        map.put("dataset", JsonMapper.toJsonString(page.getList()));
        map.put("pageSize", page.getPageSize() + "");
        return "/view/app/wkqConfigPage";
    }

    /**
     *  查询电表配置信息
     * */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject query(WkqConfigModel wkqConfigModel) {
        log.info("#查询温控器配置信息");
        return wkqConfigService.query(wkqConfigModel);
    }

    /**
     * 更新电表配置信息
     * */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject update(WkqConfigModel wkqConfigModel) {
        log.info("#更新温控器配置信息");
        return wkqConfigService.update(wkqConfigModel);
    }

    /**
     * 更新电表配置信息
     * */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject delete(WkqConfigModel wkqConfigModel) {
        log.info("#删除温控器配置信息");
        return wkqConfigService.delete(wkqConfigModel);
    }

    /**
     *
     * @Description:文件上传 后台接收方法
     * @param request
     * @param multipartFile
     * @return
     * @throws Exception ISSPReturnObject
     */
    @RequestMapping(value = "/besWkqFileUpload", method = RequestMethod.POST)
    @ResponseBody
    public Object fileUpload(HttpServletRequest request,
                             @RequestParam(required = false, value = "file") MultipartFile multipartFile) throws Exception {
        ISSPReturnObject returnObject = wkqConfigService.impExcel(request, multipartFile);
        return returnObject;
    }

}













