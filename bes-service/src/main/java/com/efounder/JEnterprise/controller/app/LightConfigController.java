package com.efounder.JEnterprise.controller.app;

import com.core.common.ISSPReturnObject;
import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.model.app.LightConfig;
import com.efounder.JEnterprise.service.app.LightConfigService;
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
 * describe: 灯光配置
 *
 * @author zs
 * @date 2020/05/12
 */
@Controller
@RequestMapping("/view/app/light")
public class LightConfigController {

    @Resource
    private LightConfigService lightConfigService;

    @RequestMapping("/lightConfig")
    public String view(){
        return "/view/app/light/lightConfig";
    }


    /**
     * 查询灯光配置列表
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @RequestMapping("/queryLightConfigList")
    @ResponseBody
    public Map queryLightConfigList(String pageSize,String pageNumber){

        return lightConfigService.queryLightConfigList(new HashMap(),pageSize,pageNumber);
    }

    /**
     *
     * 筛选查询，分页查询
     */
    @RequestMapping(value = "/getPaging", method = RequestMethod.POST)
    public String getPaging(Map map, Integer pageSize, Integer pageNum) {

        PageInfo<LightConfig> page = lightConfigService.getPaging(pageSize, pageNum, map);
        map.put("page", page);
        map.put("dataset", JsonMapper.toJsonString(page.getList()));
        map.put("pageSize", page.getPageSize() + "");
        return "/view/app/light/lightConfigPage";
    }

    /**
     * 删除
     * @param id 主键
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(String id){
        boolean result = lightConfigService.delete(id);
        return String.valueOf(result);
    }

    /**
     * 修改
     * @param lightConfig
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String update(LightConfig lightConfig){
        boolean result = lightConfigService.update(lightConfig);
        return String.valueOf(result);
    }

    /**
     * 新增
     * @param lightConfig
     * @return
     */
    @RequestMapping("/insert")
    @ResponseBody
    public String insert(LightConfig lightConfig){
        boolean result = lightConfigService.insert(lightConfig);
        return String.valueOf(result);
    }

    /**
     * 查询
     * @param id
     * @return
     */
    @RequestMapping("/queryOne")
    @ResponseBody
    public LightConfig queryOne(String id){
        return lightConfigService.queryOne(id);
    }

    /**
     *
     * @Description:文件上传 后台接收方法
     * @param request
     * @param multipartFile
     * @return
     * @throws Exception ISSPReturnObject
     */
    @RequestMapping(value = "/besLightFileUpload", method = RequestMethod.POST)
    @ResponseBody
    public Object fileUpload(HttpServletRequest request,
                             @RequestParam(required = false, value = "file") MultipartFile multipartFile) throws Exception {
        ISSPReturnObject returnObject = lightConfigService.impExcel(request, multipartFile);
        return returnObject;
    }
}
