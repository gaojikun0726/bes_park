package com.efounder.JEnterprise.controller.app;

import com.core.common.ISSPReturnObject;
import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.model.app.AirconditioningConfigModel;
import com.efounder.JEnterprise.service.app.AirconditioningConfigService;
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
@RequestMapping(value = "/view/app/airconditioningConfig")
public class AirconditioningConfigController {
	private static final Logger log = LoggerFactory.getLogger(AirconditioningConfigController.class);

    @Resource
	private AirconditioningConfigService airconditioningConfigService;

   @GetMapping("/page")
    public String showPage() {
        log.info("#ColdHeatSourceController 进入空调监控页面");
        return "view/app/airconditioningConfig";
    }

    /**
     * 添加空调配置
     * */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject create(AirconditioningConfigModel airconditioningConfigModel) {
        log.info("#添加空调配置");
        return airconditioningConfigService.create(airconditioningConfigModel);
    }


    /**
     *
     * 筛选查询，分页查询
     */
    @RequestMapping(value = "/getPaging", method = RequestMethod.POST)
    public String getPaging(ModelMap map, Integer pageSize, Integer pageNum, AirconditioningConfigModel airconditioningConfigModel) {

        PageInfo<Object> page = airconditioningConfigService.getPaging(pageSize, pageNum, airconditioningConfigModel);
        map.put("page", page);
        map.put("dataset", JsonMapper.toJsonString(page.getList()));
        map.put("pageSize", page.getPageSize() + "");
        return "/view/app/airconditioningConfigPage";
    }

    /**
     *  查询电表配置信息
     * */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject query(AirconditioningConfigModel airconditioningConfigModel) {
        log.info("#查询空调配置信息");
        return airconditioningConfigService.query(airconditioningConfigModel);
    }

    /**
     * 更新电表配置信息
     * */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject update(AirconditioningConfigModel airconditioningConfigModel) {
        log.info("#更新空调配置信息");
        return airconditioningConfigService.update(airconditioningConfigModel);
    }

    /**
     * 更新电表配置信息
     * */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject delete(AirconditioningConfigModel airconditioningConfigModel) {
        log.info("#删除空调配置信息");
        return airconditioningConfigService.delete(airconditioningConfigModel);
    }

    /**
     *
     * @Description:文件上传 后台接收方法
     * @param request
     * @param multipartFile
     * @return
     * @throws Exception ISSPReturnObject
     */
    @RequestMapping(value = "/besAirconditioningFileUpload", method = RequestMethod.POST)
    @ResponseBody
    public Object fileUpload(HttpServletRequest request,
                             @RequestParam(required = false, value = "file") MultipartFile multipartFile) throws Exception {
        ISSPReturnObject returnObject = airconditioningConfigService.impExcel(request, multipartFile);
        return returnObject;
    }

}













