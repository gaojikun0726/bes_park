package com.efounder.JEnterprise.controller.app.electriccurtain;

import com.core.common.ISSPReturnObject;
import com.core.common.util.JsonMapper;
import com.efounder.JEnterprise.model.app.electriccurtain.BESCurain;
import com.efounder.JEnterprise.service.app.electriccurtain.BESCurtainService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author wzx
 * @Date 2020-5-20 15:13:38
 */
@Controller
@RequestMapping(value = "/view/app/electriccurtain/curtain")
public class BESCurtainController {

    private static final Logger log = LoggerFactory.getLogger(BESCurtainController.class);

    @Autowired
    private BESCurtainService besCurtainService;
    @GetMapping("/showInitPage")
    public String showPage() {
        log.info("#BESCurtainController 进入电动窗帘页面");
        return "view/app/electriccurtain/curtain";
    }
    /**
     *
     * 筛选查询，分页查询
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.POST)
    public String getPaging(ModelMap map, Integer pageSize, Integer pageNum, BESCurain bescurain) {

        PageInfo<Object> page = besCurtainService.getPaging(pageSize, pageNum, bescurain);
        map.put("page", page);
        map.put("dataset", JsonMapper.toJsonString(page.getList()));
        map.put("pageSize", page.getPageSize() + "");
        return "view/app/electriccurtain/curtainPage";
    }
    /**
     *  查询电表配置信息
     * */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject query(BESCurain bescurain) {
        log.info("#查询电动窗帘配置信息");
        return besCurtainService.query(bescurain);
    }
    /**
     * 添加空调配置
     * */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject create(BESCurain bescurain) {
        log.info("#添加电动窗帘配置");
        return besCurtainService.create(bescurain);
    }

    /**
     * 更新电表配置信息
     * */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject update(BESCurain bescurain) {
        log.info("#更新电动窗帘配置信息");
        return besCurtainService.update(bescurain);
    }

    /**
     * 更新电表配置信息
     * */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ISSPReturnObject delete(BESCurain bescurain) {
        log.info("#删除电动窗帘配置信息");
        return besCurtainService.delete(bescurain);
    }

    /**
     *
     * @Description:文件上传 后台接收方法
     * @param request
     * @param multipartFile
     * @return
     * @throws Exception ISSPReturnObject
     */
    @RequestMapping(value = "/besCurtainFileUpload", method = RequestMethod.POST)
    @ResponseBody
    public Object fileUpload(HttpServletRequest request,
                             @RequestParam(required = false, value = "file") MultipartFile multipartFile) throws Exception {
        ISSPReturnObject returnObject = besCurtainService.impExcel(request, multipartFile);
        return returnObject;
    }

}
