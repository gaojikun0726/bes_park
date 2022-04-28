package com.efounder.JEnterprise.controller.app;

import com.core.common.ISSPReturnObject;
import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.model.app.SocketConfigModel;
import com.efounder.JEnterprise.service.app.SocketConfigService;
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
 * describe: 插座配置
 *
 * @author wzx
 * @date 2020-9-24 9:57:05
 */
@Controller
@RequestMapping("/view/app/socket")
public class SocketConfigController {

    @Resource
    private SocketConfigService socketConfigService;

    @RequestMapping("/socketConfig")
    public String view(){
        return "/view/app/socketConfig";
    }


    /**
     * 查询插座配置列表
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @RequestMapping("/querySocketConfigList")
    @ResponseBody
    public Map querySocketConfigList(String pageSize,String pageNumber){

        return socketConfigService.querySocketConfigList(new HashMap(),pageSize,pageNumber);
    }

    /**
     *
     * 筛选查询，分页查询
     */
    @RequestMapping(value = "/getPaging", method = RequestMethod.POST)
    public String getPaging(Map map, Integer pageSize, Integer pageNum) {

        PageInfo<SocketConfigModel> page = socketConfigService.getPaging(pageSize, pageNum, map);
        map.put("page", page);
        map.put("dataset", JsonMapper.toJsonString(page.getList()));
        map.put("pageSize", page.getPageSize() + "");
        return "/view/app/socketConfigPage";
    }

    /**
     * 删除
     * @param id 主键
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(String id){
        boolean result = socketConfigService.delete(id);
        return String.valueOf(result);
    }

    /**
     * 修改
     * @param socketConfig
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String update(SocketConfigModel socketConfig){
        boolean result = socketConfigService.update(socketConfig);
        return String.valueOf(result);
    }

    /**
     * 新增
     * @param socketConfig
     * @return
     */
    @RequestMapping("/insert")
    @ResponseBody
    public String insert(SocketConfigModel socketConfig){
        boolean result = socketConfigService.insert(socketConfig);
        return String.valueOf(result);
    }

    /**
     * 查询
     * @param id
     * @return
     */
    @RequestMapping("/queryOne")
    @ResponseBody
    public SocketConfigModel queryOne(String id){
        return socketConfigService.queryOne(id);
    }

    /**
     *
     * @Description:文件上传 后台接收方法
     * @param request
     * @param multipartFile
     * @return
     * @throws Exception ISSPReturnObject
     */
    @RequestMapping(value = "/besSocketFileUpload", method = RequestMethod.POST)
    @ResponseBody
    public Object fileUpload(HttpServletRequest request,
                             @RequestParam(required = false, value = "file") MultipartFile multipartFile) throws Exception {
        ISSPReturnObject returnObject = socketConfigService.impExcel(request, multipartFile);
        return returnObject;
    }
}
